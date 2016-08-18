/*
  BundleUtilities.java	java 1.7
  
  Created on 5 ao�t 2015
  Created By Mouna Boukari 
  Copyright(c) 2015 Tunisie T�l�com, Inc. All rights reserved.
  This software is the proprietary information of Tunisie T�l�com.Use is subject to license terms.    
*/
package com.ov.exercice.common;

import java.util.ResourceBundle;

/**
 * The Class BundleUtilities.
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
	public static String getParam(String bundleName, String key) {
		try {
			ResourceBundle bundle = ResourceBundle.getBundle(bundleName);

			String ret = bundle.getString(key);

			return ret;
		} catch (Exception e) {
			e.printStackTrace();
			return key;
		}
	}
}
