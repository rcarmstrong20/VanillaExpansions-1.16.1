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

public class VeBatPlushBlock extends VePlushBlock
{
    protected static final VoxelShape BAT_NORTH_BODY_SHAPE = Block.makeCuboidShape(6.5D, 1.0D, 6.0D, 10.5D, 6.5D,
            10.0D);
    protected static final VoxelShape BAT_NORTH_HEAD_SHAPE = Block.makeCuboidShape(6.0D, 5.5D, 5.0D, 11.0D, 10.0D,
            8.5D);
    protected static final VoxelShape BAT_NORTH_RIGHT_EAR_SHAPE = Block.makeCuboidShape(5.5D, 9.0D, 6.0D, 7.5D, 11.5D,
            7.0D);
    protected static final VoxelShape BAT_NORTH_LEFT_EAR_SHAPE = Block.makeCuboidShape(9.5D, 9.0D, 6.0D, 11.5D, 11.5D,
            7.0D);
    protected static final VoxelShape BAT_NORTH_RIGHT_EYE_SHAPE = Block.makeCuboidShape(6.5D, 8.0D, 4.5D, 8.0D, 9.0D,
            5.0D);
    protected static final VoxelShape BAT_NORTH_LEFT_EYE_SHAPE = Block.makeCuboidShape(9.0D, 8.0D, 4.5D, 10.5D, 9.0D,
            5.0D);
    protected static final VoxelShape BAT_NORTH_MOUTH_SHAPE = Block.makeCuboidShape(7.5D, 6.0D, 4.5D, 9.5D, 7.0D, 5.0D);
    protected static final VoxelShape BAT_NORTH_RIGHT_LEG_SHAPE = Block.makeCuboidShape(7.0D, 0.0D, 7.5D, 8.0D, 1.0D,
            8.5D);
    protected static final VoxelShape BAT_NORTH_LEFT_LEG_SHAPE = Block.makeCuboidShape(9.0D, 0.0D, 7.5D, 10.0D, 1.0D,
            8.5D);
    protected static final VoxelShape BAT_NORTH_LEFT_WING1_SHAPE = Block.makeCuboidShape(10.5D, 1.5D, 7.0D, 11.5D, 6.0D,
            9.5D);
    protected static final VoxelShape BAT_NORTH_LEFT_WING2_SHAPE = Block.makeCuboidShape(11.5D, 1.5D, 6.5D, 13.5D, 5.5D,
            9.0D);
    protected static final VoxelShape BAT_NORTH_LEFT_WING3_SHAPE = Block.makeCuboidShape(13.5D, 1.5D, 6.0D, 14.5D, 5.5D,
            8.5D);
    protected static final VoxelShape BAT_NORTH_LEFT_WING4_SHAPE = Block.makeCuboidShape(14.5D, 1.0D, 5.5D, 15.5D, 5.0D,
            8.0D);
    protected static final VoxelShape BAT_NORTH_RIGHT_WING1_SHAPE = Block.makeCuboidShape(5.5D, 1.5D, 7.0D, 6.5D, 6.0D,
            9.5D);
    protected static final VoxelShape BAT_NORTH_RIGHT_WING2_SHAPE = Block.makeCuboidShape(4.5D, 1.5D, 6.5D, 5.5D, 5.0D,
            9.5D);
    protected static final VoxelShape BAT_NORTH_RIGHT_WING3_SHAPE = Block.makeCuboidShape(3.5D, 1.5D, 6.5D, 4.5D, 5.5D,
            10.0D);
    protected static final VoxelShape BAT_NORTH_RIGHT_WING4_SHAPE = Block.makeCuboidShape(2.5D, 1.0D, 6.5D, 3.5D, 5.0D,
            9.0D);
    protected static final VoxelShape BAT_NORTH_RIGHT_WING5_SHAPE = Block.makeCuboidShape(1.5D, 0.5D, 5.5D, 2.5D, 4.5D,
            8.5D);

    protected static final VoxelShape BAT_NORTH_EARS_SHAPE = VoxelShapes.or(BAT_NORTH_RIGHT_EAR_SHAPE,
            BAT_NORTH_LEFT_EAR_SHAPE);

    protected static final VoxelShape BAT_NORTH_EYES_AND_MOUTH_SHAPE = VoxelShapes.or(BAT_NORTH_RIGHT_EYE_SHAPE,
            BAT_NORTH_LEFT_EYE_SHAPE, BAT_NORTH_MOUTH_SHAPE);

    protected static final VoxelShape BAT_NORTH_LEGS_SHAPE = VoxelShapes.or(BAT_NORTH_RIGHT_LEG_SHAPE,
            BAT_NORTH_LEFT_LEG_SHAPE);

    protected static final VoxelShape BAT_NORTH_LEFT_WING_SHAPE = VoxelShapes.or(BAT_NORTH_LEFT_WING1_SHAPE,
            BAT_NORTH_LEFT_WING2_SHAPE, BAT_NORTH_LEFT_WING3_SHAPE, BAT_NORTH_LEFT_WING4_SHAPE);

    protected static final VoxelShape BAT_NORTH_RIGHT_WING_SHAPE = VoxelShapes.or(BAT_NORTH_RIGHT_WING1_SHAPE,
            BAT_NORTH_RIGHT_WING2_SHAPE, BAT_NORTH_RIGHT_WING3_SHAPE, BAT_NORTH_RIGHT_WING4_SHAPE,
            BAT_NORTH_RIGHT_WING5_SHAPE);

    protected static final VoxelShape BAT_NORTH_SHAPE = VoxelShapes.or(BAT_NORTH_BODY_SHAPE, BAT_NORTH_HEAD_SHAPE,
            BAT_NORTH_EARS_SHAPE, BAT_NORTH_EYES_AND_MOUTH_SHAPE, BAT_NORTH_LEGS_SHAPE, BAT_NORTH_LEFT_WING_SHAPE,
            BAT_NORTH_RIGHT_WING_SHAPE);

    protected static final VoxelShape BAT_SOUTH_SHAPE = VeCollisionUtil.rotate180(Axis.Y, BAT_NORTH_SHAPE);
    protected static final VoxelShape BAT_WEST_SHAPE = VeCollisionUtil.rotate270(Axis.Y, BAT_NORTH_SHAPE);
    protected static final VoxelShape BAT_EAST_SHAPE = VeCollisionUtil.rotate90(Axis.Y, BAT_NORTH_SHAPE);

    public VeBatPlushBlock(Properties properties)
    {
        super(properties);
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context)
    {
        return defineShapes(state, BAT_NORTH_SHAPE, BAT_SOUTH_SHAPE, BAT_WEST_SHAPE, BAT_EAST_SHAPE);
    }
}
