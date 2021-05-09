package rcarmstrong20.vanilla_expansions.proxy;

import net.minecraft.client.gui.ScreenManager;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import rcarmstrong20.vanilla_expansions.VanillaExpansions;
import rcarmstrong20.vanilla_expansions.client.renderer.VeBlockAndItemColors;
import rcarmstrong20.vanilla_expansions.client.renderer.screen.VeTransmutationTableScreen;
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
        ScreenManager.register(VeContainerTypes.woodcutter, VeWoodcutterScreen::new);
        ScreenManager.register(VeContainerTypes.transmutationTable, VeTransmutationTableScreen::new);

        VanillaExpansions.LOGGER.info("Registered screen factories.");
    }

    /**
     * Bind the tile entities to their renders.
     */
    private void registerTileEntityRenderers()
    {
        ClientRegistry.bindTileEntityRenderer(VeTileEntityType.coloredCampfire,
                VeColoredCampfireTileEntityRenderer::new);

        VanillaExpansions.LOGGER.info("Registered tile entity renderers.");
    }

    /**
     * Register the render layers for each block.
     */
    private void registerRenders()
    {
        // Cutout renders

        RenderTypeLookup.setRenderLayer(VeBlocks.redGlass, RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VeBlocks.redGlassPane, RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VeBlocks.spruceLadder, RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VeBlocks.birchLadder, RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VeBlocks.jungleLadder, RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VeBlocks.acaciaLadder, RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VeBlocks.darkOakLadder, RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VeBlocks.warpedLadder, RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VeBlocks.crimsonLadder, RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VeBlocks.ironLadder, RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VeBlocks.purpleMushroom, RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VeBlocks.woodcutter, RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VeBlocks.whiteCampfire, RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VeBlocks.orangeCampfire, RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VeBlocks.magentaCampfire, RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VeBlocks.lightBlueCampfire, RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VeBlocks.yellowCampfire, RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VeBlocks.limeCampfire, RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VeBlocks.pinkCampfire, RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VeBlocks.lightGrayCampfire, RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VeBlocks.grayCampfire, RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VeBlocks.cyanCampfire, RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VeBlocks.purpleCampfire, RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VeBlocks.blueCampfire, RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VeBlocks.brownCampfire, RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VeBlocks.greenCampfire, RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VeBlocks.redCampfire, RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VeBlocks.blackCampfire, RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VeBlocks.whiteLantern, RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VeBlocks.orangeLantern, RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VeBlocks.magentaLantern, RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VeBlocks.lightBlueLantern, RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VeBlocks.yellowLantern, RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VeBlocks.limeLantern, RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VeBlocks.pinkLantern, RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VeBlocks.lightGrayLantern, RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VeBlocks.grayLantern, RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VeBlocks.cyanLantern, RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VeBlocks.purpleLantern, RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VeBlocks.blueLantern, RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VeBlocks.brownLantern, RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VeBlocks.greenLantern, RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VeBlocks.redLantern, RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VeBlocks.blackLantern, RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VeBlocks.whiteTorch, RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VeBlocks.orangeTorch, RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VeBlocks.magentaTorch, RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VeBlocks.lightBlueTorch, RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VeBlocks.yellowTorch, RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VeBlocks.limeTorch, RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VeBlocks.pinkTorch, RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VeBlocks.lightGrayTorch, RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VeBlocks.grayTorch, RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VeBlocks.cyanTorch, RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VeBlocks.purpleTorch, RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VeBlocks.blueTorch, RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VeBlocks.brownTorch, RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VeBlocks.greenTorch, RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VeBlocks.redTorch, RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VeBlocks.blackTorch, RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VeBlocks.whiteWallTorch, RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VeBlocks.orangeWallTorch, RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VeBlocks.magentaWallTorch, RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VeBlocks.lightBlueWallTorch, RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VeBlocks.yellowWallTorch, RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VeBlocks.limeWallTorch, RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VeBlocks.pinkWallTorch, RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VeBlocks.lightGrayWallTorch, RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VeBlocks.grayWallTorch, RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VeBlocks.cyanWallTorch, RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VeBlocks.purpleWallTorch, RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VeBlocks.blueWallTorch, RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VeBlocks.brownWallTorch, RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VeBlocks.greenWallTorch, RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VeBlocks.redWallTorch, RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VeBlocks.blackWallTorch, RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VeBlocks.bokChoy, RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VeBlocks.garlic, RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VeBlocks.greenOnions, RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VeBlocks.blueberryBush, RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VeBlocks.cranberryBush, RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VeBlocks.witchsCradle, RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VeBlocks.snapdragon, RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VeBlocks.pottedSnapdragon, RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VeBlocks.enderGrass, RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VeBlocks.icicle, RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VeBlocks.cattail, RenderType.cutout());

        // Cut out mipped renders

        RenderTypeLookup.setRenderLayer(VeBlocks.endermanPlush, RenderType.cutoutMipped());

        // Translucent renders

        RenderTypeLookup.setRenderLayer(VeBlocks.glassOfDarkness, RenderType.translucent());

        VanillaExpansions.LOGGER.info("Registered Renders.");
    }
}
