import java.util.Scanner;

/**
 * A simple class to run the Magpie class.
 * @author Laurie White
 * @version April 2012
 */
public class MagpieRunner4
{
	public String username = new String();
	/**
	 * Create a Magpie, give it user input, and print its replies.
	 */
	public static void main(String[] args)
	{
		Magpie4 mr_sunday = new Magpie4();
		
		System.out.println (mr_sunday.getGreeting());
		Scanner in = new Scanner (System.in);
		String username = in.nextLine();
		System.out.println("How was your day today?");
		String statement = in.nextLine();
		while (!statement.equals("Bye"))
		{
			System.out.println (mr_sunday.getResponse(statement));
			statement = in.nextLine();
		}
	}
}
