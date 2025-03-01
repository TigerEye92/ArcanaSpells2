package tigereye.eventhandler;

import net.minecraft.client.renderer.entity.*;
import tigereye.render.*;
import net.minecraftforge.client.event.*;
import tigereye.potion.*;
import net.minecraft.entity.*;
import cpw.mods.fml.common.eventhandler.*;

public class HandlerOnRenderLiving
{
    private final Render renderPolyBat;
    
    public HandlerOnRenderLiving() {
        this.renderPolyBat = (Render)new RenderPolyBat();
    }
    
    @SubscribeEvent
    public void onRenderLiving(final RenderLivingEvent.Pre event) {
        if (event.entity.isPotionActive(ModPotions.polymorphed)) {
            event.setCanceled(true);
            this.renderPolyBat.doRender((Entity)event.entity, event.x, event.y, event.z, 0.0f, 0.0f);
        }
    }
}
