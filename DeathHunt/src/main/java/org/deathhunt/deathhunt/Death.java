package org.deathhunt.deathhunt;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import java.lang.String;

public class Death implements Listener {
    String[] DeathMsgs = {};

    public static String[] addX(int n, String[] arr, String x)
    {
        int i;

        // create a new array of size n+1
        String[] newarr = new String[n + 1];

        // insert the elements from
        // the old array into the new array
        // insert all elements till n
        // then insert x at n+1
        for (i = 0; i < n; i++)
            newarr[i] = arr[i];

        newarr[n] = x;

        return newarr;
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent e) {
        if (!(DeathMsgs.length == 0)) {
            Player dead = e.getEntity();
            String deathmessage = e.getDeathMessage();
            assert deathmessage != null;
            String messagereal = deathmessage.substring(dead.getName().length() + 1);
            boolean found = true;
            for (String deathMsg : DeathMsgs){
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "bc " + deathMsg);
                if (messagereal.equals(deathMsg) || messagereal.contains(deathMsg)) {
                    found = true;
                    break;
                }
                else
                    found = false;
            }




            if (!found)
            {
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "title @a subtitle {\"text\":\"" + messagereal + "\", \"bold\":true, \"italic\":true, \"color\":\"yellow\"}");
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "title @a title {\"text\":\"" + dead.getName() + "\", \"bold\":true, \"italic\":true, \"color\":\"red\"}");
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "minecraft:scoreboard players add " + dead.getName() + " deaths 1");

                DeathMsgs = addX(DeathMsgs.length, DeathMsgs, messagereal);
            }
            else {
                Bukkit.broadcastMessage("Someone have already died this way!");
            }
        }
        else {
            Player dead = e.getEntity();
            String deathmessage = e.getDeathMessage();
            assert deathmessage != null;
            String messagereal = deathmessage.substring(dead.getName().length() + 1);
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "title @a subtitle {\"text\":\"" + messagereal + "\", \"bold\":true, \"italic\":true, \"color\":\"yellow\"}");
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "title @a title {\"text\":\"" + dead.getName() + "\", \"bold\":true, \"italic\":true, \"color\":\"red\"}");
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "minecraft:scoreboard players add " + dead.getName() + " deaths 1");

            DeathMsgs = addX(DeathMsgs.length, DeathMsgs, messagereal);
        }
    }
}