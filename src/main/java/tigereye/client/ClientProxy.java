package tigereye.client;

import tigereye.*;
import tigereye.model.*;
import net.minecraft.client.model.*;
import cpw.mods.fml.client.registry.*;
import net.minecraft.client.renderer.entity.*;
import tigereye.entity.*;
import tigereye.gui.GuiInGame;
import tigereye.render.*;
import net.minecraftforge.common.*;
import net.minecraft.client.*;
import tigereye.misc.*;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.common.network.simpleimpl.*;
import net.minecraft.entity.player.*;

public class ClientProxy extends CommonProxy
{
	
    private final GuiInGame guiInGame;

    public ClientProxy() {
        this.guiInGame = new GuiInGame(Minecraft.getMinecraft());
    }

    @SubscribeEvent
    public void onRenderTick(TickEvent.RenderTickEvent event) {
        if (event.phase == TickEvent.Phase.END) {
            guiInGame.renderGameOverlay();
        }
    }
	
    @Override
    public void registerRenderers() {
        RenderingRegistry.registerEntityRenderingHandler((Class)EntitySummonedWolf.class, (Render)new RenderSummonedWolf(new ModelSummonedWolf(), new ModelSummonedWolf(), 0.5f));
        RenderingRegistry.registerEntityRenderingHandler((Class)EntityRisenSkeleton.class, (Render)new RenderRisenSkeleton());
        RenderingRegistry.registerEntityRenderingHandler((Class)EntityRisenWitherSkeleton.class, (Render)new RenderRisenWitherSkeleton());
        RenderingRegistry.registerEntityRenderingHandler((Class)EntityRisenZombie.class, (Render)new RenderRisenZombie());
        RenderingRegistry.registerEntityRenderingHandler((Class)EntityRisenZombiePigman.class, (Render)new RenderRisenZombiePigman());
        RenderingRegistry.registerEntityRenderingHandler((Class)EntitySummonedWitch.class, (Render)new RenderSummonedWitch());
        RenderingRegistry.registerEntityRenderingHandler((Class)EntitySummonedSpider.class, (Render)new RenderSummonedSpider());
        RenderingRegistry.registerEntityRenderingHandler((Class)EntitySummonedCaveSpider.class, (Render)new RenderSummonedCaveSpider());
        RenderingRegistry.registerEntityRenderingHandler((Class)EntityRisenIceGiant.class, (Render)new RenderRisenIceGiant());
        RenderingRegistry.registerEntityRenderingHandler((Class)EntitySummonEldritchGolem.class, (Render)new RenderSummonEldritchGolem());
        RenderingRegistry.registerEntityRenderingHandler((Class)EntitySummonTaintacle.class, (Render)new RenderSummonTaintacle(0.6f, 10));
        MinecraftForge.EVENT_BUS.register((Object)new GuiManaBar(Minecraft.getMinecraft()));
    }
    
    @Override
    public EntityPlayer getPlayerFromMessageContext(final MessageContext ctx) {
        return (EntityPlayer)Minecraft.getMinecraft().thePlayer;
    }
}
