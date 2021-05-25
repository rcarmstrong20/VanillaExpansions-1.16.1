package rcarmstrong20.vanilla_expansions.item;

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
import rcarmstrong20.vanilla_expansions.core.VeParticleTypes;
import rcarmstrong20.vanilla_expansions.util.VeEffectUtil;
import rcarmstrong20.vanilla_expansions.util.VeParticleUtil;
import rcarmstrong20.vanilla_expansions.util.VeTimeUtil;

public class VeTotemOfTheGuardianItem extends Item
{
    private int duration;

    public VeTotemOfTheGuardianItem(Properties properties, int duration)
    {
        super(properties);
        this.duration = duration;
    }

    @Override
    public ActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand)
    {
        ItemStack stack = player.getItemInHand(hand);
        int ticks = VeTimeUtil.convertSecsToTicks(duration);

        player.addEffect(new EffectInstance(Effects.WATER_BREATHING, ticks));
        player.addEffect(new EffectInstance(Effects.NIGHT_VISION, ticks));

        if (!player.isCreative())
        {
            stack.shrink(1);
        }

        if (player instanceof ServerPlayerEntity)
        {
            VeParticleUtil.spawnTotemParticles(VeParticleTypes.totemOfTheGuardian, (ServerPlayerEntity) player);
        }
        return ActionResult.success(stack);
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(ItemStack stack, World world, List<ITextComponent> text, ITooltipFlag flag)
    {
        super.appendHoverText(stack, world, text, flag);

        int effectTicks = VeTimeUtil.convertSecsToTicks(duration);
        EffectInstance waterBreathingInstance = new EffectInstance(Effects.WATER_BREATHING, effectTicks);
        EffectInstance nightVisionInstance = new EffectInstance(Effects.NIGHT_VISION, effectTicks);

        VeEffectUtil.addTotemEffectTooltip(ImmutableList.of(waterBreathingInstance, nightVisionInstance), text);

        text.add(StringTextComponent.EMPTY);
        text.add(new TranslationTextComponent("guardianTotem.useDescLine1").withStyle(TextFormatting.DARK_PURPLE));
        text.add(new TranslationTextComponent("guardianTotem.useDescLine2").withStyle(TextFormatting.DARK_PURPLE));
        text.add(new TranslationTextComponent("guardianTotem.useDescLine3").withStyle(TextFormatting.DARK_PURPLE));
    }
}
