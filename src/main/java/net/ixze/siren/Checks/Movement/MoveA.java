package net.ixze.siren.Checks.Movement;

import net.ixze.siren.Checks.Check;
import net.ixze.siren.Player.PlayerData;
import net.ixze.siren.Siren;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class MoveA extends Check {


    public MoveA() {
        super("MoveA", true);
    }


    @Override
    public void Analysis(Player player) {

        if (player.isFlying() || player.getGameMode() == GameMode.CREATIVE || player.getGameMode() == GameMode.SPECTATOR){
            return;
        }

        PlayerData DataInstance = Siren.GetPlayerData(player);
        HashMap<String, Float[]> Data = DataInstance.GetData();

        float DeltaXZ = Math.abs(Data.get("DeltaXZ")[0]);
        float DeltaYaw = Math.abs(Data.get("DeltaYaw")[0]);

        if (DeltaYaw > 2F && DeltaXZ > .82) {
            Siren.FlagPlayer(player,this.getName());
        }

        super.Analysis(player);
    }
}
