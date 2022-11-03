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
		
		System.out.println (maggie.getGreeting());
		Scanner in = new Scanner (System.in);
		String statement = in.nextLine();
		in.close();
		while (!statement.equals("Bye"))
		{
			if(statement.equals("Bye")){
				break;
			}
			System.out.println (maggie.getResponse(statement));
			statement = in.nextLine();
		}
		System.out.println("Adieu my friend! It was fun discussing politics with you");	
	}

}
