package MouseTest;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import MouseTest.BackgroundKey;


public class Application extends JFrame {
    public Board board;
    public SerialReader sreader;
    public Application() {

        initUI();
    
    }

    private void initUI() {
    	Board board = new Board();
    
        add(board);
      BackgroundKey.runmain(board);
      //  BackgroundMouse.runmain(board);
       // setSize(500, 500);
        JFrame frame = new JFrame("simulate inputs");
        frame.setBackground(Color.GRAY);
    	frame.setSize(500, 500);
    	
    	frame.add(board.selectorButtons());

        
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       // frame.getContentPane().add(board.selectorButtons(), BorderLayout.NORTH);
        frame.setVisible(true);
      
        setTitle("Application");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }    
    

    
    public static void main(String[] args) {
    	
    	

        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                Application ex = new Application();
                
              //  ex.setVisible(true);
            }
        });
        
        
        
    }
}