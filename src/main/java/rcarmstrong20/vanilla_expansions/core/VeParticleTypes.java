package rcarmstrong20.vanilla_expansions.core;

import net.minecraft.particles.BasicParticleType;
import net.minecraft.particles.ParticleType;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import rcarmstrong20.vanilla_expansions.VanillaExpansions;

/**
 * A class for holding every particle type instance that vanilla expansions has.
 *
 * @author Ryan
 */
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class VEParticleTypes
{
    public static final DeferredRegister<ParticleType<?>> PARTICLES = DeferredRegister
            .create(ForgeRegistries.PARTICLE_TYPES, VanillaExpansions.MOD_ID);

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
    public static BasicParticleType whiteFlame = register("white_flame", false);
    public static BasicParticleType orangeFlame = register("orange_flame", false);
    public static BasicParticleType magentaFlame = register("magenta_flame", false);
    public static BasicParticleType lightBlueFlame = register("light_blue_flame", false);
    public static BasicParticleType yellowFlame = register("yellow_flame", false);
    public static BasicParticleType limeFlame = register("lime_flame", false);
    public static BasicParticleType pinkFlame = register("pink_flame", false);
    public static BasicParticleType grayFlame = register("gray_flame", false);
    public static BasicParticleType lightGrayFlame = register("light_gray_flame", false);
    public static BasicParticleType cyanFlame = register("cyan_flame", false);
    public static BasicParticleType purpleFlame = register("purple_flame", false);
    public static BasicParticleType blueFlame = register("blue_flame", false);
    public static BasicParticleType brownFlame = register("brown_flame", false);
    public static BasicParticleType greenFlame = register("green_flame", false);
    public static BasicParticleType redFlame = register("red_flame", false);
    public static BasicParticleType blackFlame = register("black_flame", false);

    public static BasicParticleType totemOfTheGuardian = register("totem_of_the_guardian", false);
    public static BasicParticleType totemOfTheBrute = register("totem_of_the_brute", false);
    public static BasicParticleType totemOfTheFortunate = register("totem_of_the_fortunate", false);

    /**
     * @param name       The name of this particle.
     * @param alwaysShow
     * @return The particle added.
     */
    private static BasicParticleType register(String name, boolean alwaysShow)
    {
        BasicParticleType particle = new BasicParticleType(alwaysShow);

        PARTICLES.register(name, () -> particle);
        return particle;
    }
}