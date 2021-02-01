package rcarmstrong20.vanilla_expansions.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.Direction.Axis;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import rcarmstrong20.vanilla_expansions.util.VeShapeUtil;

public class VeSheepPlushBlock extends VePlushBlock
{
    protected static final VoxelShape SHEEP_NORTH_BACK_RIGHT_LEG_TUFF_SHAPE = Block.makeCuboidShape(8.5D, 2.0D, 3.5D,
            11.0D, 4.0D, 6.0D);
    protected static final VoxelShape SHEEP_NORTH_BACK_RIGHT_LEG_SHAPE = Block.makeCuboidShape(9.0D, 0.0D, 4.0D, 10.5D,
            2.0D, 5.5D);
    protected static final VoxelShape SHEEP_NORTH_BACK_LEFT_LEG_TUFF_SHAPE = Block.makeCuboidShape(5.0D, 2.0D, 3.5D,
            7.5D, 4.0D, 6.0D);
    protected static final VoxelShape SHEEP_NORTH_BACK_LEFT_LEG_SHAPE = Block.makeCuboidShape(5.5D, 0.0D, 4.0D, 7.0D,
            2.0D, 5.5D);
    protected static final VoxelShape SHEEP_NORTH_FRONT_RIGHT_LEG_TUFF_SHAPE = Block.makeCuboidShape(8.5D, 2.0D, 10.0D,
            11.0D, 4.0D, 12.5D);
    protected static final VoxelShape SHEEP_NORTH_FRONT_RIGHT_LEG_SHAPE = Block.makeCuboidShape(9.0D, 0.0D, 10.5D,
            10.5D, 2.0D, 12.0D);
    protected static final VoxelShape SHEEP_NORTH_FRONT_LEFT_LEG_TUFF_SHAPE = Block.makeCuboidShape(5.0D, 2.0D, 10.0D,
            7.5D, 4.0D, 12.5D);
    protected static final VoxelShape SHEEP_NORTH_FRONT_LEFT_LEG_SHAPE = Block.makeCuboidShape(5.5D, 0.0D, 10.5D, 7.0D,
            2.0D, 12.0D);
    protected static final VoxelShape SHEEP_NORTH_HEAD_SHAPE = Block.makeCuboidShape(5.5D, 9.0D, 1.0D, 10.5D, 14.0D,
            6.0D);
    protected static final VoxelShape SHEEP_NORTH_TORSO_SHAPE = Block.makeCuboidShape(4.5D, 4.0D, 3.0D, 11.5D, 11.0D,
            14.0D);
    protected static final VoxelShape SHEEP_NORTH_FOREHEAD_SHAPE = Block.makeCuboidShape(6.0D, 9.5D, 0.5D, 10.0D, 13.5D,
            1.0D);
    protected static final VoxelShape SHEEP_NORTH_EARS_SHAPE = Block.makeCuboidShape(5.5D, 11.0D, 0.5D, 10.5D, 12.5D,
            1.0D);
    protected static final VoxelShape SHEEP_NORTH_RIGHT_EYE_SHAPE = Block.makeCuboidShape(8.5D, 12.0D, 0.5D, 10.0D,
            11.0D, 0.0D);
    protected static final VoxelShape SHEEP_NORTH_LEFT_EYE_SHAPE = Block.makeCuboidShape(6.0D, 12.0D, 0.5D, 7.5D, 11.0D,
            0.0D);
    protected static final VoxelShape SHEEP_NORTH_MOUTH_SHAPE = Block.makeCuboidShape(7.5D, 9.5D, 0.5D, 8.5D, 10.5D,
            0.0D);

    protected static final VoxelShape SHEEP_NORTH_LEG_TUFFS_SHAPE = VoxelShapes.or(
            SHEEP_NORTH_BACK_RIGHT_LEG_TUFF_SHAPE, SHEEP_NORTH_BACK_LEFT_LEG_TUFF_SHAPE,
            SHEEP_NORTH_FRONT_RIGHT_LEG_TUFF_SHAPE, SHEEP_NORTH_FRONT_LEFT_LEG_TUFF_SHAPE);

    protected static final VoxelShape SHEEP_NORTH_LEGS_SHAPE = VoxelShapes.or(SHEEP_NORTH_BACK_RIGHT_LEG_SHAPE,
            SHEEP_NORTH_BACK_LEFT_LEG_SHAPE, SHEEP_NORTH_FRONT_RIGHT_LEG_SHAPE, SHEEP_NORTH_FRONT_LEFT_LEG_SHAPE);

    protected static final VoxelShape SHEEP_NORTH_EYES_SHAPE = VoxelShapes.or(SHEEP_NORTH_RIGHT_EYE_SHAPE,
            SHEEP_NORTH_LEFT_EYE_SHAPE);

    protected static final VoxelShape SHEEP_NORTH_SHAPE = VoxelShapes.or(SHEEP_NORTH_HEAD_SHAPE,
            SHEEP_NORTH_TORSO_SHAPE, SHEEP_NORTH_FOREHEAD_SHAPE, SHEEP_NORTH_EARS_SHAPE, SHEEP_NORTH_MOUTH_SHAPE,
            SHEEP_NORTH_LEG_TUFFS_SHAPE, SHEEP_NORTH_LEGS_SHAPE, SHEEP_NORTH_EYES_SHAPE);

    protected static final VoxelShape SHEEP_SOUTH_SHAPE = VeShapeUtil.rotate180(Axis.Y, SHEEP_NORTH_SHAPE);
    protected static final VoxelShape SHEEP_WEST_SHAPE = VeShapeUtil.rotate270(Axis.Y, SHEEP_NORTH_SHAPE);
    protected static final VoxelShape SHEEP_EAST_SHAPE = VeShapeUtil.rotate90(Axis.Y, SHEEP_NORTH_SHAPE);

    public VeSheepPlushBlock(Properties properties)
    {
        super(properties);
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context)
    {
        return defineShapes(state, SHEEP_NORTH_SHAPE, SHEEP_SOUTH_SHAPE, SHEEP_WEST_SHAPE, SHEEP_EAST_SHAPE);
    }
}
