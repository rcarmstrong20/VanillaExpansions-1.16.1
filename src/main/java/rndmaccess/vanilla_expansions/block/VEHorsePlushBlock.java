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

public class VEHorsePlushBlock extends VEPlushBlock
{
    protected static final VoxelShape HORSE_NORTH_BODY_SHAPE = Block.box(4.5D, 4.5D, 5.0D, 11.5D, 10.5D, 13.0D);
    protected static final VoxelShape HORSE_NORTH_FRONT_LEFT_LEG_PUFF_SHAPE = Block.box(10.0D, 4.0D, 4.5D, 12.0D, 9.0D,
            6.5D);
    protected static final VoxelShape HORSE_NORTH_BACK_LEFT_LEG_PUFF_SHAPE = Block.box(10.0D, 4.0D, 11.5D, 12.0D, 9.0D,
            13.5D);
    protected static final VoxelShape HORSE_NORTH_FRONT_RIGHT_LEG_PUFF_SHAPE = Block.box(4.0D, 4.0D, 4.5D, 6.0D, 9.0D,
            6.5D);
    protected static final VoxelShape HORSE_NORTH_BACK_RIGHT_LEG_PUFF_SHAPE = Block.box(4.0D, 4.0D, 11.5D, 6.0D, 9.0D,
            13.5D);
    protected static final VoxelShape HORSE_NORTH_FRONT_LEFT_LEG_MIDDLE_SHAPE = Block.box(10.5D, 2.0D, 5.0D, 11.5D,
            4.0D, 6.0D);
    protected static final VoxelShape HORSE_NORTH_BACK_LEFT_LEG_MIDDLE_SHAPE = Block.box(10.5D, 2.0D, 12.0D, 11.5D,
            4.0D, 13.0D);
    protected static final VoxelShape HORSE_NORTH_FRONT_RIGHT_LEG_MIDDLE_SHAPE = Block.box(4.5D, 2.0D, 5.0D, 5.5D, 4.0D,
            6.0D);
    protected static final VoxelShape HORSE_NORTH_BACK_RIGHT_LEG_MIDDLE_SHAPE = Block.box(4.5D, 2.0D, 12.0D, 5.5D, 4.0D,
            13.0D);
    protected static final VoxelShape HORSE_NORTH_FRONT_LEFT_HOOF_SHAPE = Block.box(10.0D, 0.0D, 4.5D, 12.0D, 2.0D,
            6.5D);
    protected static final VoxelShape HORSE_NORTH_BACK_LEFT_HOOF_SHAPE = Block.box(10.0D, 0.0D, 11.5D, 12.0D, 2.0D,
            13.5D);
    protected static final VoxelShape HORSE_NORTH_FRONT_RIGHT_HOOF_SHAPE = Block.box(4.0D, 0.0D, 4.5D, 6.0D, 2.0D,
            6.5D);
    protected static final VoxelShape HORSE_NORTH_BACK_RIGHT_HOOF_SHAPE = Block.box(4.0D, 0.0D, 11.5D, 6.0D, 2.0D,
            13.5D);
    protected static final VoxelShape HORSE_NORTH_TAIL1_SHAPE = Block.box(7.5D, 8.0D, 13.0D, 9.0D, 10.5D, 13.5D);
    protected static final VoxelShape HORSE_NORTH_TAIL2_SHAPE = Block.box(7.0D, 5.0D, 12.5D, 9.5D, 9.0D, 15.0D);
    protected static final VoxelShape HORSE_NORTH_TAIL3_SHAPE = Block.box(7.5D, 3.5D, 14.0D, 9.0D, 5.5D, 15.5D);
    protected static final VoxelShape HORSE_NORTH_TAIL4_SHAPE = Block.box(7.5D, 2.5D, 14.0D, 8.0D, 3.5D, 14.5D);
    protected static final VoxelShape HORSE_NORTH_TAIL5_SHAPE = Block.box(8.0D, 3.0D, 15.0D, 9.0D, 3.5D, 15.5D);
    protected static final VoxelShape HORSE_NORTH_TAIL6_SHAPE = Block.box(8.0D, 2.5D, 15.0D, 8.5D, 3.0D, 15.5D);
    protected static final VoxelShape HORSE_NORTH_MANE1_SHAPE = Block.box(6.5D, 10.5D, 7.0D, 9.0D, 11.5D, 9.5D);
    protected static final VoxelShape HORSE_NORTH_MANE2_SHAPE = Block.box(6.5D, 11.5D, 6.0D, 9.0D, 12.5D, 8.5D);
    protected static final VoxelShape HORSE_NORTH_MANE3_SHAPE = Block.box(6.5D, 12.5D, 5.0D, 9.0D, 13.5D, 7.5D);
    protected static final VoxelShape HORSE_NORTH_MANE4_SHAPE = Block.box(6.5D, 13.5D, 4.0D, 9.0D, 14.5D, 6.5D);
    protected static final VoxelShape HORSE_NORTH_MANE5_SHAPE = Block.box(6.5D, 14.5D, 3.0D, 9.0D, 15.5D, 5.5D);
    protected static final VoxelShape HORSE_NORTH_NECK1_SHAPE = Block.box(6.0D, 10.0D, 4.0D, 9.5D, 12.5D, 7.0D);
    protected static final VoxelShape HORSE_NORTH_NECK2_SHAPE = Block.box(6.0D, 10.0D, 7.0D, 9.5D, 11.5D, 8.0D);
    protected static final VoxelShape HORSE_NORTH_HEAD_FRONT1_SHAPE = Block.box(5.5D, 10.5D, 1.0D, 10.0D, 12.5D, 2.0D);
    protected static final VoxelShape HORSE_NORTH_HEAD_FRONT2_SHAPE = Block.box(5.5D, 10.0D, 2.0D, 10.0D, 13.0D, 4.0D);
    protected static final VoxelShape HORSE_NORTH_HEAD_MIDDLE_SHAPE = Block.box(5.5D, 9.5D, 3.0D, 10.0D, 14.5D, 5.0D);
    protected static final VoxelShape HORSE_NORTH_HEAD_BACK1_SHAPE = Block.box(5.5D, 11.5D, 5.5D, 10.0D, 13.0D, 6.5D);
    protected static final VoxelShape HORSE_NORTH_HEAD_BACK2_SHAPE = Block.box(5.5D, 10.0D, 5.0D, 10.0D, 13.5D, 5.5D);
    protected static final VoxelShape HORSE_NORTH_RIGHT_EYE_SHAPE = Block.box(5.0D, 11.3D, 3.0D, 5.5D, 12.7D, 4.5D);
    protected static final VoxelShape HORSE_NORTH_LEFT_EYE_SHAPE = Block.box(10.0D, 11.3D, 3.0D, 10.5D, 12.7D, 4.5D);
    protected static final VoxelShape HORSE_NORTH_RIGHT_EAR_SHAPE = Block.box(6.0D, 13.0D, 1.5D, 7.5D, 15.0D, 3.5D);
    protected static final VoxelShape HORSE_NORTH_LEFT_EAR_SHAPE = Block.box(8.0D, 13.0D, 1.5D, 9.5D, 15.0D, 3.5D);

    protected static final VoxelShape HORSE_NORTH_EARS_SHAPE = VoxelShapes.or(HORSE_NORTH_RIGHT_EAR_SHAPE,
            HORSE_NORTH_LEFT_EAR_SHAPE);

    protected static final VoxelShape HORSE_NORTH_EYES_SHAPE = VoxelShapes.or(HORSE_NORTH_RIGHT_EYE_SHAPE,
            HORSE_NORTH_LEFT_EYE_SHAPE);

    protected static final VoxelShape HORSE_NORTH_LEG_PUFFS_SHAPE = VoxelShapes.or(
            HORSE_NORTH_FRONT_LEFT_LEG_PUFF_SHAPE, HORSE_NORTH_BACK_LEFT_LEG_PUFF_SHAPE,
            HORSE_NORTH_FRONT_RIGHT_LEG_PUFF_SHAPE, HORSE_NORTH_BACK_RIGHT_LEG_PUFF_SHAPE);

    protected static final VoxelShape HORSE_NORTH_LEG_MIDDLE_SHAPE = VoxelShapes.or(
            HORSE_NORTH_FRONT_LEFT_LEG_MIDDLE_SHAPE, HORSE_NORTH_BACK_LEFT_LEG_MIDDLE_SHAPE,
            HORSE_NORTH_FRONT_RIGHT_LEG_MIDDLE_SHAPE, HORSE_NORTH_BACK_RIGHT_LEG_MIDDLE_SHAPE);

    protected static final VoxelShape HORSE_NORTH_HOOVES_SHAPE = VoxelShapes.or(HORSE_NORTH_FRONT_LEFT_HOOF_SHAPE,
            HORSE_NORTH_BACK_LEFT_HOOF_SHAPE, HORSE_NORTH_FRONT_RIGHT_HOOF_SHAPE, HORSE_NORTH_BACK_RIGHT_HOOF_SHAPE);

    protected static final VoxelShape HORSE_NORTH_LEGS_SHAPE = VoxelShapes.or(HORSE_NORTH_LEG_PUFFS_SHAPE,
            HORSE_NORTH_LEG_MIDDLE_SHAPE, HORSE_NORTH_HOOVES_SHAPE);

    protected static final VoxelShape HORSE_NORTH_TAIL_SHAPE = VoxelShapes.or(HORSE_NORTH_TAIL1_SHAPE,
            HORSE_NORTH_TAIL2_SHAPE, HORSE_NORTH_TAIL3_SHAPE, HORSE_NORTH_TAIL4_SHAPE, HORSE_NORTH_TAIL5_SHAPE,
            HORSE_NORTH_TAIL6_SHAPE);

    protected static final VoxelShape HORSE_NORTH_NECK_SHAPE = VoxelShapes.or(HORSE_NORTH_NECK1_SHAPE,
            HORSE_NORTH_NECK2_SHAPE);

    protected static final VoxelShape HORSE_NORTH_HEAD_SHAPE = VoxelShapes.or(HORSE_NORTH_HEAD_BACK1_SHAPE,
            HORSE_NORTH_HEAD_BACK2_SHAPE, HORSE_NORTH_HEAD_MIDDLE_SHAPE, HORSE_NORTH_HEAD_FRONT1_SHAPE,
            HORSE_NORTH_HEAD_FRONT2_SHAPE);

    protected static final VoxelShape HORSE_NORTH_MANE_SHAPE = VoxelShapes.or(HORSE_NORTH_MANE1_SHAPE,
            HORSE_NORTH_MANE2_SHAPE, HORSE_NORTH_MANE3_SHAPE, HORSE_NORTH_MANE4_SHAPE, HORSE_NORTH_MANE5_SHAPE);

    protected static final VoxelShape HORSE_NORTH_SHAPE = VoxelShapes.or(HORSE_NORTH_BODY_SHAPE, HORSE_NORTH_TAIL_SHAPE,
            HORSE_NORTH_NECK_SHAPE, HORSE_NORTH_MANE_SHAPE, HORSE_NORTH_HEAD_SHAPE, HORSE_NORTH_LEGS_SHAPE,
            HORSE_NORTH_EYES_SHAPE, HORSE_NORTH_EARS_SHAPE);

    protected static final VoxelShape HORSE_SOUTH_SHAPE = VEBoxBlockUtil.rotate180(Axis.Y, HORSE_NORTH_SHAPE);
    protected static final VoxelShape HORSE_WEST_SHAPE = VEBoxBlockUtil.rotate270(Axis.Y, HORSE_NORTH_SHAPE);
    protected static final VoxelShape HORSE_EAST_SHAPE = VEBoxBlockUtil.rotate90(Axis.Y, HORSE_NORTH_SHAPE);

    public VEHorsePlushBlock(Properties properties)
    {
        super(properties);
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context)
    {
        return defineShapes(state, HORSE_NORTH_SHAPE, HORSE_SOUTH_SHAPE, HORSE_WEST_SHAPE, HORSE_EAST_SHAPE);
    }
}
