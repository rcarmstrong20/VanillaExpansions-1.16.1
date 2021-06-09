package rndmaccess.vanilla_expansions.block;

import javax.annotation.Nullable;

import net.minecraft.block.BlockState;
import net.minecraft.block.StonecutterBlock;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.inventory.container.SimpleNamedContainerProvider;
import net.minecraft.util.IWorldPosCallable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import rndmaccess.vanilla_expansions.inventory.container.VEWoodcutterContainer;

public class VEWoodcutterBlock extends StonecutterBlock
{
    private static final TranslationTextComponent NAME = new TranslationTextComponent("container.woodcutter");

    public VEWoodcutterBlock(Properties properties)
    {
        super(properties);
    }

    @Override
    @Nullable
    public INamedContainerProvider getMenuProvider(BlockState state, World worldIn, BlockPos pos)
    {
        return new SimpleNamedContainerProvider((windowId, playerInventory, p_220283_4_) ->
        {
            return new VEWoodcutterContainer(windowId, playerInventory, IWorldPosCallable.create(worldIn, pos));
        }, NAME);
    }
}
