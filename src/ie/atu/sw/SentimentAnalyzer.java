package ie.atu.sw;

import java.util.Scanner;

/**
 * The SentimentAnalyzer class is designed for performing sentiment analysis on a set of tweets.
 * It utilizes a lexicon-based approach where each word in a tweet is scored based on a predefined lexicon,
 * and the overall sentiment of the tweets is determined based on these scores.
 */
public class SentimentAnalyzer {

    // Paths to the lexicon file and Twitter user data file
    private static String lexiconPath;
    private static String twitterUserPath;
    public static String outputFilePath;

    /**
     * Constructor for SentimentAnalyzer.
     * Initializes the paths to the lexicon file, Twitter user data file, and output file path.
     *
     * @param lexiconPath      Path to the lexicon file.
     * @param twitterUserPath  Path to the Twitter user data file.
     * @param outputFilePath   Path to the output file where results will be stored.
     */
    public SentimentAnalyzer(String lexiconPath, String twitterUserPath, String outputFilePath) {
        this.lexiconPath = lexiconPath;
        this.twitterUserPath = twitterUserPath;
        this.outputFilePath = outputFilePath;
    }

    // Method to perform sentiment analysis
    public void analyzeSentiment(String lexiconPath, String twitterUserPath, String outputFilePath) {
        // Load the lexicon from the specified file path using LexiconLoader
        Lexicon lexicon = new LexiconLoader().loadLexicon(lexiconPath);

        // Process tweets from the specified Twitter user using TweetProcessor
        TwitterUserSentiment userSentiment = new TweetProcessor(lexicon, twitterUserPath).processTweets();

        // Print the overall sentiment of the user
        System.out.println("Overall sentiment: " + userSentiment.getOverallSentiment());
    }

    /**
     * The main method for running the sentiment analysis.
     * This method serves as an entry point for command-line execution.
     * It initializes the necessary components and runs the sentiment analysis process.
     *
     */
    public static void main(String[] args) {
        // Create a Scanner to read user input from the console
        Scanner scanner = new Scanner(System.in);

        // Print introductory messages
        System.out.println("Sentiment Analysis using Virtual Threads");
        System.out.println("--------------------------------------");

        // Load the lexicon from the specified file path using LexiconLoader
        Lexicon lexicon = new LexiconLoader().loadLexicon(lexiconPath);

        // Process tweets from the specified Twitter user using TweetProcessor
        TwitterUserSentiment userSentiment = new TweetProcessor(lexicon, twitterUserPath).processTweets();

        // Print the overall sentiment of the user
        System.out.println("Overall sentiment: " + userSentiment.getOverallSentiment());

        // Print lexiconPath for debugging purposes
        System.out.print(ConsoleColour.GREEN);
        System.err.println(lexiconPath);

        // Close the scanner to release resources
        scanner.close();
    }
}
