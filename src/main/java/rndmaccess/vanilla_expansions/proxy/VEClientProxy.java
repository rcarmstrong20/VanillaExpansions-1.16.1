package rndmaccess.vanilla_expansions.proxy;

import net.minecraft.client.gui.ScreenManager;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import rndmaccess.vanilla_expansions.VanillaExpansions;
import rndmaccess.vanilla_expansions.client.renderer.VEBlockAndItemColors;
import rndmaccess.vanilla_expansions.client.renderer.screen.VETransmutationTableScreen;
import rndmaccess.vanilla_expansions.client.renderer.screen.VEWoodcutterScreen;
import rndmaccess.vanilla_expansions.client.renderer.tile_entity.VEColoredCampfireTileEntityRenderer;
import rndmaccess.vanilla_expansions.core.VEBlocks;
import rndmaccess.vanilla_expansions.core.VEContainerTypes;
import rndmaccess.vanilla_expansions.core.VETileEntityTypes;

public class VEClientProxy extends VECommonProxy
{
    @Override
    public void onSetupClient()
    {
        super.onSetupClient();
        this.registerBlockColors();
        this.registerScreenFactories();
        this.registerTileEntityRenderers();
        this.registerRenders();
    }

    private void registerBlockColors()
    {
        VEBlockAndItemColors.registerGrassColors(false, VEBlocks.endermanPlush, VEBlocks.grassSlab);

        VanillaExpansions.LOGGER.info("Registered block and item colors.");
    }

    /**
     * Register the screen factories for the containers.
     */
    private void registerScreenFactories()
    {
        ScreenManager.register(VEContainerTypes.woodcutter, VEWoodcutterScreen::new);
        ScreenManager.register(VEContainerTypes.transmutationTable, VETransmutationTableScreen::new);

        VanillaExpansions.LOGGER.info("Registered screen factories.");
    }

    /**
     * Bind the tile entities to their renders.
     */
    private void registerTileEntityRenderers()
    {
        ClientRegistry.bindTileEntityRenderer(VETileEntityTypes.coloredCampfire,
                VEColoredCampfireTileEntityRenderer::new);

        VanillaExpansions.LOGGER.info("Registered tile entity renderers.");
    }

    /**
     * Register the render layers for each block.
     */
    private void registerRenders()
    {
        // Cutout renders

        RenderTypeLookup.setRenderLayer(VEBlocks.redGlass, RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VEBlocks.redGlassPane, RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VEBlocks.spruceLadder, RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VEBlocks.birchLadder, RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VEBlocks.jungleLadder, RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VEBlocks.acaciaLadder, RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VEBlocks.darkOakLadder, RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VEBlocks.warpedLadder, RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VEBlocks.crimsonLadder, RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VEBlocks.ironLadder, RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VEBlocks.purpleMushroom, RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VEBlocks.woodcutter, RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VEBlocks.whiteCampfire, RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VEBlocks.orangeCampfire, RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VEBlocks.magentaCampfire, RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VEBlocks.lightBlueCampfire, RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VEBlocks.yellowCampfire, RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VEBlocks.limeCampfire, RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VEBlocks.pinkCampfire, RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VEBlocks.lightGrayCampfire, RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VEBlocks.grayCampfire, RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VEBlocks.cyanCampfire, RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VEBlocks.purpleCampfire, RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VEBlocks.blueCampfire, RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VEBlocks.brownCampfire, RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VEBlocks.greenCampfire, RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VEBlocks.redCampfire, RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VEBlocks.blackCampfire, RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VEBlocks.whiteLantern, RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VEBlocks.orangeLantern, RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VEBlocks.magentaLantern, RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VEBlocks.lightBlueLantern, RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VEBlocks.yellowLantern, RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VEBlocks.limeLantern, RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VEBlocks.pinkLantern, RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VEBlocks.lightGrayLantern, RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VEBlocks.grayLantern, RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VEBlocks.cyanLantern, RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VEBlocks.purpleLantern, RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VEBlocks.blueLantern, RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VEBlocks.brownLantern, RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VEBlocks.greenLantern, RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VEBlocks.redLantern, RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VEBlocks.blackLantern, RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VEBlocks.whiteTorch, RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VEBlocks.orangeTorch, RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VEBlocks.magentaTorch, RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VEBlocks.lightBlueTorch, RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VEBlocks.yellowTorch, RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VEBlocks.limeTorch, RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VEBlocks.pinkTorch, RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VEBlocks.lightGrayTorch, RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VEBlocks.grayTorch, RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VEBlocks.cyanTorch, RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VEBlocks.purpleTorch, RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VEBlocks.blueTorch, RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VEBlocks.brownTorch, RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VEBlocks.greenTorch, RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VEBlocks.redTorch, RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VEBlocks.blackTorch, RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VEBlocks.whiteWallTorch, RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VEBlocks.orangeWallTorch, RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VEBlocks.magentaWallTorch, RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VEBlocks.lightBlueWallTorch, RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VEBlocks.yellowWallTorch, RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VEBlocks.limeWallTorch, RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VEBlocks.pinkWallTorch, RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VEBlocks.lightGrayWallTorch, RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VEBlocks.grayWallTorch, RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VEBlocks.cyanWallTorch, RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VEBlocks.purpleWallTorch, RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VEBlocks.blueWallTorch, RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VEBlocks.brownWallTorch, RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VEBlocks.greenWallTorch, RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VEBlocks.redWallTorch, RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VEBlocks.blackWallTorch, RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VEBlocks.bokChoy, RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VEBlocks.garlic, RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VEBlocks.greenOnions, RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VEBlocks.blueberryBush, RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VEBlocks.cranberryBush, RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VEBlocks.witchsCradle, RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VEBlocks.snapdragon, RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VEBlocks.pottedSnapdragon, RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VEBlocks.enderGrass, RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VEBlocks.icicle, RenderType.cutout());
        RenderTypeLookup.setRenderLayer(VEBlocks.cattail, RenderType.cutout());

        // Cut out mipped renders

        RenderTypeLookup.setRenderLayer(VEBlocks.endermanPlush, RenderType.cutoutMipped());
        RenderTypeLookup.setRenderLayer(VEBlocks.grassSlab, RenderType.cutoutMipped());

        VanillaExpansions.LOGGER.info("Registered Renders.");
    }
}
