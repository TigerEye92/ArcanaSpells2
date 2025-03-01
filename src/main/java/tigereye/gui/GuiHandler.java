package tigereye.gui;

import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class GuiHandler implements IGuiHandler {

    public static final int SPELL_BOOK_GUI = 0;

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        if (ID == SPELL_BOOK_GUI) {
            return new ContainerSpellBook(player.inventory, player.getHeldItem());
        }
        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        if (ID == SPELL_BOOK_GUI) {
            return new GuiSpellBook(player.inventory, player.getHeldItem());
        }
        return null;
    }
}
