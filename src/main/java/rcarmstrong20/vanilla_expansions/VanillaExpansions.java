package rcarmstrong20.vanilla_expansions;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableMap.Builder;

import net.minecraft.block.BeetrootBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.CocoaBlock;
import net.minecraft.block.CropsBlock;
import net.minecraft.block.NetherWartBlock;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.FlameParticle;
import net.minecraft.client.particle.LavaParticle;
import net.minecraft.client.particle.ParticleManager.IParticleMetaFactory;
import net.minecraft.client.renderer.ActiveRenderInfo;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.merchant.villager.VillagerProfession;
import net.minecraft.entity.merchant.villager.VillagerTrades.ITrade;
import net.minecraft.entity.passive.RabbitEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.villager.VillagerType;
import net.minecraft.fluid.FluidState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particles.BasicParticleType;
import net.minecraft.particles.ParticleType;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.state.IntegerProperty;
import net.minecraft.util.Hand;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.Category;
import net.minecraft.world.biome.Biome.RainType;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.biome.MobSpawnInfo.Spawners;
import net.minecraft.world.gen.FlatChunkGenerator;
import net.minecraft.world.gen.GenerationStage.Decoration;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.StructureFeature;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.settings.StructureSeparationSettings;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.EntityViewRenderEvent;
import net.minecraftforge.client.event.ParticleFactoryRegisterEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent.PlayerTickEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingJumpEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.event.entity.player.BonemealEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.RightClickBlock;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.eventbus.api.Event.Result;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.minecraftforge.fml.config.ModConfig.Type;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLPaths;
import rcarmstrong20.vanilla_expansions.client.renderer.particle.VeDripParticle;
import rcarmstrong20.vanilla_expansions.client.renderer.particle.VeTotemOfTheBruteParticle;
import rcarmstrong20.vanilla_expansions.client.renderer.particle.VeTotemOfTheGuardianParticle;
import rcarmstrong20.vanilla_expansions.client.renderer.particle.VeUnderDarkMatterParticle;
import rcarmstrong20.vanilla_expansions.config.VeConfig;
import rcarmstrong20.vanilla_expansions.config.VeCropConfig;
import rcarmstrong20.vanilla_expansions.config.VeEntityConfig;
import rcarmstrong20.vanilla_expansions.config.VeEntityDataConfig;
import rcarmstrong20.vanilla_expansions.config.VeFeatureGenConfig;
import rcarmstrong20.vanilla_expansions.core.VeBlockTags;
import rcarmstrong20.vanilla_expansions.core.VeBlocks;
import rcarmstrong20.vanilla_expansions.core.VeConfiguredFeatures;
import rcarmstrong20.vanilla_expansions.core.VeConfiguredStructures;
import rcarmstrong20.vanilla_expansions.core.VeContainerTypes;
import rcarmstrong20.vanilla_expansions.core.VeFluidTags;
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
import rcarmstrong20.vanilla_expansions.entity.villager.VeVillagerTrades;
import rcarmstrong20.vanilla_expansions.entity.villager.VeVillagerType;
import rcarmstrong20.vanilla_expansions.fluid.VeDarkMatterFluid;
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
    public static int lastMinuteGathered = LocalDateTime.now().getMinute();
    public static boolean onCooldown = false;

    /**
     * This field is a mapping that represents which biome each villager type can
     * spawn in.
     */
    public static final Field BY_BIOME_FIELD = ObfuscationReflectionHelper.findField(VillagerType.class,
            "field_221180_h");

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
        VeRecipeSerializers.RECIPES.register(bus);
        VeSoundEvents.SOUNDS.register(bus);
        VeTileEntityType.TILE_ENTITY_TYPES.register(bus);
        VeVillagerProfessions.VILLAGER_PROFESSIONS.register(bus);

        bus.addListener(this::setup);
        bus.addListener(this::clientRegistries);
        bus.addListener(this::onRegisterParticle);
        ModLoadingContext.get().registerConfig(Type.SERVER, VeConfig.SERVER_CONFIG);
        ModLoadingContext.get().registerConfig(Type.CLIENT, VeConfig.CLIENT_CONFIG);

        VeConfig.loadConfig(VeConfig.SERVER_CONFIG, FMLPaths.CONFIGDIR.get().resolve("ve-server.toml").toString());
        VeConfig.loadConfig(VeConfig.CLIENT_CONFIG, FMLPaths.CONFIGDIR.get().resolve("ve-client.toml").toString());

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

    @SubscribeEvent
    public void onBlockBroken(BlockEvent.BreakEvent event)
    {
        Block block = event.getState().getBlock();
        PlayerEntity player = event.getPlayer();
        ItemStack stack = player.getHeldItem(player.getActiveHand());

        if (block == Blocks.SPRUCE_LEAVES && stack.getItem() != Items.SHEARS)
        {
            Random random = new Random();

            // 5% chance to drop by default
            if (random.nextFloat() <= (5 / 100))
            {
                Block.spawnAsEntity((World) event.getWorld(), event.getPos(), new ItemStack(VeItems.spruceCone, 1));
            }
        }
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
        registerFactory(VeParticleTypes.drippingDarkMatter, VeDripParticle.VeDrippingVoidFactory::new);
        registerFactory(VeParticleTypes.fallingDarkMatter, VeDripParticle.VeFallingVoidFactory::new);
        registerFactory(VeParticleTypes.landingDarkMatter, VeDripParticle.VeLandingVoidFactory::new);
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
        registerFactory(VeParticleTypes.totemOfTheGuardian, VeTotemOfTheGuardianParticle.Factory::new);
        registerFactory(VeParticleTypes.totemOfTheBrute, VeTotemOfTheBruteParticle.Factory::new);
    }

    /**
     * A helper method for registering particle factories that is a lot cleaner than
     * the vanilla path.
     *
     * @param particleIn        The particle.
     * @param particleFactoryIn The factory.
     */
    @SuppressWarnings("resource")
    private static void registerFactory(ParticleType<BasicParticleType> particleIn,
            IParticleMetaFactory<BasicParticleType> particleFactoryIn)
    {
        Minecraft.getInstance().particles.registerFactory(particleIn, particleFactoryIn);
    }
    /*
     * @SubscribeEvent public void onPlayerTick(PlayerTickEvent event) {
     *
     *
     * // player.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(-0.001);
     *
     * // if (player.areEyesInFluid(VeFluidTags.darkMatter)) { //
     * player.setMotion(player.getMotion().add(0.0D, 0.3D, 0.0D));
     *
     * /* double d3 = player.getLookVec().y; double d4 = d3 < -0.2D ? 0.085D :
     * 0.06D;
     *
     * Vector3d vector3d1 = player.getMotion(); player.setMotion(vector3d1.add(0.0D,
     * (d3 - vector3d1.y) * d4, 0.0D));
     */
    /*
     * double d3 = player.getLookVec().y; double d4 = d3 < -0.2D ? 0.085D : 0.06D;
     * if (d3 <= 0.0D || player.getMotion().getY() > 0 || !player.world
     * .getBlockState(new BlockPos(player.getPosX(), player.getPosY() + 1.0D - 0.1D,
     * player.getPosZ())) .getFluidState().isEmpty()) {
     *
     * }
     */
    // VanillaExpansions.LOGGER.info("Swimming");
    // }

    // VanillaExpansions.LOGGER.info("Swimming");
    // }

    @SuppressWarnings("unchecked") // Needed for the byBiome field.
    @SubscribeEvent
    @OnlyIn(Dist.CLIENT)
    public void addTrades(VillagerTradesEvent event)
    {
        addTrade(event, VeVillagerProfessions.lumberjack, VeVillagerTrades.lumberjackTrades);

        Map<RegistryKey<Biome>, VillagerType> byBiome;
        try
        {
            BY_BIOME_FIELD.setAccessible(true);

            byBiome = (Map<RegistryKey<Biome>, VillagerType>) BY_BIOME_FIELD.get(VillagerType.class);

            byBiome.put(Biomes.CRIMSON_FOREST, VeVillagerType.crimson);
            byBiome.put(Biomes.WARPED_FOREST, VeVillagerType.warped);

            BY_BIOME_FIELD.setAccessible(false);
        }
        catch (IllegalArgumentException | IllegalAccessException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Adds a trade to the game for a specific profession.
     *
     * @param event
     * @param profession The profession to populate with trades.
     * @param trades     The trade list.
     */
    private static void addTrade(VillagerTradesEvent event, VillagerProfession profession,
            ImmutableMap<Integer, List<ITrade>> trades)
    {
        for (int i = 1; i <= trades.size(); i++)
        {
            if (event.getType().equals(profession))
            {
                event.getTrades().put(i, trades.get(i));
            }
        }
    }

    @SubscribeEvent
    public void onEntityJump(LivingJumpEvent event)
    {
        LivingEntity livingEntity = event.getEntityLiving();
        World world = livingEntity.getEntityWorld();
        FluidState fluidState = world
                .getFluidState(new BlockPos(livingEntity.getPosX(), livingEntity.getPosY(), livingEntity.getPosZ()));
        Vector3d motion = livingEntity.getMotion();

        if (fluidState.isTagged(VeFluidTags.darkMatter))
        {
            // livingEntity.addVelocity(0, 2.0, 0);
            // livingEntity.setAIMoveSpeed(0.005F);
            // VanillaExpansions.LOGGER.info("Entity Jumped");
        }
    }

    @SubscribeEvent
    public void onLivingEntityFall(LivingFallEvent event)
    {
        LivingEntity livingEntity = event.getEntityLiving();
        /*
         * World world = livingEntity.getEntityWorld(); FluidState fluidState = world
         * .getFluidState(new BlockPos(livingEntity.getPosX(), livingEntity.getPosY(),
         * livingEntity.getPosZ())); Vector3d motion = livingEntity.getMotion();
         *
         * if (fluidState.isTagged(VeFluidTags.darkMatter)) {
         * livingEntity.setMotion(motion.getX(), motion.getY() + 2, motion.getZ());
         * VanillaExpansions.LOGGER.info("Entity is falling"); }
         */

        // Cancels rabbit fall damage.
        if (VeEntityConfig.VeOverworldConfig.enableSaveTheBunnies.get() && livingEntity instanceof RabbitEntity)
        {
            event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public void onPlayerTick(PlayerTickEvent event)
    {
        Map<Item, Integer> totemGuardianMap = (new Builder<Item, Integer>()).put(VeItems.totemOfTheGuardianI, 600)
                .put(VeItems.totemOfTheGuardianII, 1200).put(VeItems.totemOfTheGuardianIII, 2400)
                .put(VeItems.totemOfTheGuardianIV, 4800).build();

        Map<Item, Integer> totemBruteMap = (new Builder<Item, Integer>()).put(VeItems.totemOfTheBruteI, 0)
                .put(VeItems.totemOfTheBruteII, 1).put(VeItems.totemOfTheBruteIII, 2).put(VeItems.totemOfTheBruteIV, 3)
                .build();

        if (event.player instanceof ServerPlayerEntity)
        {
            ServerPlayerEntity serverPlayer = (ServerPlayerEntity) event.player;

            if (activateTotemOfTheBrute(totemBruteMap, Hand.MAIN_HAND, serverPlayer))
            {
                removeFromMainHand(serverPlayer);
            }
            else if (activateTotemOfTheBrute(totemBruteMap, Hand.OFF_HAND, serverPlayer))
            {
                removeFromOffHand(serverPlayer);
            }
            else if (activateTotemOfTheGuardian(totemGuardianMap, Hand.MAIN_HAND, serverPlayer))
            {
                removeFromMainHand(serverPlayer);
            }
            else if (activateTotemOfTheGuardian(totemGuardianMap, Hand.OFF_HAND, serverPlayer))
            {
                removeFromOffHand(serverPlayer);
            }
        }

        LivingEntity player = event.player;

        // Push the player when in flowing dark matter.
        player.handleFluidAcceleration(VeFluidTags.darkMatter, 0.005);

        World world = player.getEntityWorld();
        FluidState fluidState = world.getFluidState(new BlockPos(player.getPosX(), player.getPosY(), player.getPosZ()));
        Vector3d motion = player.getMotion();
        Vector3d motion2 = player.getMotion();
        // double height1 = player.getPosY();

        // VanillaExpansions.LOGGER.info(motion2.getY() + motion.getY());

        // VanillaExpansions.LOGGER.info(height1 + " and " + prevY);

        // When the player is standing the motion of second - first =
        // -0.1568000030517578.
        /*
         * if (fluidState.isTagged(VeFluidTags.darkMatter) && height1 - prevY < 0) { //
         * player.addVelocity(0, motion.getY() * 0.005, 0); //prevY = height1;
         * //VanillaExpansions.LOGGER.info("Falling height is " + (prevY - height1)); }
         */
    }

    private void removeFromMainHand(ServerPlayerEntity player)
    {
        removeFromStack(player, player.getHeldItem(Hand.MAIN_HAND), Hand.MAIN_HAND);
    }

    private void removeFromOffHand(ServerPlayerEntity player)
    {
        removeFromStack(player, player.getHeldItem(Hand.OFF_HAND), Hand.OFF_HAND);
    }

    private void removeFromStack(ServerPlayerEntity player, ItemStack heldStack, Hand hand)
    {
        player.setHeldItem(hand, new ItemStack(heldStack.getItem(), (heldStack.getCount() - 1)));
    }

    /**
     * Trigger the brute totem's power.
     *
     * @param itemToPowerLvl A map that associates the item with an amplifier level.
     * @param hand           The player's hand which is holding the item.
     * @param player         The player using this item.
     * @return true if this method successfully enacts an action.
     */
    private boolean activateTotemOfTheBrute(Map<Item, Integer> itemToPowerLvl, Hand hand, ServerPlayerEntity player)
    {
        Random rand = new Random();
        ItemStack heldStack = player.getHeldItem(hand);
        float halfHealth = player.getMaxHealth() / 2;
        int currentMinute = LocalDateTime.now().getMinute();

        if (!onCooldown && player.getHealth() <= halfHealth && itemToPowerLvl.containsKey(heldStack.getItem()))
        {
            setOnCooldown(true);

            player.addPotionEffect(new EffectInstance(Effects.STRENGTH, 600, itemToPowerLvl.get(heldStack.getItem())));
            player.addPotionEffect(new EffectInstance(Effects.RESISTANCE, 600, 1));
            spawnParticles(VeParticleTypes.totemOfTheBrute, player, rand);
            VanillaExpansions.LOGGER.info("Used brute totem");
            return true;
        }
        else if (onCooldown && (currentMinute - lastMinuteGathered) >= 4)
        {
            setOnCooldown(false);
            player.sendStatusMessage(ITextComponent
                    .getTextComponentOrEmpty("\u00A72" + "<Totem Of The Brute> The totem's cooldown is over."), false);
            lastMinuteGathered = currentMinute;
            return false;
        }
        else
        {
            return false;
        }
    }

    public static void setOnCooldown(boolean onCooldown)
    {
        VanillaExpansions.onCooldown = onCooldown;
    }

    /**
     * Trigger the guardian totem's power.
     *
     * @param itemToPowerLvl A map that associates the item with a time duration.
     * @param hand           The player's hand which is holding the item.
     * @param player         The player using this item.
     * @return true if this method successfully enacts an action.
     */
    private boolean activateTotemOfTheGuardian(Map<Item, Integer> itemToPowerLvl, Hand hand, ServerPlayerEntity player)
    {
        Random rand = new Random();
        ItemStack heldStack = player.getHeldItem(hand);
        int maxAir = player.getMaxAir();

        if (player.getAir() == 0 && itemToPowerLvl.containsKey(heldStack.getItem()))
        {
            player.addPotionEffect(
                    new EffectInstance(Effects.WATER_BREATHING, itemToPowerLvl.get(heldStack.getItem())));
            player.setAir(maxAir);

            spawnParticles(VeParticleTypes.totemOfTheGuardian, player, rand);
            return true;
        }
        else
        {
            return false;
        }
    }

    private void spawnParticles(BasicParticleType particle, ServerPlayerEntity serverPlayer, Random rand)
    {
        int max = rand.nextInt(15) + 15;

        serverPlayer.playSound(SoundEvents.ITEM_TOTEM_USE, 20000, 10000);

        for (int i = 0; i <= max; i++)
        {
            int count = rand.nextInt(5) + 5;
            double x = serverPlayer.getPosXRandom(2.0);
            double y = serverPlayer.getPosYRandom();
            double z = serverPlayer.getPosZRandom(2.0);

            serverPlayer.getServerWorld().spawnParticle(serverPlayer, particle, true, x, y, z, count, 0.0, 1.0, 0.0,
                    0.0);
        }
    }

    /**
     * Makes the fog black when the player is in a dark matter block.
     *
     * @param event An instance of the FogColors class.
     */
    @SubscribeEvent
    @OnlyIn(Dist.CLIENT)
    public void onFogColor(EntityViewRenderEvent.FogColors event)
    {
        ActiveRenderInfo info = event.getInfo();
        FluidState state = info.getFluidState();

        float black = 0.0F;

        if (state.getFluid() instanceof VeDarkMatterFluid)
        {
            event.setRed(black);
            event.setGreen(black);
            event.setBlue(black);
        }
    }

    /**
     * Controls the density of the fog color when in a dark matter block.
     *
     * @param event An instance of the FogDensity class.
     */
    @SubscribeEvent
    @OnlyIn(Dist.CLIENT)
    public void onFogDensity(EntityViewRenderEvent.FogDensity event)
    {
        ActiveRenderInfo info = event.getInfo();
        FluidState state = info.getFluidState();
        if (state.getFluid() instanceof VeDarkMatterFluid)
        {
            event.setDensity(0.5F);
            event.setCanceled(true);
        }
    }

    /**
     * Controls right-click crop harvesting and campfire re-coloring behavior.
     *
     * @param event Called when the player right-clicks a block.
     */
    @SubscribeEvent
    public void onRightClickBlock(final RightClickBlock event)
    {
        BlockPos pos = event.getPos();
        World world = event.getWorld();
        BlockState worldState = event.getWorld().getBlockState(pos);
        ItemStack itemStack = event.getItemStack();
        IntegerProperty cropsAge = CropsBlock.AGE;
        IntegerProperty netherWartAge = NetherWartBlock.AGE;
        IntegerProperty beetrootAge = BeetrootBlock.BEETROOT_AGE;
        IntegerProperty cocoaAge = CocoaBlock.AGE;
        boolean flag = VeCropConfig.VeHarvestConfig.enableSmartHarvest.get();

        if (!event.getWorld().isRemote())
        {
            if (flag)
            {
                if (worldState.getBlock() instanceof CropsBlock && itemStack.getItem() != Items.BONE_MEAL)
                {
                    if (worldState.getBlock() instanceof BeetrootBlock)
                    {
                        if (worldState.get(beetrootAge).equals(getMaxAge(beetrootAge)))
                        {
                            resetCrop(worldState, world, pos, beetrootAge);
                            event.setResult(Result.ALLOW);
                            event.setCanceled(true);
                        }
                    }
                    else if (worldState.get(cropsAge).equals(getMaxAge(cropsAge)))
                    {
                        resetCrop(worldState, world, pos, cropsAge);
                        event.setResult(Result.ALLOW);
                        event.setCanceled(true);
                    }
                }
                else if (worldState.getBlock() instanceof NetherWartBlock)
                {
                    if (worldState.get(netherWartAge).equals(getMaxAge(netherWartAge)))
                    {
                        resetCrop(worldState, world, pos, netherWartAge);
                        event.setResult(Result.ALLOW);
                        event.setCanceled(true);
                    }
                }
                else if (worldState.getBlock() instanceof CocoaBlock)
                {
                    if (worldState.get(cocoaAge) == getMaxAge(cocoaAge))
                    {
                        resetCrop(worldState, world, pos, cocoaAge);
                        event.setResult(Result.ALLOW);
                        event.setCanceled(true);
                    }
                }
            }
            else
            {
                event.setResult(Result.DEFAULT);
            }
        }
    }

    /**
     * A helper method that harvests the passed in crop.
     *
     * @param state The state of the crop to harvest.
     * @param world The current world.
     * @param pos   The position for the crop to harvest.
     * @param age   The age property for this crop.
     */
    private static void resetCrop(BlockState state, World world, BlockPos pos, IntegerProperty age)
    {
        Block.replaceBlock(state, Blocks.AIR.getDefaultState(), world, pos, 1); // Note: If the replacement block is
                                                                                // anything but air the blocks don't
                                                                                // drop and play their appropriate
                                                                                // sound.
        world.setBlockState(pos, state.with(age, 0));
    }

    /**
     * @param age The age property to use.
     * @return The max age for the age property passed.
     */
    private int getMaxAge(IntegerProperty age)
    {
        return age.getAllowedValues().size() - 1;
    }

    @SubscribeEvent
    public void onLoad(final WorldEvent.Load event)
    {
        if (event.getWorld() instanceof ServerWorld)
        {
            ServerWorld serverWorld = (ServerWorld) event.getWorld();

            // Don't add structures to superflat worlds.
            if (serverWorld.getChunkProvider().getChunkGenerator() instanceof FlatChunkGenerator
                    && serverWorld.getDimensionKey().equals(World.OVERWORLD))
            {
                return;
            }

            addSpacing(serverWorld, VeStructure.overworldCabin, 15, 10, 724628428);
            addSpacing(serverWorld, VeStructure.netherCabin, 10, 5, 487422842);
        }
    }

    /**
     * @param serverWorld        Server side world
     * @param structure          The structure to add.
     * @param maxChunkSeperation The minimum chunk distance between spawning
     *                           attempts.
     * @param minChunkSeperation The maximum chunk distance between spawning
     *                           attempts.
     * @param structureSeed      The seed that is used to make sure that the
     *                           structure is not spawned at the same position as
     *                           another of this type.
     */
    private static void addSpacing(ServerWorld serverWorld, Structure<?> structure, int maxChunkSeperation,
            int minChunkSeperation, int structureSeed)
    {
        serverWorld.getChunkProvider().getChunkGenerator().func_235957_b_().func_236195_a_().put(structure,
                new StructureSeparationSettings(maxChunkSeperation, minChunkSeperation, structureSeed));
    }

    @SubscribeEvent(priority = EventPriority.HIGH)
    public void onLoadBiome(final BiomeLoadingEvent event)
    {
        List<String> endCityBiomes = Arrays.asList("end_barrens", "end_highlands", "end_midlands", "small_end_islands");
        List<String> darkForestBiomes = Arrays.asList("dark_forest", "dark_forest_hills");
        List<String> forestCabinBiomes = Arrays.asList("forest", "birch_forest", "birch_forest_hills",
                "tall_birch_forest", "tall_birch_hills");

        boolean netherSmokyQuartzFlag = VeFeatureGenConfig.VeNetherConfig.enableNetherSmokyQuartzOreSpawns.get();
        boolean netherRubyFlag = VeFeatureGenConfig.VeNetherConfig.enableNetherRubyOreSpawns.get();
        boolean blueberryBushFlag = VeFeatureGenConfig.VeOverworldConfig.enableBlueberryBushSpawns.get();
        boolean cranberryBushFlag = VeFeatureGenConfig.VeOverworldConfig.enableCranberryBushSpawns.get();
        boolean witchesCradleFlag = VeFeatureGenConfig.VeOverworldConfig.enableWitchsCradleSpawns.get();
        boolean darkMatterLakeFlag = VeFeatureGenConfig.VeEndConfig.enableDarkMatterLakeSpawns.get();
        boolean snapdragonAndEnderGrassFlag = VeFeatureGenConfig.VeEndConfig.enableSnapdragonAndEnderGrassSpawns.get();
        boolean hugePurpleMushroomFlag = VeFeatureGenConfig.VeOverworldConfig.enableHugePurpleMushroomSpawns.get();
        boolean taigaCabinFlag = VeFeatureGenConfig.VeOverworldConfig.enableTaigaCabinSpawns.get();
        boolean forestCabinFlag = VeFeatureGenConfig.VeOverworldConfig.enableForestCabinSpawns.get();
        boolean crimsonCabinFlag = VeFeatureGenConfig.VeNetherConfig.enableCrimsonCabinSpawns.get();
        boolean netherZombieVillagerFlag = VeEntityConfig.VeNetherConfig.enableZombieVillagersSpawns.get();
        boolean purpleMushroomFlag = VeFeatureGenConfig.VeOverworldConfig.enablePurpleMushroomSpawns.get();

        int netherZombieVillagerWeight = VeEntityDataConfig.SpawnWeightConfig.netherZombieVillagerSpawnWeight.get();
        int netherZombieVillagerMinSize = VeEntityDataConfig.MinSpawnSizeConfig.netherZombieVillagerMinSpawnSize.get();
        int netherZombieVillagerMaxSize = VeEntityDataConfig.MaxSpawnSizeConfig.netherZombieVillagerMaxSpawnSize.get();

        ConfiguredFeature<?, ?> sparseBlueberries = VeConfiguredFeatures.PATCH_BLUEBERRY_BUSH_SPARSE;
        ConfiguredFeature<?, ?> decoratedBlueberries = VeConfiguredFeatures.PATCH_BLUEBERRY_BUSH_DECORATED;
        ConfiguredFeature<?, ?> sparseCranberries = VeConfiguredFeatures.PATCH_CRANBERRY_BUSH_SPARSE;
        ConfiguredFeature<?, ?> decoratedCranberries = VeConfiguredFeatures.PATCH_CRANBERRY_BUSH_DECORATED;
        ConfiguredFeature<?, ?> sparseWitchsCradle = VeConfiguredFeatures.PATCH_WITCHS_CRADLE_SPARSE;
        ConfiguredFeature<?, ?> decoratedWitchsCradle = VeConfiguredFeatures.PATCH_WITCHS_CRADLE_DECORATED;

        Decoration ores = Decoration.UNDERGROUND_ORES;
        Decoration vegetal = Decoration.VEGETAL_DECORATION;
        Decoration lakes = Decoration.LAKES;

        Category nether = Category.NETHER;
        Category forest = Category.FOREST;
        Category swamp = Category.SWAMP;
        Category taiga = Category.TAIGA;

        RainType rain = RainType.RAIN;
        RainType snow = RainType.SNOW;

        addFeature(event, nether, ores, VeConfiguredFeatures.NETHER_SMOKY_QUARTZ_ORE, netherSmokyQuartzFlag);
        addFeature(event, nether, ores, VeConfiguredFeatures.BLACKSTONE_RUBY_ORE, netherRubyFlag);
        addBushFeature(event, forest, sparseBlueberries, decoratedBlueberries, blueberryBushFlag);
        addBushFeature(event, forest, sparseCranberries, decoratedCranberries, cranberryBushFlag);
        addBushFeature(event, swamp, sparseWitchsCradle, decoratedWitchsCradle, witchesCradleFlag);
        addFeature(event, endCityBiomes, vegetal, VeConfiguredFeatures.SNAPDRAGON_AND_GRASS,
                snapdragonAndEnderGrassFlag);
        addFeature(event, endCityBiomes, lakes, VeConfiguredFeatures.DARK_MATTER_LAKE, darkMatterLakeFlag);
        addFeature(event, darkForestBiomes, vegetal, VeConfiguredFeatures.HUGE_PURPLE_MUSHROOM_WG,
                hugePurpleMushroomFlag);
        addFeature(event, darkForestBiomes, vegetal, VeConfiguredFeatures.PURPLE_MUSHROOM_DARK_FOREST,
                purpleMushroomFlag);

        addStructure(event, taiga, rain, VeConfiguredStructures.configuredTaigaCabin, taigaCabinFlag);
        addStructure(event, taiga, snow, VeConfiguredStructures.configuredIcyTaigaCabin, taigaCabinFlag);
        addStructure(event, forestCabinBiomes, VeConfiguredStructures.configuredForestCabin, forestCabinFlag);
        addStructure(event, "crimson_forest", VeConfiguredStructures.configuredCrimsonCabin, crimsonCabinFlag);

        addMonsterSpawner(event, EntityType.ZOMBIE_VILLAGER, netherZombieVillagerWeight, netherZombieVillagerMinSize,
                netherZombieVillagerMaxSize, netherZombieVillagerFlag, "crimson_forest", "warped_forest");
    }

    /**
     * Adds a new spawner for monsters that allows these monsters to spawn in the
     * world.
     *
     * @param event    An instance of the biome loading event.
     * @param entity   The entity to use in the spawner.
     * @param weight   How likely the mob is to spawn. A higher weight equals a
     *                 higher spawn rate.
     * @param minCount The minimum number of spawns.
     * @param maxCount The maximum number of spawns.
     * @param biomes   The biomes that this entity can spawn in.
     */
    private static void addMonsterSpawner(BiomeLoadingEvent event, EntityType<?> entity, int weight, int minCount,
            int maxCount, boolean enable, String... biomes)
    {
        if (enable)
        {
            for (String biome : biomes)
            {
                if (event.getName().equals(new ResourceLocation(biome)))
                {
                    event.getSpawns().getSpawner(EntityClassification.MONSTER)
                            .add(new Spawners(entity, weight, minCount, maxCount));
                }
            }
        }
    }

    /**
     * A helper method for adding bush features.
     *
     * @param event            The biome loading event to use.
     * @param category         The category of biomes to add this feature to.
     * @param decorationType   The decoration category that this feature belongs to.
     * @param featureSparse    The sparse bush feature to add.
     * @param featureDecorated The decorated bush feature to add.
     * @param enable           A boolean from the config used to enable and disable
     *                         this feature.
     */
    private static void addBushFeature(BiomeLoadingEvent event, Biome.Category category,
            ConfiguredFeature<?, ?> featureSparse, ConfiguredFeature<?, ?> featureDecorated, boolean enable)
    {
        Decoration vegetalDecoration = Decoration.VEGETAL_DECORATION;

        addFeature(event, category, vegetalDecoration, featureSparse, enable);
        addFeature(event, category, vegetalDecoration, featureDecorated, enable);
    }

    /**
     * Adds a new feature to a category of biomes.
     *
     * @param event      The biome loading event to use.
     * @param category   The category of biomes to add this feature to.
     * @param decoration The decoration category that this feature belongs to.
     * @param feature    The feature to add.
     * @param enable     A boolean from the config used to enable and disable this
     *                   feature.
     */
    private static void addFeature(BiomeLoadingEvent event, Biome.Category category, Decoration decorationType,
            ConfiguredFeature<?, ?> feature, boolean enable)
    {
        if (event.getCategory() == category && enable)
        {
            event.getGeneration().getFeatures(decorationType).add(() -> feature);
        }
    }

    /**
     * A helper method that only adds the feature to one biome.
     *
     * @param event      The biome loading event to use.
     * @param biome      The biome's name to add the feature to.
     * @param decoration The decoration category that this feature belongs to.
     * @param feature    The feature to add.
     * @param enable     A boolean from the config used to enable and disable this
     *                   feature.
     */
    @SuppressWarnings("unused")
    private static void addFeature(BiomeLoadingEvent event, String biome, Decoration decoration,
            ConfiguredFeature<?, ?> feature, boolean enable)
    {
        addFeature(event, Arrays.asList(biome), decoration, feature, enable);
    }

    /**
     * Adds a new feature to specific existing biomes using the minecraft name
     * space.
     *
     * @param event      The biome loading event to use.
     * @param biomes     The biome names to add the feature to.
     * @param decoration The decoration category that this feature belongs to.
     * @param feature    The feature to add.
     * @param enable     A boolean from the config used to enable and disable this
     *                   feature.
     */
    private static void addFeature(BiomeLoadingEvent event, List<String> biomes, Decoration decoration,
            ConfiguredFeature<?, ?> feature, boolean enable)
    {
        if (enable)
        {
            for (String biome : biomes)
            {
                if (event.getName().equals(new ResourceLocation(biome)))
                {
                    event.getGeneration().getFeatures(decoration).add(() -> feature);
                }
            }
        }
    }

    /**
     * Adds a new structure to a category of biomes with the rain type provided.
     *
     * @param event            The biome loading event to use.
     * @param category         The category of biomes to add this structure to.
     * @param rainType         The rain type of the biomes to spawn in.
     * @param structureFeature The structure to add.
     * @param enable           A boolean from the config used to enable and disable
     *                         this structure.
     */
    private static void addStructure(BiomeLoadingEvent event, Category category, RainType rainType,
            StructureFeature<?, ?> structureFeature, boolean enable)
    {
        if (event.getClimate().precipitation == rainType)
        {
            addStructure(event, category, structureFeature, enable);
        }
    }

    /**
     * Adds a new structure to a category of biomes.
     *
     * @param event            The biome loading event to use.
     * @param category         The category of biomes to add this structure to.
     * @param structureFeature The structure to add.
     * @param enable           A boolean from the config used to enable and disable
     *                         this structure.
     */
    private static void addStructure(BiomeLoadingEvent event, Category category,
            StructureFeature<?, ?> structureFeature, boolean enable)
    {
        if (enable)
        {
            if (event.getCategory() == category)
            {
                event.getGeneration().getStructures().add(() -> structureFeature);
            }
        }
    }

    /**
     * Adds a new structure to specific existing biomes using the minecraft name
     * space.
     *
     * @param event            The biome loading event to use.
     * @param biomes           The biome names to add the structure to.
     * @param structureFeature The structure to add.
     * @param enable           A boolean from the config used to enable and disable
     *                         this structure.
     */
    private static void addStructure(BiomeLoadingEvent event, List<String> biomes,
            StructureFeature<?, ?> structureFeature, boolean enable)
    {
        if (enable)
        {
            for (String biome : biomes)
            {
                if (event.getName().equals(new ResourceLocation(biome)))
                {
                    event.getGeneration().getStructures().add(() -> structureFeature);
                }
            }
        }
    }

    /**
     * A helper method that only adds the feature to one biome.
     *
     * @param event            The biome loading event to use.
     * @param biome            The biome's name to add the structure to.
     * @param structureFeature The structure to add.
     * @param enable           A boolean from the config used to enable and disable
     *                         this structure.
     */
    private static void addStructure(BiomeLoadingEvent event, String biome, StructureFeature<?, ?> structureFeature,
            boolean enable)
    {
        addStructure(event, Arrays.asList(biome), structureFeature, enable);
    }

    /**
     * Called when the player attempts to use bone meal on a block.
     *
     * @param event A new instance of the BonemealEvent.
     */
    @SubscribeEvent
    public void onBonemeal(final BonemealEvent event)
    {
        World world = event.getWorld();
        BlockPos pos = event.getPos();
        Random random = new Random();

        // Used to add functionality for growing snapdragons on end stone when using
        // bone meal.
        if (VeBlockTags.endBoneMealable.contains(event.getBlock().getBlock()))
        {
            if (!world.isRemote) // Only place the snapdragon blocks server side.
            {
                for (int i = 0; i < 128; ++i)
                {
                    BlockPos blockpos = pos;

                    for (int j = 0; j < i / 16; ++j)
                    {
                        blockpos = blockpos.add(random.nextInt(3) - 1, (random.nextInt(3) - 1) * random.nextInt(3) / 2,
                                random.nextInt(3) - 1);

                        if (VeBlockTags.endBoneMealable.contains(world.getBlockState(blockpos.down()).getBlock())
                                && isAir(world.getBlockState(blockpos)))
                        {
                            world.setBlockState(blockpos,
                                    VeBlockTags.endBoneMealPlants.getRandomElement(random).getDefaultState());
                        }
                    }
                }
            }
            event.setResult(Result.ALLOW);
        }
    }

    /**
     * @param state The block state to check.
     * @return true if the block state's material is air.
     */
    public static boolean isAir(BlockState state)
    {
        return state.getMaterial() == Material.AIR;
    }

    /**
     * @param state The block state to check.
     * @return true if the block state's material matches one of the vanilla liquid
     *         materials.
     */
    public static boolean isLiquid(BlockState state)
    {
        return state.getMaterial() == Material.WATER || state.getMaterial() == Material.LAVA;
    }
}
