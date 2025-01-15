/* Name:Sukhapreet Sandhu 
 * Student ID: 1274838
 * Compile commands: javac discussionboard/DiscussionBoard.java 
 * Run Command: java discussionboard.DiscussionBoard
 * NOTE: run these commands in the folder where you put the discussionboard folder 
 * as mine was in lab4 I would run this command while in the lab4 folder
 */

package discussionboard;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

public class DiscussionBoard {
    /**
     * @param args main method
     *             Handles all the logic, which option to proceed
     */
    public static void main(String[] args) {
        // declaring the scanner
        Scanner inputScanner = new Scanner(System.in);

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

        // do while loop for the main menue, keeps running until user inputs choice as 6
        do {
            // display options and scan user input
            System.out.println(
                    "\n(1) Create a new user\n(2) Create new post\n(3) View all posts\n(4) View all posts with a given username\n(5) End Program");
            // helper fucntion call to get input
            choice = getIntInput("Enter your choice: ", inputScanner);

            // switch cases to execute different options selected
            switch (choice) {
                case 1:
                    User temp;
                    found = false;
                    // asks user for full name and a username
                    System.out.print("Enter your full name: ");
                    userFullName = inputScanner.nextLine();

                    System.out.print("Enter your username: ");
                    // scans user name and converts to lowercase
                    userName = inputScanner.nextLine().toLowerCase();

                    // try making the user object and cathes any exceptions thorown by the
                    // constructor of that
                    try {
                        temp = new User(userFullName, userName);
                    } catch (Exception e) {
                        // prints the message from the exception thrown and back to main menu
                        String message = e.getMessage();
                        System.out.println(message);
                        continue;
                    }

                    // check if user already exists in the arrayList
                    for (User user : users) {
                        if (user.getUserName().equals(temp.getUserName())) {
                            found = true;
                        }
                    }
                    // if username exists quit option 1
                    if (found) {
                        System.out.println("Username already exists");
                        break;
                    } else {
                        // add the user to the array list users
                        users.add(temp);
                    }
                    break;

                case 2:
                    // scans user input
                    System.out.print("Enter username: ");
                    userName = inputScanner.nextLine().toLowerCase();

                    // no empty username for making a post
                    while (userName.isEmpty()) {
                        System.out.print("Enter a valid username: ");
                        userName = inputScanner.nextLine().toLowerCase();
                    }
                    User foundUser = null;

                    // checks if user exists
                    for (User user : users) {
                        if (user.getUserName().equals(userName)) {
                            foundUser = user;
                        }
                    }

                    // if user not registered
                    if (foundUser == null) {
                        System.out.println("Request denied, user doesn’t have a permission (Register as a user)");
                        break;
                    }

                    // create title and content for the post
                    System.out.print("Enter post title: ");
                    String title = inputScanner.nextLine();
                    System.out.print("Enter content: ");
                    String content = inputScanner.nextLine();

                    Post newPost;
                    // add post to the post arrayList
                    // posts.add(new Post(title, content, foundUser));
                    try {
                        newPost = new Post(title, content, foundUser);
                    } catch (Exception e) {
                        // prints the message from the exception thrown and back to main menu
                        String message = e.getMessage();
                        System.out.println(message);
                        continue;
                    }
                    // adds new post to eh arrylist
                    posts.add(newPost);

                    int postIndex = posts.size() - 1;

                    // Add the post index to userPostIndices hashmap
                    if (userPostIndices.containsKey(userName)) {
                        userPostIndices.get(userName).add(postIndex);
                    } else {
                        ArrayList<Integer> newPostList = new ArrayList<>();
                        newPostList.add(postIndex);
                        userPostIndices.put(userName, newPostList);
                    }

                    break;
                case 3:
                    // is the discussion board empty
                    if (posts.size() == 0) {
                        System.out.println("There are no posts.");
                    } else {
                        // not empty, print all the posts
                        for (Post post : posts) {
                            System.out.println(post.toString());
                        }
                    }
                    break;

                case 4:
                    // scans user input
                    System.out.print("Enter username to view its all posts: ");
                    userName = inputScanner.nextLine().toLowerCase();

                    // username in the hashmap postIndices
                    if (userPostIndices.containsKey(userName)) {
                        // get the arraylist of the indices for this user
                        ArrayList<Integer> postIndices = userPostIndices.get(userName);
                        // loop through the indices and print them
                        for (int index : postIndices) {
                            System.out.println(posts.get(index).toString());
                        }
                    } else {
                        // no user name in the hashmap
                        System.out.println("No posts by that username.");
                    }
                    break;
                case 5:
                    break;
                default:
                    System.out.println("Invalid option");
            }
        } while (choice != 5);
        // close scanner
        inputScanner.close();
    }

    // helper function to get int input from user
    public static int getIntInput(String prompt, Scanner inputScanner) {
        int value;
        while (true) {
            // print whatever prompt for user
            System.out.print(prompt);
            // try catch to validate the input
            try {
                // getting integer from the input
                value = Integer.parseInt(inputScanner.nextLine().trim());
                return value;
                // catches the error and reprompts the user 
            } catch (NumberFormatException e) {
                // reask user for invalid input
                System.out.println("Invalid input. Please enter a valid integer.");
            }
        }
    }
}