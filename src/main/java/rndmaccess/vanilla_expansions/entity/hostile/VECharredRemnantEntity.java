package rndmaccess.vanilla_expansions.entity.hostile;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.monster.ZombieEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;
import rndmaccess.vanilla_expansions.core.VEEntityTypes;

public class VECharredRemnantEntity extends ZombieEntity
{
    public VECharredRemnantEntity(EntityType<? extends ZombieEntity> zombie, World world)
    {
        super(VEEntityTypes.charredRemnant, world);
    }

    @Override
    public boolean doHurtTarget(Entity entity)
    {
        boolean flag = super.doHurtTarget(entity);
        if (flag && this.getMainHandItem().isEmpty() && entity instanceof LivingEntity)
        {
            float f = this.level.getCurrentDifficultyAt(this.blockPosition()).getEffectiveDifficulty();
            ((LivingEntity) entity).addEffect(new EffectInstance(Effects.WEAKNESS, 140 * (int) f));
        }
        return flag;
    }

    @Override
    protected boolean isSunSensitive()
    {
        return false;
    }

    @Override
    protected SoundEvent getAmbientSound()
    {
        return SoundEvents.HUSK_AMBIENT;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource)
    {
        return SoundEvents.HUSK_HURT;
    }

    @Override
    protected SoundEvent getDeathSound()
    {
        return SoundEvents.HUSK_DEATH;
    }

    @Override
    protected SoundEvent getStepSound()
    {
        return SoundEvents.HUSK_STEP;
    }

    @Override
    protected boolean convertsInWater()
    {
        return true;
    }

    @Override
    protected void doUnderWaterConversion()
    {
        this.convertToZombieType(EntityType.HUSK);
        if (!this.isSilent())
        {
            this.level.levelEvent(null, 1041, this.blockPosition(), 0);
        }
    }

    @Override
    protected ItemStack getSkull()
    {
        return ItemStack.EMPTY;
    }

    @Override
    public boolean fireImmune()
    {
        return true;
    }
}
