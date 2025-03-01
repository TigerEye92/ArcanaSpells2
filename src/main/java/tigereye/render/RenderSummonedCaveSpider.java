package tigereye.render;

import cpw.mods.fml.relauncher.*;
import net.minecraft.util.*;
import org.lwjgl.opengl.*;
import net.minecraft.entity.*;

@SideOnly(Side.CLIENT)
public class RenderSummonedCaveSpider extends RenderSummonedSpider
{
    private static final ResourceLocation caveSpiderTextures;
    
    public RenderSummonedCaveSpider() {
        this.shadowSize *= 0.7f;
    }
    
    protected void preRenderCallback(final EntityLivingBase par1EntityLivingBase, final float par2) {
        GL11.glScalef(0.7f, 0.7f, 0.7f);
    }
    
    @Override
    protected ResourceLocation getEntityTexture(final Entity par1Entity) {
        return RenderSummonedCaveSpider.caveSpiderTextures;
    }
    
    static {
        caveSpiderTextures = new ResourceLocation("textures/entity/spider/cave_spider.png");
    }
}
