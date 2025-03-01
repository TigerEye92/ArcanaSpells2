package tigereye.render;

import net.minecraft.client.renderer.entity.*;
import cpw.mods.fml.relauncher.*;
import net.minecraft.util.*;
import net.minecraft.client.model.*;
import tigereye.entity.*;
import org.lwjgl.opengl.*;
import net.minecraft.block.*;
import net.minecraft.client.renderer.*;
import net.minecraft.init.*;
import net.minecraft.item.*;
import net.minecraft.entity.*;

@SideOnly(Side.CLIENT)
public class RenderSummonedWitch extends RenderLiving
{
    private static final ResourceLocation witchTextures;
    private final ModelWitch witchModel;
    
    public RenderSummonedWitch() {
        super((ModelBase)new ModelWitch(0.0f), 0.5f);
        this.witchModel = (ModelWitch)this.mainModel;
    }
    
    protected void renderEquippedItems(final EntityLivingBase par1EntityLivingBase, final float par2) {
        final EntitySummonedWitch witch = (EntitySummonedWitch)par1EntityLivingBase;
        GL11.glColor3f(1.0f, 1.0f, 1.0f);
        super.renderEquippedItems((EntityLivingBase)witch, par2);
        final ItemStack itemstack = witch.getHeldItem();
        if (itemstack != null) {
            GL11.glPushMatrix();
            if (this.mainModel.isChild) {
                final float f1 = 0.5f;
                GL11.glTranslatef(0.0f, 0.625f, 0.0f);
                GL11.glRotatef(-20.0f, -1.0f, 0.0f, 0.0f);
                GL11.glScalef(0.5f, 0.5f, 0.5f);
            }
            this.witchModel.villagerNose.postRender(0.0625f);
            GL11.glTranslatef(-0.0625f, 0.53125f, 0.21875f);
            if (itemstack.getItem() instanceof ItemBlock && RenderBlocks.renderItemIn3d(Block.getBlockFromItem(itemstack.getItem()).getRenderType())) {
                float f1 = 0.5f;
                GL11.glTranslatef(0.0f, 0.1875f, -0.3125f);
                f1 *= 0.75f;
                GL11.glRotatef(20.0f, 1.0f, 0.0f, 0.0f);
                GL11.glRotatef(45.0f, 0.0f, 1.0f, 0.0f);
                GL11.glScalef(f1, -f1, f1);
            }
            else if (itemstack.getItem() == Items.bow) {
                final float f1 = 0.625f;
                GL11.glTranslatef(0.0f, 0.125f, 0.3125f);
                GL11.glRotatef(-20.0f, 0.0f, 1.0f, 0.0f);
                GL11.glScalef(0.625f, -0.625f, 0.625f);
                GL11.glRotatef(-100.0f, 1.0f, 0.0f, 0.0f);
                GL11.glRotatef(45.0f, 0.0f, 1.0f, 0.0f);
            }
            else if (itemstack.getItem().isFull3D()) {
                final float f1 = 0.625f;
                if (itemstack.getItem().shouldRotateAroundWhenRendering()) {
                    GL11.glRotatef(180.0f, 0.0f, 0.0f, 1.0f);
                    GL11.glTranslatef(0.0f, -0.125f, 0.0f);
                }
                this.func_82410_b();
                GL11.glScalef(0.625f, -0.625f, 0.625f);
                GL11.glRotatef(-100.0f, 1.0f, 0.0f, 0.0f);
                GL11.glRotatef(45.0f, 0.0f, 1.0f, 0.0f);
            }
            else {
                final float f1 = 0.375f;
                GL11.glTranslatef(0.25f, 0.1875f, -0.1875f);
                GL11.glScalef(0.375f, 0.375f, 0.375f);
                GL11.glRotatef(60.0f, 0.0f, 0.0f, 1.0f);
                GL11.glRotatef(-90.0f, 1.0f, 0.0f, 0.0f);
                GL11.glRotatef(20.0f, 0.0f, 0.0f, 1.0f);
            }
            GL11.glRotatef(-15.0f, 1.0f, 0.0f, 0.0f);
            GL11.glRotatef(40.0f, 0.0f, 0.0f, 1.0f);
            this.renderManager.itemRenderer.renderItem((EntityLivingBase)witch, itemstack, 0);
            if (itemstack.getItem().requiresMultipleRenderPasses()) {
                this.renderManager.itemRenderer.renderItem((EntityLivingBase)witch, itemstack, 1);
            }
            GL11.glPopMatrix();
        }
    }
    
    protected void func_82410_b() {
        GL11.glTranslatef(0.0f, 0.1875f, 0.0f);
    }
    
    protected void preRenderCallback(final EntityLivingBase par1EntityLivingBase, final float par2) {
        final float f1 = 0.9375f;
        GL11.glScalef(0.9375f, 0.9375f, 0.9375f);
    }
    
    protected ResourceLocation getEntityTexture(final Entity par1Entity) {
        return RenderSummonedWitch.witchTextures;
    }
    
    public void doRender(final Entity par1Entity, final double par2, final double par4, final double par6, final float par8, final float par9) {
        final ItemStack itemstack = ((EntitySummonedWitch)par1Entity).getHeldItem();
        this.witchModel.field_82900_g = (itemstack != null);
        super.doRender((EntityLiving)par1Entity, par2, par4, par6, par8, par9);
    }
    
    static {
        witchTextures = new ResourceLocation("textures/entity/witch.png");
    }
}
