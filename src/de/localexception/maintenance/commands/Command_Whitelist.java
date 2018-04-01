package de.localexception.maintenance.commands;

import de.localexception.maintenance.utilities.Data;
import de.localexception.maintenance.utilities.Messages;
import de.localexception.maintenance.utilities.UUIDFetcher;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.plugin.Command;

import java.util.UUID;

/**********************************************************************
 *                                                                    *
 *   Copyright © LocalException 2018                                  *
 *                                                                    *
 *   Jeder Art der Vervielfältigung, Verbreitung, Verleihung,         *
 *   öffentlichen Zugänglichmachung oder andere Nutzung bedarf        *
 *   der ausdrücklichen, schriftlichen Genehmigung von LocalException * 
 *                                                                    *
 **********************************************************************/


public class Command_Whitelist extends Command{

    public Command_Whitelist(){
        super("whitelist");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {

        if(sender.hasPermission("maintenance.whitelist")){

            if(args.length == 2){

                if(args[0].equalsIgnoreCase("add")){
                    String target = args[1];
                    String uuid = UUIDFetcher.getUUID(target).replaceAll("-","");

                    if(Data.whitelist.contains(uuid)){
                        sender.sendMessage(new TextComponent(Data.prefix+Messages.alreadyonwhitelist));
                    }else{
                        Data.whitelist.add(uuid);
                        sender.sendMessage(new TextComponent(Data.prefix+Messages.addedtowhitelist));
                    }

                }else if(args[0].equalsIgnoreCase("remove")){
                    String target = args[1];
                    String uuid = UUIDFetcher.getUUID(target.replaceAll("-",""));

                    if(Data.whitelist.contains(uuid)){
                        Data.whitelist.remove(uuid);
                        sender.sendMessage(new TextComponent(Data.prefix+Messages.removedfromwhitelist));
                    }else{
                        sender.sendMessage(new TextComponent(Data.prefix+Messages.isnotonwhitelist));
                    }
                }

            }else if(args.length == 1){
                if(args[0].equalsIgnoreCase("list")){

                    sender.sendMessage(new TextComponent("§aWhitelisted Players§8:"));

                    for(int i = 0; i < Data.whitelist.size(); i++){

                        sender.sendMessage(new TextComponent("§8» §e"+UUIDFetcher.getName(Data.whitelist.get(i))));

                    }

                }
            }

        }else{
            sender.sendMessage(new TextComponent(Messages.nopermission));
        }

    }
}
