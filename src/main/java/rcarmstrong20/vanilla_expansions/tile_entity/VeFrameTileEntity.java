package rcarmstrong20.vanilla_expansions.tile_entity;

import javax.annotation.Nullable;

import net.minecraft.block.BlockState;
import net.minecraft.inventory.IClearable;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.NonNullList;
import rcarmstrong20.vanilla_expansions.core.VeTileEntityType;

public class VeFrameTileEntity extends TileEntity implements IClearable
{
    private NonNullList<ItemStack> inventory = NonNullList.withSize(1, ItemStack.EMPTY);

    public VeFrameTileEntity()
    {
        super(VeTileEntityType.frame);
    }

    @Override
    public void func_230337_a_(BlockState state, CompoundNBT compound) // Same as read
    {
        super.func_230337_a_(state, compound); // Same as read
        this.inventory.clear();
        ItemStackHelper.loadAllItems(compound, this.inventory);
    }

    @Override
    public CompoundNBT write(CompoundNBT compound)
    {
        super.write(compound);
        ItemStackHelper.saveAllItems(compound, this.inventory, true);
        return compound;
    }

    @Nullable
    public SUpdateTileEntityPacket getUpdatePacket()
    {
        return new SUpdateTileEntityPacket(this.getPos(), 1, this.getUpdateTag());
    }

    @Override
    public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket pkt)
    {
        CompoundNBT compound = pkt.getNbtCompound();
        this.func_230337_a_(this.getBlockState(), compound); // Same as read
    }

    @Override
    public CompoundNBT getUpdateTag()
    {
        return this.write(new CompoundNBT());
    }

    /*
     * Add an item to the specified index in the inventory list
     */
    public boolean addItem(ItemStack stack)
    {
        if (!stack.isEmpty())
        {
            this.inventory.set(0, stack.split(1));
            this.inventoryChanged();

            return true;
        }
        return false;
    }

    private void inventoryChanged()
    {
        this.markDirty();
        this.getWorld().notifyBlockUpdate(this.getPos(), this.getBlockState(), this.getBlockState(), 3);
    }

    /**
     * Returns a NonNullList<ItemStack> of items currently held in the double slab.
     */
    public NonNullList<ItemStack> getInventory()
    {
        return this.inventory;
    }

    @Override
    public double getMaxRenderDistanceSquared()
    {
        // A temporary value so the tile entity doesn't unrender when the player goes
        // far away.
        return 100000D;
    }

    @Override
    public void clear()
    {
        this.inventory.clear();
    }

    public void dropAllItems()
    {
        if (!this.getWorld().isRemote)
        {
            InventoryHelper.dropItems(this.getWorld(), this.getPos(), this.getInventory());
        }
        this.inventoryChanged();
    }
}
