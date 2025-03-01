package tigereye.render;

import net.minecraft.client.renderer.entity.*;
import cpw.mods.fml.relauncher.*;
import net.minecraft.util.*;
import tigereye.model.*;
import net.minecraft.client.model.*;
import org.lwjgl.opengl.*;
import net.minecraft.entity.*;

@SideOnly(Side.CLIENT)
public class RenderRisenSkeleton extends RenderBiped
{
    private static final ResourceLocation skeletonTextures;
    
    public RenderRisenSkeleton() {
        super((ModelBiped)new ModelRisenSkeleton(), 0.5f);
    }
    
    protected void func_82422_c() {
        GL11.glTranslatef(0.09375f, 0.1875f, 0.0f);
    }
    
    protected ResourceLocation getEntityTexture(final Entity par1EntitySkeleton) {
        return RenderRisenSkeleton.skeletonTextures;
    }
    
    static {
        skeletonTextures = new ResourceLocation("textures/entity/skeleton/skeleton.png");
    }
}
