import java.util.*;
public class PIQ {
    public static String quiz(String username) {
        Scanner in = new Scanner(System.in);
        System.out.println("What is your name?");
        int total = 0;

        System.out.println("Hello there "+username+", let's take a political ideology quiz!");
        System.out.println("Please respond to each of the following questions with Agree, Maybe, or Disagree");

        //question 1
        System.out.println("Government should not censor speech, press, media or internet.");
        String response = in.nextLine();
        switch (response.toLowerCase()){
            case "agree":total+=1;break;
            case "maybe":total+=0;break;
            case "disagree":total-=1;break;
        }

        //question 2
        System.out.println("Military service should be voluntary. There should be no draft.");
        response = in.nextLine();
        switch (response.toLowerCase()){
            case "agree":total+=1;break;
            case "maybe":total+=0;break;
            case "disagree":total-=1;break;
        }

        //question 3
        System.out.println("There should be no laws regarding sex between consenting adults.");
        response = in.nextLine();
        switch (response.toLowerCase()){
            case "agree":total+=1;break;
            case "maybe":total+=0;break;
            case "disagree":total-=1;break;
        }

        //question 4
        System.out.println("Repeal laws prohibiting adult possession and use of drugs.");
        response = in.nextLine();
        switch (response.toLowerCase()){
            case "agree":total+=1;break;
            case "maybe":total+=0;break;
            case "disagree":total-=1;break;
        }

        //question 5
        System.out.println("Government should not target, detain, and deport undocumented workers.");
        response = in.nextLine();
        switch (response.toLowerCase()){
            case "agree":total+=1;break;
            case "maybe":total+=0;break;
            case "disagree":total-=1;break;
        }

        //question 6
        System.out.println("Taxpayers should NOT be responsible for student loan debt.");
        response = in.nextLine();
        switch (response.toLowerCase()){
            case "agree":total-=1;break;
            case "maybe":total+=0;break;
            case "disagree":total+=1;break;
        }

        //question 7
        System.out.println("Government should not be responsible for providing healthcare.");
        response = in.nextLine();
        switch (response.toLowerCase()){
            case "agree":total-=1;break;
            case "maybe":total+=0;break;
            case "disagree":total+=1;break;
        }

        //question 8
        System.out.println("Let people control their own retirement; privatize Social Security.");
        response = in.nextLine();
        switch (response.toLowerCase()){
            case "agree":total-=1;break;
            case "maybe":total+=0;break;
            case "disagree":total+=1;break;
        }

        //question 9
        System.out.println("Replace government welfare with private charity.");
        response = in.nextLine();
        switch (response.toLowerCase()){
            case "agree":total-=1;break;
            case "maybe":total+=0;break;
            case "disagree":total+=1;break;
        }

        //question 10
        System.out.println("Cut taxes and government spending by 50% or more.");
        response = in.nextLine();
        switch (response.toLowerCase()){
            case "agree":total-=1;break;
            case "maybe":total+=0;break;
            case "disagree":total+=1;break;
        }

        if(total<0){
            return "You are conservative!";
        }
        else if (total==0){
            return "You are centrist!";
        }
        else{
            return "You are liberal!";
        }
    }
}
