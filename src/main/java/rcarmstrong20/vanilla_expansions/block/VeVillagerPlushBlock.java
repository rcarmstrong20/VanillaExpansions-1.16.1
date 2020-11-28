package rcarmstrong20.vanilla_expansions.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.Direction.Axis;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import rcarmstrong20.vanilla_expansions.core.VeBlocks;
import rcarmstrong20.vanilla_expansions.util.VeCollisionUtil;

public class VeVillagerPlushBlock extends VePlushBlock
{
    // Shared Shapes

    protected static final VoxelShape NORTH_HEAD_SHAPE = Block.makeCuboidShape(5.5D, 9.0D, 5.5D, 10.5D, 15.0D, 11.5D);
    protected static final VoxelShape NORTH_TORSO_SHAPE = Block.makeCuboidShape(5.0D, 0.0D, 6.5D, 11.0D, 9.0D, 10.5D);
    protected static final VoxelShape NORTH_ARM_MIDDLE_SHAPE = Block.makeCuboidShape(4.5D, 5.0D, 4.5D, 11.5D, 7.5D,
            6.0D);
    protected static final VoxelShape NORTH_RIGHT_ARM_SHAPE = Block.makeCuboidShape(3.5D, 5.0D, 4.5D, 4.5D, 8.0D, 8.0D);
    protected static final VoxelShape NORTH_LEFT_ARM_SHAPE = Block.makeCuboidShape(11.5D, 5.0D, 4.5D, 12.5D, 8.0D,
            8.0D);
    protected static final VoxelShape NORTH_NOSE_SHAPE = Block.makeCuboidShape(7.5D, 9.0D, 4.5D, 8.5D, 11.0D, 5.5D);
    protected static final VoxelShape NORTH_MOUTH_SHAPE = Block.makeCuboidShape(7.0D, 10.0D, 5.0D, 9.0D, 10.5D, 5.5D);
    protected static final VoxelShape NORTH_RIGHT_EYE_SHAPE = Block.makeCuboidShape(6.5D, 11.0D, 5.0D, 7.5D, 11.5D,
            5.5D);
    protected static final VoxelShape NORTH_LEFT_EYE_SHAPE = Block.makeCuboidShape(8.5D, 11.0D, 5.0D, 9.5D, 11.5D,
            5.5D);
    protected static final VoxelShape NORTH_EYEBROW_SHAPE = Block.makeCuboidShape(6.0D, 11.5D, 5.0D, 10.0D, 12.5D,
            5.5D);

    protected static final VoxelShape NORTH_ARM_SHAPE = VoxelShapes.or(NORTH_ARM_MIDDLE_SHAPE, NORTH_RIGHT_ARM_SHAPE,
            NORTH_LEFT_ARM_SHAPE);

    protected static final VoxelShape NORTH_FACE_SHAPE = VoxelShapes.or(NORTH_NOSE_SHAPE, NORTH_MOUTH_SHAPE,
            NORTH_RIGHT_EYE_SHAPE, NORTH_LEFT_EYE_SHAPE, NORTH_EYEBROW_SHAPE);

    protected static final VoxelShape NORTH_BODY_SHAPE = VoxelShapes.or(NORTH_HEAD_SHAPE, NORTH_TORSO_SHAPE,
            NORTH_ARM_SHAPE, NORTH_FACE_SHAPE);

    public VeVillagerPlushBlock(Properties properties)
    {
        super(properties);
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context)
    {
        Block block = this.getBlock();

        if (block == VeBlocks.plainsVillagerPlush)
        {
            return defineShapes(state, Plains.NORTH_SHAPE, Plains.SOUTH_SHAPE, Plains.WEST_SHAPE, Plains.EAST_SHAPE);
        }
        else if (block == VeBlocks.desertVillagerPlush)
        {
            return defineShapes(state, Desert.NORTH_SHAPE, Desert.SOUTH_SHAPE, Desert.WEST_SHAPE, Desert.EAST_SHAPE);
        }
        else if (block == VeBlocks.jungleVillagerPlush)
        {
            return defineShapes(state, Jungle.NORTH_SHAPE, Jungle.SOUTH_SHAPE, Jungle.WEST_SHAPE, Jungle.EAST_SHAPE);
        }
        else if (block == VeBlocks.savannaVillagerPlush)
        {
            return defineShapes(state, Savanna.NORTH_SHAPE, Savanna.SOUTH_SHAPE, Savanna.WEST_SHAPE,
                    Savanna.EAST_SHAPE);
        }
        else if (block == VeBlocks.snowVillagerPlush)
        {
            return defineShapes(state, Snow.NORTH_SHAPE, Snow.SOUTH_SHAPE, Snow.WEST_SHAPE, Snow.EAST_SHAPE);
        }
        else if (block == VeBlocks.swampVillagerPlush)
        {
            return defineShapes(state, Swamp.NORTH_SHAPE, Swamp.SOUTH_SHAPE, Swamp.WEST_SHAPE, Swamp.EAST_SHAPE);
        }
        else if (block == VeBlocks.taigaVillagerPlush)
        {
            return defineShapes(state, Taiga.NORTH_SHAPE, Taiga.SOUTH_SHAPE, Taiga.WEST_SHAPE, Taiga.EAST_SHAPE);
        }
        else
        {
            return VoxelShapes.fullCube();
        }
    }

    private static class Plains
    {
        protected static final VoxelShape NORTH_COAT_BASE_SHAPE = Block.makeCuboidShape(4.5D, 2.0D, 6.0D, 11.5D, 9.0D,
                11.0D);
        protected static final VoxelShape NORTH_COAT_NECK1_SHAPE = Block.makeCuboidShape(6.0D, 8.0D, 6.0D, 10.0D, 9.0D,
                7.0D);
        protected static final VoxelShape NORTH_COAT_NECK2_SHAPE = Block.makeCuboidShape(6.5D, 6.0D, 6.0D, 9.5D, 8.0D,
                7.0D);
        protected static final VoxelShape NORTH_COAT_OPENING_SHAPE = Block.makeCuboidShape(7.5D, 2.0D, 6.0D, 8.5D, 6.0D,
                7.0D);

        protected static final VoxelShape NORTH_COAT_SHAPE = VePlushBlock.cutShape(NORTH_COAT_BASE_SHAPE,
                NORTH_COAT_NECK1_SHAPE, NORTH_COAT_NECK2_SHAPE, NORTH_COAT_OPENING_SHAPE);

        protected static final VoxelShape NORTH_SHAPE = VoxelShapes.or(NORTH_BODY_SHAPE, NORTH_COAT_SHAPE);

        protected static final VoxelShape SOUTH_SHAPE = VeCollisionUtil.rotate180(Axis.Y, NORTH_SHAPE);
        protected static final VoxelShape WEST_SHAPE = VeCollisionUtil.rotate270(Axis.Y, NORTH_SHAPE);
        protected static final VoxelShape EAST_SHAPE = VeCollisionUtil.rotate90(Axis.Y, NORTH_SHAPE);
    }

    private static class Desert
    {
        protected static final VoxelShape NORTH_COAT_RIP1_SHAPE = Block.makeCuboidShape(4.5D, 2.0D, 6.0D, 5.0D, 3.0D,
                7.0D);
        protected static final VoxelShape NORTH_COAT_RIP2_SHAPE = Block.makeCuboidShape(4.5D, 2.0D, 6.0D, 6.0D, 3.5D,
                6.5D);
        protected static final VoxelShape NORTH_COAT_RIP3_SHAPE = Block.makeCuboidShape(6.0D, 2.0D, 6.0D, 6.5D, 2.5D,
                6.5D);
        protected static final VoxelShape NORTH_HAT_COTTEN_PUFF_SHAPE = Block.makeCuboidShape(7.0D, 13.0D, 7.5D, 9.0D,
                16.0D, 9.5D);
        protected static final VoxelShape NORTH_HAT_MIDDLE_SHAPE = Block.makeCuboidShape(5.0D, 13.0D, 5.0D, 11.0D,
                15.5D, 12.0D);
        protected static final VoxelShape NORTH_HAT_BRIM_SHAPE = Block.makeCuboidShape(4.5D, 13.0D, 4.5D, 11.5D, 14.0D,
                12.5D);

        protected static final VoxelShape NORTH_COAT_SHAPE = VePlushBlock.cutShape(Plains.NORTH_COAT_SHAPE,
                NORTH_COAT_RIP1_SHAPE, NORTH_COAT_RIP2_SHAPE, NORTH_COAT_RIP3_SHAPE);

        protected static final VoxelShape NORTH_HAT_SHAPE = VoxelShapes.or(NORTH_HAT_COTTEN_PUFF_SHAPE,
                NORTH_HAT_MIDDLE_SHAPE, NORTH_HAT_BRIM_SHAPE);

        protected static final VoxelShape NORTH_SHAPE = VoxelShapes.or(NORTH_BODY_SHAPE, NORTH_COAT_SHAPE,
                NORTH_HAT_SHAPE);

        protected static final VoxelShape SOUTH_SHAPE = VeCollisionUtil.rotate180(Axis.Y, NORTH_SHAPE);
        protected static final VoxelShape WEST_SHAPE = VeCollisionUtil.rotate270(Axis.Y, NORTH_SHAPE);
        protected static final VoxelShape EAST_SHAPE = VeCollisionUtil.rotate90(Axis.Y, NORTH_SHAPE);
    }

    private static class Jungle
    {
        protected static final VoxelShape NORTH_COAT_BASE_SHAPE = Block.makeCuboidShape(4.5D, 1.0D, 6.0D, 11.5D, 9.0D,
                11.0D);
        protected static final VoxelShape NORTH_LEFT_HOLE_SHAPE = Block.makeCuboidShape(11.0D, 1.0D, 6.0D, 11.5D, 2.5D,
                11.0D);
        protected static final VoxelShape NORTH_FRONT_HOLE1_SHAPE = Block.makeCuboidShape(6.0D, 1.0D, 6.0D, 11.0D, 1.5D,
                6.5D);
        protected static final VoxelShape NORTH_FRONT_HOLE2_SHAPE = Block.makeCuboidShape(6.5D, 1.5D, 6.0D, 11.0D, 2.0D,
                6.5D);
        protected static final VoxelShape NORTH_FRONT_HOLE3_SHAPE = Block.makeCuboidShape(7.5D, 2.0D, 6.0D, 11.0D, 2.5D,
                6.5D);
        protected static final VoxelShape NORTH_FRONT_HOLE4_SHAPE = Block.makeCuboidShape(8.5D, 2.5D, 6.0D, 10.5D, 3.0D,
                6.5D);
        protected static final VoxelShape NORTH_FRONT_HOLE5_SHAPE = Block.makeCuboidShape(9.5D, 3.0D, 6.0D, 10.5D, 3.5D,
                6.5D);
        protected static final VoxelShape NORTH_FRONT_HOLE6_SHAPE = Block.makeCuboidShape(9.5D, 3.5D, 6.0D, 10.0D, 4.0D,
                6.5D);
        protected static final VoxelShape NORTH_BACK_HOLE1_SHAPE = Block.makeCuboidShape(7.5D, 1.0D, 10.5D, 11.5D, 2.0D,
                11.0D);
        protected static final VoxelShape NORTH_BACK_HOLE2_SHAPE = Block.makeCuboidShape(8.0D, 2.0D, 10.5D, 11.5D, 2.5D,
                11.0D);
        protected static final VoxelShape NORTH_BACK_HOLE3_SHAPE = Block.makeCuboidShape(8.0D, 2.5D, 10.5D, 10.0D, 3.0D,
                11.0D);
        protected static final VoxelShape NORTH_BACK_HOLE4_SHAPE = Block.makeCuboidShape(9.0D, 3.0D, 10.5D, 10.0D, 4.0D,
                11.0D);
        protected static final VoxelShape NORTH_VINE_BELT_BOTTOM_BASE_SHAPE = Block.makeCuboidShape(4.0D, 4.5D, 5.5D,
                12.0D, 5.5D, 11.5D);
        protected static final VoxelShape NORTH_VINE_BELT_BOTTOM_HOLE_SHAPE = Block.makeCuboidShape(4.0D, 4.5D, 8.0D,
                4.5D, 5.5D, 9.5D);
        protected static final VoxelShape NORTH_VINE_BELT_TOP_SHAPE = Block.makeCuboidShape(4.0D, 5.5D, 7.0D, 4.5D,
                6.5D, 10.5D);

        protected static final VoxelShape NORTH_COAT_SHAPE = VePlushBlock.cutShape(NORTH_COAT_BASE_SHAPE,
                Plains.NORTH_COAT_NECK1_SHAPE, Plains.NORTH_COAT_NECK2_SHAPE, NORTH_LEFT_HOLE_SHAPE,
                NORTH_FRONT_HOLE1_SHAPE, NORTH_FRONT_HOLE2_SHAPE, NORTH_FRONT_HOLE3_SHAPE, NORTH_FRONT_HOLE4_SHAPE,
                NORTH_FRONT_HOLE5_SHAPE, NORTH_FRONT_HOLE6_SHAPE, NORTH_BACK_HOLE1_SHAPE, NORTH_BACK_HOLE2_SHAPE,
                NORTH_BACK_HOLE3_SHAPE, NORTH_BACK_HOLE4_SHAPE);

        protected static final VoxelShape NORTH_VINE_BELT_BOTTOM_SHAPE = VePlushBlock
                .cutShape(NORTH_VINE_BELT_BOTTOM_BASE_SHAPE, NORTH_VINE_BELT_BOTTOM_HOLE_SHAPE);

        protected static final VoxelShape NORTH_VINE_BELT_SHAPE = VoxelShapes.or(NORTH_VINE_BELT_BOTTOM_SHAPE,
                NORTH_VINE_BELT_TOP_SHAPE);

        protected static final VoxelShape NORTH_SHAPE = VoxelShapes.or(NORTH_BODY_SHAPE, NORTH_VINE_BELT_SHAPE,
                NORTH_COAT_SHAPE);

        protected static final VoxelShape SOUTH_SHAPE = VeCollisionUtil.rotate180(Axis.Y, NORTH_SHAPE);
        protected static final VoxelShape WEST_SHAPE = VeCollisionUtil.rotate270(Axis.Y, NORTH_SHAPE);
        protected static final VoxelShape EAST_SHAPE = VeCollisionUtil.rotate90(Axis.Y, NORTH_SHAPE);
    }

    private static class Savanna
    {
        protected static final VoxelShape NORTH_HEAD_BAND_SHAPE = Block.makeCuboidShape(5.0D, 13.5D, 5.0D, 11.0D, 14.0D,
                12.0D);
        protected static final VoxelShape NORTH_APRON_BAND_SHAPE = Block.makeCuboidShape(4.0D, 4.0D, 5.5D, 12.0D, 4.5D,
                11.5D);

        protected static final VoxelShape NORTH_SHAPE = VoxelShapes.or(NORTH_BODY_SHAPE, Plains.NORTH_COAT_BASE_SHAPE,
                NORTH_HEAD_BAND_SHAPE, NORTH_APRON_BAND_SHAPE);

        protected static final VoxelShape SOUTH_SHAPE = VeCollisionUtil.rotate180(Axis.Y, NORTH_SHAPE);
        protected static final VoxelShape WEST_SHAPE = VeCollisionUtil.rotate270(Axis.Y, NORTH_SHAPE);
        protected static final VoxelShape EAST_SHAPE = VeCollisionUtil.rotate90(Axis.Y, NORTH_SHAPE);
    }

    private static class Snow
    {
        protected static final VoxelShape NORTH_SWEATER_BASE_SHAPE = Block.makeCuboidShape(4.5D, 1.5D, 6.0D, 12.0D,
                9.0D, 11.0D);
        protected static final VoxelShape NORTH_SWEATER_NECK1_SHAPE = Block.makeCuboidShape(6.0D, 8.5D, 6.0D, 10.0D,
                9.0D, 7.0D);
        protected static final VoxelShape NORTH_SWEATER_NECK2_SHAPE = Block.makeCuboidShape(6.5D, 8.0D, 6.0D, 9.5D,
                8.5D, 7.0D);
        protected static final VoxelShape NORTH_SWEATER_HOLE_FRONT_BOTTOM_SHAPE = Block.makeCuboidShape(6.5D, 1.5D,
                6.0D, 9.5D, 4.0D, 6.5D);
        protected static final VoxelShape NORTH_SWEATER_HOLE_FRONT_TOP_SHAPE = Block.makeCuboidShape(6.0D, 4.5D, 6.0D,
                12.0D, 7.0D, 6.5D);
        protected static final VoxelShape NORTH_SWEATER_HOLE_SIDE_SHAPE = Block.makeCuboidShape(11.5D, 4.5D, 6.0D,
                12.0D, 9.0D, 11.0D);
        protected static final VoxelShape NORTH_HAT_SHAPE = Block.makeCuboidShape(5.0D, 13.0D, 5.0D, 11.0D, 15.5D,
                12.0D);

        protected static final VoxelShape NORTH_SWEATER_SHAPE = VePlushBlock.cutShape(NORTH_SWEATER_BASE_SHAPE,
                NORTH_SWEATER_NECK1_SHAPE, NORTH_SWEATER_NECK2_SHAPE, NORTH_SWEATER_HOLE_FRONT_BOTTOM_SHAPE,
                NORTH_SWEATER_HOLE_FRONT_TOP_SHAPE, NORTH_SWEATER_HOLE_SIDE_SHAPE);

        protected static final VoxelShape NORTH_SHAPE = VoxelShapes.or(NORTH_BODY_SHAPE, NORTH_SWEATER_SHAPE,
                NORTH_HAT_SHAPE);

        protected static final VoxelShape SOUTH_SHAPE = VeCollisionUtil.rotate180(Axis.Y, NORTH_SHAPE);
        protected static final VoxelShape WEST_SHAPE = VeCollisionUtil.rotate270(Axis.Y, NORTH_SHAPE);
        protected static final VoxelShape EAST_SHAPE = VeCollisionUtil.rotate90(Axis.Y, NORTH_SHAPE);
    }

    private static class Swamp
    {
        protected static final VoxelShape NORTH_HAT_TOP_SHAPE = Block.makeCuboidShape(5.0D, 14.5D, 5.0D, 11.0D, 15.5D,
                12.0D);
        protected static final VoxelShape NORTH_HAT_SIDES_SHAPE = Block.makeCuboidShape(5.0D, 13.0D, 5.5D, 11.0D, 14.5D,
                12.0D);
        protected static final VoxelShape NORTH_HAT_BACK_SHAPE = Block.makeCuboidShape(5.5D, 11.5D, 11.5D, 10.5D, 13.0D,
                12.0D);
        protected static final VoxelShape NORTH_APRON_MAIN_SHAPE = Block.makeCuboidShape(4.0D, 2.0D, 6.0D, 12.0D, 5.0D,
                11.5D);
        protected static final VoxelShape NORTH_APRON_BAND_SHAPE = Block.makeCuboidShape(3.5D, 4.0D, 5.5D, 12.5D, 4.5D,
                12.0D);
        protected static final VoxelShape NORTH_APRON_PATCH_SHAPE = Block.makeCuboidShape(12.0D, 3.0D, 7.5D, 12.5D,
                4.0D, 8.5D);

        protected static final VoxelShape NORTH_HAT_SHAPE = VoxelShapes.or(NORTH_HAT_TOP_SHAPE, NORTH_HAT_SIDES_SHAPE,
                NORTH_HAT_BACK_SHAPE);

        protected static final VoxelShape NORTH_APRON_SHAPE = VoxelShapes.or(NORTH_APRON_MAIN_SHAPE,
                NORTH_APRON_BAND_SHAPE, NORTH_APRON_PATCH_SHAPE);

        protected static final VoxelShape NORTH_SHAPE = VoxelShapes.or(NORTH_BODY_SHAPE, NORTH_HAT_SHAPE,
                NORTH_APRON_SHAPE, Plains.NORTH_COAT_SHAPE);

        protected static final VoxelShape SOUTH_SHAPE = VeCollisionUtil.rotate180(Axis.Y, NORTH_SHAPE);
        protected static final VoxelShape WEST_SHAPE = VeCollisionUtil.rotate270(Axis.Y, NORTH_SHAPE);
        protected static final VoxelShape EAST_SHAPE = VeCollisionUtil.rotate90(Axis.Y, NORTH_SHAPE);
    }

    private static class Taiga
    {
        protected static final VoxelShape NORTH_COAT_NECK3_SHAPE = Block.makeCuboidShape(7.5D, 7.5D, 6.0D, 8.5D, 8.0D,
                7.0D);
        protected static final VoxelShape NORTH_COAT_BOTTOM1_SHAPE = Block.makeCuboidShape(7.0D, 2.5D, 6.0D, 9.0D, 3.0D,
                7.0D);
        protected static final VoxelShape NORTH_COAT_BOTTOM2_SHAPE = Block.makeCuboidShape(6.0D, 2.0D, 6.0D, 10.0D,
                2.5D, 7.0D);
        protected static final VoxelShape NORTH_BELT_STRAP_SHAPE = Block.makeCuboidShape(4.0D, 3.5D, 5.5D, 12.0D, 4.5D,
                11.5D);
        protected static final VoxelShape NORTH_BELT_BUCKLE_SHAPE = Block.makeCuboidShape(7.0D, 3.5D, 5.0D, 9.0D, 4.5D,
                5.5D);

        protected static final VoxelShape NORTH_COAT_SHAPE = VePlushBlock.cutShape(Plains.NORTH_COAT_BASE_SHAPE,
                Snow.NORTH_SWEATER_NECK1_SHAPE, Snow.NORTH_SWEATER_NECK2_SHAPE, NORTH_COAT_NECK3_SHAPE,
                NORTH_COAT_BOTTOM1_SHAPE, NORTH_COAT_BOTTOM2_SHAPE);

        protected static final VoxelShape NORTH_BELT_SHAPE = VoxelShapes.or(NORTH_BELT_STRAP_SHAPE,
                NORTH_BELT_BUCKLE_SHAPE);

        protected static final VoxelShape NORTH_SHAPE = VoxelShapes.or(NORTH_BODY_SHAPE, NORTH_COAT_SHAPE,
                NORTH_BELT_SHAPE);

        protected static final VoxelShape SOUTH_SHAPE = VeCollisionUtil.rotate180(Axis.Y, NORTH_SHAPE);
        protected static final VoxelShape WEST_SHAPE = VeCollisionUtil.rotate270(Axis.Y, NORTH_SHAPE);
        protected static final VoxelShape EAST_SHAPE = VeCollisionUtil.rotate90(Axis.Y, NORTH_SHAPE);
    }
}
