package rcarmstrong20.vanilla_expansions;

import java.util.HashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.FlameParticle;
import net.minecraft.client.particle.LavaParticle;
import net.minecraft.client.particle.ParticleManager.IParticleMetaFactory;
import net.minecraft.item.Item;
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
import rcarmstrong20.vanilla_expansions.client.renderer.particle.VEDripParticle;
import rcarmstrong20.vanilla_expansions.client.renderer.particle.VETotemParticle;
import rcarmstrong20.vanilla_expansions.client.renderer.particle.VEUnderDarkMatterParticle;
import rcarmstrong20.vanilla_expansions.config.VEConfig;
import rcarmstrong20.vanilla_expansions.core.VEBlocks;
import rcarmstrong20.vanilla_expansions.core.VEContainerTypes;
import rcarmstrong20.vanilla_expansions.core.VEFeature;
import rcarmstrong20.vanilla_expansions.core.VEFluids;
import rcarmstrong20.vanilla_expansions.core.VEItems;
import rcarmstrong20.vanilla_expansions.core.VEPaintingTypes;
import rcarmstrong20.vanilla_expansions.core.VEParticleTypes;
import rcarmstrong20.vanilla_expansions.core.VEPointOfInterestTypes;
import rcarmstrong20.vanilla_expansions.core.VERecipeSerializers;
import rcarmstrong20.vanilla_expansions.core.VESoundEvents;
import rcarmstrong20.vanilla_expansions.core.VEStructures;
import rcarmstrong20.vanilla_expansions.core.VETileEntityTypes;
import rcarmstrong20.vanilla_expansions.core.VEVillagerProfessions;
import rcarmstrong20.vanilla_expansions.events.VEBiomeLoadingEvent;
import rcarmstrong20.vanilla_expansions.events.VEBlockEvent;
import rcarmstrong20.vanilla_expansions.events.VEBonemealEvent;
import rcarmstrong20.vanilla_expansions.events.VELivingEvent;
import rcarmstrong20.vanilla_expansions.events.VELoadEvent;
import rcarmstrong20.vanilla_expansions.events.VEPlayerInteractEvent;
import rcarmstrong20.vanilla_expansions.events.VEViewRenderEvent;
import rcarmstrong20.vanilla_expansions.events.VEVillagerTradesEvent;
import rcarmstrong20.vanilla_expansions.item.VEItemGroup;
import rcarmstrong20.vanilla_expansions.proxy.VEClientProxy;
import rcarmstrong20.vanilla_expansions.proxy.VECommonProxy;
import rcarmstrong20.vanilla_expansions.util.VEHashMapBuilder;

/**
 * The main mod class.
 *
 * @author Ryan
 */
@Mod("vanillaexpansions")
public class VanillaExpansions
{
    public static Object modInstance;
    public static final Logger LOGGER = LogManager.getLogger(VanillaExpansions.MOD_ID);
    public static final String MOD_ID = "vanillaexpansions";
    public static final VEItemGroup VE_GROUP = new VEItemGroup(VanillaExpansions.MOD_ID);
    public static final VECommonProxy PROXY = DistExecutor.safeRunForDist(() -> VEClientProxy::new,
            () -> VECommonProxy::new);

    // The totem maps have to be initialized here or the items will be null.
    public static final HashMap<Item, Integer> BRUTE_TOTEM_MAP = (new VEHashMapBuilder<Item, Integer>())
            .put(VEItems.coalTotemOfTheBrute, 150).put(VEItems.ironTotemOfTheBrute, 150)
            .put(VEItems.goldTotemOfTheBrute, 150).put(VEItems.diamondTotemOfTheBrute, 150).build();
    public static final HashMap<Item, Integer> GUARDIAN_TOTEM_MAP = (new VEHashMapBuilder<Item, Integer>())
            .put(VEItems.coalTotemOfTheGuardian, 150).put(VEItems.ironTotemOfTheGuardian, 270)
            .put(VEItems.goldTotemOfTheGuardian, 390).put(VEItems.diamondTotemOfTheGuardian, 510).build();
    public static final HashMap<Item, Integer> FORTUNATE_TOTEM_MAP = (new VEHashMapBuilder<Item, Integer>())
            .put(VEItems.coalTotemOfTheFortunate, 150).put(VEItems.ironTotemOfTheFortunate, 150)
            .put(VEItems.goldTotemOfTheFortunate, 150).put(VEItems.diamondTotemOfTheFortunate, 150).build();
    public static final HashMap<Item, Integer> MINER_TOTEM_MAP = (new VEHashMapBuilder<Item, Integer>())
            .put(VEItems.coalTotemOfTheMiner, 90).put(VEItems.ironTotemOfTheMiner, 90)
            .put(VEItems.goldTotemOfTheMiner, 90).put(VEItems.diamondTotemOfTheMiner, 90).build();
    public static final HashMap<Item, Integer> PHANTOM_TOTEM_MAP = (new VEHashMapBuilder<Item, Integer>())
            .put(VEItems.coalTotemOfThePhantom, 150).put(VEItems.ironTotemOfThePhantom, 150)
            .put(VEItems.goldTotemOfThePhantom, 150).put(VEItems.diamondTotemOfThePhantom, 150).build();

    /**
     * This is where everything is registered to the game.
     */
    public VanillaExpansions()
    {
        modInstance = this;

        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        VEBlocks.BLOCKS.register(bus);
        VEBlocks.ITEMS.register(bus);
        VEItems.ITEMS.register(bus);
        VEContainerTypes.CONTAINERS.register(bus);
        VEFluids.FLUIDS.register(bus);
        VEPaintingTypes.PAINTING_TYPES.register(bus);
        VEParticleTypes.PARTICLES.register(bus);
        VEPointOfInterestTypes.POI_TYPES.register(bus);
        VERecipeSerializers.RECIPE_SERIALIZERS.register(bus);
        VESoundEvents.SOUNDS.register(bus);
        VETileEntityTypes.TILE_ENTITY_TYPES.register(bus);
        VEVillagerProfessions.VILLAGER_PROFESSIONS.register(bus);
        VEFeature.FEATURES.register(bus);
        VEStructures.STRUCTURES.register(bus);

        bus.addListener(this::setup);
        bus.addListener(this::clientRegistries);
        bus.addListener(this::onRegisterParticle);
        ModLoadingContext.get().registerConfig(Type.SERVER, VEConfig.SERVER_CONFIG);
        ModLoadingContext.get().registerConfig(Type.CLIENT, VEConfig.CLIENT_CONFIG);

        VEConfig.loadConfig(VEConfig.SERVER_CONFIG,
                FMLPaths.CONFIGDIR.get().resolve(VanillaExpansions.MOD_ID + "-server.toml").toString());
        VEConfig.loadConfig(VEConfig.CLIENT_CONFIG,
                FMLPaths.CONFIGDIR.get().resolve(VanillaExpansions.MOD_ID + "-client.toml").toString());

        MinecraftForge.EVENT_BUS.register(new VEBiomeLoadingEvent());
        MinecraftForge.EVENT_BUS.register(new VEBlockEvent());
        MinecraftForge.EVENT_BUS.register(new VEBonemealEvent());
        MinecraftForge.EVENT_BUS.register(new VELivingEvent());
        MinecraftForge.EVENT_BUS.register(new VELoadEvent());
        MinecraftForge.EVENT_BUS.register(new VEPlayerInteractEvent());
        MinecraftForge.EVENT_BUS.register(new VEViewRenderEvent());
        MinecraftForge.EVENT_BUS.register(new VEVillagerTradesEvent());
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
        registerFactory(VEParticleTypes.drippingDarkMatter, VEDripParticle.VeDrippingDarkMatterFactory::new);
        registerFactory(VEParticleTypes.fallingDarkMatter, VEDripParticle.VeFallingDarkMatterFactory::new);
        registerFactory(VEParticleTypes.landingDarkMatter, VEDripParticle.VeLandingDarkMatterFactory::new);
        registerFactory(VEParticleTypes.underDarkMatter, VEUnderDarkMatterParticle.Factory::new);
        registerFactory(VEParticleTypes.whiteSpark, LavaParticle.Factory::new);
        registerFactory(VEParticleTypes.orangeSpark, LavaParticle.Factory::new);
        registerFactory(VEParticleTypes.magentaSpark, LavaParticle.Factory::new);
        registerFactory(VEParticleTypes.lightBlueSpark, LavaParticle.Factory::new);
        registerFactory(VEParticleTypes.yellowSpark, LavaParticle.Factory::new);
        registerFactory(VEParticleTypes.limeSpark, LavaParticle.Factory::new);
        registerFactory(VEParticleTypes.pinkSpark, LavaParticle.Factory::new);
        registerFactory(VEParticleTypes.graySpark, LavaParticle.Factory::new);
        registerFactory(VEParticleTypes.lightGraySpark, LavaParticle.Factory::new);
        registerFactory(VEParticleTypes.cyanSpark, LavaParticle.Factory::new);
        registerFactory(VEParticleTypes.purpleSpark, LavaParticle.Factory::new);
        registerFactory(VEParticleTypes.blueSpark, LavaParticle.Factory::new);
        registerFactory(VEParticleTypes.brownSpark, LavaParticle.Factory::new);
        registerFactory(VEParticleTypes.greenSpark, LavaParticle.Factory::new);
        registerFactory(VEParticleTypes.redSpark, LavaParticle.Factory::new);
        registerFactory(VEParticleTypes.blackSpark, LavaParticle.Factory::new);
        registerFactory(VEParticleTypes.whiteFlame, FlameParticle.Factory::new);
        registerFactory(VEParticleTypes.orangeFlame, FlameParticle.Factory::new);
        registerFactory(VEParticleTypes.magentaFlame, FlameParticle.Factory::new);
        registerFactory(VEParticleTypes.lightBlueFlame, FlameParticle.Factory::new);
        registerFactory(VEParticleTypes.yellowFlame, FlameParticle.Factory::new);
        registerFactory(VEParticleTypes.limeFlame, FlameParticle.Factory::new);
        registerFactory(VEParticleTypes.pinkFlame, FlameParticle.Factory::new);
        registerFactory(VEParticleTypes.grayFlame, FlameParticle.Factory::new);
        registerFactory(VEParticleTypes.lightGrayFlame, FlameParticle.Factory::new);
        registerFactory(VEParticleTypes.cyanFlame, FlameParticle.Factory::new);
        registerFactory(VEParticleTypes.purpleFlame, FlameParticle.Factory::new);
        registerFactory(VEParticleTypes.blueFlame, FlameParticle.Factory::new);
        registerFactory(VEParticleTypes.brownFlame, FlameParticle.Factory::new);
        registerFactory(VEParticleTypes.greenFlame, FlameParticle.Factory::new);
        registerFactory(VEParticleTypes.redFlame, FlameParticle.Factory::new);
        registerFactory(VEParticleTypes.blackFlame, FlameParticle.Factory::new);
        registerFactory(VEParticleTypes.totemOfTheGuardian, VETotemParticle.VeTotemOfTheGuardianFactory::new);
        registerFactory(VEParticleTypes.totemOfTheBrute, VETotemParticle.VeTotemOfTheBruteFactory::new);
        registerFactory(VEParticleTypes.totemOfTheFortunate, VETotemParticle.VeTotemOfTheFortunateFactory::new);
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
