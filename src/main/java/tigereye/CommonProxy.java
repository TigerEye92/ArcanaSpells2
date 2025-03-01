package tigereye;

import net.minecraft.nbt.*;
import cpw.mods.fml.common.network.simpleimpl.*;
import net.minecraft.entity.player.*;
import java.util.*;

public class CommonProxy
{
    private static final Map<String, NBTTagCompound> extendedEntityData;
    
    public void registerRenderers() {
    }
    
    public EntityPlayer getPlayerFromMessageContext(final MessageContext ctx) {
        return (EntityPlayer)ctx.getServerHandler().playerEntity;
    }
    
    public static void storeEntityData(final String name, final NBTTagCompound compound) {
        CommonProxy.extendedEntityData.put(name, compound);
    }
    
    public static NBTTagCompound getEntityData(final String name) {
        return CommonProxy.extendedEntityData.remove(name);
    }
    
    static {
        extendedEntityData = new HashMap<String, NBTTagCompound>();
    }
}
