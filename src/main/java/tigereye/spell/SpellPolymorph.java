package tigereye.spell;

import net.minecraft.world.*;
import net.minecraft.entity.player.*;
import net.minecraft.entity.*;
import tigereye.potion.*;
import net.minecraft.potion.*;

public class SpellPolymorph extends Spell
{
    protected SpellPolymorph(final int par1) {
        super(par1);
        this.setName("polymorph");
    }
    
    @Override
    public int getManaCost() {
        return 6;
    }
    
    @Override
    public short getCooldown() {
        return 200;
    }
    
    @Override
    public void castSpell(final short par1Level, final World par2World, final EntityPlayer par3EntityPlayer) {
        par2World.playSoundAtEntity((Entity)par3EntityPlayer, this.getSoundName(), 1.0f, 1.0f);
        par3EntityPlayer.addPotionEffect(new PotionEffect(ModPotions.polymorphed.getId(), 200, 0));
    }
}
