package rcarmstrong20.vanilla_expansions.events;

import java.util.HashMap;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.Hand;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import rcarmstrong20.vanilla_expansions.core.VeFluidTags;
import rcarmstrong20.vanilla_expansions.core.VeItems;
import rcarmstrong20.vanilla_expansions.core.VeParticleTypes;
import rcarmstrong20.vanilla_expansions.util.VeParticleUtil;
import rcarmstrong20.vanilla_expansions.util.VeTimeUtil;

public class VeTickEvent
{
    public static final HashMap<Item, Integer> TOTEM_GUARDIAN_MAP = complileTotemMap(VeItems.totemOfTheGuardianI, 240,
            VeItems.totemOfTheGuardianII, 360, VeItems.totemOfTheGuardianIII, 480, VeItems.totemOfTheGuardianIV, 600);
    public static final HashMap<Item, Integer> TOTEM_BRUTE_MAP = complileTotemMap(VeItems.totemOfTheBruteI, 0,
            VeItems.totemOfTheBruteII, 1, VeItems.totemOfTheBruteIII, 2, VeItems.totemOfTheBruteIV, 3);

    @SubscribeEvent
    public void onPlayerTick(final TickEvent.PlayerTickEvent event)
    {
        PlayerEntity player = event.player;

        if (event.side.isServer() && player instanceof ServerPlayerEntity)
        {
            ServerPlayerEntity serverPlayer = (ServerPlayerEntity) event.player;
            ItemStack mainHandStack = serverPlayer.getItemInHand(Hand.MAIN_HAND);
            ItemStack offHandStack = serverPlayer.getItemInHand(Hand.OFF_HAND);

            if (activateTotemOfTheBrute(TOTEM_BRUTE_MAP, Hand.MAIN_HAND, serverPlayer))
            {
                mainHandStack.shrink(1);
            }
            else if (activateTotemOfTheBrute(TOTEM_BRUTE_MAP, Hand.OFF_HAND, serverPlayer))
            {
                offHandStack.shrink(1);
            }
            else if (activateTotemOfTheGuardian(TOTEM_GUARDIAN_MAP, Hand.MAIN_HAND, serverPlayer))
            {
                mainHandStack.shrink(1);
            }
            else if (activateTotemOfTheGuardian(TOTEM_GUARDIAN_MAP, Hand.OFF_HAND, serverPlayer))
            {
                offHandStack.shrink(1);
            }
        }

        // Push the player when in flowing dark matter.
        player.updateFluidHeightAndDoFluidPushing(VeFluidTags.darkMatter, 0.005);
    }

    /**
     * A helper method that constructs a hash map with 4 item, integer pairs.
     */
    private static HashMap<Item, Integer> complileTotemMap(Item item0, int int0, Item item1, int int1, Item item2,
            int int2, Item item3, int int3)
    {
        HashMap<Item, Integer> hashMap = new HashMap<>(4);

        hashMap.put(item0, int0);
        hashMap.put(item1, int1);
        hashMap.put(item2, int2);
        hashMap.put(item3, int3);

        return hashMap;
    }

    /**
     * Trigger the brute totem's power.
     *
     * @param itemToPowerLvl A map that associates the item with an amplifier level.
     * @param hand           The player's hand holding the item.
     * @param player         The player using this item.
     * @return true if the totem was used.
     */
    private boolean activateTotemOfTheBrute(HashMap<Item, Integer> itemToPowerLvl, Hand hand, ServerPlayerEntity player)
    {
        ItemStack heldStack = player.getItemInHand(hand);
        float halfHealth = player.getMaxHealth() / 2;

        if (itemToPowerLvl.containsKey(heldStack.getItem()) && player.getHealth() <= halfHealth
                && !(player.getCooldowns().isOnCooldown(heldStack.getItem())))
        {
            for (Item totem : itemToPowerLvl.keySet())
            {
                player.getCooldowns().addCooldown(totem, VeTimeUtil.convertSecsToTicks(60));
            }

            int effectTicks = VeTimeUtil.convertSecsToTicks(30);

            player.addEffect(
                    new EffectInstance(Effects.DAMAGE_BOOST, effectTicks, itemToPowerLvl.get(heldStack.getItem())));
            player.addEffect(new EffectInstance(Effects.DAMAGE_RESISTANCE, effectTicks, 1));

            VeParticleUtil.spawnTotemParticles(VeParticleTypes.totemOfTheBrute, player);
            return true;
        }
        return false;
    }

    /**
     * Trigger the guardian totem's power.
     *
     * @param itemToPowerLvl A map that associates the item with a time duration.
     * @param hand           The player's hand holding the item.
     * @param player         The player using this item.
     * @return true if the totem was used.
     */
    private boolean activateTotemOfTheGuardian(HashMap<Item, Integer> itemToPowerLvl, Hand hand,
            ServerPlayerEntity player)
    {
        ItemStack heldStack = player.getItemInHand(hand);
        int maxAir = player.getMaxAirSupply();

        if (itemToPowerLvl.containsKey(heldStack.getItem()) && player.getAirSupply() == 0
                && !(player.getCooldowns().isOnCooldown(heldStack.getItem())))
        {
            int ticks = VeTimeUtil.convertSecsToTicks(itemToPowerLvl.get(heldStack.getItem()));

            player.getCooldowns().addCooldown(heldStack.getItem(), ticks);

            player.addEffect(new EffectInstance(Effects.WATER_BREATHING, ticks));
            player.addEffect(new EffectInstance(Effects.NIGHT_VISION, ticks));
            player.setAirSupply(maxAir);

            VeParticleUtil.spawnTotemParticles(VeParticleTypes.totemOfTheGuardian, player);
            return true;
        }
        return false;
    }
}
