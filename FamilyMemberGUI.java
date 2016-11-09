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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/**
 * Main class that will call and handle user interaction.
 * @author paturne4
 */
public class FamilyMemberGUI extends JFrame {

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
    static JPanel exercisePrefPanel = new JPanel();
    static JPanel foodPrefPanel = new JPanel();
    static JPanel weightPrefPanel = new JPanel();
//    static JPanel setMealRatingsPanel = new JPanel();
//    static JPanel setExerciseRatingsPanel = new JPanel();
//    static JPanel getMealRatingsPanel = new JPanel();
//    static JPanel getExerciseRatingsPanel = new JPanel();
    static String loginUser;

    /**
     * Display the GUI.
     */
    public static void fullscreenGUI() {

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
    public static void loginMenu() {

        frame.add(loginPanel);
        loginPanel.setLayout(new GridBagLayout());

        JLabel userLabel = new JLabel("User");
        loginPanel.add(userLabel);
        userLabel.requestFocusInWindow();

        JTextField userText = new JTextField(20);
        loginPanel.add(userText);
        loginUser = userText.getText();
        userText.requestFocusInWindow();

        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {

                loginUser = userText.getText();
                if (RegisterGUI.isUser(loginUser)) {
                    loginPanel.removeAll();
                    loginPanel.revalidate();
                    loginPanel.repaint();
                    mainGUI(loginUser);
                } else {
                    JOptionPane.showMessageDialog(frame, "Error! Name already in use!");
                    loginMenu();
                }
            }
        });
        loginPanel.add(loginButton);
        loginButton.requestFocusInWindow();

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
        loginPanel.add(registerButton);
        registerButton.requestFocusInWindow();

        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
            }
        });
        loginPanel.add(exitButton);
        exitButton.requestFocusInWindow();
        loginPanel.setVisible(true);
    }

    /**
     * Lets user access or view their information. Has access to meals, weight,
     * and exercise plans.
     * @param userName
     */
    public static void mainGUI(String userName) {

        frame.add(mainPanel);
        mainPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20, 0, 0, 0);

        JLabel userLabel = new JLabel("Hello, " + userName + "!");
        gbc.gridx = 0;
        gbc.ipadx = 50;
        mainPanel.add(userLabel, gbc);
        userLabel.requestFocusInWindow();

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
        mainPanel.add(foodButton, gbc);
        foodButton.requestFocusInWindow();

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
        gbc.ipadx = 40;
        mainPanel.add(weightButton, gbc);
        weightButton.requestFocusInWindow();

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
        gbc.ipadx = 30;
        mainPanel.add(exerciseButton, gbc);
        exerciseButton.requestFocusInWindow();

        JButton backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {

                mainPanel.removeAll();
                mainPanel.revalidate();
                mainPanel.repaint();
                loginMenu();
            }
        });
        gbc.ipadx = 105;
        mainPanel.add(backButton, gbc);
        backButton.requestFocusInWindow();
        mainPanel.setVisible(true);
    }
   
    /**
     * Starts the application.
     * @param args
     */
    public static void main(String[] args) {

        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                fullscreenGUI();
            }
        });
    }
}

/**
 * Handles the users weight information. Will display their current weight,
 * weight goal, and weekly weight. User can change weight at anytime.
 * @author paturne4
 */
class WeightGUI extends FamilyMemberGUI {

    /**
     * Displays users current weight with their goal. Offers option to change
     * weight goal.
     * @param userName
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static void weightGUI(String userName) throws FileNotFoundException, IOException {

        frame.add(weightPanel);
        weightPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
       
       
       
        JLabel weightLabel = new JLabel("Weight goal: ");
        weightPanel.add(weightLabel);

        JButton weightEditButton = new JButton("Edit goal");
        weightEditButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                weightPanel.removeAll();
                weightPanel.revalidate();
                weightPanel.repaint();
                editWeightGUI(userName);
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
                FamilyMemberGUI.mainGUI(loginUser);
            }
        });
        weightPanel.add(backButton);
        backButton.requestFocusInWindow();
        weightPanel.setVisible(true);
    }
   
    /**
     * Lets the user change their weight goal to a different yet reasonable goal.
     * @param userName
     */
    public static void editWeightGUI(String userName) {

        frame.add(newWeightPanel);
        newWeightPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        JLabel newWeightLabel = new JLabel("Enter new weight");
        newWeightPanel.add(newWeightLabel);
        newWeightLabel.requestFocusInWindow();

        JTextField userText = new JTextField(20);
        newWeightPanel.add(userText);
        userText.requestFocusInWindow();

        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {

                newWeightPanel.removeAll();
                newWeightPanel.revalidate();
                newWeightPanel.repaint();
                try {
                    weightGUI(userName);
                } catch (IOException ex) {
                    Logger.getLogger(WeightGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        newWeightPanel.add(saveButton);
        saveButton.requestFocusInWindow();
        newWeightPanel.setVisible(true);
    }
}

/**
 * Handles all information regarding the users meal preferences, how much they
 * ate, and how they rate the meal. If user did not eat at home, ask for caloric
 * intake instead.
 * @author paturne4
 */
class FoodGUI extends FamilyMemberGUI {

   
    public static void foodGUI() throws IOException {

        frame.add(foodPanel);
        foodPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

//        JLabel randLabel = new JLabel("Meal information will go here.");
//        foodPanel.add(randLabel);
//        randLabel.requestFocusInWindow();

            Ratings.setMealRatings(loginUser);
            ViewRatings.mealRatings(loginUser);

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
        foodPanel.setVisible(true);
    }
}

/**
 * Handles all information about users exercise ratings and activities/plans
 * that were completed. Weekly weight will also be displayed.
 * @author paturne4
 */
class ExerciseGUI extends FamilyMemberGUI {

   
    public static void exerciseGUI() throws FileNotFoundException, IOException {

        frame.add(exercisePanel);
        exercisePanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;

//        JLabel randLabel = new JLabel("Exercise information will go here.");
//        exercisePanel.add(randLabel, gbc);
//        randLabel.requestFocusInWindow();
        Ratings.setExerciseRatings(loginUser);
        Ratings.getExerciseRatings(loginUser);

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
        exercisePanel.add(backButton, gbc);
        backButton.requestFocusInWindow();
        exercisePanel.setVisible(true);
    }
}
/**
 * Handles new users. Asks them their preferences for food, exercise,
 * and current weight. Stores information for later use in a text file.
 * @author paturne4
 */
class RegisterGUI extends FamilyMemberGUI {
   
    /**
     * Takes in users name to find folder and file. Asks for their exercise
     * preferences and stores them for later use. Will automatically call
     * foodPref method.
     * @param userName
     * @throws IOException
     */
    public static void exercisePref(String userName) throws IOException {

        frame.add(exercisePrefPanel);
        exercisePrefPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        File userFile = new File("C://" + loginUser, loginUser + "ExercisePref.txt");
        userFile.createNewFile();
       
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
        exercisePrefPanel.removeAll();
        exercisePrefPanel.revalidate();
        exercisePrefPanel.repaint();
        RegisterGUI.foodPref(userName);

//        JButton toFoodPref = new JButton("Next");
//        toFoodPref.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent ae) {
//                exercisePrefPanel.removeAll();
//                exercisePrefPanel.revalidate();
//                exercisePrefPanel.repaint();
//                try (BufferedWriter fileOut = new BufferedWriter(new FileWriter(userFile))) {
//                    textArea.write(fileOut);
//                } catch (IOException ex) {
//                    Logger.getLogger(RegisterGUI.class.getName()).log(Level.SEVERE, null, ex);
//                }
//                try {
//                    foodPref(userName);
//                } catch (IOException ex) {
//                    Logger.getLogger(RegisterGUI.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }
//        });
//        exercisePrefPanel.add(toFoodPref, gbc);
//        exercisePrefPanel.setVisible(true);
        
    }
   
    /**
     * Called by exercisePref. Asks user for their food preferences.
     * Stores the information for later use in text file located in the users
     * folder. Automatically calls weightPref method.
     * @param userName
     * @throws IOException
     */
    public static void foodPref(String userName) throws IOException{
       
        frame.add(foodPrefPanel);
        foodPrefPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        File userFile = new File("C://" + loginUser, loginUser + "FoodPref.txt");
        userFile.createNewFile();
       
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
        } catch (IOException ex) {
            Logger.getLogger(RegisterGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        foodPrefPanel.removeAll();
        foodPrefPanel.revalidate();
        foodPrefPanel.repaint();
        RegisterGUI.weightPref(userName);
       
//        JButton toWeightPref = new JButton("Next");
//        toWeightPref.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent ae) {
//                foodPrefPanel.removeAll();
//                foodPrefPanel.revalidate();
//                foodPrefPanel.repaint();
//                try (BufferedWriter fileOut = new BufferedWriter(new FileWriter(userFile))) {
//                    textArea.write(fileOut);
//                } catch (IOException ex) {
//                    Logger.getLogger(RegisterGUI.class.getName()).log(Level.SEVERE, null, ex);
//                }
//                try {
//                    weightPref(userName);
//                } catch (IOException ex) {
//                    Logger.getLogger(RegisterGUI.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }
//        });
//        foodPrefPanel.add(toWeightPref, gbc);
//        foodPrefPanel.setVisible(true);
        
    }
   
    /**
     * Called by foodPref. Asks users for the current weight and stores it in a
     * text file for use later. Takes user to mainGUI.
     * @param userName
     * @throws IOException
     */
    public static void weightPref(String userName) throws IOException{
       
        frame.add(weightPrefPanel);
        weightPrefPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        File userFile = new File("C://" + loginUser, loginUser + "WeightPref.txt");
        userFile.createNewFile();
       
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
        
        JOptionPane.showMessageDialog(frame, "You are now registered. Returning"
                + "to the login screen.");
        
        weightPrefPanel.removeAll();
        weightPrefPanel.revalidate();
        weightPrefPanel.repaint();
        FamilyMemberGUI.loginMenu();
       
//        JButton save = new JButton("Save");
//        save.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent ae) {
//                weightPrefPanel.removeAll();
//                weightPrefPanel.revalidate();
//                weightPrefPanel.repaint();
//                try (BufferedWriter fileOut = new BufferedWriter(new FileWriter(userFile))) {
//                    textArea.write(fileOut);
//                } catch (IOException ex) {
//                    Logger.getLogger(RegisterGUI.class.getName()).log(Level.SEVERE, null, ex);
//                }
//                FamilyMemberGUI.mainGUI(userName);
//            }
//        });
//        weightPrefPanel.add(save, gbc);
//        weightPrefPanel.setVisible(true);
        
        
    }
   
    /**
     * Checks to see if loginUser is a new user. If a new user, create a folder
     * with their name. Else, throw error that says file already exists.
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
     * @param loginUser
     * @return
     */
    public static boolean isUser(String loginUser) {

        File file = new File("C://" + loginUser);
        return file.exists();
    }
}

class ViewRatings extends FamilyMemberGUI{
    
    public static void mealRatings(String userName) throws FileNotFoundException, IOException{
        if(RegisterGUI.isUser(userName)){
            Ratings.getMealRatings(userName);
        }
        else{
            JOptionPane.showMessageDialog(frame, "Error! File does not exist!");
            FamilyMemberGUI.loginMenu();
        }
    }
    
    public static void exerciseRatings(String userName) throws FileNotFoundException{
        
        if(RegisterGUI.isUser(userName)){
            Ratings.getExerciseRatings(userName);
        }
        else{
            JOptionPane.showMessageDialog(frame, "Error! File does not exist!");
            FamilyMemberGUI.loginMenu();
        }
    }
}

class Ratings extends FamilyMemberGUI{
    
    public static void getMealRatings(String userName) throws IOException {
        
//        frame.add(getMealRatingsPanel);
//        getMealRatingsPanel.setLayout(new GridBagLayout());
frame.add(foodPanel);
foodPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        
//        JLabel info = new JLabel("Meal Ratings");
//        foodPanel.add(info, gbc);
        
        File userFile = new File("C://" + userName, userName + "FoodRatings.txt");
        if(!userFile.exists()){
            JOptionPane.showMessageDialog(frame, "Error! File does not exist!");
        }
        
        JTextArea textArea = new JTextArea();
        FileReader reader = new FileReader(userFile);
        textArea.read(reader, userFile);
        
        foodPanel.add(textArea, gbc);
        textArea.requestFocusInWindow();
        
//        File inFile = FindUser.getUser(userName);
//        
//        JTextArea textArea = new JTextArea();
//        
//        textArea.setText(inFile.toString());
//        getMealRatingsPanel.add(textArea, gbc);
//        textArea.requestFocusInWindow();
//        getMealRatingsPanel.setVisible(true);
    }
    
    public static void getExerciseRatings(String userName){
//        frame.add(getExerciseRatingsPanel);
//        getExerciseRatingsPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        
        JLabel info = new JLabel("Exercise Ratings");
        exercisePanel.add(info, gbc);
        info.requestFocusInWindow();
        
        
    }
    
    public static void setMealRatings(String userName) throws IOException{
        
//        frame.add(setMealRatingsPanel);
//        setMealRatingsPanel.setLayout(new GridBagLayout());
        
        File userFile = new File("C://" + userName, userName + "FoodRatings.txt");
        if(!userFile.exists()){
            userFile.createNewFile();
        }
        
//        setMealRatingsPanel.setVisible(true);
    }
    
    public static void setExerciseRatings(String userName) throws IOException{
        
//        frame.add(setExerciseRatingsPanel);
//        setExerciseRatingsPanel.setLayout(new GridBagLayout());
        
        File userFile = new File("C://" + userName, userName + "ExerciseRatings.txt");
        if(!userFile.exists()){
            userFile.createNewFile();
        }
        
//        setExerciseRatingsPanel.setVisible(true);
    }
}

/**
 * Finds the user by name.
 * @author paturne4
 */
class FindUser extends FamilyMemberGUI{
    
    /**
     * If user already has a file, they exist. If not, throw exception.
     * @param userName
     * @return
     * @throws FileNotFoundException 
     */
    public static File getUser(String userName) throws FileNotFoundException{
        
        File file = new File("C://" + userName);
        if(file.exists()){
            return file;
        }
        else{
            throw new FileNotFoundException("Error! File does not exist!");
        }
    }
}

/**
 * Gets the preferences for meals and exercises.
 * @author paturne4
 */
class Preferences extends FamilyMemberGUI{
    
    /**
     * Return meal preferences.
     * @param userName 
     */
    public static void getMealPreferences(String userName){
        
        ViewPreferences.mealPreferences(userName);
    }
    
    /**
     * Return exercise preferences.
     * @param userName 
     */
    public static void getExercisePreferences(String userName){
        
        ViewPreferences.exercisePreferences(userName);
    }
}

/**
 * Handles the weekly weight, current weight, and weight goal of user.
 * Can also set weekly goal, and let user see weekly weight.
 * @author paturne4
 */
class Weight extends FamilyMemberGUI{
    
    /**
     * Return weekly weight of user.
     * @param userName 
     */
    public static void getWeeklyWeight(String userName){
        
        ViewWeight.weeklyWeight(userName);
    }
    
    /**
     * Return current weight of user.
     * @param userName 
     */
    public static void getCurrentWeight(String userName){
        
        ViewWeight.currentWeight(userName);
    }
    
    /**
     * Sets the weight goal for the user.
     * @param userName 
     */
    public static void setWeightGoal(String userName){
        
    }
    
    /**
     * Returns the weight goal of the user.
     * @param userName 
     */
    public static void getWeightGoal(String userName){
        
        ViewWeight.weightGoal(userName);
    }
    
    /**
     * Sets the weight of the user for the week.
     * @param userName 
     */
    public static void setWeeklyWeight(String userName){
        
    }
}

/**
 * Handles all of the views requests.
 * @author paturne4
 */
class ViewWeight extends FamilyMemberGUI {
    
    /**
     * Gets weekly user weight.
     * @param userName 
     */
    public static void weeklyWeight(String userName) {
        
    }
    
    /**
     * Gets current user weight.
     * @param userName 
     */
    public static void currentWeight(String userName){
        
    }
    
    /**
     * Gets the weight goal for the user.
     * @param userName 
     */
    public static void weightGoal(String userName){
        
    }
}

/**
 * Handles the requests to view the preferences.
 * @author paturne4
 */
class ViewPreferences extends FamilyMemberGUI{
    
    /**
     * Shows the preferences of meals the user does NOT like.
     * @param userName 
     */
    public static void mealPreferences(String userName){
        
    }
    
    /**
     * Shows what exercises the user likes.
     * @param userName 
     */
    public static void exercisePreferences(String userName){
        
    }
}
