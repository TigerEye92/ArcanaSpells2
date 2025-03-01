package tigereye.eventhandler;

import net.minecraftforge.common.*;
import cpw.mods.fml.common.*;

public class ModEventHandlers
{
    public static void registerModEventHandlers() {
        MinecraftForge.EVENT_BUS.register((Object)new HandlerManaEvents());
        MinecraftForge.EVENT_BUS.register((Object)new HandlerOnAnvilUpdate());
        MinecraftForge.EVENT_BUS.register((Object)new HandlerOnLivingAttack());
        FMLCommonHandler.instance().bus().register((Object)new HandlerOnPlayerPostTick());
    }
}
