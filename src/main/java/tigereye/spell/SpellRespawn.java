package tigereye.spell;

import net.minecraft.world.*;
import net.minecraft.entity.player.*;
import net.minecraft.entity.*;
import net.minecraft.util.*;

public class SpellRespawn extends Spell
{
    protected SpellRespawn(final int par1) {
        super(par1);
        this.setName("respawn");
    }
    
    @Override
    public int getManaCost() {
        return 12;
    }
    
    @Override
    public short getCooldown() {
        return 6000;
    }
    
    @Override
    public String getParticleName() {
        return "portal";
    }
    
    @Override
    public double getParticleAmount() {
        return 1.0;
    }
    
    @Override
    public String getSoundName() {
        return "mob.ghast.fireball";
    }
    
    @Override
    public void castSpell(final short par1Level, final World par2World, final EntityPlayer par3EntityPlayer) {
        par2World.playSoundAtEntity((Entity)par3EntityPlayer, this.getSoundName(), 1.0f, 1.0f);
        ChunkCoordinates coordSpawn = par3EntityPlayer.getBedLocation(0);
        if (coordSpawn != null) {
            par3EntityPlayer.setPositionAndUpdate((double)coordSpawn.posX, (double)(coordSpawn.posY + 1), (double)coordSpawn.posZ);
        }
        else {
            coordSpawn = par2World.getSpawnPoint();
            par3EntityPlayer.setPositionAndUpdate((double)coordSpawn.posX, (double)coordSpawn.posY, (double)coordSpawn.posZ);
        }
        par2World.playSoundAtEntity((Entity)par3EntityPlayer, this.getSoundName(), 1.0f, 1.0f);
    }
}
