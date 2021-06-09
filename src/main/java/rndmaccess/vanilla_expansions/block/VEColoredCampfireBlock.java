package rndmaccess.vanilla_expansions.block;

import java.util.Optional;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.CampfireBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CampfireCookingRecipe;
import net.minecraft.particles.IParticleData;
import net.minecraft.state.StateContainer.Builder;
import net.minecraft.stats.Stats;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import rndmaccess.vanilla_expansions.tile_entity.VEColoredCampfireTileEntity;

public class VEColoredCampfireBlock extends CampfireBlock
{
    private final IParticleData particle;

    public VEColoredCampfireBlock(IParticleData particle, Properties properties)
    {
        super(true, 1, properties);
        this.particle = particle;
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void animateTick(BlockState stateIn, World worldIn, BlockPos pos, Random rand)
    {
        if (stateIn.getValue(LIT))
        {
            if (rand.nextInt(10) == 0)
            {
                worldIn.playLocalSound(pos.getX() + 0.5D, pos.getY() + 0.5D, pos.getZ() + 0.5D,
                        SoundEvents.CAMPFIRE_CRACKLE, SoundCategory.BLOCKS, 0.5F + rand.nextFloat(),
                        rand.nextFloat() * 0.7F + 0.6F, false);
            }

            if (rand.nextInt(5) == 0)
            {
                for (int i = 0; i < rand.nextInt(1) + 1; ++i)
                {
                    worldIn.addParticle(particle, pos.getX() + 0.5D, pos.getY() + 0.5D, pos.getZ() + 0.5D,
                            rand.nextFloat() / 2.0F, 5.0E-5D, rand.nextFloat() / 2.0F);
                }
            }
        }
    }

    /**
     * Called when the player right-clicks a block.
     */

    @Override
    public ActionResultType use(BlockState state, World worldIn, BlockPos pos, PlayerEntity playerIn, Hand handIn,
            BlockRayTraceResult hit)
    {
        TileEntity tileEntity = worldIn.getBlockEntity(pos);

        if (tileEntity instanceof VEColoredCampfireTileEntity)
        {
            VEColoredCampfireTileEntity campfireTileEntity = (VEColoredCampfireTileEntity) tileEntity;
            ItemStack itemStack = playerIn.getItemInHand(handIn);
            Optional<CampfireCookingRecipe> optional = campfireTileEntity.getCookableRecipe(itemStack);

            if (optional.isPresent())
            {
                if (!worldIn.isClientSide() && campfireTileEntity.placeFood(
                        playerIn.abilities.instabuild ? itemStack.copy() : itemStack, optional.get().getCookingTime()))
                {
                    playerIn.awardStat(Stats.INTERACT_WITH_CAMPFIRE);
                    return ActionResultType.SUCCESS;
                }
                return ActionResultType.CONSUME;
            }
        }
        return ActionResultType.PASS;
    }

    /**
     * Called when the player right-clicks this block with a new block.
     */
    @Override
    public void onRemove(BlockState state, World worldIn, BlockPos pos, BlockState newState, boolean isMoving)
    {
        if (state.getBlock() != newState.getBlock())
        {
            TileEntity tileEntity = worldIn.getBlockEntity(pos);

            if (tileEntity instanceof VEColoredCampfireTileEntity)
            {
                VEColoredCampfireTileEntity campfireTileEntity = (VEColoredCampfireTileEntity) tileEntity;
                InventoryHelper.dropContents(worldIn, pos, campfireTileEntity.getItems());
            }
            super.onRemove(state, worldIn, pos, newState, isMoving);
        }
    }

    @Override
    public TileEntity newBlockEntity(IBlockReader world)
    {
        return new VEColoredCampfireTileEntity();
    }

    @Override
    protected void createBlockStateDefinition(Builder<Block, BlockState> builder)
    {
        builder.add(LIT, SIGNAL_FIRE, WATERLOGGED, FACING);
    }
}
