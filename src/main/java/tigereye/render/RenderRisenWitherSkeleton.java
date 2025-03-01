package tigereye.render;

import net.minecraft.client.renderer.entity.*;
import cpw.mods.fml.relauncher.*;
import net.minecraft.util.*;
import tigereye.model.*;
import net.minecraft.client.model.*;
import org.lwjgl.opengl.*;
import net.minecraft.entity.*;

@SideOnly(Side.CLIENT)
public class RenderRisenWitherSkeleton extends RenderBiped
{
    private static final ResourceLocation witherSkeletonTextures;
    
    public RenderRisenWitherSkeleton() {
        super((ModelBiped)new ModelRisenWitherSkeleton(), 0.5f);
    }
    
    protected void func_82422_c() {
        GL11.glTranslatef(0.09375f, 0.1875f, 0.0f);
    }
    
    protected ResourceLocation getEntityTexture(final Entity par1EntitySkeleton) {
        return RenderRisenWitherSkeleton.witherSkeletonTextures;
    }
    
    protected void preRenderCallback(final EntityLivingBase par1EntityLivingBase, final float par2) {
        GL11.glScalef(1.2f, 1.2f, 1.2f);
    }
    
    static {
        witherSkeletonTextures = new ResourceLocation("textures/entity/skeleton/wither_skeleton.png");
    }
}
