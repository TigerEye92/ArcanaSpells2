package tigereye.model;

import net.minecraft.client.model.*;
import net.minecraft.entity.*;

public class ModelIceGiant extends ModelBase
{
    public ModelRenderer iceGiantRightArm;
    public ModelRenderer iceGiantLeftArm;
    public ModelRenderer iceGiantBody0;
    public ModelRenderer iceGiantBody1;
    public ModelRenderer iceGiantHead;
    public ModelRenderer iceGiantRightLeg;
    public ModelRenderer iceGiantLeftLeg;
    public ModelRenderer iceGiantRightArmChild;
    public ModelRenderer iceGiantRightArmChild_1;
    public ModelRenderer iceGiantLeftArmChild;
    public ModelRenderer iceGiantLeftArmChild_1;
    public ModelRenderer iceGiantBodyChild;
    public ModelRenderer iceGiantBodyChild_1;
    public ModelRenderer iceGiantBodyChild_2;
    public ModelRenderer iceGiantBodyChild_3;
    public ModelRenderer iceGiantBodyChild_4;
    public ModelRenderer iceGiantHeadChild;
    public ModelRenderer iceGiantHeadChild_1;
    public ModelRenderer iceGiantHeadChild_2;
    public ModelRenderer iceGiantHeadChild_3;
    public ModelRenderer iceGiantHeadChild_4;
    public ModelRenderer iceGiantHeadChild_5;
    public ModelRenderer iceGiantHeadChild_6;
    
    public ModelIceGiant() {
        this.textureWidth = 128;
        this.textureHeight = 128;
        (this.iceGiantBody1 = new ModelRenderer((ModelBase)this, 0, 70)).setRotationPoint(0.0f, -7.0f, 0.0f);
        this.iceGiantBody1.addBox(-4.5f, 10.0f, -3.0f, 9, 5, 6, 0.5f);
        (this.iceGiantRightArmChild_1 = new ModelRenderer((ModelBase)this, 0, 0)).setRotationPoint(-12.0f, 3.0f, 0.0f);
        this.iceGiantRightArmChild_1.addBox(-2.0f, -2.0f, -2.0f, 4, 4, 4, 0.0f);
        this.setRotateAngle(this.iceGiantRightArmChild_1, 0.7853982f, 0.0f, 0.9598885f);
        (this.iceGiantBody0 = new ModelRenderer((ModelBase)this, 0, 40)).setRotationPoint(0.0f, -7.0f, 0.0f);
        this.iceGiantBody0.addBox(-9.0f, -2.0f, -6.0f, 18, 12, 11, 0.0f);
        (this.iceGiantBodyChild_3 = new ModelRenderer((ModelBase)this, 32, 69)).setRotationPoint(0.0f, 3.0f, -4.0f);
        this.iceGiantBodyChild_3.addBox(-3.0f, -3.0f, -3.0f, 6, 6, 6, 0.0f);
        this.setRotateAngle(this.iceGiantBodyChild_3, -0.7853982f, -0.6109078f, 1.5707964f);
        (this.iceGiantHeadChild_5 = new ModelRenderer((ModelBase)this, 0, 0)).setRotationPoint(-4.0f, -6.0f, 5.0f);
        this.iceGiantHeadChild_5.addBox(-2.0f, -2.0f, -2.0f, 4, 4, 4, 0.0f);
        this.setRotateAngle(this.iceGiantHeadChild_5, -1.0297197f, -0.7703263f, 1.7426893f);
        this.iceGiantRightLeg = new ModelRenderer((ModelBase)this, 60, 0);
        this.iceGiantRightLeg.mirror = true;
        this.iceGiantRightLeg.setRotationPoint(5.0f, 11.0f, 0.0f);
        this.iceGiantRightLeg.addBox(-3.5f, -3.0f, -3.0f, 6, 16, 5, 0.0f);
        (this.iceGiantBodyChild = new ModelRenderer((ModelBase)this, 32, 69)).setRotationPoint(-4.0f, 3.0f, 3.0f);
        this.iceGiantBodyChild.addBox(-3.0f, -3.0f, -3.0f, 6, 6, 6, 0.0f);
        this.setRotateAngle(this.iceGiantBodyChild, 0.941521f, 0.5081364f, 1.0115681f);
        (this.iceGiantBodyChild_1 = new ModelRenderer((ModelBase)this, 32, 69)).setRotationPoint(4.0f, 3.0f, 3.0f);
        this.iceGiantBodyChild_1.addBox(-3.0f, -3.0f, -3.0f, 6, 6, 6, 0.0f);
        this.setRotateAngle(this.iceGiantBodyChild_1, 0.9415698f, -0.5080775f, -1.0116609f);
        (this.iceGiantHeadChild_1 = new ModelRenderer((ModelBase)this, 0, 0)).setRotationPoint(4.0f, -9.0f, 1.0f);
        this.iceGiantHeadChild_1.addBox(0.0f, -4.0f, 0.0f, 4, 4, 4, 0.0f);
        this.setRotateAngle(this.iceGiantHeadChild_1, -0.02729957f, 0.7636954f, -0.7646218f);
        (this.iceGiantLeftArmChild = new ModelRenderer((ModelBase)this, 0, 0)).setRotationPoint(11.0f, -1.0f, 0.0f);
        this.iceGiantLeftArmChild.addBox(-2.0f, -2.0f, -2.0f, 4, 4, 4, 0.0f);
        this.setRotateAngle(this.iceGiantLeftArmChild, 1.8386672f, -0.54658943f, -1.2831051f);
        (this.iceGiantRightArmChild = new ModelRenderer((ModelBase)this, 0, 0)).setRotationPoint(-11.0f, -1.0f, 0.0f);
        this.iceGiantRightArmChild.addBox(-2.0f, -2.0f, -2.0f, 4, 4, 4, 0.0f);
        this.setRotateAngle(this.iceGiantRightArmChild, 1.8385788f, 0.5466727f, 1.283012f);
        (this.iceGiantHeadChild = new ModelRenderer((ModelBase)this, 0, 0)).setRotationPoint(-4.0f, -3.0f, -4.0f);
        this.iceGiantHeadChild.addBox(-4.0f, -4.0f, 0.0f, 4, 4, 4, 0.0f);
        this.setRotateAngle(this.iceGiantHeadChild, -0.5464409f, -0.29087415f, 1.0256622f);
        (this.iceGiantHeadChild_2 = new ModelRenderer((ModelBase)this, 0, 0)).setRotationPoint(4.0f, -6.0f, 5.0f);
        this.iceGiantHeadChild_2.addBox(-2.0f, -2.0f, -2.0f, 4, 4, 4, 0.0f);
        this.setRotateAngle(this.iceGiantHeadChild_2, -0.54107267f, -0.77030987f, 1.3989034f);
        (this.iceGiantRightArm = new ModelRenderer((ModelBase)this, 60, 21)).setRotationPoint(0.0f, -7.0f, 0.0f);
        this.iceGiantRightArm.addBox(-13.0f, -2.5f, -3.0f, 4, 30, 6, 0.0f);
        (this.iceGiantLeftLeg = new ModelRenderer((ModelBase)this, 37, 0)).setRotationPoint(-4.0f, 11.0f, 0.0f);
        this.iceGiantLeftLeg.addBox(-3.5f, -3.0f, -3.0f, 6, 16, 5, 0.0f);
        (this.iceGiantLeftArmChild_1 = new ModelRenderer((ModelBase)this, 0, 0)).setRotationPoint(12.0f, 3.0f, 0.0f);
        this.iceGiantLeftArmChild_1.addBox(-2.0f, -2.0f, -2.0f, 4, 4, 4, 0.0f);
        this.setRotateAngle(this.iceGiantLeftArmChild_1, 0.7853982f, 0.0f, -0.95995283f);
        (this.iceGiantBodyChild_4 = new ModelRenderer((ModelBase)this, 0, 0)).setRotationPoint(-6.0f, 5.0f, -5.0f);
        this.iceGiantBodyChild_4.addBox(-2.0f, -2.0f, -2.0f, 4, 4, 4, 0.0f);
        this.setRotateAngle(this.iceGiantBodyChild_4, 2.4082177f, 0.65425724f, 1.3959057f);
        (this.iceGiantHead = new ModelRenderer((ModelBase)this, 0, 10)).setRotationPoint(0.0f, -7.0f, -1.0f);
        this.iceGiantHead.addBox(-6.0f, -14.0f, -6.5f, 12, 12, 12, 0.0f);
        (this.iceGiantLeftArm = new ModelRenderer((ModelBase)this, 60, 58)).setRotationPoint(0.0f, -7.0f, 0.0f);
        this.iceGiantLeftArm.addBox(9.0f, -2.5f, -3.0f, 4, 30, 6, 0.0f);
        (this.iceGiantHeadChild_6 = new ModelRenderer((ModelBase)this, 0, 0)).setRotationPoint(0.0f, -13.0f, 5.0f);
        this.iceGiantHeadChild_6.addBox(-2.0f, -2.0f, -2.0f, 4, 4, 4, 0.0f);
        this.setRotateAngle(this.iceGiantHeadChild_6, 2.3560588f, 0.7853981f, 1.5707004f);
        (this.iceGiantHeadChild_4 = new ModelRenderer((ModelBase)this, 0, 0)).setRotationPoint(4.0f, -3.0f, -4.0f);
        this.iceGiantHeadChild_4.addBox(0.0f, -4.0f, 0.0f, 4, 4, 4, 0.0f);
        this.setRotateAngle(this.iceGiantHeadChild_4, 0.3754078f, -0.9521125f, 0.18120287f);
        (this.iceGiantBodyChild_2 = new ModelRenderer((ModelBase)this, 0, 0)).setRotationPoint(6.0f, 5.0f, -5.0f);
        this.iceGiantBodyChild_2.addBox(-2.0f, -2.0f, -2.0f, 4, 4, 4, 0.0f);
        this.setRotateAngle(this.iceGiantBodyChild_2, 2.408331f, -0.6541652f, -1.3959987f);
        (this.iceGiantHeadChild_3 = new ModelRenderer((ModelBase)this, 0, 0)).setRotationPoint(-4.0f, -9.0f, 1.0f);
        this.iceGiantHeadChild_3.addBox(-4.0f, -4.0f, 0.0f, 4, 4, 4, 0.0f);
        this.setRotateAngle(this.iceGiantHeadChild_3, -0.027358165f, -0.7637484f, 0.7646577f);
        this.iceGiantRightArm.addChild(this.iceGiantRightArmChild_1);
        this.iceGiantBody0.addChild(this.iceGiantBodyChild_3);
        this.iceGiantHead.addChild(this.iceGiantHeadChild_5);
        this.iceGiantBody0.addChild(this.iceGiantBodyChild);
        this.iceGiantBody0.addChild(this.iceGiantBodyChild_1);
        this.iceGiantHead.addChild(this.iceGiantHeadChild_1);
        this.iceGiantLeftArm.addChild(this.iceGiantLeftArmChild);
        this.iceGiantRightArm.addChild(this.iceGiantRightArmChild);
        this.iceGiantHead.addChild(this.iceGiantHeadChild);
        this.iceGiantHead.addChild(this.iceGiantHeadChild_2);
        this.iceGiantLeftArm.addChild(this.iceGiantLeftArmChild_1);
        this.iceGiantBody0.addChild(this.iceGiantBodyChild_4);
        this.iceGiantHead.addChild(this.iceGiantHeadChild_6);
        this.iceGiantHead.addChild(this.iceGiantHeadChild_4);
        this.iceGiantBody0.addChild(this.iceGiantBodyChild_2);
        this.iceGiantHead.addChild(this.iceGiantHeadChild_3);
    }
    
    public void render(final Entity entity, final float f, final float f1, final float f2, final float f3, final float f4, final float f5) {
        this.iceGiantBody1.render(f5);
        this.iceGiantBody0.render(f5);
        this.iceGiantRightLeg.render(f5);
        this.iceGiantRightArm.render(f5);
        this.iceGiantLeftLeg.render(f5);
        this.iceGiantHead.render(f5);
        this.iceGiantLeftArm.render(f5);
    }
    
    public void setRotateAngle(final ModelRenderer modelRenderer, final float x, final float y, final float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
