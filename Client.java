
import java.awt.Event.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
public class Client {

	JFileChooser jfc;
	JFrame frame;
	JMenuBar menubar;
	JMenu file,send,exit;
	JMenuItem openFile;
	public File fileToSend=null;
	DatagramSocket ds = null;
	byte buffer[] = null;
	DatagramPacket packet = null;
	int portno;
	CreatePacket cp = null;
	Client c = null;
	public long length;
	public Client(){
		try{
			ds = new DatagramSocket();
		}
		catch(Exception e){
			JOptionPane.showMessageDialog(null,e.getMessage(),"error",JOptionPane.ERROR_MESSAGE);
		}
	}
	public void rdt_send(File fileToSend,InetAddress ip,int portno){
		if(fileToSend!=null){
			try{
				System.out.println("Sending the file");
				cp = new CreatePacket();
				length = fileToSend.length();
				while(cp.cur_index < length){
				buffer = cp.make_pkt(0,fileToSend,0);
				packet = new DatagramPacket(buffer,buffer.length,ip,8888);
				ds.send(packet);
				}
				}
			catch(Exception e){
				JOptionPane.showMessageDialog(null,e.getMessage(),"error",JOptionPane.ERROR_MESSAGE);
			}
		}
		else
			JOptionPane.showMessageDialog(null,"Select a file before sending","error",JOptionPane.ERROR_MESSAGE);
	}
	void menu(){
		jfc = new JFileChooser();
		frame = new JFrame("Menu");
		frame.setSize(700,900);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		menubar = new JMenuBar();
		frame.setJMenuBar(menubar);
		file = new JMenu("File");
		send = new JMenu("Send");
		exit = new JMenu("Exit");
		send.addMenuListener(new MenuListener(){
				public void menuSelected(MenuEvent e){
					try{
						c = new Client();
						String host="127.0.0.1";
						InetAddress ip;
						ip = InetAddress.getByName(host);
						c.rdt_send(fileToSend,ip,8888);
					}
					catch (UnknownHostException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				public void menuDeselected(MenuEvent e){
		        
				}
				public void menuCanceled(MenuEvent e){
		        
		        }
		});
		menubar.add(file);
		menubar.add(send);
		menubar.add(exit);
		openFile = new JMenuItem(new AbstractAction("Open File"){
		    public void actionPerformed(ActionEvent a) {
		    	chooseFile();
		    	}
		});
		
		file.add(openFile);
		
	}
	void chooseFile(){
		int p;
    	p= jfc.showOpenDialog(null);
    	if(p == JFileChooser.APPROVE_OPTION){
    		 fileToSend = jfc.getSelectedFile();
    	}
    	else
    		JOptionPane.showMessageDialog(frame,"File cannot be selected","error",JOptionPane.ERROR_MESSAGE);
    }
	public static void main(String[] args){
		Client ss = new Client();
		ss.menu();
		
		
	}

}
