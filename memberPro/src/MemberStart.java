

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
//레이아웃만 잡을 메인
public class MemberStart extends JFrame implements ActionListener{
	//폼
	JPanel nPane = new JPanel(new BorderLayout()); 
		//폼 라벨
		JPanel lblPane =  new JPanel(new GridLayout(6,1));
			JLabel numLbl = new JLabel("번호");
			JLabel nameLbl = new JLabel("이름");
			JLabel telLbl = new JLabel("연락처");
			JLabel emailLbl = new JLabel("이메일");
			JLabel addrLbl = new JLabel("주소");
			JLabel writedateLbl = new JLabel("등록일"); // 등록일은 안넣어도 되는것인데 그냥 해봄
		//폼 입력 콤포넌트
		JPanel tfPane = new JPanel(new GridLayout(6,1)); //이게 왼쪽에 붙게 하려면 패널을 새로 만들어서 넣거나 레이아웃을 없에고 좌표로 지정하면된다. 아래는 패널 새로 만듬
			JTextField numTf = new JTextField(5);			JPanel p1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
			JTextField nameTf = new JTextField(10);			JPanel p2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
			JTextField telTf = new JTextField(15);			JPanel p3 = new JPanel(new FlowLayout(FlowLayout.LEFT));
			JTextField emailTf = new JTextField(30);		JPanel p4 = new JPanel(new FlowLayout(FlowLayout.LEFT));
			JTextField addrTf = new JTextField(40);			JPanel p5 = new JPanel(new FlowLayout(FlowLayout.LEFT));
			JTextField writedateTf = new JTextField(20);	JPanel p6 = new JPanel(new FlowLayout(FlowLayout.LEFT));
			
		//메뉴, 회원리스트
		JPanel cPane = new JPanel(new BorderLayout());
			//메뉴
			JPanel menuPane = new JPanel();
				JButton allListBtn = new JButton("전체 목록");
				JButton addBtn = new JButton("추가");
				JButton editBtn = new JButton("수정");
				JButton delBtn = new JButton("삭제");
				JButton clearBtn = new JButton("지우기");
				JButton endBtn = new JButton("종료");
				JButton excelSaveBtn = new JButton("엣셀로 저장하기");
				JButton excelOpenBtn = new JButton("엑셀에서 불러오기");
			//회원목록
			String title[] = {"번호", "이름", "연락처", "이메일", "주소", "등록일"};
			DefaultTableModel model = new DefaultTableModel(title,0); //이렇게 쓰면 시작할때 값이 하나도 없이 시작된다.
			JTable table = new JTable(model);
			JScrollPane sp = new JScrollPane(table);
			
			
	public MemberStart() {
		super("회원관리시스템");
		// 폼 만들기
		nPane.add(BorderLayout.WEST, lblPane);
			lblPane.add(numLbl); lblPane.add(nameLbl); lblPane.add(telLbl); lblPane.add(emailLbl); lblPane.add(addrLbl); lblPane.add(writedateLbl);
			
		nPane.add(BorderLayout.CENTER, tfPane);
			
			p1.add(numTf); 			tfPane.add(p1);//왼쪽으로 붙게 만들기 //패널에다가 텍스트필드 담기 .. 한꺼번에 하니까 안되네 tfPane.add(p1.add(numTf));하니까 안됨.
			p2.add(nameTf); 		tfPane.add(p2);
			p3.add(telTf); 			tfPane.add(p3);
			p4.add(emailTf); 		tfPane.add(p4);
			p5.add(addrTf); 		tfPane.add(p5);
			p6.add(writedateTf);	tfPane.add(p6);
		
		
		add(BorderLayout.NORTH, nPane);
		
		//메뉴, 회원리스트
		menuPane.add(allListBtn);
		menuPane.add(addBtn); menuPane.add(editBtn); menuPane.add(delBtn); menuPane.add(clearBtn); menuPane.add(endBtn);
		menuPane.add(excelSaveBtn); menuPane.add(excelOpenBtn);
		cPane.add(menuPane, BorderLayout.NORTH); 
		cPane.add(sp);
		
		add(cPane);
		
		setSize(700,700);
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		//전체 레코드 추가
		getAllRecord();
		
		//JTable 이벤트 등록하기
		table.addMouseListener(new MouseAdapter(){
			public void mouseReleased(MouseEvent me) {
				if(me.getButton()== 1) { //마우스의 왼쪽 버튼 클릭시 동작
					int row = table.getSelectedRow(); //현재선택된 행의 index
					numTf.setText(String.valueOf(model.getValueAt(row, 0))); //원래 int니까 integer로 바꾸고 string으로 바꿔줘야한다.?(Integer) model앞에 이거 빼도 되는데 확인
					nameTf.setText((String)model.getValueAt(row, 1));
					telTf.setText((String)model.getValueAt(row, 2));
					emailTf.setText((String)model.getValueAt(row, 3));
					addrTf.setText((String)model.getValueAt(row, 4));
					writedateTf.setText((String)model.getValueAt(row, 5));
				}
			}
		});
		//메뉴 이벤트등록
		allListBtn.addActionListener(this);
		addBtn.addActionListener(this);
		editBtn.addActionListener(this);
		delBtn.addActionListener(this);
		clearBtn.addActionListener(this);
		endBtn.addActionListener(this);
		excelSaveBtn.addActionListener(this);
		excelOpenBtn.addActionListener(this);
	}

	//레코드 전체 선택하여 JTable에 추가하기.(이걸로 JTable 갱신)
	public void getAllRecord() { //이거만 선택하면 테이블 다시 세팅
		//JTable에 있는 레코드 지우기... //지금 써있는걸 지우고 다시써야지 안그러면 써있는것+새로쓴것 순으로 나온다.
		model.setRowCount(0); //행의 수를 0으로 하면 다 지워진다.
		
		//DAO객체생성
		// MemberDAO dao = new MemberDAO(); 아래랑 이거랑 둘중 하나 쓰면 되는데 아래를 선호하는듯?
		MemberDAO dao = MemberDAO.getInstance(); //이걸 위해서 DAO에 메소드 만듬
		
		List<MemberVO> list= dao.getAllMember(); //정보가 돌아와서 list에 담겨져있다.
		
		for(int i=0; i<list.size(); i++) {
			MemberVO vo = list.get(i); // i번째에 있는 VO를 끄집어내서 vo에 담아줌
			Object[] record = {vo.getNum(), vo.getUsername(), vo.getTel(), vo.getEmail(), vo.getAddr(), vo.getWritedate()};
			model.addRow(record);
		}
	}
	
	//폼의 문자열 지우기
	public void formClear() {
		numTf.setText("");
		nameTf.setText("");
		telTf.setText("");
		emailTf.setText("");
		addrTf.setText("");
		writedateTf.setText("");
	}
	
	//폼의 레코드를 데이터베이스에 추가하기 .. VO에 데이터를 추가함. 아직 DB에 들어가진 않음
	public void memberInsert() {
		MemberVO vo = new MemberVO();
		vo.setNum(Integer.parseInt(numTf.getText()));
		vo.setUsername(nameTf.getText());
		vo.setTel(telTf.getText());
		vo.setEmail(emailTf.getText());
		vo.setAddr(addrTf.getText());
		
		//데이터 입력 유무 확인
		if(vo.getNum()==0) { //int는 데이터를 안넣으면 초기값이 0이다
			JOptionPane.showMessageDialog(this, "번호를 입력하세요..");
		}else if(vo.getUsername()==null || vo.getUsername().equals("")) { //null이 있을지 모르는 자료라면 항상 null먼저 확인을 해야한다. 그렇지 않으면 에러남(값이 null인데 확인하려고하니까)
			JOptionPane.showMessageDialog(this, "이름을 입력하세요..");
		}else if(vo.getTel()==null || vo.getTel().equals("")) {
			JOptionPane.showMessageDialog(this, "연락처를 입력하세요...");
		}else {
			MemberDAO dao = new MemberDAO();
			int result = dao.insertRecord(vo);
			if(result>0) { //레코드가 추가되었을때 //2이상은 안나온다.
				JOptionPane.showMessageDialog(this, "회원이 등록되었습니다.");
				getAllRecord(); //테이블 갱신
				formClear(); //테이블 갱신 후 텍스트필드 비우기
			}else { //추가실패시
				JOptionPane.showMessageDialog(this, "회원등록이 실패하였습니다.");
			}
		}
		
	}
	//회원정보 수정
	public void memberEdit() {
		MemberVO vo = new MemberVO(); //이걸 객체를 만들면 보낼정보를 vo에 담는것.
		vo.setNum(Integer.parseInt(numTf.getText()));
		vo.setUsername(nameTf.getText());
		vo.setTel(telTf.getText());
		vo.setEmail(emailTf.getText());
		vo.setAddr(addrTf.getText());
		/////////////////여기까지 vo에 업데이트할 데이터를 담는것
		if(vo.getNum()<=0) {
			JOptionPane.showMessageDialog(this, "번호는 반드시 있어야 합니다."); //여기서 this는 프레임
		}else if(vo.getUsername()==null || vo.getUsername().equals("")) {
			JOptionPane.showMessageDialog(this, "이름은 반드시 있어야 합니다.");
		}else if(vo.getTel()==null || vo.getUsername().equals("")) {
			JOptionPane.showMessageDialog(this, "연락처는 반드시 있어야 합니다.");
		}else {
			MemberDAO dao = MemberDAO.getInstance();
			int result = dao.updateRecord(vo);
			if(result>0) {
				JOptionPane.showMessageDialog(this, "회원정보가 수정되었습니다.");
				getAllRecord(); //테이블 갱신
				formClear(); //테이블 갱신 후 텍스트필드 비우기
			}else {
				JOptionPane.showMessageDialog(this, "회원정보가 수정되지 않았습니다.");
			}
		}
	}
	//회원삭제
	public void memberDelete() {
		//YES NO 선택창 띄우기
		int okNo = JOptionPane.showConfirmDialog(this, "회원삭제하시겠습니까?", "회원삭제확인", JOptionPane.YES_NO_OPTION); //this는 부모컨테이너.. 여기서는 fram
																							//yes 누르면 0이 돌아오고 no 누르면 1이 돌아온다.
		if(okNo==JOptionPane.OK_OPTION) { //이렇게 하면 1인지 0인지 상관없이 확인가능
			MemberDAO dao = MemberDAO.getInstance();
			int result = dao.deleteRecord(Integer.parseInt(numTf.getText()));
			if(result>0) {
				JOptionPane.showMessageDialog(this, "회원이 삭제되었습니다.");
				getAllRecord();
				formClear();
			}else {
				JOptionPane.showMessageDialog(this, "회원삭제 실패하였습니다.");
			}
		}		
	}
	//엑셀로 JTable데이터 저장하기 //빌드패스 먼저 걸기
	public void setExcelSave() {
		JFileChooser fc = new JFileChooser(); //파일선택창
		//보통 이렇게 한종류의 파일만 사용할때는 memo에서 한 필터 기능으로 엑셀파일만 가능하게 설정해주는게 좋다. 
		int state = fc.showSaveDialog(this);
		if(state==0) { //저장버튼을 선택했을때
			//워크북
			HSSFWorkbook book = new HSSFWorkbook();
			
			//시트
			HSSFSheet sheet = book.createSheet("회원목록"); //내용 안적으면 시트1 시트2 식으로 자동
			
			//제목
			HSSFRow r  = sheet.createRow(0);
			r.createCell(0).setCellValue("번호"); //0번째 열의 번호
			r.createCell(1).setCellValue("이름"); //1번째 열의 이름
			r.createCell(2).setCellValue("연락처");
			r.createCell(3).setCellValue("이메일");
			r.createCell(4).setCellValue("주소");
			r.createCell(5).setCellValue("등록일");
			
			//행
			int rowCount = table.getRowCount(); // JTable에 있는 레코드 수 구하기

			for(int row=0; row<rowCount; row++) { //0~레코드 수까지
				HSSFRow r1 = sheet.createRow(row+1);

				//열
				int columnCount =  table.getColumnCount(); //열의 갯수 구하기. 테이블의 퀄럼 수
				for(int col=0; col<columnCount; col++) { //0~5까지 
					if(col==0) { //숫자로 저장 .. 0번은 번호니까
						r1.createCell(col).setCellValue((Integer)model.getValueAt(row, col)); //row=행 col=열
						//JTable의 index와 엑셀의 index는 행번호가 다르다.
						
					}else {//문자로 저장
						r1.createCell(col).setCellValue((String)model.getValueAt(row, col)); //row=행 col=열
					}
				}
			}
			
			//워크북을 파일에 쓰기
			try {
				File f = fc. getSelectedFile();
				FileOutputStream fos = new FileOutputStream(f);
				
				book.write(fos);
				fos.close();
				book.close();
				JOptionPane.showMessageDialog(this, "엑셀로 쓰기가 완료되었습니다.");
			}catch(Exception e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(this, "엑셀로 쓰기가 실패하였습니다.");
			}
		}
	}
	
	//엑셀파일을 읽어 JTable에 목록으로 표시하기
	public void getExcelLoad() {
		JFileChooser fc = new JFileChooser();
		int state = fc.showOpenDialog(this); //선택된 파일은 fc에있고 이걸 뭘 눌렀는지가 state에 리턴이된다. 0이면 열기 1이면 취소
		if(state==0) {
			try {
			File f = fc.getSelectedFile(); 
			FileInputStream fis = new FileInputStream(f); // 파일읽어오기?
			
			POIFSFileSystem poi = new POIFSFileSystem(fis);//엑셀로 바꿔줘야한다.
			
			//워크북
			HSSFWorkbook book= new HSSFWorkbook(poi);
			
			//시트
			HSSFSheet sheet = book.getSheet("회원목록");
			
			//JTable의 원래 정보 없애기
			model.setRowCount(0);
			
			//행
			int rowCount = sheet.getPhysicalNumberOfRows();//엑셀에 있는 행의수
			for(int r=1; r<rowCount; r++) {
				HSSFRow row = sheet.getRow(r); 
				//열
				int cellCount = row.getPhysicalNumberOfCells();//셀의 수..칸의 수
				Object data[] = new Object[cellCount]; //칸의 수만큼.. 한줄의 데이터가 들어가는것
				for(int c=0; c<cellCount; c++) { //1행 0열 개념으로 들어감
					if(c==0) { //숫자
						data[c] = (int)row.getCell(c).getNumericCellValue();//c가 0부터시작.row에서 c번째 셀을 구하고. 숫자데이터만가져온다. 더블이라 강제로 정수변한
					}else { //문자
						data[c] = row.getCell(c).getStringCellValue(); //문자데이터만 가져온다.
					}
				}
				model.addRow(data);
			}
			poi.close();
			fis.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void actionPerformed(ActionEvent ae) {
		Object event = ae.getSource();
		if(event == clearBtn) { //폼의 문자열 지우기
			formClear();
		}else if(event == addBtn) { //폼의 데레코드를 데이터베이스에 추가하기
			memberInsert(); //데이터베이스 추가하기. 
		}else if(event == editBtn) {
			memberEdit();
		}else if(event == delBtn) {
			memberDelete();
		}else if(event == endBtn) {
			dispose();
			System.exit(0);
		}else if(event == excelSaveBtn) {
			setExcelSave();
		}else if(event == excelOpenBtn) {
			getExcelLoad();
		}else if(event == allListBtn) {
			getAllRecord();
		}
	}
	
	public static void main(String[] args) {
		new MemberStart();
	}

}
