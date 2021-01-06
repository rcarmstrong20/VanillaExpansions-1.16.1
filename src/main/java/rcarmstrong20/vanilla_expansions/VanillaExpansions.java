package rcarmstrong20.vanilla_expansions;

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
import net.minecraft.client.particle.LavaParticle;
import net.minecraft.client.renderer.ActiveRenderInfo;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.merchant.villager.VillagerProfession;
import net.minecraft.entity.merchant.villager.VillagerTrades.ITrade;
import net.minecraft.entity.passive.RabbitEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.TableLootEntry;
import net.minecraft.particles.BasicParticleType;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.state.IntegerProperty;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.Category;
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
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.event.TickEvent.PlayerTickEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.event.entity.player.BonemealEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.RightClickBlock;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.eventbus.api.Event.Result;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
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
import rcarmstrong20.vanilla_expansions.config.VeFeatureGenConfig;
import rcarmstrong20.vanilla_expansions.core.VeBlockTags;
import rcarmstrong20.vanilla_expansions.core.VeConfiguredFeatures;
import rcarmstrong20.vanilla_expansions.core.VeConfiguredStructures;
import rcarmstrong20.vanilla_expansions.core.VeFluidTags;
import rcarmstrong20.vanilla_expansions.core.VeItems;
import rcarmstrong20.vanilla_expansions.core.VeParticleTypes;
import rcarmstrong20.vanilla_expansions.core.VeStructure;
import rcarmstrong20.vanilla_expansions.core.VeVillagerProfessions;
import rcarmstrong20.vanilla_expansions.core.VeVillagerTrades;
import rcarmstrong20.vanilla_expansions.fluid.VeDarkMatterFluid;
import rcarmstrong20.vanilla_expansions.proxy.ClientProxy;
import rcarmstrong20.vanilla_expansions.proxy.CommonProxy;

/**
 *
 * @author Ryan
 *
 *         The main mod class.
 *
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

    public VanillaExpansions()
    {
        modInstance = this;

        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::clientRegistries);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::onRegisterParticle);
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

    /**
     * This takes care of registering the particle factories if they are not
     * registered under the particle factory event there will be a bug.
     *
     * @param event Called during particle factory registry.
     */
    @SuppressWarnings("resource")
    @OnlyIn(Dist.CLIENT)
    private void onRegisterParticle(ParticleFactoryRegisterEvent event)
    {
        Minecraft.getInstance().particles.registerFactory(VeParticleTypes.drippingDarkMatter,
                VeDripParticle.VeDrippingVoidFactory::new);
        Minecraft.getInstance().particles.registerFactory(VeParticleTypes.fallingDarkMatter,
                VeDripParticle.VeFallingVoidFactory::new);
        Minecraft.getInstance().particles.registerFactory(VeParticleTypes.landingDarkMatter,
                VeDripParticle.VeLandingVoidFactory::new);
        Minecraft.getInstance().particles.registerFactory(VeParticleTypes.underDarkMatter,
                VeUnderDarkMatterParticle.Factory::new);
        Minecraft.getInstance().particles.registerFactory(VeParticleTypes.whiteSpark, LavaParticle.Factory::new);
        Minecraft.getInstance().particles.registerFactory(VeParticleTypes.orangeSpark, LavaParticle.Factory::new);
        Minecraft.getInstance().particles.registerFactory(VeParticleTypes.magentaSpark, LavaParticle.Factory::new);
        Minecraft.getInstance().particles.registerFactory(VeParticleTypes.lightBlueSpark, LavaParticle.Factory::new);
        Minecraft.getInstance().particles.registerFactory(VeParticleTypes.yellowSpark, LavaParticle.Factory::new);
        Minecraft.getInstance().particles.registerFactory(VeParticleTypes.limeSpark, LavaParticle.Factory::new);
        Minecraft.getInstance().particles.registerFactory(VeParticleTypes.pinkSpark, LavaParticle.Factory::new);
        Minecraft.getInstance().particles.registerFactory(VeParticleTypes.graySpark, LavaParticle.Factory::new);
        Minecraft.getInstance().particles.registerFactory(VeParticleTypes.lightGraySpark, LavaParticle.Factory::new);
        Minecraft.getInstance().particles.registerFactory(VeParticleTypes.cyanSpark, LavaParticle.Factory::new);
        Minecraft.getInstance().particles.registerFactory(VeParticleTypes.purpleSpark, LavaParticle.Factory::new);
        Minecraft.getInstance().particles.registerFactory(VeParticleTypes.blueSpark, LavaParticle.Factory::new);
        Minecraft.getInstance().particles.registerFactory(VeParticleTypes.brownSpark, LavaParticle.Factory::new);
        Minecraft.getInstance().particles.registerFactory(VeParticleTypes.greenSpark, LavaParticle.Factory::new);
        Minecraft.getInstance().particles.registerFactory(VeParticleTypes.redSpark, LavaParticle.Factory::new);
        Minecraft.getInstance().particles.registerFactory(VeParticleTypes.blackSpark, LavaParticle.Factory::new);
        Minecraft.getInstance().particles.registerFactory(VeParticleTypes.totemOfTheGuardian,
                VeTotemOfTheGuardianParticle.Factory::new);
        Minecraft.getInstance().particles.registerFactory(VeParticleTypes.totemOfTheBrute,
                VeTotemOfTheBruteParticle.Factory::new);
    }

    @SubscribeEvent
    @OnlyIn(Dist.CLIENT)
    public void onClientPlayer(PlayerTickEvent event)
    {
        // Push the player when in flowing dark matter.
        event.player.handleFluidAcceleration(VeFluidTags.darkMatter, 0.005);
    }

    @SubscribeEvent
    @OnlyIn(Dist.CLIENT)
    public void addTrades(VillagerTradesEvent event)
    {
        addTrade(event, VeVillagerProfessions.lumberjack, VeVillagerTrades.lumberjackTrades);
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
    public void onPlayerTick(PlayerTickEvent event)
    {
        Map<Item, Integer> totemGuardianMap = (new Builder<Item, Integer>()).put(VeItems.totemOfTheGuardianI, 600)
                .put(VeItems.totemOfTheGuardianII, 1200).put(VeItems.totemOfTheGuardianIII, 2400).build();

        Map<Item, Integer> totemBruteMap = (new Builder<Item, Integer>()).put(VeItems.totemOfTheBruteI, 0)
                .put(VeItems.totemOfTheBruteII, 1).put(VeItems.totemOfTheBruteIII, 2).build();

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

        if (!event.getWorld().isRemote())
        {
            if (VeCropConfig.enableSmartHarvest.get() && worldState.getBlock() instanceof CropsBlock
                    && itemStack.getItem() != Items.BONE_MEAL)
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

    /**
     * Called when the vanilla loot tables load.
     *
     * @param event A new instance of the LootTableLoadEvent.
     */
    @SubscribeEvent
    public void onLootLoad(final LootTableLoadEvent event)
    {
        addPoolToTable(event, "jungle_temple");
        addPoolToTable(event, "desert_pyramid");
        addPoolToTable(event, "nether_bridge");
    }

    /**
     * A helper method that adds a new pool to a vanilla table.
     *
     * @param event    An instance of the current loot table event.
     * @param lootName The name of the loot table that should be added to.
     */
    private static void addPoolToTable(LootTableLoadEvent event, String lootName)
    {
        String lootPath = "chests/" + lootName;
        ResourceLocation modLocation = new ResourceLocation(VanillaExpansions.MOD_ID, lootPath);
        ResourceLocation vanillaLocation = new ResourceLocation(lootPath);

        if (event.getName().equals(vanillaLocation))
        {
            event.getTable().addPool(LootPool.builder().addEntry(TableLootEntry.builder(modLocation)).build());
        }
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

        addFeature(event, Category.NETHER, Decoration.UNDERGROUND_ORES, VeConfiguredFeatures.NETHER_SMOKY_QUARTZ_ORE,
                VeFeatureGenConfig.enableNetherSmokyQuartzOreSpawns.get());
        addFeature(event, Category.NETHER, Decoration.UNDERGROUND_ORES, VeConfiguredFeatures.NETHER_RUBY_ORE,
                VeFeatureGenConfig.enableNetherRubyOreSpawns.get());
        addFeature(event, Category.FOREST, Decoration.VEGETAL_DECORATION,
                VeConfiguredFeatures.PATCH_BLUEBERRY_BUSH_DECORATED,
                VeFeatureGenConfig.enableBlueberryBushSpawns.get());
        addFeature(event, Category.FOREST, Decoration.VEGETAL_DECORATION,
                VeConfiguredFeatures.PATCH_BLUEBERRY_BUSH_SPARSE, VeFeatureGenConfig.enableBlueberryBushSpawns.get());
        addFeature(event, Category.FOREST, Decoration.VEGETAL_DECORATION,
                VeConfiguredFeatures.PATCH_CRANBERRY_BUSH_DECORATED,
                VeFeatureGenConfig.enableCranberryBushSpawns.get());
        addFeature(event, Category.FOREST, Decoration.VEGETAL_DECORATION,
                VeConfiguredFeatures.PATCH_CRANBERRY_BUSH_SPARSE, VeFeatureGenConfig.enableCranberryBushSpawns.get());
        addFeature(event, Category.SWAMP, Decoration.VEGETAL_DECORATION,
                VeConfiguredFeatures.PATCH_WITCHS_CRADLE_DECORATED, VeFeatureGenConfig.enableWitchsCradleSpawns.get());
        addFeature(event, Category.SWAMP, Decoration.VEGETAL_DECORATION,
                VeConfiguredFeatures.PATCH_WITCHS_CRADLE_SPARSE, VeFeatureGenConfig.enableWitchsCradleSpawns.get());
        addFeature(event, endCityBiomes, Decoration.LAKES, VeConfiguredFeatures.DARK_MATTER_LAKE,
                VeFeatureGenConfig.enableVoidLakeSpawns.get());
        addFeature(event, endCityBiomes, Decoration.VEGETAL_DECORATION, VeConfiguredFeatures.SNAPDRAGON_AND_GRASS,
                VeFeatureGenConfig.enableSnapdragonAndEnderGrassSpawns.get());
        addFeature(event, darkForestBiomes, Decoration.VEGETAL_DECORATION, VeConfiguredFeatures.HUGE_PURPLE_MUSHROOM,
                VeFeatureGenConfig.enableHugePurpleMushroomSpawns.get());
        addStructure(event, Category.TAIGA, VeConfiguredStructures.configuredTaigaCabin,
                VeFeatureGenConfig.enableTaigaCabinSpawns.get());
        addStructure(event, forestCabinBiomes, VeConfiguredStructures.configuredForestCabin,
                VeFeatureGenConfig.enableForestCabinSpawns.get());
        addStructure(event, "crimson_forest", VeConfiguredStructures.configuredCrimsonCabin,
                VeFeatureGenConfig.enableCrimsonCabinSpawns.get());
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
                if (event.getName().equals(new ResourceLocation("minecraft:" + biome)))
                {
                    event.getGeneration().getFeatures(decoration).add(() -> feature);
                }
            }
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
                if (event.getName().equals(new ResourceLocation("minecraft:" + biome)))
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
     * Called when a living entity is falling.
     *
     * @param event An instance of the LivingFallEvent.
     */
    @SubscribeEvent
    public void onEntityFall(final LivingFallEvent event)
    {
        LivingEntity entity = event.getEntityLiving();

        if (VeEntityConfig.enableSaveTheBunnies.get() && entity instanceof RabbitEntity)
        {
            event.setCanceled(true);
        }
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
        if (VeBlockTags.end_bone_mealable.contains(event.getBlock().getBlock()))
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

                        if (VeBlockTags.end_bone_mealable.contains(world.getBlockState(blockpos.down()).getBlock())
                                && isAir(world.getBlockState(blockpos)))
                        {
                            world.setBlockState(blockpos,
                                    VeBlockTags.end_bone_meal_plants.getRandomElement(random).getDefaultState());
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
