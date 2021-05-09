package rcarmstrong20.vanilla_expansions.inventory.container;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.util.IWorldPosCallable;
import rcarmstrong20.vanilla_expansions.core.VeBlocks;
import rcarmstrong20.vanilla_expansions.core.VeContainerTypes;

public class VeTransmutationTableContainer extends Container
{
    private IWorldPosCallable worldPosCallable;

    public VeTransmutationTableContainer(int windowIdIn, PlayerInventory playerInventoryIn)
    {
        this(windowIdIn, playerInventoryIn, IWorldPosCallable.NULL);
    }

    public VeTransmutationTableContainer(int windowIdIn, PlayerInventory playerInventoryIn,
            final IWorldPosCallable worldPosCallableIn)
    {
        super(VeContainerTypes.transmutationTable, windowIdIn);
        this.worldPosCallable = worldPosCallableIn;
    }

    @Override
    public boolean stillValid(PlayerEntity playerIn)
    {
        return stillValid(this.worldPosCallable, playerIn, VeBlocks.transmutationTable);
    }
}
