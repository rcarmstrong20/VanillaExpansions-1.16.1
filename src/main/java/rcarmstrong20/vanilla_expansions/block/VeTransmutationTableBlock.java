package rcarmstrong20.vanilla_expansions.block;

import java.util.Random;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.inventory.container.SimpleNamedContainerProvider;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.IWorldPosCallable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import rcarmstrong20.vanilla_expansions.inventory.container.VeTransmutationTableContainer;

public class VeTransmutationTableBlock extends Block
{
    private static final TranslationTextComponent NAME = new TranslationTextComponent("container.transmutation_table");
    protected static final VoxelShape SHAPE = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 12.0D, 16.0D);

    public VeTransmutationTableBlock(Properties properties)
    {
        super(properties);
    }

    @Override
    @Nullable
    public INamedContainerProvider getMenuProvider(BlockState state, World worldIn, BlockPos pos)
    {
        return new SimpleNamedContainerProvider((windowId, playerInventory, p_220283_4_) ->
        {
            return new VeTransmutationTableContainer(windowId, playerInventory, IWorldPosCallable.create(worldIn, pos));
        }, NAME);
    }

    @Override
    public ActionResultType use(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand,
            BlockRayTraceResult rayTrace)
    {
        if (world.isClientSide())
        {
            return ActionResultType.SUCCESS;
        }
        else
        {
            player.openMenu(state.getMenuProvider(world, pos));
            return ActionResultType.CONSUME;
        }
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader reader, BlockPos pos, ISelectionContext context)
    {
        return SHAPE;
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void animateTick(BlockState state, World world, BlockPos pos, Random random)
    {
        double x = pos.getX() + random.nextDouble();
        double y = pos.above().getY() - 0.5 + random.nextDouble();
        double z = pos.getZ() + random.nextDouble();
        double d1 = random.nextDouble();
        double d2 = random.nextDouble();

        world.addAlwaysVisibleParticle(ParticleTypes.ENCHANT, x, y, z, 0.0, d1, 0.0);
        world.addAlwaysVisibleParticle(ParticleTypes.ENCHANT, x, y, z, 0.0, d2, 0.0);
    }
}
