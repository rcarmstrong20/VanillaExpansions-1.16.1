package rcarmstrong20.vanilla_expansions.tile_entity;

import java.util.Optional;
import java.util.Random;

import javax.annotation.Nullable;

import net.minecraft.block.BlockState;
import net.minecraft.block.CampfireBlock;
import net.minecraft.inventory.IClearable;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CampfireCookingRecipe;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import rcarmstrong20.vanilla_expansions.block.VeColoredCampfireBlock;
import rcarmstrong20.vanilla_expansions.core.VeTileEntityType;

public class VeColoredCampfireTileEntity extends TileEntity implements IClearable, ITickableTileEntity
{
    private NonNullList<ItemStack> items = NonNullList.withSize(4, ItemStack.EMPTY);
    private int[] cookingProgress = new int[4];
    private int[] cookingTime = new int[4];

    public VeColoredCampfireTileEntity()
    {
        super(VeTileEntityType.coloredCampfire);
    }

    @Override
    public void tick()
    {
        boolean flag = this.getBlockState().getValue(VeColoredCampfireBlock.LIT);
        boolean flag1 = this.level.isClientSide();

        if (flag1)
        {
            if (flag)
            {
                this.makeParticles();
            }
        }
        else
        {
            if (flag)
            {
                this.cook();
            }
            else
            {
                for (int i = 0; i < this.items.size(); ++i)
                {
                    if (this.cookingProgress[i] > 0)
                    {
                        this.cookingProgress[i] = MathHelper.clamp(this.cookingProgress[i] - 2, 0, this.cookingTime[i]);
                    }
                }
            }
        }
    }

    /**
     * Individually tracks the cooking of each item, then spawns the finished
     * product in-world and clears the corresponding held item.
     */
    private void cook()
    {
        for (int i = 0; i < this.items.size(); ++i)
        {
            ItemStack itemstack = this.items.get(i);

            if (!itemstack.isEmpty())
            {
                this.cookingProgress[i]++;
                if (this.cookingProgress[i] >= this.cookingTime[i])
                {
                    IInventory iinventory = new Inventory(itemstack);
                    ItemStack itemstack1 = this.level.getRecipeManager()
                            .getRecipeFor(IRecipeType.CAMPFIRE_COOKING, iinventory, this.level).map((recipe) ->
                            {
                                return recipe.assemble(iinventory);
                            }).orElse(itemstack);
                    BlockPos blockpos = this.getBlockPos();
                    InventoryHelper.dropItemStack(this.level, blockpos.getX(), blockpos.getY(), blockpos.getZ(),
                            itemstack1);
                    this.items.set(i, ItemStack.EMPTY);
                    this.markUpdated();
                }
            }
        }
    }

    private void makeParticles()
    {
        World world = this.getLevel();
        if (world != null)
        {
            BlockPos blockpos = this.getBlockPos();
            Random random = world.random;
            if (random.nextFloat() < 0.11F)
            {
                for (int i = 0; i < random.nextInt(2) + 2; ++i)
                {
                    CampfireBlock.makeParticles(world, blockpos,
                            this.getBlockState().getValue(CampfireBlock.SIGNAL_FIRE), false);
                }
            }

            int l = this.getBlockState().getValue(CampfireBlock.FACING).get2DDataValue();

            for (int j = 0; j < this.items.size(); ++j)
            {
                if (!this.items.get(j).isEmpty() && random.nextFloat() < 0.2F)
                {
                    Direction direction = Direction.from2DDataValue(Math.floorMod(j + l, 4));
                    double d0 = blockpos.getX() + 0.5D - direction.getStepX() * 0.3125F
                            + direction.getClockWise().getStepX() * 0.3125F;
                    double d1 = blockpos.getY() + 0.5D;
                    double d2 = blockpos.getZ() + 0.5D - direction.getStepZ() * 0.3125F
                            + direction.getClockWise().getStepZ() * 0.3125F;

                    for (int k = 0; k < 4; ++k)
                    {
                        world.addParticle(ParticleTypes.SMOKE, d0, d1, d2, 0.0D, 5.0E-4D, 0.0D);
                    }
                }
            }
        }
    }

    /**
     * Returns a NonNullList<ItemStack> of items currently held in the colored
     * campfire.
     */
    public NonNullList<ItemStack> getItems()
    {
        return this.items;
    }

    @Override
    public void load(BlockState state, CompoundNBT compound)
    {
        super.load(state, compound);
        this.items.clear();
        ItemStackHelper.loadAllItems(compound, this.items);
        if (compound.contains("CookingTimes", 11))
        {
            int[] aint = compound.getIntArray("CookingTimes");
            System.arraycopy(aint, 0, this.cookingProgress, 0, Math.min(this.cookingTime.length, aint.length));
        }

        if (compound.contains("CookingTotalTimes", 11))
        {
            int[] aint1 = compound.getIntArray("CookingTotalTimes");
            System.arraycopy(aint1, 0, this.cookingTime, 0, Math.min(this.cookingTime.length, aint1.length));
        }
    }

    @Override
    public CompoundNBT save(CompoundNBT compound)
    {
        this.saveMetadataAndItems(compound);
        compound.putIntArray("CookingTimes", this.cookingProgress);
        compound.putIntArray("CookingTotalTimes", this.cookingTime);
        return compound;
    }

    private CompoundNBT saveMetadataAndItems(CompoundNBT compound)
    {
        super.save(compound);
        ItemStackHelper.saveAllItems(compound, this.items, true);
        return compound;
    }

    @Override
    @Nullable
    public SUpdateTileEntityPacket getUpdatePacket()
    {
        return new SUpdateTileEntityPacket(this.worldPosition, 13, this.getUpdateTag());
    }

    @Override
    public CompoundNBT getUpdateTag()
    {
        return this.saveMetadataAndItems(new CompoundNBT());
    }

    @Override
    public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket pkt)
    {
        CompoundNBT compound = pkt.getTag();
        this.load(this.getBlockState(), compound);
    }

    public Optional<CampfireCookingRecipe> getCookableRecipe(ItemStack itemstackIn)
    {
        return this.items.stream().noneMatch(ItemStack::isEmpty) ? Optional.empty()
                : this.level.getRecipeManager().getRecipeFor(IRecipeType.CAMPFIRE_COOKING, new Inventory(itemstackIn),
                        this.level);
    }

    public boolean placeFood(ItemStack itemstackIn, int cookTimeIn)
    {
        for (int i = 0; i < this.items.size(); ++i)
        {
            ItemStack itemstack = this.items.get(i);
            if (itemstack.isEmpty())
            {
                this.cookingTime[i] = cookTimeIn;
                this.cookingProgress[i] = 0;
                this.items.set(i, itemstackIn.split(1));
                this.markUpdated();
                return true;
            }
        }
        return false;
    }

    private void markUpdated()
    {
        this.setChanged();
        this.getLevel().sendBlockUpdated(this.getBlockPos(), this.getBlockState(), this.getBlockState(), 3);
    }

    @Override
    public void clearContent()
    {
        this.items.clear();
    }

    public void dowse()
    {
        if (this.level != null)
        {
            if (!this.level.isClientSide())
            {
                InventoryHelper.dropContents(this.level, this.getBlockPos(), this.getItems());
            }
            this.markUpdated();
        }
    }
}
