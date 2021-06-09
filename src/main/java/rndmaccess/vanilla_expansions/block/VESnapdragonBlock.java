package rndmaccess.vanilla_expansions.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.FlowerBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.FoxEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.Effect;
import net.minecraft.util.Direction;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.IPlantable;
import rndmaccess.vanilla_expansions.core.VEBlockTags;

public class VESnapdragonBlock extends FlowerBlock
{
    public VESnapdragonBlock(Effect effect, int effectDuration, Properties properties)
    {
        super(effect, effectDuration, properties);
    }

    @Override
    protected boolean mayPlaceOn(BlockState state, IBlockReader worldIn, BlockPos pos)
    {
        return isValidBlock(worldIn, pos);
    }

    @Override
    public boolean canSurvive(BlockState state, IWorldReader worldIn, BlockPos pos)
    {
        return isValidBlock(worldIn, pos);
    }

    @Override
    public boolean canSustainPlant(BlockState state, IBlockReader world, BlockPos pos, Direction facing,
            IPlantable plantable)
    {
        return isValidBlock(world, pos);
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void animateTick(BlockState stateIn, World worldIn, BlockPos pos, Random random)
    {
        double x = (double) pos.getX() + random.nextFloat();
        double y = (double) pos.getY() + random.nextFloat();
        double z = (double) pos.getZ() + random.nextFloat();

        worldIn.addParticle(ParticleTypes.PORTAL, x, y, z, 0.0D, 0.0D, 0.0D);
    }

    /**
     * A helper method used for checking that the plant placement is valid.
     *
     * @param world The current world.
     * @param pos   The plants position.
     * @return True if the soil can support the plant.
     */
    private boolean isValidBlock(IBlockReader world, BlockPos pos)
    {
        Block block = world.getBlockState(pos.below()).getBlock();

        if (block instanceof VEPlanterBoxBlock)
        {
            return VEBlockTags.endBoxes.contains(block) && VEBlockTags.endBoxable.contains(this.getBlock());
        }
        else
        {
            return VEBlockTags.endPlantable.contains(block);
        }
    }

    @Override
    public void entityInside(BlockState state, World world, BlockPos pos, Entity entity)
    {
        if (entity instanceof LivingEntity && entity.getType() != EntityType.BEE)
        {
            LivingEntity livingEntity = (LivingEntity) entity;

            if (!world.isClientSide() && !livingEntity.isCrouching() && entity.getDeltaMovement().y() == 0.0D)
            {
                double d0 = livingEntity.getX();
                double d1 = livingEntity.getY();
                double d2 = livingEntity.getZ();

                for (int i = 0; i < 16; ++i)
                {
                    double d3 = livingEntity.getX() + (livingEntity.getRandom().nextDouble() - 0.5D) * 16.0D;
                    double d4 = MathHelper.clamp(livingEntity.getY() + (livingEntity.getRandom().nextInt(16) - 8), 0.0D,
                            world.getHeight() - 1);
                    double d5 = livingEntity.getZ() + (livingEntity.getRandom().nextDouble() - 0.5D) * 16.0D;

                    if (livingEntity.isPassenger())
                    {
                        livingEntity.stopRiding();
                    }

                    if (livingEntity.randomTeleport(d3, d4, d5, true))
                    {
                        SoundEvent soundevent = livingEntity instanceof FoxEntity ? SoundEvents.FOX_TELEPORT
                                : SoundEvents.CHORUS_FRUIT_TELEPORT;
                        world.playSound((PlayerEntity) null, d0, d1, d2, soundevent, SoundCategory.PLAYERS, 1.0F, 1.0F);
                        livingEntity.playSound(soundevent, 1.0F, 1.0F);
                        break;
                    }
                }
            }
        }
    }
}
