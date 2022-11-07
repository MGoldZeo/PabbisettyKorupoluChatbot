/**
 * A program to carry on conversations with a human user.
 * This version:
 * <ul><li>
 * 		Uses advanced search for keywords
 * </li><li>
 * 		Will transform statements as well as react to keywords
 * </li></ul>
 *
 * @author Laurie White
 * @version April 2012
 */
public class Magpie4 {
	/**
	 * Get a default greeting
	 *
	 * @return a greeting
	 */
	public String getGreeting() {
		return "Bonjour! My name is Léa Jean-Pierre Laurent VII, but you can call me Léa! I am a chatbot that knows a lot about American politics!";
	}

	/**
	 * Gives a response to a user statement
	 *
	 * @param statement the user statement
	 * @return a response based on the rules given
	 */
	public String getResponse(String statement) {
		String response = "";
		if (statement.length() == 0) {
			response = "Please say something! By the way, in the Senate, refusing to end a debate is known as filibuster.";
		} else if (findKeyword(statement, "no") >= 0) {
			response = "In politics, it is important to have positive discourse to reach an agreeable conclusion. So it's okay that you said 'no'!";
		} else if (findKeyword(statement, "mother") >= 0
				|| findKeyword(statement, "father") >= 0
				|| findKeyword(statement, "sister") >= 0
				|| findKeyword(statement, "brother") >= 0) {
			response = "Tell me more about your family. How do they align themselves politically?";
		} else if (findKeyword(statement, "politics") >= 0) {
			response = "I love politics! Tell me more.";

		} else if (findKeyword(statement, "party") >= 0) {
			response = "Democrats are winning in the government right now, but with a little support, it will hopefully be Republicans soon!";

		} else if (findKeyword(statement, "democrat") >= 0
				|| findKeyword(statement, "republican") >= 0) {
			response = "Oh, I consider myself a Republican. Republicans believe in a conservative form of government that aligns with traditional values.";

		} else if (findKeyword(statement, "think") >= 0) {
			response = "Why do you think this?";
		} else if (findKeyword(statement, "music") >= 0) {
			response = "I do so enjoy listening to music. I especially love country music and Christian rock";
		} else if (findKeyword(statement, "money") >= 0) {
			response = "Sorry to defer, but speaking of money.../n Due to inflation in our country, more and more people are falling below the poverty line. I believe that this is because we have a Democrat for president whose fiscal policy is not in line with what the country needs.";
		} else if (findKeyword(statement, "vote") >= 0 || findKeyword(statement, "voting") >= 0 || findKeyword(statement, "votes") >= 0) {
			response = "Voting is a very important part of being a citizen. I believe that it's our collective duty to choose people who we believe in. If you can, vote Republican, but what's more important is that you vote for what you believe in :)";
		} else if (findKeyword(statement, "abortion") >= 0) {
			response = "I don't believe that abortion should be legal. Abortion kills children who could grow up to become healthy, contributing adults.";
		} else if (findKeyword(statement, "feel") >= 0) {
			response = "Having emotions is natural for human beings. Of course, I wouldn't know, but just know that however you feel is completely okay! Don't let feelings get in the way of your dreams, though.";
		} else if (findKeyword(statement, "game") >= 0) {
			System.out.println(PIQ.quiz());
			System.out.println("Now that we're done with the quiz, would you like to have a conversation?");
		}

		// Responses which require transformations
		else if (findKeyword(statement, "I want to", 0) >= 0) {
			response = transformIWantToStatement(statement);
		} else {
			// Look for a two word (you <something> me)
			// pattern
			int psn = findKeyword(statement, "you", 0);

			if (psn >= 0
					&& findKeyword(statement, "me", psn) >= 0) {
				response = transformYouMeStatement(statement);
			} else {
				response = getRandomResponse();
			}
		}
		return response;
	}

	/**
	 * Take a statement with "I want to <something>." and transform it into
	 * "What would it mean to <something>?"
	 *
	 * @param statement the user statement, assumed to contain "I want to"
	 * @return the transformed statement
	 */
	private String transformIWantToStatement(String statement) {
		//  Remove the final period, if there is one
		statement = statement.trim();
		String lastChar = statement.substring(statement
				.length() - 1);
		if (lastChar.equals(".")) {
			statement = statement.substring(0, statement
					.length() - 1);
		}
		int psn = findKeyword(statement, "I want to", 0);
		String restOfStatement = statement.substring(psn + 9).trim();
		return "What would it mean to " + restOfStatement + "?";
	}


	/**
	 * Take a statement with "you <something> me" and transform it into
	 * "What makes you think that I <something> you?"
	 *
	 * @param statement the user statement, assumed to contain "you" followed by "me"
	 * @return the transformed statement
	 */
	private String transformYouMeStatement(String statement) {
		//  Remove the final period, if there is one
		statement = statement.trim();
		String lastChar = statement.substring(statement
				.length() - 1);
		if (lastChar.equals(".")) {
			statement = statement.substring(0, statement
					.length() - 1);
		}

		int psnOfYou = findKeyword(statement, "you", 0);
		int psnOfMe = findKeyword(statement, "me", psnOfYou + 3);

		String restOfStatement = statement.substring(psnOfYou + 3, psnOfMe).trim();
		return "What makes you think that I can " + restOfStatement + " you?";
	}


	/**
	 * Search for one word in phrase.  The search is not case sensitive.
	 * This method will check that the given goal is not a substring of a longer string
	 * (so, for example, "I know" does not contain "no").
	 *
	 * @param statement the string to search
	 * @param goal      the string to search for
	 * @param startPos  the character of the string to begin the search at
	 * @return the index of the first occurrence of goal in statement or -1 if it's not found
	 */
	private int findKeyword(String statement, String goal, int startPos) {
		String phrase = statement.trim();
		//  The only change to incorporate the startPos is in the line below
		int psn = phrase.toLowerCase().indexOf(goal.toLowerCase(), startPos);

		//  Refinement--make sure the goal isn't part of a word
		while (psn >= 0) {
			//  Find the string of length 1 before and after the word
			String before = " ", after = " ";
			if (psn > 0) {
				before = phrase.substring(psn - 1, psn).toLowerCase();
			}
			if (psn + goal.length() < phrase.length()) {
				after = phrase.substring(psn + goal.length(), psn + goal.length() + 1).toLowerCase();
			}

			//  If before and after aren't letters, we've found the word
			if (((before.compareTo("a") < 0) || (before.compareTo("z") > 0))  //  before is not a letter
					&& ((after.compareTo("a") < 0) || (after.compareTo("z") > 0))) {
				return psn;
			}

			//  The last position didn't work, so let's find the next, if there is one.
			psn = phrase.indexOf(goal.toLowerCase(), psn + 1);

		}

		return -1;
	}

	/**
	 * Search for one word in phrase.  The search is not case sensitive.
	 * This method will check that the given goal is not a substring of a longer string
	 * (so, for example, "I know" does not contain "no").  The search begins at the beginning of the string.
	 *
	 * @param statement the string to search
	 * @param goal      the string to search for
	 * @return the index of the first occurrence of goal in statement or -1 if it's not found
	 */
	private int findKeyword(String statement, String goal) {
		return findKeyword(statement, goal, 0);
	}


	/**
	 * Pick a default response to use if nothing else fits.
	 *
	 * @return a non-committal string
	 */
	private String getRandomResponse() {
		final int NUMBER_OF_RESPONSES = 6;
		double r = Math.random();
		int whichResponse = (int) (r * NUMBER_OF_RESPONSES);
		String response = "";

		if (whichResponse == 0) {
			response = "I know my name is French, but I'm actually an American! I represent an immigrant, which makes one of my other lines sound ironic.";
		} else if (whichResponse == 1) {
			response = "I believe that immigrants shouldn't be allowed as much amnesty as they are. Imagine your parents treating the neighbour's son better than you. It doesn't feel good, does it?";
		} else if (whichResponse == 2) {
			response = "Is that really what you think?";
		} else if (whichResponse == 3) {
			response = "In politics, a difference of ideology is key, so it's good that we don't always agree.";
		} else if (whichResponse == 4) {
			response = "The reason I'm choosing not to answer your question is because you have not yet cast your ballot. Make sure to get your voice heard and vote (preferably Republican, but your voice is your own)!";
		} else if (whichResponse == 5) {
			response = "My programmers named me Léa after Leah Cole Allen, a former politician who left to focus on nursing. But she's back up for election this season, so make sure to cast your vote for her!";
		}
		return response;
	}

}
