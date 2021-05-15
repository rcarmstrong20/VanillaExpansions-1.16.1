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
import rcarmstrong20.vanilla_expansions.core.VeParticleTypes;
import rcarmstrong20.vanilla_expansions.util.VeEffectUtil;

public class VeTotemOfTheFortunateItem extends Item
{
    private int amplifier;

    public VeTotemOfTheFortunateItem(Properties properties, int amplifier)
    {
        super(properties);
        this.amplifier = amplifier;
    }

    @Override
    public ActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand)
    {
        player.addEffect(new EffectInstance(Effects.LUCK, 2410, amplifier)); // 2410 is a 2:00 duration
        player.playSound(SoundEvents.TOTEM_USE, 20000, 10000);
        ItemStack stack = player.getItemInHand(hand);
        int max = random.nextInt(15) + 15;

        if (!player.isCreative())
        {
            stack.shrink(1);
        }

        if (player instanceof ServerPlayerEntity)
        {
            ServerPlayerEntity serverPlayer = (ServerPlayerEntity) player;

            for (int i = 0; i <= max; i++)
            {
                int count = random.nextInt(5) + 5;
                double x = serverPlayer.getRandomX(2.0);
                double y = serverPlayer.getRandomY();
                double z = serverPlayer.getRandomZ(2.0);

                serverPlayer.getServer().overworld().sendParticles(serverPlayer, VeParticleTypes.totemOfTheFortunate,
                        true, x, y, z, count, 0.0, 1.0, 0.0, 0.0);
            }
        }
        return ActionResult.success(stack);
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(ItemStack stack, World world, List<ITextComponent> text, ITooltipFlag flag)
    {
        super.appendHoverText(stack, world, text, flag);

        EffectInstance luckInstance = new EffectInstance(Effects.LUCK, 2410, amplifier); // 2410 is a 2:00 duration

        VeEffectUtil.addTotemEffectTooltip(ImmutableList.of(luckInstance), text);
    }
}
