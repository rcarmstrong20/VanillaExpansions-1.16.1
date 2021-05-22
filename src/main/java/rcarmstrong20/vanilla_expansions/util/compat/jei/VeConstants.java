package rcarmstrong20.vanilla_expansions.util.compat.jei;

import mezz.jei.api.constants.ModIds;
import net.minecraft.util.ResourceLocation;

/**
 * The Contants JEI class prevents the mod from building so this is a temporary
 * fix.
 *
 * @author Ryan
 */
public class VeConstants
{
    public static final String TEXTURE_GUI_PATH = "textures/gui/";
    public static final String TEXTURE_GUI_VANILLA = TEXTURE_GUI_PATH + "gui_vanilla.png";
    public static final ResourceLocation RECIPE_GUI_VANILLA = new ResourceLocation(ModIds.JEI_ID, TEXTURE_GUI_VANILLA);
}
