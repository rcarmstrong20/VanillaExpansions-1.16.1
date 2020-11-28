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

public class VeSilverfishPlushBlock extends VePlushBlock
{
    protected static final VoxelShape SILVERFISH_NORTH_HEAD_BODY_SHAPE = Block.makeCuboidShape(6.5D, 0.0D, 1.0D, 9.5D,
            2.5D, 2.0D);
    protected static final VoxelShape SILVERFISH_NORTH_FRONT_BODY_SHAPE = Block.makeCuboidShape(6.0D, 0.0D, 2.0D, 10.0D,
            3.0D, 4.0D);
    protected static final VoxelShape SILVERFISH_NORTH_MIDDLE_BODY_SHAPE = Block.makeCuboidShape(5.5D, 0.0D, 4.0D,
            10.5D, 4.0D, 9.0D);
    protected static final VoxelShape SILVERFISH_NORTH_BACK1_BODY_SHAPE = Block.makeCuboidShape(6.5D, 0.0D, 9.0D, 9.5D,
            3.0D, 11.0D);
    protected static final VoxelShape SILVERFISH_NORTH_BACK2_BODY_SHAPE = Block.makeCuboidShape(7.0D, 0.0D, 11.0D, 9.0D,
            2.0D, 12.0D);
    protected static final VoxelShape SILVERFISH_NORTH_TAIL_SHAPE = Block.makeCuboidShape(7.5D, 0.0D, 12.0D, 8.5D, 1.0D,
            14.0D);
    protected static final VoxelShape SILVERFISH_NORTH_FRONT_TOP_TUFF_BOTTOM_SHAPE = Block.makeCuboidShape(7.0D, 3.0D,
            2.0D, 9.0D, 4.0D, 3.0D);
    protected static final VoxelShape SILVERFISH_NORTH_FRONT_TOP_TUFF_TOP_SHAPE = Block.makeCuboidShape(6.0D, 4.0D,
            2.0D, 7.0D, 5.0D, 3.0D);
    protected static final VoxelShape SILVERFISH_NORTH_FRONT_RIGHT_TUFF_BOTTOM_SHAPE = Block.makeCuboidShape(5.0D, 0.0D,
            2.0D, 6.0D, 1.0D, 3.0D);
    protected static final VoxelShape SILVERFISH_NORTH_FRONT_RIGHT_TUFF_TOP_SHAPE = Block.makeCuboidShape(4.0D, 1.0D,
            2.0D, 5.0D, 2.0D, 3.0D);
    protected static final VoxelShape SILVERFISH_NORTH_FRONT_LEFT_TUFF_BOTTOM_SHAPE = Block.makeCuboidShape(10.0D, 0.0D,
            2.0D, 11.0D, 1.0D, 3.0D);
    protected static final VoxelShape SILVERFISH_NORTH_FRONT_LEFT_TUFF_TOP_SHAPE = Block.makeCuboidShape(11.0D, 1.0D,
            2.0D, 12.0D, 2.0D, 3.0D);
    protected static final VoxelShape SILVERFISH_NORTH_MIDDLE_FRONT_TOP_BOTTOM_LEFT_TUFF_SHAPE = Block
            .makeCuboidShape(10.5D, 3.0D, 4.0D, 11.5D, 4.0D, 5.0D);
    protected static final VoxelShape SILVERFISH_NORTH_MIDDLE_FRONT_TOP_TOP_LEFT_TUFF_SHAPE = Block
            .makeCuboidShape(11.5D, 4.0D, 4.0D, 12.5D, 5.0D, 5.0D);
    protected static final VoxelShape SILVERFISH_NORTH_MIDDLE_FRONT_BOTTOM_BOTTOM_LEFT_TUFF_SHAPE = Block
            .makeCuboidShape(10.5D, 0.0D, 4.0D, 12.0D, 1.0D, 5.0D);
    protected static final VoxelShape SILVERFISH_NORTH_MIDDLE_FRONT_BOTTOM_TOP_LEFT_TUFF_SHAPE = Block
            .makeCuboidShape(12.0D, 1.0D, 4.0D, 13.0D, 2.0D, 5.0D);
    protected static final VoxelShape SILVERFISH_NORTH_MIDDLE_FRONT_BOTTOM_RIGHT_TUFF_SHAPE = Block
            .makeCuboidShape(4.0D, 0.0D, 4.0D, 5.5D, 1.0D, 5.0D);
    protected static final VoxelShape SILVERFISH_NORTH_MIDDLE_FRONT_TOP_RIGHT_TUFF_SHAPE = Block.makeCuboidShape(4.5D,
            4.0D, 4.0D, 5.5D, 5.0D, 5.0D);
    protected static final VoxelShape SILVERFISH_NORTH_MIDDLE_FRONT_TOP_BOTTOM_TUFF_SHAPE = Block.makeCuboidShape(6.5D,
            4.0D, 4.0D, 8.5D, 5.0D, 5.0D);
    protected static final VoxelShape SILVERFISH_NORTH_MIDDLE_FRONT_TOP_TOP_TUFF_SHAPE = Block.makeCuboidShape(8.5D,
            5.0D, 4.0D, 9.5D, 7.0D, 5.0D);
    protected static final VoxelShape SILVERFISH_NORTH_MIDDLE_BACK_LEFT_TUFF_BOTTOM_SHAPE = Block.makeCuboidShape(10.5D,
            1.0D, 8.0D, 12.5D, 2.0D, 9.0D);
    protected static final VoxelShape SILVERFISH_NORTH_MIDDLE_BACK_LEFT_TUFF_TOP_BOTTOM_SHAPE = Block
            .makeCuboidShape(10.5D, 4.0D, 8.0D, 11.5D, 5.0D, 9.0D);
    protected static final VoxelShape SILVERFISH_NORTH_MIDDLE_BACK_LEFT_TUFF_TOP_TOP_SHAPE = Block
            .makeCuboidShape(11.5D, 5.0D, 8.0D, 12.5D, 6.0D, 9.0D);
    protected static final VoxelShape SILVERFISH_NORTH_MIDDLE_BACK_RIGHT_TUFF_BOTTOM_SHAPE = Block.makeCuboidShape(4.5D,
            1.0D, 8.0D, 5.5D, 2.0D, 9.0D);
    protected static final VoxelShape SILVERFISH_NORTH_MIDDLE_BACK_RIGHT_TUFF_TOP_SHAPE = Block.makeCuboidShape(4.5D,
            3.0D, 8.0D, 5.5D, 4.0D, 9.0D);
    protected static final VoxelShape SILVERFISH_NORTH_MIDDLE_TOP_RIGHT_TUFF_BOTTOM_SHAPE = Block.makeCuboidShape(6.5D,
            4.0D, 8.0D, 7.5D, 5.0D, 9.0D);
    protected static final VoxelShape SILVERFISH_NORTH_MIDDLE_TOP_RIGHT_TUFF_TOP_SHAPE = Block.makeCuboidShape(5.5D,
            5.0D, 8.0D, 6.5D, 6.0D, 9.0D);
    protected static final VoxelShape SILVERFISH_NORTH_BACK2_LEFT_SHAPE = Block.makeCuboidShape(9.0D, 2.0D, 11.0D,
            10.0D, 3.0D, 12.0D);
    protected static final VoxelShape SILVERFISH_NORTH_BACK2_RIGHT_BOTTOM_SHAPE = Block.makeCuboidShape(5.0D, 1.0D,
            11.0D, 7.0D, 2.0D, 12.0D);
    protected static final VoxelShape SILVERFISH_NORTH_BACK2_RIGHT_TOP1_SHAPE = Block.makeCuboidShape(6.0D, 2.0D, 11.0D,
            7.0D, 3.0D, 12.0D);
    protected static final VoxelShape SILVERFISH_NORTH_BACK2_RIGHT_TOP2_SHAPE = Block.makeCuboidShape(7.0D, 3.0D, 11.0D,
            8.0D, 4.0D, 12.0D);

    protected static final VoxelShape SILVERFISH_NORTH_BODY_SHAPE = VoxelShapes.or(SILVERFISH_NORTH_HEAD_BODY_SHAPE,
            SILVERFISH_NORTH_FRONT_BODY_SHAPE, SILVERFISH_NORTH_MIDDLE_BODY_SHAPE, SILVERFISH_NORTH_BACK1_BODY_SHAPE,
            SILVERFISH_NORTH_BACK2_BODY_SHAPE, SILVERFISH_NORTH_TAIL_SHAPE);

    protected static final VoxelShape SILVERFISH_NORTH_FRONT_TUFFS_SHAPE = VoxelShapes.or(
            SILVERFISH_NORTH_FRONT_TOP_TUFF_BOTTOM_SHAPE, SILVERFISH_NORTH_FRONT_TOP_TUFF_TOP_SHAPE,
            SILVERFISH_NORTH_FRONT_RIGHT_TUFF_BOTTOM_SHAPE, SILVERFISH_NORTH_FRONT_RIGHT_TUFF_TOP_SHAPE,
            SILVERFISH_NORTH_FRONT_LEFT_TUFF_BOTTOM_SHAPE, SILVERFISH_NORTH_FRONT_LEFT_TUFF_TOP_SHAPE);

    protected static final VoxelShape SILVERFISH_NORTH_MIDDLE_TUFFS_SHAPE = VoxelShapes.or(
            SILVERFISH_NORTH_MIDDLE_FRONT_TOP_BOTTOM_LEFT_TUFF_SHAPE,
            SILVERFISH_NORTH_MIDDLE_FRONT_TOP_TOP_LEFT_TUFF_SHAPE,
            SILVERFISH_NORTH_MIDDLE_FRONT_BOTTOM_BOTTOM_LEFT_TUFF_SHAPE,
            SILVERFISH_NORTH_MIDDLE_FRONT_BOTTOM_TOP_LEFT_TUFF_SHAPE,
            SILVERFISH_NORTH_MIDDLE_FRONT_BOTTOM_RIGHT_TUFF_SHAPE, SILVERFISH_NORTH_MIDDLE_FRONT_TOP_RIGHT_TUFF_SHAPE,
            SILVERFISH_NORTH_MIDDLE_FRONT_TOP_BOTTOM_TUFF_SHAPE, SILVERFISH_NORTH_MIDDLE_FRONT_TOP_TOP_TUFF_SHAPE,
            SILVERFISH_NORTH_MIDDLE_BACK_LEFT_TUFF_BOTTOM_SHAPE,
            SILVERFISH_NORTH_MIDDLE_BACK_LEFT_TUFF_TOP_BOTTOM_SHAPE,
            SILVERFISH_NORTH_MIDDLE_BACK_LEFT_TUFF_TOP_TOP_SHAPE, SILVERFISH_NORTH_MIDDLE_BACK_RIGHT_TUFF_BOTTOM_SHAPE,
            SILVERFISH_NORTH_MIDDLE_BACK_RIGHT_TUFF_TOP_SHAPE, SILVERFISH_NORTH_MIDDLE_TOP_RIGHT_TUFF_BOTTOM_SHAPE,
            SILVERFISH_NORTH_MIDDLE_TOP_RIGHT_TUFF_TOP_SHAPE);

    protected static final VoxelShape SILVERFISH_NORTH_BACK2_TUFFS_SHAPE = VoxelShapes.or(
            SILVERFISH_NORTH_BACK2_LEFT_SHAPE, SILVERFISH_NORTH_BACK2_RIGHT_BOTTOM_SHAPE,
            SILVERFISH_NORTH_BACK2_RIGHT_TOP1_SHAPE, SILVERFISH_NORTH_BACK2_RIGHT_TOP2_SHAPE);

    protected static final VoxelShape SILVERFISH_NORTH_SHAPE = VoxelShapes.or(SILVERFISH_NORTH_BODY_SHAPE,
            SILVERFISH_NORTH_FRONT_TUFFS_SHAPE, SILVERFISH_NORTH_MIDDLE_TUFFS_SHAPE,
            SILVERFISH_NORTH_BACK2_TUFFS_SHAPE);

    protected static final VoxelShape SILVERFISH_SOUTH_SHAPE = VeCollisionUtil.rotate180(Axis.Y,
            SILVERFISH_NORTH_SHAPE);
    protected static final VoxelShape SILVERFISH_WEST_SHAPE = VeCollisionUtil.rotate270(Axis.Y, SILVERFISH_NORTH_SHAPE);
    protected static final VoxelShape SILVERFISH_EAST_SHAPE = VeCollisionUtil.rotate90(Axis.Y, SILVERFISH_NORTH_SHAPE);

    public VeSilverfishPlushBlock(Properties properties)
    {
        super(properties);
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context)
    {
        return defineShapes(state, SILVERFISH_NORTH_SHAPE, SILVERFISH_SOUTH_SHAPE, SILVERFISH_WEST_SHAPE,
                SILVERFISH_EAST_SHAPE);
    }
}
