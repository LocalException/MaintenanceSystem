package de.localexception.maintenance.utilities;

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


public class Motd {

    public static String[] normalmotd;
    public static String[] maintenancemotd;

    public static List<String> playerinfo;

    public static String getPlayerInfo(){

        String info = "";

        for(int i = 0; i < playerinfo.size(); i++){
            if(i+1 < playerinfo.size()){
                info = info+playerinfo.get(i)
                        .replaceAll("&","§")
                        .replaceAll("%prefix%",Data.prefix)
                        .replaceAll("%duration%",Data.maintenanceduration)
                        .replaceAll("%reason%",Data.maintenancereason)
                        .replaceAll("%servername%",Data.servername)+"\n§r";
            }else{
                info = info+playerinfo.get(i)
                        .replaceAll("&","§")
                        .replaceAll("%prefix%",Data.prefix)
                        .replaceAll("%duration%",Data.maintenanceduration)
                        .replaceAll("%reason%",Data.maintenancereason)
                        .replaceAll("%servername%",Data.servername);
            }
        }

        return info;
    }


    public static String maintenancemessage;

    public static boolean motdsystem;


    public static int slots;

}
