package ie.atu.sw;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

/**
 * The LexiconLoader class is responsible for reading a sentiment lexicon from a
 * file and creating a Lexicon object.
 */
public class LexiconLoader {

	/**
	 * Loads a lexicon from a specified file path. This method reads the file line
	 * by line and uses the lines to construct a Lexicon object. Each line in the
	 * file should contain a word and its corresponding sentiment score, separated
	 * by a comma.
	 * @return A Lexicon object initialized with the data from the file.
	 * @throws RuntimeException if an I/O error occurs when opening or reading from
	 *                          the file.
	 */
	public Lexicon loadLexicon(String lexiconPath) {
		try {
			// Read all lines from the specified file path and store them in a List<String>
			List<String> lines = Files.readAllLines(Path.of(lexiconPath));

			// Create a new Lexicon object by passing the list of lines to its constructor
			return new Lexicon(lines);
		} catch (IOException e) {
			// Handle any IOException that may occur during the file reading process
			throw new RuntimeException("Error loading lexicon from file: " + lexiconPath, e);
		}
	}
}
