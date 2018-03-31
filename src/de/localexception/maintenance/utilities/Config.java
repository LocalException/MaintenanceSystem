package de.localexception.maintenance.utilities;

import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
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


public class Config {

    private static File ordner = new File("plugins/Maintenance");

    private static File config = new File(ordner,"config.yml");
    private static File messages = new File(ordner,"messages.yml");
    private static File whitelist = new File(ordner,"whitelist.yml");

    public static void init(){
        if(!ordner.exists()){
            ordner.mkdir();
        }

        if(!config.exists()){
            try {
                config.createNewFile();

                Configuration cfg = YamlConfiguration.getProvider(YamlConfiguration.class).load(config);

                cfg.set("Prefix","&7[&4Maintenance&7]");
                cfg.set("Server","LocalException.eu");
                cfg.set("Maintenance",false);
                cfg.set("MaintenanceReason","Updates");
                cfg.set("MaintenanceDuration","Custom");

                YamlConfiguration.getProvider(YamlConfiguration.class).save(cfg,config);

            } catch (IOException e) {
                //e.printStackTrace();
            }
        }

        if(!messages.exists()){
            try {
                messages.createNewFile();

                Configuration msg = YamlConfiguration.getProvider(YamlConfiguration.class).load(messages);

                msg.set("TeamNotify","&cThe player &e%player% &chas tried to enter the network during the maintenance!");
                msg.set("NoPermission","&cYou are not permitted!");

                msg.set("AlreadyOnWhitelist","&cThis player is already on the whitelist!");
                msg.set("IsNotOnWhitelist","&cThis player is not on the whitelist!");
                msg.set("AddedToWhitelist","&aThe player is now allowed to enter the Network!");
                msg.set("RemovedFromWhitelist","&aThe player was succesfully removed from the Whitelist!");

                msg.set("AlreadyMaintenance","&cThe Network is already in Maintenance!");
                msg.set("IsNotMaintenance","&cThe Network is not in Maintenance!");
                msg.set("MaintenanceActive","&aThe Network is now in Maintenance!");
                msg.set("MaintenanceDeactive","&aThe Network is now out of Maintenance!");

                msg.set("NewMaintenanceReason","&aThe new Reason of the Maintenance is &e%newreason%");
                msg.set("NewMaintenanceDuration","&aThe new Duration of the Maintenance is &e%newduration%");

                List<String> cancel = new ArrayList<>();

                cancel.add("&8&m-------------------------");
                cancel.add("");
                cancel.add("&2%servername% &7| &4Maintenance");
                cancel.add("");
                cancel.add("&cOur Network is currently in Maintenance!");
                cancel.add("");
                cancel.add("&3Reason&8: &e%reason%");
                cancel.add("&3Duration&8: &e%duration%");
                cancel.add("");
                cancel.add("&8&m-------------------------");

                msg.set("JoinMessage",cancel);

                YamlConfiguration.getProvider(YamlConfiguration.class).save(msg,messages);

            } catch (IOException e) {
                //e.printStackTrace();
            }
        }

        if(!whitelist.exists()){
            try {
                whitelist.createNewFile();

                Configuration wl = YamlConfiguration.getProvider(YamlConfiguration.class).load(whitelist);

                wl.set("Whitelist",new ArrayList<String>());

                YamlConfiguration.getProvider(YamlConfiguration.class).save(wl,whitelist);

            } catch (IOException e) {
                //e.printStackTrace();
            }
        }

        try {
            Configuration cfg = YamlConfiguration.getProvider(YamlConfiguration.class).load(config);
            Configuration msg = YamlConfiguration.getProvider(YamlConfiguration.class).load(messages);
            Configuration wl = YamlConfiguration.getProvider(YamlConfiguration.class).load(whitelist);

            Data.prefix = cfg.getString("Prefix").replaceAll("&","§")+" §r";
            Data.servername = cfg.getString("Server");

            Data.maintenance = cfg.getBoolean("Maintenance");
            Data.maintenancereason = cfg.getString("MaintenanceReason");
            Data.maintenanceduration = cfg.getString("MaintenanceDuration");

            Messages.teamnotify = msg.getString("TeamNotify").replaceAll("&","§");

            Messages.nopermission = msg.getString("NoPermission").replaceAll("&","§");

            Messages.removedfromwhitelist = msg.getString("RemovedFromWhitelist").replaceAll("&","§");
            Messages.addedtowhitelist = msg.getString("AddedToWhitelist").replaceAll("&","§");
            Messages.isnotonwhitelist = msg.getString("IsNotOnWhitelist").replaceAll("&","§");
            Messages.alreadyonwhitelist = msg.getString("AlreadyOnWhitelist").replaceAll("&","§");

            Messages.alreadymaintenance = msg.getString("AlreadyMaintenance").replaceAll("&","§");
            Messages.isnotmaintenance = msg.getString("IsNotMaintenance").replaceAll("&","§");
            Messages.maintenanceactive = msg.getString("MaintenanceActive").replaceAll("&","§");
            Messages.maintenancedeactive = msg.getString("MaintenanceDeactive").replaceAll("&","§");

            Messages.newmaintenancereason = msg.getString("NewMaintenanceReason").replaceAll("&","§");
            Messages.newmaintenanceduration = msg.getString("NewMaintenanceDuration").replaceAll("&","§");


            Messages.cancellist = msg.getStringList("JoinMessage");

            Data.whitelist = wl.getStringList("Whitelist");

        } catch (IOException e) {
            //e.printStackTrace();
        }

    }


    public static void shutdown(){

        try {
            Configuration cfg = YamlConfiguration.getProvider(YamlConfiguration.class).load(config);
            Configuration wl = YamlConfiguration.getProvider(YamlConfiguration.class).load(whitelist);

            cfg.set("Prefix",Data.prefix);
            cfg.set("Server",Data.servername);
            cfg.set("Maintenance",Data.maintenance);
            cfg.set("MaintenanceReason",Data.maintenancereason);
            cfg.set("MaintenanceDuration",Data.maintenanceduration);

            wl.set("Whitelist",Data.whitelist);

            YamlConfiguration.getProvider(YamlConfiguration.class).save(cfg,config);
            YamlConfiguration.getProvider(YamlConfiguration.class).save(wl,whitelist);
        } catch (IOException e) {
            //e.printStackTrace();
        }

    }


}
