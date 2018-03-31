package de.localexception.maintenance.listeners;

import de.localexception.maintenance.utilities.Data;
import de.localexception.maintenance.utilities.Messages;
import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.LoginEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

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

    @EventHandler
    public void onLogin(LoginEvent event){

        if(Data.maintenance){

            String uuid = event.getConnection().getUniqueId().toString();

            if(!Data.whitelist.contains(uuid)){
                event.setCancelReason(new TextComponent(Messages.cancelreason()));
                event.setCancelled(true);

                for(ProxiedPlayer proxiedPlayer : BungeeCord.getInstance().getPlayers()){
                    if(proxiedPlayer.hasPermission("maintenance.notify")){
                        proxiedPlayer.sendMessage(new TextComponent(Data.prefix+ Messages.teamnotify.replaceAll("%player%",event.getConnection().getName())));
                    }
                }

            }

        }

    }
}
