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

public class VETotemOfTheBruteItem extends Item
{
    private int amplifier;

    public VETotemOfTheBruteItem(Properties properties, int amplifier)
    {
        super(properties);
        this.amplifier = amplifier;
    }

    @Override
    public ActionResult<ItemStack> use(World level, PlayerEntity player, Hand hand)
    {
        ItemStack stack = player.getItemInHand(hand);
        int effectTicks = VETimeUtil.convertSecsToTicks(30);

        player.addEffect(new EffectInstance(Effects.DAMAGE_BOOST, effectTicks, amplifier));
        player.addEffect(new EffectInstance(Effects.DAMAGE_RESISTANCE, effectTicks, 1));

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
        int duration = VETimeUtil.convertSecsToTicks(120);
        EffectInstance strengthInstance = new EffectInstance(Effects.DAMAGE_BOOST, duration, amplifier);
        EffectInstance resistanceInstance = new EffectInstance(Effects.DAMAGE_RESISTANCE, duration, 1);

        VEEffectUtil.addTotemEffectTooltip(ImmutableList.of(strengthInstance, resistanceInstance),
                VanillaExpansions.BRUTE_TOTEM_MAP, stack.getItem(), text);

        text.add(StringTextComponent.EMPTY);
        text.add(new TranslationTextComponent("bruteTotem.useDescLine1").withStyle(TextFormatting.DARK_PURPLE));
        text.add(new TranslationTextComponent("bruteTotem.useDescLine2").withStyle(TextFormatting.DARK_PURPLE));
        text.add(new TranslationTextComponent("bruteTotem.useDescLine3").withStyle(TextFormatting.DARK_PURPLE));
    }
}
