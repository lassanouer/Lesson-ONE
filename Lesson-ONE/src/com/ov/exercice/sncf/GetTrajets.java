package com.ov.exercice.sncf;

import java.io.IOException;
import java.net.HttpURLConnection;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.ov.exercice.common.BundleUtilities;
import com.ov.exercice.common.ConnectionProvider;
import com.ov.exercice.common.Constants;
import com.ov.exercice.common.Utils;

/**
 * 
 * @author Anouer Lassoued
 *
 */
public class GetTrajets {

	private HttpURLConnection lHttpURLConnection = null;

	// TODO facorisation
	/**
	 * parse info from json-object to String
	 * 
	 * @param iTextInfo
	 * @return String informations
	 */
	public String getInfo(JSONArray iTextInfo) {
		String response = "";
		for (int i = 0; i < iTextInfo.size() - 1; i++) {
			response.concat(((JSONObject) ((JSONObject) iTextInfo.get(i)).get(Constants.sStop_Date_Time))
					.get(Constants.sDeparture_Date_Time) + Constants.sSeparateurDeuxPoints
					+ ((JSONObject) ((JSONObject) ((JSONObject) iTextInfo.get(i)).get(Constants.sRoute))
							.get(Constants.sLine)).get(Constants.sName)
					+ System.lineSeparator());
		}

		return response;
	}

	/**
	 * retour les information des départs de fichier Json
	 * 
	 * @param iJsonText
	 */
	public void getInfoFromJson(String iJsonText) {
		JSONArray lArray = Utils.jsonArrayParser(iJsonText, Constants.sDepartures);
		System.out.println(BundleUtilities.getParam(Constants.sSystemConfig, "msg.prochaine.depart"));
		System.out.println(getInfo(lArray));
	}

	/**
	 * get info from API
	 */
	public void recupererHorraires() {
		StringBuilder stringBuilder;
		try {
			stringBuilder = Utils.readFromStream(lHttpURLConnection);
			String lJsonText = stringBuilder.toString();
			getInfoFromJson(lJsonText);
		} catch (IOException e) {
			System.out.println(BundleUtilities.getParam(Constants.sSystemConfig, "msg.error.read.file"));
		}
	}

	/**
	 * 
	 * @param args
	 */
	public void main(String[] args) {
		try {
			lHttpURLConnection = ConnectionProvider.connectToBase();
			int code = lHttpURLConnection.getResponseCode();
			if (code == Constants.sDEUXCENTS_UN) {
				recupererHorraires();
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		} finally {
			ConnectionProvider.disconnect();
		}
	}

}
