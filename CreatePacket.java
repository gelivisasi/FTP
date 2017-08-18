
import java.io.*;


public class CreatePacket {
	int ack_num,nack_num;
	static int seq_no=0;
	byte buffer[] = null;
	long length,StartIndex,EndIndex,i;
	static long cur_index;
	FileInputStream fis = null;
	int data;
	char c;
	String str;
	StringBuffer sb;


	public byte[] make_pkt(int var,File fileToSend,int checksum){
		buffer = new byte[64]; //56 Bytes for data and 8 Bytes for Control Header
		length = fileToSend.length();
		sb=new StringBuffer("");
		sb.append(Integer.toString(seq_no));
		seq_no++;
		sb.append("@");
		sb.append(Integer.toString(ack_num));
		sb.append("$");
		try{
			fis = new FileInputStream(fileToSend);
			StartIndex = cur_index;
			for(i=0;i<cur_index && ((data = fis.read()) != -1);i++){
				
			}
			for(i=cur_index; i<(StartIndex + 56) && i<length ; i++,cur_index++){
					data = fis.read();
					c = (char)data;
					str = Character.toString(c);
					sb.append(str);
					str = sb.toString();
					buffer = str.getBytes();
					
				}
			if(length == i){
				
				sb.append("^");
				str = sb.toString();
				buffer = str.getBytes();
				
			}
		}
		catch (Exception e){
					// TODO Auto-generated catch block
					System.out.println(e.getMessage());
					e.printStackTrace();
		}
			
	return buffer;
	}
}
