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

public class VeChickenPlushBlock extends VePlushBlock
{
    protected static final VoxelShape CHICKEN_NORTH_FEET_SHAPE = Block.box(5.0D, 0.0D, 7.5D, 11.0D, 0.5D, 8.5D);
    protected static final VoxelShape CHICKEN_NORTH_TORSO_SHAPE = Block.box(4.0D, 3.0D, 4.0D, 12.0D, 10.0D, 13.0D);
    protected static final VoxelShape CHICKEN_NORTH_WINGS_SHAPE = Block.box(3.0D, 3.5D, 5.0D, 13.0D, 9.0D, 12.0D);
    protected static final VoxelShape CHICKEN_NORTH_HEAD_SHAPE = Block.box(5.5D, 9.0D, 2.0D, 10.5D, 14.0D, 5.0D);
    protected static final VoxelShape CHICKEN_NORTH_WATTLE_SHAPE = Block.box(6.5D, 8.5D, 1.0D, 9.5D, 11.0D, 3.0D);
    protected static final VoxelShape CHICKEN_NORTH_BEAK_SHAPE = Block.box(6.0D, 11.0D, 0.0D, 10.0D, 12.0D, 3.0D);
    protected static final VoxelShape CHICKEN_NORTH_RIGHT_EYE_SHAPE = Block.box(9.0D, 12.0D, 1.5D, 10.0D, 13.0D, 2.0D);
    protected static final VoxelShape CHICKEN_NORTH_LEFT_EYE_SHAPE = Block.box(6.0D, 12.0D, 1.5D, 7.0D, 13.0D, 2.0D);
    protected static final VoxelShape CHICKEN_NORTH_LEFT_LEG_BACK_SHAPE = Block.box(5.5D, 0.0D, 9.5D, 6.5D, 3.0D,
            10.5D);
    protected static final VoxelShape CHICKEN_NORTH_RIGHT_LEG_BACK_SHAPE = Block.box(9.5D, 0.0D, 9.5D, 10.5D, 3.0D,
            10.5D);
    protected static final VoxelShape CHICKEN_NORTH_LEFT_LEG_FRONT_SHAPE = Block.box(5.5D, 0.0D, 8.5D, 6.5D, 0.5D,
            9.5D);
    protected static final VoxelShape CHICKEN_NORTH_RIGHT_LEG_FRONT_SHAPE = Block.box(9.5D, 0.0D, 8.5D, 10.5D, 0.5D,
            9.5D);

    protected static final VoxelShape CHICKEN_NORTH_LEGS_SHAPE = VoxelShapes.or(CHICKEN_NORTH_LEFT_LEG_BACK_SHAPE,
            CHICKEN_NORTH_RIGHT_LEG_BACK_SHAPE, CHICKEN_NORTH_LEFT_LEG_FRONT_SHAPE,
            CHICKEN_NORTH_RIGHT_LEG_FRONT_SHAPE);

    protected static final VoxelShape CHICKEN_NORTH_EYES_SHAPE = VoxelShapes.or(CHICKEN_NORTH_RIGHT_EYE_SHAPE,
            CHICKEN_NORTH_LEFT_EYE_SHAPE);

    protected static final VoxelShape CHICKEN_NORTH_SHAPE = VoxelShapes.or(CHICKEN_NORTH_FEET_SHAPE,
            CHICKEN_NORTH_TORSO_SHAPE, CHICKEN_NORTH_WINGS_SHAPE, CHICKEN_NORTH_HEAD_SHAPE, CHICKEN_NORTH_WATTLE_SHAPE,
            CHICKEN_NORTH_BEAK_SHAPE, CHICKEN_NORTH_LEGS_SHAPE, CHICKEN_NORTH_EYES_SHAPE);

    protected static final VoxelShape CHICKEN_SOUTH_SHAPE = VeBoxUtil.rotate180(Axis.Y, CHICKEN_NORTH_SHAPE);
    protected static final VoxelShape CHICKEN_WEST_SHAPE = VeBoxUtil.rotate270(Axis.Y, CHICKEN_NORTH_SHAPE);
    protected static final VoxelShape CHICKEN_EAST_SHAPE = VeBoxUtil.rotate90(Axis.Y, CHICKEN_NORTH_SHAPE);

    public VeChickenPlushBlock(Properties properties)
    {
        super(properties);
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context)
    {
        return defineShapes(state, CHICKEN_NORTH_SHAPE, CHICKEN_SOUTH_SHAPE, CHICKEN_WEST_SHAPE, CHICKEN_EAST_SHAPE);
    }
}
