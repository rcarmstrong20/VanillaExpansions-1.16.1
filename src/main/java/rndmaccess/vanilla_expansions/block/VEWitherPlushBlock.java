package rndmaccess.vanilla_expansions.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import rndmaccess.vanilla_expansions.util.VEBoxUtil;

public class VEWitherPlushBlock extends VEPlushBlock
{
    protected static final VoxelShape NORTH_HEAD = Block.box(4.0, 8.0, 4.0, 12.0, 16.0, 12.0);
    protected static final VoxelShape NORTH_MOUTH = Block.box(6.0, 9.5, 3.5, 10.0, 10.5, 4.0);

    protected static final VoxelShape NORTH_RIGHT_EYE = Block.box(5.0, 12.0, 3.5, 7.0, 14.0, 4.0);
    protected static final VoxelShape NORTH_LEFT_EYE = Block.box(9.0, 12.0, 3.5, 11.0, 14.0, 4.0);

    protected static final VoxelShape NORTH_MAIN_TORSO = Block.box(6.5, 2.0, 6.5, 9.5, 8.0, 9.0);
    protected static final VoxelShape NORTH_TORSO_BEND = Block.box(6.5, 0.0, 6.5, 9.5, 4.0, 10.5);

    protected static final VoxelShape NORTH_TOP_SPIKE = Block.box(3.0, 5.0, 6.5, 13.0, 7.0, 9.0);
    protected static final VoxelShape NORTH_BOTTOM_SPIKE = Block.box(3.0, 2.0, 6.5, 13.0, 4.0, 9.0);

    protected static final VoxelShape NORTH_EYES = VoxelShapes.or(NORTH_RIGHT_EYE, NORTH_LEFT_EYE);
    protected static final VoxelShape NORTH_TORSO = VoxelShapes.or(NORTH_MAIN_TORSO, NORTH_TORSO_BEND);
    protected static final VoxelShape NORTH_SPIKES = VoxelShapes.or(NORTH_TOP_SPIKE, NORTH_BOTTOM_SPIKE);

    protected static final VoxelShape NORTH_SHAPE = VoxelShapes.or(NORTH_HEAD, NORTH_MOUTH, NORTH_EYES, NORTH_TORSO,
            NORTH_SPIKES);

    protected static final VoxelShape SOUTH_SHAPE = VEBoxUtil.rotate180(NORTH_SHAPE);
    protected static final VoxelShape WEST_SHAPE = VEBoxUtil.rotate270(NORTH_SHAPE);
    protected static final VoxelShape EAST_SHAPE = VEBoxUtil.rotate90(NORTH_SHAPE);

    public VEWitherPlushBlock(Properties properties)
    {
        super(properties);
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader reader, BlockPos pos, ISelectionContext context)
    {
        return defineShapes(state, NORTH_SHAPE, SOUTH_SHAPE, WEST_SHAPE, EAST_SHAPE);
    }
}
