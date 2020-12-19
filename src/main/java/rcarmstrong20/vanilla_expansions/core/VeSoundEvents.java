package rcarmstrong20.vanilla_expansions.core;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import rcarmstrong20.vanilla_expansions.VanillaExpansions;

@Mod.EventBusSubscriber(modid = VanillaExpansions.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class VeSoundEvents
{
    private static final List<SoundEvent> SOUNDS = new ArrayList<>();

    public static SoundEvent blockMushroomBounce = register("block.mushroom_bounce");
    public static SoundEvent blockDarkMatterAmbient = register("block.dark_matter_ambient");
    public static SoundEvent blockDarkMatterHardens = register("block.dark_matter_hardens");
    public static SoundEvent uiWoodcutterTakeResult = register("ui.woodcutter.take_result");

    /**
     * @param name The name for the sound event.
     * @return new sound event.
     */
    private static SoundEvent register(String name)
    {
        ResourceLocation location = new ResourceLocation(VanillaExpansions.MOD_ID, name);
        SoundEvent event = new SoundEvent(location);
        event.setRegistryName(location);
        SOUNDS.add(event);
        return event;
    }

    @SubscribeEvent
    public static void registerSounds(final RegistryEvent.Register<SoundEvent> event)
    {
        SOUNDS.forEach(soundEvent -> event.getRegistry().register(soundEvent));
        SOUNDS.clear();

        VanillaExpansions.LOGGER.info("Sound events registered.");
    }
}
