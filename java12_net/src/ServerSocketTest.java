import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerSocketTest {

	public ServerSocketTest() {
		try {
		//접속을 대기한 후 접속하면 Socket 객체를 얻을 수 있다.
		ServerSocket ss  =new ServerSocket(20000); //포트번호는 65500정도니까 그 사이에 있는것 선택
		//이건 객체만 만든거고 접속을 기다리진 않고있는것.
		//접속을 대기하는 메소드가 따로 있다.. 누군가 접속하길 기다리는기능, 
		//누군가접속하면 접속한사람의 inet어드레스를 포함해서 소켓을 생성해줌
		//접속안하면 여기서 계속 대기중
		
		while(true) { //접속만하면 문자를 보내게 무한루프 여러명을 대기하도록
			//접속대기 : 접속자의 ip가 들어있음. 
			System.out.println("server start....");
			System.out.println("접속대기중....");
			Socket socket = ss.accept();
			////////////
			//잡자에서는 콘솔로 두개를 동시에 실행할수가없으니 dos로 들어가서 확인할것
			
			//접속자의 InetAddress객체
			InetAddress ia = socket.getInetAddress();
			System.out.println(ia.getHostAddress()+"님이 접속하였습니다..");
			
			//접속자에게 문자보내기 준비
			OutputStream os = socket.getOutputStream();
			OutputStreamWriter osw = new OutputStreamWriter(os); //한글로써야하니까
			PrintWriter pw = new PrintWriter(osw); // 
			
			//문자보내기. ps->osw->os->socket
			pw.println("서버에 접속 되었습니다.. 환영합니다."); //문자 한줄 보내기. 지금 접속하고 있는 컴퓨터에게
			pw.flush();
			//flush()메서드는 버퍼에있는 데이터를 모두 파일에 쓰고 나서 버퍼를 비우는 메서드
			
			
			//클라이언트가 보낸문자 받기
			InputStream is = socket.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
			
			String msg = br.readLine();
			System.out.println("클라이언트로부터 받은 문자-->"+ msg);
			
		}
		
		}catch(Exception e) {
			e.printStackTrace();
		}
		System.out.println("프로그램이 종료되었습니다.");
	}

	public static void main(String[] args) {
		new ServerSocketTest();
	}

}
