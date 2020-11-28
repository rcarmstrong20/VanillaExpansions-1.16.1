package rcarmstrong20.vanilla_expansions.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.Direction.Axis;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import rcarmstrong20.vanilla_expansions.util.VeCollisionUtil;

public class VePersianCatPlushBlock extends VeCatPlushBlock
{
    protected static final VoxelShape PERSIAN_NORTH_NOSE_SHAPE = Block.makeCuboidShape(7.5D, 5.0D, 0.5D, 8.5D, 6.0D,
            1.0D);

    protected static final VoxelShape PERSIAN_NORTH_SHAPE = VoxelShapes.or(CAT_NORTH_BODY_SHAPE,
            PERSIAN_NORTH_NOSE_SHAPE);

    protected static final VoxelShape PERSIAN_SOUTH_SHAPE = VeCollisionUtil.rotate180(Axis.Y, PERSIAN_NORTH_SHAPE);
    protected static final VoxelShape PERSIAN_WEST_SHAPE = VeCollisionUtil.rotate270(Axis.Y, PERSIAN_NORTH_SHAPE);
    protected static final VoxelShape PERSIAN_EAST_SHAPE = VeCollisionUtil.rotate90(Axis.Y, PERSIAN_NORTH_SHAPE);

    public VePersianCatPlushBlock(Properties properties)
    {
        super(properties);
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context)
    {
        return defineShapes(state, PERSIAN_NORTH_SHAPE, PERSIAN_SOUTH_SHAPE, PERSIAN_WEST_SHAPE, PERSIAN_EAST_SHAPE);
    }
}
