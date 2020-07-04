package rcarmstrong20.vanilla_expansions.item;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.GlassBottleItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvents;
import rcarmstrong20.vanilla_expansions.core.VeItems;

public class VeGlassVialItem extends GlassBottleItem
{
	public VeGlassVialItem(Properties properties)
	{
		super(properties);
	}
	
	@Override
	public ActionResultType itemInteractionForEntity(ItemStack stack, PlayerEntity playerIn, LivingEntity target, Hand hand)
	{
		if(stack.getItem() == VeItems.glass_vial)
		{
			if(target instanceof MobEntity && target.getType() != EntityType.ENDER_DRAGON && target.getType() != EntityType.WITHER)
			{
				MobEntity mobTarget = (MobEntity) target;
				
				mobTarget.playSound(SoundEvents.ITEM_BOTTLE_FILL, 1.0F, 1.0F);
				mobTarget.attackEntityFrom(DamageSource.GENERIC, 1);
				
				playerIn.addItemStackToInventory(new ItemStack(VeItems.blood_vial));
			}
			return ActionResultType.CONSUME;
		}
		return ActionResultType.PASS;
	}
}
