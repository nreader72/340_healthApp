/*
@Author: Preston Turner
paturne4@uncg.edu
9-14-2016
This is a GUI for the Family Member subsystem. The family member will be able to
add their prefernces of food and exercise. They will also be able to put in their
desired weight, whether it be to gain weight or to lose it. All information will
be stored in a text file to be used by other team members.
 */

import java.io.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.text.BadLocationException;

/**
 * Main class that will call and handle user interaction.
 *
 * @author paturne4
 */
public class FamilyMemberGUI {

    //Panels and frame that will be used throughout program.
    static GridBagLayout Grid = new GridBagLayout();
    static JFrame frame;
    static JPanel loginPanel = new JPanel(new GridBagLayout());
    static JPanel mainPanel = new JPanel(new GridBagLayout());
    static JPanel weightPanel = new JPanel(new GridBagLayout());
    static JPanel exercisePanel = new JPanel(new GridBagLayout());
    static JPanel foodPanel = new JPanel(new GridBagLayout());
    static JPanel registerPanel = new JPanel(new GridBagLayout());
    static JPanel newWeightPanel = new JPanel(new GridBagLayout());
    static JPanel exercisePrefPanel = new JPanel(new GridBagLayout());
    static JPanel foodPrefPanel = new JPanel(new GridBagLayout());
    static JPanel weightPrefPanel = new JPanel(new GridBagLayout());
    static JPanel extraPanel = new JPanel(new GridBagLayout());
    static String loginUser;

    /**
     * Display the GUI.
     */
    public static void fullscreenGUI() throws IOException {

        //Create and set up the window.
        frame = new JFrame("Family Member Subsystem");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Display the window.
        frame.pack();
        frame.setVisible(true);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        loginMenu();
    }

    /**
     * Asks user for their name and checks to see if the name is already in use.
     */
    public static void loginMenu() throws IOException {

        frame.add(loginPanel);
        loginPanel.setLayout(new GridBagLayout());

        JTextField userText = new JTextField(20);

        loginUser = userText.getText();

        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {

                loginUser = userText.getText();
                if (RegisterGUI.isUser(loginUser)) {
                    loginPanel.removeAll();
                    loginPanel.revalidate();
                    loginPanel.repaint();
                    try {
                        mainGUI(loginUser);
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(FamilyMemberGUI.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    JOptionPane.showMessageDialog(frame, "Error! Name not registered!");
                    try {
                        loginMenu();
                    } catch (IOException ex) {
                        Logger.getLogger(FamilyMemberGUI.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });

        JButton registerButton = new JButton("Register");
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {

                loginUser = userText.getText();
                try {
                    if (!RegisterGUI.checkNewUser(loginUser)) {
                        loginMenu();
                    } else {
                        loginPanel.removeAll();
                        loginPanel.revalidate();
                        loginPanel.repaint();
                        RegisterGUI.exercisePref(loginUser);
                    }
                } catch (IOException ex) {
                    Logger.getLogger(FamilyMemberGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
            }
        });

        loginPanel.add(userText);
        userText.requestFocus();
        loginPanel.add(loginButton);
        loginButton.requestFocus();
        loginPanel.add(registerButton);
        registerButton.requestFocus();
        loginPanel.add(exitButton);
        exitButton.requestFocus();

        loginPanel.setVisible(true);
    }

    /**
     * Lets user access or view their information. Has access to meals, weight,
     * and exercise plans.
     *
     * @param userName
     * @throws java.io.FileNotFoundException
     */
    public static void mainGUI(String userName) throws FileNotFoundException {

        File mpFile = new File("C://" + loginUser, loginUser + "MP.txt");
        File ptFile = new File("C://" + loginUser, loginUser + "PT.txt");

        frame.add(mainPanel);
        mainPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20, 0, 0, 0);
        gbc.gridx = 0;

        JTextField name = new JTextField("Hello, " + loginUser + "!");
        name.setEditable(false);

        JButton foodButton = new JButton("Meal Manager");
        foodButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                mainPanel.removeAll();
                mainPanel.revalidate();
                mainPanel.repaint();
                try {
                    FoodGUI.foodGUI();
                } catch (IOException ex) {
                    Logger.getLogger(FamilyMemberGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        JButton weightButton = new JButton("Weight Manager");
        weightButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                mainPanel.removeAll();
                mainPanel.revalidate();
                mainPanel.repaint();
                try {
                    WeightGUI.weightGUI(userName);
                } catch (IOException ex) {
                    Logger.getLogger(FamilyMemberGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        JButton exerciseButton = new JButton("Exercise Manager");
        exerciseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {

                mainPanel.removeAll();
                mainPanel.revalidate();
                mainPanel.repaint();
                try {
                    ExerciseGUI.exerciseGUI();
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(FamilyMemberGUI.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(FamilyMemberGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        JButton backButton = new JButton("Logout");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {

                mainPanel.removeAll();
                mainPanel.revalidate();
                mainPanel.repaint();
                try {
                    loginMenu();
                } catch (IOException ex) {
                    Logger.getLogger(FamilyMemberGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        mainPanel.add(name, gbc);
        gbc.ipadx = 50;
        mainPanel.add(foodButton, gbc);
        foodButton.requestFocus();
        gbc.ipadx = 40;
        mainPanel.add(weightButton, gbc);
        weightButton.requestFocus();
        gbc.ipadx = 30;
        mainPanel.add(exerciseButton, gbc);
        exerciseButton.requestFocus();
        gbc.ipadx = 95;
        mainPanel.add(backButton, gbc);
        backButton.requestFocus();

        if (mpFile.exists()) {
            int n = JOptionPane.showConfirmDialog(frame, "Would you like to go to MP subsytem?",
                    "", JOptionPane.YES_NO_OPTION);
            if (n == 0) {
                mealPlannerGUI mp = new mealPlannerGUI();
                JPanel panel = new JPanel();
                mp.MealPlanner(panel);
                mp.makeJFrame(panel, "Meal Planner", 500, 500);
            }
        }
        if (ptFile.exists()) {
            int n = JOptionPane.showConfirmDialog(frame, "Would you like to go to PT subsytem?",
                    "", JOptionPane.YES_NO_OPTION);
            if (n == 0) {
                PT pt = new PT();
            }
        }

        mainPanel.setVisible(true);
    }

    /**
     * Starts the application.
     *
     * @param args
     */
    public static void main(String[] args) {

        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    fullscreenGUI();
                } catch (IOException ex) {
                    Logger.getLogger(FamilyMemberGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
}

/**
 * Handles the users weight information. Will display their current weight,
 * weight goal, and weekly weight. User can change weight at anytime.
 *
 * @author paturne4
 */
class WeightGUI extends FamilyMemberGUI {

    /**
     * Displays users current weight with their goal. Offers option to change
     * weight goal.
     *
     * @param userName
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static void weightGUI(String userName) throws FileNotFoundException, IOException {

        frame.add(weightPanel);
        weightPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        JTextField weightLabel = new JTextField("Weight goal: ", 7);

        JLabel spaceMaker = new JLabel("        ");

        JTextField currWeight = new JTextField("Current weight: ");

        JButton weightEditButton = new JButton("Weight management");
        weightEditButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                weightPanel.removeAll();
                weightPanel.revalidate();
                weightPanel.repaint();
                try {
                    editWeightGUI(userName);
                } catch (IOException ex) {
                    Logger.getLogger(WeightGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        JButton backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {

                weightPanel.removeAll();
                weightPanel.revalidate();
                weightPanel.repaint();
                try {
                    FamilyMemberGUI.mainGUI(loginUser);
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(WeightGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        weightPanel.add(weightLabel, gbc);
        weightLabel.setEditable(false);
        weightLabel.requestFocus();
        ViewWeight.weightGoal(userName);
        weightPanel.add(spaceMaker, gbc);
        weightPanel.add(currWeight, gbc);
        currWeight.setEditable(false);
        currWeight.requestFocus();
        Weight.getCurrentWeight(userName);
        weightPanel.add(weightEditButton, gbc);
        weightEditButton.requestFocus();
        weightPanel.add(backButton, gbc);
        backButton.requestFocus();

        weightPanel.setVisible(true);
    }

    /**
     * Lets the user change their weight goal to a different yet reasonable
     * goal.
     *
     * @param userName
     */
    public static void editWeightGUI(String userName) throws FileNotFoundException, IOException {

        File userFile = new File("C://" + userName, userName + "WeightPref.txt");
        File goalFile = new File("C://" + userName, userName + "WeightGoal.txt");
        frame.add(newWeightPanel);
        newWeightPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        FileReader reader = new FileReader(userFile);
        FileReader reader2 = new FileReader(goalFile);

        JTextField userText = new JTextField(25);
        userText.read(reader, userFile);
        JTextField newGoal = new JTextField(20);
        newGoal.read(reader2, goalFile);
        JTextField txt1 = new JTextField("Weekly weight");
        txt1.setEditable(false);
        JTextField txt2 = new JTextField("New weight goal");
        txt2.setEditable(false);

        String tempTxt1 = userText.getText();
        String tempTxt2 = newGoal.getText();

        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {

                newWeightPanel.removeAll();
                newWeightPanel.revalidate();
                newWeightPanel.repaint();
                try {
                    try (BufferedWriter fileOut = new BufferedWriter(new FileWriter(userFile))) {
                        userText.write(fileOut);
                    } catch (IOException ex) {
                        Logger.getLogger(RegisterGUI.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    try (BufferedWriter fileOut2 = new BufferedWriter(new FileWriter(goalFile))) {
                        newGoal.write(fileOut2);
                    } catch (IOException ex) {
                        Logger.getLogger(RegisterGUI.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    weightGUI(userName);
                } catch (IOException ex) {
                    Logger.getLogger(WeightGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        JButton backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {

                newWeightPanel.removeAll();
                newWeightPanel.revalidate();
                newWeightPanel.repaint();
                try {
                    WeightGUI.weightGUI(loginUser);
                } catch (IOException ex) {
                    Logger.getLogger(WeightGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        newWeightPanel.add(userText);
        userText.requestFocus();
        newWeightPanel.add(newGoal, gbc);
        newWeightPanel.add(saveButton);
        saveButton.requestFocus();
        newWeightPanel.add(backButton);
        backButton.requestFocus();
        gbc.gridy = 1;
        newWeightPanel.add(txt1, gbc);
        txt1.requestFocus();
        newWeightPanel.add(txt2, gbc);
        txt2.requestFocus();
        newWeightPanel.setVisible(true);
    }
}

/**
 * Handles all information regarding the users meal preferences, how much they
 * ate, and how they rate the meal. If user did not eat at home, ask for caloric
 * intake instead.
 *
 * @author paturne4
 */
class FoodGUI extends FamilyMemberGUI {

    public static void foodGUI() throws IOException {

        frame.add(foodPanel);
        foodPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;

        JButton viewPlan = new JButton("View meal plan");
        viewPlan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {

                foodPanel.removeAll();
                foodPanel.revalidate();
                foodPanel.repaint();
                try {
                    Ratings.setMealRatings(loginUser);
                } catch (IOException ex) {
                    Logger.getLogger(ExerciseGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        JButton ratePlan = new JButton("Rate plan");
        ratePlan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {

                foodPanel.removeAll();
                foodPanel.revalidate();
                foodPanel.repaint();
                try {
                    Ratings.getMealRatings(loginUser);
                } catch (IOException ex) {
                    Logger.getLogger(ExerciseGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        JButton backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {

                foodPanel.removeAll();
                foodPanel.revalidate();
                foodPanel.repaint();
                try {
                    mainGUI(loginUser);
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(ExerciseGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        foodPanel.add(viewPlan);
        viewPlan.requestFocus();
        foodPanel.add(ratePlan);
        ratePlan.requestFocus();
        foodPanel.add(backButton);
        backButton.requestFocus();
        foodPanel.setVisible(true);
    }
}

/**
 * Handles all information about users exercise ratings and activities/plans
 * that were completed. Weekly weight will also be displayed.
 *
 * @author paturne4
 */
class ExerciseGUI extends FamilyMemberGUI {

    public static void exerciseGUI() throws FileNotFoundException, IOException {

        frame.add(exercisePanel);
        exercisePanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;

        JButton viewPlan = new JButton("View exercise plan");
        viewPlan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {

                exercisePanel.removeAll();
                exercisePanel.revalidate();
                exercisePanel.repaint();
                try {
                    Ratings.getExerciseRatings(loginUser);
                } catch (IOException ex) {
                    Logger.getLogger(ExerciseGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        JButton ratePlan = new JButton("Rate plan");
        ratePlan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {

                exercisePanel.removeAll();
                exercisePanel.revalidate();
                exercisePanel.repaint();
                try {
                    Ratings.setExerciseRatings(loginUser);
                } catch (IOException ex) {
                    Logger.getLogger(ExerciseGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        JButton backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {

                exercisePanel.removeAll();
                exercisePanel.revalidate();
                exercisePanel.repaint();
                try {
                    mainGUI(loginUser);
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(ExerciseGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        exercisePanel.add(viewPlan);
        viewPlan.requestFocus();
        exercisePanel.add(ratePlan);
        ratePlan.requestFocus();
        exercisePanel.add(backButton);
        backButton.requestFocus();
        exercisePanel.setVisible(true);
    }
}

/**
 * Handles new users. Asks them their preferences for food, exercise, and
 * current weight. Stores information for later use in a text file.
 *
 * @author paturne4
 */
class RegisterGUI extends FamilyMemberGUI {

    /**
     * Takes in users name to find folder and file. Asks for their exercise
     * preferences and stores them for later use. Will automatically call
     * foodPref method.
     *
     * @param userName
     * @throws IOException
     */
    public static void exercisePref(String userName) throws IOException {

        frame.add(exercisePrefPanel);
        exercisePrefPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;

        File familyFolder = new File("C://" + "PrideHealthMangement");
        familyFolder.mkdir();
        File newUser = new File("C://" + "PrideHealthMangement", "Users.txt");
        newUser.createNewFile();

        File userFile = new File("C://" + loginUser, loginUser + "ExercisePref.txt");
        userFile.createNewFile();
        File userFile2 = new File("C://" + userName, userName + "ExerciseRatings.txt");
        userFile2.createNewFile();
        FileWriter fw = new FileWriter(userFile2, true);
        fw.write("Sample\nChin-Ups  3\n");
        fw.close();

        JTextArea textArea = new JTextArea("Enter your exercise preferences here.");
        textArea.setFont(new Font("Serif", Font.ITALIC, 16));
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setRows(5);
        textArea.setColumns(10);
        exercisePrefPanel.add(textArea, gbc);

        JOptionPane.showMessageDialog(null, new JScrollPane(textArea));

        try (BufferedWriter fileOut = new BufferedWriter(new FileWriter(userFile))) {
            textArea.write(fileOut);
        } catch (IOException ex) {
            Logger.getLogger(RegisterGUI.class.getName()).log(Level.SEVERE, null, ex);
        }

        FileWriter fw2 = new FileWriter(newUser, true);
        fw2.write(loginUser);
        fw2.write("\n");
        fw2.close();

        exercisePrefPanel.removeAll();
        exercisePrefPanel.revalidate();
        exercisePrefPanel.repaint();
        RegisterGUI.foodPref(userName);
    }

    /**
     * Called by exercisePref. Asks user for their food preferences. Stores the
     * information for later use in text file located in the users folder.
     * Automatically calls weightPref method.
     *
     * @param userName
     * @throws IOException
     */
    public static void foodPref(String userName) throws IOException {

        frame.add(foodPrefPanel);
        foodPrefPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        File userFile = new File("C://" + loginUser, loginUser + "FoodPref.txt");
        userFile.createNewFile();
        File userFile2 = new File("C://" + userName, userName + "FoodRatings.txt");
        userFile2.createNewFile();
        File newUser = new File("C://" + "PrideHealthMangement", "Pref.txt");
        newUser.createNewFile();
        FileWriter fw = new FileWriter(userFile2, true);
        fw.write("Sample [Eggplant  3]");
        fw.close();

        JTextArea textArea = new JTextArea("Enter foods you do NOT like here.");
        textArea.setFont(new Font("Serif", Font.ITALIC, 16));
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setRows(5);
        textArea.setColumns(10);
        foodPrefPanel.add(textArea, gbc);

        JOptionPane.showMessageDialog(null, new JScrollPane(textArea));

        try (BufferedWriter fileOut = new BufferedWriter(new FileWriter(userFile))) {
            textArea.write(fileOut);
            textArea.append("\n");
        } catch (IOException ex) {
            Logger.getLogger(RegisterGUI.class.getName()).log(Level.SEVERE, null, ex);
        }

        FileWriter fw2 = new FileWriter(newUser, true);
        fw2.write(textArea.getText());
        fw2.close();

        foodPrefPanel.removeAll();
        foodPrefPanel.revalidate();
        foodPrefPanel.repaint();
        RegisterGUI.weightPref(userName);
    }

    /**
     * Called by foodPref. Asks users for the current weight and stores it in a
     * text file for use later. Takes user to mainGUI.
     *
     * @param userName
     * @throws IOException
     */
    public static void weightPref(String userName) throws IOException {

        frame.add(weightPrefPanel);
        weightPrefPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        File userFile = new File("C://" + loginUser, loginUser + "WeightPref.txt");
        userFile.createNewFile();
        File userFile2 = new File("C://" + loginUser, loginUser + "WeightGoal.txt");
        userFile2.createNewFile();

        JTextArea textArea = new JTextArea("Enter your current weight for the"
                + " week here.");
        textArea.setFont(new Font("Serif", Font.ITALIC, 16));
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setRows(5);
        textArea.setColumns(10);
        weightPrefPanel.add(textArea, gbc);

        JOptionPane.showMessageDialog(null, new JScrollPane(textArea));

        try (BufferedWriter fileOut = new BufferedWriter(new FileWriter(userFile))) {
            textArea.write(fileOut);
        } catch (IOException ex) {
            Logger.getLogger(RegisterGUI.class.getName()).log(Level.SEVERE, null, ex);
        }

        JTextArea textArea2 = new JTextArea("Enter your weight goal here.");
        textArea2.setFont(new Font("Serif", Font.ITALIC, 16));
        textArea2.setLineWrap(true);
        textArea2.setWrapStyleWord(true);
        textArea2.setRows(5);
        textArea2.setColumns(10);
        weightPrefPanel.add(textArea2, gbc);

        JOptionPane.showMessageDialog(null, new JScrollPane(textArea2));

        try (BufferedWriter fileOut2 = new BufferedWriter(new FileWriter(userFile2))) {
            textArea2.write(fileOut2);
        } catch (IOException ex) {
            Logger.getLogger(RegisterGUI.class.getName()).log(Level.SEVERE, null, ex);
        }

        String txt = "Type MP for Meal Planner\n"
                + "Type PT for Physical Tranier\n"
                + "Erase for neither one";
        JTextArea txtArea = new JTextArea(txt);
        txtArea.setLineWrap(true);
        txtArea.setWrapStyleWord(true);
        txtArea.setRows(5);
        txtArea.setColumns(10);
        weightPanel.add(txtArea, gbc);

        JOptionPane.showMessageDialog(null, new JScrollPane(txtArea));

        String text = txtArea.getText().trim();

        if (!text.equals("")) {
            if (text.equals("MP")) {
                File mpFile = new File("C://" + loginUser, loginUser + "MP.txt");
                mpFile.createNewFile();
            }
            if (text.equals("PT")) {
                File ptFile = new File("C://" + loginUser, loginUser + "PT.txt");
                ptFile.createNewFile();
            }
        }

        JOptionPane.showMessageDialog(frame, "You are now registered. Returning"
                + " to the login screen.");

        weightPrefPanel.removeAll();
        weightPrefPanel.revalidate();
        weightPrefPanel.repaint();
        FamilyMemberGUI.loginMenu();
    }

    /**
     * Checks to see if loginUser is a new user. If a new user, create a folder
     * with their name. Else, throw error that says file already exists.
     *
     * @param loginUser
     * @return
     * @throws IOException
     */
    public static boolean checkNewUser(String loginUser) throws IOException {

        File fileDir = new File("C:/" + loginUser);
        fileDir.mkdirs();
        File finalFile = new File(fileDir, loginUser + ".txt");
        if (finalFile.isFile()) {
            JOptionPane.showMessageDialog(frame, "Error! Name already in use!");
            return false;
        } else {
            File userFile = new File("C://" + loginUser, loginUser + ".txt");
            userFile.createNewFile();
            return true;
        }
    }

    /**
     * Checks to see if folder with loginUser name exists.
     *
     * @param loginUser
     * @return
     */
    public static boolean isUser(String loginUser) {

        File file = new File("C://" + loginUser);
        return file.exists();
    }
}

class ViewRatings extends FamilyMemberGUI {

    public static void mealRatings(String userName) throws FileNotFoundException, IOException {

        if (RegisterGUI.isUser(userName)) {
            Ratings.getMealRatings(userName);
        } else {
            JOptionPane.showMessageDialog(frame, "Error! File does not exist!");
            FamilyMemberGUI.loginMenu();
        }
    }

    public static void exerciseRatings(String userName) throws FileNotFoundException, IOException {

        if (RegisterGUI.isUser(userName)) {
            Ratings.getExerciseRatings(userName);
        } else {
            JOptionPane.showMessageDialog(frame, "Error! File does not exist!");
            FamilyMemberGUI.loginMenu();
        }
    }
}

class Ratings extends FamilyMemberGUI {

    public static void getMealRatings(String userName) throws IOException {
        
        frame.add(foodPanel);
        foodPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;

        File userFile = new File("C://" + userName, userName + "FoodRatings.txt");
        if (!userFile.exists()) {
            JOptionPane.showMessageDialog(frame, "Error! File does not exist!");
        }

        String filePath = userFile.getPath();
        JTextArea txtArea = new JTextArea();
        
        String content = new Scanner(new File("C://" + userName, userName + "FoodRatings.txt")).useDelimiter("\\Z").next();
        txtArea.setText(content);

        JButton backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {

                foodPanel.removeAll();
                foodPanel.revalidate();
                foodPanel.repaint();
                try {
                    mainGUI(loginUser);
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(Ratings.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {

                foodPanel.removeAll();
                foodPanel.revalidate();
                foodPanel.repaint();
                try {
                    try (BufferedWriter fileOut = new BufferedWriter(new FileWriter(userFile))) {
                        txtArea.write(fileOut);
                    } catch (IOException ex) {
                        Logger.getLogger(RegisterGUI.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    mainGUI(loginUser);
                } catch (IOException ex) {
                    Logger.getLogger(WeightGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        foodPanel.add(txtArea);
        foodPanel.add(saveButton);
        foodPanel.add(backButton);
        backButton.requestFocus();
        saveButton.requestFocus();
    }

    public static void getExerciseRatings(String userName) throws FileNotFoundException, IOException {

        frame.add(exercisePanel);
        exercisePanel.setLayout(new GridBagLayout());
        File userFile = new File("C://" + userName, userName + "ExerciseRatings.txt");
        GridBagConstraints gbc = new GridBagConstraints();

        ReadWrite rw = new ReadWrite();
        String text = rw.getXPlan(userName, "2000", "1", "01");
        JTextField txt = new JTextField(text);
        exercisePanel.add(txt);

        JButton backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {

                exercisePanel.removeAll();
                exercisePanel.revalidate();
                exercisePanel.repaint();
                try {
                    mainGUI(loginUser);
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(Ratings.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        exercisePanel.add(backButton);
        backButton.requestFocus();
    }

    public static void setMealRatings(String userName) throws IOException {
        
        frame.add(foodPanel);
        foodPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        File userFile = new File("C://" + userName, userName + "FoodRatings.txt");
        String filePath = userFile.getPath();
        
        JTextArea txtArea = new JTextArea();
        
        mealPlannerGUI mp2 = new mealPlannerGUI();
        mealPlannerGUI.MealPlan mp3 = mp2.new MealPlan();
        Map<String, ArrayList> newMap = mp3.mapFromFile("mealPlan.txt");
        for (Map.Entry<String, ArrayList> entry : newMap.entrySet()) {
            txtArea.append(entry.getKey());
            txtArea.append("\n");
            txtArea.append(entry.getValue().toString().replace("[", "").replace("]", ""));
        }
        
        JButton backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {

                foodPanel.removeAll();
                foodPanel.revalidate();
                foodPanel.repaint();
                try {
                    mainGUI(loginUser);
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(Ratings.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        foodPanel.add(txtArea);
        foodPanel.add(backButton);
        backButton.requestFocus();
    }

    public static void setExerciseRatings(String userName) throws IOException {

        Modify obj = new Modify(loginUser);
        FamilyMemberGUI.mainGUI(userName);
    }
}

/**
 * Finds the user by name.
 *
 * @author paturne4
 */
class FindUser extends FamilyMemberGUI {

    /**
     * If user already has a file, they exist. If not, throw exception.
     *
     * @param userName
     * @return
     * @throws FileNotFoundException
     */
    public static File getUser(String userName) throws FileNotFoundException {

        File file = new File("C://" + userName);
        if (file.exists()) {
            return file;
        } else {
            throw new FileNotFoundException("Error! File does not exist!");
        }
    }
}

/**
 * Gets the preferences for meals and exercises.
 *
 * @author paturne4
 */
class Preferences extends FamilyMemberGUI {

    /**
     * Return meal preferences.
     *
     * @param userName
     */
    public static void getMealPreferences(String userName) {

        ViewPreferences.mealPreferences(userName);
    }

    /**
     * Return exercise preferences.
     *
     * @param userName
     */
    public static void getExercisePreferences(String userName) {

        ViewPreferences.exercisePreferences(userName);
    }
}

/**
 * Handles the weekly weight, current weight, and weight goal of user. Can also
 * set weekly goal, and let user see weekly weight.
 *
 * @author paturne4
 */
class Weight extends FamilyMemberGUI {

    /**
     * Return weekly weight of user.
     *
     * @param userName
     */
    public static void getWeeklyWeight(String userName) {

        ViewWeight.weeklyWeight(userName);
    }

    /**
     * Return current weight of user.
     *
     * @param userName
     */
    public static void getCurrentWeight(String userName) throws IOException {

        ViewWeight.currentWeight(userName);
    }

    /**
     * Sets the weight goal for the user.
     *
     * @param userName
     */
    public static File setWeightGoal(String userName) {
        File userFile = new File("C://" + userName, userName + "WeightGoal.txt");
        return userFile;
    }

    /**
     * Returns the weight goal of the user.
     *
     * @param userName
     */
    public static void getWeightGoal(String userName) throws IOException {

        ViewWeight.weightGoal(userName);
    }

    /**
     * Sets the weight of the user for the week.
     *
     * @param userName
     */
    public static File setWeeklyWeight(String userName) {
        File userFile = new File("C://" + userName, userName + "WeightPref.txt");
        return userFile;
    }
}

/**
 * Handles all of the views requests.
 *
 * @author paturne4
 */
class ViewWeight extends FamilyMemberGUI {

    /**
     * Gets weekly user weight.
     *
     * @param userName
     */
    public static File weeklyWeight(String userName) {
        File userFile = new File("C://" + userName, userName + "WeightPref.txt");
        return userFile;
    }

    /**
     * Gets current user weight.
     *
     * @param userName
     */
    public static void currentWeight(String userName) throws FileNotFoundException, IOException {
        frame.add(weightPanel);
        weightPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        File userFile = new File("C://" + userName, userName + "WeightPref.txt");
        BufferedReader reader = new BufferedReader(new FileReader(userFile));
        String goal = reader.readLine();

        JTextField weightGoal = new JTextField(goal);
        weightGoal.setEditable(false);
        weightPanel.add(weightGoal);
        weightGoal.requestFocus();
    }

    /**
     * Gets the weight goal for the user.
     *
     * @param userName
     */
    public static void weightGoal(String userName) throws FileNotFoundException, IOException {

        frame.add(weightPanel);
        weightPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        File userFile = new File("C://" + userName, userName + "WeightGoal.txt");
        BufferedReader reader = new BufferedReader(new FileReader(userFile));
        String goal = reader.readLine();

        JTextField weightGoal = new JTextField(goal);
        weightGoal.setEditable(false);
        weightPanel.add(weightGoal);
        weightGoal.requestFocus();
    }
}

/**
 * Handles the requests to view the preferences.
 *
 * @author paturne4
 */
class ViewPreferences extends FamilyMemberGUI {

    /**
     * Shows the preferences of meals the user does NOT like.
     *
     * @param userName
     */
    public static File mealPreferences(String userName) {
        File userFile = new File("C://" + userName, userName + "MealPref.txt");
        return userFile;
    }

    /**
     * Shows what exercises the user likes.
     *
     * @param userName
     */
    public static File exercisePreferences(String userName) {
        File userFile = new File("C://" + userName, userName + "ExercisePref.txt");
        return userFile;
    }
}
