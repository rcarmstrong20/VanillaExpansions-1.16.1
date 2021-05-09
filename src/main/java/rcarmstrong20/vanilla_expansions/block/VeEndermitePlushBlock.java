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

public class VeEndermitePlushBlock extends VePlushBlock
{
    protected static final VoxelShape ENDERMITE_NORTH_FRONT_SHAPE = Block.box(6.0D, 0.0D, 3.5D, 10.0D, 3.0D, 4.5D);
    protected static final VoxelShape ENDERMITE_NORTH_MIDDLE_SHAPE = Block.box(5.0D, 0.0D, 4.5D, 11.0D, 5.0D, 10.5D);
    protected static final VoxelShape ENDERMITE_NORTH_BACK1_SHAPE = Block.box(6.0D, 0.0D, 10.5D, 10.0D, 4.0D, 11.5D);
    protected static final VoxelShape ENDERMITE_NORTH_BACK2_SHAPE = Block.box(7.0D, 0.0D, 11.5D, 9.0D, 2.0D, 12.5D);

    protected static final VoxelShape ENDERMITE_NORTH_SHAPE = VoxelShapes.or(ENDERMITE_NORTH_FRONT_SHAPE,
            ENDERMITE_NORTH_MIDDLE_SHAPE, ENDERMITE_NORTH_BACK1_SHAPE, ENDERMITE_NORTH_BACK2_SHAPE);

    protected static final VoxelShape ENDERMITE_SOUTH_SHAPE = VeBoxUtil.rotate180(Axis.Y, ENDERMITE_NORTH_SHAPE);
    protected static final VoxelShape ENDERMITE_WEST_SHAPE = VeBoxUtil.rotate270(Axis.Y, ENDERMITE_NORTH_SHAPE);
    protected static final VoxelShape ENDERMITE_EAST_SHAPE = VeBoxUtil.rotate90(Axis.Y, ENDERMITE_NORTH_SHAPE);

    public VeEndermitePlushBlock(Properties properties)
    {
        super(properties);
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context)
    {
        return defineShapes(state, ENDERMITE_NORTH_SHAPE, ENDERMITE_SOUTH_SHAPE, ENDERMITE_WEST_SHAPE,
                ENDERMITE_EAST_SHAPE);
    }
}
