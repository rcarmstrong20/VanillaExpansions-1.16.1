package rcarmstrong20.vanilla_expansions.item;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;

public class VeTotemOfTheFortunateItem extends Item
{
    private int amplifier;

    public VeTotemOfTheFortunateItem(Properties properties, int amplifier)
    {
        super(properties);
        this.amplifier = amplifier;
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand)
    {
        player.addPotionEffect(new EffectInstance(Effects.LUCK, 1400, amplifier));
        player.playSound(SoundEvents.ITEM_TOTEM_USE, 20000, 10000);

        return ActionResult.resultSuccess(
                new ItemStack(player.getHeldItem(hand).getItem(), (player.getHeldItem(hand).getCount() - 1)));
    }
    /*
     * private ActionResult<ItemStack> useTotem() { if() { return
     * ActionResult.resultSuccess(); } else if() { return
     * ActionResult.resultSuccess(); } else { return ActionResult.resultPass(type);
     * } }
     */
}
