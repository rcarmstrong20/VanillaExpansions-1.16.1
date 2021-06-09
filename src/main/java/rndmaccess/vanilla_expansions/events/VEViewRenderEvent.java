package rndmaccess.vanilla_expansions.events;

import net.minecraft.client.renderer.ActiveRenderInfo;
import net.minecraft.fluid.FluidState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.EntityViewRenderEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import rndmaccess.vanilla_expansions.fluid.VEDarkMatterFluid;

public class VEViewRenderEvent
{
    @SubscribeEvent
    @OnlyIn(Dist.CLIENT)
    public void onFogColor(EntityViewRenderEvent.FogColors event)
    {
        ActiveRenderInfo info = event.getInfo();
        FluidState state = info.getFluidInCamera();

        float black = 0.0F;

        // Makes the fog black when the player is inside dark matter.
        if (state.getType() instanceof VEDarkMatterFluid)
        {
            event.setRed(black);
            event.setGreen(black);
            event.setBlue(black);
        }
    }

    @SubscribeEvent
    @OnlyIn(Dist.CLIENT)
    public void onFogDensity(EntityViewRenderEvent.FogDensity event)
    {
        ActiveRenderInfo info = event.getInfo();
        FluidState state = info.getFluidInCamera();

        // Density of the fog when in dark matter.
        if (state.getType() instanceof VEDarkMatterFluid)
        {
            event.setDensity(0.5F);
            event.setCanceled(true);
        }
    }
}
