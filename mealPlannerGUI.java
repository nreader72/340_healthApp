//package prideHealth;

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
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.Reader;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ButtonGroup;







public  class mealPlannerGUI extends JFrame implements ActionListener,Serializable {
    
    //static String item;
    //static String itemType;
    //static String description;
    //static String calories;
    HashMap<String, ArrayList> itemMap = new HashMap<>();
    
    //static String beverage;
    //static String mainItem;
    //static String sideItem;
    //static String sideItem2;
    //static String dessert;
    
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
        mealPlannerGUI object = new mealPlannerGUI();
        
        //creates window through which all navigation is done
        
        JPanel panel = new JPanel();
	object.MealPlanner(panel);
        object.makeJFrame(panel, "Meal Planner", 500, 550);
        
        
        
                  
    }

    
/* 
    method that creates the inner workings of the Meal Planner window
    
    */
public  void MealPlanner(JPanel panel) {

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
        } catch (IOException ex) {
            Logger.getLogger(mealPlannerGUI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(mealPlannerGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
                makeJFrame(panel, "Display Previous Menus", 500, 500);
        
    }
    });
    MenuItem blah = new MenuItem();
    //creates a button that when clicked takes user to the make a menu item window            
    JButton mealPlanChoice2 = new JButton("Make a Menu Item");
    mealPlanChoice2.setBounds(100, 120, 280, 50);
    panel.add(mealPlanChoice2);
    mealPlanChoice2.addActionListener(new ActionListener(){
    @Override
    public void actionPerformed(ActionEvent e){
        
            JPanel panel = new JPanel();
        try {
            MenuItem(panel);
            makeJFrame(panel, "Create A Menu Item", 600, 400);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(mealPlannerGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
            

            
        
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
        try {
            MealPlan(panel);
            makeJFrame(panel, "Create a Meal Plan", 400, 550);
        } catch (IOException ex) {
            Logger.getLogger(mealPlannerGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        
                       
    }
    });
       
    //creates a button that when clicked takes user to a calendar of meal plans
    JButton mealPlanChoice4 = new JButton("Display Meal Plan Calendar");
    mealPlanChoice4.setBounds(100, 280, 280, 50);
    panel.add(mealPlanChoice4);
    mealPlanChoice4.addActionListener(new ActionListener(){
    @Override
    public void actionPerformed(ActionEvent e){
        try {
            JPanel panel = new JPanel();
            Calendar(panel);
            makeJFrame(panel, "Meal Plan Calendar", 500, 350);
        } catch (IOException ex) {
            Logger.getLogger(mealPlannerGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
                       
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
public  void PreviousMenus(final JPanel panel) throws BadLocationException, IOException, InterruptedException{
    PrevMenus previousMenus = new PrevMenus();
    
    previousMenus.displayPlan(panel);
       
}
 
/*
method that builds a window that allows user to create menu item, description and caloric content
*/
public void MenuItem(JPanel panel) throws FileNotFoundException{
    
    MenuItem createItems = new MenuItem();
    
    createItems.setItem(panel);

    
}
/*
method that builds a window that allows user to choose menu items based on family members
preferences and restrictions
*/
public  void MealPlan(JPanel panel) throws IOException{
    
    MealPlan createPlans = new MealPlan();
    
    createPlans.makePlan(panel);
    
    
    }


public void setValue(String value){
     String newValue = value;
}

public JRadioButton getSelectedRadioButton(ButtonGroup buttonGroup) {

    Enumeration<AbstractButton> abstractButtons = buttonGroup.getElements();
    JRadioButton radioButton = null;

    while (abstractButtons.hasMoreElements()) {
        radioButton = (JRadioButton) abstractButtons.nextElement();
        if (radioButton.isSelected()) {
            break;
        }
    }
    return radioButton;
}

/*
method that builds a calendar with meals for the week
*/
        
public  void Calendar(JPanel panel) throws IOException{
     Calendar displayCal = new Calendar();
     displayCal.makeCalendar(panel);
    
     
}

/*
method that builds generic textPanes
*/
public  void makeTextPane(JPanel panel,String mealPlan,int paneX,int paneY,int paneWidth,
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

public  void makeLabel(JPanel panel,String day, int labelX,int labelY,int labelWidth,
                int labelHeighth){
    
    JLabel newLabel = new JLabel(day);
    newLabel.setBounds(labelX, labelY, labelWidth, labelHeighth);
    panel.add(newLabel);
    
}
//MAY HAVE TO OVERLOAD THESE METHODS TO CREATE DIFFERENT FRAMES THAT CAN BE
//"dispose()"-ED, ALSO SOME THAT CAN BE SCROLLED
/*
method that builds generic JFrames
*/
public  void makeJFrame (JPanel panel, String frameName, int xFrame, int yFrame){

    JFrame newFrame = new JFrame(frameName);
    newFrame.setSize(xFrame, yFrame);
    ImageIcon img = new ImageIcon("src/resourceFolder/Pimage.png");
                      
    newFrame.setIconImage(img.getImage());
    newFrame.add(panel);
    newFrame.setVisible(true);
    
}

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public void makeAvailableFrame(String itemType) throws IOException{
        
    JPanel panel = new JPanel();
            makeJFrame(panel, itemType, 300, 250);
            
            AvailableItem availItems = new AvailableItem();
            List<String> available = new ArrayList<String>(availItems.getAvailableItem("[" + itemType));
            available.toArray();
            Object[] mealTypes = available.toArray();
            //String[] mealTypes = new String[] {"Breakfast", "Lunch","Dinner"};
             final JComboBox<Object> choiceBox2 = new JComboBox<>(mealTypes);
            makeLabel(panel,"Please Choose a " + itemType +": ", 10, 125, 200,25);
            choiceBox2.setBounds(250,125,100,25);
            panel.add(choiceBox2);
            //JPanel panel = new JPanel();
        JButton addButton = new JButton("Add Item To Plan");
            addButton.setBounds(150, 200, 150, 25);
            panel.add(addButton);
            
            choiceBox2.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                setValue(choiceBox2.getSelectedItem().toString());
                
                }
                   
    }); 
            
            
            
     
    }
     



    
 

///////////////////////////////////////////////////////LOGIC LAYER
 public class CalorieCount extends mealPlannerGUI{
        
        private int calories;
        
        public int getCalories(String keyItem) throws IOException{
             //calories = item.calories;
             MenuItem object = new MenuItem();
             return  Integer.parseInt(object.getHashValue(keyItem, 2));
            
        }
        
        public void calculateCalories(String bevKey, String mainItemKey, String sideItemKey,
                        String dessertKey) throws IOException{
            calories = getCalories(bevKey) + getCalories(mainItemKey) + getCalories(sideItemKey)+
                    getCalories(dessertKey);
            
        }
        
    }
    
    public class AvailableItem extends mealPlannerGUI{
        
        public List getAvailableItem(String menuType) throws IOException{
            //gets preference list
            Scanner s = new Scanner(new File("C://" + "PrideHealthMangement", "Pref.txt"));
            ArrayList<String> preferenceList = new ArrayList<String>();
            while (s.hasNext()){
            preferenceList.add(s.next());
            }
            s.close();
            
            //gets the menu items and turns into into a hash
            FileInputStream menuItemFile = new FileInputStream("itemMenu.txt");
            ResourceBundle resources;
            resources = new PropertyResourceBundle(menuItemFile);
            Map<String,String> map = new HashMap<String,String>();
            //convert ResourceBundle to Map
            Enumeration<String> keys = resources.getKeys();
            while (keys.hasMoreElements()) {
                String key = keys.nextElement();
                map.put(key, (resources.getString(key)));
                
            }
            
            //turns the string of attributes into an arrayList which reassembles the original hash
            Map<String,ArrayList> newMap = new HashMap<String,ArrayList>();
            List<String> keysList = new ArrayList<String>(map.keySet());
            for(int i = 0; i<keysList.size();i++){
            String values = map.get(keysList.get(i));
            String [] items = values.split(",");
            ArrayList<String> itemList = new ArrayList<String>(Arrays.asList(items));
            newMap.put(keysList.get(i),itemList);
            }
            
            List<String> itemList2 = new ArrayList<String>(newMap.keySet());
            List<String> availList = new ArrayList<String>();
            
            for (Map.Entry<String, ArrayList> entry : newMap.entrySet()) {
                int i = 0;
                if(newMap.get(entry.getKey()).get(0).equals(menuType)){
                    availList.add(i,entry.getKey());
                }
		i++;	
		}
            
                        
            //use this to add all preferences into one file
            ArrayList<String> allPrefs= new ArrayList<String>();
            allPrefs.addAll(preferenceList);
            allPrefs.addAll(preferenceList);
            
            
            //use this to check if there are no issues, if there are not, just print 
            //the list to the meal plan
            if(Collections.disjoint(allPrefs, availList)){
                return availList;
                
            }
            else{
                for(String str: preferenceList){
                if(availList.contains(str)){
                   availList.remove(str);
                    
                }                   
}
               
            }
                         
         return availList;    
            
        
         
        }  
        
        public ArrayList<String> getUsers(String file) throws FileNotFoundException, IOException{
            
            ArrayList<String> users = new ArrayList<String>();
            for(int i = 0; i < users.size(); i++){
               
    
                File inFile = new File(file);
                BufferedReader br = new BufferedReader(new InputStreamReader(
                new FileInputStream(inFile)));
                users.add(br.readLine());
                br.close();
    

}
       return users;         
            }
            
          
            
        }
        
    
    
    
    ////////////////////////////////////////////////////////DATA LAYER
     public class PrevMenus extends mealPlannerGUI{
         private String prevDay;
        private String prevType;
        private String bev;
        private String main;
        private String sideItem;
        private String sideItem2;
        private String dessert;
        private boolean isClicked;
        public MealPlan getPlan(){
            
            
        return null;
        
        
    }
        public String getRatings(MealPlan plan){
         
            return null;
        }
        
        private void displayPlan(final JPanel panel) throws IOException{
            panel.setLayout(null);
    
        MealPlan map = new MealPlan();

        final Map<String,ArrayList> newMap = map.mapFromFile("previousMenus.txt");

        

        final ArrayList keyList = new ArrayList(newMap.keySet());

        System.out.println(keyList.get(1));


        final JTextArea eventHandler = new JTextArea();

     
            eventHandler.setEditable(false);
            panel.add(eventHandler);
            
        final JButton addPlan = new JButton("Add Previous Meal Plan");
                addPlan.setBounds(175,400,200,25);
                panel.add(addPlan);    

        JButton next = new JButton("View Plan");
        next.setBounds(200,225,100,25);
        next.addActionListener(new ActionListener(){
        int counter = 0;    
        @Override
        public void actionPerformed(ActionEvent e){
        
        if(counter<keyList.size()){
                isClicked = false;
            
                eventHandler.setText("Beverage: " + newMap.get(keyList.get(counter)).get(0).toString().replace("[","")
                        + "\n" + "Main Item: " + newMap.get(keyList.get(counter)).get(1).toString().replace("[","") + "\n" +
                        "Side Item: " + newMap.get(keyList.get(counter)).get(2).toString().replace("[","") + "\n" +
                        "Side Item: " + newMap.get(keyList.get(counter)).get(3).toString().replace("[","") + "\n" +
                        "Dessert: " + newMap.get(keyList.get(counter)).get(4).toString().replace("[","") + "\n" +
                        "Ratings: " + newMap.get(keyList.get(counter)).get(5).toString().replace("]",""));
                
                
                eventHandler.setBounds(100,50,300,150);
                panel.add(eventHandler);
                
                
                        
                         bev = newMap.get(keyList.get(counter)).get(0).toString().replace("[","");
                         main = newMap.get(keyList.get(counter)).get(1).toString().replace("[","");
                         sideItem = newMap.get(keyList.get(counter)).get(2).toString().replace("[","");
                         sideItem2 = newMap.get(keyList.get(counter)).get(3).toString().replace("[","");
                         dessert = newMap.get(keyList.get(counter)).get(4).toString().replace("[","");
                        addPlan.addActionListener(new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e){

            try {
                isClicked = true;
                if(isClicked == true){
                MealPlan obj = new MealPlan();
                obj.setPlan(prevDay,prevType,bev,main,sideItem,sideItem2,dessert);
                }
                isClicked = false;
                
                
            } catch (IOException ex) {
                Logger.getLogger(mealPlannerGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
                
            
            }
        
        });
        
                        
                      counter++;
        }
                    }
                });
        

        String[] days = new String[] {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday",
                                    "Friday", "Saturday", "Sunday"};

        final JComboBox<String> choiceBox1 = new JComboBox<>(days);
        makeLabel(panel,"Please Select a Day and Type:", 10, 350, 200,25);
        choiceBox1.setBounds(200,350,100,25); 
        panel.add(choiceBox1);

        choiceBox1.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e){
                    prevDay = choiceBox1.getSelectedItem().toString();

                    }

        }); 

        String[] mealTypes = new String[] {"Breakfast", "Lunch","Dinner"};

        final JComboBox<String> choiceBox2 = new JComboBox<>(mealTypes);
        makeLabel(panel,"If you wish to add this plan,", 10, 300, 200,25);
        choiceBox2.setBounds(325,350,100,25);
        panel.add(choiceBox2);

        choiceBox2.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e){
                    prevType = choiceBox2.getSelectedItem().toString();

                    }

        }); 

        panel.add(next);
        

        }    
    }
    public class MenuItem extends mealPlannerGUI{
        String input;
        String itemType;
        String item;
        //String description;
        String calories;
        
        public void MenuItem(String newItem, String newDescription,String newCalories){
            item = newItem;
            //description = newDescription;
            calories = newCalories;
        }
        
        private void setItem(JPanel panel){
            panel.setLayout(null);
    String[] menuTypes = new String[] {"Beverage", "Main Item", "Side Item", "Dessert"};
    
    final JComboBox<String> choiceBox = new JComboBox<>(menuTypes);
    makeLabel(panel,"Please Choose a Menu Type", 20, 10, 200,25);
    choiceBox.setBounds(250,10,100,25); 
    choiceBox.addActionListener(new ActionListener(){
    @Override
    public void actionPerformed(ActionEvent e){
        //FileWriter fileWriter = null;
        //try {
             itemType = choiceBox.getSelectedItem().toString();

        
    }
    });    
    
    //makes a text field with mock input
    JLabel menuItem = new JLabel("Menu Item:");
    menuItem.setBounds(20, 115, 100,25);
    panel.add(menuItem);
    final JTextField menuField = new JTextField();
    menuField.setBounds(250, 115, 250, 25);
    
    menuField.addActionListener(new ActionListener(){
   @Override
   public void actionPerformed(ActionEvent e){
       
         }
    });
    
    //makes a text field with mock caloric content input
    JLabel caloricContent = new JLabel("Caloric Content:");
    caloricContent.setBounds(20, 185, 100,25);
    panel.add(caloricContent);
    final JTextField caloricField = new JTextField();
    caloricField.setBounds(250, 185, 250, 25);
    
    caloricField.addActionListener(new ActionListener(){
   public void actionPerformed(ActionEvent e){
      
   }
    });
        String[] users = new String[] {"Jeremy", "David", "Preston"};
        JComboBox<String> choiceBox2 = new JComboBox<>(users);
        makeLabel(panel,"View Preferences of:  ", 75, 285, 200,25);
        choiceBox2.setBounds(250,285,150,25);
        panel.add(choiceBox2);
        choiceBox2.addActionListener(new ActionListener(){
    @Override
    public void actionPerformed(ActionEvent e){
        JPanel panel = new JPanel();
        makeJFrame(panel, "Family Member Preferences", 400, 200);
        String user = "blah";
        MenuItem object1 = new MenuItem(); 
        try {
            String blah = object1.displayPreferences(user);
                    JTextArea eventHandler2 = new JTextArea(blah);
                    eventHandler2.setEditable(false);
       eventHandler2.setBounds(10,150,300,300);
        panel.add(eventHandler2);
       
//
//
//object1.displayPreferences(user);
        } catch (IOException ex) {
            Logger.getLogger(mealPlannerGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        }
    });
    
        JButton createItemButton = new JButton("Create Item");
        createItemButton.setBounds(250, 315, 150, 25);
        panel.add(createItemButton);
        panel.add(choiceBox);
        panel.add(menuField);
        panel.add(caloricField);
        createItemButton.addActionListener(new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e){

        //try {
            
           String item1 = menuField.getText();
            //description = descriptionField.getText();
            calories = caloricField.getText();
            
            try {
                
                ArrayList itemElements = new ArrayList();
                itemElements.add(itemType);
                //itemElements.add(description);
                itemElements.add(calories);
                
                itemMap.put(item1,itemElements);
                
                Properties properties = new Properties();


                for (Map.Entry<String,ArrayList> entry : itemMap.entrySet()) {
                    properties.put(entry.getKey(), entry.getValue().toString());
                }



                FileOutputStream fos = new FileOutputStream(("itemMenu.txt"), true);
                properties.store(fos,null);

            }   catch (FileNotFoundException ex) {
                Logger.getLogger(mealPlannerGUI.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(mealPlannerGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
        }
    
   
       
    });
            
        }
        
        public String displayPreferences(String user) throws IOException{
            
            input = new Scanner(new File("testFile.txt")).useDelimiter("\\Z").next();


            return input;
        }
        
        public String getHashValue(String hashKey,int index) throws FileNotFoundException, IOException{
            FileInputStream fis;            
            fis = new FileInputStream("itemMenu.txt");
            ResourceBundle resources;
            resources = new PropertyResourceBundle(fis);
            Map<String,String> map = new HashMap<String,String>();
           //convert ResourceBundle to Map
            Enumeration<String> keys = resources.getKeys();
            while (keys.hasMoreElements()) {
                String key = keys.nextElement();
                map.put(key, (resources.getString(key)));
                
            }
            String s = map.get(hashKey);
            String [] items = s.split(",");
            List<String> itemList = new ArrayList<String>(Arrays.asList(items));
            String value = itemList.get(index);


            return value;
        }
        
        
    }
    
    public class MealPlan extends mealPlannerGUI{
        
        private String day;
        private String mealType;
        private String beverage;
        private String mainItem;
        private String sideItem;
        private String sideItem2;
        private String dessert;
        
        public void MealPlan(String newDay, String newMealType, String newBeverage, 
                String newMainItem, String newSideItem, String newSideItem2, String newDessert){
            
            day = newDay;
            mealType = newMealType;
            beverage = newBeverage;
            mainItem = newMainItem;
            sideItem = newSideItem;
            sideItem2 = newSideItem2;
            dessert = newDessert;
            
        }
        
        void setPlan(String day, String type, String bev, String mainDish, 
                        String sideDish, String sideDish2,String dessertItem) throws FileNotFoundException, IOException {
            
           try {
            String key = day + type;
            ArrayList itemElements = new ArrayList();
            itemElements.add(bev);
            itemElements.add(mainDish);
            itemElements.add(sideDish);
            itemElements.add(sideDish2);
            itemElements.add(dessertItem);
            
             HashMap<String, ArrayList> mealMap = new HashMap<>();
            
            mealMap.put(key,itemElements);
            
            Properties properties = new Properties();

        
            for (Map.Entry<String,ArrayList> entry : mealMap.entrySet()) {
            properties.put(entry.getKey(), entry.getValue().toString());
}


        
            FileOutputStream fos = new FileOutputStream(("mealPlan.txt"), true);
            properties.store(fos,null);
            
        
    }   catch (FileNotFoundException ex) {
            Logger.getLogger(mealPlannerGUI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(mealPlannerGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    
        }
        public Map<String,ArrayList> mapFromFile(String file) throws FileNotFoundException, IOException{
            
                    FileInputStream mapFile = new FileInputStream(file);
                    ResourceBundle resources;
                    resources = new PropertyResourceBundle(mapFile);
                    Map<String,String> map = new HashMap<String,String>();
                    //convert ResourceBundle to Map
                    Enumeration<String> keys = resources.getKeys();
                    while (keys.hasMoreElements()) {
                        String key = keys.nextElement();
                        map.put(key, (resources.getString(key)));
                        
                    }       //turns the string of attributes into an arrayList which reassembles the original hash
                    Map<String,ArrayList> newMap = new HashMap<String,ArrayList>();
                    List<String> keysList = new ArrayList<String>(map.keySet());
                    for(int i = 0; i<keysList.size();i++){
                        String values = map.get(keysList.get(i));
                        String [] items = values.split(",");
                        ArrayList<String> itemList = new ArrayList<String>(Arrays.asList(items));
                        newMap.put(keysList.get(i),itemList);
                        
                        
                    }
            return newMap;       
            
        }
        
        public void mapToFile(String file) throws IOException{
            
            MealPlan map = new MealPlan();
    
            Map<String,ArrayList> newMap = map.mapFromFile("mealPlan.txt");
            
            /*
            Here is where the ratings will be appended to.  If they are just added
            in a specific sequence I should be good.
            */
            
            
            Properties properties = new Properties();

            for (Map.Entry<String,ArrayList> entry : newMap.entrySet()) {
                properties.put(entry.getKey(), entry.getValue().toString());
            }



            FileOutputStream fos = new FileOutputStream((file), true);
            properties.store(fos,null);
            
        }
       
        
        private void makePlan(JPanel panel) throws IOException{
            panel.setLayout(null);
    
        String[] days = new String[] {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday",
                                    "Friday", "Saturday", "Sunday"};

        final JComboBox<String> choiceBox1 = new JComboBox<>(days);
        makeLabel(panel,"Please Choose a Day:", 10, 10, 200,25);
        choiceBox1.setBounds(250,10,100,25); 
        panel.add(choiceBox1);

        choiceBox1.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e){
                    day = choiceBox1.getSelectedItem().toString();

                    }

        }); 

        String[] mealTypes = new String[] {"Breakfast", "Lunch","Dinner"};

        final JComboBox<String> choiceBox2 = new JComboBox<>(mealTypes);
        makeLabel(panel,"Please Choose a Meal Type:", 10, 60, 200,25);
        choiceBox2.setBounds(250,60,100,25);
        panel.add(choiceBox2);

        choiceBox2.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e){
                    mealType = choiceBox2.getSelectedItem().toString();

                    }

        }); 
    
    
            JLabel userLabel = new JLabel("Available Menu Items Based on Preferences and Restricitions");
            userLabel.setBounds(10, 100, 400, 25);
            panel.add(userLabel);

            AvailableItem availBevItems = new AvailableItem();
            List<String> availableBev = new ArrayList<String>(availBevItems.getAvailableItem("[" + "Beverage"));
            Object[] availBevList = availableBev.toArray();
            final JComboBox<Object> BevBox3 = new JComboBox<>(availBevList);
            makeLabel(panel,"Please Choose a Beverage: ", 10, 150, 200,25);
            BevBox3.setBounds(250,150,100,25);
            panel.add(BevBox3);
            
            
            BevBox3.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                beverage = BevBox3.getSelectedItem().toString();
                
                }
                   
    }); 
            AvailableItem availMainItems = new AvailableItem();
            List<String> availableMain = new ArrayList<String>(availMainItems.getAvailableItem("[" + "Main Item"));
            Object[] availMainList = availableMain.toArray();
            final JComboBox<Object> MainBox = new JComboBox<>(availMainList);
            makeLabel(panel,"Please Choose a Main Item: ", 10, 200, 200,25);
            MainBox.setBounds(250,200,100,25);
            panel.add(MainBox);
            
            
            MainBox.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                mainItem = MainBox.getSelectedItem().toString();
                
                }
                   
    }); 
            
            AvailableItem availSideItem1 = new AvailableItem();
            List<String> availableSideItem1 = new ArrayList<String>(availSideItem1.getAvailableItem("[" + "Side Item"));
            Object[] availSideList1 = availableSideItem1.toArray();
            final JComboBox<Object> SideBox1 = new JComboBox<>(availSideList1);
            makeLabel(panel,"Please Choose a Side Item: ", 10, 250, 200,25);
            SideBox1.setBounds(250,250,100,25);
            panel.add(SideBox1);
            
            
            SideBox1.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                sideItem = SideBox1.getSelectedItem().toString();
                
                }
                   
    }); 
            
            AvailableItem availSideItem2 = new AvailableItem();
            List<String> availableSideItem2 = new ArrayList<String>(availSideItem2.getAvailableItem("[" + "Side Item"));
            Object[] availSideList2 = availableSideItem2.toArray();
            final JComboBox<Object> SideBox2 = new JComboBox<>(availSideList2);
            makeLabel(panel,"Please Choose a Second Side Item: ", 10, 300, 235,25);
            SideBox2.setBounds(250,300,100,25);
            panel.add(SideBox2);
            
            
            SideBox2.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                sideItem2 = SideBox2.getSelectedItem().toString();
                
                }
                   
    }); 
            
            AvailableItem availDessertItem = new AvailableItem();
            List<String> availableDessertItem = new ArrayList<String>(availDessertItem.getAvailableItem("[" + "Dessert"));
            Object[] availDessertList = availableDessertItem.toArray();
            final JComboBox<Object> DessertBox1 = new JComboBox<>(availDessertList);
            makeLabel(panel,"Please Choose a Dessert: ", 10, 350, 200,25);
            DessertBox1.setBounds(250,350,100,25);
            panel.add(DessertBox1);
            
            
            DessertBox1.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                dessert = DessertBox1.getSelectedItem().toString();
                
                }
                   
    }); 
        
            JButton mealPlanChoice1 = new JButton("Create Plan");
            mealPlanChoice1.setBounds(100, 445, 175, 50);
            panel.add(mealPlanChoice1);
            mealPlanChoice1.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                try {
                    MealPlan plan = new MealPlan();
                    plan.setPlan(day, mealType, beverage, mainItem,            
                            sideItem, sideItem2,dessert);
                } catch (IOException ex) {
                    Logger.getLogger(mealPlannerGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
    });
    
    
           
            
            
        }
        
        public MenuItem getAvailable(){
            
            return null;
        }
        
    }
    
    public class Calendar extends mealPlannerGUI{
        
        private String day;
        private String mealType;
        private String calDay;
        private String type;
        
        
        public MealPlan getPlan(){
            
            return null;
        }
        
        public void makeCalendar(JPanel panel) throws FileNotFoundException, IOException{
            panel.setLayout(null);
    
    
    
    //creates breakfast, lunch and dinner labels
    JLabel instruction = new JLabel("Display Meal Plan For: ");
    instruction.setBounds(10, 25, 200, 25);
    panel.add(instruction);
    
    String[] mealTypes = new String[] {"Breakfast", "Lunch","Dinner"};
    
    String[] days = new String[] {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday",
                                "Friday", "Saturday", "Sunday"};
    
    final JComboBox<String> choiceBox1 = new JComboBox<>(days);
    makeLabel(panel,"Please Choose a Day", 10, 75, 200,25);
    choiceBox1.setBounds(250,75,100,25);
    panel.add(choiceBox1);
    
    choiceBox1.addActionListener(new ActionListener(){
        
            @Override
            public void actionPerformed(ActionEvent e){
                 
               calDay = choiceBox1.getSelectedItem().toString();
                
                }
                   
    }); 
    
    
    final JComboBox<String> choiceBox2 = new JComboBox<>(mealTypes);
    makeLabel(panel,"Please Choose a Meal Type: ", 10, 125, 200,25);
    choiceBox2.setBounds(250,125,100,25);
    panel.add(choiceBox2);
     
    choiceBox2.addActionListener(new ActionListener(){
        
            @Override
            public void actionPerformed(ActionEvent e){
                
                    type  = choiceBox2.getSelectedItem().toString();
            }            
    }); 
    
    JButton displayCal = new JButton("Display Calendar");
    displayCal.setBounds(200, 225, 150, 35);
    panel.add(displayCal);
    
               
    displayCal.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                
                try {
                    
                    BufferedReader br;     
                    br = new BufferedReader(new FileReader("mealPlan.txt"));
                    if (br.readLine() == null) {
                        JPanel errorPanel = new JPanel();
                        makeJFrame(errorPanel, "No Meal to Display", 300,300);
                        makeLabel(errorPanel, "Would you like to add a Meal Plan",10,10,200,25);
                    }
                    else{
                        final JPanel newPanel = new JPanel();
                    
                    makeJFrame(newPanel, calDay + " " + type, 300,250);
                        FileInputStream menuItemFile = null;
                        menuItemFile = new FileInputStream("mealPlan.txt");
                        System.out.println("empty");
                        ResourceBundle resources;
                        System.out.println("empty44");
                        resources = new PropertyResourceBundle(menuItemFile);
                        
                        System.out.println("empty55");
                        Map<String,String> map = new HashMap<String,String>();
                        System.out.println("empty2");
                        //convert ResourceBundle to Map
                        Enumeration<String> keys = resources.getKeys();
                        while (keys.hasMoreElements()) {
                            String key = keys.nextElement();
                            map.put(key, (resources.getString(key)));
                            
                        }
                        System.out.println("empty3");//turns the string of attributes into an arrayList which reassembles the original hash
                        final Map<String,ArrayList> newMap = new HashMap<String,ArrayList>();
                        final List<String> keysList = new ArrayList<String>(map.keySet());
                        for(int i = 0; i<keysList.size();i++){
                            String values = map.get(keysList.get(i));
                            String [] items = values.split(",");
                            ArrayList<String> itemList = new ArrayList<String>(Arrays.asList(items));
                            newMap.put(keysList.get(i),itemList);
                        }
                        
                        
                        System.out.println(newMap);
                        //JTextField calendarContent = new JTextField("success");
                        System.out.println(newMap.keySet());
                        System.out.println(day + type);
                        //this needs to be formatted differently and the brackets taken away!!!!
                        if(!keysList.contains(calDay + type)){
                            makeLabel(newPanel, "There is no Meal Plan for this day and time.",10,10,250,25);
                            
                        }
                        else{
                        JTextArea calendarContent = new JTextArea("Beverage: " +
                                newMap.get(calDay + type).get(0).toString().replace("[","") + "\n"
                                + "Main Item: " + newMap.get(calDay + type).get(1).toString().replace("[","") + "\n"
                                + "Side Item: " + newMap.get(calDay + type).get(2).toString().replace("[","") + "\n"
                                + "Side Item: " + newMap.get(calDay + type).get(3).toString().replace("[","") + "\n"
                                + "Dessert: " + newMap.get(calDay + type).get(4).toString().replace("]",""));    
                        calendarContent.setEditable(false);
                        calendarContent.setBounds(20, 50, 300, 300);
                        newPanel.add(calendarContent);
                        }
                        
                        
                        
                        
                    }   } catch (FileNotFoundException ex) {
                    Logger.getLogger(mealPlannerGUI.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(mealPlannerGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }  
    }); 
    
    
        
    }
        
    
        
        }
    }

   

