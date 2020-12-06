package rcarmstrong20.vanilla_expansions.core;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.particles.BasicParticleType;
import net.minecraft.particles.ParticleType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import rcarmstrong20.vanilla_expansions.VanillaExpansions;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class VeParticleTypes
{
    private static final List<BasicParticleType> PARTICLES = new ArrayList<>();

    public static BasicParticleType drippingDarkMatter = register("dripping_dark_matter", false);
    public static BasicParticleType fallingDarkMatter = register("falling_dark_matter", false);
    public static BasicParticleType landingDarkMatter = register("landing_dark_matter", false);
    public static BasicParticleType underDarkMatter = register("under_dark_matter", true);
    public static BasicParticleType whiteSpark = register("white_spark", false);
    public static BasicParticleType orangeSpark = register("orange_spark", false);
    public static BasicParticleType magentaSpark = register("magenta_spark", false);
    public static BasicParticleType lightBlueSpark = register("light_blue_spark", false);
    public static BasicParticleType yellowSpark = register("yellow_spark", false);
    public static BasicParticleType limeSpark = register("lime_spark", false);
    public static BasicParticleType pinkSpark = register("pink_spark", false);
    public static BasicParticleType graySpark = register("gray_spark", false);
    public static BasicParticleType lightGraySpark = register("light_gray_spark", false);
    public static BasicParticleType cyanSpark = register("cyan_spark", false);
    public static BasicParticleType purpleSpark = register("purple_spark", false);
    public static BasicParticleType blueSpark = register("blue_spark", false);
    public static BasicParticleType brownSpark = register("brown_spark", false);
    public static BasicParticleType greenSpark = register("green_spark", false);
    public static BasicParticleType redSpark = register("red_spark", false);
    public static BasicParticleType blackSpark = register("black_spark", false);
    public static BasicParticleType totemOfTheGuardian = register("totem_of_the_guardian", false);
    public static BasicParticleType totemOfTheBrute = register("totem_of_the_brute", false);

    private static BasicParticleType register(String name, boolean alwaysShow)
    {
        return register(new ResourceLocation(VanillaExpansions.MOD_ID, name), new BasicParticleType(alwaysShow));
    }

    /**
     * @param name     A name for the particle type.
     * @param particle A new instance of BasicParticleType for the particle type.
     * @return A new particle type.
     */
    private static BasicParticleType register(ResourceLocation name, BasicParticleType particle)
    {
        particle.setRegistryName(name);
        PARTICLES.add(particle);
        return particle;
    }

    @SubscribeEvent
    public static void registerParticles(final RegistryEvent.Register<ParticleType<?>> event)
    {
        PARTICLES.forEach(particle -> event.getRegistry().register(particle));
        PARTICLES.clear();
        VanillaExpansions.LOGGER.info("Particles registered.");
    }
}