package de.localexception.maintenance.utilities;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.md_5.bungee.api.connection.ProxiedPlayer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.HashMap;

public class UUIDFetcher {
	private static HashMap<String, String> uuidCache = new HashMap<String, String>();


	public static String getName(String uuid) {
		uuid = uuid.replace("-", "");

		String output = callURL("https://sessionserver.mojang.com/session/minecraft/profile/" + uuid);

		StringBuilder result = new StringBuilder();

		int i = 0;

		while(i < 200) {

			if((String.valueOf(output.charAt(i)).equalsIgnoreCase("n")) && (String.valueOf(output.charAt(i+1)).equalsIgnoreCase("a")) && (String.valueOf(output.charAt(i+2)).equalsIgnoreCase("m")) && (String.valueOf(output.charAt(i+3)).equalsIgnoreCase("e"))) {

				int k = i+7;

				while(k < 100) {

					if(!String.valueOf(output.charAt(k)).equalsIgnoreCase("\"")) {

						result.append(String.valueOf(output.charAt(k)));

					} else {
						break;
					}

					k++;
				}

				break;
			}

			i++;
		}

		return result.toString();
	}

	public static String getUUID(ProxiedPlayer player) {
		String output = callURL("https://api.mojang.com/users/profiles/minecraft/" + player.getName());

		StringBuilder result = new StringBuilder();

		readData(output, result);

		return result.toString();
	}

	public static String getUUID(String playername) {
		String output = callURL("https://api.mojang.com/users/profiles/minecraft/" + playername);

		StringBuilder result = new StringBuilder();

		readData(output, result);

		return result.toString();
	}

	private static void readData(String toRead, StringBuilder result) {
		int i = 7;

		while(i < 200) {
			if(!String.valueOf(toRead.charAt(i)).equalsIgnoreCase("\"")) {

				result.append(String.valueOf(toRead.charAt(i)));

			} else {
				break;
			}

			i++;
		}
	}

	private static String callURL(String URL) {
		StringBuilder sb = new StringBuilder();
		URLConnection urlConn = null;
		InputStreamReader in = null;
		try {
			URL url = new URL(URL);
			urlConn = url.openConnection();

			if (urlConn != null) urlConn.setReadTimeout(60 * 1000);

			if (urlConn != null && urlConn.getInputStream() != null) {
				in = new InputStreamReader(urlConn.getInputStream(), Charset.defaultCharset());
				BufferedReader bufferedReader = new BufferedReader(in);

				if (bufferedReader != null) {
					int cp;

					while ((cp = bufferedReader.read()) != -1) {
						sb.append((char) cp);
					}

					bufferedReader.close();
				}
			}

			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return sb.toString();
	}
	}
