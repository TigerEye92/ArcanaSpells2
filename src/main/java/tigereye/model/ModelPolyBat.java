package tigereye.model;

import cpw.mods.fml.relauncher.*;
import net.minecraft.client.model.*;
import net.minecraft.entity.*;
import net.minecraft.util.*;

@SideOnly(Side.CLIENT)
public class ModelPolyBat extends ModelBase
{
    private ModelRenderer batHead;
    private ModelRenderer batBody;
    private ModelRenderer batRightWing;
    private ModelRenderer batLeftWing;
    private ModelRenderer batOuterRightWing;
    private ModelRenderer batOuterLeftWing;
    
    public ModelPolyBat() {
        this.textureWidth = 64;
        this.textureHeight = 64;
        (this.batHead = new ModelRenderer((ModelBase)this, 0, 0)).addBox(-3.0f, -3.0f, -3.0f, 6, 6, 6);
        final ModelRenderer modelrenderer = new ModelRenderer((ModelBase)this, 24, 0);
        modelrenderer.addBox(-4.0f, -6.0f, -2.0f, 3, 4, 1);
        this.batHead.addChild(modelrenderer);
        final ModelRenderer modelrenderer2 = new ModelRenderer((ModelBase)this, 24, 0);
        modelrenderer2.mirror = true;
        modelrenderer2.addBox(1.0f, -6.0f, -2.0f, 3, 4, 1);
        this.batHead.addChild(modelrenderer2);
        (this.batBody = new ModelRenderer((ModelBase)this, 0, 16)).addBox(-3.0f, 4.0f, -3.0f, 6, 12, 6);
        this.batBody.setTextureOffset(0, 34).addBox(-5.0f, 16.0f, 0.0f, 10, 6, 1);
        (this.batRightWing = new ModelRenderer((ModelBase)this, 42, 0)).addBox(-12.0f, 1.0f, 1.5f, 10, 16, 1);
        (this.batOuterRightWing = new ModelRenderer((ModelBase)this, 24, 16)).setRotationPoint(-12.0f, 1.0f, 1.5f);
        this.batOuterRightWing.addBox(-8.0f, 1.0f, 0.0f, 8, 12, 1);
        this.batLeftWing = new ModelRenderer((ModelBase)this, 42, 0);
        this.batLeftWing.mirror = true;
        this.batLeftWing.addBox(2.0f, 1.0f, 1.5f, 10, 16, 1);
        this.batOuterLeftWing = new ModelRenderer((ModelBase)this, 24, 16);
        this.batOuterLeftWing.mirror = true;
        this.batOuterLeftWing.setRotationPoint(12.0f, 1.0f, 1.5f);
        this.batOuterLeftWing.addBox(0.0f, 1.0f, 0.0f, 8, 12, 1);
        this.batBody.addChild(this.batRightWing);
        this.batBody.addChild(this.batLeftWing);
        this.batRightWing.addChild(this.batOuterRightWing);
        this.batLeftWing.addChild(this.batOuterLeftWing);
    }
    
    public int getBatSize() {
        return 36;
    }
    
    public void render(final Entity par1Entity, final float par2, final float par3, final float par4, final float par5, final float par6, final float par7) {
        this.batHead.rotateAngleX = par6 / 57.295776f;
        this.batHead.rotateAngleY = par5 / 57.295776f;
        this.batHead.rotateAngleZ = 0.0f;
        this.batHead.setRotationPoint(0.0f, 0.0f, 0.0f);
        this.batRightWing.setRotationPoint(0.0f, 0.0f, 0.0f);
        this.batLeftWing.setRotationPoint(0.0f, 0.0f, 0.0f);
        this.batBody.rotateAngleX = 0.7853982f + MathHelper.cos(par4 * 0.1f) * 0.15f;
        this.batBody.rotateAngleY = 0.0f;
        this.batRightWing.rotateAngleY = MathHelper.cos(par4 * 1.3f) * 3.1415927f * 0.25f;
        this.batLeftWing.rotateAngleY = -this.batRightWing.rotateAngleY;
        this.batOuterRightWing.rotateAngleY = this.batRightWing.rotateAngleY * 0.5f;
        this.batOuterLeftWing.rotateAngleY = -this.batRightWing.rotateAngleY * 0.5f;
        this.batHead.render(par7);
        this.batBody.render(par7);
    }
}
