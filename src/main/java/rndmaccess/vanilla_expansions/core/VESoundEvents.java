package rndmaccess.vanilla_expansions.core;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import rndmaccess.vanilla_expansions.VanillaExpansions;

/**
 * A class for holding every sound event instance that vanilla expansions has.
 *
 * @author Ryan
 */
@Mod.EventBusSubscriber(modid = VanillaExpansions.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class VESoundEvents
{
    public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS,
            VanillaExpansions.MOD_ID);

    public static SoundEvent blockMushroomBounce = register("block.mushroom_bounce");
    public static SoundEvent blockDarkMatterAmbient = register("block.dark_matter_ambient");
    public static SoundEvent blockDarkMatterHardens = register("block.dark_matter_hardens");
    public static SoundEvent uiWoodcutterTakeResult = register("ui.woodcutter.take_result");
    public static SoundEvent uiTransmutationTableTakeResult = register("ui.transmutation_table.take_result");

    /**
     * @param name The name for the sound event.
     * @return new sound event.
     */
    private static SoundEvent register(String name)
    {
        ResourceLocation location = new ResourceLocation(VanillaExpansions.MOD_ID, name);
        SoundEvent event = new SoundEvent(location);
        SOUNDS.register(name, () -> event);
        return event;
    }
}
