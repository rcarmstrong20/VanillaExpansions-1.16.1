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

public class VESheepPlushBlock extends VEPlushBlock
{
    protected static final VoxelShape NORTH_TORSO = Block.box(4.0D, 4.0D, 4.5D, 12.0D, 9.0D, 14.0D);
    protected static final VoxelShape NORTH_HEAD = Block.box(4.5D, 5.0D, 2.0D, 11.5D, 12.0D, 8.0D);

    protected static final VoxelShape NORTH_FRONT_RIGHT_LEG_TUFF = Block.box(4.0D, 2.0D, 4.5D, 7.0D, 4.0D, 7.5D);
    protected static final VoxelShape NORTH_FRONT_RIGHT_LEG = Block.box(4.5D, 0.0D, 5.0D, 6.5D, 2.0D, 7.0D);

    protected static final VoxelShape NORTH_BACK_LEFT_LEG_TUFF = Block.box(9.0D, 2.0D, 11.0D, 12.0D, 4.0D, 14.0D);
    protected static final VoxelShape NORTH_BACK_LEFT_LEG = Block.box(9.5D, 0.0D, 11.5D, 11.5D, 2.0D, 13.5D);

    protected static final VoxelShape NORTH_FRONT_LEFT_LEG_TUFF = Block.box(9.0D, 2.0D, 4.5D, 12.0D, 4.0D, 7.5D);
    protected static final VoxelShape NORTH_FRONT_LEFT_LEG = Block.box(9.5D, 0.0D, 5.0D, 11.5D, 2.0D, 7.0D);

    protected static final VoxelShape NORTH_BACK_RIGHT_LEG_TUFF = Block.box(4.0D, 2.0D, 11.0D, 7.0D, 4.0D, 14.0D);
    protected static final VoxelShape NORTH_BACK_RIGHT_LEG = Block.box(4.5D, 0.0D, 11.5D, 6.5D, 2.0D, 13.5D);

    protected static final VoxelShape NORTH_LEFT_EYE = Block.box(8.5D, 8.0D, 1.5D, 10.5D, 9.0D, 2.0D);
    protected static final VoxelShape NORTH_RIGHT_EYE = Block.box(5.5D, 8.0D, 1.5D, 7.5D, 9.0D, 2.0D);

    protected static final VoxelShape NORTH_MOUTH = Block.box(7.5D, 6.0D, 1.5D, 8.5D, 7.0D, 2.0D);

    protected static final VoxelShape NORTH_LEG_TUFFS = VoxelShapes.or(NORTH_FRONT_RIGHT_LEG_TUFF,
            NORTH_BACK_LEFT_LEG_TUFF, NORTH_FRONT_LEFT_LEG_TUFF, NORTH_BACK_RIGHT_LEG_TUFF);
    protected static final VoxelShape NORTH_LEGS = VoxelShapes.or(NORTH_FRONT_RIGHT_LEG, NORTH_BACK_LEFT_LEG,
            NORTH_FRONT_LEFT_LEG, NORTH_BACK_RIGHT_LEG);
    protected static final VoxelShape NORTH_EYES = VoxelShapes.or(NORTH_LEFT_EYE, NORTH_RIGHT_EYE);

    protected static final VoxelShape SHEEP_NORTH_SHAPE = VoxelShapes.or(NORTH_TORSO, NORTH_HEAD, NORTH_MOUTH,
            NORTH_LEG_TUFFS, NORTH_LEGS, NORTH_EYES);

    protected static final VoxelShape SHEEP_SOUTH_SHAPE = VEBoxBlockUtil.rotate180(Axis.Y, SHEEP_NORTH_SHAPE);
    protected static final VoxelShape SHEEP_WEST_SHAPE = VEBoxBlockUtil.rotate270(Axis.Y, SHEEP_NORTH_SHAPE);
    protected static final VoxelShape SHEEP_EAST_SHAPE = VEBoxBlockUtil.rotate90(Axis.Y, SHEEP_NORTH_SHAPE);

    public VESheepPlushBlock(Properties properties)
    {
        super(properties);
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context)
    {
        return defineShapes(state, SHEEP_NORTH_SHAPE, SHEEP_SOUTH_SHAPE, SHEEP_WEST_SHAPE, SHEEP_EAST_SHAPE);
    }
}
