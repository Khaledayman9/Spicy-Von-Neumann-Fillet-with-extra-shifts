package spicyVonNeumannFilletWithExtraShifts;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Interpreter {

	public int countLines(String fileName) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(new File(fileName + ".txt")));
		int numberOfLines = 0;
		while (br.readLine() != null) {
			numberOfLines++;
		}
		br.close();
		return numberOfLines;
	}

	public String[] readLines(String fileName) throws IOException {
		String currentLine;
		BufferedReader br = new BufferedReader(new FileReader(new File(fileName + ".txt")));
		String[] arr = new String[countLines(fileName)];
		int i = 0;
		while ((currentLine = br.readLine()) != null) {
			arr[i] = currentLine;
			i++;
		}
		br.close();
		return arr;
	}

}