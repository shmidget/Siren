package net.ixze.siren.Checks;


import org.bukkit.entity.Player;

public abstract class Check {
    String Name;
    boolean Enabled;

    public Check(String name, boolean enabled){
        this.Name = name;
        this.Enabled = enabled;
    }

    public String getName(){
        return this.Name;
    }

    public boolean isEnabled(){
        return this.Enabled;
    }



    public void Analysis(Player player){

    }
}
