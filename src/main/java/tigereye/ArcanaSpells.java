package tigereye;

import cpw.mods.fml.common.network.simpleimpl.*;
import cpw.mods.fml.common.event.*;
import cpw.mods.fml.common.network.*;
import tigereye.misc.*;
import cpw.mods.fml.relauncher.*;
import net.minecraftforge.common.MinecraftForge;
import tigereye.itemblock.*;
import tigereye.entity.*;
import tigereye.eventhandler.*;
import tigereye.gui.GuiHandler;
import cpw.mods.fml.common.registry.*;
import cpw.mods.fml.common.*;

@Mod(modid = "ArcanaSpells", version = "1.0.0", name = "ArcanaSpells", dependencies = "required-after:Forge@[10.13.2,);required-after:Thaumcraft@[4.2.3.4,)")
public class ArcanaSpells
{
    @Mod.Instance("ArcanaSpells")
    public static ArcanaSpells instance;
    @SidedProxy(clientSide = "tigereye.client.ClientProxy", serverSide = "tigereye.CommonProxy")
    public static CommonProxy proxy;
    public static final String MODID = "tigereye";
    public static final String VERSION = "1.0.0";
    public static final String NAME = "tigereye";
    public static final String CLIENTSIDE = "tigereye.client.ClientProxy";
    public static final String SERVERSIDE = "tigereye.CommonProxy";
    public static SimpleNetworkWrapper networkWrapper;
    ManaCrystalGen manaCrystalGen;
    
    public ArcanaSpells() {
        this.manaCrystalGen = new ManaCrystalGen();
    }
    
    @Mod.EventHandler
    public void preInit(final FMLPreInitializationEvent event) {
        (ArcanaSpells.networkWrapper = NetworkRegistry.INSTANCE.newSimpleChannel("tigereye")).registerMessage((Class)MaxManaMessage.Handler.class, (Class)MaxManaMessage.class, 0, Side.CLIENT);
        ModItemsBlocks.registerModItemsAndBlocks();
        ModEntities.registerModEntities();
        ModEventHandlers.registerModEventHandlers();
        GameRegistry.registerWorldGenerator((IWorldGenerator)this.manaCrystalGen, 0);
        ArcanaSpells.proxy.registerRenderers();
        MinecraftForge.EVENT_BUS.register(proxy);
        NetworkRegistry.INSTANCE.registerGuiHandler(this, new GuiHandler());

    }
    
    
    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        // Регистрируем GuiHandler

    }
}
