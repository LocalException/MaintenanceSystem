package de.localexception.maintenance;

import de.localexception.maintenance.commands.Command_Maintenance;
import de.localexception.maintenance.commands.Command_Whitelist;
import de.localexception.maintenance.listeners.Listener_LoginEvent;
import de.localexception.maintenance.listeners.Listener_ProxyPingEvent;
import de.localexception.maintenance.utilities.Config;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.plugin.Plugin;

/**********************************************************************
 *                                                                    *
 *   Copyright © LocalException 2018                                  *
 *                                                                    *
 *   Jeder Art der Vervielfältigung, Verbreitung, Verleihung,         *
 *   öffentlichen Zugänglichmachung oder andere Nutzung bedarf        *
 *   der ausdrücklichen, schriftlichen Genehmigung von LocalException * 
 *                                                                    *
 **********************************************************************/


public class Main extends Plugin{

    private static Main instance;

    @Override
    public void onEnable() {
        instance = this;
        Config.init();

        getProxy().getPluginManager().registerCommand(this,new Command_Whitelist());
        getProxy().getPluginManager().registerCommand(this,new Command_Maintenance());

        getProxy().getPluginManager().registerListener(this, new Listener_LoginEvent());
        getProxy().getPluginManager().registerListener(this, new Listener_ProxyPingEvent());

        getProxy().getConsole().sendMessage(new TextComponent(
                "§a\n##     ##    ###    #### ##    ## ######## ######## ##    ##    ###    ##    ##  ######  ######## \n" +
                    "###   ###   ## ##    ##  ###   ##    ##    ##       ###   ##   ## ##   ###   ## ##    ## ##       \n" +
                    "#### ####  ##   ##   ##  ####  ##    ##    ##       ####  ##  ##   ##  ####  ## ##       ##       \n" +
                    "## ### ## ##     ##  ##  ## ## ##    ##    ######   ## ## ## ##     ## ## ## ## ##       ######   \n" +
                    "##     ## #########  ##  ##  ####    ##    ##       ##  #### ######### ##  #### ##       ##       \n" +
                    "##     ## ##     ##  ##  ##   ###    ##    ##       ##   ### ##     ## ##   ### ##    ## ##       \n" +
                    "##     ## ##     ## #### ##    ##    ##    ######## ##    ## ##     ## ##    ##  ######  ########\n"));
        getProxy().getConsole().sendMessage(new TextComponent("§e"+getDescription().getName()+" v."+getDescription().getVersion() +" | by "+getDescription().getAuthor()));
    }

    @Override
    public void onDisable() {

        Config.shutdown();

        getProxy().getConsole().sendMessage(new TextComponent(
                "§c\n##     ##    ###    #### ##    ## ######## ######## ##    ##    ###    ##    ##  ######  ######## \n" +
                    "###   ###   ## ##    ##  ###   ##    ##    ##       ###   ##   ## ##   ###   ## ##    ## ##       \n" +
                    "#### ####  ##   ##   ##  ####  ##    ##    ##       ####  ##  ##   ##  ####  ## ##       ##       \n" +
                    "## ### ## ##     ##  ##  ## ## ##    ##    ######   ## ## ## ##     ## ## ## ## ##       ######   \n" +
                    "##     ## #########  ##  ##  ####    ##    ##       ##  #### ######### ##  #### ##       ##       \n" +
                    "##     ## ##     ##  ##  ##   ###    ##    ##       ##   ### ##     ## ##   ### ##    ## ##       \n" +
                    "##     ## ##     ## #### ##    ##    ##    ######## ##    ## ##     ## ##    ##  ######  ########"));
    }

    public static Main getInstance() {
        return instance;
    }
}
