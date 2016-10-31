/*Jeremy Hutton
340 software engineering
class that implements the GUI front end
of the Meal Planner Subsystem of a Health Management System
*/



import java.awt.Image;
import java.awt.event.ActionListener;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.text.BadLocationException;
import java.io.IOException;
import java.awt.event.*;
import java.util.logging.Level;
import java.util.logging.Logger;



public abstract class mealPlannerGUI extends JFrame implements ActionListener {

    public static void main(String[] args) throws BadLocationException, IOException {
		        
        //draws background for application
        JFrame frame = new JFrame("Pride Health Management");
        frame.setSize(1500, 1500);
        ImageIcon image = new ImageIcon(ImageIO.read(new File("src/resourceFolder/lion_pride.png"))
                               .getScaledInstance(900, 700, Image.SCALE_SMOOTH));
        ImageIcon img = new ImageIcon("src/resourceFolder/Pimage.png");
                      
        frame.setIconImage(img.getImage());
        JLabel imageLabel = new JLabel(image);
        frame.add(imageLabel);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        
        /*  CREATED BY ANOTHER STUDENT
        JPanel panel = new JPanel();
	Login(panel);
        makeJFrame(panel,"Family Member Login", 300, 150);
        */
        
        //creates window through which all navigation is done
        JPanel panel = new JPanel();
	MealPlanner(panel);
        makeJFrame(panel, "Meal Planner", 500, 550);
                  
    }
/*  CREATED BY ANOTHER STUDENT
private  static void Login(JPanel panel) {

    panel.setLayout(null);

    JLabel userLabel = new JLabel("User");
    userLabel.setBounds(10, 10, 80, 25);
    panel.add(userLabel);

    JTextField userText = new JTextField(20);
    userText.setBounds(100, 10, 160, 25);
    panel.add(userText);

    JButton loginButton = new JButton("login");
    loginButton.setBounds(10, 80, 80, 25);
    panel.add(loginButton);
               
                
		
	}
*/
    
/* 
    method that creates the inner workings of the Meal Planner window
    
    */
private static void MealPlanner(JPanel panel) {

    panel.setLayout(null);
    
    //creates a button which when pressed displays previous menus
    JButton mealPlanChoice1 = new JButton("Display Previous Menus");
    mealPlanChoice1.setBounds(100, 40, 280, 50);
    panel.add(mealPlanChoice1);
    mealPlanChoice1.addActionListener(new ActionListener(){
    @Override
    public void actionPerformed(ActionEvent e){
        JPanel panel = new JPanel();
        try {
            PreviousMenus(panel);
        } catch (BadLocationException ex) {
            Logger.getLogger(mealPlannerGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
                makeJFrame(panel, "Display Previous Menus", 500, 450);
        
    }
    });
    
    //creates a button that when clicked takes user to the make a menu item window            
    JButton mealPlanChoice2 = new JButton("Make a Menu Item");
    mealPlanChoice2.setBounds(100, 120, 280, 50);
    panel.add(mealPlanChoice2);
    mealPlanChoice2.addActionListener(new ActionListener(){
    @Override
    public void actionPerformed(ActionEvent e){
        JPanel panel = new JPanel();
        MenuItem(panel);
        makeJFrame(panel, "Create A Menu Item", 600, 400);
        
    }
    });
    
    //creates a button which when clicked takes user to make a meal plan window           
    JButton mealPlanChoice3 = new JButton("Make a Meal Plan");
    mealPlanChoice3.setBounds(100, 200, 280, 50);
    panel.add(mealPlanChoice3);
    mealPlanChoice3.addActionListener(new ActionListener(){
    @Override
    public void actionPerformed(ActionEvent e){
        JPanel panel = new JPanel();
        MealPlan(panel);
        makeJFrame(panel, "Create a Meal Plan", 400, 800);
                       
    }
    });
       
    //creates a button that when clicked takes user to a calendar of meal plans
    JButton mealPlanChoice4 = new JButton("Display Meal Plan Calendar");
    mealPlanChoice4.setBounds(100, 280, 280, 50);
    panel.add(mealPlanChoice4);
    mealPlanChoice4.addActionListener(new ActionListener(){
    @Override
    public void actionPerformed(ActionEvent e){
        JPanel panel = new JPanel();
        Calendar(panel);
        makeJFrame(panel, "Meal Plan Calendar", 600, 1000);
                       
    }
    });
    
    //creates a button that returns user to family planner system
    JButton mealPlanChoice5 = new JButton("Return to Family Planner System");
    mealPlanChoice5.setBounds(100, 360, 280, 50);
    panel.add(mealPlanChoice5);
    mealPlanChoice5.addActionListener(new ActionListener(){
    @Override
    public void actionPerformed(ActionEvent e){
        JPanel panel = new JPanel();
        
    }
    });
               
}
 
/*
method that builds the inner workings of a window that displays previous menus
*/
public static void PreviousMenus(JPanel panel) throws BadLocationException{
            
          
    // define items in a String array:

 
// create a combo box with the fixed array:
   
    
    JCheckBox checkBox1 = new JCheckBox("Spaghetti and Meatballs");
    JButton button1 = new JButton("Menu Information");
    button1.setBounds(300,150,75,25);
    button1.addActionListener(new ActionListener(){
    @Override
    public void actionPerformed(ActionEvent e){
        JPanel panel = new JPanel();
        makeJFrame(panel, "Description,Caloric Content, Ratings", 400, 200);
        JTextArea eventHandler = new JTextArea("Spaghetti in a maranara sauce with "
                + "sausage meatballs." + "\n" + "Calories: 800" + "\n"
                + "Ratings: 4");
        eventHandler.setBounds(10,150,300,150);
        panel.add(eventHandler);
        }
    });
    panel.add(checkBox1);
    panel.add(button1);
       
}
 
/*
method that builds a window that allows user to create menu item, description and caloric content
*/
public static void MenuItem(JPanel panel){

    panel.setLayout(null);
    String[] menuTypes = new String[] {"Beverage", "Main Item", "Side Item", "Dessert"};
    
    JComboBox<String> choiceBox = new JComboBox<>(menuTypes);
    makeLabel(panel,"Please Choose a Menu Type", 20, 10, 200,25);
    choiceBox.setBounds(250,10,100,25); 
    
    //makes a text field with mock input
    JLabel menuItem = new JLabel("Menu Item:");
    menuItem.setBounds(20, 100, 100,25);
    panel.add(menuItem);
    JTextField menuField = new JTextField();
    menuField.setBounds(250, 100, 250, 25);
    panel.add(menuField);
    
    //makes a text field with mock description input
    JLabel menuDescription = new JLabel("Menu Item Description:");
    menuDescription.setBounds(20, 150, 150,25);
    panel.add(menuDescription);
    JTextField descriptionField = new JTextField();
    descriptionField.setBounds(250, 150, 250, 25);
    panel.add(descriptionField);
    
    //makes a text field with mock caloric content input
    JLabel caloricContent = new JLabel("Caloric Content:");
    caloricContent.setBounds(20, 200, 100,25);
    panel.add(caloricContent);
    JTextField caloricField = new JTextField();
    caloricField.setBounds(250, 200, 250, 25);
    panel.add(caloricField);
    
    
    JButton createItemButton = new JButton("Create Item");
    createItemButton.setBounds(150, 285, 120, 25);
    panel.add(createItemButton);
    
    JButton cancelButton = new JButton("Cancel");
    cancelButton.setBounds(275, 285, 120, 25);
    panel.add(cancelButton);
    panel.add(choiceBox);

        }

/*
method that builds a window that allows user to choose menu items based on family members
preferences and restrictions
*/
public static void MealPlan(JPanel panel){
    
    panel.setLayout(null);
    
    String[] days = new String[] {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday",
                                "Friday", "Saturday", "Sunday"};
    
    JComboBox<String> choiceBox1 = new JComboBox<>(days);
    makeLabel(panel,"Please Choose a Day", 10, 10, 200,25);
    choiceBox1.setBounds(250,10,100,25); 
    
    String[] mealTypes = new String[] {"Breakfast", "Lunch","Dinner"};
    
    JComboBox<String> choiceBox2 = new JComboBox<>(mealTypes);
    makeLabel(panel,"Please Choose a Meal Type", 10, 60, 200,25);
    choiceBox2.setBounds(250,60,100,25);
    
    JLabel userLabel = new JLabel("Available Menu Items Based on Preferences and Restricitions");
    userLabel.setBounds(10, 100, 400, 25);
    panel.add(userLabel);
    
    //radio buttons created with mock data from system
    JRadioButton radButton1 = new JRadioButton("Spaghetti and Meatballs");
    radButton1.addActionListener(new ActionListener(){
    @Override
    public void actionPerformed(ActionEvent e){
        JPanel panel = new JPanel();
        makeJFrame(panel, "Something Happened", 400, 200);
        JLabel eventHandler = new JLabel("Action initiated by click");
        panel.add(eventHandler);
    }
    });
    radButton1.setBounds(10, 150, 200, 25);
    JRadioButton radButton2 = new JRadioButton("Pork Chops");
    radButton2.addActionListener(new ActionListener(){
    @Override
    public void actionPerformed(ActionEvent e){
        JPanel panel = new JPanel();
        makeJFrame(panel, "Something Happened", 400, 200);
        JLabel eventHandler = new JLabel("Action initiated by click");
        panel.add(eventHandler);
    }
    });
    radButton2.setBounds(10, 200, 200, 25);
    JRadioButton radButton3 = new JRadioButton("Grilled Salmon"); 
    radButton3.setBounds(10, 250, 200, 25);
    radButton3.addActionListener(new ActionListener(){
    @Override
    public void actionPerformed(ActionEvent e){
        JPanel panel = new JPanel();
        makeJFrame(panel, "Something Happened", 400, 200);
        JLabel eventHandler = new JLabel("Action initiated by click");
        panel.add(eventHandler);
    }
    });
    JRadioButton radButton4 = new JRadioButton("Pan Seared Flounder");
    radButton4.addActionListener(new ActionListener(){
    @Override
    public void actionPerformed(ActionEvent e){
        JPanel panel = new JPanel();
        makeJFrame(panel, "Something Happened", 400, 200);
        JLabel eventHandler = new JLabel("Action initiated by click");
        panel.add(eventHandler);
    }
    });
    radButton4.setBounds(10, 300, 200, 25);
    
    panel.add(choiceBox1);
    panel.add(choiceBox2);
    panel.add(radButton1);
    panel.add(radButton2);
    panel.add(radButton3);
    panel.add(radButton4);
    
    JButton mealPlanChoice1 = new JButton("Make Plan");
    mealPlanChoice1.setBounds(75, 600, 100, 50);
    panel.add(mealPlanChoice1);
                
    JButton mealPlanChoice2 = new JButton("Cancel");
    mealPlanChoice2.setBounds(175, 600, 100, 50);
    panel.add(mealPlanChoice2);            
            
        }

/*
method that builds a calendar with meals for the week
*/
        
public static void Calendar(JPanel panel){
    
    panel.setLayout(null);
    
    //creates breakfast, lunch and dinner labels
    JLabel breakfastLabel = new JLabel("Breakfast");
    breakfastLabel.setBounds(150, 15, 75, 25);
    panel.add(breakfastLabel);
    
    JLabel lunchLabel = new JLabel("Lunch");
    lunchLabel.setBounds(300, 15, 75, 25);
    panel.add(lunchLabel);
    
    JLabel dinnerLabel = new JLabel("Dinner");
    dinnerLabel.setBounds(450, 15, 75, 25);
    panel.add(dinnerLabel);
    
    //days of the week labels
    makeLabel(panel, "Sunday", 20, 50, 75, 25);
    makeLabel(panel, "Monday", 20, 150, 75, 25);
    makeLabel(panel, "Tuesday", 20, 250, 75, 25);
    makeLabel(panel, "Wednesday", 20, 350, 75, 25);
    makeLabel(panel, "Thursday", 20, 450, 75, 25);
    makeLabel(panel, "Friday", 20, 550, 75, 25);
    makeLabel(panel, "Saturday", 20, 650, 75, 25);
    
    //for loops that create the text fields and populate them with mock meals
    int yValue = 50;
    for(int i = 0; i < 7; i++){
    //int yValue = 125;
    makeTextPane(panel,"Eggs,bacon, hashbrowns",125,yValue,100,75);
    yValue += 100;
    }
    int yValue2 = 50;
    for(int i = 0; i < 7; i++){
    //int yValue = 125;
    makeTextPane(panel,"Chicken, Mash Potatoes, Green Beans",265,yValue2,100,75);
    
    yValue2 += 100;
    
    }
    int yValue3 = 50;
    for(int i = 0; i < 7; i++){
    //int yValue = 125;
    makeTextPane(panel,"Salmon, Asparagus, Polenta",415,yValue3,100,75);
    yValue3 += 100;
    
    }
    
}

/*
method that builds generic textPanes
*/
public static void makeTextPane(JPanel panel,String mealPlan,int paneX,int paneY,int paneWidth,
                int paneHeigth){
    
    
    JTextPane newPanel = new JTextPane();
    newPanel.setEditable(false);  // prevents the user from editting it.
    newPanel.setText(mealPlan);
    newPanel.setBounds(paneX, paneY, paneWidth, paneHeigth);
    newPanel.setEditable(false);
    panel.add(newPanel);
    
}

/*
method that builds generic labels
*/

public static void makeLabel(JPanel panel,String day, int labelX,int labelY,int labelWidth,
                int labelHeighth){
    
    JLabel newLabel = new JLabel(day);
    newLabel.setBounds(labelX, labelY, labelWidth, labelHeighth);
    panel.add(newLabel);
    
}

/*
method that builds generic JFrames
*/
public static void makeJFrame (JPanel panel, String frameName, int xFrame, int yFrame){

    JFrame newFrame = new JFrame(frameName);
    newFrame.setSize(xFrame, yFrame);
    ImageIcon img = new ImageIcon("src/resourceFolder/Pimage.png");
                      
    newFrame.setIconImage(img.getImage());
    newFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    newFrame.add(panel);
    newFrame.setVisible(true);
}

/*work in progress

public static void makeScrollFrame (JPanel panel, String frameName, int xFrame, int yFrame){

    JScrollPane myJScrollPane = new JScrollPane(panel,
         JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
         JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    
    JFrame newFrame = new JFrame(frameName);
    //JScrollPane pane = new JScrollPane(panel);
    newFrame.setSize(xFrame, yFrame);
    ImageIcon img = new ImageIcon("src/resourceFolder/Pimage.png");
                      
    newFrame.setIconImage(img.getImage());
    newFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    //newFrame.add(myJScrollPane);
    newFrame.add(panel);
    //newFrame.add(pane);
    //panel.add(myJScrollPane);
    newFrame.setVisible(true);
    
}
*/

}