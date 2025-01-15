package discussionboard5;

public class Post {
    // instance varibales
    private String title;
    private String content;
    private User user;

    // constructor to set the instance varibales
    public Post(String postTitle, String postContent, User userInfo) {
        // check for empty post title
        if (postTitle == null || postTitle.trim().isEmpty()) {
            throw new IllegalArgumentException("Post title cannot be empty");
        }
        // check for empty post conent
        if(postContent == null || postContent.trim().isEmpty()){
            throw new IllegalArgumentException("Post content cannot be empty");

        }
        title = postTitle;
        content = postContent;
        user = userInfo;
    }

    // getter for title
    public String getTitle() {
        return title;
    }

    // getter for content
    public String getContent() {
        return content;
    }

    // getter for username
    public String getUserName() {
        return user.getUserName();
    }

    // getter for user full name
    public String getUserFullname() {
        return user.getFullName();
    }

    // printing the post
    public String toString() {
        return "\nCreated By: " + user.getFullName() + "(@" + user.getUserName() + ")\n" + "Title: " + title + "\n"
                + content;
    }
}
