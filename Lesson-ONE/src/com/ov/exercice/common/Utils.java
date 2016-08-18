package com.ov.exercice.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.util.Base64;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Utils {
	/**
	 * 
	 * @param url
	 * @return
	 */
	public static String toBase64(String iurl) {
		return Base64.getUrlEncoder().encodeToString(iurl.getBytes());
	}

	/**
	 * 
	 * @param iJsonText
	 * @param key
	 * @return
	 */
	public static JSONArray jsonArrayParser(String iJsonText, String key) {
		try {

			JSONParser lParser = new JSONParser();
			JSONObject jsonObject = (JSONObject) lParser.parse(iJsonText);
			JSONArray jsonArray = (JSONArray) jsonObject.get(key);
			return jsonArray;
		} catch (Exception e) {
			System.out.println(BundleUtilities.getParam(Constants.sSystemConfig, "msg.error.parse"));
			return null;
		}
	}

	/**
	 * 
	 * @param iHttpURLConnection
	 * @return
	 * @throws IOException
	 */
	public static StringBuilder readFromStream(HttpURLConnection iHttpURLConnection) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(iHttpURLConnection.getInputStream()));
		StringBuilder stringBuilder = new StringBuilder();
		String line = bufferedReader.readLine();

		while (line != null) {
			stringBuilder.append(line + "\n");
			line = bufferedReader.readLine();
		}

		bufferedReader.close();
		return stringBuilder;
	}
}
