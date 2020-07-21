package rcarmstrong20.vanilla_expansions;

import java.util.Map;
import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.common.collect.ImmutableMap.Builder;

import net.minecraft.block.BeetrootBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.CampfireBlock;
import net.minecraft.block.CocoaBlock;
import net.minecraft.block.CropsBlock;
import net.minecraft.block.NetherWartBlock;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.passive.RabbitEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.TableLootEntry;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.IntegerProperty;
import net.minecraft.tileentity.CampfireTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.ParticleFactoryRegisterEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.event.entity.player.BonemealEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.RightClickBlock;
import net.minecraftforge.eventbus.api.Event.Result;
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
import rcarmstrong20.vanilla_expansions.client.renderer.particle.VeUndervoidParticle;
import rcarmstrong20.vanilla_expansions.config.VeConfig;
import rcarmstrong20.vanilla_expansions.config.VeCropConfig;
import rcarmstrong20.vanilla_expansions.core.VeBlocks;
import rcarmstrong20.vanilla_expansions.core.VeParticleTypes;
import rcarmstrong20.vanilla_expansions.core.VeSoundEvents;
import rcarmstrong20.vanilla_expansions.proxy.ClientProxy;
import rcarmstrong20.vanilla_expansions.proxy.CommonProxy;

@Mod("ve")
public class VanillaExpansions
{
    public static Object modInstance;
    public static final Logger LOGGER = LogManager.getLogger(VanillaExpansions.MOD_ID);
    public static final String MOD_ID = "ve";
    public static final VeItemGroup VE_GROUP = new VeItemGroup(VanillaExpansions.MOD_ID);
    public static final CommonProxy PROXY = DistExecutor.safeRunForDist(() -> ClientProxy::new, () -> CommonProxy::new);

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
     * @param event Called on client setup.
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
        Minecraft.getInstance().particles.registerFactory(VeParticleTypes.dripping_void,
                VeDripParticle.VeDrippingVoidFactory::new);
        Minecraft.getInstance().particles.registerFactory(VeParticleTypes.falling_void,
                VeDripParticle.VeFallingVoidFactory::new);
        Minecraft.getInstance().particles.registerFactory(VeParticleTypes.landing_void,
                VeDripParticle.VeLandingVoidFactory::new);
        Minecraft.getInstance().particles.registerFactory(VeParticleTypes.undervoid, VeUndervoidParticle.Factory::new);
    }

    /**
     * Controls right-click crop harvesting and campfire re-coloring behavior.
     * 
     * @param event Called when the player right-clicks a block.
     */
    @SubscribeEvent
    public void onRightClickBlock(final RightClickBlock event)
    {
        // General variables
        BlockPos pos = event.getPos();
        World world = event.getWorld();

        // Block and items
        BlockState worldState = event.getWorld().getBlockState(pos);
        ItemStack itemStack = event.getItemStack();
        TileEntity tileEntity = event.getWorld().getTileEntity(pos);

        // Crop age properties
        IntegerProperty cropsAge = CropsBlock.AGE;
        IntegerProperty netherWartAge = NetherWartBlock.AGE;
        IntegerProperty beetrootAge = BeetrootBlock.BEETROOT_AGE;
        IntegerProperty cocoaAge = CocoaBlock.AGE;

        // Campfire properties
        BooleanProperty isLit = CampfireBlock.LIT;

        if (!event.getWorld().isRemote)
        {
            // If the block your clicking is a crop and your not using bone meal return
            // true.
            if (VeCropConfig.enableRightClickHarvesting.get() && worldState.getBlock() instanceof CropsBlock
                    && itemStack.getItem() != Items.BONE_MEAL)
            {
                if (worldState.getBlock() instanceof BeetrootBlock)
                {
                    // When the beet root crop is fully grown and clicked then harvest it.
                    if (worldState.get(beetrootAge) == getMaxAge(beetrootAge))
                    {
                        resetCrop(worldState, world, pos, beetrootAge);
                        event.setResult(Result.ALLOW);
                        event.setCanceled(true);
                    }
                }
                // If its not a beet root crop then it must be a normal 7 stage crop and
                // if it's fully grown harvest it.
                else if (worldState.get(cropsAge) == getMaxAge(cropsAge))
                {
                    resetCrop(worldState, world, pos, cropsAge);
                    event.setResult(Result.ALLOW);
                    event.setCanceled(true);
                }
            }
            // If its not a crop it might be nether wart and if so check if it's fully grown
            // and if so harvest it.
            else if (worldState.getBlock() instanceof NetherWartBlock)
            {
                if (worldState.get(netherWartAge) == getMaxAge(netherWartAge))
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
            else if (worldState.getBlock() == Blocks.CAMPFIRE && tileEntity instanceof CampfireTileEntity)
            {
                Map<Item, Block> dyeToCampfire = (new Builder<Item, Block>())
                        .put(Items.WHITE_DYE, VeBlocks.white_campfire).put(Items.ORANGE_DYE, VeBlocks.orange_campfire)
                        .put(Items.MAGENTA_DYE, VeBlocks.magenta_campfire)
                        .put(Items.LIGHT_BLUE_DYE, VeBlocks.light_blue_campfire)
                        .put(Items.YELLOW_DYE, VeBlocks.yellow_campfire).put(Items.LIME_DYE, VeBlocks.lime_campfire)
                        .put(Items.PINK_DYE, VeBlocks.pink_campfire).put(Items.GRAY_DYE, VeBlocks.gray_campfire)
                        .put(Items.LIGHT_GRAY_DYE, VeBlocks.light_gray_campfire)
                        .put(Items.CYAN_DYE, VeBlocks.cyan_campfire).put(Items.PURPLE_DYE, VeBlocks.purple_campfire)
                        .put(Items.BLUE_DYE, VeBlocks.blue_campfire).put(Items.BROWN_DYE, VeBlocks.brown_campfire)
                        .put(Items.GREEN_DYE, VeBlocks.green_campfire).put(Items.RED_DYE, VeBlocks.red_campfire)
                        .put(Items.BLACK_DYE, VeBlocks.black_campfire).build();

                CampfireTileEntity campfireTileEntity = (CampfireTileEntity) tileEntity;
                NonNullList<ItemStack> campfireInventory = campfireTileEntity.getInventory();
                Direction currentFacing = worldState.get(CampfireBlock.FACING);

                // Only convert if the campfire is lit, the player is holding one of the dyes,
                // and the campfire's inventory is empty.
                if (worldState.get(isLit) && dyeToCampfire.containsKey(itemStack.getItem())
                        && campfireInventory.get(0) == ItemStack.EMPTY)
                {
                    world.playSound((PlayerEntity) null, pos, VeSoundEvents.BLOCK_CAMPFIRE_DYED, SoundCategory.BLOCKS,
                            1.0F, 0.8F + world.rand.nextFloat() * 0.4F);
                    world.setBlockState(pos, dyeToCampfire.get(itemStack.getItem()).getDefaultState()
                            .with(CampfireBlock.FACING, currentFacing));
                    itemStack.shrink(1);
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
    private void resetCrop(BlockState state, World world, BlockPos pos, IntegerProperty age)
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
    private void addPoolToTable(LootTableLoadEvent event, String lootName)
    {
        String lootPath = "chests/" + lootName;
        ResourceLocation modLocation = new ResourceLocation(VanillaExpansions.MOD_ID, lootPath);
        ResourceLocation vanillaLocation = new ResourceLocation(lootPath);

        if (event.getName().equals(vanillaLocation))
        {
            event.getTable().addPool(LootPool.builder().addEntry(TableLootEntry.builder(modLocation)).build());
        }
    }

    /**
     * Individually tracks the naming behavior for each white and killer rabbit
     * entity, then either sets the rabbit type to 99 or 1.
     * 
     * @param event Called when the player right-clicks any entity.
     */
    @SubscribeEvent
    public void onNameBunnyEntity(final PlayerInteractEvent.EntityInteractSpecific event)
    {
        World world = event.getWorld();

        if (!world.isRemote && event.getTarget() instanceof RabbitEntity)
        {
            RabbitEntity rabbit = (RabbitEntity) event.getTarget();
            ItemStack itemStack = event.getItemStack();

            if (rabbit.getRabbitType() == 1 || rabbit.getRabbitType() == 99)
            {
                if (itemStack.getItem() == Items.NAME_TAG && itemStack.hasDisplayName())
                {
                    if ("The Killer Bunny".equals(itemStack.getDisplayName().getUnformattedComponentText()))
                    {
                        rabbit.setRabbitType(99);
                    }
                    else
                    {
                        rabbit.setRabbitType(1);
                    }
                }
            }
        }
    }

    /**
     * Used to add functionality for growing snapdragons on end stone when using
     * bone meal.
     * 
     * @param event Called when the player uses bone meal.
     */
    @SubscribeEvent
    public void onBonemeal(final BonemealEvent event)
    {
        World world = event.getWorld();
        BlockPos pos = event.getPos();
        Random random = new Random();

        if (event.getBlock().getBlock() == Blocks.END_STONE)
        {
            if (!world.isRemote) // Only place the blocks on the server
            {
                for (int i = 0; i < 128; ++i)
                {
                    BlockPos blockpos = pos;
                    BlockState blockstate = VeBlocks.snapdragon.getDefaultState();

                    for (int j = 0; j < i / 16; ++j)
                    {
                        blockpos = blockpos.add(random.nextInt(3) - 1, (random.nextInt(3) - 1) * random.nextInt(3) / 2,
                                random.nextInt(3) - 1);

                        if (world.getBlockState(blockpos.down()).getBlock() == Blocks.END_STONE
                                && world.getBlockState(blockpos).isAir(world, blockpos))
                        {
                            world.setBlockState(blockpos, blockstate);
                        }
                    }
                }
            }
            event.setResult(Result.ALLOW);
        }
    }
}
