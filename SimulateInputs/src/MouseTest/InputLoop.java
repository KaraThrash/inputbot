package MouseTest;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.Border;

import org.jnativehook.keyboard.NativeKeyEvent;
public class InputLoop {
	public String listenkey;
//public String[] inputs;
//public Integer[] inputwaittimes;
public ArrayList<String> inputs;
public ArrayList<Integer> inputwaittimes;
public ArrayList<JTextField> inputkeylistdisplay;
public ArrayList<JTextField> waittimelistdisplay;

public JTextField listenkeyfield;
public JPanel mypanel;
public JButton confirmInputSetButton; //to lock in this input loop
public int inputlistmaxsize = 3;
public Board board;
public boolean inputslockedin;

	public InputLoop(Board theboard )
	{
		Board board = theboard;
	 inputs = new ArrayList<String>();
		inputwaittimes = new ArrayList<Integer>();
	
		
	}
	
	public JPanel getinputpanel()
	{
		mypanel = new JPanel();
		 inputkeylistdisplay = new ArrayList<JTextField>();
		 waittimelistdisplay = new ArrayList<JTextField>();

		inputs.add("break");
		inputwaittimes.add(0);
		inputs.add("break");
		inputwaittimes.add(0);
		inputs.add("break");
		inputwaittimes.add(0);
		inputs.add("break");
		inputwaittimes.add(0);
		mypanel = new JPanel();
		mypanel.setPreferredSize(new Dimension(500,30));
		confirmInputSetButton = new JButton("unlocked");
		
		confirmInputSetButton.addActionListener(new ActionListener(){ 
			@Override public void actionPerformed(ActionEvent e){ 
				textfieldeditabletoggle();
				//confirmloop();
				}
			
			 
		});
		

	
		
		mypanel.add(confirmInputSetButton);
		for (int i = 0 ; i < inputlistmaxsize; i++)
		{
			
		
			JTextField tempfield = new JTextField(3);
			tempfield.setBackground(Color.LIGHT_GRAY);
		
			JTextField tempfieldnum = new JTextField(5);
			tempfieldnum.setBackground(Color.CYAN);
			inputkeylistdisplay.add(tempfield);
			waittimelistdisplay.add(tempfieldnum);
			mypanel.add(tempfield);
			mypanel.add(tempfieldnum);
		}
		
		 listenkeyfield = new JTextField(1);
		 listenkeyfield.addKeyListener(new KeyListener() {

			@Override
			public void keyPressed(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub
				System.out.println("input key place" + KeyEvent.getKeyText(arg0.getKeyCode()));
				String tempstring = "" + arg0.getKeyChar();
				listenkeyfield.setText(tempstring);
			}

			@Override
			public void keyTyped(KeyEvent arg0) {
				
				// TODO Auto-generated method stub
				
			} });
		// listenkeyfield.setBackground(Color.RED);
			
				
		mypanel.add(listenkeyfield);
		mypanel.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				if(inputslockedin == true) {
					inputslockedin = false;
					confirmInputSetButton.setText("unlocked");
					System.out.println("not lcoekd in" + inputslockedin);
					}else 
					{
						
						
					}
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}});
		
		return mypanel;
		
	}
	public ArrayList<String> getloops()
	{return this.inputs;}
	public ArrayList<Integer> getwaittimes()
	{return this.inputwaittimes;}
	public void confirmloop()
	{
	

		 inputs = new ArrayList<String>();
	inputwaittimes = new ArrayList<Integer>();
		
		int i = 0;
    	for (JTextField el: inputkeylistdisplay)
    	{
    		
    		
    		if(el.getText().length() > 0 )
    		{
    		String inputkey = el.getText();
    		inputkey.trim();
    		if(inputkey.length() > 0 )
    		{
    			inputs.add(inputkey.toUpperCase());
    		}
    		else 
    		{
    			inputs.add("break");
    			
    		}
    		el.setEditable(false);
    		}else {inputs.add("break");}
    		inputs.add("done");
    		
    		if(waittimelistdisplay.get(i).getText().length() > 0 )
    		{
    		String pattern = "^[0-9]";
        	String ts = waittimelistdisplay.get(i).getText();
     		System.out.println("the new loop text" + ts);
    		if(ts.length() > 0 && ts.matches(pattern))
    		{
    			inputwaittimes.add( Integer.parseInt(ts));

    		}else { inputwaittimes.add( 0);}
    		waittimelistdisplay.get(i).setEditable(false);
    		
    	}else {inputwaittimes.add( 0);}
    		inputwaittimes.add( 0);
    		i++;
    		}
    	listenkeyfield.setEditable(false);
    	 listenkey = listenkeyfield.getText().trim().toUpperCase();
    	
    	inputslockedin = true;
    	confirmInputSetButton.setText("locked");
    	System.out.println("lcoekd in?" + inputslockedin);
		}
	
	
	public void textfieldeditabletoggle()
	{
		if(inputslockedin == true) {
		inputslockedin = false;
		listenkeyfield.setEditable(true);
		for (JTextField el: inputkeylistdisplay)
    	{el.setEditable(true);}
		for (JTextField el: waittimelistdisplay)
    	{el.setEditable(true);}
		confirmInputSetButton.setText("unlocked");
		}else {
			inputslockedin = true;
			listenkeyfield.setEditable(false);
			for (JTextField el: inputkeylistdisplay)
	    	{el.setEditable(false);}
			for (JTextField el: waittimelistdisplay)
	    	{el.setEditable(false);}
			confirmInputSetButton.setText("locked");
			confirmloop();
			
		}
	}
	
	public void reseteverything()
	{
		for (JTextField el: inputkeylistdisplay)
    	{el.setText("");}
		for (JTextField el: waittimelistdisplay)
    	{el.setText("");}
		inputslockedin = false;
		confirmInputSetButton.setText("unlocked");
		listenkeyfield.setText("");
	}
}
