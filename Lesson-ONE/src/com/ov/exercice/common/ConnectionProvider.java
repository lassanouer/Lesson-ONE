package com.ov.exercice.common;

import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 
 * @author Anouer Lassoued
 *
 */
public class ConnectionProvider {

	private static HttpURLConnection sHttpURLConnection = null;
	
	/**
	 * 
	 * @return
	 */
	public static HttpURLConnection connectToBase() {
		URL baseURL;
		try {
			baseURL = new URL(BundleUtilities.getParam(Constants.sSystemConfig, "url.connection"));

			sHttpURLConnection = (HttpURLConnection) baseURL.openConnection();
			sHttpURLConnection.setRequestProperty(Constants.sProp_Content_Length, Constants.sZero);

			String basicAuth = Constants.sBasic + new String(
					Utils.toBase64(BundleUtilities.getParam(Constants.sSystemConfig, "clee.api")).getBytes());
			sHttpURLConnection.setRequestProperty(Constants.sAuthorization, basicAuth);
			sHttpURLConnection.setRequestMethod(Constants.sRequest_Methode_GET);

			sHttpURLConnection.setUseCaches(false);
			sHttpURLConnection.setAllowUserInteraction(false);

			sHttpURLConnection.connect();

		} catch (Exception exception) {
			exception.printStackTrace();
		} finally {
			if (sHttpURLConnection != null) {
				disconnect();
			}
		}
		return sHttpURLConnection;
	}

	
	/**
	 * Disconnect
	 */
	public static void disconnect() {
		if (sHttpURLConnection != null) {
			sHttpURLConnection.disconnect();
		}
	}

}
