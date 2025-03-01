package tigereye.render;

import net.minecraft.client.renderer.entity.*;
import cpw.mods.fml.relauncher.*;
import net.minecraft.util.*;
import net.minecraft.client.model.*;
import net.minecraft.entity.*;

@SideOnly(Side.CLIENT)
public class RenderRisenZombiePigman extends RenderBiped
{
    private static final ResourceLocation zombiePigmanTextures;
    
    public RenderRisenZombiePigman() {
        super((ModelBiped)new ModelZombie(), 0.5f, 1.0f);
    }
    
    protected ResourceLocation getEntityTexture(final Entity par1Entity) {
        return RenderRisenZombiePigman.zombiePigmanTextures;
    }
    
    static {
        zombiePigmanTextures = new ResourceLocation("textures/entity/zombie_pigman.png");
    }
}
