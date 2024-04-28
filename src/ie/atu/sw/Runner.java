package ie.atu.sw;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * The Runner class serves as the entry point for a sentiment analysis application.
 * It provides a command-line interface for users to interact with the application, allowing them to configure 
 * and run sentiment analysis on specified text data. The class supports various options like specifying text files, 
 * setting up lexicon paths, and executing the analysis process.
 */
public class Runner {
	 // Declaring global variables for file paths and a flag for loaded status
	private static String textFilePath;
	private static String url;
	private static String outputFilePath = "./out.txt";
	private static String lexiconPath;
	private static boolean loaded = false;
	
    /**
     * The main method serves as the entry point for the application. It initializes a scanner
     * to read user input and provides a menu-driven interface for the application's functionality.
     * Users can choose options to specify text files, URLs, lexicon paths, execute analysis, and manage output.
     * The method handles input parsing, validation, and executes corresponding actions based on user selection.
     * @throws InterruptedException if any thread has interrupted the current thread.
     */
	public static void main(String[] args) throws InterruptedException {
		// Initialize a scanner to read input from the user
		Scanner scanner = new Scanner(System.in);
		int option = 0;

		do {
			// Display menu options
			Menu.display();
			System.out.print(ConsoleColour.BLACK_BOLD_BRIGHT);
			System.out.print("Select Option [1-7]>");
			String input = scanner.nextLine();

			try {
				// Parse the input to an integer
				option = Integer.parseInt(input.trim());
			} catch (NumberFormatException e) {
				 // Handle invalid input and prompt again
				System.out.println("Error: Please enter a valid integer.");
				continue;
			}
			
			// Process based on the selected option
			switch (option) {
			case 1:
				// Option to specify a text file path
				System.out.println("Option 1 selected: Specify a Text File");
				System.out.print("Enter the path to the text file: ");
				textFilePath = scanner.nextLine().trim().replaceAll("\"", "");
				break;
			case 2:
				// Option to specify a URL
				System.out.println("Option 2 selected: Specify a URL");
				System.out.print("Enter the URL: ");
				url = scanner.nextLine().trim();
				break;
			case 3:
				// Option to specify the output file path
				System.out.println("Option 3 selected: Specify an Output File");
				System.out.print("Enter the path to the output file (default: ./out.txt): ");
				outputFilePath = scanner.nextLine().trim().replaceAll("\"", "");

				// Conditionally write to the document based on the loaded flag
				if (loaded) {
					try (FileWriter writer = new FileWriter(outputFilePath, true)) {
						writer.write("Option 3 executed with output file path: " + outputFilePath + "\n");
					} catch (IOException e) {
						System.out.println("Error writing to the output file.");
					}
				}
				break;
			case 4:
				// Option to configure lexicon file path
				System.out.println("Option 4 selected: Configure Lexicons");
				System.out.print("Enter the path to the lexicon file: ");
				lexiconPath = scanner.nextLine().trim().replaceAll("\"", "");
				break;
			case 5:
				// Option to execute analysis
				System.out.println("Option 5 selected: Execute, Analyze, and Report");
				if (textFilePath != null && lexiconPath != null) {
					loaded = true;
					PrintLoading pLoad = new PrintLoading();
					pLoad.print();

					SentimentAnalyzer sentimentAnalyzer = new SentimentAnalyzer(lexiconPath, textFilePath, outputFilePath);
					sentimentAnalyzer.analyzeSentiment(lexiconPath, textFilePath, outputFilePath);
				} else {
					System.out.println("Error: Please provide necessary inputs before executing analysis.");
					loaded = false;
				}
				break;
			case 6:
				System.out.println("Optional Extras selected");
				// Add your logic for handling optional extras
				break;
			case 7:
				System.out.println("Quit selected");
				System.exit(0);
				break;
			default:
				System.out.println("Invalid option selected");
				break;
			}

		} while (option != 7); // Continue until the user selects the option to quit

		scanner.close();
	}
}
