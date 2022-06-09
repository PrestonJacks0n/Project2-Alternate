/* Problem 2 Sell My Pet Food
 * Author: Preston Jackson
 * Date Started: 5/31/22
 * Date Finished: 6/8/22
 * Course: APCSA, Woldseth, Tri 3 2022
 * 
 * Description: Sorts through social media posts in order to search for keywords, and use the keywords to identify
 * who should be advertised to. 
 * 
 * Preconditions: Program must have social media posts, and target words
 * 
 * Postconditions: Program produces a list of users and their posts that should be target for advertisment based 
 * on the target words. 
 */
public class TargetedAd {

  public static void main(String[] args)
  {
    //My Code
    DataCollector gatherer = new DataCollector();
    gatherer.setData("socialMediaPosts.txt", "targetWords.txt");
    //Variables to hold values including, list of names, and reset point for Target Words
    String names = ""; 
    String currentPost = gatherer.getNextPost();
    String currentTarget = gatherer.getNextTargetWord();
    String firstTarget = currentTarget;
    String modify;
    //while loops to check if more posts or target words are available
    while(!(currentPost.equals("NONE"))){
      while(!(currentTarget.equals("NONE"))){
        modify = currentPost.toLowerCase();
        /*this if statement below was to make sure I only got the words targeted such as dog and 
        *not words like hotdog, it also ensures no duplicates for who should be targeted, and lastly it considers #'s
        */
        if((modify.contains(" " + currentTarget + " ") || modify.contains("\"" + currentTarget) || modify.contains(currentTarget + "\"") || 
        modify.contains(currentTarget + "!") || modify.contains(currentTarget + ".") || modify.contains(currentTarget + "?") || 
        modify.contains(currentTarget + ",") || (modify.contains("#") && modify.indexOf(currentTarget) > modify.indexOf("#"))) 
        && !names.contains(currentPost.substring(0, currentPost.indexOf(" ")))){
          names += (currentPost.substring(0, currentPost.indexOf(" ")) + " ");
        }
        currentTarget = gatherer.getNextTargetWord();
      }
      //Moves to next post and resets targetWords to start
      currentPost = gatherer.getNextPost();
      currentTarget = firstTarget;
      currentTarget = gatherer.getNextTargetWord();
    }
    //Calls prepareAdvertisement, which creates new file as final product
    gatherer.prepareAdvertisement("peopletoAdvertise.txt", names, "Click Here for Restaruants that Allow Pets Nearby!");

}
}
