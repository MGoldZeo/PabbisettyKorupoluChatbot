import java.util.Scanner;

/**
 * A simple class to run the Magpie class.
 *
 * @author Laurie White
 * @version April 2012
 */
public class MagpieRunner4 {
    /**
     * Create a Magpie, give it user input, and print its replies.
     */
    public static void main(String[] args) {
        Magpie4 mr_sunday = new Magpie4();

        Scanner in = new Scanner(System.in);
        System.out.println("Hello user! What is your name?");
        String username = in.nextLine();
        System.out.println("It's so nice to meet you, " + username + "!");
        System.out.println(mr_sunday.getGreeting());
        String statement = in.nextLine();
		System.out.println(mr_sunday.getResponse(statement));
		statement = in.nextLine();
		while (Magpie4.findKeyword(statement, "bye") < 0 && Magpie4.findKeyword(statement, "goodbye") < 0) {
			System.out.println(mr_sunday.getResponse(statement));
			statement = in.nextLine();
		}
    }
}
