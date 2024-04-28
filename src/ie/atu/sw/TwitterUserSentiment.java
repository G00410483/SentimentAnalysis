package ie.atu.sw;

/**
 * Represents the overall sentiment of a Twitter user's tweets.
 * This class encapsulates the result of sentiment analysis performed on a collection of tweets.
 * It stores the collective sentiment expressed across the user's tweets, determined by analyzing individual tweet sentiments.
 *
 */
public class TwitterUserSentiment {

    // Private member variable to store the overall sentiment of a Twitter user
    private String overallSentiment;

    /**
     * Constructor for the TwitterUserSentiment class.
     * Initializes the TwitterUserSentiment object with a specific overall sentiment.
     *
     * @param overallSentiment The overall sentiment of the Twitter user's tweets.
     */
    public TwitterUserSentiment(String overallSentiment) {
        // Set the overallSentiment member variable to the provided overall sentiment
        this.overallSentiment = overallSentiment;
    }

    /**
     * Retrieves the overall sentiment of the Twitter user.
     * This method returns the overall sentiment that has been analyzed and set for the user's tweets.
     *
     * @return The overall sentiment of the Twitter user's tweets.
     */
    public String getOverallSentiment() {
        // Return the overall sentiment stored in the private member variable
        return overallSentiment;
    }
}
