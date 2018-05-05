package MouseTest;
import java.awt.MouseInfo;
import java.util.logging.Level;
import java.util.logging.Logger;

//global listener for when the program is in the background
import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.mouse.NativeMouseEvent;
import org.jnativehook.mouse.NativeMouseInputListener;
import MouseTest.ReadScreen;
import MouseTest.Board;
public class BackgroundMouse implements NativeMouseInputListener {
	public static  Board board;
	public boolean canclick = true;
	public void nativeMouseClicked(NativeMouseEvent e) {
		System.out.println("Mouse Clicked: " + e.getClickCount());
		
		
		 //try {
	
		//	} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
		//		e1.printStackTrace();
		//	}
		
	}

	public void nativeMousePressed(NativeMouseEvent e) {
		
		if(canclick == true)
		{
			//canclick = false;
	
		}
	}

	public void nativeMouseReleased(NativeMouseEvent e) {
	
		System.out.println("Mouse Released: " + e.getButton());
	}

	public void nativeMouseMoved(NativeMouseEvent e) {
		//ReadScreen.ParseScreen();
		//System.out.println("Mouse Moved: " + ReadScreen.ParseScreen(MouseInfo.getPointerInfo().getLocation().x,MouseInfo.getPointerInfo().getLocation().y));
		canclick = true;
	}

	public void nativeMouseDragged(NativeMouseEvent e) {
		canclick = false;
		System.out.println("Mouse Dragged: " + e.getX() + ", " + e.getY());
	}
	public static  void runmain(Board inutboard) {
		  board = inutboard;
		  try {
				GlobalScreen.registerNativeHook();
				Logger logger = Logger.getLogger(GlobalScreen.class.getPackage().getName());
				logger.setLevel(Level.WARNING);
				logger.setUseParentHandlers(false);
				//https://github.com/kwhat/jnativehook/wiki/ConsoleOutput
			}
			catch (NativeHookException ex) {
				System.err.println("There was a problem registering the native hook.");
			//	System.err.println(ex.getMessage());
	
				System.exit(1);
			}
	
			// Construct the example object.
			BackgroundMouse example = new BackgroundMouse();
	
			// Add the appropriate listeners.
			GlobalScreen.addNativeMouseListener(example);
			GlobalScreen.addNativeMouseMotionListener(example);
		}
	
	
	
//	public static void main(String[] args) {
//		try {
//			GlobalScreen.registerNativeHook();
//			Logger logger = Logger.getLogger(GlobalScreen.class.getPackage().getName());
//			logger.setLevel(Level.WARNING);
//			logger.setUseParentHandlers(false);
//			//https://github.com/kwhat/jnativehook/wiki/ConsoleOutput
//		}
//		catch (NativeHookException ex) {
//			System.err.println("There was a problem registering the native hook.");
//		//	System.err.println(ex.getMessage());
//
//			System.exit(1);
//		}
//
//		// Construct the example object.
//		BackgroundMouse example = new BackgroundMouse();
//
//		// Add the appropriate listeners.
//		GlobalScreen.addNativeMouseListener(example);
//		GlobalScreen.addNativeMouseMotionListener(example);
//	}
}
