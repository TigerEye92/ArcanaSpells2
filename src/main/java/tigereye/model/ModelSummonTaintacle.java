package tigereye.model;
//
//import net.minecraft.client.model.*;
//import net.minecraft.entity.*;
//import tigereye.entity.*;
//import net.minecraft.util.*;
//import org.lwjgl.opengl.*;
//
//public class ModelSummonTaintacle extends ModelBase
//{
//    public ModelRenderer tentacle;
//    public ModelRenderer[] tents;
//    public ModelRenderer orb;
//    private int length;
//    
//    public ModelSummonTaintacle(final int length) {
//        this.tentacle = new ModelRendererSummonTaintacle(this);
//        this.orb = new ModelRendererSummonTaintacle(this);
//        this.length = 10;
//        final int var3 = 0;
//        this.length = length;
//        this.textureHeight = 64;
//        this.textureWidth = 64;
//        (this.tentacle = new ModelRendererSummonTaintacle(this, 0, 0)).addBox(-4.0f, -4.0f, -4.0f, 8, 8, 8);
//        this.tentacle.rotationPointX = 0.0f;
//        this.tentacle.rotationPointZ = 0.0f;
//        this.tentacle.rotationPointY = 12.0f;
//        this.tents = new ModelRendererSummonTaintacle[length];
//        for (int k = 0; k < length - 1; ++k) {
//            (this.tents[k] = new ModelRendererSummonTaintacle(this, 0, 16)).addBox(-4.0f, -4.0f, -4.0f, 8, 8, 8);
//            this.tents[k].rotationPointY = -8.0f;
//            if (k == 0) {
//                this.tentacle.addChild(this.tents[k]);
//            }
//            else {
//                this.tents[k - 1].addChild(this.tents[k]);
//            }
//        }
//        (this.orb = new ModelRendererSummonTaintacle(this, 0, 56)).addBox(-2.0f, -2.0f, -2.0f, 4, 4, 4);
//        this.orb.rotationPointY = -8.0f;
//        this.tents[length - 2].addChild(this.orb);
//        (this.tents[length - 1] = new ModelRendererSummonTaintacle(this, 0, 32)).addBox(-6.0f, -6.0f, -6.0f, 12, 12, 12);
//        this.tents[length - 1].rotationPointY = -8.0f;
//        this.tents[length - 2].addChild(this.tents[length - 1]);
//    }
//    
//    public void setRotationAngles(final float par1, final float par2, final float par3, final float par4, final float par5, final float par6, final Entity entity) {
//        boolean agi = false;
//        float flail = 0.0f;
//        int ht = 0;
//        int at = 0;
//        if (entity instanceof EntitySummonTaintacle) {
//            final EntitySummonTaintacle tentacle = (EntitySummonTaintacle)entity;
//            agi = tentacle.getAgitationState();
//            flail = tentacle.flailIntensity;
//            ht = tentacle.hurtTime;
//            at = tentacle.attackTime;
//        }
//        final float mod = par6 * 0.2f;
//        final float fs = agi ? 3.0f : (1.0f + (agi ? mod : (-mod)));
//        final float fi = flail + ((ht > 0 || at > 0) ? mod : (-mod));
//        this.tentacle.rotateAngleX = 0.0f;
//        for (int k = 0; k < this.length - 1; ++k) {
//            this.tents[k].rotateAngleX = 0.15f * fi * MathHelper.sin(par3 * 0.1f * fs - k / 2.0f);
//            this.tents[k].rotateAngleZ = 0.1f / fi * MathHelper.sin(par3 * 0.15f - k / 2.0f);
//        }
//    }
//    
//    public void render(final Entity par1Entity, final float par2, final float par3, final float par4, final float par5, final float par6, final float par7) {
//        this.setRotationAngles(par2, par3, par4, par5, par6, par7, par1Entity);
//        GL11.glPushMatrix();
//        GL11.glEnable(3042);
//        GL11.glBlendFunc(770, 771);
//        float height = 0.0f;
//        final float hc = par1Entity.height * 10.0f;
//        if (par1Entity.ticksExisted < hc) {
//            height = (hc - par1Entity.ticksExisted) / hc * par1Entity.height;
//        }
//        GL11.glTranslatef(0.0f, ((par1Entity.height == 3.0f) ? 0.6f : 1.2f) + height, 0.0f);
//        GL11.glScalef(par1Entity.height / 3.0f, par1Entity.height / 3.0f, par1Entity.height / 3.0f);
//        ((ModelRendererSummonTaintacle)this.tentacle).render(par7);
//        GL11.glDisable(3042);
//        GL11.glPopMatrix();
//    }
//}
import net.minecraft.client.model.*;
import net.minecraft.entity.*;

import net.minecraft.util.*;
import tigereye.entity.EntitySummonTaintacle;

import org.lwjgl.opengl.*;

public class ModelSummonTaintacle extends ModelBase
{
    public ModelRenderer tentacle;
    public ModelRenderer[] tents;
    public ModelRenderer orb;
    private int length;
    
    public ModelSummonTaintacle(final int length) {
        this.tentacle = new ModelRendererSummonTaintacle(this);
        this.orb = new ModelRendererSummonTaintacle(this);
        this.length = 10;
        final int var3 = 0;
        this.length = length;
        this.textureHeight = 64;
        this.textureWidth = 64;
        (this.tentacle = new ModelRendererSummonTaintacle(this, 0, 0)).addBox(-4.0f, -4.0f, -4.0f, 8, 8, 8);
        this.tentacle.rotationPointX = 0.0f;
        this.tentacle.rotationPointZ = 0.0f;
        this.tentacle.rotationPointY = 12.0f;
        this.tents = new ModelRendererSummonTaintacle[length];
        for (int k = 0; k < length - 1; ++k) {
            (this.tents[k] = new ModelRendererSummonTaintacle(this, 0, 16)).addBox(-4.0f, -4.0f, -4.0f, 8, 8, 8);
            this.tents[k].rotationPointY = -8.0f;
            if (k == 0) {
                this.tentacle.addChild(this.tents[k]);
            }
            else {
                this.tents[k - 1].addChild(this.tents[k]);
            }
        }
        (this.orb = new ModelRendererSummonTaintacle(this, 0, 56)).addBox(-2.0f, -2.0f, -2.0f, 4, 4, 4);
        this.orb.rotationPointY = -8.0f;
        this.tents[length - 2].addChild(this.orb);
        (this.tents[length - 1] = new ModelRendererSummonTaintacle(this, 0, 32)).addBox(-6.0f, -6.0f, -6.0f, 12, 12, 12);
        this.tents[length - 1].rotationPointY = -8.0f;
        this.tents[length - 2].addChild(this.tents[length - 1]);
    }
    
    public void setRotationAngles(final float par1, final float par2, final float par3, final float par4, final float par5, final float par6, final Entity entity) {
        boolean agi = false;
        float flail = 0.0f;
        int ht = 0;
        int at = 0;
        if (entity instanceof EntitySummonTaintacle) {
            final EntitySummonTaintacle tentacle = (EntitySummonTaintacle)entity;
            agi = tentacle.getAgitationState();
            flail = tentacle.flailIntensity;
            ht = tentacle.hurtTime;
            at = tentacle.attackTime;
        }
        final float mod = par6 * 0.2f;
        final float fs = agi ? 3.0f : (1.0f + (agi ? mod : (-mod)));
        final float fi = flail + ((ht > 0 || at > 0) ? mod : (-mod));
        this.tentacle.rotateAngleX = 0.0f;
        for (int k = 0; k < this.length - 1; ++k) {
            this.tents[k].rotateAngleX = 0.15f * fi * MathHelper.sin(par3 * 0.1f * fs - k / 2.0f);
            this.tents[k].rotateAngleZ = 0.1f / fi * MathHelper.sin(par3 * 0.15f - k / 2.0f);
        }
    }
    
    public void render(final Entity par1Entity, final float par2, final float par3, final float par4, final float par5, final float par6, final float par7) {
        this.setRotationAngles(par2, par3, par4, par5, par6, par7, par1Entity);
        GL11.glPushMatrix();
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 771);
        float height = 0.0f;
        final float hc = par1Entity.height * 10.0f;
        if (par1Entity.ticksExisted < hc) {
            height = (hc - par1Entity.ticksExisted) / hc * par1Entity.height;
        }
        GL11.glTranslatef(0.0f, ((par1Entity.height == 3.0f) ? 0.6f : 1.2f) + height, 0.0f);
        GL11.glScalef(par1Entity.height / 3.0f, par1Entity.height / 3.0f, par1Entity.height / 3.0f);
        ((ModelRendererSummonTaintacle)this.tentacle).render(par7, 0.88f);
        GL11.glDisable(3042);
        GL11.glPopMatrix();
    }
}