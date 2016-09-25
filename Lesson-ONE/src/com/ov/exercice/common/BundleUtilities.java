package com.ov.exercice.common;

import java.util.ResourceBundle;

/**
 * 
 * @author Anouer Lassoued
 *
 */
public class BundleUtilities {

	/**
	 * Gets the param.
	 *
	 * @param bundleName
	 *            the bundle name
	 * @param key
	 *            the key
	 * @return the param
	 */
	public static String getParam(String ibundleName, String ikey) {
		try {
			ResourceBundle bundle = ResourceBundle.getBundle(ibundleName);

			String ret = bundle.getString(ikey);

			return ret;
		} catch (Exception e) {
			e.printStackTrace();
			return ikey;
		}
	}
}
