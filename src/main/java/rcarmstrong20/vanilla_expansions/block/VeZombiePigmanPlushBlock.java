package rcarmstrong20.vanilla_expansions.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.Direction.Axis;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import rcarmstrong20.vanilla_expansions.block.util.VEBoxBlockUtil;

public class VEZombiePigmanPlushBlock extends VEZombiePlushBlock
{
    protected static final VoxelShape ZOMBIE_PIGMAN_NORTH_RIGHT_EYE_SHAPE = Block.box(5.0D, 14.0D, 7.0D, 7.0D, 15.0D,
            7.5D);
    protected static final VoxelShape ZOMBIE_PIGMAN_NORTH_LEFT_HEAD_PIECE1_FRONT_SHAPE = Block.box(10.5D, 11.5D, 7.0D,
            11.5D, 16.5D, 7.5D);
    protected static final VoxelShape ZOMBIE_PIGMAN_NORTH_LEFT_HEAD_PIECE2_FRONT_SHAPE = Block.box(9.0D, 12.5D, 7.0D,
            10.5D, 16.5D, 7.5D);
    protected static final VoxelShape ZOMBIE_PIGMAN_NORTH_LEFT_HEAD_PIECE3_FRONT_SHAPE = Block.box(8.5D, 13.0D, 7.0D,
            9.0D, 16.5D, 7.5D);
    protected static final VoxelShape ZOMBIE_PIGMAN_NORTH_LEFT_HEAD_PIECE4_FRONT_SHAPE = Block.box(8.5D, 14.0D, 7.0D,
            8.0D, 16.5D, 7.5D);
    protected static final VoxelShape ZOMBIE_PIGMAN_NORTH_LEFT_HEAD_PIECE1_SIDE_SHAPE = Block.box(11.0D, 11.5D, 7.0D,
            11.5D, 16.5D, 9.0D);
    protected static final VoxelShape ZOMBIE_PIGMAN_NORTH_LEFT_HEAD_PIECE2_SIDE_SHAPE = Block.box(11.0D, 12.0D, 9.0D,
            11.5D, 16.5D, 9.5D);
    protected static final VoxelShape ZOMBIE_PIGMAN_NORTH_LEFT_HEAD_PIECE3_SIDE_SHAPE = Block.box(11.0D, 12.5D, 9.5D,
            11.5D, 16.5D, 10.5D);
    protected static final VoxelShape ZOMBIE_PIGMAN_NORTH_LEFT_HEAD_PIECE_TOP_SHAPE = Block.box(8.0D, 16.0D, 7.0D,
            11.5D, 16.5D, 10.5D);
    protected static final VoxelShape ZOMBIE_PIGMAN_NORTH_RIBCAGE_PIECE1_SHAPE = Block.box(7.5D, 8.5D, 8.0D, 10.5D,
            10.5D, 8.5D);
    protected static final VoxelShape ZOMBIE_PIGMAN_NORTH_RIBCAGE_PIECE2_SHAPE = Block.box(10.5D, 8.0D, 8.0D, 9.0D,
            8.5D, 8.5D);
    protected static final VoxelShape ZOMBIE_PIGMAN_NORTH_RIBCAGE_PIECE3_SHAPE = Block.box(7.5D, 8.0D, 8.0D, 8.5D, 8.5D,
            8.5D);
    protected static final VoxelShape ZOMBIE_PIGMAN_NORTH_RIBCAGE_PIECE4_SHAPE = Block.box(6.5D, 9.5D, 8.0D, 7.5D,
            10.5D, 8.5D);
    protected static final VoxelShape ZOMBIE_PIGMAN_NORTH_TOP_STRAP_SHAPE = Block.box(5.0D, 7.0D, 8.0D, 11.0D, 7.5D,
            12.0D);
    protected static final VoxelShape ZOMBIE_PIGMAN_NORTH_BOTTOM_STRAP_SHAPE = Block.box(5.0D, 6.0D, 8.0D, 11.0D, 6.5D,
            12.0D);

    protected static final VoxelShape ZOMBIE_PIGMAN_NORTH_HEAD_PIECES_SHAPE = VoxelShapes.or(
            ZOMBIE_PIGMAN_NORTH_LEFT_HEAD_PIECE1_FRONT_SHAPE, ZOMBIE_PIGMAN_NORTH_LEFT_HEAD_PIECE2_FRONT_SHAPE,
            ZOMBIE_PIGMAN_NORTH_LEFT_HEAD_PIECE3_FRONT_SHAPE, ZOMBIE_PIGMAN_NORTH_LEFT_HEAD_PIECE4_FRONT_SHAPE,
            ZOMBIE_PIGMAN_NORTH_LEFT_HEAD_PIECE1_SIDE_SHAPE, ZOMBIE_PIGMAN_NORTH_LEFT_HEAD_PIECE2_SIDE_SHAPE,
            ZOMBIE_PIGMAN_NORTH_LEFT_HEAD_PIECE3_SIDE_SHAPE, ZOMBIE_PIGMAN_NORTH_LEFT_HEAD_PIECE_TOP_SHAPE);

    protected static final VoxelShape ZOMBIE_PIGMAN_NORTH_RIBCAGE_SHAPE = VoxelShapes.or(
            ZOMBIE_PIGMAN_NORTH_RIBCAGE_PIECE1_SHAPE, ZOMBIE_PIGMAN_NORTH_RIBCAGE_PIECE2_SHAPE,
            ZOMBIE_PIGMAN_NORTH_RIBCAGE_PIECE3_SHAPE, ZOMBIE_PIGMAN_NORTH_RIBCAGE_PIECE4_SHAPE);

    protected static final VoxelShape ZOMBIE_PIGMAN_NORTH_STRAP_SHAPE = VoxelShapes
            .or(ZOMBIE_PIGMAN_NORTH_TOP_STRAP_SHAPE, ZOMBIE_PIGMAN_NORTH_BOTTOM_STRAP_SHAPE);

    protected static final VoxelShape ZOMBIE_PIGMAN_NORTH_SHAPE = VoxelShapes.or(ZOMBIE_PIGMAN_NORTH_RIGHT_EYE_SHAPE,
            ZOMBIE_PIGMAN_NORTH_HEAD_PIECES_SHAPE, ZOMBIE_PIGMAN_NORTH_RIBCAGE_SHAPE, ZOMBIE_PIGMAN_NORTH_STRAP_SHAPE,
            ZOMBIE_NORTH_BODY_SHAPE);

    protected static final VoxelShape ZOMBIE_PIGMAN_SOUTH_SHAPE = VEBoxBlockUtil.rotate180(Axis.Y,
            ZOMBIE_PIGMAN_NORTH_SHAPE);
    protected static final VoxelShape ZOMBIE_PIGMAN_WEST_SHAPE = VEBoxBlockUtil.rotate270(Axis.Y, ZOMBIE_PIGMAN_NORTH_SHAPE);
    protected static final VoxelShape ZOMBIE_PIGMAN_EAST_SHAPE = VEBoxBlockUtil.rotate90(Axis.Y, ZOMBIE_PIGMAN_NORTH_SHAPE);

    public VEZombiePigmanPlushBlock(Properties properties)
    {
        super(properties);
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context)
    {
        return defineShapes(state, ZOMBIE_PIGMAN_NORTH_SHAPE, ZOMBIE_PIGMAN_SOUTH_SHAPE, ZOMBIE_PIGMAN_WEST_SHAPE,
                ZOMBIE_PIGMAN_EAST_SHAPE);
    }
}
