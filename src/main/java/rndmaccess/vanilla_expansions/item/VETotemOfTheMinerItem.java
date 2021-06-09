package rndmaccess.vanilla_expansions.item;

import java.util.List;

import com.google.common.collect.ImmutableList;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import rndmaccess.vanilla_expansions.VanillaExpansions;
import rndmaccess.vanilla_expansions.core.VEParticleTypes;
import rndmaccess.vanilla_expansions.util.VEEffectUtil;
import rndmaccess.vanilla_expansions.util.VEParticleUtil;
import rndmaccess.vanilla_expansions.util.VETimeUtil;

public class VETotemOfTheMinerItem extends Item
{
    private int duration = VETimeUtil.convertSecsToTicks(60);
    private int amplifier;

    public VETotemOfTheMinerItem(Properties properties, int amplifier)
    {
        super(properties);
        this.amplifier = amplifier;
    }

    @Override
    public ActionResult<ItemStack> use(World level, PlayerEntity player, Hand hand)
    {
        ItemStack stack = player.getItemInHand(hand);

        player.addEffect(new EffectInstance(Effects.DIG_SPEED, duration, amplifier));
        player.addEffect(new EffectInstance(Effects.NIGHT_VISION, duration));

        if (!player.isCreative())
        {
            stack.shrink(1);
        }

        if (player instanceof ServerPlayerEntity)
        {
            VEParticleUtil.spawnTotemParticles(VEParticleTypes.totemOfTheBrute, (ServerPlayerEntity) player);
        }
        return ActionResult.success(stack);
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(ItemStack stack, World world, List<ITextComponent> text, ITooltipFlag flag)
    {
        EffectInstance strengthInstance = new EffectInstance(Effects.DIG_SPEED, duration, amplifier);
        EffectInstance resistanceInstance = new EffectInstance(Effects.NIGHT_VISION, duration);

        VEEffectUtil.addTotemEffectTooltip(ImmutableList.of(strengthInstance, resistanceInstance),
                VanillaExpansions.MINER_TOTEM_MAP, stack.getItem(), text);

        text.add(StringTextComponent.EMPTY);
        text.add(new TranslationTextComponent("minerTotem.useDescLine1").withStyle(TextFormatting.DARK_PURPLE));
        text.add(new TranslationTextComponent("minerTotem.useDescLine2").withStyle(TextFormatting.DARK_PURPLE));
        text.add(new TranslationTextComponent("minerTotem.useDescLine3").withStyle(TextFormatting.DARK_PURPLE));
    }
}
