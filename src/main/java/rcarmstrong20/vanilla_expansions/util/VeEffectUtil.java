package rcarmstrong20.vanilla_expansions.util;

import java.util.List;

import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.EffectUtils;
import net.minecraft.potion.Effects;
import net.minecraft.util.text.IFormattableTextComponent;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

public class VeEffectUtil
{
    /**
     * Adds the effect details for the effects provided.
     *
     * @param effects The effects to use.
     * @param text    The text to add to.
     */
    public static void addTotemEffectTooltip(List<EffectInstance> effects, List<ITextComponent> text)
    {
        for (EffectInstance effectinstance : effects)
        {
            IFormattableTextComponent textcomponent = new TranslationTextComponent(effectinstance.getDescriptionId());

            if (effectinstance.getAmplifier() > 0)
            {
                textcomponent = new TranslationTextComponent("potion.withAmplifier", textcomponent,
                        new TranslationTextComponent("potion.potency." + effectinstance.getAmplifier()));
            }

            textcomponent = new TranslationTextComponent("potion.withDuration", textcomponent,
                    EffectUtils.formatDuration(effectinstance, 1));

            text.add(textcomponent.withStyle(Effects.LUCK.getCategory().getTooltipFormatting()));
        }
        /*
         * text.add(StringTextComponent.EMPTY); text.add(new
         * TranslationTextComponent("potion.whenDrank").withStyle(TextFormatting.
         * DARK_PURPLE));
         * 
         * for (EffectInstance effectinstance : effects) { IFormattableTextComponent
         * textcomponent2 = new
         * TranslationTextComponent(effectinstance.getDescriptionId()); double percent =
         * (1 + effectinstance.getAmplifier()) * 20;
         * 
         * textcomponent2 = new TranslationTextComponent( "+" +
         * ItemStack.ATTRIBUTE_MODIFIER_FORMAT.format(percent) +
         * "% ").append(textcomponent2);
         * 
         * text.add(textcomponent2.withStyle(TextFormatting.BLUE)); }
         */
    }
}
