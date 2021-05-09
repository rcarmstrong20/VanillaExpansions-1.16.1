package rcarmstrong20.vanilla_expansions.block;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.inventory.container.SimpleNamedContainerProvider;
import net.minecraft.util.IWorldPosCallable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import rcarmstrong20.vanilla_expansions.inventory.container.VeTransmutationTableContainer;

public class VeTransmutationTableBlock extends Block
{
    private static final TranslationTextComponent NAME = new TranslationTextComponent("container.transmutation_table");

    public VeTransmutationTableBlock(Properties properties)
    {
        super(properties);
    }

    @Override
    @Nullable
    public INamedContainerProvider getMenuProvider(BlockState state, World worldIn, BlockPos pos)
    {
        return new SimpleNamedContainerProvider((windowId, playerInventory, p_220283_4_) ->
        {
            return new VeTransmutationTableContainer(windowId, playerInventory, IWorldPosCallable.create(worldIn, pos));
        }, NAME);
    }
}
