package rndmaccess.vanilla_expansions.entity.hostile;

import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import net.minecraft.block.Blocks;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.monster.MagmaCubeEntity;
import net.minecraft.entity.monster.ZombieEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Direction;
import net.minecraft.util.EntityPredicates;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import rndmaccess.vanilla_expansions.core.VEEntityTypes;

public class VECharredRemnantEntity extends ZombieEntity
{
    public VECharredRemnantEntity(EntityType<? extends ZombieEntity> zombie, World world)
    {
        super(VEEntityTypes.charredRemnant, world);
    }

    public static boolean checkCharredRemnantSpawnRules(EntityType<VECharredRemnantEntity> entity, IWorld world,
            SpawnReason reason, BlockPos pos, Random random)
    {
        BlockPos.Mutable blockpos$mutable = pos.mutable();

        do
        {
            blockpos$mutable.move(Direction.UP);
        }
        while (!world.getBlockState(blockpos$mutable).isAir());

        return world.getFluidState(blockpos$mutable.below()).is(FluidTags.LAVA);
    }

    @Override
    @Nullable
    public ILivingEntityData finalizeSpawn(IServerWorld world, DifficultyInstance difficulty, SpawnReason reason,
            @Nullable ILivingEntityData livingData, @Nullable CompoundNBT compound)
    {
        this.getAttribute(Attributes.FOLLOW_RANGE).addPermanentModifier(new AttributeModifier("Random spawn bonus",
                this.random.nextGaussian() * 0.05D, AttributeModifier.Operation.MULTIPLY_BASE));
        if (this.random.nextFloat() < 0.05F)
        {
            this.setLeftHanded(true);
        }
        else
        {
            this.setLeftHanded(false);
        }

        float f = difficulty.getSpecialMultiplier();
        this.setCanPickUpLoot(this.random.nextFloat() < 0.55F * f);
        if (livingData == null)
        {
            livingData = new ZombieEntity.GroupData(getSpawnAsBabyOdds(world.getRandom()), true);
        }

        if (livingData instanceof ZombieEntity.GroupData)
        {
            ZombieEntity.GroupData zombieentity$groupdata = (ZombieEntity.GroupData) livingData;
            if (zombieentity$groupdata.isBaby)
            {
                this.setBaby(true);
                if (zombieentity$groupdata.canSpawnJockey)
                {
                    if (world.getRandom().nextFloat() < 0.05D)
                    {
                        List<MagmaCubeEntity> list = world.getEntitiesOfClass(MagmaCubeEntity.class,
                                this.getBoundingBox().inflate(5.0D, 3.0D, 5.0D),
                                EntityPredicates.ENTITY_NOT_BEING_RIDDEN);
                        if (!list.isEmpty())
                        {
                            MagmaCubeEntity magmaCubeEntity = list.get(0);
                            magmaCubeEntity.setBaby(true);
                            magmaCubeEntity.positionRider(this);
                            this.startRiding(magmaCubeEntity);
                        }
                    }
                    else if (world.getRandom().nextFloat() < 0.05D)
                    {
                        MagmaCubeEntity magmaCubeEntity1 = EntityType.MAGMA_CUBE.create(this.level);
                        magmaCubeEntity1.moveTo(this.getX(), this.getY(), this.getZ(), this.yRot, 0.0F);
                        magmaCubeEntity1.finalizeSpawn(world, difficulty, SpawnReason.JOCKEY, (ILivingEntityData) null,
                                (CompoundNBT) null);
                        magmaCubeEntity1.setBaby(true);
                        magmaCubeEntity1.positionRider(this);
                        this.startRiding(magmaCubeEntity1);
                        world.addFreshEntity(magmaCubeEntity1);
                    }
                }
            }

            this.setCanBreakDoors(this.supportsBreakDoorGoal() && this.random.nextFloat() < f * 0.1F);
            this.populateDefaultEquipmentSlots(difficulty);
            this.populateDefaultEquipmentEnchantments(difficulty);
        }

        if (this.getItemBySlot(EquipmentSlotType.HEAD).isEmpty())
        {
            LocalDate localdate = LocalDate.now();
            int i = localdate.get(ChronoField.DAY_OF_MONTH);
            int j = localdate.get(ChronoField.MONTH_OF_YEAR);
            if (j == 10 && i == 31 && this.random.nextFloat() < 0.25F)
            {
                this.setItemSlot(EquipmentSlotType.HEAD,
                        new ItemStack(this.random.nextFloat() < 0.1F ? Blocks.JACK_O_LANTERN : Blocks.CARVED_PUMPKIN));
                this.armorDropChances[EquipmentSlotType.HEAD.getIndex()] = 0.0F;
            }
        }

        this.handleAttributes(f);
        return livingData;
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
    public boolean canStandOnFluid(Fluid fluid)
    {
        return fluid.is(FluidTags.LAVA);
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

    @Override
    public void tick()
    {
        super.tick();
        this.floatInLava();
    }

    private void floatInLava()
    {
        if (this.isInLava())
        {
            ISelectionContext iselectioncontext = ISelectionContext.of(this);
            if (iselectioncontext.isAbove(FlowingFluidBlock.STABLE_SHAPE, this.blockPosition(), true)
                    && !this.level.getFluidState(this.blockPosition().above()).is(FluidTags.LAVA))
            {
                this.onGround = true;
            }
            else
            {
                this.setDeltaMovement(this.getDeltaMovement().scale(0.5D).add(0.0D, 0.05D, 0.0D));
            }
        }
    }

    @Override
    public boolean checkSpawnObstruction(IWorldReader p_205019_1_)
    {
        return p_205019_1_.isUnobstructed(this);
    }
}
