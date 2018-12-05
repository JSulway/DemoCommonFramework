package com.demo.properties;

import java.util.Properties;

/**
 * Property helper class which extends to provide property access methods
 * catering for substitution and replacement operations 
 * 
 * @author sulwayj
 *
 */
public class PropertyHelper extends Properties {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Returns the value associated to the provided property key
	 * 
	 * @param key
	 * @return
	 */
	public String getPropertyValue(String key){
		return getProperty(key);
	}
	
	/**
	 * Returns the specified property and replaces any templates with the values provided
	 * 
	 * @param key
	 * @param substitutionValues
	 * @return
	 */
	public String getPropertyAndReplaceTemplates(String key,String... substitutionValues){
		String value = getProperty(key);
		if(value != null){
			if(substitutionValues != null){
				int count = 0;
				for(String subValue : substitutionValues){
					if(subValue != null){
						count++;
						String replaceTemplateString = "{" + count + "}";
						value = value.replace(replaceTemplateString, subValue);
					}
				}
				
			}
		}
		return value;
	}
	
	
}
