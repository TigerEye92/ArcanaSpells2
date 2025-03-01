package tigereye.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import tigereye.itemblock.ItemSpellScroll;
import tigereye.spell.SpellData;

public class GuiInGame extends Gui {

    private static final ResourceLocation SPELL_ICONS = new ResourceLocation("tigereye", "textures/gui/spell_icons.png");
    private final Minecraft mc;

    public GuiInGame(Minecraft mc) {
        this.mc = mc;
    }

    public void renderGameOverlay() {
        // Получаем текущий предмет в руке игрока
        ItemStack heldItem = mc.thePlayer.getHeldItem();
        if (heldItem != null && heldItem.getItem() instanceof ItemSpellScroll) {
            // Получаем данные о заклинании
            SpellData spellData = ItemSpellScroll.getSpellData(heldItem);
            if (spellData != null) {
                // Отрисовываем иконку заклинания
                renderSpellIcon(spellData, 10, 10);
            }
        }
    }

    private void renderSpellIcon(SpellData spellData, int x, int y) {
        // Привязываем текстуру иконок заклинаний
        mc.getTextureManager().bindTexture(SPELL_ICONS);

        // Включаем прозрачность
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

        // Отрисовываем иконку заклинания
        Tessellator tessellator = Tessellator.instance;
        tessellator.startDrawingQuads();
        tessellator.addVertexWithUV(x, y + 16, 0, 0, 1);
        tessellator.addVertexWithUV(x + 16, y + 16, 0, 1, 1);
        tessellator.addVertexWithUV(x + 16, y, 0, 1, 0);
        tessellator.addVertexWithUV(x, y, 0, 0, 0);
        tessellator.draw();

        // Отключаем прозрачность
        GL11.glDisable(GL11.GL_BLEND);
    }
}
