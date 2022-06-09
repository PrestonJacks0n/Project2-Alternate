/*
 * Problem 2 Sell My Pet Food
 */
public class TargetedAd {

  public static void main(String[] args)
  {
    /*  
     * TODO:
     * PREPARATION WORK
     * (1) Create a file called targetWords.txt. Populate this file with words on each line that
     *     you think would determine if a user is a dog or cat owner.
     * 
     * PROGRAMMING
     * (2) Create a new DataCollector object and set the data to "socialMediaPostsSmall.txt" and "targetWords.txt"
     *     Important: Use the socialMedialPostsSmall to create your algorithm. Using a small file will help you 
     *     generate your solution quicker and give you the ability to double check your work.
     * (3) Create a String variable to hold the names of all the user. (The first word of every post is 
     *     a person's username)
     * (4) Compare each user's post to each target word. If a user mentions a target word, add their username to 
     *     the String of users. Separate usernames with a space. 
     *         Hint: You can use loops to look through each word. 
     *         Hint2: You can use indexOf to check if a word is in a user post. 
     * (5) Once you have all the users, use your DataCollector's prepareAdvertisement method to prepare a file 
     *     with all users and the advertisement you will send them.
     *         Additional Info: The prepareAdvertisement creates a new file on your computer. Check the posts of
     *         some of the usernames to make sure your algorithm worked.
     * 
     * THE FINAL SOLUTION
     * (6) Your solution should work with the socialMedialPostsSmall.txt. Modify your DataCollector initialization
     *    so you use the socialMediaPosts.txt. You should now have a larger file of users to target.
     */


    /* your code here */
    DataCollector gatherer = new DataCollector();
    gatherer.setData("socialMediaPosts.txt", "targetWords.txt");
    String names = ""; 
    String currentPost = gatherer.getNextPost();
    String currentTarget = gatherer.getNextTargetWord();
    String firstTarget = currentTarget;
    String modify;
    while(!(currentPost.equals("NONE"))){
      while(!(currentTarget.equals("NONE"))){
        //this if statement below was an attempt to make sure I only got the words, not words like hotdog
        //however, it failed to do so and just ended up not catching words like apcaty <-- something with a word in it
        //it still catches a majority of the desired posts, while sacrificing a few
        modify = currentPost.toLowerCase();
        if((modify.contains(" " + currentTarget + " ") || modify.contains("\"" + currentTarget) || modify.contains(currentTarget + "\"") || 
        modify.contains(currentTarget + "!") || modify.contains(currentTarget + ".") || modify.contains(currentTarget + "?") || 
        modify.contains(currentTarget + ",") || (modify.contains("#") && modify.indexOf(currentTarget) > modify.indexOf("#"))) 
        && !names.contains(currentPost.substring(0, currentPost.indexOf(" ")))){
          names += (currentPost.substring(0, currentPost.indexOf(" ")) + " ");
        }
        currentTarget = gatherer.getNextTargetWord();
      }
      currentPost = gatherer.getNextPost();
      currentTarget = firstTarget;
      currentTarget = gatherer.getNextTargetWord();
    }
    gatherer.prepareAdvertisement("peopletoAdvertise.txt", names, "Click Here for Restaruants that Allow Pets Nearby!");

}
}
