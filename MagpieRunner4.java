import java.util.Scanner;

/**
 * A simple class to run the Magpie class.
 * @author Laurie White
 * @version April 2012
 */
public class MagpieRunner4
{

	/**
	 * Create a Magpie, give it user input, and print its replies.
	 */
	public static void main(String[] args)
	{
		Magpie4 maggie = new Magpie4();
		Review r = new Review();
		System.out.println (maggie.getGreeting());
		Scanner in = new Scanner (System.in);
		String statement = in.nextLine();
		in.close();
		while (!statement.equals("Bye"))
		{
			if(statement.equals("Bye")){
				break;
			}
			double sentVal = r.totalSentiment(statement);
			String state = maggie.setState(sentVal);
			System.out.println (maggie.getRandomResponse(state));
			statement = in.nextLine();
		}
		System.out.println("Adieu my friend! It was fun discussing politics with you");	
	}

}
