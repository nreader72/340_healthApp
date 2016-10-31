
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.*;
import java.util.*;
import javax.swing.event.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Admin
 */
public class MainPT {
    public static void main(String[] args) {
        String name = "David";
        String[] things = { "Cooldown", "Elliptical", "Hiking", "Pushups", "Running", 
            "Sit-ups", "Warm-up", "Weights" };//Demo equipment list
        
        new PT();
        //new SAP(0);
        
        //new ViewPlans(name);
        //new Modify(name);
        
        //new ViewRatings(name);
        
        //new MakePlan(name);
        
        //new ViewActivities(things);
        //new Create(things);
        //new ModifySelect();
        //new ModifyAct();
        
    }
}
    class PT extends JFrame{
        
    public PT(){
        super("Personal Trainer");
        JPanel background = new JPanel();
        background.setLayout(new BoxLayout(background, BoxLayout.PAGE_AXIS));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500,400);
        
        setLocationRelativeTo(null);
        background.setBackground(Color.yellow);
        
        final String[] things = { "Cooldown", "Elliptical", "Hiking", "Pushups", "Running", 
            "Sit-ups", "Warm-up", "Weights" };//Demo equipment list
       
        JButton MXP =  new JButton("Make Exercise Plan");
        JButton VXR =  new JButton("View Exercise Ratings");
        JButton VXP = new JButton("View Old Exercise Plans");
        JButton VXA =  new JButton("View Exercise Activities");
        JButton exit = new JButton("Exit to Family Planner");
        
        MXP.setAlignmentX(Component.CENTER_ALIGNMENT);
        VXR.setAlignmentX(Component.CENTER_ALIGNMENT);
        VXP.setAlignmentX(Component.CENTER_ALIGNMENT);
        VXA.setAlignmentX(Component.CENTER_ALIGNMENT);
        exit.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        background.add(Box.createVerticalGlue());
        background.add(MXP);
        MXP.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                new SAP(1);
                dispose();
            }});
        
        background.add(Box.createVerticalGlue());
        background.add(VXR);
        VXR.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                new SAP(2);
                dispose();
            }});
        
        background.add(Box.createVerticalGlue());
        background.add(VXP);
        VXP.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                new SAP(3);
                dispose();
            }});
        
        
        background.add(Box.createVerticalGlue());
        background.add(VXA);
        VXA.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                new ViewActivities(things);
                dispose();
                
            }});
        
        
        background.add(Box.createVerticalGlue());
        background.add(exit);
        exit.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                dispose();
                //FamilyMemberGUI.fullscreenGUI();
            }});
        
        background.add(Box.createVerticalGlue());
        
        add(background); 
        setVisible(true);
        
        
        //pack();
  }   
        
}
class SAP extends JFrame {
        public static int selected;
    public SAP(int option){
        
        super("Personal Trainer");
        JPanel background = new JPanel();
        background.setLayout(new BoxLayout(background, BoxLayout.PAGE_AXIS));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500,400);
        selected = option;
        
        background.setBackground(Color.yellow);
        setLocationRelativeTo(null);
        
        background.add(Box.createVerticalGlue());
        JPanel namesLayout = new JPanel();
        namesLayout.setLayout(new BoxLayout(namesLayout, BoxLayout.PAGE_AXIS));
        namesLayout.setBackground(Color.yellow);
        namePress namepress = new namePress();
        String[] names = {"David", "Preston", "Jeremy"};
        for (int i = 0; i < names.length; i++){
            final JButton[] listofnames = new JButton[names.length];
            listofnames[i] = new JButton(names[i]);
            listofnames[i].setAlignmentX(Component.CENTER_ALIGNMENT);
            listofnames[i].addActionListener(namepress);
            namesLayout.add(listofnames[i]);
            namesLayout.add(Box.createVerticalGlue());
        }
        background.add(namesLayout);
        
        JButton exit = new JButton("Exit to Main Menu");
        exit.setAlignmentX(Component.CENTER_ALIGNMENT);
        background.add(exit);
        exit.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                new PT();
                dispose();
            }});
        background.add(Box.createVerticalGlue());
                
        add(background, BorderLayout.CENTER);
        setVisible(true);
}
    private class namePress implements ActionListener{
    
    public void actionPerformed (ActionEvent e){
    
    System.out.println(e.getActionCommand());
    System.out.println(selected);
    if (selected == 1){
        new MakePlan(e.getActionCommand());
        dispose();
     }
    else if (selected == 2){
        new ViewRatings(e.getActionCommand());
        dispose();
        }
    else if (selected == 3){
        new ViewPlans(e.getActionCommand());
        dispose();
        } 
       }
     }
    }
    

class MakePlan extends JFrame{
    
    public MakePlan(String name){
        super("Make Exercise Plan"); //title
        final SpinnerDateModel datemodel = new SpinnerDateModel(new Date(), null, null,
                                                            Calendar.DAY_OF_WEEK);
        final DateFormat dateformat = new SimpleDateFormat("dd MMM yyyy");
        
        String name1 = "David";  //Demo test string
        setSize(400,300);
        
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        JPanel background = new JPanel();
        background.setLayout(new BoxLayout(background, BoxLayout.PAGE_AXIS));
        add(background);
        
        //List of plans title
        JLabel plans = new JLabel("List of Plans for " + name1);
        plans.setAlignmentX(Component.CENTER_ALIGNMENT);
        background.add(plans);
        
        //List of plans portion
        JPanel lopPane = new JPanel();
        lopPane.setLayout(new FlowLayout());
        JScrollPane lopScroll = new JScrollPane(lopPane);
        
        JPanel lopBox = new JPanel();
        lopBox.setLayout(new BoxLayout(lopBox, BoxLayout.PAGE_AXIS));
        String dateBox[] = {"10/25/16", "Tue" };
        JTextArea[] db = new JTextArea[2];
        for(int i = 0; i < dateBox.length; i++)
            {
            db[i] = new JTextArea(1,10);
            db[i].setText(dateBox[i]);
            db[i].setEditable(false);
            lopBox.add(db[i]);
           }
        lopPane.add(lopBox);
        
        JPanel timeBox = new JPanel();
        timeBox.setLayout(new BoxLayout(timeBox, BoxLayout.PAGE_AXIS));
        JTextArea timeLOP = new JTextArea(1, 10);
        timeLOP.setEditable(false);
       
        String times[] = {"12:00 PM", "12:10 PM", "12:50 PM"};
        JTextArea[] tb = new JTextArea[3];
        for(int i = 0; i < times.length; i++)
            {
            tb[i] = new JTextArea(1,10);
            tb[i].setText(times[i]);
            tb[i].setEditable(false);
            timeBox.add(tb[i]);
           }
        lopPane.add(timeBox);
        
        JPanel lopBoxRight = new JPanel();
        lopBoxRight.setLayout(new BoxLayout(lopBoxRight, BoxLayout.PAGE_AXIS));
        String lops[] = {"Warmup", "Running", "Cooldown"};
        JTextArea[] lb = new JTextArea[3];
        for(int i = 0; i < 3; i++)
            {
            lb[i] = new JTextArea(1,10);
            lb[i].setText(lops[i]);
            lb[i].setEditable(false);
            lopBoxRight.add(lb[i]);   
            }
        lopPane.add(lopBoxRight);
        background.add(lopScroll);
        
        
        final JPanel dowdisplay = new JPanel();
        FlowLayout dowLayout = new FlowLayout();
        dowdisplay.setLayout(dowLayout);
        JLabel date = new JLabel("Date: ");
        dowdisplay.add(date);
        
        final JSpinner datespinner = new JSpinner(datemodel);
        JSpinner.DateEditor dateeditor = new JSpinner.DateEditor(datespinner, "dd MMM yyyy");
        JFormattedTextField datetextfield = dateeditor.getTextField();
        datetextfield.setEditable(false);
        datespinner.setEditor(dateeditor);
        dowdisplay.add(datespinner);
        
        JLabel dowtitle = new JLabel("Day of the Week: ");
        dowdisplay.add(dowtitle);
        final JTextArea dowspace = new JTextArea(1,5);
        dowspace.setText(datespinner.getValue().toString().substring( 0, 3));
        dowdisplay.add(dowspace);
        
        datespinner.addChangeListener(new ChangeListener()
        {
            public void stateChanged(ChangeEvent e)
            {
            dowspace.setText(datespinner.getValue().toString().substring( 0, 3));
            }
        });
        background.add(dowdisplay);
        
        //Time display portion
        JPanel timedisplay = new JPanel();
        FlowLayout timeLayout = new FlowLayout();
        timedisplay.setLayout(timeLayout);
        timeLayout.setHgap(10);
        JLabel time = new JLabel("Time: ");
        timedisplay.add(time);
        String[] tod = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" };
        JComboBox timelist = new JComboBox(tod);
        timedisplay.add(timelist);
        timedisplay.add(new JLabel(":"));
        
        SpinnerNumberModel secmodel = new SpinnerNumberModel(0, 0, 59, 1);
        JSpinner sec = new JSpinner(secmodel);
        timedisplay.add(sec);
        
        String[] ampm = { "AM", "PM" };
        JComboBox ampmlist = new JComboBox(ampm);
        timedisplay.add(ampmlist);
        background.add(timedisplay);
        
        //Activity display portion
        JPanel activitydisplay = new JPanel();
        FlowLayout activityLayout = new FlowLayout();
        timedisplay.setLayout(timeLayout);
        timeLayout.setHgap(10);
        JLabel activity = new JLabel("Activity: ");
        activitydisplay.add(activity);
        String[] things = { "Cooldown", "Elliptical", "Hiking", "Pushups", "Running", 
            "Sit-ups", "Warm-up", "Weights" };
        JComboBox thingslist = new JComboBox(things);
        activitydisplay.add(thingslist);
        background.add(activitydisplay);
        
        JTextField statustxt = new JTextField(20);
        statustxt.setHorizontalAlignment(JTextField.CENTER);
        statustxt.setEditable(false);
        statustxt.setText("Standing by to create plan");
        
        //Enter button
        JButton enter = new JButton("Enter: ");
        enter.setAlignmentX(Component.CENTER_ALIGNMENT);
        background.add(enter);
        enter.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                dowdisplay.remove(datespinner);
            }});
        
        //Save button
        JPanel bottomdisplay = new JPanel();
        FlowLayout bottomLayout = new FlowLayout();
        bottomdisplay.setLayout(bottomLayout);
        bottomLayout.setHgap(10);
        
        JButton save = new JButton("Save and Exit");
        save.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                System.out.println("Save");
            }});
        
        //Exit button
        JButton exit = new JButton("Exit to Personal Trainer Main Menu");
        exit.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                new PT();
                dispose();
            }});
        bottomdisplay.add(save);
        bottomdisplay.add(exit);
        background.add(bottomdisplay);
        background.add(statustxt);
        
        //pack();
        setVisible(true);
      
    }
    
}
 class ViewPlans extends JFrame{
        public static String name;
    public ViewPlans(final String name){
        super("View Exercise Plans");
        //setLayout(new FlowLayout());
        setSize(500,400);
        
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        JPanel background = new JPanel();
        background.setLayout(new BoxLayout(background, BoxLayout.Y_AXIS));
        add(background);
        
        JPanel toptitle = new JPanel();
        toptitle.setLayout(new FlowLayout());
        JLabel op = new JLabel("Old Plans for");
        op.setAlignmentX(Component.CENTER_ALIGNMENT);
        toptitle.add(op);
        String x = "David"; //Demo sample name
        JLabel titlename = new JLabel(x);
        toptitle.add(titlename);
        background.add(toptitle);
        
        FlowLayout dtaLayout = new FlowLayout();
        JPanel dtaPane = new JPanel();
        dtaPane.setLayout(dtaLayout);
        dtaLayout.setHgap(40);
        JLabel date = new JLabel("Date: ");
        JLabel time = new JLabel("Time: ");
        JLabel activity = new JLabel("Activity: ");
        dtaPane.add(date);
        dtaPane.add(time);
        dtaPane.add(activity);
        background.add(dtaPane);
        
        JPanel lopPane = new JPanel();
        FlowLayout lopLayout = new FlowLayout();
        lopPane.setLayout(lopLayout);
        JScrollPane lopScroll = new JScrollPane(lopPane);
        
        JPanel lopBox = new JPanel();
        lopBox.setLayout(new BoxLayout(lopBox, BoxLayout.PAGE_AXIS));
        String dateBox[] = {"10/25/16", "Tue" };
        JTextArea[] db = new JTextArea[2];
        for(int i = 0; i < dateBox.length; i++)
            {
            db[i] = new JTextArea(1,5);
            db[i].setText(dateBox[i]);
            db[i].setEditable(false);
            lopBox.add(db[i]);
           }
        lopPane.add(lopBox);
        
        JPanel timeBox = new JPanel();
        timeBox.setLayout(new BoxLayout(timeBox, BoxLayout.PAGE_AXIS));
        JTextArea timeLOP = new JTextArea(1, 10);
        timeLOP.setEditable(false);
       
        String times[] = {"12:00 PM", "12:10 PM", "12:50 PM"};
        JTextArea[] tb = new JTextArea[3];
        for(int i = 0; i < 3; i++){
            tb[i] = new JTextArea(1,5);
            tb[i].setText(times[i]);
            tb[i].setEditable(false);
            timeBox.add(tb[i]);
        }
        lopPane.add(timeBox);
        
        JPanel lopMiddleBox = new JPanel();
        lopMiddleBox.setLayout(new BoxLayout(lopMiddleBox, BoxLayout.PAGE_AXIS));
        JTextArea lopLOP = new JTextArea(1, 10);
        String lops[] = {"Warmup", "Running", "Cooldown"};
        JTextArea[] lb = new JTextArea[3];
        for(int i = 0; i < 3; i++)
            {
            lb[i] = new JTextArea(1,10);
            lb[i].setText(lops[i]);
            lb[i].setEditable(false);
            lopMiddleBox.add(lb[i]);   
            }
        lopPane.add(lopMiddleBox);
        background.add(lopScroll);
        
        JPanel cyclePane = new JPanel();
        cyclePane.setLayout(new FlowLayout());
        JButton plus = new JButton("+");
        plus.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                System.out.println("plus pressed");
            }});
        cyclePane.add(plus);
        JLabel cycletitle = new JLabel("Cycle through Old Plans");
        cyclePane.add(cycletitle);
        JButton minus = new JButton("--");
        minus.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                System.out.println("minus pressed");
            }});
        cyclePane.add(minus);
        background.add(cyclePane);
        
        JButton modify = new JButton("Modify Plans");
        modify.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                dispose();
                new Modify(name);
            }});
        
        JButton exit = new JButton("Exit to Personal Trainer Main Menu");
        exit.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                dispose();
                new PT();
            }});
        
        JPanel MVP = new JPanel(new FlowLayout());
        MVP.add(modify);
        MVP.add(exit);
        background.add(MVP);
        //pack();
        setVisible(true);
        
    }
}
    class Modify extends JFrame{
        public static String name;
        
        public Modify (final String name){
            super("Modify Exercise Plans");
            
            setSize(500,400);
             
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            setLocationRelativeTo(null);
            
            JPanel background = new JPanel();
            background.setLayout(new BoxLayout(background, BoxLayout.Y_AXIS));
            add(background);
            
            JPanel toptitle = new JPanel();
            toptitle.setLayout(new FlowLayout());
            JLabel changes = new JLabel("Make Changes for");
            changes.setAlignmentX(Component.CENTER_ALIGNMENT);
            toptitle.add(changes);
            String x = "David"; //Demo sample name
            JLabel titlename = new JLabel(x);
            toptitle.add(titlename);
            background.add(toptitle);
            
            
            FlowLayout dtaLayout = new FlowLayout();
            JPanel dtaPane = new JPanel();
            dtaPane.setLayout(dtaLayout);
            dtaLayout.setHgap(40);
            JLabel date = new JLabel("Date: ");
            JLabel time = new JLabel("Time: ");
            JLabel activity = new JLabel("Activity: ");
            dtaPane.add(date);
            dtaPane.add(time);
            dtaPane.add(activity);
            background.add(dtaPane);
            
            JButton back = new JButton("Go Back to View Old Plans");
            back.setAlignmentX(Component.CENTER_ALIGNMENT);
            back.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                new ViewPlans(name);
                dispose();
            }});
            
            
           
        JPanel lopPane = new JPanel();
        FlowLayout lopLayout = new FlowLayout();
        lopPane.setLayout(lopLayout);
        JScrollPane lopScroll = new JScrollPane(lopPane);
        
        JPanel lopBox = new JPanel();
        lopBox.setLayout(new BoxLayout(lopBox, BoxLayout.PAGE_AXIS));
        String dateBox[] = {"10/25/16", "Tue" };
        JTextArea[] db = new JTextArea[2];
        for(int i = 0; i < dateBox.length; i++)
            {
            db[i] = new JTextArea(1,5);
            db[i].setText(dateBox[i]);
            db[i].setEditable(false);
            lopBox.add(db[i]);
           }
        lopPane.add(lopBox);
        
        JPanel timeBox = new JPanel();
        timeBox.setLayout(new BoxLayout(timeBox, BoxLayout.PAGE_AXIS));
        JTextArea timeLOP = new JTextArea(1, 10);
        timeLOP.setEditable(false);
       
        String times[] = {"12:00 PM", "12:10 PM", "12:50 PM"};
        JTextArea[] tb = new JTextArea[3];
        for(int i = 0; i < 3; i++){
            tb[i] = new JTextArea(1,5);
            tb[i].setText(times[i]);
            tb[i].setEditable(true);
            timeBox.add(tb[i]);
        }
        lopPane.add(timeBox);
        
        JPanel lopMiddleBox = new JPanel();
        lopMiddleBox.setLayout(new BoxLayout(lopMiddleBox, BoxLayout.PAGE_AXIS));
        JTextArea lopLOP = new JTextArea(1, 10);
        String lops[] = {"Warmup", "Running", "Cooldown"};
        JTextArea[] lb = new JTextArea[3];
        for(int i = 0; i < 3; i++)
            {
            lb[i] = new JTextArea(1,10);
            lb[i].setText(lops[i]);
            lb[i].setEditable(true);
            lopMiddleBox.add(lb[i]);   
            }
        lopPane.add(lopMiddleBox);
        
        JPanel MVP1 = new JPanel(new FlowLayout());
        JButton save = new JButton("Save Changes and Exit"); 
        save.setAlignmentX(Component.CENTER_ALIGNMENT);
        save.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e){
                System.out.println("Save Pressed");
            }});
            
        JButton exit = new JButton("Exit to Personal Trainer Main Menu");
        exit.setAlignmentX(Component.CENTER_ALIGNMENT);
        exit.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent e){
                dispose();
                new PT();
            }});
        
        MVP1.add(save);
        MVP1.add(exit);
        
        background.add(lopScroll);
        background.add(back);
        background.add(save);
        background.add(MVP1);
        //pack();
        setVisible(true);
        }
    }
 class ViewRatings extends JFrame{
        public static String name;
    public ViewRatings(String name){
        super("View Exercise Ratings");
        setSize(500,400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        
        JPanel background = new JPanel();
        background.setLayout(new BoxLayout(background, BoxLayout.PAGE_AXIS));
        add(background);
        
        //Title of Page
        JPanel toptitle = new JPanel();
        toptitle.setLayout(new FlowLayout());
        JLabel list = new JLabel("List of Activities rated for ");
        list.setAlignmentX(Component.CENTER_ALIGNMENT);
        toptitle.add(list);
        
        String x = "David"; //Demo sample name
        JLabel titlename = new JLabel(x);
        toptitle.add(titlename);
        background.add(toptitle);
        
        
        //Exercise & Rating titles
        FlowLayout ratingsLayout = new FlowLayout();
        JPanel ratingstitle = new JPanel();
        ratingstitle.setLayout(ratingsLayout);
        ratingsLayout.setHgap(40);
        
        JLabel exercise = new JLabel("Exercise");
        ratingstitle.add(exercise);
        
        JLabel ratings = new JLabel("Rating");
        ratingstitle.add(ratings);
        background.add(ratingstitle);
        
        //Demo string
        String[] things = { "Cooldown", "Elliptical", "Hiking", "Pushups", "Running", 
            "Sit-ups", "Warm-up", "Weights" };
        
        //List of Exercise & Ratings Display
        FlowLayout lopTopLayout = new FlowLayout();
        JPanel lopTop = new JPanel();
        lopTop.setLayout(lopTopLayout);
        lopTopLayout.setHgap(50);
        
        //Setup Boxlayout for lop displays
        JPanel lopBottomLeft = new JPanel();
        lopBottomLeft.setLayout(new BoxLayout(lopBottomLeft, BoxLayout.PAGE_AXIS));
        JTextArea[] prefsarray = new JTextArea[3];
        for(int i = 0; i < 3; i++){
            {
            prefsarray[i] = new JTextArea(1,5);
            prefsarray[i].setText(things[i]);
            prefsarray[i].setEditable(false);
            lopBottomLeft.add(prefsarray[i]);   
            }
        }
        lopTop.add(lopBottomLeft);
        JPanel lopBottomRight = new JPanel();
        lopBottomRight.setLayout(new BoxLayout(lopBottomRight, BoxLayout.PAGE_AXIS));
        String [] nums = {"5","3","4"};//Demo string representing ratings
        JTextArea[] ratingsarray = new JTextArea[3];
        for(int i = 0; i < 3; i++){
            ratingsarray[i] = new JTextArea(1,5);
            ratingsarray[i].setText(nums[i]);
            ratingsarray[i].setEditable(false);
            lopBottomRight.add(ratingsarray[i]);
        }
        lopTop.add(lopBottomRight);
        background.add(lopTop);
        
        JPanel ratinglist = new JPanel();
        BorderLayout rLayout = new BorderLayout();
        ratinglist.setLayout(rLayout);
        rLayout.setHgap(30);
        
        JComboBox thingslist = new JComboBox(things);
        JScrollPane thingsPane = new JScrollPane(thingslist);
        ratinglist.add(thingsPane, BorderLayout.WEST);
        ratinglist.add(thingsPane);
        
        //Setup Preferences box
        JLabel preferences = new JLabel("Preferences");
        preferences.setAlignmentX(Component.CENTER_ALIGNMENT);
        background.add(preferences, BorderLayout.EAST);
        
        JTextArea prefs = new JTextArea(5, 20);
        prefs.setText("Prefers to exercise on Monday & Wednesday in the afternoon\nDoesn't like to run"
                + " but prefers the treadmill. \nLimit exercise to no more than 75 minutes.");
        JScrollPane prefPane = new JScrollPane(prefs);
        prefs.setEditable(true);
        background.add(prefPane);
        
        JPanel bottomlist = new JPanel();
        FlowLayout bottomLayout = new FlowLayout();
        ratinglist.setLayout(bottomLayout);
        bottomLayout.setHgap(30);
        
        //Exit Button
        JButton exit = new JButton("Exit to Personal Trainer Main Menu");
            exit.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                dispose();
                new PT();
            }});
          
        bottomlist.add(exit);
        background.add(bottomlist);
        setVisible(true);
        //pack();
       
    }
    
}
 class ViewActivities extends JFrame{
     
    public ViewActivities(final String[] things){
        super("View Exercise Activities");
        setSize(500,400);
        
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        JPanel background = new JPanel();
        background.setLayout(new BoxLayout(background, BoxLayout.PAGE_AXIS));
        add(background);
        
        
        JLabel list = new JLabel("List of Activities");
        list.setAlignmentX(Component.CENTER_ALIGNMENT);
        background.add(list);
        
        JList thingslist = new JList(things);
        JScrollPane thingsPane = new JScrollPane(thingslist);
        background.add(thingsPane);
        
        background.add(Box.createVerticalGlue());
        JButton create = new JButton("Create Exercise Activity");
        create.setAlignmentX(Component.CENTER_ALIGNMENT);
        create.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                new Create(things);
                dispose();
            }});
        background.add(create);
        
        background.add(Box.createVerticalGlue());
        JButton modify = new JButton("Modify Exercise Activity");
        modify.setAlignmentX(Component.CENTER_ALIGNMENT);
        modify.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                new ModifySelect();
                dispose();
            }});
        background.add(modify);
        
        background.add(Box.createVerticalGlue());
        JButton exit = new JButton("Exit to Personal Trainer Main Menu");
        exit.setAlignmentX(Component.CENTER_ALIGNMENT);
        exit.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
               new PT(); 
               dispose();
            }});
        background.add(exit);
        setVisible(true);
        //pack();
    }
    
}
    class Create extends JFrame {
        
        public Create(final String[] things){
            super("Create Exercise Activities");
            setSize(500,400);
            
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            setLocationRelativeTo(null);
            
            JPanel background = new JPanel();
            background.setLayout(new BoxLayout(background, BoxLayout.PAGE_AXIS));
            add(background);
            
            JLabel list = new JLabel("List of Activities");
            list.setAlignmentX(Component.CENTER_ALIGNMENT);
            background.add(list);
            //String[] things = { "Cooldown", "Elliptical", "Hiking", "Pushups", "Running", 
            //   "Sit-ups", "Warm-up", "Weights" };
            JList thingslist = new JList(things);
            JScrollPane thingsPane = new JScrollPane(thingslist);
            background.add(thingsPane);
            
            JPanel namePane = new JPanel(new FlowLayout());
            JLabel name = new JLabel("Name of Activity: ");
            namePane.add(name);
            
            final JTextField namefield = new JTextField(20);
            namefield.setEditable(true);
            namefield.setText("Elliptical");
            namePane.add(namefield);
            
            JButton update = new JButton("Update List");
            namePane.add(update);
            background.add(namePane);
            
            
            JLabel description = new JLabel("Enter Description of Activity");
            description.setAlignmentX(Component.CENTER_ALIGNMENT);
            background.add(description);
            
            
            JTextArea descriptext = new JTextArea(5, 20);
            descriptext.setEditable(true);
            descriptext.setText("30 minutes \nSetting - Aerobic \n1.5 Miles \n150 calories.");
            JScrollPane descripPane = new JScrollPane(descriptext);
            background.add(descripPane);
            
            
            JPanel bottomlist = new JPanel();
            FlowLayout bottomLayout = new FlowLayout();
            bottomlist.setLayout(bottomLayout);
            bottomLayout.setHgap(30);
        
            JButton save = new JButton("Save Changes and Exit");
            JButton exit = new JButton("Exit to Personal Trainer Main Menu");
                exit.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    new PT();
                    dispose();
                }});
                
            final JTextField statustxt = new JTextField(20);
            statustxt.setHorizontalAlignment(JTextField.CENTER);
            statustxt.setEditable(false);
            statustxt.setText("Standing by to create plan");
            
            update.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                   boolean found = arrayCompare(namefield.getText(), things);
                     if(found)
                          statustxt.setText("That activity was added to the list");
                      else
                          statustxt.setText("That activity exists and was not added to the list");
                    
            }});
            
            save.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    
            }});
            
            bottomlist.add(save);    
            bottomlist.add(exit);
            background.add(bottomlist);
            background.add(statustxt);
            
            //pack();
            setVisible(true);
            
    
        }
        private boolean arrayCompare(String n, String[] list){
            int j = 0;
            for (int i = 0; i < list.length; i++){
                if (n.equalsIgnoreCase(list[i])){
                    return false; 
                    
                }
                if (n.compareToIgnoreCase(list[i])>1)
                    j++;
                    
                }
            String[] listnew = new String[list.length+1];
            for (int i = 0; i < j; i++){
                listnew[i]=list[i];
            }
            listnew[j]=n;
            
            for (int i = j+1; i < listnew.length; i++){
                listnew[i]=list[i-1];
            }
            return true; 
        }
        
        
    }
    class ModifySelect extends JFrame{
        
        public ModifySelect(){
            super("Modify Selected Activity");
            setLayout(new FlowLayout());
            setSize(500,400);
            
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            setLocationRelativeTo(null);
            
            JPanel background = new JPanel();
            background.setLayout(new BoxLayout(background, BoxLayout.PAGE_AXIS));
            add(background);
            
            JLabel list = new JLabel("List of Activities");
            list.setAlignmentX(Component.CENTER_ALIGNMENT);
            background.add(list);
            String[] things = { "Cooldown", "Elliptical", "Hiking", "Pushups", "Running", 
                "Sit-ups", "Warm-up", "Weights" };
            JList thingslist = new JList(things);
            thingslist.setVisibleRowCount(5);
            thingslist.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
            JScrollPane thingsPane = new JScrollPane(thingslist);
            background.add(thingsPane);
            
            JLabel select = new JLabel("Select Exercise Activity and hit continue");
            select.setAlignmentX(Component.CENTER_ALIGNMENT);
            background.add(select);
            
            
            JPanel bottomlist = new JPanel();
            FlowLayout bottomLayout = new FlowLayout();
            bottomlist.setLayout(bottomLayout);
            bottomLayout.setHgap(30);
        
            JButton cont = new JButton("Continue");
            cont.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    new ModifyAct();
                    dispose();
                }});
        
            JButton exit = new JButton("Exit to Personal Trainer Main Menu");
                exit.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    new PT();
                    dispose();
                }});
            bottomlist.add(cont);    
            bottomlist.add(exit);
            background.add(bottomlist);
            
            //pack();
            setVisible(true);
        }
    }

    class ModifyAct extends JFrame{
        
        public ModifyAct(){
            super("Modify Selected Activity");
            
            setSize(500,400);
            
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            setLocationRelativeTo(null);
            String x = "Elliptical";
            
            JPanel background = new JPanel();
            background.setLayout(new BoxLayout(background, BoxLayout.PAGE_AXIS));
            add(background);
            
            
            JLabel activity = new JLabel("Activity Name: " + x);
            activity.setAlignmentX(Component.CENTER_ALIGNMENT);
            background.add(activity);
            
            
            JLabel description = new JLabel("Description");
            description.setAlignmentX(Component.CENTER_ALIGNMENT);
            background.add(description);
            
            JTextArea modactivity = new JTextArea(5, 20);
            JScrollPane modactivitypane = new JScrollPane(modactivity);
            modactivity.setEditable(true);
            background.add(modactivitypane);
            modactivity.setText("30 minutes \nSetting - Aerobic \n1.5 Miles \n150 calories.");
            background.add(Box.createVerticalGlue());
            
            JPanel bottomlist = new JPanel();
            FlowLayout bottomLayout = new FlowLayout();
            bottomlist.setLayout(bottomLayout);
            bottomLayout.setHgap(30);
        
            JButton save = new JButton("Save Changes and Exit");
            save.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    new PT();
                    dispose();
                }});
        
            JButton exit = new JButton("Exit to Personal Trainer Main Menu");
                exit.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    new PT();
                    dispose();
                }});
            bottomlist.add(save);    
            bottomlist.add(exit);
            background.add(bottomlist);
            
            //pack();
            setVisible(true);
             
        }
    }
