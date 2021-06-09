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

public class VEPigPlushBlock extends VEPlushBlock
{
    protected static final VoxelShape NORTH_BODY = Block.box(4.5D, 4.0D, 4.0D, 11.5D, 9.0D, 15.0D);
    protected static final VoxelShape NORTH_HEAD = Block.box(5.5D, 5.0D, 1.0D, 10.5D, 10.0D, 5.0D);
    protected static final VoxelShape NORTH_FRONT_RIGHT_LEG = Block.box(9.0D, 0.0D, 6.0D, 11.5D, 4.0D, 8.0D);
    protected static final VoxelShape NORTH_FRONT_LEFT_LEG = Block.box(4.5D, 0.0D, 6.0D, 7.0D, 4.0D, 8.0D);
    protected static final VoxelShape NORTH_BACK_RIGHT_LEG = Block.box(9.0D, 0.0D, 13.5D, 11.5D, 4.0D, 15.5D);
    protected static final VoxelShape NORTH_BACK_LEFT_LEG = Block.box(4.5D, 0.0D, 13.5D, 7.0D, 4.0D, 15.5D);
    protected static final VoxelShape NORTH_TAIL1 = Block.box(9.0D, 4.0D, 15.0D, 10.0D, 7.5D, 15.5D);
    protected static final VoxelShape NORTH_TAIL2 = Block.box(6.0D, 4.0D, 15.0D, 9.0D, 5.0D, 15.5D);
    protected static final VoxelShape NORTH_TAIL3 = Block.box(7.0D, 7.5D, 15.0D, 9.0D, 8.5D, 15.5D);
    protected static final VoxelShape NORTH_TAIL4 = Block.box(6.0D, 6.5D, 15.0D, 7.0D, 7.5D, 15.5D);
    protected static final VoxelShape NORTH_TAIL5 = Block.box(7.0D, 5.5D, 15.0D, 8.0D, 6.5D, 15.5D);
    protected static final VoxelShape NORTH_RIGHT_EYE = Block.box(8.5D, 7.5D, 0.5D, 10.0D, 8.5D, 1.0D);
    protected static final VoxelShape NORTH_LEFT_EYE = Block.box(6.0D, 7.5D, 0.5D, 7.5D, 8.5D, 1.0D);
    protected static final VoxelShape NORTH_SNOUT = Block.box(7.0D, 6.0D, 0.0D, 9.0D, 7.0D, 1.0D);
    protected static final VoxelShape NORTH_LEGS = VoxelShapes.or(NORTH_FRONT_RIGHT_LEG, NORTH_FRONT_LEFT_LEG,
            NORTH_BACK_RIGHT_LEG, NORTH_BACK_LEFT_LEG);
    protected static final VoxelShape NORTH_TAIL = VoxelShapes.or(NORTH_TAIL1, NORTH_TAIL2, NORTH_TAIL3, NORTH_TAIL4,
            NORTH_TAIL5);
    protected static final VoxelShape NORTH_EYES = VoxelShapes.or(NORTH_RIGHT_EYE, NORTH_LEFT_EYE);
    protected static final VoxelShape NORTH_SHAPE = VoxelShapes.or(NORTH_BODY, NORTH_HEAD, NORTH_LEGS, NORTH_TAIL,
            NORTH_EYES, NORTH_SNOUT);
    protected static final VoxelShape SOUTH_SHAPE = VEBoxBlockUtil.rotate180(Axis.Y, NORTH_SHAPE);
    protected static final VoxelShape WEST_SHAPE = VEBoxBlockUtil.rotate270(Axis.Y, NORTH_SHAPE);
    protected static final VoxelShape EAST_SHAPE = VEBoxBlockUtil.rotate90(Axis.Y, NORTH_SHAPE);

    public VEPigPlushBlock(Properties properties)
    {
        super(properties);
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context)
    {
        return defineShapes(state, NORTH_SHAPE, SOUTH_SHAPE, WEST_SHAPE, EAST_SHAPE);
    }
}
