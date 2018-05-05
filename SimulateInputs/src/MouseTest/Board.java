package MouseTest;
import java.util.Date;
import java.util.HashMap;
import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.*;
import javax.swing.Timer;
import javax.swing.border.Border;

import java.util.ArrayList;

import java.awt.Rectangle;

import java.awt.Robot;
import MouseTest.InputLoop;
// global listener for when the program is in the background
//import org.jnativehook.GlobalScreen;
//import org.jnativehook.NativeHookException;
//import org.jnativehook.mouse.NativeMouseEvent;
//import org.jnativehook.mouse.NativeMouseInputListener;
public class Board extends JPanel implements ActionListener {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public int clicknumber = 0;
	public Robot robot;
public int score = 0;
    private Timer timer;

    private final int DELAY = 10;

    public String[] inputlist;// inputs to be executed
    public String[] waittimelist;// inputs to be executed
    
    public ArrayList<ArrayList<Integer>> inputkeypresswait; // each element is an array list of the key as an int, the press up/down, and how long to wait after
    //6 elements one for each button
    
    public ArrayList<JButton> buttonlist;
    public ArrayList<JPanel> jpanellist;
    public ArrayList<JTextField> inputkeylistdisplay;
    public ArrayList<JTextField> waittimelistdisplay;
    
    public JButton inputbutton0;// to be able to select which set of inputs
    public JButton inputbutton1;
    public JButton inputbutton2;
    public JButton inputbutton3;
    public JButton inputbutton4;
    public JButton inputbutton5;
    public JTextField numberofloops;//how many times to go through all the buttons inputs
    public int selectedbutton ;
    public JButton submit;
    public JButton runloop;
    public JButton togglelistener;
    public JButton clearallinputs;
    public JTextField newinputkey;
    public JTextField newwaittime;
    public JCheckBox listeningenabled;
    public ArrayList<InputLoop> inputloops;
public InputLoop lastlooprun;
    public boolean runningloop;
    public boolean looptoggle;
    public String lastlistenkey;
    public int loopssincelastkeypress; //to avoid it running out of control
    
    
    
    public Board() {
    	try {
			robot = new Robot();
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	lastlistenkey = "striddng";
    	inputloops = new ArrayList<InputLoop>();
        inputkeypresswait = new ArrayList<ArrayList<Integer>>();
        buttonlist = new ArrayList<JButton>();
        inputkeylistdisplay = new ArrayList<JTextField>();
        waittimelistdisplay = new ArrayList<JTextField>();
        jpanellist = new ArrayList<JPanel>();
       
    	initbuttons();
    	
        initBoard();
    }
    public void SetSelectedButton(int newselectedbutton)
    {
    	for(JPanel el: jpanellist)
    	{
    		el.setBackground(Color.GRAY);
    	}
    	
    	selectedbutton = newselectedbutton;
    	jpanellist.get(newselectedbutton).setBackground(Color.RED);
    }
    public void initbuttons()
    {
    	selectedbutton = 0;
    	submit = new JButton("submit");
    	runloop = new JButton("runloop");
    	togglelistener = new JButton("Start Listening");
    	clearallinputs = new JButton("Reset Everything");
    	togglelistener.addActionListener(new ActionListener(){ @Override public void actionPerformed(ActionEvent e){ listenerOnOffButton();} });
    	
//    	submit.addActionListener(new ActionListener(){ @Override public void actionPerformed(ActionEvent e){ SetButtonText();} });
	clearallinputs.addActionListener(new ActionListener(){ @Override public void actionPerformed(ActionEvent e){ ResetEverything();} });
//    	runloop.addActionListener(new ActionListener(){ @Override public void actionPerformed(ActionEvent e){ try {
//			VariablePressKey();
//		} catch (InterruptedException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}} });
    	for(int i = 0; i < 10; i++)
    	{
    		
    		inputkeylistdisplay.add(new JTextField(5));
    		waittimelistdisplay.add(new JTextField(5));
    	}
    	
    	
    	listeningenabled = new JCheckBox();
    	
    	newinputkey = new JTextField(5);
    
    	
    	
    	newwaittime = new JTextField(5);
    	
    	numberofloops = new JTextField(5);
    	
    	//updowncheckbox.isSelected();
    }
    public void ListenToKeys(String keypressed)
    {
    	
    	 if( lastlistenkey.equals(keypressed) != true) {
    	if(runningloop == false && looptoggle == true)
    	{
    		for ( InputLoop el: inputloops)
    		{
    			if(el.listenkey != null && el.inputslockedin == true)
    			{
    			 
    			 
    			 System.out.print(" >>>" + el.listenkey + " - Does this match - " + keypressed + "<<<");
	    			if(keypressed.equals(el.listenkey.toUpperCase()) && lastlistenkey.equals(keypressed) != true)
	    			{
	    				 System.out.print(" >>>MATCH<<<");
	    				 
	    				// loopssincelastkeypress = 0;
	    				 lastlooprun = el;
	    				 lastlistenkey = keypressed;
	    				runningloop = true;
	    				runinputloop(el);
	    				
	    			}
    			}
    		}
    		
    	}else {loopssincelastkeypress = 0;}
    	 }
    	
    }
    public JPanel selectorButtons()
    {
    
    	//ArrayList<InputLoop> inputloops = new ArrayList<InputLoop>();
    	JPanel panel = new JPanel();
    	panel.setBackground(Color.GRAY);
    	Border border = BorderFactory.createLineBorder(Color.GRAY);
    	JPanel panel5 = new JPanel();
    	panel5.setPreferredSize(new Dimension(500,350));
	    for(int i = 0; i < 10; i++)
	    {
	    	InputLoop templopp = new InputLoop(this);
	    	inputloops.add(templopp);
	    	panel5.add(templopp.getinputpanel(),BorderLayout.NORTH);
	    	
	    	
	    }
	 
		panel.add(panel5);
	    JPanel panel3 = new JPanel();
    	
    	panel.add(panel3);
    	 JPanel panel4 = new JPanel();
    	 panel4.add(clearallinputs);
    	 listeningenabled.setSize(50, 50);
    	 panel.add(togglelistener);
    	 
    	 panel.add(panel4);
    
    	return panel;
    }
//    public void confirmloop(int ip)
//    {
//    	System.out.println("board? " + inputloops.get(0).inputlistmaxsize);
//    	
//    	System.out.println("board? ");
//    	
  //  }
    
    public void SetButtonText( )
    {
    	//String tempstring = "Press ";
//    	if(updowncheckbox.isSelected() == true)
//    	{
//    		
//    		tempstring = "Release ";
//    	}
    	 
    	//buttonlist.get(selectedbutton).setText(tempstring + newinputkey.getText() + ":" + newwaittime.getText());
    	
    	newinputkey.getText().trim();
    	newwaittime.getText().trim();
    	inputkeylistdisplay.get(selectedbutton).setText(newinputkey.getText());
    	waittimelistdisplay.get(selectedbutton).setText(newwaittime.getText());
    	//buttonlist.get(selectedbutton).setSize(100,20);
     
    }
    public void ResetEverything()
    {
    	 for(InputLoop el: inputloops)
 	    {
    		 	el.reseteverything();
 	    }
    }
    private void initBoard() {
        
      //  addKeyListener(new TAdapter());
        setFocusable(true);
        setBackground(Color.GRAY);
   
       // timer = new Timer(DELAY, this);
       // timer.start();        
    }

    public void runinputloop(InputLoop iloop)
    {
	   	try {
	   		ArrayList<String> templist = new ArrayList<String>();
	   		templist = iloop.getloops();
	   		
	   		ArrayList<Integer> tempintlist = new ArrayList<>();
	   		tempintlist = iloop.getwaittimes();
	   		
	   		System.out.println("input list int 0 " + iloop.getloops().get(0));
	   		System.out.println("input list wait 0  " + iloop.getwaittimes().get(0));
	   		VariablePressKeyWithParams(templist,tempintlist);
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
    public void VariablePressKeyWithParams(ArrayList<String> inputcommands,ArrayList<Integer> inputwaittimes) throws InterruptedException
    {
    	System.out.println("!!!time since last key press!!!!" + loopssincelastkeypress);
    	int totalwaittime = 0;
    	loopssincelastkeypress++;
    	if(loopssincelastkeypress < 20)
    	{
    	for (int i = 0; i < inputcommands.size(); i++)
    	{
    		
    		if( i < inputcommands.size() - 1) {
    	
    		
    		if(inputcommands.get(i).equals(lastlistenkey) != true)
    		{
    			inputkeyswitchstatementpress(inputcommands.get(i));
    		}
	      	Thread.sleep(20);

	      	totalwaittime += inputwaittimes.get(i);
   		Thread.sleep(inputwaittimes.get(i));
    		}
    		else {
    			// runningloop = false;
    			 canloopnow(80 + totalwaittime );
    		}
    		
    	 if(i == inputcommands.size() - 1) {
    			//Thread.sleep(20);
    		
    	
    		 }
    	}
    	 
    	}
    	else {
    		System.out.println("!!!!!!!!!!!!!!!");
    		listeningenabled.setSelected(false); loopssincelastkeypress = 0; canloopnow(20);
    		}
    }
    public void canloopnow(int timetimeloop)
    {
    	try {
			Thread.sleep(timetimeloop);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	System.out.println("!donedone!");
    	
		 runningloop = false;
		 lastlooprun.listenkey = lastlistenkey;
		 //togglelistener.setText("Start Listening");
		 //looptoggle = false;
		 lastlistenkey = "done";
		
    }
    public void listenerOnOffButton()
    {
    	if (looptoggle == true)
    	{looptoggle = false;togglelistener.setText("Start Listening");}else {looptoggle = true;togglelistener.setText("Stop Listening");loopssincelastkeypress = 0;}
    	
    }
    
    
//    public void VariablePressKey() throws InterruptedException
//    {
//    	int tempwaittime = 0;
//    	for (int i = 0; i < 6; i++)
//    	{
//    		
//    		String inputkey = inputkeylistdisplay.get(i).getText();
//    		System.out.println("input key " + inputkey);
//    		inputkey.trim();
//    		if(waittimelistdisplay.get(i).getText().length() > 0)
//    		{tempwaittime = Integer.parseInt(waittimelistdisplay.get(i).getText());}
//    		 
//    		
//    		
//    		if( inputkey.length() > 0)
//    		{
//    			
//    		
//    			inputkey = inputkey.toUpperCase();
//    			inputkeyswitchstatementpress(inputkey);
//	      	Thread.sleep(20);
//	    	//inputkeyswitchstatementrelease(inputkey);
//
//    		}
//    		Thread.sleep(tempwaittime);
//    		
//    	 
//    	}
//    	String pattern = "^[0-9]";
//    	String ts = numberofloops.getText();
// 		System.out.println("the new loop text" + ts);
//		ts.matches(pattern);
// 		System.out.println("the new loop text" + ts);
//		System.out.println("the new loop text" + ts);
//    	if(ts.length() > 0)
//    	{
//    		
//    		System.out.println("the new loop text" + ts);
//    		int tempint = Integer.parseInt(ts);
//    		if(tempint > 0)
//    		{
//    			tempint--;
//    			String ts2 =   "" + tempint;
//    			numberofloops.setText(ts2);
//    			robot.keyPress(KeyEvent.VK_ENTER);
//    			robot.keyRelease(KeyEvent.VK_ENTER);
//    			
//    			VariablePressKey();
//    		}
//    	}
//    	
//    }
    
    public void inputkeyswitchstatementpress(String keytopress)
    {
    	 switch (keytopress) {
    	 case "A": robot.keyPress(KeyEvent.VK_A);
     	
     	robot.keyRelease(KeyEvent.VK_A);
    	 	break;
    	 case "B": robot.keyPress(KeyEvent.VK_B);
    
      	robot.keyRelease(KeyEvent.VK_B);
     	 	break;
    	 case "C": robot.keyPress(KeyEvent.VK_C);
      	
      	robot.keyRelease(KeyEvent.VK_C);
     	 	break;
     	 case "D": robot.keyPress(KeyEvent.VK_D);
     
       	robot.keyRelease(KeyEvent.VK_D);
      	 	break;
     	 case "E": robot.keyPress(KeyEvent.VK_E);
      	
      	robot.keyRelease(KeyEvent.VK_E);
     	 	break;
     	 case "F": robot.keyPress(KeyEvent.VK_F);
     
       	robot.keyRelease(KeyEvent.VK_F);
      	 	break;
     	 case "G": robot.keyPress(KeyEvent.VK_G);
       	
       	robot.keyRelease(KeyEvent.VK_G);
      	 	break;
      	 case "H": robot.keyPress(KeyEvent.VK_H);
      
        	robot.keyRelease(KeyEvent.VK_H);
       	 	break;
    	 	
     	 	
case "I": robot.keyPress(KeyEvent.VK_I);
     	
     	robot.keyRelease(KeyEvent.VK_I);
    	 	break;
    	 case "J": robot.keyPress(KeyEvent.VK_J);
    
      	robot.keyRelease(KeyEvent.VK_J);
     	 	break;
    	 case "K": robot.keyPress(KeyEvent.VK_K);
      	
      	robot.keyRelease(KeyEvent.VK_K);
     	 	break;
     	 case "L": robot.keyPress(KeyEvent.VK_L);
     
       	robot.keyRelease(KeyEvent.VK_L);
      	 	break;
     	 case "M": robot.keyPress(KeyEvent.VK_M);
      	
      	robot.keyRelease(KeyEvent.VK_M);
     	 	break;
     	 case "N": robot.keyPress(KeyEvent.VK_N);
     
       	robot.keyRelease(KeyEvent.VK_N);
      	 	break;
     	 case "O": robot.keyPress(KeyEvent.VK_O);
       	
       	robot.keyRelease(KeyEvent.VK_O);
      	 	break;
      	 case "P": robot.keyPress(KeyEvent.VK_P);
      
        	robot.keyRelease(KeyEvent.VK_P);
       	 	break; 	
      	 case "Q": robot.keyPress(KeyEvent.VK_Q);
       	
       	robot.keyRelease(KeyEvent.VK_Q);
      	 	break;
      	 case "R": robot.keyPress(KeyEvent.VK_R);
      
        	robot.keyRelease(KeyEvent.VK_R);
       	 	break;
      	 case "S": robot.keyPress(KeyEvent.VK_S);
       	
       	robot.keyRelease(KeyEvent.VK_S);
      	 	break;
      	 case "T": robot.keyPress(KeyEvent.VK_T);
      
        	robot.keyRelease(KeyEvent.VK_T);
       	 	break;
      	 case "U": robot.keyPress(KeyEvent.VK_U);
        	
        	robot.keyRelease(KeyEvent.VK_U);
       	 	break;
       	 case "V": robot.keyPress(KeyEvent.VK_V);
       
         	robot.keyRelease(KeyEvent.VK_V);
        	 	break; 	
      	 case "W": robot.keyPress(KeyEvent.VK_W);
        	
        	robot.keyRelease(KeyEvent.VK_W);
       	 	break;
       	 case "X": robot.keyPress(KeyEvent.VK_X);
       
         	robot.keyRelease(KeyEvent.VK_X);
        	 	break;
       	 case "Y": robot.keyPress(KeyEvent.VK_Y);
         	
         	robot.keyRelease(KeyEvent.VK_Y);
        	 	break;
        	 case "Z": robot.keyPress(KeyEvent.VK_Z);
        
          	robot.keyRelease(KeyEvent.VK_Z);
         	 	break; 
    	 	default:
    	 		break;
    	 }
    		
    }
    public void inputkeyswitchstatementrelease(String keytopress)
    {
    	 switch (keytopress) {
    	 case "A": robot.keyPress(KeyEvent.VK_A);
     	
     	robot.keyRelease(KeyEvent.VK_A);
    	 	break;
    	 case "B": robot.keyPress(KeyEvent.VK_B);
    
      	robot.keyRelease(KeyEvent.VK_B);
     	 	break;
    	 case "C": robot.keyPress(KeyEvent.VK_A);
      	
      	robot.keyRelease(KeyEvent.VK_A);
     	 	break;
     	 case "D": robot.keyPress(KeyEvent.VK_B);
     
       	robot.keyRelease(KeyEvent.VK_B);
      	 	break;
     	 case "E": robot.keyPress(KeyEvent.VK_A);
      	
      	robot.keyRelease(KeyEvent.VK_A);
     	 	break;
     	 case "F": robot.keyPress(KeyEvent.VK_B);
     
       	robot.keyRelease(KeyEvent.VK_B);
      	 	break;
     	 case "G": robot.keyPress(KeyEvent.VK_A);
       	
       	robot.keyRelease(KeyEvent.VK_A);
      	 	break;
      	 case "H": robot.keyPress(KeyEvent.VK_B);
      
        	robot.keyRelease(KeyEvent.VK_B);
       	 	break;
    	 	
     	 	
case "I": robot.keyPress(KeyEvent.VK_A);
     	
     	robot.keyRelease(KeyEvent.VK_A);
    	 	break;
    	 case "J": robot.keyPress(KeyEvent.VK_B);
    
      	robot.keyRelease(KeyEvent.VK_B);
     	 	break;
    	 case "K": robot.keyPress(KeyEvent.VK_A);
      	
      	robot.keyRelease(KeyEvent.VK_A);
     	 	break;
     	 case "L": robot.keyPress(KeyEvent.VK_B);
     
       	robot.keyRelease(KeyEvent.VK_B);
      	 	break;
     	 case "M": robot.keyPress(KeyEvent.VK_A);
      	
      	robot.keyRelease(KeyEvent.VK_A);
     	 	break;
     	 case "N": robot.keyPress(KeyEvent.VK_B);
     
       	robot.keyRelease(KeyEvent.VK_B);
      	 	break;
     	 case "O": robot.keyPress(KeyEvent.VK_A);
       	
       	robot.keyRelease(KeyEvent.VK_A);
      	 	break;
      	 case "P": robot.keyPress(KeyEvent.VK_B);
      
        	robot.keyRelease(KeyEvent.VK_B);
       	 	break; 	
      	 case "Q": robot.keyPress(KeyEvent.VK_A);
       	
       	robot.keyRelease(KeyEvent.VK_A);
      	 	break;
      	 case "R": robot.keyPress(KeyEvent.VK_B);
      
        	robot.keyRelease(KeyEvent.VK_B);
       	 	break;
      	 case "S": robot.keyPress(KeyEvent.VK_A);
       	
       	robot.keyRelease(KeyEvent.VK_A);
      	 	break;
      	 case "T": robot.keyPress(KeyEvent.VK_B);
      
        	robot.keyRelease(KeyEvent.VK_B);
       	 	break;
      	 case "U": robot.keyPress(KeyEvent.VK_A);
        	
        	robot.keyRelease(KeyEvent.VK_A);
       	 	break;
       	 case "V": robot.keyPress(KeyEvent.VK_B);
       
         	robot.keyRelease(KeyEvent.VK_B);
        	 	break; 	
      	 case "W": robot.keyPress(KeyEvent.VK_A);
        	
        	robot.keyRelease(KeyEvent.VK_A);
       	 	break;
       	 case "X": robot.keyPress(KeyEvent.VK_B);
       
         	robot.keyRelease(KeyEvent.VK_B);
        	 	break;
       	 case "Y": robot.keyPress(KeyEvent.VK_A);
         	
         	robot.keyRelease(KeyEvent.VK_A);
        	 	break;
        	 case "Z": robot.keyPress(KeyEvent.VK_B);
        
          	robot.keyRelease(KeyEvent.VK_B);
         	 	break; 
    	 	default:
    	 		break;
    	 }
    	
    }
    public void clickmouseUp()
    {
  
    	//robot.mousePress(MouseEvent.BUTTON1_DOWN_MASK);
    	
    	robot.mouseRelease(MouseEvent.BUTTON1_DOWN_MASK);
  
    	
    }
    public void clickmouseDown()
    {
  
    	robot.mousePress(MouseEvent.BUTTON1_DOWN_MASK);
    	
    	//robot.mouseRelease(MouseEvent.BUTTON1_DOWN_MASK);
  
    	
    }
    
    public void dorangomthings() throws InterruptedException
    {
    	DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    	Date date = new Date();
    	System.out.println(dateFormat.format(date));
    	//robot.keyPress(KeyEvent.VK_D);
    	//Thread.sleep(90);
    	
    	
    	//this is just a comment because I need a change here?
    	
    	int presscount = 110;
	   while(presscount > 0)
	   {
		   robot.keyPress(KeyEvent.VK_D);
		   presscount--;
		   Thread.sleep(20);
		   }
    	//Thread.sleep(5550);
    	robot.keyRelease(KeyEvent.VK_D);
//    	robot.keyPress(KeyEvent.VK_I);
//    	Thread.sleep(20);
//    	robot.keyRelease(KeyEvent.VK_I);
//    	Thread.sleep(250);
//    	robot.keyRelease(KeyEvent.VK_K);
//    	Thread.sleep(550);
//    	robot.keyPress(KeyEvent.VK_K);
//    	robot.keyPress(KeyEvent.VK_I);
//    	Thread.sleep(50);
//    	robot.keyRelease(KeyEvent.VK_RIGHT);
//    	Thread.sleep(250);
//    	robot.keyRelease(KeyEvent.VK_K);
//    	Thread.sleep(260);
    	
//    	robot.keyPress(KeyEvent.VK_K);
//    	Thread.sleep(10);
//    	robot.keyRelease(KeyEvent.VK_K);
//    	Thread.sleep(1900);
    	//robot.keyRelease(KeyEvent.VK_D);
    	System.out.println(dateFormat.format(new Date()));
    	
    	
//    	
//    	try {
//			TimeUnit.MICROSECONDS.wait(10);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
    	
    	
    }
    
    
 
    
    
    public void clickmouse()
    {
    	System.out.print(">>>>  >>>  <<<<<<");
    //	Point window = getLocationOnScreen();
    	robot.mousePress(MouseEvent.BUTTON1_DOWN_MASK);
    	
    	robot.mouseRelease(MouseEvent.BUTTON1_DOWN_MASK);
    	
//    	int x = MouseInfo.getPointerInfo().getLocation().x;
//    	int y = MouseInfo.getPointerInfo().getLocation().y;
//    	
//    	robot.mouseMove(window.x, window.y);
//    	robot.mousePress(MouseEvent.BUTTON1_DOWN_MASK);
//    	robot.mouseRelease(MouseEvent.BUTTON1_DOWN_MASK);
//    	robot.mouseMove(x, y);
    	//KeyEvent.KEY_PRESSED == true;
    	
//    	robot.keyPress(KeyEvent.VK_SHIFT);
//    	robot.keyPress(KeyEvent.VK_U);
//    	robot.keyRelease(KeyEvent.VK_SHIFT);
//    	robot.keyRelease(KeyEvent.VK_U);
    	
    	
		clicknumber --;
    	if (clicknumber > 0)
    	{
    		//clickmouse();
    	}
    		
    	
    }
   
    
    
    public  void presskey(int inputkey)
    {
    	
    	robot.keyPress(inputkey);
    	//robot.keyRelease(inputkey);
    	System.out.print(">>>> pressing >>> " + inputkey + " <<<<<<");
    	
    }
    
    
   
    
    
    public class TAdapter extends KeyAdapter {
    	
        @Override
        public void keyReleased(KeyEvent e) {
        	 int key = e.getKeyCode();
        	if (key == KeyEvent.VK_E) {
            	//robot.keyRelease(KeyEvent.VK_E);
            	System.out.print(" released EeeeEEEE " + key);
            }
        }

        @Override
        public void keyPressed(KeyEvent e) {
        	 int key = e.getKeyCode();
        	if (key == KeyEvent.VK_F) {
        		//presskey();
            	//robot.keyPress(KeyEvent.VK_E);
            	System.out.print(" - FFFFFFFFFFFFF- ");
            }
        	if(key == KeyEvent.VK_A) { 
        		System.out.print(" - AAAAAAAAAAAAAAAA - ");
        	clicknumber = 0;
        	}
        	if (key == KeyEvent.VK_E) {
            	//robot.keyRelease(KeyEvent.VK_E);
            	System.out.print(" - eeeeeeeeeeeeeeeee- ");
            	//robot.mousePress(MouseEvent.BUTTON1_DOWN_MASK);
            	//robot.mouseRelease(MouseEvent.BUTTON1_DOWN_MASK);
//            	
//            	 clicknumber = 3;
//            	
//                	clickmouse();

            	System.out.println(getLocationOnScreen());
            }
        }
        
    }

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
    
    
    
    
    
    
    
    
    
    
    
    
    

    
    
}