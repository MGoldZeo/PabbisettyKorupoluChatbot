import java.util.*;

public class PIQ {
    public static String quiz() {
        Scanner in = new Scanner(System.in);
        int total = 0;

        System.out.println("Let's take a political ideology quiz!");
        System.out.println("There are ten questions in total. Please respond to each statement with Agree, Maybe, or Disagree");
        System.out.println();

        //question 1
        System.out.println("The government should censor speech, the press, media or the internet.");
        String response = loopQ(in.nextLine());
        total += tChange(response);

        //question 2
        System.out.println("Military service should be voluntary. There should be no draft.");
        response = loopQ(in.nextLine());
        total += tChange(response);


        //question 3
        System.out.println("Laws regarding sex between consenting adults should not exist.");
        response = loopQ(in.nextLine());
        total += tChange(response);


        //question 4
        System.out.println("Laws regarding the possession and use of drugs as an adult should be repealed.");
        response = loopQ(in.nextLine());
        total += tChange(response);


        //question 5
        System.out.println("The government should not target, detain, or deport undocumented workers.");
        response = loopQ(in.nextLine());
        total += tChange(response);


        //question 6
        System.out.println("Taxpayers should be responsible for student loan debt.");
        response = loopQ(in.nextLine());
        total += tChange(response);


        //question 7
        System.out.println("The government should be responsible for providing healthcare for all citizens.");
        response = loopQ(in.nextLine());
        total += tChange(response);


        //question 8
        System.out.println("Social Security should not be privatised. Everyone has a right to benefit.");
        response = loopQ(in.nextLine());
        total += tChange(response);


        //question 9
        System.out.println("Do not replace government welfare with private charity.");
        response = loopQ(in.nextLine());
        total += tChange(response);


        //question 10
        System.out.println("Cut taxes and government spending by 50% or more.");
        response = loopQ(in.nextLine());
        total += tChange(response);

        System.out.println("You're finished with the quiz! Here are your results:");


        if (total < -1) {
            return "You are conservative, with a score of " + total + "!";
        } else if (total <= 1 && total >= -1) {
            return "You are centrist, with a score of " + total + "!";
        } else {
            return "You are liberal, with a score of " + total + "!";
        }
    }

    private static int tChange(String txt) {
        int change = 0;
        switch (txt.toLowerCase()) {
            case "agree" -> change = 1;
            case "maybe" -> change = 0;
            case "disagree" -> change = -1;
        }
        return change;
    }

    private static String loopQ(String txt) {
        Scanner ne = new Scanner(System.in);
        while (!txt.equalsIgnoreCase("agree") && !txt.equalsIgnoreCase("disagree") && !txt.equalsIgnoreCase("maybe")) {
            System.out.println("Please answer with 'agree', 'disagree' or 'maybe'.");
            txt = ne.nextLine();
        }
        return txt;
    }
}
