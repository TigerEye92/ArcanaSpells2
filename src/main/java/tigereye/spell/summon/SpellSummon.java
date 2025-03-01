package tigereye.spell.summon;

import tigereye.spell.*;
import java.lang.reflect.*;
import net.minecraft.world.*;
import net.minecraft.entity.player.*;
import tigereye.entity.*;
import net.minecraft.util.*;
import net.minecraft.entity.*;
import net.minecraft.entity.passive.*;
import java.util.*;

public class SpellSummon extends Spell
{
    private final Constructor entityConstr;
    
    public SpellSummon(final int par1, final String par2Name, final Class par3EntityClass) {
        super(par1);
        this.setName("summon." + par2Name);
        this.entityConstr = getConstructor(par3EntityClass);
    }
    
    private static Constructor getConstructor(final Class par1EntityClass) {
        try {
            return par1EntityClass.getConstructor(World.class);
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    @Override
    public short getMaxLevel() {
        return 4;
    }
    
    @Override
    public int getManaCost() {
        return 8;
    }
    
    @Override
    public short getCooldown() {
        return 1200;
    }
    
    @Override
    public double getParticleAmount() {
        return 0.25;
    }
    
    @Override
    public String getParticleName() {
        return "witchMagic";
    }
    
    @Override
    public void castSpell(final short par1Level, final World par2World, final EntityPlayer par3EntityPlayer) {
        par2World.playSoundAtEntity((Entity)par3EntityPlayer, this.getSoundName(), 1.0f, 1.0f);
        final List<EntitySummoned> entities = (List<EntitySummoned>)par2World.getEntitiesWithinAABB((Class)EntitySummoned.class, par3EntityPlayer.boundingBox.expand(20.0, 20.0, 20.0));
        for (final EntitySummoned entity : entities) {
            if (entity.getOwner() == par3EntityPlayer) {
                entity.attackEntityFrom(DamageSource.generic, entity.getMaxHealth());
            }
        }
        try {
            final int[] xSpawnOffset = { -2, 0, 2, 0 };
            final int[] zSpawnOffset = { 0, 2, 0, -2 };
            for (int i = 0; i < this.getNormalizedLevel(par1Level); ++i) {
            	EntityCreature entity2 = (EntityCreature) entityConstr.newInstance(par2World);
                entity2.setLocationAndAngles(par3EntityPlayer.posX + xSpawnOffset[i], par3EntityPlayer.posY, par3EntityPlayer.posZ + zSpawnOffset[i], entity2.rotationYaw, 0.0f);
                final String comSendName = par3EntityPlayer.getCommandSenderName();
                ((EntityTameable)entity2).func_152115_b(par3EntityPlayer.getUniqueID().toString());
                entity2.setCustomNameTag("\u041f\u0440\u0438\u0441\u043f\u0435\u0448\u043d\u0438\u043a" + comSendName);
                entity2.setAlwaysRenderNameTag(true);
                par2World.spawnEntityInWorld((Entity)entity2);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
