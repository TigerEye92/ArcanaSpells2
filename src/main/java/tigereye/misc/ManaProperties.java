package tigereye.misc;

import net.minecraftforge.common.*;
import net.minecraft.entity.player.*;
import net.minecraft.util.*;
import net.minecraft.nbt.*;
import net.minecraft.entity.*;
import net.minecraft.world.*;

public class ManaProperties implements IExtendedEntityProperties
{
    public static final String NAME = "ManaProperties";
    public static final int MANA_WATCHER = 20;
    private EntityPlayer player;
    private int maxMana;
    public int manaTimer;
    
    public ManaProperties(final EntityPlayer player) {
        this.player = player;
        this.maxMana = 0;
        this.manaTimer = 0;
        this.player.getDataWatcher().addObject(20, (Object)this.maxMana);
    }
    
    public boolean consumeMana(final int amount) {
        int currentMana = this.player.getDataWatcher().getWatchableObjectInt(20);
        if (currentMana >= amount) {
            currentMana -= amount;
            this.player.getDataWatcher().updateObject(20, (Object)currentMana);
            return true;
        }
        return false;
    }
    
    public void replenishMana(final int amount) {
        int currentMana = this.player.getDataWatcher().getWatchableObjectInt(20);
        currentMana = Math.min(currentMana + amount, this.maxMana);
        this.player.getDataWatcher().updateObject(20, (Object)currentMana);
    }
    
    public int getMaxMana() {
        return this.maxMana;
    }
    
    public void setMaxMana(final int maxMana) {
        this.maxMana = MathHelper.clamp_int(maxMana, 0, 100);
    }
    
    public int getCurrentMana() {
        return this.player.getDataWatcher().getWatchableObjectInt(20);
    }
    
    public static final void register(final EntityPlayer player) {
        player.registerExtendedProperties("ManaProperties", (IExtendedEntityProperties)new ManaProperties(player));
    }
    
    public static final ManaProperties get(final EntityPlayer player) {
        return (ManaProperties)player.getExtendedProperties("ManaProperties");
    }
    
    public void saveNBTData(final NBTTagCompound compound) {
        final NBTTagCompound properties = new NBTTagCompound();
        properties.setInteger("CurrentMana", this.player.getDataWatcher().getWatchableObjectInt(20));
        properties.setInteger("MaxMana", this.maxMana);
        properties.setInteger("ManaTimer", this.manaTimer);
        compound.setTag("ManaProperties", (NBTBase)properties);
    }
    
    public void loadNBTData(final NBTTagCompound compound) {
        final NBTTagCompound properties = (NBTTagCompound)compound.getTag("ManaProperties");
        this.player.getDataWatcher().updateObject(20, (Object)properties.getInteger("CurrentMana"));
        this.maxMana = properties.getInteger("MaxMana");
        this.manaTimer = properties.getInteger("ManaTimer");
    }
    
    public void init(final Entity entity, final World world) {
    }
}
