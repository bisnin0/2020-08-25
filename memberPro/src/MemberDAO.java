import java.util.ArrayList;
import java.util.List;


public class MemberDAO extends DBConn {

	public MemberDAO() {
		
	}
	
	public static MemberDAO getInstance() { //�� �޼ҵ带 ȣ���ϸ� ��ü�� ���ƿ´�. static�� ���ϸ� ����������. ??�̰� ���̷��� Ȯ���ϱ�
		return new MemberDAO(); 
	} //�̰� �ص� �ǰ� ���ص� �Ǵµ� ���� �ϴµ�? ��ü������ ��� �Ұ����� �����ε�
	
	// ���ڵ� �߰�
	public int insertRecord(MemberVO vo) {
		int result = 0; //ó������� ��� ����
		try {
			getConn();
			String sql = "insert into member(num, username, tel, email, addr, writedate)"
					+ " values(?,?,?,?,?,sysdate)";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,  vo.getNum());
			pstmt.setString(2, vo.getUsername());
			pstmt.setString(3, vo.getTel());
			pstmt.setString(4, vo.getEmail());
			pstmt.setString(5, vo.getAddr());
			
			result= pstmt.executeUpdate(); //������Ʈ�ϸ� 1�� ����.
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			
		}
		
		return result; //0�ƴϸ� 1�� ���ϵɰ��̴�.
	}
	// ���ڵ� ����
	public int updateRecord(MemberVO vo) { //vo�� ������ �����ű⶧���� �ŰԺ����� vo�����Ѵ�.
		//vo�� ������ �޾Ƽ� �װ� DB�� ������Ʈ.
		//�����Ǹ� int�� ���ϵȰ����� ������Ʈ �Ǿ����� �ƴ��� Ȯ���� �� �ֱ⶧���� int�� ����
		int result=0;
		try {
			getConn();
			String sql = "update member set username=?, tel=?, email=?, addr=?"
					+ " where num=?"; //���Ǿ����� �� ��������. ����
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getUsername());
			pstmt.setString(2, vo.getTel());
			pstmt.setString(3, vo.getEmail());
			pstmt.setString(4, vo.getAddr());
			pstmt.setInt(5, vo.getNum());
			
			result = pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			getClose();
		}
		return result;
	}
	// ���ڵ� ����
	public int deleteRecord(int num) {//���ڵ� ��ȣ �ϳ��� ������ �Ǵϱ� int num
		int result = 0;
		try {
			getConn();
			String sql="delete from member where num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			result = pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			getClose();
			
		}
		return result;
	}
	
	
	// ���ڵ� ��ü ����  --> �ݺ������� �̷������ �����. Jtable ����? �̺κ� Ȯ���ϱ�. �����Ѱſ��� �ȵƴ���? �̰� �׺κ��� �´��� Ȯ��ġ����.
	public List<MemberVO> getAllMember() {  //ArrayList�� �ε����� �������ְ� add�ϴ� ������� ���δ�.
		List<MemberVO> list= new ArrayList<MemberVO>(); //�������Ŭ������ List�� ���� �տ���
		try {
			getConn();
			
			String sql = "select num, username, tel, email, addr, writedate from member order by num";
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery(); //�����Ͱ� ������ ������.. �����Ҷ��� update  .. rs�� DBConn������
			while(rs.next()) {
/*				
				MemberVO vo = new MemberVO();
				///�����͸� �ϳ��� �ִ¹�. ���ͼ����̿�.
				vo.setNum(rs.getInt(1)); //rs���ִ� ù��° �ʵ忡 �ִ� �����͸� ������ �о vo���ִ� num�� �����ض�
				vo.setUsername(rs.getString(2));
				vo.setTel(rs.getString(3));
				vo.setEmail(rs.getString(4));
				vo.setAddr(rs.getString(5));
				vo.setWritedate(rs.getString(6));
				////////////////
*/				 
				//VO�� ������ �޼ҵ�� ������̿��ؼ� �ѹ��� �ִ¹�.. ���� ��Ȯ�ϰ�
				MemberVO vo= new MemberVO(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6));
				//���� ���Ѱɷ� �ϸ� �ȴ�. ���� �Ʒ��� ���� ��
				
				// �ݺ����̶� ���� ���ư��� vo�� ��� ������ �������. �̰� �����Ұ� �ʿ��� //���� ArrayList ��ü����
				list.add(vo); //����ؼ� �����͸� ����
				//
				
				
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			getClose();
		}
		return list; //�̷��� ���� �ϸ� �� �޼ҵ忡 ArrayList�� ������ ��� ��� ������ ����ְԵȴ�.
	}

}

/*
List .. �������� , set.. ��������, map .. Ű�� ������ ����

List = 

vector
ArrayList(�̰� ���� ���� ��)
....���������
*/