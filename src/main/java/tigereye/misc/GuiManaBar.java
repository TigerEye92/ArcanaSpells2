package tigereye.misc;

import net.minecraft.client.gui.*;
import net.minecraft.util.*;
import net.minecraft.client.*;
import net.minecraftforge.client.event.*;
import net.minecraft.entity.player.*;
import cpw.mods.fml.common.eventhandler.*;

public class GuiManaBar extends Gui
{
    private final Minecraft mc;
    
    public GuiManaBar(final Minecraft mc) {
        this.mc = mc;
    }
    
    @SubscribeEvent(priority = EventPriority.NORMAL)
    public void onRenderOverlay(final RenderGameOverlayEvent.Post event) {
        // Используем событие TEXT, чтобы не конфликтовать с другими элементами интерфейса
        if (event.isCancelable() || event.type != RenderGameOverlayEvent.ElementType.TEXT) {
            return;
        }

        // Получаем свойства маны игрока
        final ManaProperties props = ManaProperties.get((EntityPlayer)this.mc.thePlayer);
        if (props == null || props.getMaxMana() == 0) {
            return;
        }

        // Получаем текущее и максимальное значение маны
        int currentMana = props.getCurrentMana();
        int maxMana = props.getMaxMana();

        // Получаем разрешение экрана
        final int width = event.resolution.getScaledWidth();
        final int height = event.resolution.getScaledHeight();

        // Позиция текста (например, над полоской здоровья)
        int x = width / 2 - 8; // Сдвиг вправо от центра
        int y = height - 51; // Над полоской здоровья

        // Отрисовываем текст с текущим и максимальным значением маны
        String manaText = currentMana + "/" + maxMana;
        this.mc.fontRenderer.drawStringWithShadow(manaText, x, y, 0x00FF00); // Зеленый цвет текста
    }
}