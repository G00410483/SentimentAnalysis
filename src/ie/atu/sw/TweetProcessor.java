package ie.atu.sw;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * The TweetProcessor class is responsible for processing a collection of tweets
 * and analyzing their sentiment. This class uses a lexicon-based approach to
 * evaluate the sentiment of each word in the tweets and calculates an overall
 * sentiment score.
 */
public class TweetProcessor {

	// Lexicon for scoring words and path to the file containing tweets
	private Lexicon lexicon;
	private String twitterUserPath;

	/**
	 * Constructor for the TweetProcessor class. Initializes the TweetProcessor with
	 * a lexicon and a path to the file containing tweets.
	 *
	 * @param lexicon         The lexicon used for scoring words in the tweets.
	 * @param twitterUserPath Path to the file containing the tweets.
	 */
	public TweetProcessor(Lexicon lexicon, String twitterUserPath) {
		this.lexicon = lexicon;
		this.twitterUserPath = twitterUserPath;
	}
	
	 /**
     * Processes the tweets from the specified file and calculates their overall sentiment.
     * This method uses virtual multithreading to efficiently process each tweet.
     * The sentiment of each tweet is evaluated, and an overall sentiment is determined.
     *
     * @return TwitterUserSentiment object containing the overall sentiment of the tweets.
     */
	public TwitterUserSentiment processTweets() {
		try {
			// Read all lines from the file containing tweets
			List<String> tweets = Files.readAllLines(Path.of(twitterUserPath));

			// Test if all lines of file are read
			// System.out.println("Number of tweets read: " + tweets.size());

			// Use AtomicInteger for thread-safe accumulation of total score
			AtomicInteger totalScore = new AtomicInteger(0);

			// Create a virtual thread executor
			ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor();

			// Process each tweet in a separate virtual thread
			for (String tweet : tweets) {
				executor.submit(() -> {
					totalScore.addAndGet(computeTweetScore(tweet));
					// System.out.println(tweet);
				});
			}

			// Shutdown the executor and wait for all tasks to complete
			executor.shutdown();

			while (!executor.isTerminated()) {
				// Wait for all tasks to complete
			}

			// Determine overall sentiment based on the total score
			String sentiment = determineOverallSentiment(totalScore.get());
			System.out.println("Total Score: " + totalScore.get() + ", Sentiment: " + sentiment); // Added print
																									// statement

			return new TwitterUserSentiment(sentiment);

		} catch (Exception e) {
			// Throw a runtime exception if there is an error processing tweets
			throw new RuntimeException("Error processing tweets", e);
		}
	}

	 /**
     * Cleans and tokenizes the content of a tweet into words.
     * Non-alphabetic characters are replaced with spaces, and the content is converted to lowercase.
     *
     * @param content The tweet content to be cleaned and tokenized.
     * @return An array of words extracted from the tweet.
     */
	private String[] cleanTweets(String content) {
		// Use replaceAll to replace non-alphabetic characters with a space
		String cleanedContent = content.replaceAll("[^a-zA-Z'’-]+", " ").toLowerCase().trim();
		// System.out.println("Cleaned tweet: " + cleanedContent);
		String[] words = cleanedContent.split("\\s+");
		// System.out.println("Split words: " + Arrays.toString(words)); // Add this
		// line
		return words;
	}

	/**
     * Computes the sentiment score for a single tweet.
     * This method tokenizes the tweet, cleans it, and scores each word using the lexicon.
     * The scores are then accumulated to obtain the total sentiment score of the tweet.
     *
     * @param tweet The tweet for which the sentiment score is to be computed.
     * @return The total sentiment score of the tweet.
     */
	private int computeTweetScore(String tweet) {
		int totalTweetScore = 0;
		// System.out.println(tweet);
		// Tokenize and clean the tweet into words
		String[] words = cleanTweets(tweet);

		// Score each word based on the lexicon and accumulate the total score
		for (String word : words) {
			try {
				double score = lexicon.getScore(word);
				totalTweetScore += score;
				System.out.println(word + " " + score);
			} catch (Exception e) {
				System.out.println("Error processing word: " + word + "; Error: " + e.getMessage());
			}
		}
		return totalTweetScore;
	}

	/**
     * Determines the overall sentiment based on the total sentiment score.
     * The method classifies the sentiment as positive, negative, or neutral based on the score.
     *
     * @param totalScore The total sentiment score of all tweets.
     * @return A string representing the overall sentiment ("Positive", "Negative", or "Neutral").
     */
	private String determineOverallSentiment(int totalScore) {
		// Check if the total score is positive, negative, or neutral, and return the
		// corresponding sentiment
		if (totalScore > 0) {
			return "Positive";
		} else if (totalScore < 0) {
			return "Negative";
		} else {
			return "Neutral";
		}
	}
}
