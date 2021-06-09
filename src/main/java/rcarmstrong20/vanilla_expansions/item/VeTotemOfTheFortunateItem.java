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
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import rcarmstrong20.vanilla_expansions.VanillaExpansions;
import rcarmstrong20.vanilla_expansions.core.VEParticleTypes;
import rcarmstrong20.vanilla_expansions.util.VEEffectUtil;
import rcarmstrong20.vanilla_expansions.util.VEParticleUtil;
import rcarmstrong20.vanilla_expansions.util.VETimeUtil;

public class VETotemOfTheFortunateItem extends Item
{
    private int amplifier;

    public VETotemOfTheFortunateItem(Properties properties, int amplifier)
    {
        super(properties);
        this.amplifier = amplifier;
    }

    @Override
    public ActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand)
    {
        ItemStack stack = player.getItemInHand(hand);

        player.addEffect(new EffectInstance(Effects.LUCK, VETimeUtil.convertSecsToTicks(120), amplifier));
        player.playSound(SoundEvents.TOTEM_USE, 20000, 10000);

        if (!player.isCreative())
        {
            stack.shrink(1);
        }

        if (player instanceof ServerPlayerEntity)
        {
            VEParticleUtil.spawnTotemParticles(VEParticleTypes.totemOfTheFortunate, (ServerPlayerEntity) player);
        }
        return ActionResult.success(stack);
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(ItemStack stack, World world, List<ITextComponent> text, ITooltipFlag flag)
    {
        EffectInstance luckInstance = new EffectInstance(Effects.LUCK, VETimeUtil.convertSecsToTicks(120), amplifier);

        super.appendHoverText(stack, world, text, flag);

        VEEffectUtil.addTotemEffectTooltip(ImmutableList.of(luckInstance), VanillaExpansions.FORTUNATE_TOTEM_MAP,
                stack.getItem(), text);
    }
}
