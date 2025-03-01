package tigereye.render;

import cpw.mods.fml.relauncher.*;
import tigereye.model.*;
import net.minecraft.client.model.*;
import net.minecraft.client.renderer.entity.*;
import net.minecraft.entity.*;
import org.lwjgl.opengl.*;
import net.minecraft.util.*;
import net.minecraft.client.renderer.*;
import org.apache.logging.log4j.*;

@SideOnly(Side.CLIENT)
public class RenderPolyBat extends RendererLivingEntity
{
    private static final Logger logger;
    private static final ResourceLocation RES_ITEM_GLINT;
    private static final ResourceLocation batTextures;
    private int renderedBatSize;
    
    public RenderPolyBat() {
        super((ModelBase)new ModelPolyBat(), 0.25f);
        this.renderedBatSize = ((ModelPolyBat)this.mainModel).getBatSize();
        this.renderManager = RenderManager.instance;
    }
    
    protected ResourceLocation getEntityTexture(final Entity par1Entity) {
        return RenderPolyBat.batTextures;
    }
    
    protected void preRenderCallback(final EntityLivingBase par1EntityLivingBase, final float par2) {
        GL11.glScalef(0.35f, 0.35f, 0.35f);
    }
    
    public void doRender(final EntityLivingBase par1EntityLivingBase, final double par2, final double par4, final double par6, final float par8, final float par9) {
        final int i = ((ModelPolyBat)this.mainModel).getBatSize();
        if (i != this.renderedBatSize) {
            this.renderedBatSize = i;
            this.mainModel = new ModelPolyBat();
        }
        GL11.glPushMatrix();
        GL11.glDisable(2884);
        this.mainModel.onGround = this.renderSwingProgress(par1EntityLivingBase, par9);
        if (this.renderPassModel != null) {
            this.renderPassModel.onGround = this.mainModel.onGround;
        }
        this.mainModel.isRiding = par1EntityLivingBase.isRiding();
        if (this.renderPassModel != null) {
            this.renderPassModel.isRiding = this.mainModel.isRiding;
        }
        this.mainModel.isChild = par1EntityLivingBase.isChild();
        if (this.renderPassModel != null) {
            this.renderPassModel.isChild = this.mainModel.isChild;
        }
        try {
            float f2 = this.interpolateRotation(par1EntityLivingBase.prevRenderYawOffset, par1EntityLivingBase.renderYawOffset, par9);
            final float f3 = this.interpolateRotation(par1EntityLivingBase.prevRotationYawHead, par1EntityLivingBase.rotationYawHead, par9);
            if (par1EntityLivingBase.isRiding() && par1EntityLivingBase.ridingEntity instanceof EntityLivingBase) {
                final EntityLivingBase entitylivingbase1 = (EntityLivingBase)par1EntityLivingBase.ridingEntity;
                f2 = this.interpolateRotation(entitylivingbase1.prevRenderYawOffset, entitylivingbase1.renderYawOffset, par9);
                float f4 = MathHelper.wrapAngleTo180_float(f3 - f2);
                if (f4 < -85.0f) {
                    f4 = -85.0f;
                }
                if (f4 >= 85.0f) {
                    f4 = 85.0f;
                }
                f2 = f3 - f4;
                if (f4 * f4 > 2500.0f) {
                    f2 += f4 * 0.2f;
                }
            }
            final float f5 = par1EntityLivingBase.prevRotationPitch + (par1EntityLivingBase.rotationPitch - par1EntityLivingBase.prevRotationPitch) * par9;
            this.renderLivingAt(par1EntityLivingBase, par2, par4, par6);
            float f4 = this.handleRotationFloat(par1EntityLivingBase, par9);
            this.rotateCorpse(par1EntityLivingBase, f4, f2, par9);
            final float f6 = 0.0625f;
            GL11.glEnable(32826);
            GL11.glScalef(-1.0f, -1.0f, 1.0f);
            this.preRenderCallback(par1EntityLivingBase, par9);
            GL11.glTranslatef(0.0f, -1.5078125f, 0.0f);
            float f7 = par1EntityLivingBase.prevLimbSwingAmount + (par1EntityLivingBase.limbSwingAmount - par1EntityLivingBase.prevLimbSwingAmount) * par9;
            float f8 = par1EntityLivingBase.limbSwing - par1EntityLivingBase.limbSwingAmount * (1.0f - par9);
            if (par1EntityLivingBase.isChild()) {
                f8 *= 3.0f;
            }
            if (f7 > 1.0f) {
                f7 = 1.0f;
            }
            GL11.glEnable(3008);
            this.mainModel.setLivingAnimations(par1EntityLivingBase, f8, f7, par9);
            this.renderModel(par1EntityLivingBase, f8, f7, f4, f3 - f2, f5, 0.0625f);
            for (int i2 = 0; i2 < 4; ++i2) {
                final int j = this.shouldRenderPass(par1EntityLivingBase, i2, par9);
                if (j > 0) {
                    this.renderPassModel.setLivingAnimations(par1EntityLivingBase, f8, f7, par9);
                    this.renderPassModel.render((Entity)par1EntityLivingBase, f8, f7, f4, f3 - f2, f5, 0.0625f);
                    if ((j & 0xF0) == 0x10) {
                        this.func_82408_c(par1EntityLivingBase, i2, par9);
                        this.renderPassModel.render((Entity)par1EntityLivingBase, f8, f7, f4, f3 - f2, f5, 0.0625f);
                    }
                    if ((j & 0xF) == 0xF) {
                        final float f9 = par1EntityLivingBase.ticksExisted + par9;
                        this.bindTexture(RenderPolyBat.RES_ITEM_GLINT);
                        GL11.glEnable(3042);
                        final float f10 = 0.5f;
                        GL11.glColor4f(0.5f, 0.5f, 0.5f, 1.0f);
                        GL11.glDepthFunc(514);
                        GL11.glDepthMask(false);
                        for (int k = 0; k < 2; ++k) {
                            GL11.glDisable(2896);
                            final float f11 = 0.76f;
                            GL11.glColor4f(0.38f, 0.19f, 0.608f, 1.0f);
                            GL11.glBlendFunc(768, 1);
                            GL11.glMatrixMode(5890);
                            GL11.glLoadIdentity();
                            final float f12 = f9 * (0.001f + k * 0.003f) * 20.0f;
                            final float f13 = 0.33333334f;
                            GL11.glScalef(0.33333334f, 0.33333334f, 0.33333334f);
                            GL11.glRotatef(30.0f - k * 60.0f, 0.0f, 0.0f, 1.0f);
                            GL11.glTranslatef(0.0f, f12, 0.0f);
                            GL11.glMatrixMode(5888);
                            this.renderPassModel.render((Entity)par1EntityLivingBase, f8, f7, f4, f3 - f2, f5, 0.0625f);
                        }
                        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
                        GL11.glMatrixMode(5890);
                        GL11.glDepthMask(true);
                        GL11.glLoadIdentity();
                        GL11.glMatrixMode(5888);
                        GL11.glEnable(2896);
                        GL11.glDisable(3042);
                        GL11.glDepthFunc(515);
                    }
                    GL11.glDisable(3042);
                    GL11.glEnable(3008);
                }
            }
            GL11.glDepthMask(true);
            this.renderEquippedItems(par1EntityLivingBase, par9);
            final float f14 = par1EntityLivingBase.getBrightness(par9);
            final int j = this.getColorMultiplier(par1EntityLivingBase, f14, par9);
            OpenGlHelper.setActiveTexture(OpenGlHelper.lightmapTexUnit);
            GL11.glDisable(3553);
            OpenGlHelper.setActiveTexture(OpenGlHelper.defaultTexUnit);
            if ((j >> 24 & 0xFF) > 0 || par1EntityLivingBase.hurtTime > 0 || par1EntityLivingBase.deathTime > 0) {
                GL11.glDisable(3553);
                GL11.glDisable(3008);
                GL11.glEnable(3042);
                GL11.glBlendFunc(770, 771);
                GL11.glDepthFunc(514);
                if (par1EntityLivingBase.hurtTime > 0 || par1EntityLivingBase.deathTime > 0) {
                    GL11.glColor4f(f14, 0.0f, 0.0f, 0.4f);
                    this.mainModel.render((Entity)par1EntityLivingBase, f8, f7, f4, f3 - f2, f5, 0.0625f);
                    for (int l = 0; l < 4; ++l) {
                        if (this.inheritRenderPass(par1EntityLivingBase, l, par9) >= 0) {
                            GL11.glColor4f(f14, 0.0f, 0.0f, 0.4f);
                            this.renderPassModel.render((Entity)par1EntityLivingBase, f8, f7, f4, f3 - f2, f5, 0.0625f);
                        }
                    }
                }
                if ((j >> 24 & 0xFF) > 0) {
                    final float f9 = (j >> 16 & 0xFF) / 255.0f;
                    final float f10 = (j >> 8 & 0xFF) / 255.0f;
                    final float f15 = (j & 0xFF) / 255.0f;
                    final float f11 = (j >> 24 & 0xFF) / 255.0f;
                    GL11.glColor4f(f9, f10, f15, f11);
                    this.mainModel.render((Entity)par1EntityLivingBase, f8, f7, f4, f3 - f2, f5, 0.0625f);
                    for (int i3 = 0; i3 < 4; ++i3) {
                        if (this.inheritRenderPass(par1EntityLivingBase, i3, par9) >= 0) {
                            GL11.glColor4f(f9, f10, f15, f11);
                            this.renderPassModel.render((Entity)par1EntityLivingBase, f8, f7, f4, f3 - f2, f5, 0.0625f);
                        }
                    }
                }
                GL11.glDepthFunc(515);
                GL11.glDisable(3042);
                GL11.glEnable(3008);
                GL11.glEnable(3553);
            }
            GL11.glDisable(32826);
        }
        catch (Exception exception) {
            RenderPolyBat.logger.error("Couldn't render entity", (Throwable)exception);
        }
        OpenGlHelper.setActiveTexture(OpenGlHelper.lightmapTexUnit);
        GL11.glEnable(3553);
        OpenGlHelper.setActiveTexture(OpenGlHelper.defaultTexUnit);
        GL11.glEnable(2884);
        GL11.glPopMatrix();
        this.passSpecialRender(par1EntityLivingBase, par2, par4, par6);
    }
    
    private float interpolateRotation(final float par1, final float par2, final float par3) {
        float f3;
        for (f3 = par2 - par1; f3 < -180.0f; f3 += 360.0f) {}
        while (f3 >= 180.0f) {
            f3 -= 360.0f;
        }
        return par1 + par3 * f3;
    }
    
    protected void rotateCorpse(final EntityLivingBase par1EntityLivingBase, final float par2, final float par3, final float par4) {
        GL11.glTranslatef(0.0f, MathHelper.cos(par2 * 0.3f) * 0.1f, 0.0f);
        super.rotateCorpse(par1EntityLivingBase, par2, par3, par4);
    }
    
    static {
        logger = LogManager.getLogger();
        RES_ITEM_GLINT = new ResourceLocation("textures/misc/enchanted_item_glint.png");
        batTextures = new ResourceLocation("textures/entity/bat.png");
    }
}
