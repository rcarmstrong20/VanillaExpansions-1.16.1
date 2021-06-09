package rndmaccess.vanilla_expansions.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.Direction.Axis;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import rndmaccess.vanilla_expansions.block.util.VEBoxBlockUtil;

public class VEBatPlushBlock extends VEPlushBlock
{
    protected static final VoxelShape BODY = Block.box(6.5D, 1.0D, 6.0D, 10.5D, 6.5D, 10.0D);
    protected static final VoxelShape HEAD = Block.box(6.0D, 5.5D, 5.0D, 11.0D, 10.0D, 8.5D);
    protected static final VoxelShape RIGHT_EAR = Block.box(5.5D, 9.0D, 6.0D, 7.5D, 11.5D, 7.0D);
    protected static final VoxelShape LEFT_EAR = Block.box(9.5D, 9.0D, 6.0D, 11.5D, 11.5D, 7.0D);
    protected static final VoxelShape RIGHT_EYE = Block.box(6.5D, 8.0D, 4.5D, 8.0D, 9.0D, 5.0D);
    protected static final VoxelShape LEFT_EYE = Block.box(9.0D, 8.0D, 4.5D, 10.5D, 9.0D, 5.0D);
    protected static final VoxelShape MOUTH = Block.box(7.5D, 6.0D, 4.5D, 9.5D, 7.0D, 5.0D);
    protected static final VoxelShape RIGHT_LEG = Block.box(7.0D, 0.0D, 7.5D, 8.0D, 1.0D, 8.5D);
    protected static final VoxelShape LEFT_LEG = Block.box(9.0D, 0.0D, 7.5D, 10.0D, 1.0D, 8.5D);
    protected static final VoxelShape LEFT_WING1 = Block.box(10.5D, 1.5D, 7.0D, 11.5D, 6.0D, 9.5D);
    protected static final VoxelShape LEFT_WING2 = Block.box(11.5D, 1.5D, 6.5D, 13.5D, 5.5D, 9.0D);
    protected static final VoxelShape LEFT_WING3 = Block.box(13.5D, 1.5D, 6.0D, 14.5D, 5.5D, 8.5D);
    protected static final VoxelShape LEFT_WING4 = Block.box(14.5D, 1.0D, 5.5D, 15.5D, 5.0D, 8.0D);
    protected static final VoxelShape RIGHT_WING1 = Block.box(5.5D, 1.5D, 7.0D, 6.5D, 6.0D, 9.5D);
    protected static final VoxelShape RIGHT_WING2 = Block.box(4.5D, 1.5D, 6.5D, 5.5D, 5.0D, 9.5D);
    protected static final VoxelShape RIGHT_WING3 = Block.box(3.5D, 1.5D, 6.5D, 4.5D, 5.5D, 10.0D);
    protected static final VoxelShape RIGHT_WING4 = Block.box(2.5D, 1.0D, 6.5D, 3.5D, 5.0D, 9.0D);
    protected static final VoxelShape RIGHT_WING5 = Block.box(1.5D, 0.5D, 5.5D, 2.5D, 4.5D, 8.5D);

    protected static final VoxelShape EARS = VoxelShapes.or(RIGHT_EAR, LEFT_EAR);
    protected static final VoxelShape EYES_AND_MOUTH = VoxelShapes.or(RIGHT_EYE, LEFT_EYE, MOUTH);
    protected static final VoxelShape LEGS = VoxelShapes.or(RIGHT_LEG, LEFT_LEG);
    protected static final VoxelShape LEFT_WING = VoxelShapes.or(LEFT_WING1, LEFT_WING2, LEFT_WING3, LEFT_WING4);
    protected static final VoxelShape RIGHT_WING = VoxelShapes.or(RIGHT_WING1, RIGHT_WING2, RIGHT_WING3, RIGHT_WING4,
            RIGHT_WING5);
    protected static final VoxelShape NORTH_SHAPE = VoxelShapes.or(BODY, HEAD, EARS, EYES_AND_MOUTH, LEGS, LEFT_WING,
            RIGHT_WING);
    protected static final VoxelShape SOUTH_SHAPE = VEBoxBlockUtil.rotate180(Axis.Y, NORTH_SHAPE);
    protected static final VoxelShape WEST_SHAPE = VEBoxBlockUtil.rotate270(Axis.Y, NORTH_SHAPE);
    protected static final VoxelShape EAST_SHAPE = VEBoxBlockUtil.rotate90(Axis.Y, NORTH_SHAPE);

    public VEBatPlushBlock(Properties properties)
    {
        super(properties);
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context)
    {
        return defineShapes(state, NORTH_SHAPE, SOUTH_SHAPE, WEST_SHAPE, EAST_SHAPE);
    }
}
