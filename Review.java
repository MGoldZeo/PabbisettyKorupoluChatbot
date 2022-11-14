import java.io.*;
import java.util.*;


/**
 * Class that contains helper methods for the Review Lab
 **/
public class Review {
  
  private static HashMap<String, Double> sentiment = new HashMap<String, Double>();
  private static ArrayList<String> posAdjectives = new ArrayList<String>();
  private static ArrayList<String> negAdjectives = new ArrayList<String>();
 
  
  private static final String SPACE = " ";

  public static double totalSentiment(String Filename){
    String[] wordList;
    double sum=0.0;
    String rawText = textToString(Filename);
    System.out.println(rawText);
    wordList=rawText.split(SPACE);

    for(int i =0;i<wordList.length;i++){
      double sentVal = sentimentVal(wordList[i]);
      if(wordList[i].length()<3){ //ignore short words
        sentVal=0;
      }else if(wordList[i].equals("not")){ //accounts for words modified by not
        sentVal=-2*sentimentVal(wordList[i+1]);
      }else if(wordList[i].equals("very")){ //accounts for words modified by very
        sentVal=1.5*sentimentVal(wordList[i+1]);
      }else{
        if(wordList[i].substring(wordList[i].length()-1).equals("!")){
          sentVal*=1.2;
      }
      //System.out.println("The sentiment value for the word "+word+" is "+sentimentVal(word));
      sum+=sentVal;
      //sum/=Math.sqrt(wordList.length);
      }
    }
    return sum;//Math.sqrt(wordList.length);
  }

  public static String fakeReview(String Filename){
    String review="";
    String rawText = textToString(Filename);
    String[] wordList=rawText.split(" ");
    Scanner sc = new Scanner(System.in);
    System.out.println("Positive or negative: ");
    String sent = sc.nextLine();
    sc.close();
    for(String word:wordList){
      if(word.substring(0,1).equals("*")){
        if(sent.equals("positive")){
          String randPosAdj = randomPositiveAdj();
          while(sentimentVal(randPosAdj)<sentimentVal(word.substring(1))){
            randPosAdj = randomPositiveAdj();
          }
          review+=(randPosAdj+" ");
        }else if(sent.equals("negative")){
          String randNegAdj = randomNegativeAdj();
          while(sentimentVal(randNegAdj)>sentimentVal(word.substring(1))){
            randNegAdj = randomNegativeAdj();
          }
          review+=(randNegAdj+" ");
        }
      }else{
        review+=(word+" ");
      }
    }
    return review;
  }
  public static int starRating(double val){
    int stars;
    if(val<0){
      stars=-10;
    }else if(val>=0&&val<1){
      stars=-9;
    }else if(val>=1&&val<2){
      stars=-8;
    }else if(val>=2&&val<3){
      stars=-7;
    }else if(val>=3&&val<4){
      stars=-6;
    }else if(val>=4&&val<5){
      stars=-5;
    }else if(val>=5&&val<6){
      stars=-4;
    }else if(val>=6&&val<7){
      stars=-3;
    }else if(val>=7&&val<8){
      stars=-2;
    }else if(val>=8&&val<9){
      stars=-1;
    }else if(val>=9&&val<10){
      stars=0;
    }else if(val>=10&&val<11){
      stars=1;
    }else if(val>=11&&val<12){
      stars=2;
    }else if(val>=13&&val<14){
      stars=3;
    }else if(val>=14&&val<15){
      stars=4;
    }else if(val>=15&&val<16){
      stars=5;
    }else if(val>=16&&val<17){
      stars=6;
    }else if(val>=17&&val<18){
      stars=7;
    }else if(val>=18&&val<19){
      stars=8;
    }else if(val>=19&&val<20){
      stars=9;
    }else{
      stars=10;
    }
    return stars;
  }

  static{
    try {
      Scanner input = new Scanner(new File("cleanSentiment.csv"));
      while(input.hasNextLine()){
        String[] temp = input.nextLine().split(",");
        sentiment.put(temp[0],Double.parseDouble(temp[1]));
        //System.out.println("added "+ temp[0]+", "+temp[1]);
      }
      input.close();
    }
    catch(Exception e){
      System.out.println("Error reading or parsing cleanSentiment.csv");
    }
  
  
    //read in the positive adjectives in postiveAdjectives.txt
     try {
      Scanner input = new Scanner(new File("positiveAdjectives.txt"));
      while(input.hasNextLine()){
        String temp = input.nextLine().trim();
        System.out.println(temp);
        posAdjectives.add(temp);
      }
      input.close();
    }
    catch(Exception e){
      System.out.println("Error reading or parsing postitiveAdjectives.txt\n" + e);
    }   
 
    //read in the negative adjectives in negativeAdjectives.txt
     try {
      Scanner input = new Scanner(new File("negativeAdjectives.txt"));
      while(input.hasNextLine()){
        negAdjectives.add(input.nextLine().trim());
      }
      input.close();
    }
    catch(Exception e){
      System.out.println("Error reading or parsing negativeAdjectives.txt");
    }   
  }
  
  /** 
   * returns a string containing all of the text in fileName (including punctuation), 
   * with words separated by a single space 
   */
  public static String textToString( String fileName )
  {  
    String temp = "";
    try {
      Scanner input = new Scanner(new File(fileName));
      
      //add 'words' in the file to the string, separated by a single space
      while(input.hasNext()){
        temp = temp + input.next() + " ";
      }
      input.close();
      
    }
    catch(Exception e){
      System.out.println("Unable to locate " + fileName);
    }
    //make sure to remove any additional space that may have been added at the end of the string.
    return temp.trim();
  }
  
  /**
   * Returns the sentiment value of word as a number between -1 (very negative) to 1 (very positive sentiment) 
   */
  public static double sentimentVal( String word )
  {
    try
    {
      return sentiment.get(word.toLowerCase());
    }
    catch(Exception e)
    {
      return 0;
    }
  }
  
  /**
   * Returns the ending punctuation of a string, or the empty string if there is none 
   */
  public static String getPunctuation( String word )
  { 
    String punc = "";
    for(int i=word.length()-1; i >= 0; i--){
      if(!Character.isLetterOrDigit(word.charAt(i))){
        punc = punc + word.charAt(i);
      } else {
        return punc;
      }
    }
    return punc;
  }

      /**
   * Returns the word after removing any beginning or ending punctuation
   */
  public static String removePunctuation( String word )
  {
    while(word.length() > 0 && !Character.isAlphabetic(word.charAt(0)))
    {
      word = word.substring(1);
    }
    while(word.length() > 0 && !Character.isAlphabetic(word.charAt(word.length()-1)))
    {
      word = word.substring(0, word.length()-1);
    }
    
    return word;
  }
 
  /** 
   * Randomly picks a positive adjective from the positiveAdjectives.txt file and returns it.
   */
  public static String randomPositiveAdj()
  {
    int index = (int)(Math.random() * posAdjectives.size());
    return posAdjectives.get(index);
  }
  
  /** 
   * Randomly picks a negative adjective from the negativeAdjectives.txt file and returns it.
   */
  public static String randomNegativeAdj()
  {
    int index = (int)(Math.random() * negAdjectives.size());
    return negAdjectives.get(index);
    
  }
  
  /** 
   * Randomly picks a positive or negative adjective and returns it.
   */
  public static String randomAdjective()
  {
    boolean positive = Math.random() < .5;
    if(positive){
      return randomPositiveAdj();
    } else {
      return randomNegativeAdj();
    }
  }
}
