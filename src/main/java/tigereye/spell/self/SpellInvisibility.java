package tigereye.spell.self;

import tigereye.spell.Spell;
import net.minecraft.world.World;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

public class SpellInvisibility extends Spell {

    public SpellInvisibility(final int par1) {
        super(par1);
        this.setName("invisibility");
    }

    @Override
    public short getMaxLevel() {
        return 3; // Максимальный уровень заклинания
    }

    @Override
    public int getManaCost() {
        return 8; // Стоимость маны
    }

    @Override
    public short getCooldown() {
        return 200; // Время перезарядки
    }

    @Override
    public String getParticleName() {
        return "smoke"; // Частицы дыма
    }

    @Override
    public double getParticleAmount() {
        return 0.3; // Количество частиц
    }

    @Override
    public String getSoundName() {
        return "random.fizz"; // Звук исчезновения
    }

    @Override
    public void castSpell(final short par1Level, final World par2World, final EntityPlayer par3EntityPlayer) {
        // Воспроизводим звук
        par2World.playSoundAtEntity(par3EntityPlayer, this.getSoundName(), 1.0f, 1.0f);

        // Накладываем эффект невидимости
        int duration = 200 + 100 * par1Level; // Длительность зависит от уровня
        par3EntityPlayer.addPotionEffect(new PotionEffect(Potion.invisibility.getId(), duration, 0));

        // Создаем частицы
        for (int i = 0; i < 10; i++) {
            par2World.spawnParticle(this.getParticleName(),
                    par3EntityPlayer.posX + (Spell.random.nextDouble() - 0.5),
                    par3EntityPlayer.posY + Spell.random.nextDouble() * 2,
                    par3EntityPlayer.posZ + (Spell.random.nextDouble() - 0.5),
                    0.0, 0.0, 0.0);
        }
    }
}
