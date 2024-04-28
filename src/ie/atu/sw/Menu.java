package ie.atu.sw;

public class Menu {

    public static void display() {
        System.out.println(ConsoleColour.WHITE);
        System.out.println("************************************************************");
        System.out.println("*     ATU - Dept. of Computer Science & Applied Physics    *");
        System.out.println("*                                                          *");
        System.out.println("*             Virtual Threaded Sentiment Analyser          *");
        System.out.println("*                                                          *");
        System.out.println("************************************************************");
        System.out.println("(1) Specify a Text File");
        System.out.println("(2) Specify a URL");
        System.out.println("(3) Specify an Output File (default: ./out.txt)");
        System.out.println("(4) Configure Lexicons");
        System.out.println("(5) Execute, Analyse and Report");
        System.out.println("(?) Optional Extras...");
        System.out.println("(?) Quit");
        System.out.print(ConsoleColour.BLACK_BOLD_BRIGHT);
        System.out.println();
    }
}
