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
import rndmaccess.vanilla_expansions.enums.VEVillagerType;

public class VEVillagerPlushBlock extends VEPlushBlock
{
    private VEVillagerType type;

    // Shared Shapes
    protected static final VoxelShape NORTH_HEAD = Block.box(4.0D, 5.0D, 5.5D, 12.0D, 14.0D, 12.0D);
    protected static final VoxelShape NORTH_TORSO = Block.box(5.0D, 1.5D, 7.0D, 11.0D, 5.0D, 11.0D);

    protected static final VoxelShape NORTH_RIGHT_FOOT = Block.box(5.5D, 0.0D, 8.0D, 7.5D, 1.5D, 10.0D);
    protected static final VoxelShape NORTH_LEFT_FOOT = Block.box(8.5D, 0.0D, 8.0D, 10.5D, 1.5D, 10.0D);

    protected static final VoxelShape NORTH_ARM = Block.box(4.5D, 1.5D, 5.0D, 11.5D, 5.0D, 9.0D);

    protected static final VoxelShape NORTH_EYEBROW = Block.box(5.5D, 9.0D, 5.0D, 10.5D, 10.0D, 5.5D);
    protected static final VoxelShape NORTH_LEFT_EYE = Block.box(8.5D, 8.0D, 5.0D, 10.5D, 9.0D, 5.5D);
    protected static final VoxelShape NORTH_RIGHT_EYE = Block.box(5.5D, 8.0D, 5.0D, 7.5D, 9.0D, 5.5D);
    protected static final VoxelShape NORTH_MOUTH = Block.box(6.0D, 6.0D, 5.0D, 10.0D, 7.0D, 5.5D);
    protected static final VoxelShape NORTH_NOSE = Block.box(7.0D, 4.0D, 3.5D, 9.0D, 7.5D, 5.5D);

    protected static final VoxelShape NORTH_FEET = VoxelShapes.or(NORTH_RIGHT_FOOT, NORTH_LEFT_FOOT);
    protected static final VoxelShape NORTH_FACE = VoxelShapes.or(NORTH_EYEBROW, NORTH_LEFT_EYE, NORTH_RIGHT_EYE,
            NORTH_MOUTH, NORTH_NOSE);
    protected static final VoxelShape NORTH_BODY = VoxelShapes.or(NORTH_HEAD, NORTH_TORSO, NORTH_ARM);

    protected static final VoxelShape NORTH_BASIC_SHAPE = VoxelShapes.or(NORTH_FEET, NORTH_FACE, NORTH_BODY);
    protected static final VoxelShape SOUTH_BASIC_SHAPE = VEBoxBlockUtil.rotate180(Axis.Y, NORTH_BASIC_SHAPE);
    protected static final VoxelShape WEST_BASIC_SHAPE = VEBoxBlockUtil.rotate270(Axis.Y, NORTH_BASIC_SHAPE);
    protected static final VoxelShape EAST_BASIC_SHAPE = VEBoxBlockUtil.rotate90(Axis.Y, NORTH_BASIC_SHAPE);

    public VEVillagerPlushBlock(Properties properties, VEVillagerType type)
    {
        super(properties);
        this.type = type;
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context)
    {
        switch (type)
        {
            case PLAINS:
                return defineBasicShapes(state);
            case DESERT:
                return defineShapes(state, Desert.NORTH_SHAPE, Desert.SOUTH_SHAPE, Desert.WEST_SHAPE,
                        Desert.EAST_SHAPE);
            case JUNGLE:
                return defineBasicShapes(state);
            case SAVANNA:
                return defineShapes(state, Savanna.NORTH_SHAPE, Savanna.SOUTH_SHAPE, Savanna.WEST_SHAPE,
                        Savanna.EAST_SHAPE);
            case SNOW:
                return defineShapes(state, Snow.NORTH_SHAPE, Snow.SOUTH_SHAPE, Snow.WEST_SHAPE, Snow.EAST_SHAPE);
            case SWAMP:
                return defineShapes(state, Swamp.NORTH_SHAPE, Swamp.SOUTH_SHAPE, Swamp.WEST_SHAPE, Swamp.EAST_SHAPE);
            case TAIGA:
                return defineBasicShapes(state);
            case NETHER:
                return defineBasicShapes(state);
            default:
                return VoxelShapes.block();
        }
    }

    private VoxelShape defineBasicShapes(BlockState state)
    {
        return defineShapes(state, NORTH_BASIC_SHAPE, SOUTH_BASIC_SHAPE, WEST_BASIC_SHAPE, EAST_BASIC_SHAPE);
    }

    /**
     * A sub-class that holds bounding box data for the desert variant.
     *
     * @author Ryan
     */
    protected static class Desert
    {
        protected static final VoxelShape NORTH_HAT = Block.box(3.0D, 12.0D, 4.5D, 13.0D, 14.0D, 13.0D);
        protected static final VoxelShape NORTH_POMPOM = Block.box(6.0D, 14.0D, 7.0D, 10.0D, 15.0D, 10.5D);

        protected static final VoxelShape NORTH_SHAPE = VoxelShapes.or(NORTH_BASIC_SHAPE, NORTH_HAT, NORTH_POMPOM);

        protected static final VoxelShape SOUTH_SHAPE = VEBoxBlockUtil.rotate180(Axis.Y, NORTH_SHAPE);
        protected static final VoxelShape WEST_SHAPE = VEBoxBlockUtil.rotate270(Axis.Y, NORTH_SHAPE);
        protected static final VoxelShape EAST_SHAPE = VEBoxBlockUtil.rotate90(Axis.Y, NORTH_SHAPE);
    }

    /**
     * A sub-class that holds bounding box data for the savanna variant.
     *
     * @author Ryan
     */
    protected static class Savanna
    {
        protected static final VoxelShape NORTH_HEADBAND = Block.box(3.0D, 12.0D, 4.5D, 13.0D, 13.0D, 13.0D);

        protected static final VoxelShape NORTH_SHAPE = VoxelShapes.or(NORTH_BASIC_SHAPE, NORTH_HEADBAND);

        protected static final VoxelShape SOUTH_SHAPE = VEBoxBlockUtil.rotate180(Axis.Y, NORTH_SHAPE);
        protected static final VoxelShape WEST_SHAPE = VEBoxBlockUtil.rotate270(Axis.Y, NORTH_SHAPE);
        protected static final VoxelShape EAST_SHAPE = VEBoxBlockUtil.rotate90(Axis.Y, NORTH_SHAPE);
    }

    /**
     * A sub-class that holds bounding box data for the snow variant.
     *
     * @author Ryan
     */
    protected static class Snow
    {
        protected static final VoxelShape NORTH_HAT = Block.box(3.0D, 11.0D, 4.5D, 13.0D, 15.0D, 13.0D);

        protected static final VoxelShape NORTH_SHAPE = VoxelShapes.or(NORTH_BASIC_SHAPE, NORTH_HAT);

        protected static final VoxelShape SOUTH_SHAPE = VEBoxBlockUtil.rotate180(Axis.Y, NORTH_SHAPE);
        protected static final VoxelShape WEST_SHAPE = VEBoxBlockUtil.rotate270(Axis.Y, NORTH_SHAPE);
        protected static final VoxelShape EAST_SHAPE = VEBoxBlockUtil.rotate90(Axis.Y, NORTH_SHAPE);
    }

    /**
     * A sub-class that holds bounding box data for the swamp variant.
     *
     * @author Ryan
     */
    protected static class Swamp
    {
        protected static final VoxelShape NORTH_HAT_TOP = Block.box(3.0, 13.0, 4.5, 13.0, 15.0, 13.0);
        protected static final VoxelShape NORTH_LEFT_BACK_HAT_DETAIL = Block.box(9.0, 12.0, 9.0, 13.0, 13.0, 13.0);
        protected static final VoxelShape NORTH_RIGHT_BACK_HAT_DETAIL = Block.box(3.0, 11.0, 9.0, 6.5, 13.0, 13.0);
        protected static final VoxelShape NORTH_LEFT_FRONT_HAT_DETAIL = Block.box(11.0, 12.0, 4.5, 13.0, 13.0, 6.5);
        protected static final VoxelShape NORTH_MIDDLE_FRONT_HAT_DETAIL0 = Block.box(5.0, 12.0, 4.5, 9.0, 13.0, 5.5);
        protected static final VoxelShape NORTH_MIDDLE_FRONT_HAT_DETAIL1 = Block.box(8.0, 11.0, 4.5, 9.0, 12.0, 5.5);

        protected static final VoxelShape NORTH_HAT = VoxelShapes.or(NORTH_HAT_TOP, NORTH_LEFT_BACK_HAT_DETAIL,
                NORTH_RIGHT_BACK_HAT_DETAIL, NORTH_LEFT_FRONT_HAT_DETAIL, NORTH_MIDDLE_FRONT_HAT_DETAIL0,
                NORTH_MIDDLE_FRONT_HAT_DETAIL1);

        protected static final VoxelShape NORTH_SHAPE = VoxelShapes.or(NORTH_BASIC_SHAPE, NORTH_HAT);

        protected static final VoxelShape SOUTH_SHAPE = VEBoxBlockUtil.rotate180(Axis.Y, NORTH_SHAPE);
        protected static final VoxelShape WEST_SHAPE = VEBoxBlockUtil.rotate270(Axis.Y, NORTH_SHAPE);
        protected static final VoxelShape EAST_SHAPE = VEBoxBlockUtil.rotate90(Axis.Y, NORTH_SHAPE);
    }
}
