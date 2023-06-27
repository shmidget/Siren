package net.ixze.siren;

import net.ixze.siren.Checks.Movement.MoveA;
import net.ixze.siren.Player.PlayerData;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.UUID;

public final class Siren extends JavaPlugin implements Listener {

    public static HashMap<UUID, PlayerData> PlayerDataContainer = new HashMap<>();
    public static MoveA moveA = new MoveA();

    public static void FlagPlayer(Player Player, String CheckFailed){
        Bukkit.broadcastMessage("§7[§dSiren§7] §d" + Player.getName() + " §fFailed " + "§d" + CheckFailed + " §7[§dVL§7: §f0§7]"); // VL not implemented yet, obvs.
    }

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(this,this);

        for (Player player : Bukkit.getOnlinePlayers()){
            RegisterData(player);
        }

        Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(this, () -> {
            for (Player player : Bukkit.getOnlinePlayers()){
                PlayerData Data = GetPlayerData(player);
                Data.Update(player);
                moveA.Analysis(player);
            }
        }, 0, 0);

    }

    // PlayerData Setup

    public static void RegisterData(Player player){
        if (!Siren.PlayerDataContainer.containsKey(player.getUniqueId())){
            Siren.PlayerDataContainer.put(player.getUniqueId(), new PlayerData());
        }
    }

    public static PlayerData GetPlayerData(Player player){
        return Siren.PlayerDataContainer.get(player.getUniqueId());
    }


    @EventHandler
    public void OnJoinEvent(PlayerJoinEvent event){
        RegisterData(event.getPlayer());
    }

    @EventHandler
    public void OnLeaveEvent(PlayerQuitEvent event){
        PlayerDataContainer.remove(event.getPlayer().getUniqueId());
    }


}
