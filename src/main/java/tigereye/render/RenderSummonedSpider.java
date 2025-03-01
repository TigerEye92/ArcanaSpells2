package tigereye.render;

import net.minecraft.client.renderer.entity.*;
import cpw.mods.fml.relauncher.*;
import net.minecraft.util.*;
import net.minecraft.client.model.*;
import org.lwjgl.opengl.*;
import net.minecraft.client.renderer.*;
import net.minecraft.entity.*;

@SideOnly(Side.CLIENT)
public class RenderSummonedSpider extends RenderLiving
{
    private static final ResourceLocation spiderEyesTextures;
    private static final ResourceLocation spiderTextures;
    
    public RenderSummonedSpider() {
        super((ModelBase)new ModelSpider(), 1.0f);
        this.setRenderPassModel((ModelBase)new ModelSpider());
    }
    
    protected float getDeathMaxRotation(final EntityLivingBase par1EntityLivingBase) {
        return 180.0f;
    }
    
    protected int shouldRenderPass(final EntityLivingBase par1EntityLivingBase, final int par2, final float par3) {
        if (par2 != 0) {
            return -1;
        }
        this.bindTexture(RenderSummonedSpider.spiderEyesTextures);
        GL11.glEnable(3042);
        GL11.glDisable(3008);
        GL11.glBlendFunc(1, 1);
        if (par1EntityLivingBase.isInvisible()) {
            GL11.glDepthMask(false);
        }
        else {
            GL11.glDepthMask(true);
        }
        final char c0 = '\uf0f0';
        final int j = 61680;
        final int k = 0;
        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 61680.0f, 0.0f);
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        return 1;
    }
    
    protected ResourceLocation getEntityTexture(final Entity par1Entity) {
        return RenderSummonedSpider.spiderTextures;
    }
    
    static {
        spiderEyesTextures = new ResourceLocation("textures/entity/spider_eyes.png");
        spiderTextures = new ResourceLocation("textures/entity/spider/spider.png");
    }
}
