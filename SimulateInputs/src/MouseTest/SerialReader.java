package MouseTest;
import java.awt.Robot;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.EventListener;

import jssc.*;

public class SerialReader  {
	public    SerialPort port; // defines Object Serial
	public   Robot robot; 
	public  String data = "";
	public  static  SerialPort port2;
	public int value = 1;
	public   void  setup2() throws SerialPortException
	{
		
		//System.out.println("2222");
		//delay(2000);

	port = new SerialPort("COM1"); // starts the serial communication
	//System.out.println("2222");
	port.openPort();
	//System.out.println("2222");
	//port.bufferUntil(1); // reads the data from the serial port up to the character '.'. So actually it reads this: 215,214/141;315:314<316!314?315.
	port.readBytes();
	//port.closePort();
	}
	public void readport()
	{
		try {
			
			 port.openPort();//Open serial port
			 port.setParams(9600, 8, 1, 0);//Set params.
		        byte[] buffer = port.readBytes(10);//Read 10 bytes from serial port
		        port.closePort();//Close serial port
		} catch (SerialPortException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public static void closeport()
	{
		try {
			port2 = new SerialPort("COM1");
			port2.closePort();
			port2 = new SerialPort("COM6");
		
			port2.closePort();
		} catch (SerialPortException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	void serialEvent (SerialPort inputport) throws SerialPortException // starts reading data from the Serial Port
	{
		System.out.println(inputport + "inputport");
	data = inputport.readString(); // reads the data from the serial port up to the character '.' and it sets that into the String variable "data". So actually it reads this: 215,214/141;315:314<316!314?315.
	System.out.println(data);
	
	
	}
	public  void readsystemin()
	{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		InputStreamReader irr = new InputStreamReader(System.in);
		try {
			irr.read();
			  System.out.println(irr.read());
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  System.out.println(br);
	}
	
	  public static void main(String[] args) {
		  closeport();
		    String[] portNames = SerialPortList.getPortNames();
		    for(int i = 0; i < portNames.length; i++){
		        System.out.println(portNames[i]);
		    }
		}
}