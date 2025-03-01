package tigereye.spell.targeted;

import tigereye.spell.*;
import net.minecraft.world.*;
import net.minecraft.entity.player.*;
import net.minecraft.entity.projectile.*;
import net.minecraft.util.*;
import net.minecraft.entity.*;

public class SpellGhastFire extends Spell implements ISpellTargeted
{
    public SpellGhastFire(final int par1) {
        super(par1);
        this.setName("ghastfire");
    }
    
    @Override
    public int getManaCost() {
        return 3;
    }
    
    @Override
    public short getMaxLevel() {
        return 3;
    }
    
    @Override
    public short getCooldown() {
        return 40;
    }
    
    @Override
    public String getParticleName() {
        return "flame";
    }
    
    @Override
    public double getParticleAmount() {
        return 0.3;
    }
    
    @Override
    public void castSpell(final short par1Level, final World par2World, final EntityPlayer par3EntityPlayer) {
        par2World.playSoundAtEntity((Entity)par3EntityPlayer, this.getSoundName(), 1.0f, 1.0f);
        final Vec3 v3 = par3EntityPlayer.getLook(1.0f);
        final int[] accuracies = { 9, 8, 9 };
        for (int i = 0; i < this.getNormalizedLevel(par1Level); ++i) {
            final EntitySmallFireball smallfireball = new EntitySmallFireball(par2World, par3EntityPlayer.posX, par3EntityPlayer.posY + par3EntityPlayer.eyeHeight, par3EntityPlayer.posZ, v3.xCoord + SpellBlazeFire.random.nextGaussian() / accuracies[par1Level - 1], v3.yCoord, v3.zCoord + SpellBlazeFire.random.nextGaussian() / accuracies[par1Level - 1]);
            smallfireball.shootingEntity = (EntityLivingBase)par3EntityPlayer;
            par2World.spawnEntityInWorld((Entity)smallfireball);
        }
    }
}
