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
        int secDuration = duration * 10; // 120 is 1 minute

        EffectInstance waterBreathingInstance = new EffectInstance(Effects.WATER_BREATHING, secDuration);

        VeEffectUtil.addTotemEffectTooltip(ImmutableList.of(waterBreathingInstance), text);

        text.add(StringTextComponent.EMPTY);
        text.add(new TranslationTextComponent("totem.useWhen").withStyle(TextFormatting.DARK_PURPLE));
        text.add(new TranslationTextComponent("totem.drownRequirement").withStyle(TextFormatting.BLUE));
        text.add(new TranslationTextComponent("totem.holdRequirement").withStyle(TextFormatting.BLUE));
    }
}
