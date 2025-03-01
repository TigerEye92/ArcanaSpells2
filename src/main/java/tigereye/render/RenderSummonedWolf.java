package tigereye.render;

import net.minecraft.client.renderer.entity.*;
import cpw.mods.fml.relauncher.*;
import net.minecraft.util.*;
import net.minecraft.client.model.*;
import tigereye.entity.*;
import org.lwjgl.opengl.*;
import net.minecraft.entity.*;

@SideOnly(Side.CLIENT)
public class RenderSummonedWolf extends RenderLiving
{
    private static final ResourceLocation wolfTextures;
    
    public RenderSummonedWolf(final ModelBase par1ModelBase, final ModelBase par2ModelBase, final float par3) {
        super(par1ModelBase, par3);
        this.setRenderPassModel(par2ModelBase);
    }
    
    protected int shouldRenderPass(final EntityLivingBase par1EntityLivingBase, final int par2, final float par3) {
        final EntitySummonedWolf wolf = (EntitySummonedWolf)par1EntityLivingBase;
        if (par2 == 0 && wolf.getWolfShaking()) {
            final float f1 = wolf.getBrightness(par3) * wolf.getShadingWhileShaking(par3);
            this.bindTexture(RenderSummonedWolf.wolfTextures);
            GL11.glColor3f(f1, f1, f1);
            return 1;
        }
        return -1;
    }
    
    protected float handleRotationFloat(final EntityLivingBase par1EntityLivingBase, final float par2) {
        return ((EntitySummonedWolf)par1EntityLivingBase).getTailRotation();
    }
    
    protected ResourceLocation getEntityTexture(final Entity par1Entity) {
        return RenderSummonedWolf.wolfTextures;
    }
    
    static {
        wolfTextures = new ResourceLocation("textures/entity/wolf/wolf.png");
    }
}
