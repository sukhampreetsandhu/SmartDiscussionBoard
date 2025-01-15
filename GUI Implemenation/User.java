package discussionboard5;

import java.util.StringTokenizer;

public class User {
    // instance varibales
    private String name;
    private String user;

    // constructor for setting the instance varibales
    public User(String fullName, String userName) {
        if (fullName == null || fullName.trim().isEmpty()) {
            throw new IllegalArgumentException("Full name cannot be blank.");
        }
        // take first word of the fullname when username field is empty
        if (userName.trim().isEmpty()) {
            String delimiters = " "; // blank space
            // check if no username is enetered
            if (userName.isEmpty()) {
                // only takes first word in the full name string for username
                StringTokenizer nameFull = new StringTokenizer(fullName, delimiters);
                userName = nameFull.nextToken().toLowerCase();
            } else {
                // only take the first word entered for username
                String[] inputString = userName.split(delimiters);
                userName = inputString[0].toLowerCase();
            }
        }
        name = fullName;
        user = userName;
    }

    // getter for full name
    public String getFullName() {
        return name;
    }

    // getter for user name
    public String getUserName() {
        return user;
    }

}
