package rcarmstrong20.vanilla_expansions.item;

import java.util.List;

import com.google.common.collect.ImmutableList;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import rcarmstrong20.vanilla_expansions.util.VeEffectUtil;
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
