package rndmaccess.vanilla_expansions.item;

import java.util.Arrays;
import java.util.List;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvents;
import rndmaccess.vanilla_expansions.core.VEItems;

public class VEGlassVialItem extends Item
{
    private static final List<EntityType<?>> ANIMALS_THAT_GIVE_BLOOD = Arrays.asList(EntityType.SHEEP,
            EntityType.CHICKEN, EntityType.COW, EntityType.RABBIT, EntityType.PIG, EntityType.MOOSHROOM);

    public VEGlassVialItem(Properties properties)
    {
        super(properties);
    }

    @Override
    public ActionResultType interactLivingEntity(ItemStack stack, PlayerEntity playerIn, LivingEntity target, Hand hand)
    {
        if (stack.getItem() == VEItems.glassVial)
        {
            if (target instanceof AnimalEntity && target.getHealth() > 0.0
                    && ANIMALS_THAT_GIVE_BLOOD.contains(target.getType()))
            {
                MobEntity animalTarget = (MobEntity) target;

                if (animalTarget.hurt(DamageSource.GENERIC, 1))
                {
                    playerIn.addItem(new ItemStack(VEItems.bloodVial));
                    animalTarget.playSound(SoundEvents.BOTTLE_FILL, 1.0F, 1.0F);
                    stack.shrink(1);
                    return ActionResultType.CONSUME;
                }
            }
        }
        return ActionResultType.PASS;
    }
}
