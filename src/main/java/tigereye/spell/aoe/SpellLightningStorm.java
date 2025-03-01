package tigereye.spell.aoe;

import net.minecraft.world.*;
import net.minecraft.entity.player.*;
import net.minecraft.entity.*;
import net.minecraft.entity.effect.*;

public class SpellLightningStorm extends SpellAoE
{
    public SpellLightningStorm(final int par1) {
        super(par1);
        this.setName("lightningstorm");
    }
    
    @Override
    public int getManaCost() {
        return 8;
    }
    
    @Override
    public void castSpell(final short par1Level, final World par2World, final EntityPlayer par3EntityPlayer) {
        par2World.playSoundAtEntity((Entity)par3EntityPlayer, this.getSoundName(), 1.0f, 1.0f);
        super.castSpell(par1Level, par2World, par3EntityPlayer);
    }
    
    @Override
    protected void affectEntity(final World par1World, final EntityLivingBase par2EntityLivingBase) {
        par1World.addWeatherEffect((Entity)new EntityLightningBolt(par1World, par2EntityLivingBase.posX, par2EntityLivingBase.posY, par2EntityLivingBase.posZ));
        par2EntityLivingBase.knockBack((Entity)par2EntityLivingBase, 0.0f, 1.0, 0.0);
    }
}
