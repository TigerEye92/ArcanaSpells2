package tigereye.render;

import net.minecraft.client.renderer.entity.*;
import cpw.mods.fml.relauncher.*;
import net.minecraft.util.*;
import tigereye.model.*;
import net.minecraft.client.model.*;
import org.lwjgl.opengl.*;
import net.minecraft.entity.*;

@SideOnly(Side.CLIENT)
public class RenderRisenIceGiant extends RenderLiving
{
    public static final ResourceLocation IceGiant_texture;
    public static ModelIceGiant modelicegiant;
    public static float modelHeight;
    
    public RenderRisenIceGiant() {
        super((ModelBase)RenderRisenIceGiant.modelicegiant, 1.0f);
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
        return RenderRisenIceGiant.IceGiant_texture;
    }
    
    static {
        IceGiant_texture = new ResourceLocation("tigereye:textures/models/ModelIceGiant-texture.png");
        RenderRisenIceGiant.modelicegiant = new ModelIceGiant();
        RenderRisenIceGiant.modelHeight = 3.5f;
    }
}
