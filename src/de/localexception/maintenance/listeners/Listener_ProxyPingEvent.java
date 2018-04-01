package de.localexception.maintenance.listeners;

import de.localexception.maintenance.utilities.Data;
import de.localexception.maintenance.utilities.Motd;
import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.ServerPing;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.event.ProxyPingEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;
import net.md_5.bungee.event.EventPriority;

/**********************************************************************
 *                                                                    *
 *   Copyright © LocalException 2018                                  *
 *                                                                    *
 *   Jeder Art der Vervielfältigung, Verbreitung, Verleihung,         *
 *   öffentlichen Zugänglichmachung oder andere Nutzung bedarf        *
 *   der ausdrücklichen, schriftlichen Genehmigung von LocalException * 
 *                                                                    *
 **********************************************************************/


public class Listener_ProxyPingEvent implements Listener{

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onProxyPing(ProxyPingEvent event){

        if(Motd.motdsystem){

            ServerPing serverPing = event.getResponse();

            if(Data.maintenance){
                serverPing.setVersion(new ServerPing.Protocol(Motd.maintenancemessage,-1));
                serverPing.setDescriptionComponent(new TextComponent(Motd.maintenancemotd[0]+"\n"+Motd.maintenancemotd[1]));
            }else{
                serverPing.setDescriptionComponent(new TextComponent(Motd.normalmotd[0]+"\n"+Motd.normalmotd[1]));
            }
            ServerPing.PlayerInfo playerinfo[] = new ServerPing.PlayerInfo[]{new ServerPing.PlayerInfo(Motd.getPlayerInfo(),"")};

            serverPing.getPlayers().setOnline(BungeeCord.getInstance().getOnlineCount());
            serverPing.getPlayers().setMax(Motd.slots);
            serverPing.getPlayers().setSample(playerinfo);

        }


    }
}
