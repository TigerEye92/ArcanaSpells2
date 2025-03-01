package tigereye.spell.shield;

import tigereye.spell.*;
import net.minecraft.world.*;
import net.minecraft.entity.player.*;
import net.minecraft.entity.*;
import net.minecraft.potion.*;
import tigereye.potion.*;

public abstract class SpellShield extends Spell
{
    protected SpellShield(final int par1, final String par2Name) {
        super(par1);
        this.setName("shield." + par2Name);
    }
    
    @Override
    public int getManaCost() {
        return 4;
    }
    
    @Override
    public short getCooldown() {
        return 1200;
    }
    
    @Override
    public short getMaxLevel() {
        return 3;
    }
    
    public abstract Potion getShieldEffect();
    
    @Override
    public void castSpell(final short par1Level, final World par2World, final EntityPlayer par3EntityPlayer) {
        par2World.playSoundAtEntity((Entity)par3EntityPlayer, this.getSoundName(), 1.0f, 1.0f);
        clearShields(par3EntityPlayer);
        par3EntityPlayer.addPotionEffect(new PotionEffect(this.getShieldEffect().getId(), 12000, par1Level - 1));
    }
    
    private static void clearShields(final EntityPlayer par1EntityPlayer) {
        par1EntityPlayer.removePotionEffect(ModPotions.fireShield.getId());
        par1EntityPlayer.removePotionEffect(ModPotions.earthShield.getId());
        par1EntityPlayer.removePotionEffect(ModPotions.waterShield.getId());
        par1EntityPlayer.removePotionEffect(ModPotions.stormShield.getId());
        par1EntityPlayer.removePotionEffect(ModPotions.frostShield.getId());
    }
}
