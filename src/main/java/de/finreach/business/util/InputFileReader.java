package de.finreach.business.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class InputFileReader {
	
	public List<String> readInputFromFile(String fileName) throws IOException{
		
		List<String> readLines = new ArrayList<String>();
		String currentLine;
		
		@SuppressWarnings("resource")
		BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
		
 		while ((currentLine = bufferedReader.readLine()) != null) {
			System.out.println(currentLine);
			readLines.add(currentLine);
		}
 		
		return readLines;
	}
}
