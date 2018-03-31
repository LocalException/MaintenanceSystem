package de.localexception.maintenance.commands;

import de.localexception.maintenance.utilities.Data;
import de.localexception.maintenance.utilities.Messages;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.plugin.Command;

/**********************************************************************
 *                                                                    *
 *   Copyright © LocalException 2018                                  *
 *                                                                    *
 *   Jeder Art der Vervielfältigung, Verbreitung, Verleihung,         *
 *   öffentlichen Zugänglichmachung oder andere Nutzung bedarf        *
 *   der ausdrücklichen, schriftlichen Genehmigung von LocalException * 
 *                                                                    *
 **********************************************************************/


public class Command_Maintenance extends Command{

    public Command_Maintenance(){
        super("maintenance");
    }


    @Override
    public void execute(CommandSender sender, String[] args) {

        if(sender.hasPermission("maintenance.modify")){

            if(args.length == 1){
                if(args[0].equalsIgnoreCase("on")){
                    if(Data.maintenance){
                        sender.sendMessage(new TextComponent(Data.prefix+Messages.alreadymaintenance));
                    }else{
                        Data.maintenance = true;
                        sender.sendMessage(new TextComponent(Data.prefix+Messages.maintenanceactive));
                    }
                }else if(args[0].equalsIgnoreCase("off")){
                    if(Data.maintenance){
                        Data.maintenance = false;
                        sender.sendMessage(new TextComponent(Data.prefix+Messages.maintenancedeactive));
                    }else{
                        sender.sendMessage(new TextComponent(Data.prefix+Messages.isnotmaintenance));
                    }
                }else{
                    sender.sendMessage(new TextComponent(Data.prefix+"§cUsage§8: §c/maintenance <on/off>"));
                }
            }else if(args.length >= 2){
                if(args[0].equalsIgnoreCase("reason")){
                    String reason = "";
                    for(int i = 1; i < args.length; i++){
                        reason = reason +" " + args[i];
                    }
                    reason = reason.replaceFirst(" ","");

                    Data.maintenancereason = reason;

                    sender.sendMessage(new TextComponent(Data.prefix+Messages.newmaintenancereason.replaceAll("%newreason%",reason)));
                }else if(args[0].equalsIgnoreCase("duration")){
                    String duration = "";
                    for(int i = 1; i < args.length; i++){
                        duration = duration +" " + args[i];
                    }
                    duration = duration.replaceFirst(" ","");

                    Data.maintenanceduration = duration;

                    sender.sendMessage(new TextComponent(Data.prefix+Messages.newmaintenanceduration.replaceAll("%newduration%",duration)));
                }else{
                    sender.sendMessage(new TextComponent(Data.prefix+"§cUsage§8: §c/maintenance <duration/reason> <new Duration / new Reason>"));
                }
            }else{
                sender.sendMessage(new TextComponent(Data.prefix+"§cUsage§8: §c/maintenance <duration/reason> <new Duration / new Reason>"));
                sender.sendMessage(new TextComponent(Data.prefix+"§cUsage§8: §c/maintenance <on/off>"));
            }

        }else{
            sender.sendMessage(new TextComponent(Messages.nopermission));
        }

    }
}
