import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class SocketTest {
	Scanner scan = new Scanner(System.in);
	
	public SocketTest() {
		try {
		InetAddress ia = InetAddress.getByName("192.168.0.214");
		
		int port = 20000;
		Socket socket = new Socket(ia, port); //serverSocketTest에 20000으로 열어놈. 여기서 접속끝남
		/////////////////이 시점에서 접속이 이루어진다.
		
		
		/////server의 메시지를 받기
		/////Server에서 보낸 socket의 output을 받아서 is->isr->br->msg
		InputStream is = socket.getInputStream();//바이트단위로 읽어온다. 한글 깨짐
		InputStreamReader isr = new InputStreamReader(is); //문자단위로 읽어오기. 한글안깨지게. 한글자씩
		BufferedReader br = new BufferedReader(isr); //데이터를 한줄씩 읽기
		
		String msg = br.readLine();
		System.out.println("서버로부터 받은 문자->"+msg);
		
		///////////
		
		//클라이언트가 서버로 문자 보내기
		OutputStream os = socket.getOutputStream();
		OutputStreamWriter osw = new OutputStreamWriter(os);
		PrintWriter pw = new PrintWriter(osw);
	
		pw.println("나 클라이언트 문자를 서버로 보냄...");
		pw.flush();
		
		while(true) {
			System.out.println("보낼문자입력=");
			String data = scan.nextLine(); //한줄 읽어오는것
			socket = new Socket(ia, port);//원래는 할때마다 접속하고 그러지 않지만.. 
			//여기선 socket이 종료되면서 버려지기때문에 쓸때마다 새로운 socket을 만들어서 보내고 버리고 반복 
			os = socket.getOutputStream();
			osw = new OutputStreamWriter(os);
			pw = new PrintWriter(osw);
			
			pw.println(data);
			pw.flush();
		}
		
		
		}catch(Exception e) {e.printStackTrace();}
		System.out.println("클라이언트 프로그램이 종료되었습니다.");
	}
	

	public static void main(String[] args) {
		new SocketTest();
	}

}
