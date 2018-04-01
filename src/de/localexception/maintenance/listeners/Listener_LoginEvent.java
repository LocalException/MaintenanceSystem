package de.localexception.maintenance.listeners;

import de.localexception.maintenance.utilities.Data;
import de.localexception.maintenance.utilities.Messages;
import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.LoginEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;
import net.md_5.bungee.event.EventPriority;

import java.util.List;

/**********************************************************************
 *                                                                    *
 *   Copyright © LocalException 2018                                  *
 *                                                                    *
 *   Jeder Art der Vervielfältigung, Verbreitung, Verleihung,         *
 *   öffentlichen Zugänglichmachung oder andere Nutzung bedarf        *
 *   der ausdrücklichen, schriftlichen Genehmigung von LocalException * 
 *                                                                    *
 **********************************************************************/


public class Listener_LoginEvent implements Listener{

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onLogin(LoginEvent event){

        String uuid = event.getConnection().getUniqueId().toString().replaceAll("-","");
        String name = event.getConnection().getName().toString();

        List<String> whitelist = Data.whitelist;

        if(Data.maintenance){

            System.out.println(uuid);
            if(whitelist.contains(uuid)){
                event.setCancelled(false);
                return;
            }

            event.setCancelReason(new TextComponent(Messages.cancelreason()));
            event.setCancelled(true);

            for(ProxiedPlayer proxiedPlayer : BungeeCord.getInstance().getPlayers()){
                if(proxiedPlayer.hasPermission("maintenance.notify")){
                    proxiedPlayer.sendMessage(new TextComponent(Data.prefix+ Messages.teamnotify.replaceAll("%player%",name)));
                }
            }


        }

    }
}
