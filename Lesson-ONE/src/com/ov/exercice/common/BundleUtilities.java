/*
  BundleUtilities.java	java 1.7
  
  Created on 5 août 2015
  Created By Mouna Boukari 
  Copyright(c) 2015 Tunisie Télécom, Inc. All rights reserved.
  This software is the proprietary information of Tunisie Télécom.Use is subject to license terms.    
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
