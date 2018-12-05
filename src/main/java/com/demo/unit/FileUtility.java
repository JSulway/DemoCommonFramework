package com.demo.unit;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.demo.error.CommonErrorCode;
import com.demo.error.CommonRuntimeException;

/**
 * A utility class for commonly used for file operation.
 * 
 * @author sulwayj
 *
 */
public class FileUtility {
	
	private static final Logger logger = Logger.getLogger(FileUtility.class);


	/**
	 * Read file content and return as a string
	 * @param fileName
	 * @return
	 */
	public String readFile(String fileName) {
		String fileContent = null;
		StringBuilder result = new StringBuilder("");

		// Get file from resources
		File file = new File(this.getClass().getClassLoader().getResource(fileName).getFile());

		try (Scanner scanner = new Scanner(file)) {
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				result.append(line).append("\n");
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			logger.error("Error during file read." + e);
			throw new CommonRuntimeException(CommonErrorCode.RESOURCE_NOT_FOUND,  e, "Error during file read" + fileName);
		}
		fileContent = result.toString();
		logger.trace("File content\n:"+ fileContent);
		return fileContent;
	}

}
