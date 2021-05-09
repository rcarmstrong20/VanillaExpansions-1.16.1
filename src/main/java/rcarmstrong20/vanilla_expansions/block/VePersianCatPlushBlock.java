package rcarmstrong20.vanilla_expansions.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.Direction.Axis;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import rcarmstrong20.vanilla_expansions.util.VeBoxUtil;

public class VePersianCatPlushBlock extends VeCatPlushBlock
{
    protected static final VoxelShape NORTH_NOSE = Block.box(7.5D, 5.0D, 0.5D, 8.5D, 6.0D, 1.0D);
    protected static final VoxelShape NORTH_SHAPE = VoxelShapes.or(NORTH_BODY, NORTH_NOSE);
    protected static final VoxelShape SOUTH_SHAPE = VeBoxUtil.rotate180(Axis.Y, NORTH_SHAPE);
    protected static final VoxelShape WEST_SHAPE = VeBoxUtil.rotate270(Axis.Y, NORTH_SHAPE);
    protected static final VoxelShape EAST_SHAPE = VeBoxUtil.rotate90(Axis.Y, NORTH_SHAPE);

    public VePersianCatPlushBlock(Properties properties)
    {
        super(properties);
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context)
    {
        return defineShapes(state, NORTH_SHAPE, SOUTH_SHAPE, WEST_SHAPE, EAST_SHAPE);
    }
}
