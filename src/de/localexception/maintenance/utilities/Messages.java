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


public class Messages {

    public static String teamnotify;
    public static String nopermission;

    public static List<String> cancellist;

    public static String cancelreason(){

        String reason = "";

        for(int i = 0; i < cancellist.size(); i++){
            reason = reason+"\n§r"+cancellist.get(i)
                    .replaceAll("&","§")
                    .replaceAll("%prefix%",Data.prefix)
                    .replaceAll("%duration%",Data.maintenanceduration)
                    .replaceAll("%reason%",Data.maintenancereason)
                    .replaceAll("%servername%",Data.servername);
        }

        return reason;
    }

    //Whitelist Command

    public static String alreadyonwhitelist;
    public static String isnotonwhitelist;
    public static String addedtowhitelist;
    public static String removedfromwhitelist;

    //Maintenance Command

    public static String alreadymaintenance;
    public static String isnotmaintenance;
    public static String maintenanceactive;
    public static String maintenancedeactive;
    public static String newmaintenancereason;
    public static String newmaintenanceduration;



}
