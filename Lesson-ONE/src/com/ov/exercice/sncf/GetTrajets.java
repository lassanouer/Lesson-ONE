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
 * Consigne: Enregistrez vous sur https://data.sncf.com/api
 * (http://www.navitia.io/register/) pour récupérer une clé d'API. Ce code va
 * servir à récupérer les horaires de trains au départ de montparnasse. Puis
 * ensuite les afficher sous forme Heure : destination Vous utiliserez votre clé
 * dans l'url d'appel de l'API. Il ne respecte pas les standards et doit être
 * nettoyé puis refactoré pour être réutilisable et compréhensible.
 */

public class GetTrajets {

	private static HttpURLConnection sHttpURLConnection = null;

	public static void getInfoFromJson(String iJsonText) {
		JSONArray lArray = Utils.jsonArrayParser(iJsonText, Constants.sDepartures);
		System.out.println(BundleUtilities.getParam(Constants.sSystemConfig, "msg.prochaine.depart"));
		System.out.println(getInfo(lArray));
	}

	public static void recupererHorraires() {
		StringBuilder stringBuilder;
		try {
			stringBuilder = Utils.readFromStream(sHttpURLConnection);
			String lJsonText = stringBuilder.toString();
			getInfoFromJson(lJsonText);
		} catch (IOException e) {
			System.out.println(BundleUtilities.getParam(Constants.sSystemConfig, "msg.error.read.file"));
		}
	}

	public static void trainsDepartMontparnasse() throws IOException {
		sHttpURLConnection = ConnectionProvider.connectToBase();
		int code = sHttpURLConnection.getResponseCode();
		if (code == Constants.sDEUXCENTS_UN) {
			recupererHorraires();
		}
	}

	public static void main(String[] args) {
		try {
			trainsDepartMontparnasse();
		} catch (Exception exception) {
			exception.printStackTrace();
		} finally {
			ConnectionProvider.disconnect();
		}
	}

	/**
	 * 
	 * @param iTextInfo
	 * @return String informations
	 */
	public static String getInfo(JSONArray iTextInfo) {
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

}
