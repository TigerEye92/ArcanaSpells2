package tigereye.render;

import net.minecraft.client.renderer.entity.*;
import cpw.mods.fml.relauncher.*;
import net.minecraft.util.*;
import tigereye.model.*;
import net.minecraft.client.model.*;
import org.lwjgl.opengl.*;
import net.minecraft.entity.*;

@SideOnly(Side.CLIENT)
public class RenderSummonEldritchGolem extends RenderLiving
{
    public static final ResourceLocation SummonEldritchGolem_texture;
    public static ModelSummonEldritchGolem modelsummoneldritchgolem;
    public static float modelHeight;
    
    public RenderSummonEldritchGolem() {
        super((ModelBase)RenderSummonEldritchGolem.modelsummoneldritchgolem, 1.0f);
        this.shadowSize = 0.6f;
    }
    
    public void doRenderLiving(final EntityLiving golem, final double par2, final double par4, final double par6, final float par8, final float par9) {
        GL11.glEnable(3042);
        GL11.glAlphaFunc(516, 0.003921569f);
        GL11.glBlendFunc(770, 771);
        final double d3 = par4 - golem.yOffset;
        this.doRender(golem, par2, d3, par6, par8, par9);
        GL11.glDisable(3042);
        GL11.glAlphaFunc(516, 0.1f);
    }
    
    public void doRender(final Entity par1Entity, final double par2, final double par4, final double par6, final float par8, final float par9) {
        this.doRenderLiving((EntityLiving)par1Entity, par2, par4, par6, par8, par9);
    }
    
    protected ResourceLocation getEntityTexture(final Entity var1) {
        return RenderSummonEldritchGolem.SummonEldritchGolem_texture;
    }
    
    static {
        SummonEldritchGolem_texture = new ResourceLocation("tigereye:textures/models/eldritch_golem2.png");
        RenderSummonEldritchGolem.modelsummoneldritchgolem = new ModelSummonEldritchGolem();
        RenderSummonEldritchGolem.modelHeight = 3.5f;
    }
}
