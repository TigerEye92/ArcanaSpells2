package tigereye.spell.aoe;

import net.minecraft.world.*;
import net.minecraft.entity.player.*;
import net.minecraft.entity.*;
import net.minecraft.util.*;

public class SpellBlazeStorm extends SpellAoE
{
    public SpellBlazeStorm(final int par1) {
        super(par1);
        this.setName("blazestorm");
    }
    
    @Override
    public void castSpell(final short par1Level, final World par2World, final EntityPlayer par3EntityPlayer) {
        par2World.playSoundAtEntity((Entity)par3EntityPlayer, this.getSoundName(), 1.0f, 1.0f);
        super.castSpell(par1Level, par2World, par3EntityPlayer);
    }
    
    @Override
    protected void affectEntity(final World par1World, final EntityLivingBase par2EntityLivingBase) {
        par2EntityLivingBase.setFire(5);
        par2EntityLivingBase.attackEntityFrom(DamageSource.onFire, 4.0f);
        par2EntityLivingBase.knockBack((Entity)par2EntityLivingBase, 0.0f, 1.0, 0.0);
    }
}
