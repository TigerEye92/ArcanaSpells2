package tigereye.render;

import net.minecraft.client.renderer.entity.*;
import cpw.mods.fml.relauncher.*;
import net.minecraft.util.*;
import tigereye.model.*;
import net.minecraft.client.model.*;
import net.minecraft.entity.*;
import thaumcraft.common.entities.monster.boss.*;
import net.minecraft.entity.boss.*;
import org.lwjgl.opengl.*;

@SideOnly(Side.CLIENT)
public class RenderSummonTaintacle extends RenderLiving
{
    private static final ResourceLocation rl;
    
    public RenderSummonTaintacle(final float shadow, final int length) {
        super((ModelBase)new ModelSummonTaintacle(length), shadow);
    }
    
    protected ResourceLocation getEntityTexture(final Entity entity) {
        return RenderSummonTaintacle.rl;
    }
    
    protected void preRenderCallback(final EntityLivingBase par1EntityLiving, final float par2) {
        if (par1EntityLiving instanceof EntityTaintacleGiant) {
            BossStatus.setBossStatus((IBossDisplayData)par1EntityLiving, false);
            GL11.glScalef(1.33f, 1.33f, 1.33f);
        }
        super.preRenderCallback(par1EntityLiving, par2);
    }
    
    static {
        rl = new ResourceLocation("tigereye:textures/models/taintacle2.png");
    }
}
