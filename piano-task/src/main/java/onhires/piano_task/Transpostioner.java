package onhires.piano_task;

import java.util.List;
import java.util.stream.Collectors;

public class Transpostioner {

	private final static Integer MIN_OCTAVE_NUMBER = -3;
	private final static Integer MIN_OCTAVE_MIN_NUMBER = 10;
	private final static Integer MAX_OCTAVE_NUMBER = 5;
	private final static Integer MAX_OCTAVE_MAX_NUMBER = 1;
	private final static Integer NOTE_NUMBER_IN_OCTAVE = 12;
	
	public List<List<Integer>> transposition(List<List<Integer>> positions, Integer offset) {
		List<List<Integer>> resultPositions = positions.stream().map(position -> {
			
			int crossOctaveNote = position.get(1) + offset;
			int octaveShift = getOctaveShift(crossOctaveNote);
			int resultOctave = position.get(0) + octaveShift;
			int octaveNotes = getOctaveNotes(octaveShift, offset);
			
			int nonOctaveNotesDiff = offset - octaveNotes;
			int resultNote = getResultNote(position, nonOctaveNotesDiff);
			
			
			if (resultOctave < MIN_OCTAVE_NUMBER || resultOctave > MAX_OCTAVE_NUMBER || 
					(resultOctave == MIN_OCTAVE_NUMBER && resultNote < MIN_OCTAVE_MIN_NUMBER) || 
					(resultOctave == MAX_OCTAVE_NUMBER && resultNote > MAX_OCTAVE_MAX_NUMBER)) {
				throw new RuntimeException("Incorrect offset");
			}
			return List.of(resultOctave, resultNote);
		}).collect(Collectors.toList());
		return resultPositions;
	}

	private int getResultNote(List<Integer> position, int nonOctaveNotesDiff) {
		int note = position.get(1) + nonOctaveNotesDiff;
		if (note < 0) {
			return NOTE_NUMBER_IN_OCTAVE + note;
		}
		return 0 + note;
	}

	private int getOctaveNotes(int octaveShift, int offset) {
		if (offset < NOTE_NUMBER_IN_OCTAVE) {
			return 0;
		}
		return octaveShift * NOTE_NUMBER_IN_OCTAVE;
	}

	private int getOctaveShift(int crossOctaveNote) {
		int octaveShift = crossOctaveNote / NOTE_NUMBER_IN_OCTAVE;
		if (octaveShift == 0 && crossOctaveNote < 0) {
			return -1;
		}
		return octaveShift;
	}
}
