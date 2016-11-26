

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.text.*;
import java.util.*;
import java.time.*;
import javax.swing.event.*;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.text.BadLocationException;
import java.util.List;
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
    public static void main(String[] args) throws FileNotFoundException {
        //String name = "Jill";
        //String[] things = { "Cooldown", "Elliptical", "Hiking", "Pushups", "Running", 
        //   "Sit-ups", "Warm-up", "Weights" };//Demo equipment list
        new PT();
        //new SAP(0);
        //new ViewPlans2(name);
        //new Modify(name);
        //new MakePlan(name, things);
        //new ViewActivities(things);
        //new Create(things);
        //new ModifySelect();
        
    }
}
    class PT extends JFrame{
        public static String[] actlist;
        
    public PT() throws FileNotFoundException{
        super("Personal Trainer");
        ReadWrite rw = new ReadWrite();
        actlist = rw.ReadArray();
        JPanel background = new JPanel();
        background.setLayout(new BoxLayout(background, BoxLayout.PAGE_AXIS));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500,400);
        setLocationRelativeTo(null);
        background.setBackground(Color.yellow);
        
        
        JButton MXP =  new JButton("Make Exercise Plan and View Ratings & Preferences");
        JButton VXP = new JButton("View Old Exercise Plans");
        JButton VXA =  new JButton("View Exercise Activities");
        JButton exit = new JButton("Exit to Family Planner");
        
        MXP.setAlignmentX(Component.CENTER_ALIGNMENT);
        VXP.setAlignmentX(Component.CENTER_ALIGNMENT);
        VXA.setAlignmentX(Component.CENTER_ALIGNMENT);
        exit.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        background.add(Box.createVerticalGlue());
        background.add(MXP);
        MXP.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                new SAP(1, actlist);
                dispose();
            }});
        
        background.add(Box.createVerticalGlue());
        
        background.add(VXP);
        VXP.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                new SAP(3, actlist);
                dispose();
            }});
        
        
        background.add(Box.createVerticalGlue());
        background.add(VXA);
        VXA.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                new ViewActivities(actlist);
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
        public static String[] things;
        public static ReadWrite rw;
        
    public SAP(int option, String[] actlist){
        super("Personal Trainer");
        JPanel background = new JPanel();
        background.setLayout(new BoxLayout(background, BoxLayout.PAGE_AXIS));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500,400);
        background.setBackground(Color.yellow);
        setLocationRelativeTo(null);
        
        selected = option;
        things = actlist;
        rw = new ReadWrite();
        
        background.add(Box.createVerticalGlue());
        JPanel namesLayout = new JPanel();
        namesLayout.setLayout(new BoxLayout(namesLayout, BoxLayout.PAGE_AXIS));
        namesLayout.setBackground(Color.yellow);
        namePress namepress = new namePress();
        String[] names = rw.getNames();
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
                try {
                    new PT();
                } catch (FileNotFoundException ex) {
                    System.out.println("File not found");
                }
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
        new MakePlan(e.getActionCommand(), things);
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
        public static String[] things;
        public static ReadWrite rw;
        
        
    public MakePlan(String name, String[] actlist){
        super("Make Exercise Plan"); //title
        setSize(700,600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        things = actlist;
        rw = new ReadWrite();
        
        final SpinnerDateModel datemodel = new SpinnerDateModel(new Date(), null, null,
                                                            Calendar.DAY_OF_WEEK);
        final DateFormat dateformat = new SimpleDateFormat("dd MMM yyyy");
        
        JPanel background = new JPanel();
        background.setLayout(new BoxLayout(background, BoxLayout.PAGE_AXIS));
        add(background);
        
        //List of plans title
        JLabel plans = new JLabel("List of Plans for " + name);
        plans.setAlignmentX(Component.CENTER_ALIGNMENT);
        background.add(plans);
        
        //List of plans portion
        JPanel lopPane = new JPanel();
        lopPane.setLayout(new FlowLayout());
        JScrollPane lopScroll = new JScrollPane(lopPane);
        background.add(lopScroll);
        JLabel leftside = new JLabel("Nov 29, 2016 Tues");
        JTextArea rightside = new JTextArea(5,30);
        lopPane.add(leftside);
        lopPane.add(rightside);
        rightside.setEditable(false);
        rightside.setText("\tSample Plan\n\t12:00 PM\tWarm-up\n\t12:10 PM\tRunning\n\t12:50 PM\tCooldown");
        
        JPanel weightPane = new JPanel();
        weightPane.setLayout(new FlowLayout());
        background.add(weightPane);
        JLabel weightLabel = new JLabel("Last recorded weight:");
        weightPane.add(weightLabel);
        JTextField weightText = new JTextField(4);
        weightText.setEditable(false);
        FileReader reader = null;
                try {
                    reader = new FileReader("H://"+name+"/"+name+"WeightPref.txt");
                    weightText.read(reader, weightPane);
                } 
                catch (IOException exception) {
                    System.err.println("Load error");
                }
        weightPane.add(weightText);
        
        
        JLabel weightgoaldisplay = new JLabel("Weight goal:");
        JTextField weightgoalText = new JTextField(4);
        weightgoalText.setEditable(false);
        reader = null;
                try {
                    reader = new FileReader("H://"+name+"/"+name+"WeightGoal.txt");
                    weightgoalText.read(reader, weightPane);
                } 
                catch (IOException exception) {
                    System.err.println("Load error");
                }
        weightPane.add(weightgoaldisplay);
        weightPane.add(weightgoalText);
        
       
        JLabel weightgoal = new JLabel("");
        weightgoal.setAlignmentX(Component.CENTER_ALIGNMENT);
        background.add(weightgoal);
        System.out.println(weightText.getSelectedText());
        if (weightText.getText().compareTo(weightgoalText.getText())<=0)
            weightgoal.setText("Weight goal reached");
        else
            weightgoal.setText("Weight goal not reached");
        background.add(Box.createVerticalGlue()); 
        
        JPanel dowdisplay = new JPanel();
        FlowLayout dowLayout = new FlowLayout();
        dowdisplay.setLayout(dowLayout);
        JLabel date = new JLabel("Date: ");
        dowdisplay.add(date);
        
        JSpinner datespinner = new JSpinner(datemodel);
        JSpinner.DateEditor dateeditor = new JSpinner.DateEditor(datespinner, "dd MMM yyyy");
        JFormattedTextField datetextfield = dateeditor.getTextField();
        datetextfield.setEditable(false);
        datespinner.setEditor(dateeditor);
        dowdisplay.add(datespinner);
        
        JLabel dowtitle = new JLabel("Day of the Week: ");
        dowdisplay.add(dowtitle);
        JTextArea dowspace = new JTextArea(1,5);
        dowspace.setEditable(false);
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
        JSpinner min = new JSpinner(secmodel);
        timedisplay.add(min);
        
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
        JComboBox thingslist = new JComboBox(things);
        activitydisplay.add(thingslist);
        
        background.add(activitydisplay);
        background.add(Box.createVerticalGlue());
        
        JPanel DataEntryPane = new JPanel();
        DataEntryPane.setLayout(new FlowLayout());
        JButton keepdate = new JButton("Keep Current Date but Start Over");
        JButton enter = new JButton("Enter Data into Exercise Plan Above");
        JButton restart = new JButton("Clear All and Start Over");
        
        DataEntryPane.add(keepdate);
        DataEntryPane.add(enter);
        DataEntryPane.add(restart);
        background.add(DataEntryPane);
        background.add(Box.createVerticalGlue());
        
        JPanel bottomPane = new JPanel();
        bottomPane.setLayout(new FlowLayout());
        JPanel bottomleftPane = new JPanel();
        bottomleftPane.setLayout(new BoxLayout(bottomleftPane, BoxLayout.Y_AXIS));
        JPanel bottommiddlePane = new JPanel();
        bottommiddlePane.setLayout(new BoxLayout(bottommiddlePane, BoxLayout.Y_AXIS));
        JPanel bottomrightPane = new JPanel();
        bottomrightPane.setLayout(new BoxLayout(bottomrightPane, BoxLayout.Y_AXIS));
        
        JLabel actDescripLabel = new JLabel("Activity Description");
        actDescripLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        JTextArea actDescrip = new JTextArea(5, 20);
        JScrollPane actDescriPane = new JScrollPane(actDescrip);
        actDescrip.setEditable(false);
        bottomleftPane.add(actDescripLabel);
        bottomleftPane.add(actDescriPane);
        
        JLabel prefDescripLabel = new JLabel("Personal Preferences");
        prefDescripLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        JTextArea prefDescrip = new JTextArea(5, 20);
        JScrollPane prefDescriPane = new JScrollPane(prefDescrip);
        prefDescrip.setEditable(false);
        reader = null;
                try {
                    reader = new FileReader("H://"+name+"/"+name+"ExercisePref.txt");
                    prefDescrip.read(reader, bottommiddlePane);
                } 
                catch (IOException exception) {
                    System.err.println("Load error");
                }
        bottommiddlePane.add(prefDescripLabel);
        bottommiddlePane.add(prefDescriPane);
        
        JLabel ratDescripLabel = new JLabel("Personal Activity Ratings");
        ratDescripLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        JTextArea ratDescrip = new JTextArea(5, 20);
        JScrollPane ratDescriPane = new JScrollPane(ratDescrip);
        ratDescrip.setEditable(false);
        reader = null;
                try {
                    reader = new FileReader("H://"+name+"/"+name+"ExerciseRatings.txt");
                    ratDescrip.read(reader, bottomrightPane);
                } 
                catch (IOException exception) {
                    System.err.println("Load error");
                }
        bottomrightPane.add(ratDescripLabel);
        bottomrightPane.add(ratDescriPane);
        
        bottomPane.add(bottomleftPane);
        bottomPane.add(bottommiddlePane);
        bottomPane.add(bottomrightPane);
        background.add(bottomPane);
        background.add(Box.createVerticalGlue());
        
        keepdate.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                rightside.setText("");
            }});
        
        enter.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                try { 
                    int start = rightside.getLineStartOffset(rightside.getLineCount()- 1);
                    int end = rightside.getLineEndOffset(rightside.getLineCount()- 1);
                    System.out.println(rightside.getText(start, end-start));
                } catch (BadLocationException ex) {
                    System.out.println("Previous line doesn't exist.");
                }
                String emptyitem = "";
                String emptyhour = "";
                String emptyampm = "";
                String minconvert = "";
                if (rightside.getText().contains("Sample"))
                    rightside.setText("");
                if (timelist.getSelectedItem()==null)
                    emptyhour = "1";
                else 
                    emptyhour = (String)(timelist.getSelectedItem());
                if ((int)min.getValue() < 10)
                    minconvert = "0"+min.getValue().toString();
                else
                    minconvert = min.getValue().toString();
                if (ampmlist.getSelectedItem()==null)
                    emptyampm = "AM";
                else
                    emptyampm = (String)(ampmlist.getSelectedItem());
                if (thingslist.getSelectedItem() == null)
                    emptyitem = things[0];
                else
                    emptyitem = (String)thingslist.getSelectedItem();
                
                leftside.setText(datespinner.getValue().toString().substring(4,10)+","+
                        (datespinner.getValue().toString().substring(24,28))+"  "+
                        (datespinner.getValue().toString().substring(0,3)));
                
                rightside.append("\t"+emptyhour+":"+minconvert+" "+emptyampm);
                rightside.append("\t"+emptyitem+"\n");
                dowdisplay.remove(date);
                dowdisplay.remove(datespinner);
                dowdisplay.remove(dowtitle);
                dowdisplay.remove(dowspace);
                
            }});
        
        //Save button
        JPanel bottomdisplay = new JPanel();
        FlowLayout bottomLayout = new FlowLayout();
        bottomdisplay.setLayout(bottomLayout);
        bottomLayout.setHgap(10);
        
        JButton save = new JButton("Save and Exit");
        save.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                try {
                        PrintWriter textwriter = null;
                        String yearstring = datespinner.getValue().toString().substring(24,28);
                        String monthstring = datespinner.getValue().toString().substring(4,7);
                        File year = new File("H://"+name+"/XP/"+yearstring);
                        if (!year.exists())
                            year.mkdirs();
                        File month = new File("H://"+name+"/XP/"+yearstring+"/"+
                                rw.monthNum(monthstring)+"/Ratings");
                        if (!month.exists())
                            month.mkdirs();
                        String filename = "H://"+name+"/XP/"+yearstring+"/"+
                                rw.monthNum(monthstring)+"/"
                                +datespinner.getValue().toString().substring(8,10)+".txt";
                        //System.out.println(filename);
                        textwriter= new PrintWriter(filename, "UTF-8");
                        rightside.write(textwriter);
                        textwriter.close();
                        filename = "H://"+name+"/XP/"+yearstring+"/"+
                                rw.monthNum(monthstring)+"/Ratings/"
                                +datespinner.getValue().toString().substring(8,10)+".txt";
                        textwriter= new PrintWriter(filename, "UTF-8");
                        textwriter.write("-");
                        textwriter.close();
                        new PT();
                    } catch (FileNotFoundException ex) {
                        System.out.println("File not found");
                    } catch (IOException ex) {
                        System.out.println("Save button for modify");
                    }
                    
                dispose();
            }});
        
        //Exit button
        JButton exit = new JButton("Exit to Personal Trainer Main Menu");
        exit.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                try {
                    new PT();
                } catch (FileNotFoundException ex) {
                    System.out.println("File not found");
                }
                dispose();
            }});
        
        restart.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                new MakePlan(name, things);
                dispose(); 
            }});
        
        thingslist.addActionListener (new ActionListener () {
            public void actionPerformed(ActionEvent e) {
                actDescrip.setText("");
                FileReader reader = null;
                try {
                    reader = new FileReader("H://Activities/"+thingslist.getSelectedItem()+".txt");
                    actDescrip.read(reader, background);
                } 
                catch (IOException exception) {
                    System.err.println("Load error");
                }
                }
            });
        bottomdisplay.add(save);
        bottomdisplay.add(exit);
        background.add(bottomdisplay);
        //background.add(statustxt);
        
        //pack();
        setVisible(true);
      
    }
    
}

 class ViewPlans extends JFrame{
        public static ReadWrite rw;
        public static String[] montharray;
        public static String[] dayarray;
        public static String monthFile;
        
    public ViewPlans(String name){
        super("View Exercise Plans");
        setSize(500,400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        rw = new ReadWrite();
        rw.createNewFiles(name);
                
        JPanel background = new JPanel();
        background.setLayout(new BoxLayout(background, BoxLayout.Y_AXIS));
        add(background);
        
        JPanel toptitle = new JPanel();
        toptitle.setLayout(new FlowLayout());
        JLabel op = new JLabel("Old Plans for");
        op.setAlignmentX(Component.CENTER_ALIGNMENT);
        toptitle.add(op);
        JLabel titlename = new JLabel(name);
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
        background.add(lopScroll);
        
        JLabel leftside = new JLabel("10/25/16 Tue");
        JTextArea rightside = new JTextArea(5,30);
        lopPane.add(leftside);
        lopPane.add(rightside);
        rightside.setEditable(false);
        rightside.setText("\tSample Plan\n\t12:00 PM\tWarm-up\n\t12:10 PM\tRunning\n\t12:50 PM\tCooldown");
        
        
        int x = 4;
        JLabel ratingValue = new JLabel("Rating is "+Integer.toString(x));
        ratingValue.setAlignmentX(Component.CENTER_ALIGNMENT);
        background.add(ratingValue);
        
        JLabel yeartitle = new JLabel("Choose an available year");
        JPanel yeardisplay = new JPanel();
        FlowLayout yearLayout = new FlowLayout();
        yeardisplay.setLayout(yearLayout);
        yearLayout.setHgap(10);
        yeardisplay.add(yeartitle);
        String[] yeararray = rw.getYear(name); //Gets list of years in directory
        JComboBox yearlist = new JComboBox(yeararray);
        yeardisplay.add(yearlist);
        background.add(yeardisplay);
        yearlist.addActionListener(yearlist);
        
        JLabel monthtitle = new JLabel("Choose an available month");
        JPanel monthdisplay = new JPanel();
        FlowLayout monthLayout = new FlowLayout();
        monthdisplay.setLayout(monthLayout);
        monthLayout.setHgap(10);
        monthdisplay.add(monthtitle);
        montharray = rw.getMonths(name, yearlist.getSelectedItem().toString());//Gets list of months
        JComboBox monthlist = new JComboBox(montharray);
        
        monthdisplay.add(monthlist);
        background.add(monthdisplay);
        
        JLabel daytitle = new JLabel("Choose an available day");
        JPanel daydisplay = new JPanel();
        FlowLayout dayLayout = new FlowLayout();
        daydisplay.setLayout(yearLayout);
        dayLayout.setHgap(10);
        daydisplay.add(daytitle);
        
        monthFile = rw.monthNum(monthlist.getSelectedItem().toString());
        dayarray = rw.getDays(name, yearlist.getSelectedItem().toString(), 
               monthFile);//Gets list of days in months file
        
        JComboBox daylist = new JComboBox(dayarray);
        daydisplay.add(daylist);
        background.add(daydisplay);
        
        
        JButton modify = new JButton("Modify This Plan");
        JButton save = new JButton("Save This Plan and Edit Another");
        JButton exit = new JButton("Exit to Personal Trainer Main Menu");
        
        JPanel MVP = new JPanel(new FlowLayout());
        background.add(modify);
        MVP.add(save);
        MVP.add(exit);
        background.add(MVP);
        JTextArea status = new JTextArea(1,20);
        status.setAlignmentX(Component.CENTER_ALIGNMENT);
        //background.add(status);
        background.add(Box.createVerticalGlue());
        
        yearlist.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                
                monthlist.setModel(new DefaultComboBoxModel(rw.getMonths(name, 
                        yearlist.getSelectedItem().toString())));
                monthFile = rw.monthNum(monthlist.getSelectedItem().toString());
                
                daylist.setModel(new DefaultComboBoxModel(rw.getDays(name, 
                        yearlist.getSelectedItem().toString(),monthFile)));
              }
        });
        monthlist.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                monthFile = rw.monthNum(monthlist.getSelectedItem().toString());
                status.setText(monthFile);
                daylist.setModel(new DefaultComboBoxModel(rw.getDays(name, 
                        yearlist.getSelectedItem().toString(),monthFile)));
              }
        });
        daylist.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                rightside.setText("");
                leftside.setText(monthlist.getSelectedItem().toString()+" "
                        +daylist.getSelectedItem().toString()+", "+
                        yearlist.getSelectedItem().toString());
                
                try{
                String text = rw.getXPlan(name, yearlist.getSelectedItem().toString(),
                        monthlist.getSelectedItem().toString(), daylist.getSelectedItem().toString());
                rightside.setText(text);
                text = rw.getXRatings(name, yearlist.getSelectedItem().toString(),
                        monthlist.getSelectedItem().toString(), daylist.getSelectedItem().toString());
                ratingValue.setText("Rating is "+text);
                }
                catch (IOException ex) {
                    System.out.println("File does not Exist");
                }
        }});
        
        
        
        
        modify.setAlignmentX(Component.CENTER_ALIGNMENT);
        modify.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                rightside.setEditable(true);
                }});
        
        save.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
            try {
                PrintWriter textwriter = null;
                String dayFile;
                if (daylist.getSelectedItem() == null)
                    dayFile = "00";
                else 
                    dayFile = daylist.getSelectedItem().toString();
                
                String filename = "H://"+name+"/XP/"+yearlist.getSelectedItem().toString()+
                    "/"+monthFile+"/"+dayFile+".txt";
                    
                System.out.println(filename);
                textwriter= new PrintWriter(filename, "UTF-8");
                rightside.write(textwriter);
                textwriter.close();
                } catch (FileNotFoundException ex) {
                  System.out.println("File not found");
                } catch (IOException ex) {
                  System.out.println("Save button for modify");
                }
                new ViewPlans(name);
                dispose();
            }});
        
        exit.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                try {
                    new PT();
                } catch (FileNotFoundException ex) {
                    System.out.println("File not found");
                }
                dispose();
            }});
        
        setVisible(true);
        
    }
        
}
 class Modify extends JFrame{
        public static String name;
        
        public Modify (String name){
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
            JLabel titlename = new JLabel(name);
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
            try {
                new PT();
            } catch (FileNotFoundException ex) {
                System.out.println("File not found");
            }
            dispose();
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
 
 class ViewActivities extends JFrame{
        public static String[] things;
    public ViewActivities(String[] actlist){
        super("View Exercise Activities");
        setSize(500,400);
        
        things = actlist;
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
                new ModifySelect(things);
                dispose();
            }});
        background.add(modify);
        
        background.add(Box.createVerticalGlue());
        JButton exit = new JButton("Exit to Personal Trainer Main Menu");
        exit.setAlignmentX(Component.CENTER_ALIGNMENT);
        exit.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                try { 
                    new PT();
                } catch (FileNotFoundException ex) {
                    System.out.println("File not found");
                }
               dispose();
            }});
        background.add(exit);
        setVisible(true);
        //pack();
    }
    
}
 class Create extends JFrame {
            public static String[] things;
            public static ReadWrite rw;
        public Create(String[] actlist){
            super("Create Exercise Activities");
            things = actlist;
            rw = new ReadWrite();
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
            
            JPanel namePane = new JPanel(new FlowLayout());
            JLabel name = new JLabel("Name of Activity: ");
            namePane.add(name);
            
            JTextField namefield = new JTextField(20);
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
        
            JButton save = new JButton("Save List and Exit");
            JButton exit = new JButton("Exit to Personal Trainer Main Menu");
                exit.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    try {
                        new PT();
                    } catch (FileNotFoundException ex) {
                        System.out.println("File not found");
                    }
                    dispose();
                }});
                
            JTextField statustxt = new JTextField(20);
            statustxt.setHorizontalAlignment(JTextField.CENTER);
            statustxt.setEditable(false);
            statustxt.setText("Standing by to create plan");
            
            update.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    String[] newlist;
                    newlist = rw.arrayCompare(namefield.getText(), things);
                    try {
                        FileWriter textwriter = null;
                        textwriter= new FileWriter("H://Activities/"+namefield.getText()+".txt");
                        descriptext.write(textwriter);
                    } catch (IOException ex) {
                        System.out.println("Save button for modify");
                    }
                    new Create(newlist);
                    dispose();
                    }});
            
            save.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                   rw.WriteArray(things);
                    try {
                        new PT();
                        dispose();
                    } catch (FileNotFoundException ex) {
                        System.out.println("File could not be saved.");
                    }
                }});
            
            bottomlist.add(save);    
            bottomlist.add(exit);
            background.add(bottomlist);
            background.add(statustxt);
            
            //pack();
            setVisible(true);
            
    
        }
        
    }
 class ModifySelect extends JFrame{
            public static String[] things;
            
        public ModifySelect(String[] actlist){
            super("Modify Selected Activity");
            setLayout(new FlowLayout());
            setSize(700,600);
            
            things = actlist;
            
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            setLocationRelativeTo(null);
            
            JPanel background = new JPanel();
            background.setLayout(new BoxLayout(background, BoxLayout.PAGE_AXIS));
            add(background);
            
            JLabel list = new JLabel("List of Activities");
            list.setAlignmentX(Component.CENTER_ALIGNMENT);
            background.add(list);
            
            JList thingslist = new JList(things);
            thingslist.setVisibleRowCount(5);
            thingslist.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
            JScrollPane thingsPane = new JScrollPane(thingslist);
            background.add(thingsPane);
            
            JLabel select = new JLabel("Select Exercise Activity and hit Modify Selected Item");
            select.setAlignmentX(Component.CENTER_ALIGNMENT);
            background.add(select);
            background.add(Box.createVerticalGlue());
            
            JLabel actDescripLabel = new JLabel("Activity Description");
            actDescripLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            background.add(actDescripLabel);
            JTextArea actDescrip = new JTextArea(5, 20);
            JScrollPane actDescriPane = new JScrollPane(actDescrip);
            actDescrip.setEditable(false);
            background.add(actDescriPane);
            
            
            thingslist.addListSelectionListener(new ListSelectionListener(){
                public void valueChanged(ListSelectionEvent e){
                    try {
                    actDescrip.setText("");
                    FileReader reader = null;
                    reader = new FileReader("H://Activities/"+(String)thingslist.getSelectedValue()+".txt");
                    actDescrip.read(reader, actDescriPane);
                    } 
                    catch (IOException exception) {
                    System.err.println("Load error");
                }}});
            
            JPanel bottomlist = new JPanel();
            FlowLayout bottomLayout = new FlowLayout();
            bottomlist.setLayout(bottomLayout);
            bottomLayout.setHgap(30);
            
            JButton cont = new JButton("Modify Selected Item");
            cont.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    if (thingslist.getSelectedValue()==null){
                        new ModifySelect(things);
                        dispose();
                    }
                    else{
                        actDescrip.setEditable(true);
                    }
            }});
            JButton save = new JButton("Save Changes and Exit");
            save.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    try {
                        FileWriter textwriter = null;
                        textwriter= new FileWriter("H://Activities/"+(String)thingslist.getSelectedValue()+".txt");
                        actDescrip.write(textwriter);
                        new PT();
                    } catch (FileNotFoundException ex) {
                        System.out.println("File not found");
                    } catch (IOException ex) {
                        System.out.println("Save button for modify");
                    }
                    dispose();
            }});
            
            JButton exit = new JButton("Exit to Personal Trainer Main Menu");
                exit.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    try {
                        new PT();
                    } catch (FileNotFoundException ex) {
                        System.out.println("File not found");
                    }
                    dispose();
                }});
            
            bottomlist.add(cont);
            bottomlist.add(save);
            bottomlist.add(exit);
            background.add(bottomlist);
            
            //pack();
            setVisible(true);
        }
    }

 class ViewPlans2 extends JFrame{
        public static ReadWrite rw;
        public static String[] montharray;
        public static String[] dayarray;
        public static String monthFile;
        
    public ViewPlans2(String name){
        super("Change Exercise Plan Ratings");
        setSize(500,400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        rw = new ReadWrite();
        rw.createNewFiles(name);
                
        JPanel background = new JPanel();
        background.setLayout(new BoxLayout(background, BoxLayout.Y_AXIS));
        add(background);
        
        JPanel toptitle = new JPanel();
        toptitle.setLayout(new FlowLayout());
        JLabel op = new JLabel("Old Plans for");
        op.setAlignmentX(Component.CENTER_ALIGNMENT);
        toptitle.add(op);
        JLabel titlename = new JLabel(name);
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
        background.add(lopScroll);
        
        JLabel leftside = new JLabel("10/25/16 Tue");
        JTextArea rightside = new JTextArea(5,30);
        lopPane.add(leftside);
        lopPane.add(rightside);
        rightside.setEditable(false);
        rightside.setText("\tSample Plan\n\t12:00 PM\tWarm-up\n\t12:10 PM\tRunning\n\t12:50 PM\tCooldown");
        
        
        JPanel ratingLayout = new JPanel();
        ratingLayout.setLayout(new FlowLayout());
        JLabel ratingValue = new JLabel("Rating is (1-5):");
        ratingValue.setAlignmentX(Component.CENTER_ALIGNMENT);
        JTextField ratingText = new JTextField(3);
        ratingText.setText("4");
        ratingLayout.add(ratingValue);
        ratingLayout.add(ratingText);
        background.add(ratingLayout);
        
        JLabel yeartitle = new JLabel("Choose an available year");
        JPanel yeardisplay = new JPanel();
        FlowLayout yearLayout = new FlowLayout();
        yeardisplay.setLayout(yearLayout);
        yearLayout.setHgap(10);
        yeardisplay.add(yeartitle);
        String[] yeararray = rw.getYear(name); //Gets list of years in directory
        JComboBox yearlist = new JComboBox(yeararray);
        yeardisplay.add(yearlist);
        background.add(yeardisplay);
        yearlist.addActionListener(yearlist);
        
        JLabel monthtitle = new JLabel("Choose an available month");
        JPanel monthdisplay = new JPanel();
        FlowLayout monthLayout = new FlowLayout();
        monthdisplay.setLayout(monthLayout);
        monthLayout.setHgap(10);
        monthdisplay.add(monthtitle);
        montharray = rw.getMonths(name, yearlist.getSelectedItem().toString());//Gets list of months
        JComboBox monthlist = new JComboBox(montharray);
        
        monthdisplay.add(monthlist);
        background.add(monthdisplay);
        
        JLabel daytitle = new JLabel("Choose an available day");
        JPanel daydisplay = new JPanel();
        FlowLayout dayLayout = new FlowLayout();
        daydisplay.setLayout(yearLayout);
        dayLayout.setHgap(10);
        daydisplay.add(daytitle);
        
        monthFile = rw.monthNum(monthlist.getSelectedItem().toString());
        dayarray = rw.getDays(name, yearlist.getSelectedItem().toString(), 
               monthFile);//Gets list of days in months file
        
        JComboBox daylist = new JComboBox(dayarray);
        daydisplay.add(daylist);
        background.add(daydisplay);
        
        JButton save = new JButton("Save This Rating and Edit Another");
        JButton exit = new JButton("Exit to Personal Trainer Main Menu");
        
        JPanel MVP = new JPanel(new FlowLayout());
        MVP.add(save);
        MVP.add(exit);
        background.add(MVP);
        JTextArea status = new JTextArea(1,20);
        status.setAlignmentX(Component.CENTER_ALIGNMENT);
        //background.add(status);
        background.add(Box.createVerticalGlue());
        
        yearlist.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                
                monthlist.setModel(new DefaultComboBoxModel(rw.getMonths(name, 
                        yearlist.getSelectedItem().toString())));
                monthFile = rw.monthNum(monthlist.getSelectedItem().toString());
                
                daylist.setModel(new DefaultComboBoxModel(rw.getDays(name, 
                        yearlist.getSelectedItem().toString(),monthFile)));
              }
        });
        monthlist.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                monthFile = rw.monthNum(monthlist.getSelectedItem().toString());
                status.setText(monthFile);
                daylist.setModel(new DefaultComboBoxModel(rw.getDays(name, 
                        yearlist.getSelectedItem().toString(),monthFile)));
              }
        });
        daylist.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                rightside.setText("");
                leftside.setText(monthlist.getSelectedItem().toString()+" "
                        +daylist.getSelectedItem().toString()+", "+
                        yearlist.getSelectedItem().toString());
                
                try{
                String text = rw.getXPlan(name, yearlist.getSelectedItem().toString(),
                        monthlist.getSelectedItem().toString(), daylist.getSelectedItem().toString());
                rightside.setText(text);
                text = rw.getXRatings(name, yearlist.getSelectedItem().toString(),
                        monthlist.getSelectedItem().toString(), daylist.getSelectedItem().toString());
                ratingText.setText(text);
                }
                catch (IOException ex) {
                    System.out.println("File does not Exist");
                }
        }});
        
        save.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
            try {
                PrintWriter textwriter = null;
                String dayFile;
                if (daylist.getSelectedItem() == null)
                    dayFile = "00";
                else 
                    dayFile = daylist.getSelectedItem().toString();
                
                String filename = "H://"+name+"/XP/"+yearlist.getSelectedItem().toString()+
                    "/"+monthFile+"/Ratings/"+dayFile+".txt";
                
                textwriter= new PrintWriter(filename, "UTF-8");
                ratingText.write(textwriter);
                textwriter.close();
                } catch (FileNotFoundException ex) {
                  System.out.println("File not found");
                } catch (IOException ex) {
                  System.out.println("Save button for modify");
                }
                new ViewPlans2(name);
                dispose();
            }});
        
        exit.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                dispose();
            }});
        
        setVisible(true);
        
    }
        
}
    class ReadWrite {
        static String[] act;
        static String[] names;
        static List<String> listOfNames;
    public String[] ReadArray() {
        String str = ""; //Read each file from input
        Scanner fin;
        try {
            File actdir = new File("H://Activities");
            if (!actdir.exists()){
                actdir.mkdir();
                String[] tempacts = { "Cooldown", "Elliptical", "Hiking", "Pushups", "Running", 
                                    "Sit-ups", "Warm-up", "Weights" };
                WriteArray(tempacts);
                }
            fin = new Scanner(new File("H://activities/activities.txt"));
            int n = fin.nextInt(); //Reads 1st character which represents the
        //number of string files to read
        fin.nextLine();
        
        act = new String[n];
        for (int i = 0; i<n; i++){
          act[i]=fin.nextLine(); //Read a string input from file  
          
        }
        
        } catch (FileNotFoundException ex) {
           System.out.println("File does not Exist"); 
        }
          catch (NullPointerException ex){
            System.out.println("Can't create file.");
        }
        return act;
     }
    
    public void WriteArray(String[] array){
        try{
            PrintWriter writer = null;
            File actdir = new File("H://activities");
            if (!actdir.exists()){
                actdir.mkdir();
            }
            writer = new PrintWriter("H://activities/activities.txt", "UTF-8");
            writer.println(array.length);
            for (int i = 0; i < array.length; i++){
                writer.println(array[i]);
            }
            writer.close();
            } catch (Exception e) {
                System.out.println("Activities could not be saved");
            }
        }
    
    public void createNewFiles(String name){
        try {
            
            File actMnthdir = new File("H://"+name+"/XP/2000/1/Ratings");
            if (!actMnthdir.exists()){
                actMnthdir.mkdirs();
            PrintWriter textwriter = new PrintWriter("H://"+name+"/XP/2000/1/01.txt");
            textwriter.print("\tSample Plan\n" +"	12:00 PM	Warm-up\n" +
                "	12:10 PM	Running\n"+"	12:50 PM	Cooldown");
            textwriter.close();
            textwriter = new PrintWriter("H://"+name+"/XP/2000/1/Ratings/01.txt");
            textwriter.print("5");
            textwriter.close();
            }
        }
        
        catch (NullPointerException ex){
            System.out.println("Can't create file.");
        }
        catch (FileNotFoundException ex) {
           System.out.println("File does not Exist"); 
        }
    }
    public String[] arrayCompare(String n, String[] list){
            int j = 0;
            for (int i = 0; i < list.length; i++){
                if (n.equalsIgnoreCase(list[i])){
                    return list; 
                }
                if (n.compareToIgnoreCase(list[i]) > 0)
                    j++;
                else if (n.compareToIgnoreCase(list[i]) < 0)
                    i = list.length;
                
                }
            String[] listnew = new String[list.length+1];
            for (int i = 0; i < j; i++){
                listnew[i]=list[i];
            }
            listnew[j]=n;
            
            for (int i = j+1; i < listnew.length; i++){
                listnew[i]=list[i-1];
            }
            return listnew; 
        }
    
    public String[] getNames(){
        List<String> listOfNames = new ArrayList<String>();
        File[] files = new File("H://").listFiles();
        for (File namenum : files) {
            if (namenum.isDirectory()) {
            File nameverify = new File (namenum+"/"+namenum.getName()+"ExercisePref.txt");
            if (nameverify.exists())
                listOfNames.add(namenum.getName());
            
        }
    }
        String[] names = new String[listOfNames.size()];
        for (int i = 0; i < listOfNames.size(); i++){
            listOfNames.toArray(names);
        }
        return names;
    }
    
    public String[] getYear(String name){
        try{
        listOfNames = new ArrayList<String>();
        File[] files = new File("H://"+name+"/XP/").listFiles();
        for (File namenum : files) {
            if (namenum.isDirectory()) {
            listOfNames.add(namenum.getName());
            
        }
    }
        }
        catch (NullPointerException ex){
            createNewFiles(name);
        }
        names = new String[listOfNames.size()];
        for (int i = 0; i < listOfNames.size(); i++){
            listOfNames.toArray(names);
        }
        return arraySort(names);
    }
    public String[] getMonths(String name, String year){
        List<String> listOfNames = new ArrayList<String>();
        try {
        File[] files = new File("H://"+name+"/XP/"+year).listFiles();
        for (File namenum : files) {
            if (namenum.isDirectory()) {
            listOfNames.add(namenum.getName());
        }
    }
        names = new String[listOfNames.size()];
        
        for (int i = 0; i < listOfNames.size(); i++){
            listOfNames.toArray(names);
        }
        }
        catch (NullPointerException ex){
            createNewFiles(name);
        }
        names = arraySort(names);
        for (int i = 0; i < names.length; i++){
           
           names[i]= monthName((names[i]));
        }
        return names;
    }
    
    public String[] getDays(String name, String year, String month){
        List<String> listOfNames = new ArrayList<String>();
        try{
        File[] files = new File("H://"+name+"/XP/"+year+"/"+month).listFiles();
        for (File namenum : files) {
            if (namenum.isFile()) {
                listOfNames.add(namenum.getName().substring(0, 2));
            
                }
            }
        }
        catch (NullPointerException ex){
            createNewFiles(name);
        }
        String[] names = new String[listOfNames.size()];
        for (int i = 0; i < listOfNames.size(); i++){
            listOfNames.toArray(names);
        }
        return arraySort(names);
    }
    
    public String getXPlan(String name, String year, String month, String day) throws FileNotFoundException{
        String Xplan = "";
        month = monthNum(month);
        try {
        FileReader Xplanfile = new FileReader ("H://"+name+"/XP/"+year+"/"+month+"/"+day+".txt");
        Xplan = new Scanner(Xplanfile).useDelimiter("\\Z").next();
        }
        catch (IOException ex) {
        System.out.println("File does not Exist");
        }
        return Xplan;
    }
    
    
    public int monthDetermine(String month){
        if (month.equalsIgnoreCase("Jan"))
                return 1;
        else if (month.equalsIgnoreCase("Feb"))
                return 2;
        else if (month.equalsIgnoreCase("Mar"))
                return 3;
        else if (month.equalsIgnoreCase("Apr"))
                return 4;
        else if (month.equalsIgnoreCase("May"))
                return 5;
        else if (month.equalsIgnoreCase("Jun"))
                return 6;
        else if (month.equalsIgnoreCase("Jul"))
                return 7;
        else if (month.equalsIgnoreCase("Aug"))
                return 8;
        else if (month.equalsIgnoreCase("Sep"))
                return 9;
        else if (month.equalsIgnoreCase("Oct"))
                return 10;
        else if (month.equalsIgnoreCase("Nov"))
                return 11;
        else if (month.equalsIgnoreCase("Dec"))
                return 12;
        return 1;
    }
    public String monthnumDetermine(int month){
        if (month==1)
            return "Jan";
        if (month==2)
            return "Feb";
        if (month==3)
            return "Mar";
        if (month==4)
            return "Apr";              
        if (month==5)
            return "May";
        if (month==6)
            return "Jun";
        if (month==7)
            return "Jul";
        if (month==8)
            return "Aug";  
        if (month==9)
            return "Sep";
        if (month==10)
            return "Oct";
        if (month==11)
            return "Nov";
        if (month==12)
            return "Dec";  
        return "Jan";
    }


    private String[] arraySort (String[] ip){
        int j=ip.length-1;
        String large = "";
        while (j>0){
        for (int i = 0; i < j; i++){
            if (Integer.parseInt(ip[i]) > Integer.parseInt(ip[i+1])){
                large = ip[i];
                ip[i] = ip[i+1];
                ip[i+1] = large;
               }
        }
        j--;
        }
        return ip;
    }

    public String monthNum(String month){
        if (month.equalsIgnoreCase("Jan"))
                return "1";
        else if (month.equalsIgnoreCase("Feb"))
                return "2";
        else if (month.equalsIgnoreCase("Mar"))
                return "3";
        else if (month.equalsIgnoreCase("Apr"))
                return "4";
        else if (month.equalsIgnoreCase("May"))
                return "5";
        else if (month.equalsIgnoreCase("Jun"))
                return "6";
        else if (month.equalsIgnoreCase("Jul"))
                return "7";
        else if (month.equalsIgnoreCase("Aug"))
                return "8";
        else if (month.equalsIgnoreCase("Sep"))
                return "9";
        else if (month.equalsIgnoreCase("Oct"))
                return "10";
        else if (month.equalsIgnoreCase("Nov"))
                return "11";
        else if (month.equalsIgnoreCase("Dec"))
                return "12";
        return "1";
    }
    
    public String monthName(String month){
        if (month.equalsIgnoreCase("1"))
                return "Jan";
        else if (month.equalsIgnoreCase("2"))
                return "Feb";
        else if (month.equalsIgnoreCase("3"))
                return "Mar";
        else if (month.equalsIgnoreCase("4"))
                return "Apr";
        else if (month.equalsIgnoreCase("5"))
                return "May";
        else if (month.equalsIgnoreCase("6"))
                return "Jun";
        else if (month.equalsIgnoreCase("7"))
                return "Jul";
        else if (month.equalsIgnoreCase("8"))
                return "Aug";
        else if (month.equalsIgnoreCase("9"))
                return "Sep";
        else if (month.equalsIgnoreCase("10"))
                return "Oct";
        else if (month.equalsIgnoreCase("11"))
                return "Nov";
        else if (month.equalsIgnoreCase("12"))
                return "Dec";
        return "Jan";
    }
    
    public String getXRatings (String name, String year, String month, String day) throws FileNotFoundException {
        String Xplan = "";
        month = monthNum(month);
        try {
        FileReader Xplanfile = new FileReader ("H://"+name+"/XP/"+year+"/"+month+"/Ratings/"+day+".txt");
        Xplan = new Scanner(Xplanfile).useDelimiter("\\Z").next();
        }
        catch (IOException ex) {
        System.out.println("File does not Exist");
        }
        return Xplan;
        
    }

}


   