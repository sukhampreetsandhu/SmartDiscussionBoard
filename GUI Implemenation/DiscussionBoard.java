/* Name:Sukhapreet Sandhu 
 * Student ID: 1274838
 * Compile commands: javac discussionboard/DiscussionBoard.java 
 * Run Command: java discussionboard.DiscussionBoard
 * NOTE: run these commands in the folder where you put the discussionboard folder 
 * as mine was in lab5 I would run this command while in the lab5 folder
 */

 package discussionboard5;

 import java.util.Scanner;
 import java.util.ArrayList;
 import java.util.HashMap;
 
 public class DiscussionBoard {
     // user and post arraylists
     ArrayList<Post> posts = new ArrayList<>();
     ArrayList<User> users = new ArrayList<>();
     HashMap<String, ArrayList<Integer>> userPostIndices = new HashMap<String, ArrayList<Integer>>();
 
     // instance varibales
     int choice;
     String userName;
     String userFullName;
     boolean found = false;
     String keyword;
     // declaring the scanner
     Scanner inputScanner = new Scanner(System.in);
 
     public String registerUser(String userFullName, String userName) {
         User temp;
         found = false;
 
         // try making the user object and cathes any exceptions thorown by the
         // constructor of that
         try {
             temp = new User(userFullName, userName);
         } catch (Exception e) {
             // prints the message from the exception thrown and back to main menu
             String message = e.getMessage();
             return message;
         }
 
         // check if user already exists in the arrayList
         for (User user : users) {
             if (user.getUserName().equals(temp.getUserName())) {
                 found = true;
             }
         }
         // if username exists quit option 1
         if (found) {
             return "Username already exists";
         } else {
             // add the user to the array list users
             users.add(temp);
         }
         return "Created user " + temp.getUserName();
     }
 
     public String createPost(String userName, String postTitle, String postBody) {
         User foundUser = null;
 
         // checks if user exists
         for (User user : users) {
             if (user.getUserName().equals(userName)) {
                 foundUser = user;
             }
         }
 
         // if user not registered
         if (foundUser == null) {
             return "Request denied, user doesn’t have a permission (Register as a user)";
         }
 
         Post newPost;
         // add post to the post arrayList
         try {
             newPost = new Post(postTitle, postBody, foundUser);
         } catch (Exception e) {
             // prints the message from the exception thrown and back to main menu
             String message = e.getMessage();
             return message;
         }
         // adds new post to eh arrylist
         posts.add(newPost);
 
         int postIndex = posts.size() - 1;
 
         // // Add the post index to userPostIndices hashmap
         if (userPostIndices.containsKey(userName)) {
             userPostIndices.get(userName).add(postIndex);
         } else {
             ArrayList<Integer> newPostList = new ArrayList<>();
             newPostList.add(postIndex);
             userPostIndices.put(userName, newPostList);
         }
         return "Your post has been sucesfully posted";
     }
 
     public String searchPost(String userName) {
         userName.toLowerCase();
         StringBuilder result = new StringBuilder();
         // username in the hashmap postIndices
         if (userPostIndices.containsKey(userName)) {
             // get the arraylist of the indices for this user
             ArrayList<Integer> postIndices = userPostIndices.get(userName);
             // loop through the indices and print them
             for (int index : postIndices) {
                 result.append(posts.get(index).toString()).append("\n");
             }
         } else {
             // no user name in the hashmap
             return "No posts by that username.";
         }
         return result.toString();
     }
 }