package discussionboard5;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import javax.swing.Box;
import java.awt.Component;
import java.awt.Dimension;

public class DiscussionBoardGUI extends JFrame {
    public static final int WIDTH = 600;
    public static final int HEIGHT = 400;
    public static final int SMALL_WIDTH = 200; // for error window
    public static final int SMALL_HEIGHT = 100;// for error window
    public static final int NUMBER_OF_CHAR = 30;

    // instance varibales
    private JPanel cardPanel;
    private CardLayout cardLayout;
    private JPanel registerUser;
    private JPanel textPost;
    private JPanel search;
    private DiscussionBoard discussionBoard;
    private JTextField regisetrFullName;
    private JTextField registerUserName;
    private JTextArea registerUserMessageField;
    private JTextArea createPostMessageField;
    private JTextArea searchMessageField;
    private JButton registerButton;
    private JTextArea postBodyText;
    private JTextField postTitle;
    private JTextField createPostUserName;
    private JTextField searchUserNameText;

    // inner class for register panel listener
    private class registerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            cardLayout.show(cardPanel, "Register");
            createPostUserName.setText("");
            postBodyText.setText("");
            postTitle.setText("");
            regisetrFullName.setText("");
            registerUserName.setText("");
            searchUserNameText.setText("");
            registerUserMessageField.setText("");
            createPostMessageField.setText("");
            searchMessageField.setText("");
        }
    }

    // inner class for create post panel listener
    private class textPostListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            cardLayout.show(cardPanel, "TextPost");
            createPostUserName.setText("");
            postBodyText.setText("");
            postTitle.setText("");
            regisetrFullName.setText("");
            registerUserName.setText("");
            searchUserNameText.setText("");
            registerUserMessageField.setText("");
            createPostMessageField.setText("");
            searchMessageField.setText("");
        }
    }

    // inner class for search panel listener
    private class searchListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            cardLayout.show(cardPanel, "Search");
            createPostUserName.setText("");
            postBodyText.setText("");
            postTitle.setText("");
            regisetrFullName.setText("");
            registerUserName.setText("");
            searchUserNameText.setText("");
            registerUserMessageField.setText("");
            createPostMessageField.setText("");
            searchMessageField.setText("");
        }
    }

    // error pop window method
    /**
     * @param errorMessage what message to print out
     */
    private void displayError(String errorMessage) {
        // Create a simple error dialog
        JOptionPane.showMessageDialog(this, errorMessage, "Error", JOptionPane.ERROR_MESSAGE);
    }

    // inner class to add actionlister to button on register panel
    private class registerButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String fullName = regisetrFullName.getText();
            String userName = registerUserName.getText();
            // checks for error
            if (fullName.isEmpty()) {
                displayError("Full name cannot be empty");
            } else {
                // register users
                String message = discussionBoard.registerUser(fullName, userName);
                registerUserMessageField.setText("");
                registerUserMessageField.setText(message);
                // Clear the text fields
                regisetrFullName.setText("");
                registerUserName.setText("");
            }
        }
    }

    // inner class to add actionlister to button on create post panel
    private class createPostButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String userName = createPostUserName.getText();
            String body = postBodyText.getText();
            String title = postTitle.getText();
            // error checking
            if (userName.isEmpty() || body.isEmpty() || title.isEmpty()) {
                if (userName.isEmpty())
                    displayError("Please Enter your username");
                if (title.isEmpty())
                    displayError("Please provide a title for you post");
                if (body.isEmpty())
                    displayError("Body cannot be left empty");
            } else {
                // post the user post
                String message = discussionBoard.createPost(userName, title, body);
                createPostMessageField.setText(message); // Display the result in the message field
                // Clear the text fields
                createPostUserName.setText("");
                postBodyText.setText("");
                postTitle.setText("");
            }
        }
    }

    private class searchButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String userName = searchUserNameText.getText();
            // erorr checking
            if (userName.isEmpty()) {
                displayError("User name filed is empty");
            } else {
                // searhcing and pritng results out
                String message = discussionBoard.searchPost(userName);
                searchMessageField.setText(message);
            }
        }
    }

    /**
     * Setting up the register panels
     * adding all the labels as well as the text input places
     */
    private void setupRegisterPanel() {
        registerUser = new JPanel(new GridLayout(1, 2));
        registerUser.setBackground(Color.WHITE);
        registerUser.setVisible(false);

        // Left panel for labels
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS)); // Vertical stacking
        leftPanel.setBackground(Color.WHITE);
        leftPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel fullName = new JLabel("Full Name");
        fullName.setAlignmentX(Component.CENTER_ALIGNMENT);

        regisetrFullName = new JTextField();
        regisetrFullName.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));

        JLabel userName = new JLabel("Username");
        userName.setAlignmentX(Component.CENTER_ALIGNMENT);

        registerUserName = new JTextField();
        registerUserName.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));

        JLabel title = new JLabel("Register User");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        // button
        registerButton = new JButton("Register");
        registerButton.setBackground(Color.BLUE);
        registerButton.setForeground(Color.white);
        registerButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        registerButton.setMaximumSize(new Dimension(150, 90));

        // adding everything to the left panel with spaces between
        leftPanel.add(title);
        leftPanel.add(Box.createVerticalStrut(20)); // Add spacing
        leftPanel.add(fullName);
        leftPanel.add(Box.createVerticalStrut(10)); // Add spacing
        leftPanel.add(regisetrFullName);
        leftPanel.add(Box.createHorizontalStrut(20));
        leftPanel.add(userName);
        leftPanel.add(Box.createVerticalStrut(10)); // Add spacing
        leftPanel.add(registerUserName);
        leftPanel.add(Box.createVerticalStrut(10)); // Add spacing
        leftPanel.add(registerButton);
        leftPanel.add(Box.createVerticalStrut(100)); // Add spacing

        // Right panel for the message text field
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        rightPanel.setBackground(Color.WHITE);
        rightPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel message = new JLabel("Messages:");
        message.setAlignmentX(Component.CENTER_ALIGNMENT);
        registerUserMessageField = new JTextArea("");
        registerUserMessageField.setBackground(Color.WHITE);
        registerUserMessageField.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        rightPanel.add(Box.createHorizontalGlue());
        rightPanel.add(message);
        rightPanel.add(registerUserMessageField);
        registerButton.addActionListener(new registerButtonListener());

        // Add left and right panels to the main panel
        registerUser.add(leftPanel);
        registerUser.add(rightPanel);
        add(registerUser);
    }

    /**
     * setts upt the create post panel
     * puts all the labels and intput fields
     */
    private void setupPostPanel() {
        textPost = new JPanel(new GridLayout(1, 2));
        textPost.setBackground(Color.WHITE);
        textPost.setVisible(false);

        // Left panel for labels
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS)); // Vertical stacking
        leftPanel.setBackground(Color.WHITE);
        leftPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel userName = new JLabel("Username");
        userName.setAlignmentX(Component.CENTER_ALIGNMENT);

        createPostUserName = new JTextField();
        createPostUserName.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));

        JLabel title = new JLabel("Post Title");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        postTitle = new JTextField();
        postTitle.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));

        JLabel postBody = new JLabel("Post Body");
        postBody.setAlignmentX(Component.CENTER_ALIGNMENT);

        postBodyText = new JTextArea();
        postBodyText.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        postBodyText.setBackground(Color.WHITE);
        // adding scroll bars
        JScrollPane scrolledBody = new JScrollPane(postBodyText);
        postBodyText.setLineWrap(true);
        postBodyText.setWrapStyleWord(true);
        scrolledBody.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrolledBody.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        JLabel paneltitle = new JLabel("Create Post");
        paneltitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        // button
        JButton textPosCreatetButton = new JButton("Create");
        textPosCreatetButton.setBackground(Color.BLUE);
        textPosCreatetButton.setForeground(Color.white);
        textPosCreatetButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        textPosCreatetButton.setMaximumSize(new Dimension(150, 90)); // Set fixed size for button

        // adding all the labels and fiels to the left panel
        leftPanel.add(paneltitle);
        leftPanel.add(Box.createVerticalStrut(20));
        leftPanel.add(userName);
        leftPanel.add(Box.createVerticalStrut(10));
        leftPanel.add(createPostUserName);
        leftPanel.add(Box.createVerticalStrut(10));
        leftPanel.add(title);
        leftPanel.add(Box.createVerticalStrut(10));
        leftPanel.add(postTitle);
        leftPanel.add(Box.createVerticalStrut(10));
        leftPanel.add(postBody);
        leftPanel.add(Box.createVerticalStrut(10));
        leftPanel.add(scrolledBody);
        leftPanel.add(Box.createVerticalStrut(10));
        leftPanel.add(textPosCreatetButton);
        leftPanel.add(Box.createVerticalStrut(10));

        // Right panel for the message text field
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        rightPanel.setBackground(Color.WHITE);
        rightPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel message = new JLabel("Messages:");
        message.setAlignmentX(Component.CENTER_ALIGNMENT);

        createPostMessageField = new JTextArea();
        createPostMessageField.setBackground(Color.WHITE);
        createPostMessageField.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        // adding scroll bars
        JScrollPane scrolledText = new JScrollPane(createPostMessageField);
        scrolledText.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrolledText.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        // Add components to the panel
        rightPanel.add(Box.createHorizontalGlue());
        rightPanel.add(message);
        rightPanel.add(scrolledText);
        textPosCreatetButton.addActionListener(new createPostButtonListener());
        // Add left and right panels to the main panel
        textPost.add(leftPanel);
        textPost.add(rightPanel);
        add(textPost);
    }

    /**
     * set ups the seacrch panel
     * putts labels and fiels on the panel
     */
    private void setupSearchPanel() {
        search = new JPanel(new GridLayout(1, 2));
        search.setBackground(Color.WHITE);
        search.setVisible(false);

        // Left panel for labels
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS)); // Vertical stacking
        leftPanel.setBackground(Color.WHITE);
        leftPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        // putting lables followed by their text fields
        JLabel searchUserName = new JLabel("Username");
        searchUserName.setAlignmentX(Component.CENTER_ALIGNMENT);

        searchUserNameText = new JTextField();
        searchUserNameText.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));

        JLabel title = new JLabel("Search Posts");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        // button
        JButton searchButton = new JButton("Search");
        searchButton.setBackground(Color.BLUE);
        searchButton.setForeground(Color.white);
        searchButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        searchButton.setMaximumSize(new Dimension(150, 30));
        // addin all the labels, buttons and fileds to the left panel
        leftPanel.add(title);
        leftPanel.add(Box.createVerticalStrut(20));
        leftPanel.add(searchUserName);
        leftPanel.add(Box.createVerticalStrut(10));
        leftPanel.add(searchUserNameText);
        leftPanel.add(Box.createVerticalStrut(100));
        leftPanel.add(searchButton);
        leftPanel.add(Box.createVerticalStrut(100));

        // Right panel for the message text field
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        rightPanel.setBackground(Color.WHITE);
        rightPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel message = new JLabel("Messages:");
        message.setAlignmentX(Component.CENTER_ALIGNMENT);

        searchMessageField = new JTextArea();
        searchMessageField.setBackground(Color.WHITE);
        searchMessageField.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        // adding scroll bars
        JScrollPane scrolledText = new JScrollPane(searchMessageField);
        scrolledText.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrolledText.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        // Add components to the panel
        rightPanel.add(Box.createHorizontalGlue());
        rightPanel.add(message);
        rightPanel.add(scrolledText);
        searchButton.addActionListener(new searchButtonListener());
        // Add left and right panels to the main panel
        search.add(leftPanel);
        search.add(rightPanel);
        add(search);
    }

    public static void main(String[] args) {
        DiscussionBoardGUI gui = new DiscussionBoardGUI();
        gui.setVisible(true);
    }

    public DiscussionBoardGUI() {
        super("Discussion Board");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        discussionBoard = new DiscussionBoard();

        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        // setup the panels
        setupRegisterPanel();
        setupPostPanel();
        setupSearchPanel();

        // add panels to the cardpanel
        cardPanel.add(registerUser, "Register");
        cardPanel.add(textPost, "TextPost");
        cardPanel.add(search, "Search");

        add(cardPanel);

        // menue bar
        JMenu optionsMenu = new JMenu("Options");
        JMenuItem registerChoice = new JMenuItem("Register User");
        registerChoice.addActionListener(new registerListener());
        optionsMenu.add(registerChoice);
        JMenuItem textPostChoice = new JMenuItem("Create Post");
        textPostChoice.addActionListener(new textPostListener());
        optionsMenu.add(textPostChoice);
        JMenuItem searchChoice = new JMenuItem("Search Posts");
        searchChoice.addActionListener(new searchListener());
        optionsMenu.add(searchChoice);
        JMenuBar bar = new JMenuBar();
        bar.add(optionsMenu);
        setJMenuBar(bar);
    }
}