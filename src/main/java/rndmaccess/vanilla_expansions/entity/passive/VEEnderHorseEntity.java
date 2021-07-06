package rndmaccess.vanilla_expansions.entity.passive;

import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.horse.HorseEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Direction;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.event.entity.living.EntityTeleportEvent;
import rndmaccess.vanilla_expansions.core.VEEntityTypes;

public class VEEnderHorseEntity extends HorseEntity
{
    public VEEnderHorseEntity(EntityType<? extends HorseEntity> horseEntiy, World world)
    {
        super(VEEntityTypes.enderHorse, world);
    }

    @Override
    public boolean isSensitiveToWater()
    {
        return true;
    }

    protected boolean teleport()
    {
        if (!this.level.isClientSide() && this.isAlive())
        {
            double d0 = this.getX() + (this.random.nextDouble() - 0.5D) * 64.0D;
            double d1 = this.getY() + (this.random.nextInt(64) - 32);
            double d2 = this.getZ() + (this.random.nextDouble() - 0.5D) * 64.0D;
            return this.teleport(d0, d1, d2);
        }
        else
        {
            return false;
        }
    }

    private boolean teleportTowards(Entity p_70816_1_)
    {
        Vector3d vector3d = new Vector3d(this.getX() - p_70816_1_.getX(), this.getY(0.5D) - p_70816_1_.getEyeY(),
                this.getZ() - p_70816_1_.getZ());
        vector3d = vector3d.normalize();
        double d0 = 16.0D;
        double d1 = this.getX() + (this.random.nextDouble() - 0.5D) * 8.0D - vector3d.x * 16.0D;
        double d2 = this.getY() + (this.random.nextInt(16) - 8) - vector3d.y * 16.0D;
        double d3 = this.getZ() + (this.random.nextDouble() - 0.5D) * 8.0D - vector3d.z * 16.0D;
        return this.teleport(d1, d2, d3);
    }

    private boolean teleport(double x, double y, double z)
    {
        BlockPos.Mutable blockpos$mutable = new BlockPos.Mutable(x, y, z);

        while (blockpos$mutable.getY() > 0 && !this.level.getBlockState(blockpos$mutable).getMaterial().blocksMotion())
        {
            blockpos$mutable.move(Direction.DOWN);
        }

        BlockState blockstate = this.level.getBlockState(blockpos$mutable);
        boolean flag = blockstate.getMaterial().blocksMotion();
        boolean flag1 = blockstate.getFluidState().is(FluidTags.WATER);
        if (flag && !flag1)
        {
            EntityTeleportEvent.EnderEntity event = ForgeEventFactory.onEnderTeleport(this, x, y, z);
            if (event.isCanceled())
                return false;
            boolean flag2 = this.randomTeleport(event.getTargetX(), event.getTargetY(), event.getTargetZ(), true);
            if (flag2 && !this.isSilent())
            {
                this.level.playSound((PlayerEntity) null, this.xo, this.yo, this.zo, SoundEvents.ENDERMAN_TELEPORT,
                        this.getSoundSource(), 1.0F, 1.0F);
                this.playSound(SoundEvents.ENDERMAN_TELEPORT, 1.0F, 1.0F);
            }

            return flag2;
        }
        else
        {
            return false;
        }
    }

    @Override
    protected void registerGoals()
    {
        super.registerGoals();
        // this.targetSelector.addGoal(0, );
    }

    @Override
    protected SoundEvent getAmbientSound()
    {
        super.getAmbientSound();
        return SoundEvents.HORSE_AMBIENT;
    }

    @Override
    protected SoundEvent getDeathSound()
    {
        super.getDeathSound();
        return SoundEvents.HORSE_DEATH;
    }
    /*
     * static class FindPlayerGoal extends NearestAttackableTargetGoal<PlayerEntity>
     * { private final EndermanEntity enderman; private PlayerEntity pendingTarget;
     * private int aggroTime; private int teleportTime; private final
     * EntityPredicate startAggroTargetConditions; private final EntityPredicate
     * continueAggroTargetConditions = (new EntityPredicate()).allowUnseeable();
     *
     * public FindPlayerGoal(EndermanEntity p_i241912_1_, @Nullable
     * Predicate<LivingEntity> p_i241912_2_) { super(p_i241912_1_,
     * PlayerEntity.class, 10, false, false, p_i241912_2_); this.enderman =
     * p_i241912_1_; this.startAggroTargetConditions; }
     *
     * @Override public boolean canUse() { this.pendingTarget =
     * this.enderman.level.getNearestPlayer(this.startAggroTargetConditions,
     * this.enderman); return this.pendingTarget != null; }
     *
     * @Override public void start() { this.aggroTime = 5; this.teleportTime = 0;
     * this.enderman.setBeingStaredAt(); }
     *
     * @Override public void stop() { this.pendingTarget = null; super.stop(); }
     *
     * @Override public boolean canContinueToUse() { if (this.pendingTarget != null)
     * { if (!this.enderman.isLookingAtMe(this.pendingTarget)) { return false; }
     * else { this.enderman.lookAt(this.pendingTarget, 10.0F, 10.0F); return true; }
     * } else { return this.target != null &&
     * this.continueAggroTargetConditions.test(this.enderman, this.target) ? true :
     * super.canContinueToUse(); } }
     *
     * @Override public void tick() { if (this.enderman.getTarget() == null) {
     * super.setTarget((LivingEntity) null); }
     *
     * if (this.pendingTarget != null) { if (--this.aggroTime <= 0) { this.target =
     * this.pendingTarget; this.pendingTarget = null; super.start(); } } else { if
     * (this.target != null && !this.enderman.isPassenger()) { if
     * (this.enderman.isLookingAtMe((PlayerEntity) this.target)) { if
     * (this.target.distanceToSqr(this.enderman) < 16.0D) {
     * this.enderman.teleport(); }
     *
     * this.teleportTime = 0; } else if (this.target.distanceToSqr(this.enderman) >
     * 256.0D && this.teleportTime++ >= 30 &&
     * this.enderman.teleportTowards(this.target)) { this.teleportTime = 0; } }
     *
     * super.tick(); } } }
     */
}
