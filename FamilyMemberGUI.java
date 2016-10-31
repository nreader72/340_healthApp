/*
@Author: Preston Turner
paturne4@uncg.edu
9-14-2016
This is a GUI for the Family Member subsystem. The family member will be able to
add their prefernces of food and exercise. They will also be able to put in their
desired weight, whether it be to gain weight or to lose it. All information will
be stored in a text file to be used by other team members.
 */
import java.io.IOException;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class FamilyMemberGUI extends JFrame implements ActionListener {

    //Panels and frame that will be used throughout program.
    static GridBagLayout Grid = new GridBagLayout();
    static JFrame frame;
    static JPanel loginPanel = new JPanel();
    static JPanel mainPanel = new JPanel();
    static JPanel weightPanel = new JPanel();
    static JPanel exercisePanel = new JPanel();
    static JPanel foodPanel = new JPanel();
    static JPanel registerPanel = new JPanel();
    static JPanel newWeightPanel = new JPanel();
    static String loginUser;

    /*
    The main GUI that will be used.
    It will be fullscreen forever.
     */
    private static void fullscreenGUI() {

        //Create and set up the window.
        frame = new JFrame("Family Member Subsystem");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Display the window.
        frame.pack();
        frame.setVisible(true);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

        loginMenu();
    }

    /*
    Create the login menu for first time and returning users.
    Center layout using GridBagLayout.
    Bring user to new window when lgoin or register is selected.
     */
    private static void loginMenu() {

        frame.add(loginPanel);
        loginPanel.setLayout(new GridBagLayout());

        JLabel userLabel = new JLabel("User");
        loginPanel.add(userLabel);

        final JTextField userText = new JTextField(20);
        loginPanel.add(userText);

        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {

                loginUser = userText.getText();
                mainGUI(loginUser);
                loginPanel.setVisible(false);
            }
        });
        loginPanel.add(loginButton);

        JButton registerButton = new JButton("Register");
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                loginUser = userText.getText();
                newUserGUI(loginUser);
                loginPanel.setVisible(false);
            }
        });
        loginPanel.add(registerButton);

        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
            }
        });
        loginPanel.add(exitButton);
    }

    //The main GUI that will be seen by all Family Members.
    private static void mainGUI(String userName) {

        frame.add(mainPanel);
        mainPanel.setVisible(true);
        mainPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20,0,0,0);

        JLabel userLabel = new JLabel("Hello, " + userName + "!");
        gbc.gridx = 0;
        mainPanel.add(userLabel, gbc);

        JButton foodButton = new JButton("Meal Manager");
        foodButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                mainPanel.removeAll();
                mainPanel.revalidate();
                mainPanel.repaint();
                foodGUI();
            }
        });
        mainPanel.add(foodButton, gbc);
        foodButton.requestFocusInWindow();

        JButton weightButton = new JButton("Weight Manager");
        weightButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                mainPanel.removeAll();
                mainPanel.revalidate();
                mainPanel.repaint();
                weightGUI();
            }
        });
        mainPanel.add(weightButton, gbc);
        weightButton.requestFocusInWindow();

        JButton exerciseButton = new JButton("Exercise Manager");
        exerciseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {

                mainPanel.removeAll();
                mainPanel.revalidate();
                mainPanel.repaint();
                exerciseGUI();

            }
        });
        mainPanel.add(exerciseButton, gbc);
        exerciseButton.requestFocusInWindow();

        JButton backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {

                mainPanel.removeAll();
                mainPanel.revalidate();
                mainPanel.repaint();
                loginPanel.setVisible(true);
            }
        });
        mainPanel.add(backButton, gbc);
        backButton.requestFocusInWindow();
    }

    //Exercise GUI that will handle ratings and plans that were completed.
    private static void exerciseGUI() {

        frame.add(exercisePanel);
        exercisePanel.setVisible(true);
        exercisePanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        JButton backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {

                exercisePanel.removeAll();
                exercisePanel.revalidate();
                exercisePanel.repaint();
                mainGUI(loginUser);
            }
        });
        exercisePanel.add(backButton);
        backButton.requestFocusInWindow();
    }
    
    //Weight management GUI that will keep track of target weight.
    private static void weightGUI() {

        frame.add(weightPanel);
        weightPanel.setVisible(true);
        weightPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        
        JLabel weightLabel = new JLabel("Weight goal. " + "Peron's weight goal "
                + "will go here.");
        weightPanel.add(weightLabel);
        
        JButton weightEditButton = new JButton("Edit goal");
        weightEditButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae){
                weightPanel.removeAll();
                weightPanel.revalidate();
                weightPanel.repaint();
                editWeightGUI();
            }
        });
        weightPanel.add(weightEditButton);
        weightEditButton.requestFocusInWindow();

        JButton backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {

                weightPanel.removeAll();
                weightPanel.revalidate();
                weightPanel.repaint();
                mainGUI(loginUser);
            }
        });
        weightPanel.add(backButton);
        backButton.requestFocusInWindow();
    }
    
    //Meal GUI that will handle ratings of meals and how much was consumed.
    private static void foodGUI() {

        frame.add(foodPanel);
        foodPanel.setVisible(true);
        foodPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        JButton backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {

                foodPanel.removeAll();
                foodPanel.revalidate();
                foodPanel.repaint();
                mainGUI(loginUser);

            }
        });
        foodPanel.add(backButton);
        backButton.requestFocusInWindow();
    }
    
    //GUI that will register new user and store their preferences.
    private static void newUserGUI(String userName){
        
        registerPanel.setVisible(true);
        registerPanel.setLayout(new GridBagLayout());
        frame.add(registerPanel);
        
        JLabel randLabel = new JLabel("Random text.");
        registerPanel.add(randLabel);
    }
    
    private static void editWeightGUI(){
        
        frame.add(newWeightPanel);
        newWeightPanel.setVisible(true);
        newWeightPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        
        JLabel newWeightLabel = new JLabel("Enter new weight");
        newWeightPanel.add(newWeightLabel);

        JTextField userText = new JTextField(20);
        newWeightPanel.add(userText);
        
        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {

                newWeightPanel.removeAll();
                newWeightPanel.revalidate();
                newWeightPanel.repaint();
                weightGUI();
            }
        });
        newWeightPanel.add(saveButton);
        saveButton.requestFocusInWindow();
    }

    public static void main(String[] args) {

        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                fullscreenGUI();
            }
        });
    }

    //Ignore.
    @Override
    public void actionPerformed(ActionEvent ae) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}