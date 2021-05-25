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

public class VeTotemOfTheMinerItem extends Item
{
    private int duration = VeTimeUtil.convertSecsToTicks(60);
    private int amplifier;

    public VeTotemOfTheMinerItem(Properties properties, int amplifier)
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
            VeParticleUtil.spawnTotemParticles(VeParticleTypes.totemOfTheBrute, (ServerPlayerEntity) player);
        }
        return ActionResult.consume(stack);
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(ItemStack stack, World world, List<ITextComponent> text, ITooltipFlag flag)
    {
        super.appendHoverText(stack, world, text, flag);

        EffectInstance strengthInstance = new EffectInstance(Effects.DIG_SPEED, duration, amplifier);
        EffectInstance resistanceInstance = new EffectInstance(Effects.NIGHT_VISION, duration);

        VeEffectUtil.addTotemEffectTooltip(ImmutableList.of(strengthInstance, resistanceInstance), text);

        text.add(StringTextComponent.EMPTY);
        text.add(new TranslationTextComponent("minerTotem.useDescLine1").withStyle(TextFormatting.DARK_PURPLE));
        text.add(new TranslationTextComponent("minerTotem.useDescLine2").withStyle(TextFormatting.DARK_PURPLE));
        text.add(new TranslationTextComponent("minerTotem.useDescLine3").withStyle(TextFormatting.DARK_PURPLE));
    }
}
