package tigereye.eventhandler;

import net.minecraftforge.client.event.*;
import net.minecraft.client.*;
import tigereye.potion.*;
import cpw.mods.fml.common.eventhandler.*;

public class HandlerOnRenderHand
{
    @SubscribeEvent
    public void OnRenderHand(final RenderHandEvent event) {
        if (Minecraft.getMinecraft().thePlayer.isPotionActive(ModPotions.polymorphed)) {
            event.setCanceled(true);
        }
    }
}
