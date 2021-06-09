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

public class VECowPlushBlock extends VEPlushBlock
{
    protected static final VoxelShape COW_NORTH_TORSO_SHAPE = Block.box(4.5D, 4.0D, 4.0D, 11.5D, 10.0D, 14.5D);
    protected static final VoxelShape COW_NORTH_HEAD_SHAPE = Block.box(5.5D, 6.0D, 1.0D, 10.5D, 11.0D, 4.0D);
    protected static final VoxelShape COW_NORTH_UTTERS_SHAPE = Block.box(7.0D, 3.0D, 11.0D, 9.0D, 5.5D, 15.0D);
    protected static final VoxelShape COW_NORTH_MOUTH_SHAPE = Block.box(6.5D, 6.5D, 0.5D, 9.5D, 9.0D, 1.5D);
    protected static final VoxelShape COW_NORTH_RIGHT_EYE_SHAPE = Block.box(9.0D, 9.5D, 0.5D, 10.0D, 10.5D, 1.5D);
    protected static final VoxelShape COW_NORTH_LEFT_EYE_SHAPE = Block.box(6.0D, 9.5D, 0.5D, 7.0D, 10.5D, 1.5D);
    protected static final VoxelShape COW_NORTH_RIGHT_HORN_SHAPE = Block.box(10.5D, 10.0D, 1.5D, 11.0D, 11.5D, 2.5D);
    protected static final VoxelShape COW_NORTH_LEFT_HORN_SHAPE = Block.box(5.0D, 10.0D, 1.5D, 5.5D, 11.5D, 2.5D);
    protected static final VoxelShape COW_NORTH_LEFT_FRONT_LEG_SHAPE = Block.box(5.0D, 0.0D, 5.0D, 7.0D, 4.0D, 7.0D);
    protected static final VoxelShape COW_NORTH_RIGHT_FRONT_LEG_SHAPE = Block.box(9.0D, 0.0D, 5.0D, 11.0D, 4.0D, 7.0D);
    protected static final VoxelShape COW_NORTH_RIGHT_BACK_LEG_SHAPE = Block.box(9.0D, 0.0D, 12.0D, 11.0D, 4.0D, 14.0D);
    protected static final VoxelShape COW_NORTH_LEFT_BACK_LEG_SHAPE = Block.box(5.0D, 0.0D, 12.0D, 7.0D, 4.0D, 14.0D);

    protected static final VoxelShape COW_NORTH_LEGS_SHAPE = VoxelShapes.or(COW_NORTH_LEFT_FRONT_LEG_SHAPE,
            COW_NORTH_RIGHT_FRONT_LEG_SHAPE, COW_NORTH_RIGHT_BACK_LEG_SHAPE, COW_NORTH_LEFT_BACK_LEG_SHAPE);

    protected static final VoxelShape COW_NORTH_HORNS_SHAPE = VoxelShapes.or(COW_NORTH_RIGHT_HORN_SHAPE,
            COW_NORTH_LEFT_HORN_SHAPE);

    protected static final VoxelShape COW_NORTH_EYES_SHAPE = VoxelShapes.or(COW_NORTH_RIGHT_EYE_SHAPE,
            COW_NORTH_LEFT_EYE_SHAPE);

    protected static final VoxelShape COW_NORTH_SHAPE = VoxelShapes.or(COW_NORTH_TORSO_SHAPE, COW_NORTH_HEAD_SHAPE,
            COW_NORTH_UTTERS_SHAPE, COW_NORTH_MOUTH_SHAPE, COW_NORTH_LEGS_SHAPE, COW_NORTH_HORNS_SHAPE,
            COW_NORTH_EYES_SHAPE);

    protected static final VoxelShape COW_SOUTH_SHAPE = VEBoxBlockUtil.rotate180(Axis.Y, COW_NORTH_SHAPE);
    protected static final VoxelShape COW_WEST_SHAPE = VEBoxBlockUtil.rotate270(Axis.Y, COW_NORTH_SHAPE);
    protected static final VoxelShape COW_EAST_SHAPE = VEBoxBlockUtil.rotate90(Axis.Y, COW_NORTH_SHAPE);

    public VECowPlushBlock(Properties properties)
    {
        super(properties);
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context)
    {
        return defineShapes(state, COW_NORTH_SHAPE, COW_SOUTH_SHAPE, COW_WEST_SHAPE, COW_EAST_SHAPE);
    }
}
