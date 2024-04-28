package ie.atu.sw;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The Lexicon class is responsible for managing a sentiment lexicon, which is a collection of words and their associated sentiment scores.
 * It is used in sentiment analysis to determine the sentiment score of individual words found in text.
 * The class stores a map of word scores, where each word (converted to lowercase for consistency) is associated with its sentiment score.
 * The sentiment score is a numerical representation of the word's sentiment, where positive numbers indicate positive sentiment,
 * negative numbers indicate negative sentiment, and zero typically represents neutral or unknown sentiment.
 *
  */
public class Lexicon {

	// Map to store word scores, where the key is the word (in lowercase) and the value is its sentiment score
	private Map<String, Double> wordScores;

	// Constructor that takes a list of lines and initializes the Lexicon object
	public Lexicon(List<String> lines) {
		// Initialize the wordScores map as a HashMap
		wordScores = new HashMap<>();

		// Loop through each line in the provided list of lines
		for (String line : lines) {
			// Split the line into parts based on the comma (,) delimiter
			String[] parts = line.split(",");

			// Check if the line has exactly two parts (word and score)
			if (parts.length == 2) {
				try {
					// Parse the score as a double from the second part of the line
					double score = Double.parseDouble(parts[1]);

					// Put the word (in lower case) and its score into the wordScores map
					wordScores.put(parts[0].toLowerCase(), score);

				} catch (NumberFormatException e) {
					// Handle the case where the score cannot be parsed as a double
					System.err.println("Invalid score format for line: " + line);
				}
			} else {
				// Handle the case where the line does not have the expected format
				System.err.println("Invalid format for line: " + line);
			}
		}
	}

	/**
     * Retrieves the sentiment score for a given word. If the word is not found in the lexicon,
     * a default score of 0.0 is returned, representing a neutral or unknown sentiment.
     *
     * @return the sentiment score of the word, or 0.0 if not present in the lexicon.
     */
	public double getScore(String word) {
		// Return the sentiment score for the given word (in lowercase), or 0.0 if not found
		return wordScores.getOrDefault(word.toLowerCase(), 0.0);
	}
}
