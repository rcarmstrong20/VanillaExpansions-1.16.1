package rcarmstrong20.vanilla_expansions.proxy;

import net.minecraft.client.gui.ScreenManager;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import rcarmstrong20.vanilla_expansions.VanillaExpansions;
import rcarmstrong20.vanilla_expansions.client.renderer.VeBlockAndItemColors;
import rcarmstrong20.vanilla_expansions.client.renderer.screen.VeWoodcutterScreen;
import rcarmstrong20.vanilla_expansions.client.renderer.tile_entity.VeColoredCampfireTileEntityRenderer;
import rcarmstrong20.vanilla_expansions.core.VeBlocks;
import rcarmstrong20.vanilla_expansions.core.VeContainerTypes;
import rcarmstrong20.vanilla_expansions.core.VeTileEntityType;

public class ClientProxy extends CommonProxy
{
    @Override
    public void onSetupClient()
    {
        super.onSetupClient();
        VeBlockAndItemColors.registerColorHandlers();
        this.registerScreenFactories();
        this.registerTileEntityRenderers();
        this.registerRenders();
    }

    /**
     * Register the screen factories for the containers.
     */
    private void registerScreenFactories()
    {
        ScreenManager.registerFactory(VeContainerTypes.woodcutter, VeWoodcutterScreen::new);

        VanillaExpansions.LOGGER.info("Registered screen factories.");
    }

    /**
     * Bind the tile entities to their renders.
     */
    private void registerTileEntityRenderers()
    {
        ClientRegistry.bindTileEntityRenderer(VeTileEntityType.colored_campfire,
                VeColoredCampfireTileEntityRenderer::new);

        VanillaExpansions.LOGGER.info("Registered tile entity renderers.");
    }

    /**
     * Register the render layers for each block.
     */
    private void registerRenders()
    {
        // cutout renders

        RenderTypeLookup.setRenderLayer(VeBlocks.redGlass, RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(VeBlocks.redGlassPane, RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(VeBlocks.spruceLadder, RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(VeBlocks.birchLadder, RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(VeBlocks.jungleLadder, RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(VeBlocks.acaciaLadder, RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(VeBlocks.darkOakLadder, RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(VeBlocks.warpedLadder, RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(VeBlocks.crimsonLadder, RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(VeBlocks.ironLadder, RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(VeBlocks.modernDoor, RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(VeBlocks.purpleMushroom, RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(VeBlocks.woodcutter, RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(VeBlocks.whiteCampfire, RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(VeBlocks.orangeCampfire, RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(VeBlocks.magentaCampfire, RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(VeBlocks.lightBlueCampfire, RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(VeBlocks.yellowCampfire, RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(VeBlocks.limeCampfire, RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(VeBlocks.pinkCampfire, RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(VeBlocks.lightGrayCampfire, RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(VeBlocks.grayCampfire, RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(VeBlocks.cyanCampfire, RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(VeBlocks.purpleCampfire, RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(VeBlocks.blueCampfire, RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(VeBlocks.brownCampfire, RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(VeBlocks.greenCampfire, RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(VeBlocks.redCampfire, RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(VeBlocks.blackCampfire, RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(VeBlocks.bokChoy, RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(VeBlocks.garlic, RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(VeBlocks.greenOnions, RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(VeBlocks.quinoa, RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(VeBlocks.blueberryBush, RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(VeBlocks.cranberryBush, RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(VeBlocks.witchsCradle, RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(VeBlocks.snapdragon, RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(VeBlocks.pottedSnapdragon, RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(VeBlocks.enderGrass, RenderType.getCutout());

        // Cut out mipped renders

        RenderTypeLookup.setRenderLayer(VeBlocks.endermanPlush, RenderType.getCutoutMipped());

        // Translucent renders

        RenderTypeLookup.setRenderLayer(VeBlocks.glassOfDarkness, RenderType.getTranslucent());

        VanillaExpansions.LOGGER.info("Registered Renders.");
    }
}
