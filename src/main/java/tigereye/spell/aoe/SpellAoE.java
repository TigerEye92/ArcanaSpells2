package tigereye.spell.aoe;

import tigereye.spell.*;
import net.minecraft.world.*;
import net.minecraft.entity.player.*;
import net.minecraft.entity.*;
import net.minecraft.util.*;
import net.minecraft.entity.passive.*;
import java.util.*;

public abstract class SpellAoE extends Spell
{
    protected SpellAoE(final int par1) {
        super(par1);
    }
    
    @Override
    public short getMaxLevel() {
        return 3;
    }
    
    @Override
    public short getCooldown() {
        return 200;
    }
    
    @Override
    public int getManaCost() {
        return 6;
    }
    
    @Override
    public void castSpell(final short par1Level, final World par2World, final EntityPlayer par3EntityPlayer) {
        final double areaSize = par1Level * 4;
        final List<EntityLivingBase> entities = (List<EntityLivingBase>)par2World.getEntitiesWithinAABB((Class)EntityLivingBase.class, par3EntityPlayer.boundingBox.expand(areaSize, areaSize, areaSize));
        if (entities.size() <= 1) {
            par3EntityPlayer.addChatMessage((IChatComponent)new ChatComponentText("No targets nearby!"));
        }
        for (final EntityLivingBase entity : entities) {
            if ((!(entity instanceof EntityTameable) || ((EntityTameable)entity).getOwner() != par3EntityPlayer) && entity != par3EntityPlayer) {
                this.affectEntity(par2World, entity);
            }
        }
    }
    
    protected abstract void affectEntity(final World p0, final EntityLivingBase p1);
}
