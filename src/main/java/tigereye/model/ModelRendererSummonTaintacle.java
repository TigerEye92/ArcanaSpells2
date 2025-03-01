package tigereye.model;



import org.lwjgl.opengl.*;
import cpw.mods.fml.relauncher.*;
import net.minecraft.client.renderer.*;
import net.minecraft.client.model.*;

public class ModelRendererSummonTaintacle extends ModelRenderer
{
    private int textureOffsetX;
    private int textureOffsetY;
    private boolean compiled;
    private int displayList;
    private ModelBase baseModel;
    
    public ModelRendererSummonTaintacle(final ModelBase par1ModelBase) {
        super(par1ModelBase);
    }
    
    public ModelRendererSummonTaintacle(final ModelBase par1ModelBase, final int par2, final int par3) {
        this(par1ModelBase);
        this.setTextureOffset(par2, par3);
    }
    
    @SideOnly(Side.CLIENT)
    public void render(final float par1, final float scale) {
        if (!this.isHidden && this.showModel) {
            if (!this.compiled) {
                this.compileDisplayList(par1);
            }
            GL11.glTranslatef(this.offsetX, this.offsetY, this.offsetZ);
            if (this.rotateAngleX == 0.0f && this.rotateAngleY == 0.0f && this.rotateAngleZ == 0.0f) {
                if (this.rotationPointX == 0.0f && this.rotationPointY == 0.0f && this.rotationPointZ == 0.0f) {
                    if (this.childModels == null) {
                        final int j = 15728880;
                        final int k = j % 65536;
                        final int l = j / 65536;
                        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, k / 1.0f, l / 1.0f);
                    }
                    GL11.glCallList(this.displayList);
                    if (this.childModels != null) {
                        for (int i = 0; i < this.childModels.size(); ++i) {
                            GL11.glPushMatrix();
                            GL11.glScalef(scale, scale, scale);
                            ((ModelRendererSummonTaintacle) this.childModels.get(i)).render(par1, scale);
                            GL11.glPopMatrix();
                        }
                    }
                }
                else {
                    GL11.glTranslatef(this.rotationPointX * par1, this.rotationPointY * par1, this.rotationPointZ * par1);
                    if (this.childModels == null) {
                        final int j = 15728880;
                        final int k = j % 65536;
                        final int l = j / 65536;
                        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, k / 1.0f, l / 1.0f);
                    }
                    GL11.glCallList(this.displayList);
                    if (this.childModels != null) {
                        for (int i = 0; i < this.childModels.size(); ++i) {
                            GL11.glPushMatrix();
                            GL11.glScalef(scale, scale, scale);
                            ((ModelRendererSummonTaintacle) this.childModels.get(i)).render(par1, scale);
                            GL11.glPopMatrix();
                        }
                    }
                    GL11.glTranslatef(-this.rotationPointX * par1, -this.rotationPointY * par1, -this.rotationPointZ * par1);
                }
            }
            else {
                GL11.glPushMatrix();
                GL11.glTranslatef(this.rotationPointX * par1, this.rotationPointY * par1, this.rotationPointZ * par1);
                if (this.rotateAngleZ != 0.0f) {
                    GL11.glRotatef(this.rotateAngleZ * 57.295776f, 0.0f, 0.0f, 1.0f);
                }
                if (this.rotateAngleY != 0.0f) {
                    GL11.glRotatef(this.rotateAngleY * 57.295776f, 0.0f, 1.0f, 0.0f);
                }
                if (this.rotateAngleX != 0.0f) {
                    GL11.glRotatef(this.rotateAngleX * 57.295776f, 1.0f, 0.0f, 0.0f);
                }
                if (this.childModels == null) {
                    final int j = 15728880;
                    final int k = j % 65536;
                    final int l = j / 65536;
                    OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, k / 1.0f, l / 1.0f);
                }
                GL11.glCallList(this.displayList);
                if (this.childModels != null) {
                    for (int i = 0; i < this.childModels.size(); ++i) {
                        GL11.glPushMatrix();
                        GL11.glScalef(scale, scale, scale);
                        ((ModelRendererSummonTaintacle) this.childModels.get(i)).render(par1, scale);
                        GL11.glPopMatrix();
                    }
                }
                GL11.glPopMatrix();
            }
            GL11.glTranslatef(-this.offsetX, -this.offsetY, -this.offsetZ);
        }
    }
    
    private void compileDisplayList(final float par1) {
        // Создаем новый дисплейный список
        this.displayList = GLAllocation.generateDisplayLists(1);
        GL11.glNewList(this.displayList, 4864); // 4864 = GL_COMPILE

        // Получаем экземпляр Tessellator
        final Tessellator tessellator = Tessellator.instance;
        if (tessellator == null) {
            throw new IllegalStateException("Tessellator is not initialized");
        }

        // Рендерим все элементы cubeList
        for (int i = 0; i < this.cubeList.size(); ++i) {
            Object element = this.cubeList.get(i);
            if (element instanceof ModelBox) {
                ((ModelBox) element).render(tessellator, par1);
            } else {
                // Обработка других типов, если необходимо
            }
        }

        // Завершаем дисплейный список
        GL11.glEndList();

        // Устанавливаем флаг compiled
        this.compiled = true;
    }
}