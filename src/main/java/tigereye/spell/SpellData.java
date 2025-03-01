package tigereye.spell;

import net.minecraft.world.*;
import net.minecraft.entity.player.*;
import net.minecraft.nbt.*;

public class SpellData {
    public final Spell spellObj;
    public final short spellLevel;
    public final short spellCooldown;

    public SpellData(final Spell par1Spell, final short par2Level, final short par3Cooldown) {
        this.spellObj = par1Spell;
        this.spellLevel = par2Level;
        this.spellCooldown = par3Cooldown;
    }

    public SpellData(final Spell par1Spell, final short par2Level) {
        this(par1Spell, par2Level, (short)0);
    }

    public void castSpell(final World par1World, final EntityPlayer par2EntityPlayer) {
        this.spellObj.castSpell(this.spellLevel, par1World, par2EntityPlayer);
    }

    // ОБЫЧНЫЙ (НЕ СТАТИЧЕСКИЙ) метод для сохранения в существующий тег
    public void writeToNBT(final NBTTagCompound tag) {
        tag.setShort("id", (short)this.spellObj.effectId);
        tag.setShort("lvl", this.spellLevel);
        tag.setShort("cd", this.spellCooldown);
    }

    // СТАТИЧЕСКИЙ метод для создания нового NBTTagCompound с данными из SpellData
    public static NBTTagCompound writeToNBTTagCompound(final SpellData spellData) {
        final NBTTagCompound tag = new NBTTagCompound();
        tag.setShort("id", (short)spellData.spellObj.effectId);
        tag.setShort("lvl", spellData.spellLevel);
        tag.setShort("cd", spellData.spellCooldown);
        return tag;
    }

    public static SpellData readFromNBTTagCompound(final NBTTagCompound tag) {
        final Spell spell = Spells.spellList[tag.getShort("id")];
        final short tagLevel = tag.getShort("lvl");
        final short tagCooldown = tag.getShort("cd");
        return new SpellData(spell, tagLevel, tagCooldown);
    }

    public static void tickCooldown(final NBTTagCompound tag) {
        final short cooldown = tag.getShort("cd");
        if (cooldown > 0) {
            tag.setShort("cd", (short)(cooldown - 1));
        }
    }

    public static void startCooldown(final NBTTagCompound tag) {
        final Spell spell = Spells.spellList[tag.getShort("id")];
        tag.setShort("cd", spell.getCooldown());
    }

    public static SpellData tryCombine(final SpellData spellDataA, final SpellData spellDataB) {
        if (spellDataA.spellObj.effectId == spellDataB.spellObj.effectId) {
            if (spellDataA.spellLevel > spellDataB.spellLevel) {
                return spellDataA;
            }
            if (spellDataA.spellLevel == spellDataB.spellLevel) {
                short newSpellLevel = spellDataA.spellLevel;
                if (spellDataA.spellLevel + 1 <= spellDataA.spellObj.getMaxLevel()) {
                    ++newSpellLevel;
                }
                return new SpellData(spellDataA.spellObj, newSpellLevel);
            }
        }
        return null;
    }
}