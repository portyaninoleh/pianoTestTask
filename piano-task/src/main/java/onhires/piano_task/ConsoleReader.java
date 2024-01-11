package onhires.piano_task;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Scanner;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ConsoleReader {
	
	private final static String DEFAULT_FILE_NAME = "example.json";
	private List<List<Integer>> notes;
	private Integer offset;
	
	public List<List<Integer>> getNotes() {
		return notes;
	}	
	
	public Integer getOffset() {
		return offset;
	}
	
	public void readConsole() {
		Scanner scanner = new Scanner(System.in);
		readInputJsonFileName(scanner);
		readOffset(scanner);
	}

	private void readInputJsonFileName(Scanner scanner) {
		System.out.println("Please enter the input json file (" + DEFAULT_FILE_NAME + " is default one): ");
		
		String fileName = scanner.nextLine();
		if (fileName.isEmpty()) {
			fileName = DEFAULT_FILE_NAME;
		}
		URL resource = getClass().getResource(fileName);
		File file = new File(resource.getPath());
		if (!file.exists()) {
			System.out.println("Wrong file name");
			return;
		}
		readFile(fileName, file);
	}

	private void readFile(String fileName, File file) {
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			notes = objectMapper.readValue(file, List.class);
		} catch (IOException e) {
			System.out.println("Can not read file " + fileName);
		}
	}

	private void readOffset(Scanner scanner) {
		System.out.println("Please enter the offset: ");
		if (scanner.hasNextInt()) {
			offset = scanner.nextInt();
		} else {
			System.out.println("It can not be parset as an offset");
		}
	}
}
