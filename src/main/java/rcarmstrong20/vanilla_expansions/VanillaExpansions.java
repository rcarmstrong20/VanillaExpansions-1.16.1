package rcarmstrong20.vanilla_expansions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.FlameParticle;
import net.minecraft.client.particle.LavaParticle;
import net.minecraft.client.particle.ParticleManager.IParticleMetaFactory;
import net.minecraft.particles.BasicParticleType;
import net.minecraft.particles.ParticleType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.ParticleFactoryRegisterEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig.Type;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLPaths;
import rcarmstrong20.vanilla_expansions.client.renderer.particle.VeDripParticle;
import rcarmstrong20.vanilla_expansions.client.renderer.particle.VeTotemParticle;
import rcarmstrong20.vanilla_expansions.client.renderer.particle.VeUnderDarkMatterParticle;
import rcarmstrong20.vanilla_expansions.config.VeConfig;
import rcarmstrong20.vanilla_expansions.core.VeBlocks;
import rcarmstrong20.vanilla_expansions.core.VeContainerTypes;
import rcarmstrong20.vanilla_expansions.core.VeFeature;
import rcarmstrong20.vanilla_expansions.core.VeFluids;
import rcarmstrong20.vanilla_expansions.core.VeItems;
import rcarmstrong20.vanilla_expansions.core.VePaintingType;
import rcarmstrong20.vanilla_expansions.core.VeParticleTypes;
import rcarmstrong20.vanilla_expansions.core.VePointOfInterestTypes;
import rcarmstrong20.vanilla_expansions.core.VeRecipeSerializers;
import rcarmstrong20.vanilla_expansions.core.VeSoundEvents;
import rcarmstrong20.vanilla_expansions.core.VeStructure;
import rcarmstrong20.vanilla_expansions.core.VeTileEntityType;
import rcarmstrong20.vanilla_expansions.core.VeVillagerProfessions;
import rcarmstrong20.vanilla_expansions.events.VeBiomeLoadingEvent;
import rcarmstrong20.vanilla_expansions.events.VeBlockEvent;
import rcarmstrong20.vanilla_expansions.events.VeBonemealEvent;
import rcarmstrong20.vanilla_expansions.events.VeCropsGrowEvent;
import rcarmstrong20.vanilla_expansions.events.VeLivingEvent;
import rcarmstrong20.vanilla_expansions.events.VeLoadEvent;
import rcarmstrong20.vanilla_expansions.events.VePlayerInteractEvent;
import rcarmstrong20.vanilla_expansions.events.VeTickEvent;
import rcarmstrong20.vanilla_expansions.events.VeViewRenderEvent;
import rcarmstrong20.vanilla_expansions.events.VeVillagerTradesEvent;
import rcarmstrong20.vanilla_expansions.proxy.ClientProxy;
import rcarmstrong20.vanilla_expansions.proxy.CommonProxy;

/**
 * The main mod class.
 *
 * @author Ryan
 */
@Mod("ve")
public class VanillaExpansions
{
    public static Object modInstance;
    public static final Logger LOGGER = LogManager.getLogger(VanillaExpansions.MOD_ID);
    public static final String MOD_ID = "ve";
    public static final VeItemGroup VE_GROUP = new VeItemGroup(VanillaExpansions.MOD_ID);
    public static final CommonProxy PROXY = DistExecutor.safeRunForDist(() -> ClientProxy::new, () -> CommonProxy::new);

    /**
     * This is where everything is registered to the game.
     */
    public VanillaExpansions()
    {
        modInstance = this;

        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        VeBlocks.BLOCKS.register(bus);
        VeBlocks.ITEMS.register(bus);
        VeItems.ITEMS.register(bus);
        VeContainerTypes.CONTAINERS.register(bus);
        VeFluids.FLUIDS.register(bus);
        VePaintingType.PAINTING_TYPES.register(bus);
        VeParticleTypes.PARTICLES.register(bus);
        VePointOfInterestTypes.POI_TYPES.register(bus);
        VeRecipeSerializers.RECIPE_SERIALIZERS.register(bus);
        VeSoundEvents.SOUNDS.register(bus);
        VeTileEntityType.TILE_ENTITY_TYPES.register(bus);
        VeVillagerProfessions.VILLAGER_PROFESSIONS.register(bus);
        VeFeature.FEATURES.register(bus);
        VeStructure.STRUCTURES.register(bus);

        bus.addListener(this::setup);
        bus.addListener(this::clientRegistries);
        bus.addListener(this::onRegisterParticle);
        ModLoadingContext.get().registerConfig(Type.SERVER, VeConfig.SERVER_CONFIG);
        ModLoadingContext.get().registerConfig(Type.CLIENT, VeConfig.CLIENT_CONFIG);

        VeConfig.loadConfig(VeConfig.SERVER_CONFIG, FMLPaths.CONFIGDIR.get().resolve("ve-server.toml").toString());
        VeConfig.loadConfig(VeConfig.CLIENT_CONFIG, FMLPaths.CONFIGDIR.get().resolve("ve-client.toml").toString());

        MinecraftForge.EVENT_BUS.register(new VeBiomeLoadingEvent());
        MinecraftForge.EVENT_BUS.register(new VeBlockEvent());
        MinecraftForge.EVENT_BUS.register(new VeBonemealEvent());
        MinecraftForge.EVENT_BUS.register(new VeCropsGrowEvent());
        MinecraftForge.EVENT_BUS.register(new VeLivingEvent());
        MinecraftForge.EVENT_BUS.register(new VeLoadEvent());
        MinecraftForge.EVENT_BUS.register(new VePlayerInteractEvent());
        MinecraftForge.EVENT_BUS.register(new VeTickEvent());
        MinecraftForge.EVENT_BUS.register(new VeViewRenderEvent());
        MinecraftForge.EVENT_BUS.register(new VeVillagerTradesEvent());
        MinecraftForge.EVENT_BUS.register(this);
    }

    /**
     * Called on the mod's setup.
     *
     * @param event Called on server and client setup.
     */
    private void setup(final FMLCommonSetupEvent event)
    {
        VanillaExpansions.LOGGER.info("setup method registered");
        PROXY.onSetupCommon();
    }

    /**
     * Called exclusively on the client.
     *
     * @param event Called on client setup using Dist.CLIENT.
     */
    private void clientRegistries(final FMLClientSetupEvent event)
    {
        VanillaExpansions.LOGGER.info("client method registered");
        PROXY.onSetupClient();
    }

    /**
     * This takes care of registering the particle factories if they are not
     * registered under the particle factory event there will be a bug.
     *
     * @param event Called during particle factory registry.
     */
    @OnlyIn(Dist.CLIENT)
    private void onRegisterParticle(ParticleFactoryRegisterEvent event)
    {
        registerFactory(VeParticleTypes.drippingDarkMatter, VeDripParticle.VeDrippingDarkMatterFactory::new);
        registerFactory(VeParticleTypes.fallingDarkMatter, VeDripParticle.VeFallingDarkMatterFactory::new);
        registerFactory(VeParticleTypes.landingDarkMatter, VeDripParticle.VeLandingDarkMatterFactory::new);
        registerFactory(VeParticleTypes.underDarkMatter, VeUnderDarkMatterParticle.Factory::new);
        registerFactory(VeParticleTypes.whiteSpark, LavaParticle.Factory::new);
        registerFactory(VeParticleTypes.orangeSpark, LavaParticle.Factory::new);
        registerFactory(VeParticleTypes.magentaSpark, LavaParticle.Factory::new);
        registerFactory(VeParticleTypes.lightBlueSpark, LavaParticle.Factory::new);
        registerFactory(VeParticleTypes.yellowSpark, LavaParticle.Factory::new);
        registerFactory(VeParticleTypes.limeSpark, LavaParticle.Factory::new);
        registerFactory(VeParticleTypes.pinkSpark, LavaParticle.Factory::new);
        registerFactory(VeParticleTypes.graySpark, LavaParticle.Factory::new);
        registerFactory(VeParticleTypes.lightGraySpark, LavaParticle.Factory::new);
        registerFactory(VeParticleTypes.cyanSpark, LavaParticle.Factory::new);
        registerFactory(VeParticleTypes.purpleSpark, LavaParticle.Factory::new);
        registerFactory(VeParticleTypes.blueSpark, LavaParticle.Factory::new);
        registerFactory(VeParticleTypes.brownSpark, LavaParticle.Factory::new);
        registerFactory(VeParticleTypes.greenSpark, LavaParticle.Factory::new);
        registerFactory(VeParticleTypes.redSpark, LavaParticle.Factory::new);
        registerFactory(VeParticleTypes.blackSpark, LavaParticle.Factory::new);
        registerFactory(VeParticleTypes.whiteFlame, FlameParticle.Factory::new);
        registerFactory(VeParticleTypes.orangeFlame, FlameParticle.Factory::new);
        registerFactory(VeParticleTypes.magentaFlame, FlameParticle.Factory::new);
        registerFactory(VeParticleTypes.lightBlueFlame, FlameParticle.Factory::new);
        registerFactory(VeParticleTypes.yellowFlame, FlameParticle.Factory::new);
        registerFactory(VeParticleTypes.limeFlame, FlameParticle.Factory::new);
        registerFactory(VeParticleTypes.pinkFlame, FlameParticle.Factory::new);
        registerFactory(VeParticleTypes.grayFlame, FlameParticle.Factory::new);
        registerFactory(VeParticleTypes.lightGrayFlame, FlameParticle.Factory::new);
        registerFactory(VeParticleTypes.cyanFlame, FlameParticle.Factory::new);
        registerFactory(VeParticleTypes.purpleFlame, FlameParticle.Factory::new);
        registerFactory(VeParticleTypes.blueFlame, FlameParticle.Factory::new);
        registerFactory(VeParticleTypes.brownFlame, FlameParticle.Factory::new);
        registerFactory(VeParticleTypes.greenFlame, FlameParticle.Factory::new);
        registerFactory(VeParticleTypes.redFlame, FlameParticle.Factory::new);
        registerFactory(VeParticleTypes.blackFlame, FlameParticle.Factory::new);
        registerFactory(VeParticleTypes.totemOfTheGuardian, VeTotemParticle.VeTotemOfTheGuardianFactory::new);
        registerFactory(VeParticleTypes.totemOfTheBrute, VeTotemParticle.VeTotemOfTheBruteFactory::new);
        registerFactory(VeParticleTypes.totemOfTheFortunate, VeTotemParticle.VeTotemOfTheFortunateFactory::new);
    }

    /**
     * A helper method for registering particle factories.
     *
     * @param particleIn        The particle.
     * @param particleFactoryIn The factory.
     */
    @SuppressWarnings("resource")
    private static void registerFactory(ParticleType<BasicParticleType> particleIn,
            IParticleMetaFactory<BasicParticleType> factoryIn)
    {
        Minecraft.getInstance().particleEngine.register(particleIn, factoryIn);
    }
}
