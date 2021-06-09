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
import rcarmstrong20.vanilla_expansions.VanillaExpansions;
import rcarmstrong20.vanilla_expansions.core.VEParticleTypes;
import rcarmstrong20.vanilla_expansions.util.VEEffectUtil;
import rcarmstrong20.vanilla_expansions.util.VEParticleUtil;
import rcarmstrong20.vanilla_expansions.util.VETimeUtil;

public class VETotemOfThePhantomItem extends Item
{
    private int amplifier;

    public VETotemOfThePhantomItem(Properties properties, int amplifier)
    {
        super(properties);
        this.amplifier = amplifier;
    }

    @Override
    public ActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand)
    {
        ItemStack stack = player.getItemInHand(hand);
        int ticks = VETimeUtil.convertSecsToTicks(120);

        player.addEffect(new EffectInstance(Effects.SLOW_FALLING, ticks));
        player.addEffect(new EffectInstance(Effects.JUMP, ticks));

        if (!player.isCreative())
        {
            stack.shrink(1);
        }

        if (player instanceof ServerPlayerEntity)
        {
            VEParticleUtil.spawnTotemParticles(VEParticleTypes.totemOfTheGuardian, (ServerPlayerEntity) player);
        }
        return ActionResult.success(stack);
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(ItemStack stack, World world, List<ITextComponent> text, ITooltipFlag flag)
    {
        int effectTicks = VETimeUtil.convertSecsToTicks(120);
        EffectInstance leapingInstance = new EffectInstance(Effects.JUMP, effectTicks, amplifier);
        EffectInstance slowFallingInstance = new EffectInstance(Effects.SLOW_FALLING, effectTicks, 1);

        VEEffectUtil.addTotemEffectTooltip(ImmutableList.of(leapingInstance, slowFallingInstance),
                VanillaExpansions.PHANTOM_TOTEM_MAP, stack.getItem(), text);

        text.add(StringTextComponent.EMPTY);
        text.add(new TranslationTextComponent("phantomTotem.useDescLine1").withStyle(TextFormatting.DARK_PURPLE));
        text.add(new TranslationTextComponent("phantomTotem.useDescLine2").withStyle(TextFormatting.DARK_PURPLE));
        text.add(new TranslationTextComponent("phantomTotem.useDescLine3").withStyle(TextFormatting.DARK_PURPLE));
    }
}
