package net.snofox.minecraft.fairfood;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by Josh on 2018-12-27
 */
public class FairFood extends JavaPlugin implements Listener {
    @Override
    public void onEnable() {
        getLogger().info("Fair Food, just like it used to be");
        getServer().getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void onEntityRegainHealth(final EntityRegainHealthEvent ev) {
        if(!ev.getEntityType().equals(EntityType.PLAYER)) return;
        final Player p = (Player)ev.getEntity();
        if(!ev.getRegainReason().equals(EntityRegainHealthEvent.RegainReason.SATIATED)) return;
        if(p.getSaturation() > 0.0f && p.getFoodLevel() >= 20) {
            ev.setAmount(0.125d);
            final float gainedExhaustion = Math.min(p.getSaturation(), 6.0F);
            final float shouldGain = .35f;
            p.setExhaustion(p.getExhaustion() - gainedExhaustion + shouldGain);
        }
    }
}
