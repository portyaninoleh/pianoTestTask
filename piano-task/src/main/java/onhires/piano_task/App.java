package onhires.piano_task;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

public class App {
  public static void main(String[] args) {
    ConsoleReader consoleReader = new ConsoleReader();
    consoleReader.readConsole();
    consoleReader.getNotes();
    Transpostioner transpostioner = new Transpostioner();
    List<List<Integer>> resultPositions = transpostioner.transposition(consoleReader.getNotes(), consoleReader.getOffset());
    ObjectMapper objectMapper = new ObjectMapper();
    try {
		objectMapper.writeValue(new File("./result.json"), resultPositions);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
  }
}
