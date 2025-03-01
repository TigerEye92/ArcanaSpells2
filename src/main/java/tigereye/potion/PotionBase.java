package tigereye.potion;

import net.minecraft.potion.*;
import net.minecraft.util.*;
import net.minecraft.entity.*;
import net.minecraft.entity.player.*;
import tigereye.misc.*;
import net.minecraft.client.*;
import cpw.mods.fml.relauncher.*;

public class PotionBase extends Potion
{
    private static final ResourceLocation resourceLoc;
    
    public PotionBase(final int par1Id, final boolean par2IsBadEffect, final int par3LiquidColor) {
        super(par1Id, par2IsBadEffect, par3LiquidColor);
    }
    
    public void performEffect(final EntityLivingBase affected, final int amplifier) {
        if (affected instanceof EntityPlayer) {
            if (this.id == ModPotions.manaRegen.id) {
                final ManaProperties prop = ManaProperties.get((EntityPlayer)affected);
                if (prop != null) {
                    prop.replenishMana(1);
                }
            }
            else if (this.id == ModPotions.mana.id) {
                final ManaProperties prop = ManaProperties.get((EntityPlayer)affected);
                if (prop != null) {
                    prop.replenishMana(Math.max(4 << amplifier, 0));
                }
            }
        }
        else {
            super.performEffect(affected, amplifier);
        }
    }
    
    public void affectEntity(final EntityLivingBase thrower, final EntityLivingBase victim, final int amplifier, final double distanceModifier) {
        if (victim instanceof EntityPlayer) {
            if (this.id == ModPotions.mana.id) {
                final ManaProperties prop = ManaProperties.get((EntityPlayer)victim);
                if (prop != null) {
                    prop.replenishMana((int)(distanceModifier * (4 << amplifier) + 0.5));
                }
            }
        }
        else {
            super.affectEntity(thrower, victim, amplifier, distanceModifier);
        }
    }
    
    public boolean isReady(final int duration, final int amplifier) {
        if (this.id == ModPotions.manaRegen.id) {
            final int k = 50 >> amplifier;
            return k <= 0 || duration % k == 0;
        }
        if (this.id == ModPotions.mana.id) {
            return duration >= 1;
        }
        return super.isReady(duration, amplifier);
    }
    
    public boolean isInstant() {
        return this.id == ModPotions.mana.id;
    }
    
    public Potion setIconIndex(final int par1, final int par2) {
        super.setIconIndex(par1, par2);
        return this;
    }
    
    @SideOnly(Side.CLIENT)
    public boolean hasStatusIcon() {
        Minecraft.getMinecraft().renderEngine.bindTexture(PotionBase.resourceLoc);
        return true;
    }
    
    static {
        resourceLoc = new ResourceLocation("tigereye:textures/status_icons.png");
    }
}
