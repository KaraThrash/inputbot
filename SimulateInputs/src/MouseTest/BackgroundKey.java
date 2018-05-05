package MouseTest;
import java.awt.event.KeyEvent;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

import MouseTest.Board;

public class BackgroundKey implements NativeKeyListener {
	public static  Board board;
	public boolean cansendkey;
	
	public void nativeKeyPressed(NativeKeyEvent e) {
		System.out.println("Key Pressed: " + NativeKeyEvent.getKeyText(e.getKeyCode()));
	
	
		if (e.getKeyCode() == NativeKeyEvent.VC_ESCAPE) {
			try {
				GlobalScreen.unregisterNativeHook();
			} catch (NativeHookException e1) {
				e1.printStackTrace();
			}
		}
	}


	public void nativeKeyReleased(NativeKeyEvent e) {
		System.out.println("Key Released: " + NativeKeyEvent.getKeyText(e.getKeyCode()));
		 int key = e.getKeyCode();
		 System.out.print(" - background NOT pressed - " + key);
		 
	
			 board.ListenToKeys(NativeKeyEvent.getKeyText(e.getKeyCode()));
			
			 //board.runinputloop();
			 
			 
			 
//			 TODO: remember this try catch is for the wait inside the thread
//			 try {
//					board.dorangomthings();
//				} catch (InterruptedException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}
			 
			 
			 
		 
		
			 if ( NativeKeyEvent.getKeyText(e.getKeyCode()) == "Space") {
				 			
				// board.backkick2();
				// board.clickmouseUp();
				 }
         
	}

	public void nativeKeyTyped(NativeKeyEvent e) {
		//System.out.println("Key Typed: " + NativeKeyEvent.getKeyText(e.getKeyCode()));
		
	
		
	}

	public static  void runmain(Board inutboard) {
		  board = inutboard;
		try {
			
			GlobalScreen.registerNativeHook();
			Logger logger = Logger.getLogger(GlobalScreen.class.getPackage().getName());
			logger.setLevel(Level.WARNING);

			//https://github.com/kwhat/jnativehook/wiki/ConsoleOutput
			logger.setUseParentHandlers(false);
		}
		catch (NativeHookException ex) {
			System.err.println("There was a problem registering the native hook.");
			System.err.println(ex.getMessage());

			System.exit(1);
		}

		GlobalScreen.addNativeKeyListener(new BackgroundKey());
	}
	
	//public static void main(String[] args) {
//		try {
//			
//			GlobalScreen.registerNativeHook();
//			Logger logger = Logger.getLogger(GlobalScreen.class.getPackage().getName());
//			logger.setLevel(Level.WARNING);
//
//			//https://github.com/kwhat/jnativehook/wiki/ConsoleOutput
//			logger.setUseParentHandlers(false);
//		}
//		catch (NativeHookException ex) {
//			System.err.println("There was a problem registering the nativfe hook.");
//			System.err.println(ex.getMessage());
//
//			System.exit(1);
//		}
//
//		GlobalScreen.addNativeKeyListener(new BackgroundKey());
//	}
}
