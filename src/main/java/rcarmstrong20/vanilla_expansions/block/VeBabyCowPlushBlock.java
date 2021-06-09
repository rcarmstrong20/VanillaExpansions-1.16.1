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

public class VEBabyCowPlushBlock extends VEPlushBlock
{
    protected static final VoxelShape BODY = Block.box(6.0D, 2.0D, 6.0D, 10.0D, 6.0D, 12.0D);
    protected static final VoxelShape HEAD = Block.box(5.0D, 2.0D, 2.0D, 11.0D, 7.0D, 6.5D);
    protected static final VoxelShape MOUTH = Block.box(6.5D, 2.0D, 1.5D, 9.5D, 4.0D, 2.0D);
    protected static final VoxelShape RIGHT_EYE = Block.box(6.0D, 4.0D, 1.5D, 7.0D, 5.0D, 2.0D);
    protected static final VoxelShape LEFT_EYE = Block.box(9.0D, 4.0D, 1.5D, 10.0D, 5.0D, 2.0D);
    protected static final VoxelShape RIGHT_HORN = Block.box(4.0D, 5.5D, 2.5D, 5.0D, 7.5D, 3.5D);
    protected static final VoxelShape LEFT_HORN = Block.box(11.0D, 5.5D, 2.5D, 12.0D, 7.5D, 3.5D);
    protected static final VoxelShape RIGHT_FRONT_LEG = Block.box(6.0D, 0.0D, 7.5D, 7.5D, 2.0D, 9.0D);
    protected static final VoxelShape LEFT_FRONT_LEG = Block.box(8.5D, 0.0D, 7.5D, 10.0D, 2.0D, 9.0D);
    protected static final VoxelShape RIGHT_BACK_LEG = Block.box(6.0D, 0.0D, 10.0D, 7.5D, 2.0D, 11.5D);
    protected static final VoxelShape LEFT_BACK_LEG = Block.box(8.5D, 0.0D, 10.0D, 10.0D, 2.0D, 11.5D);
    protected static final VoxelShape UTTER = Block.box(7.5D, 1.5D, 10.5D, 8.5D, 2.5D, 12.5D);
    protected static final VoxelShape FACE = VoxelShapes.or(MOUTH, RIGHT_EYE, LEFT_EYE);
    protected static final VoxelShape HORNS = VoxelShapes.or(RIGHT_HORN, LEFT_HORN);
    protected static final VoxelShape LEGS = VoxelShapes.or(RIGHT_FRONT_LEG, LEFT_FRONT_LEG, RIGHT_BACK_LEG,
            LEFT_BACK_LEG);
    protected static final VoxelShape NORTH_SHAPE = VoxelShapes.or(FACE, HORNS, LEGS, BODY, HEAD, UTTER);
    protected static final VoxelShape SOUTH_SHAPE = VEBoxBlockUtil.rotate180(Axis.Y, NORTH_SHAPE);
    protected static final VoxelShape WEST_SHAPE = VEBoxBlockUtil.rotate270(Axis.Y, NORTH_SHAPE);
    protected static final VoxelShape EAST_SHAPE = VEBoxBlockUtil.rotate90(Axis.Y, NORTH_SHAPE);

    public VEBabyCowPlushBlock(Properties properties)
    {
        super(properties);
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context)
    {
        return defineShapes(state, NORTH_SHAPE, SOUTH_SHAPE, WEST_SHAPE, EAST_SHAPE);
    }
}
