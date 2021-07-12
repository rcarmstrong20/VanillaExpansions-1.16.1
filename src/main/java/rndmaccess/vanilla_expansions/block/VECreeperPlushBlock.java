package rndmaccess.vanilla_expansions.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import rndmaccess.vanilla_expansions.util.VEBoxUtil;

public class VECreeperPlushBlock extends VEPlushBlock
{
    protected static final VoxelShape NORTH_HEAD = Block.box(4.0, 7.0, 4.0D, 12.0D, 15.0D, 12.0D);
    protected static final VoxelShape NORTH_TORSO = Block.box(6.0D, 3.0D, 6.0D, 10.0D, 7.0D, 10.0D);

    protected static final VoxelShape NORTH_BACK_FOOT = Block.box(6.0D, 0.0D, 10.0D, 10.0D, 3.0D, 13.0D);
    protected static final VoxelShape NORTH_FRONT_FOOT = Block.box(6.0D, 0.0D, 3.0D, 10.0D, 3.0D, 6.0D);

    protected static final VoxelShape NORTH_LEFT_EYE = Block.box(9.0D, 11.0D, 3.5D, 11.0D, 13.0D, 4.0D);
    protected static final VoxelShape NORTH_RIGHT_EYE = Block.box(5.0D, 11.0D, 3.5D, 7.0D, 13.0D, 4.0D);

    protected static final VoxelShape NORTH_MIDDLE_MOUTH = Block.box(7.5D, 9.0D, 3.5D, 8.5D, 11.0D, 4.0D);
    protected static final VoxelShape NORTH_RIGHT_MOUTH = Block.box(6.5D, 8.0D, 3.5D, 7.5D, 10.0D, 4.0D);
    protected static final VoxelShape NORTH_LEFT_MOUTH = Block.box(8.5D, 8.0D, 3.5D, 9.5D, 10.0D, 4.0D);

    protected static final VoxelShape NORTH_FEET = VoxelShapes.or(NORTH_BACK_FOOT, NORTH_FRONT_FOOT);
    protected static final VoxelShape NORTH_EYES = VoxelShapes.or(NORTH_LEFT_EYE, NORTH_RIGHT_EYE);
    protected static final VoxelShape NORTH_MOUTH = VoxelShapes.or(NORTH_MIDDLE_MOUTH, NORTH_RIGHT_MOUTH,
            NORTH_LEFT_MOUTH);
    protected static final VoxelShape NORTH_BODY = VoxelShapes.or(NORTH_HEAD, NORTH_TORSO);

    protected static final VoxelShape NORTH_SHAPE = VoxelShapes.or(NORTH_FEET, NORTH_EYES, NORTH_MOUTH, NORTH_BODY);
    protected static final VoxelShape SOUTH_SHAPE = VEBoxUtil.rotate180(NORTH_SHAPE);
    protected static final VoxelShape WEST_SHAPE = VEBoxUtil.rotate270(NORTH_SHAPE);
    protected static final VoxelShape EAST_SHAPE = VEBoxUtil.rotate90(NORTH_SHAPE);

    public VECreeperPlushBlock(Properties properties)
    {
        super(properties);
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context)
    {
        return defineShapes(state, NORTH_SHAPE, SOUTH_SHAPE, WEST_SHAPE, EAST_SHAPE);
    }
}
