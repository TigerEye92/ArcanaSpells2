package tigereye.eventhandler;

import net.minecraftforge.event.entity.living.*;
import net.minecraft.nbt.*;
import cpw.mods.fml.common.eventhandler.*;
import tigereye.*;
import tigereye.misc.*;
import net.minecraft.entity.player.*;
import cpw.mods.fml.common.network.simpleimpl.*;
import net.minecraftforge.event.entity.*;

public class HandlerManaEvents
{
    @SubscribeEvent
    public void onLivingDeathEvent(final LivingDeathEvent event) {
        if (!event.entity.worldObj.isRemote && event.entity instanceof EntityPlayer) {
            final NBTTagCompound playerData = new NBTTagCompound();
            event.entity.getExtendedProperties("ManaProperties").saveNBTData(playerData);
            CommonProxy.storeEntityData(((EntityPlayer)event.entity).getDisplayName() + "ManaProperties", playerData);
        }
    }
    
    @SubscribeEvent
    public void onEntityJoinWorld(final EntityJoinWorldEvent event) {
        if (!event.entity.worldObj.isRemote && event.entity instanceof EntityPlayer) {
            final NBTTagCompound playerData = CommonProxy.getEntityData(((EntityPlayer)event.entity).getDisplayName() + "ManaProperties");
            if (playerData != null) {
                event.entity.getExtendedProperties("ManaProperties").loadNBTData(playerData);
                ManaProperties.get((EntityPlayer)event.entity).replenishMana(0);
            }
            ArcanaSpells.networkWrapper.sendTo((IMessage)new MaxManaMessage(ManaProperties.get((EntityPlayer)event.entity).getMaxMana()), (EntityPlayerMP)event.entity);
        }
    }
    
    @SubscribeEvent
    public void onEntityConstructing(final EntityEvent.EntityConstructing event) {
        if (event.entity instanceof EntityPlayer && ManaProperties.get((EntityPlayer)event.entity) == null) {
            ManaProperties.register((EntityPlayer)event.entity);
        }
    }
}
