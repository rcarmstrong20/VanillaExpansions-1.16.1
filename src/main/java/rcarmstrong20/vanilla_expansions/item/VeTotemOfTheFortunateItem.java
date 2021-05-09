package rcarmstrong20.vanilla_expansions.item;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;
import rcarmstrong20.vanilla_expansions.core.VeParticleTypes;

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
        player.addEffect(new EffectInstance(Effects.LUCK, 1400, amplifier));
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
}
