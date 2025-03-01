package tigereye.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import tigereye.itemblock.ItemSpellBook;
import tigereye.spell.SpellData;
import tigereye.spell.SpellHelper;

public class GuiSpellBook extends GuiContainer {

    private static final ResourceLocation background = new ResourceLocation("tigereye", "textures/gui/spellbook_gui.png");
    private static final ResourceLocation extras = new ResourceLocation("tigereye", "textures/gui/spellbook_gui_extras.png");

    private int bookActiveSlot; // Активный слот в книге заклинаний

    public GuiSpellBook(InventoryPlayer inventoryPlayer, ItemStack spellBookStack) {
        super(new ContainerSpellBook(inventoryPlayer, spellBookStack));
        this.xSize = 256; // Ширина GUI
        this.ySize = 250; // Высота GUI

        // Проверяем, что предмет в руке — это книга заклинаний
        if (spellBookStack.getItem() instanceof ItemSpellBook) {
            this.bookActiveSlot = ((ItemSpellBook) spellBookStack.getItem()).getActiveSlot(spellBookStack);
        } else {
            this.bookActiveSlot = 0; // По умолчанию активный слот — 0
        }
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        // Отрисовка фона
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(background);
        int x = (this.width - this.xSize) / 2;
        int y = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(x, y, 0, 0, this.xSize, this.ySize);

        // Отрисовка дополнительных элементов (например, выделение активного слота)
        this.mc.getTextureManager().bindTexture(extras);
        int slotX = x + 16; // Позиция X активного слота
        int slotY = y + 3 + bookActiveSlot * 18; // Позиция Y активного слота
        this.drawTexturedModalRect(slotX, slotY, 0, 0, 20, 20); // Отрисовка выделения
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        // Отрисовка названий заклинаний
        for (int i = 0; i < 8; ++i) {
            ItemStack stack = ((ContainerSpellBook) this.inventorySlots).getSpellBookInventory().getStackInSlot(i);
            if (stack != null) {
                SpellData spellData = SpellHelper.getSpellData(stack); // Используем универсальный метод
                if (spellData != null) {
                    String spellName = spellData.spellObj.getTranslatedName(spellData.spellLevel);
                    this.fontRendererObj.drawString(spellName, 37, 5 + i * 18, 0x404040);
                }
            }
        }
    }
}  