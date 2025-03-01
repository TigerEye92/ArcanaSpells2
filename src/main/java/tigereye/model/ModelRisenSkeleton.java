package tigereye.model;

import cpw.mods.fml.relauncher.*;
import net.minecraft.client.model.*;
import net.minecraft.entity.*;

@SideOnly(Side.CLIENT)
public class ModelRisenSkeleton extends ModelZombie
{
    public ModelRisenSkeleton() {
        this(0.0f);
    }
    
    public ModelRisenSkeleton(final float par1) {
        super(par1, 0.0f, 64, 32);
        (this.bipedRightArm = new ModelRenderer((ModelBase)this, 40, 16)).addBox(-1.0f, -2.0f, -1.0f, 2, 12, 2, par1);
        this.bipedRightArm.setRotationPoint(-5.0f, 2.0f, 0.0f);
        this.bipedLeftArm = new ModelRenderer((ModelBase)this, 40, 16);
        this.bipedLeftArm.mirror = true;
        this.bipedLeftArm.addBox(-1.0f, -2.0f, -1.0f, 2, 12, 2, par1);
        this.bipedLeftArm.setRotationPoint(5.0f, 2.0f, 0.0f);
        (this.bipedRightLeg = new ModelRenderer((ModelBase)this, 0, 16)).addBox(-1.0f, 0.0f, -1.0f, 2, 12, 2, par1);
        this.bipedRightLeg.setRotationPoint(-2.0f, 12.0f, 0.0f);
        this.bipedLeftLeg = new ModelRenderer((ModelBase)this, 0, 16);
        this.bipedLeftLeg.mirror = true;
        this.bipedLeftLeg.addBox(-1.0f, 0.0f, -1.0f, 2, 12, 2, par1);
        this.bipedLeftLeg.setRotationPoint(2.0f, 12.0f, 0.0f);
    }
    
    public void setLivingAnimations(final EntityLivingBase par1EntityLivingBase, final float par2, final float par3, final float par4) {
        this.aimedBow = true;
        super.setLivingAnimations(par1EntityLivingBase, par2, par3, par4);
    }
}
