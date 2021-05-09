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

public class VeZombieDemonPlushBlock extends VeZombiePlushBlock
{
    protected static final VoxelShape ZOMBIE_DEMON_NORTH_HORN1_BOTTOM_SHAPE = Block.box(4.0D, 14.5D, 9.0D, 5.0D, 16.5D,
            10.0D);
    protected static final VoxelShape ZOMBIE_DEMON_NORTH_HORN1_TOP_SHAPE = Block.box(4.5D, 16.5D, 9.5D, 5.0D, 17.0D,
            10.0D);
    protected static final VoxelShape ZOMBIE_DEMON_NORTH_HORN2_BOTTOM_SHAPE = Block.box(11.0D, 14.5D, 9.0D, 12.0D,
            16.5D, 10.0D);
    protected static final VoxelShape ZOMBIE_DEMON_NORTH_HORN2_TOP_SHAPE = Block.box(11.0D, 16.5D, 9.5D, 11.5D, 17.0D,
            10.0D);

    protected static final VoxelShape ZOMBIE_DEMON_NORTH_HORNS_SHAPE = VoxelShapes.or(
            ZOMBIE_DEMON_NORTH_HORN1_BOTTOM_SHAPE, ZOMBIE_DEMON_NORTH_HORN1_TOP_SHAPE,
            ZOMBIE_DEMON_NORTH_HORN2_BOTTOM_SHAPE, ZOMBIE_DEMON_NORTH_HORN2_TOP_SHAPE);

    protected static final VoxelShape ZOMBIE_DEMON_NORTH_SHAPE = VoxelShapes.or(ZOMBIE_DEMON_NORTH_HORNS_SHAPE,
            ZOMBIE_NORTH_SHAPE);

    protected static final VoxelShape ZOMBIE_DEMON_SOUTH_SHAPE = VeBoxUtil.rotate180(Axis.Y, ZOMBIE_DEMON_NORTH_SHAPE);
    protected static final VoxelShape ZOMBIE_DEMON_WEST_SHAPE = VeBoxUtil.rotate270(Axis.Y, ZOMBIE_DEMON_NORTH_SHAPE);
    protected static final VoxelShape ZOMBIE_DEMON_EAST_SHAPE = VeBoxUtil.rotate90(Axis.Y, ZOMBIE_DEMON_NORTH_SHAPE);

    public VeZombieDemonPlushBlock(Properties properties)
    {
        super(properties);
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context)
    {
        return defineShapes(state, ZOMBIE_DEMON_NORTH_SHAPE, ZOMBIE_DEMON_SOUTH_SHAPE, ZOMBIE_DEMON_WEST_SHAPE,
                ZOMBIE_DEMON_EAST_SHAPE);
    }
}
