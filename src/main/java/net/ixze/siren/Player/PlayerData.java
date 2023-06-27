package net.ixze.siren.Player;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.HashMap;


public class PlayerData {

    public static HashMap<String, Float[]> Data = new HashMap<>();


    public void SetValue(String name, float value){
        if (!Data.containsKey(name)){
            Float[] newArray = {value, value};
            Data.put(name, newArray);
            return;
        }

        Float PreviousValue = Data.get(name)[0];
        Float[] newArray = {value, PreviousValue};
        Data.put(name, newArray);
    }

    public HashMap<String, Float[]> GetData(){
        return Data;
    }

    public void Update(Player player){
        Location PlayerLocation = player.getLocation();
        this.SetValue("X", (float) PlayerLocation.getX());
        this.SetValue("Z", (float) PlayerLocation.getZ());
        this.SetValue("Yaw", PlayerLocation.getYaw());

        AddDelta("X");
        AddDelta("Z");
        AddDelta("Yaw");

        this.SetValue("DeltaXZ", Data.get("DeltaX")[0] - Data.get("DeltaZ")[0]);

    }

    public void AddDelta(String value){
        float PreviousValue =  Data.get(value)[0];
        float AfterValue =  Data.get(value)[1];
        if (PreviousValue != AfterValue) {
            this.SetValue("Delta" + value, AfterValue - PreviousValue);
        } else {
            this.SetValue("Delta" + value, 0);
        }
    }

    public void DisplayChanges(){
        for (String key : Data.keySet()){
            float PreviousValue =  Data.get(key)[0];
            float AfterValue =  Data.get(key)[1];

            if (PreviousValue != AfterValue){
                Bukkit.broadcastMessage("§b" + key + " §7┃ §aBEFORE§7: §f" + PreviousValue + "       " + " §cAFTER§7: §f" + AfterValue);
            }
        }
    }



}
