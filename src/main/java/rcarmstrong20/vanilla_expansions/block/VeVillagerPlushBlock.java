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

public class VEVillagerPlushBlock extends VEPlushBlock
{
    private int type;

    // Shared Shapes

    protected static final VoxelShape NORTH_HEAD_SHAPE = Block.box(5.5D, 9.0D, 5.5D, 10.5D, 15.0D, 11.5D);
    protected static final VoxelShape NORTH_TORSO_SHAPE = Block.box(5.0D, 0.0D, 6.5D, 11.0D, 9.0D, 10.5D);
    protected static final VoxelShape NORTH_ARM_MIDDLE_SHAPE = Block.box(4.5D, 5.0D, 4.5D, 11.5D, 7.5D, 6.0D);
    protected static final VoxelShape NORTH_RIGHT_ARM_SHAPE = Block.box(3.5D, 5.0D, 4.5D, 4.5D, 8.0D, 8.0D);
    protected static final VoxelShape NORTH_LEFT_ARM_SHAPE = Block.box(11.5D, 5.0D, 4.5D, 12.5D, 8.0D, 8.0D);
    protected static final VoxelShape NORTH_NOSE_SHAPE = Block.box(7.5D, 9.0D, 4.5D, 8.5D, 11.0D, 5.5D);
    protected static final VoxelShape NORTH_MOUTH_SHAPE = Block.box(7.0D, 10.0D, 5.0D, 9.0D, 10.5D, 5.5D);
    protected static final VoxelShape NORTH_RIGHT_EYE_SHAPE = Block.box(6.5D, 11.0D, 5.0D, 7.5D, 11.5D, 5.5D);
    protected static final VoxelShape NORTH_LEFT_EYE_SHAPE = Block.box(8.5D, 11.0D, 5.0D, 9.5D, 11.5D, 5.5D);
    protected static final VoxelShape NORTH_EYEBROW_SHAPE = Block.box(6.0D, 11.5D, 5.0D, 10.0D, 12.5D, 5.5D);

    protected static final VoxelShape NORTH_ARM_SHAPE = VoxelShapes.or(NORTH_ARM_MIDDLE_SHAPE, NORTH_RIGHT_ARM_SHAPE,
            NORTH_LEFT_ARM_SHAPE);

    protected static final VoxelShape NORTH_FACE_SHAPE = VoxelShapes.or(NORTH_NOSE_SHAPE, NORTH_MOUTH_SHAPE,
            NORTH_RIGHT_EYE_SHAPE, NORTH_LEFT_EYE_SHAPE, NORTH_EYEBROW_SHAPE);

    protected static final VoxelShape NORTH_BODY_SHAPE = VoxelShapes.or(NORTH_HEAD_SHAPE, NORTH_TORSO_SHAPE,
            NORTH_ARM_SHAPE, NORTH_FACE_SHAPE);

    public VEVillagerPlushBlock(Properties properties, int type)
    {
        super(properties);
        this.type = type;
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context)
    {
        switch (type)
        {
            case 0:
                return defineShapes(state, Plains.NORTH_SHAPE, Plains.SOUTH_SHAPE, Plains.WEST_SHAPE,
                        Plains.EAST_SHAPE);
            case 1:
                return defineShapes(state, Desert.NORTH_SHAPE, Desert.SOUTH_SHAPE, Desert.WEST_SHAPE,
                        Desert.EAST_SHAPE);
            case 2:
                return defineShapes(state, Jungle.NORTH_SHAPE, Jungle.SOUTH_SHAPE, Jungle.WEST_SHAPE,
                        Jungle.EAST_SHAPE);
            case 3:
                return defineShapes(state, Savanna.NORTH_SHAPE, Savanna.SOUTH_SHAPE, Savanna.WEST_SHAPE,
                        Savanna.EAST_SHAPE);
            case 4:
                return defineShapes(state, Snow.NORTH_SHAPE, Snow.SOUTH_SHAPE, Snow.WEST_SHAPE, Snow.EAST_SHAPE);
            case 5:
                return defineShapes(state, Swamp.NORTH_SHAPE, Swamp.SOUTH_SHAPE, Swamp.WEST_SHAPE, Swamp.EAST_SHAPE);
            case 6:
                return defineShapes(state, Taiga.NORTH_SHAPE, Taiga.SOUTH_SHAPE, Taiga.WEST_SHAPE, Taiga.EAST_SHAPE);
            case 7:
                return defineShapes(state, Nether.NORTH_SHAPE, Nether.SOUTH_SHAPE, Nether.WEST_SHAPE,
                        Nether.EAST_SHAPE);
            default:
                return VoxelShapes.block();
        }
    }

    /**
     * A sub-class that holds bounding box data for the plains variant.
     *
     * @author Ryan
     */
    protected static class Plains
    {
        protected static final VoxelShape NORTH_COAT_BASE_SHAPE = Block.box(4.5D, 2.0D, 6.0D, 11.5D, 9.0D, 11.0D);
        protected static final VoxelShape NORTH_COAT_NECK1_SHAPE = Block.box(6.0D, 8.0D, 6.0D, 10.0D, 9.0D, 7.0D);
        protected static final VoxelShape NORTH_COAT_NECK2_SHAPE = Block.box(6.5D, 6.0D, 6.0D, 9.5D, 8.0D, 7.0D);
        protected static final VoxelShape NORTH_COAT_OPENING_SHAPE = Block.box(7.5D, 2.0D, 6.0D, 8.5D, 6.0D, 7.0D);

        protected static final VoxelShape NORTH_COAT_SHAPE = VEBoxBlockUtil.cutBox(NORTH_COAT_BASE_SHAPE,
                NORTH_COAT_NECK1_SHAPE, NORTH_COAT_NECK2_SHAPE, NORTH_COAT_OPENING_SHAPE);

        protected static final VoxelShape NORTH_SHAPE = VoxelShapes.or(NORTH_BODY_SHAPE, NORTH_COAT_SHAPE);

        protected static final VoxelShape SOUTH_SHAPE = VEBoxBlockUtil.rotate180(Axis.Y, NORTH_SHAPE);
        protected static final VoxelShape WEST_SHAPE = VEBoxBlockUtil.rotate270(Axis.Y, NORTH_SHAPE);
        protected static final VoxelShape EAST_SHAPE = VEBoxBlockUtil.rotate90(Axis.Y, NORTH_SHAPE);
    }

    /**
     * A sub-class that holds bounding box data for the desert variant.
     *
     * @author Ryan
     */
    protected static class Desert
    {
        protected static final VoxelShape NORTH_COAT_RIP1_SHAPE = Block.box(4.5D, 2.0D, 6.0D, 5.0D, 3.0D, 7.0D);
        protected static final VoxelShape NORTH_COAT_RIP2_SHAPE = Block.box(4.5D, 2.0D, 6.0D, 6.0D, 3.5D, 6.5D);
        protected static final VoxelShape NORTH_COAT_RIP3_SHAPE = Block.box(6.0D, 2.0D, 6.0D, 6.5D, 2.5D, 6.5D);
        protected static final VoxelShape NORTH_HAT_COTTEN_PUFF_SHAPE = Block.box(7.0D, 13.0D, 7.5D, 9.0D, 16.0D, 9.5D);
        protected static final VoxelShape NORTH_HAT_MIDDLE_SHAPE = Block.box(5.0D, 13.0D, 5.0D, 11.0D, 15.5D, 12.0D);
        protected static final VoxelShape NORTH_HAT_BRIM_SHAPE = Block.box(4.5D, 13.0D, 4.5D, 11.5D, 14.0D, 12.5D);

        protected static final VoxelShape NORTH_COAT_SHAPE = VEBoxBlockUtil.cutBox(Plains.NORTH_COAT_SHAPE,
                NORTH_COAT_RIP1_SHAPE, NORTH_COAT_RIP2_SHAPE, NORTH_COAT_RIP3_SHAPE);

        protected static final VoxelShape NORTH_HAT_SHAPE = VoxelShapes.or(NORTH_HAT_COTTEN_PUFF_SHAPE,
                NORTH_HAT_MIDDLE_SHAPE, NORTH_HAT_BRIM_SHAPE);

        protected static final VoxelShape NORTH_SHAPE = VoxelShapes.or(NORTH_BODY_SHAPE, NORTH_COAT_SHAPE,
                NORTH_HAT_SHAPE);

        protected static final VoxelShape SOUTH_SHAPE = VEBoxBlockUtil.rotate180(Axis.Y, NORTH_SHAPE);
        protected static final VoxelShape WEST_SHAPE = VEBoxBlockUtil.rotate270(Axis.Y, NORTH_SHAPE);
        protected static final VoxelShape EAST_SHAPE = VEBoxBlockUtil.rotate90(Axis.Y, NORTH_SHAPE);
    }

    /**
     * A sub-class that holds bounding box data for the jungle variant.
     *
     * @author Ryan
     */
    protected static class Jungle
    {
        protected static final VoxelShape NORTH_COAT_BASE_SHAPE = Block.box(4.5D, 1.0D, 6.0D, 11.5D, 9.0D, 11.0D);
        protected static final VoxelShape NORTH_LEFT_HOLE_SHAPE = Block.box(11.0D, 1.0D, 6.0D, 11.5D, 2.5D, 11.0D);
        protected static final VoxelShape NORTH_FRONT_HOLE1_SHAPE = Block.box(6.0D, 1.0D, 6.0D, 11.0D, 1.5D, 6.5D);
        protected static final VoxelShape NORTH_FRONT_HOLE2_SHAPE = Block.box(6.5D, 1.5D, 6.0D, 11.0D, 2.0D, 6.5D);
        protected static final VoxelShape NORTH_FRONT_HOLE3_SHAPE = Block.box(7.5D, 2.0D, 6.0D, 11.0D, 2.5D, 6.5D);
        protected static final VoxelShape NORTH_FRONT_HOLE4_SHAPE = Block.box(8.5D, 2.5D, 6.0D, 10.5D, 3.0D, 6.5D);
        protected static final VoxelShape NORTH_FRONT_HOLE5_SHAPE = Block.box(9.5D, 3.0D, 6.0D, 10.5D, 3.5D, 6.5D);
        protected static final VoxelShape NORTH_FRONT_HOLE6_SHAPE = Block.box(9.5D, 3.5D, 6.0D, 10.0D, 4.0D, 6.5D);
        protected static final VoxelShape NORTH_BACK_HOLE1_SHAPE = Block.box(7.5D, 1.0D, 10.5D, 11.5D, 2.0D, 11.0D);
        protected static final VoxelShape NORTH_BACK_HOLE2_SHAPE = Block.box(8.0D, 2.0D, 10.5D, 11.5D, 2.5D, 11.0D);
        protected static final VoxelShape NORTH_BACK_HOLE3_SHAPE = Block.box(8.0D, 2.5D, 10.5D, 10.0D, 3.0D, 11.0D);
        protected static final VoxelShape NORTH_BACK_HOLE4_SHAPE = Block.box(9.0D, 3.0D, 10.5D, 10.0D, 4.0D, 11.0D);
        protected static final VoxelShape NORTH_VINE_BELT_BOTTOM_BASE_SHAPE = Block.box(4.0D, 4.5D, 5.5D, 12.0D, 5.5D,
                11.5D);
        protected static final VoxelShape NORTH_VINE_BELT_BOTTOM_HOLE_SHAPE = Block.box(4.0D, 4.5D, 8.0D, 4.5D, 5.5D,
                9.5D);
        protected static final VoxelShape NORTH_VINE_BELT_TOP_SHAPE = Block.box(4.0D, 5.5D, 7.0D, 4.5D, 6.5D, 10.5D);

        protected static final VoxelShape NORTH_COAT_SHAPE = VEBoxBlockUtil.cutBox(NORTH_COAT_BASE_SHAPE,
                Plains.NORTH_COAT_NECK1_SHAPE, Plains.NORTH_COAT_NECK2_SHAPE, NORTH_LEFT_HOLE_SHAPE,
                NORTH_FRONT_HOLE1_SHAPE, NORTH_FRONT_HOLE2_SHAPE, NORTH_FRONT_HOLE3_SHAPE, NORTH_FRONT_HOLE4_SHAPE,
                NORTH_FRONT_HOLE5_SHAPE, NORTH_FRONT_HOLE6_SHAPE, NORTH_BACK_HOLE1_SHAPE, NORTH_BACK_HOLE2_SHAPE,
                NORTH_BACK_HOLE3_SHAPE, NORTH_BACK_HOLE4_SHAPE);

        protected static final VoxelShape NORTH_VINE_BELT_BOTTOM_SHAPE = VEBoxBlockUtil
                .cutBox(NORTH_VINE_BELT_BOTTOM_BASE_SHAPE, NORTH_VINE_BELT_BOTTOM_HOLE_SHAPE);

        protected static final VoxelShape NORTH_VINE_BELT_SHAPE = VoxelShapes.or(NORTH_VINE_BELT_BOTTOM_SHAPE,
                NORTH_VINE_BELT_TOP_SHAPE);

        protected static final VoxelShape NORTH_SHAPE = VoxelShapes.or(NORTH_BODY_SHAPE, NORTH_VINE_BELT_SHAPE,
                NORTH_COAT_SHAPE);

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
        protected static final VoxelShape NORTH_HEAD_BAND_SHAPE = Block.box(5.0D, 13.5D, 5.0D, 11.0D, 14.0D, 12.0D);
        protected static final VoxelShape NORTH_APRON_BAND_SHAPE = Block.box(4.0D, 4.0D, 5.5D, 12.0D, 4.5D, 11.5D);

        protected static final VoxelShape NORTH_SHAPE = VoxelShapes.or(NORTH_BODY_SHAPE, Plains.NORTH_COAT_BASE_SHAPE,
                NORTH_HEAD_BAND_SHAPE, NORTH_APRON_BAND_SHAPE);

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
        protected static final VoxelShape NORTH_SWEATER_BASE_SHAPE = Block.box(4.5D, 1.5D, 6.0D, 12.0D, 9.0D, 11.0D);
        protected static final VoxelShape NORTH_SWEATER_NECK1_SHAPE = Block.box(6.0D, 8.5D, 6.0D, 10.0D, 9.0D, 7.0D);
        protected static final VoxelShape NORTH_SWEATER_NECK2_SHAPE = Block.box(6.5D, 8.0D, 6.0D, 9.5D, 8.5D, 7.0D);
        protected static final VoxelShape NORTH_SWEATER_HOLE_FRONT_BOTTOM_SHAPE = Block.box(6.5D, 1.5D, 6.0D, 9.5D,
                4.0D, 6.5D);
        protected static final VoxelShape NORTH_SWEATER_HOLE_FRONT_TOP_SHAPE = Block.box(6.0D, 4.5D, 6.0D, 12.0D, 7.0D,
                6.5D);
        protected static final VoxelShape NORTH_SWEATER_HOLE_SIDE_SHAPE = Block.box(11.5D, 4.5D, 6.0D, 12.0D, 9.0D,
                11.0D);
        protected static final VoxelShape NORTH_HAT_SHAPE = Block.box(5.0D, 13.0D, 5.0D, 11.0D, 15.5D, 12.0D);

        protected static final VoxelShape NORTH_SWEATER_SHAPE = VEBoxBlockUtil.cutBox(NORTH_SWEATER_BASE_SHAPE,
                NORTH_SWEATER_NECK1_SHAPE, NORTH_SWEATER_NECK2_SHAPE, NORTH_SWEATER_HOLE_FRONT_BOTTOM_SHAPE,
                NORTH_SWEATER_HOLE_FRONT_TOP_SHAPE, NORTH_SWEATER_HOLE_SIDE_SHAPE);

        protected static final VoxelShape NORTH_SHAPE = VoxelShapes.or(NORTH_BODY_SHAPE, NORTH_SWEATER_SHAPE,
                NORTH_HAT_SHAPE);

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
        protected static final VoxelShape NORTH_HAT_TOP_SHAPE = Block.box(5.0D, 14.5D, 5.0D, 11.0D, 15.5D, 12.0D);
        protected static final VoxelShape NORTH_HAT_SIDES_SHAPE = Block.box(5.0D, 13.0D, 5.5D, 11.0D, 14.5D, 12.0D);
        protected static final VoxelShape NORTH_HAT_BACK_SHAPE = Block.box(5.5D, 11.5D, 11.5D, 10.5D, 13.0D, 12.0D);
        protected static final VoxelShape NORTH_APRON_MAIN_SHAPE = Block.box(4.0D, 2.0D, 6.0D, 12.0D, 5.0D, 11.5D);
        protected static final VoxelShape NORTH_APRON_BAND_SHAPE = Block.box(3.5D, 4.0D, 5.5D, 12.5D, 4.5D, 12.0D);
        protected static final VoxelShape NORTH_APRON_PATCH_SHAPE = Block.box(12.0D, 3.0D, 7.5D, 12.5D, 4.0D, 8.5D);

        protected static final VoxelShape NORTH_HAT_SHAPE = VoxelShapes.or(NORTH_HAT_TOP_SHAPE, NORTH_HAT_SIDES_SHAPE,
                NORTH_HAT_BACK_SHAPE);

        protected static final VoxelShape NORTH_APRON_SHAPE = VoxelShapes.or(NORTH_APRON_MAIN_SHAPE,
                NORTH_APRON_BAND_SHAPE, NORTH_APRON_PATCH_SHAPE);

        protected static final VoxelShape NORTH_SHAPE = VoxelShapes.or(NORTH_BODY_SHAPE, NORTH_HAT_SHAPE,
                NORTH_APRON_SHAPE, Plains.NORTH_COAT_SHAPE);

        protected static final VoxelShape SOUTH_SHAPE = VEBoxBlockUtil.rotate180(Axis.Y, NORTH_SHAPE);
        protected static final VoxelShape WEST_SHAPE = VEBoxBlockUtil.rotate270(Axis.Y, NORTH_SHAPE);
        protected static final VoxelShape EAST_SHAPE = VEBoxBlockUtil.rotate90(Axis.Y, NORTH_SHAPE);
    }

    /**
     * A sub-class that holds bounding box data for the taiga variant.
     *
     * @author Ryan
     */
    protected static class Taiga
    {
        protected static final VoxelShape NORTH_COAT_NECK3_SHAPE = Block.box(7.5D, 7.5D, 6.0D, 8.5D, 8.0D, 7.0D);
        protected static final VoxelShape NORTH_COAT_BOTTOM1_SHAPE = Block.box(7.0D, 2.5D, 6.0D, 9.0D, 3.0D, 7.0D);
        protected static final VoxelShape NORTH_COAT_BOTTOM2_SHAPE = Block.box(6.0D, 2.0D, 6.0D, 10.0D, 2.5D, 7.0D);
        protected static final VoxelShape NORTH_BELT_STRAP_SHAPE = Block.box(4.0D, 3.5D, 5.5D, 12.0D, 4.5D, 11.5D);
        protected static final VoxelShape NORTH_BELT_BUCKLE_SHAPE = Block.box(7.0D, 3.5D, 5.0D, 9.0D, 4.5D, 5.5D);

        protected static final VoxelShape NORTH_COAT_SHAPE = VEBoxBlockUtil.cutBox(Plains.NORTH_COAT_BASE_SHAPE,
                Snow.NORTH_SWEATER_NECK1_SHAPE, Snow.NORTH_SWEATER_NECK2_SHAPE, NORTH_COAT_NECK3_SHAPE,
                NORTH_COAT_BOTTOM1_SHAPE, NORTH_COAT_BOTTOM2_SHAPE);

        protected static final VoxelShape NORTH_BELT_SHAPE = VoxelShapes.or(NORTH_BELT_STRAP_SHAPE,
                NORTH_BELT_BUCKLE_SHAPE);

        protected static final VoxelShape NORTH_SHAPE = VoxelShapes.or(NORTH_BODY_SHAPE, NORTH_COAT_SHAPE,
                NORTH_BELT_SHAPE);

        protected static final VoxelShape SOUTH_SHAPE = VEBoxBlockUtil.rotate180(Axis.Y, NORTH_SHAPE);
        protected static final VoxelShape WEST_SHAPE = VEBoxBlockUtil.rotate270(Axis.Y, NORTH_SHAPE);
        protected static final VoxelShape EAST_SHAPE = VEBoxBlockUtil.rotate90(Axis.Y, NORTH_SHAPE);
    }

    /**
     * A sub-class that holds bounding box data for the warped and crimson variants.
     *
     * @author Ryan
     */
    protected static class Nether
    {
        protected static final VoxelShape NORTH_BOTTOM_RIM = Block.box(6.0, 2.0, 6.0, 10.0, 4.0, 10.0);

        protected static final VoxelShape NORTH_FRONT_LEFT_RIP = Block.box(8.5, 2.0, 6.0, 9.5, 3.0, 7.0);
        protected static final VoxelShape NORTH_FRONT_RIGHT_RIP = Block.box(6.5, 2.0, 6.0, 7.5, 3.0, 7.0);

        protected static final VoxelShape NORTH_LEFT_BIG_RIP = Block.box(4.5, 2.0, 8.0, 5.0, 3.0, 9.0);
        protected static final VoxelShape NORTH_MIDDLE_BIG_RIP = Block.box(4.5, 2.0, 9.0, 5.0, 4.5, 9.5);
        protected static final VoxelShape NORTH_RIGHT_BIG_RIP = Block.box(4.5, 2.0, 9.5, 5.0, 2.5, 10.5);

        protected static final VoxelShape NORTH_FRONT_BEAD = Block.box(10.0, 3.0, 5.5, 11.0, 4.0, 6.0);
        protected static final VoxelShape NORTH_BACK_BEAD = Block.box(8.0, 3.0, 11.0, 9.0, 4.0, 11.5);
        protected static final VoxelShape NORTH_LEFT_BEAD = Block.box(11.5, 3.0, 8.0, 12.0, 4.0, 9.0);
        protected static final VoxelShape NORTH_RIGHT_BEAD = Block.box(4.0, 3.0, 6.5, 5.0, 4.0, 7.5);

        protected static final VoxelShape NORTH_COAT_SHAPE = VEBoxBlockUtil.cutBox(Plains.NORTH_COAT_BASE_SHAPE,
                Plains.NORTH_COAT_NECK1_SHAPE, Plains.NORTH_COAT_NECK2_SHAPE, NORTH_FRONT_LEFT_RIP,
                NORTH_FRONT_RIGHT_RIP, NORTH_LEFT_BIG_RIP, NORTH_MIDDLE_BIG_RIP, NORTH_RIGHT_BIG_RIP);

        protected static final VoxelShape NORTH_BEADS_SHAPE = VoxelShapes.or(NORTH_FRONT_BEAD, NORTH_BACK_BEAD,
                NORTH_LEFT_BEAD, NORTH_RIGHT_BEAD);

        protected static final VoxelShape NORTH_SHAPE = VoxelShapes.or(NORTH_COAT_SHAPE, NORTH_BEADS_SHAPE,
                Savanna.NORTH_APRON_BAND_SHAPE, NORTH_BODY_SHAPE);

        protected static final VoxelShape SOUTH_SHAPE = VEBoxBlockUtil.rotate180(Axis.Y, NORTH_SHAPE);
        protected static final VoxelShape WEST_SHAPE = VEBoxBlockUtil.rotate270(Axis.Y, NORTH_SHAPE);
        protected static final VoxelShape EAST_SHAPE = VEBoxBlockUtil.rotate90(Axis.Y, NORTH_SHAPE);
    }
}
