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

public class VeEndermanPlushBlock extends VePlushBlock
{
    protected static final VoxelShape ENDERMAN_NORTH_HEAD_SHAPE = Block.box(5.5D, 12.0D, 7.0D, 10.5D, 16.0D, 12.0D);
    protected static final VoxelShape ENDERMAN_NORTH_TORSO_SHAPE = Block.box(6.0D, 7.5D, 7.5D, 10.0D, 12.0D, 11.5D);
    protected static final VoxelShape ENDERMAN_NORTH_RIGHT_EYE_SHAPE = Block.box(8.5D, 14.5D, 6.5D, 10.0D, 15.0D, 7.0D);
    protected static final VoxelShape ENDERMAN_NORTH_LEFT_EYE_SHAPE = Block.box(6.0D, 14.5D, 6.5D, 7.5D, 15.0D, 7.0D);
    protected static final VoxelShape ENDERMAN_NORTH_RIGHT_LEG_SHAPE = Block.box(8.5D, 0.0D, 9.0D, 9.5D, 7.5D, 10.0D);
    protected static final VoxelShape ENDERMAN_NORTH_LEFT_LEG_SHAPE = Block.box(6.5D, 0.0D, 9.0D, 7.5D, 7.5D, 10.0D);
    protected static final VoxelShape ENDERMAN_NORTH_RIGHT_ARM1_SHAPE = Block.box(10.0D, 9.5D, 7.0D, 11.0D, 11.5D,
            8.0D);
    protected static final VoxelShape ENDERMAN_NORTH_RIGHT_ARM2_SHAPE = Block.box(9.0D, 7.5D, 7.0D, 10.0D, 10.5D, 8.0D);
    protected static final VoxelShape ENDERMAN_NORTH_MIDDLE_ARM_SHAPE = Block.box(7.0D, 4.5D, 7.0D, 9.0D, 8.5D, 8.0D);
    protected static final VoxelShape ENDERMAN_NORTH_LEFT_ARM1_SHAPE = Block.box(5.0D, 9.5D, 7.0D, 6.0D, 11.5D, 8.0D);
    protected static final VoxelShape ENDERMAN_NORTH_LEFT_ARM2_SHAPE = Block.box(6.0D, 7.5D, 7.0D, 7.0D, 10.5D, 8.0D);
    protected static final VoxelShape ENDERMAN_NORTH_GRASS_BLOCK_SHAPE = Block.box(6.5D, 5.0D, 4.0D, 9.5D, 8.0D, 7.0D);

    protected static final VoxelShape ENDERMAN_NORTH_BODY_SHAPE = VoxelShapes.or(ENDERMAN_NORTH_HEAD_SHAPE,
            ENDERMAN_NORTH_TORSO_SHAPE);

    protected static final VoxelShape ENDERMAN_NORTH_EYES_SHAPE = VoxelShapes.or(ENDERMAN_NORTH_RIGHT_EYE_SHAPE,
            ENDERMAN_NORTH_LEFT_EYE_SHAPE);

    protected static final VoxelShape ENDERMAN_NORTH_LEGS_SHAPE = VoxelShapes.or(ENDERMAN_NORTH_RIGHT_LEG_SHAPE,
            ENDERMAN_NORTH_LEFT_LEG_SHAPE);

    protected static final VoxelShape ENDERMAN_NORTH_ARMS_SHAPE = VoxelShapes.or(ENDERMAN_NORTH_RIGHT_ARM1_SHAPE,
            ENDERMAN_NORTH_RIGHT_ARM2_SHAPE, ENDERMAN_NORTH_MIDDLE_ARM_SHAPE, ENDERMAN_NORTH_LEFT_ARM1_SHAPE,
            ENDERMAN_NORTH_LEFT_ARM2_SHAPE);

    protected static final VoxelShape ENDERMAN_NORTH_SHAPE = VoxelShapes.or(ENDERMAN_NORTH_BODY_SHAPE,
            ENDERMAN_NORTH_EYES_SHAPE, ENDERMAN_NORTH_LEGS_SHAPE, ENDERMAN_NORTH_ARMS_SHAPE,
            ENDERMAN_NORTH_GRASS_BLOCK_SHAPE);

    protected static final VoxelShape ENDERMAN_SOUTH_SHAPE = VeBoxUtil.rotate180(Axis.Y, ENDERMAN_NORTH_SHAPE);
    protected static final VoxelShape ENDERMAN_WEST_SHAPE = VeBoxUtil.rotate270(Axis.Y, ENDERMAN_NORTH_SHAPE);
    protected static final VoxelShape ENDERMAN_EAST_SHAPE = VeBoxUtil.rotate90(Axis.Y, ENDERMAN_NORTH_SHAPE);

    public VeEndermanPlushBlock(Properties properties)
    {
        super(properties);
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context)
    {
        return defineShapes(state, ENDERMAN_NORTH_SHAPE, ENDERMAN_SOUTH_SHAPE, ENDERMAN_WEST_SHAPE,
                ENDERMAN_EAST_SHAPE);
    }
}
