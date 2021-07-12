package rndmaccess.vanilla_expansions.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import rndmaccess.vanilla_expansions.util.VEBoxUtil;

public class VECatPlushBlock extends VEPlushBlock
{
    protected static final VoxelShape NORTH_FRONT_RIGHT_LEG = Block.box(9.0D, 0.0D, 7.5D, 10.0D, 2.0D, 8.5D);
    protected static final VoxelShape NORTH_FRONT_LEFT_LEG = Block.box(6.0D, 0.0D, 7.5D, 7.0D, 2.0D, 8.5D);
    protected static final VoxelShape NORTH_TORSO = Block.box(5.5D, 2.0D, 6.0D, 10.5D, 6.0D, 11.5D);
    protected static final VoxelShape NORTH_HEAD = Block.box(5.0D, 3.0D, 1.0D, 11.0D, 8.5D, 6.0D);
    protected static final VoxelShape NORTH_RIGHT_EAR = Block.box(9.0D, 8.5D, 4.0D, 10.0D, 9.5D, 6.0D);
    protected static final VoxelShape NORTH_LEFT_EAR = Block.box(6.0D, 8.5D, 4.0D, 7.0D, 9.5D, 6.0D);
    protected static final VoxelShape NORTH_RIGHT_EYE = Block.box(8.5D, 6.0D, 0.5D, 10.5D, 7.0D, 1.0D);
    protected static final VoxelShape NORTH_LEFT_EYE = Block.box(5.5D, 6.0D, 0.5D, 7.5D, 7.0D, 1.0D);
    protected static final VoxelShape NORTH_NOSE = Block.box(6.5D, 3.5D, 0.0D, 9.5D, 5.5D, 1.0D);
    protected static final VoxelShape NORTH_BACK_RIGHT_LEG = Block.box(9.0D, 0.0D, 10.0D, 10.0D, 2.0D, 11.0D);
    protected static final VoxelShape NORTH_BACK_LEFT_LEG = Block.box(6.0D, 0.0D, 10.0D, 7.0D, 2.0D, 11.0D);
    protected static final VoxelShape NORTH_TAIL1_PIECE = Block.box(7.5D, 3.0D, 11.5D, 8.5D, 4.0D, 14.0D);
    protected static final VoxelShape NORTH_TAIL2_PIECE = Block.box(7.5D, 2.0D, 13.0D, 8.5D, 3.0D, 16.0D);
    protected static final VoxelShape NORTH_LEGS = VoxelShapes.or(NORTH_FRONT_RIGHT_LEG, NORTH_FRONT_LEFT_LEG,
            NORTH_BACK_RIGHT_LEG, NORTH_BACK_LEFT_LEG);
    protected static final VoxelShape NORTH_TAIL = VoxelShapes.or(NORTH_TAIL1_PIECE, NORTH_TAIL2_PIECE);
    protected static final VoxelShape NORTH_EARS = VoxelShapes.or(NORTH_RIGHT_EAR, NORTH_LEFT_EAR);
    protected static final VoxelShape NORTH_EYES = VoxelShapes.or(NORTH_RIGHT_EYE, NORTH_LEFT_EYE);
    protected static final VoxelShape NORTH_BODY = VoxelShapes.or(NORTH_TORSO, NORTH_HEAD, NORTH_LEGS, NORTH_TAIL,
            NORTH_EARS, NORTH_EYES);
    protected static final VoxelShape NORTH_SHAPE = VoxelShapes.or(NORTH_BODY, NORTH_NOSE);
    protected static final VoxelShape SOUTH_SHAPE = VEBoxUtil.rotate180(NORTH_SHAPE);
    protected static final VoxelShape WEST_SHAPE = VEBoxUtil.rotate270(NORTH_SHAPE);
    protected static final VoxelShape EAST_SHAPE = VEBoxUtil.rotate90(NORTH_SHAPE);

    public VECatPlushBlock(Properties properties)
    {
        super(properties);
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context)
    {
        return defineShapes(state, NORTH_SHAPE, SOUTH_SHAPE, WEST_SHAPE, EAST_SHAPE);
    }
}
