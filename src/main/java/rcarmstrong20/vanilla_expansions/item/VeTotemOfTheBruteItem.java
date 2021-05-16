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

public class VeTotemOfTheBruteItem extends Item
{
    private int amplifier;

    public VeTotemOfTheBruteItem(Properties properties, int amplifier)
    {
        super(properties);
        this.amplifier = amplifier;
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(ItemStack stack, World world, List<ITextComponent> text, ITooltipFlag flag)
    {
        super.appendHoverText(stack, world, text, flag);

        EffectInstance strengthInstance = new EffectInstance(Effects.DAMAGE_BOOST, 600, amplifier);
        EffectInstance resistanceInstance = new EffectInstance(Effects.DAMAGE_RESISTANCE, 600, 1);

        VeEffectUtil.addTotemEffectTooltip(ImmutableList.of(strengthInstance, resistanceInstance), text);

        text.add(StringTextComponent.EMPTY);
        text.add(new TranslationTextComponent("totem.consumeWhen").withStyle(TextFormatting.DARK_PURPLE));
        text.add(new TranslationTextComponent("totem.healthRequirement").withStyle(TextFormatting.BLUE));
        text.add(new TranslationTextComponent("totem.holdRequirement").withStyle(TextFormatting.BLUE));
    }
}
