
import java.net.*;
import java.io.*;

import javax.swing.JOptionPane;
public class Server{
	DatagramSocket ds = null;
	byte buf[] = null;
	InetAddress ip = null;
	byte[] receive = null;
	DatagramPacket rcv_packet = null;
	public Server(){
		try{
			
			ds = new DatagramSocket(8888);
			ip = InetAddress.getLocalHost();

		}
		catch(Exception e){
			JOptionPane.showMessageDialog(null,e.getMessage(),"error",JOptionPane.ERROR_MESSAGE);
		}
	}
	public void recv(){
		try{
			System.out.println("waiting for data to be received");
			while(true){
			receive = new byte[65535];
			rcv_packet = new DatagramPacket(receive , receive.length);  
			ds.receive(rcv_packet);
			System.out.println(data(receive));
			}
		}
		catch(Exception e){
			JOptionPane.showMessageDialog(null,e.getMessage(),"error",JOptionPane.ERROR_MESSAGE);
		}
		
	}
	public static StringBuilder data(byte[] a)
    {
        if (a == null)
            return null;
        StringBuilder ret = new StringBuilder();
        int i = 0;
        while (a[i] != 0)
        {
            ret.append((char) a[i]);
            i++;
        }
        return ret;
    }
	public static void main(String args[]){
		Server s = new Server();
		s.recv();
	}
}