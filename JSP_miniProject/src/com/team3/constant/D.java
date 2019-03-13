package com.team3.constant;

public class D {
	// 회원가입
	public static final String INSERT_MEMBER = 
	"INSERT INTO members (mem_num, mem_id, mem_pw, mem_name, mem_birth, mem_email, mem_gender, mem_phone, mem_school, mem_part, mem_job, mem_create)"
	+ "VALUES(mem_num_Seq.nextval, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, SYSDATE)";
	
	// 회원가입시 아이디 중복체크
	public static final String SELECT_MEMBER_ID = "SELECT * FROM members WHERE mem_Id = ?";
	
	// 로그인시 아이디 비밀번호 유효성 체크
	public static final String SELECT_MEMBER_ID_PW = "SELECT * FROM members WHERE mem_Id = ? AND mem_Pw = ?";
	
	// 게시판 글 작성, 수정, 삭제
	public static final String INSERT_BOARDS_WRITE = "INSERT INTO Boards VALUES(?, board_num_Seq.nextval, ?, ?, ?, SYSDATE, 0, 0, 0, ?)";
	public static final String UPDATE_BOARDS_WRITE = "UPDATE Boards SET board_subject = ?, board_content = ? WHERE board_num = ?";
	public static final String DELETE_BOARDS_WRITE = "DELETE FROM Boards WHERE board_num = ?";
	
	// 모든 게시글 불러오기
	public static final String SELECT_BOARDS_ALL =
	"SELECT ROWNUM, m.* FROM (SELECT b.* FROM Boards b where b.CATEGORY_id = ? ORDER BY b.board_num ASC) m "
	+ "where ROWNUM <= ?-(?*?)+? order by ROWNUM DESC";
	public static final String SELECT_BOARDS_ALL2 =
	"SELECT ROWNUM, m.* FROM (SELECT b.* FROM Boards b where b.CATEGORY_id = ? ORDER BY b.board_num ASC) m "
	+ "order by ROWNUM DESC";

	// 게시글 상세보기, 조회수 증가
	public static final String SELECT_BOARDS_WRITE_VIEW = "SELECT * FROM Boards WHERE Board_Num = ?";
	public static final String UPDATE_BOARDS_VIEWCOUNT = "UPDATE Boards SET board_viewcount = board_viewcount + 1 WHERE board_num = ?";
	
	// 게시글 좋아요, 싫어요 증/감
	public static final String INSERT_BOARDS_RECOMMEND = "INSERT INTO Boards_Recommend VALUES(?, ?, ?, ?)";
	public static final String SELECT_BOARDS_RECOMMEND = "SELECT * FROM Boards_Recommend WHERE board_num = ? AND mem_num = ?";
	public static final String UPDATE_BOARDS_RECOMMEND_LIKE = "UPDATE Boards_Recommend SET like_count = ? WHERE board_num = ? AND mem_num = ?";
	public static final String UPDATE_BOARDS_RECOMMEND_HATE = "UPDATE Boards_Recommend SET hate_count = ? WHERE board_num = ? AND mem_num = ?";
	public static final String UPDATE_BOARDS_LIKE_UP = "UPDATE Boards SET board_like = board_like + 1 WHERE board_num = ?";
	public static final String UPDATE_BOARDS_LIKE_DOWN = "UPDATE Boards SET board_like = board_like - 1 WHERE board_num = ?";
	public static final String UPDATE_BOARDS_HATE_UP = "UPDATE Boards SET board_hate = board_hate + 1 WHERE board_num = ?";
	public static final String UPDATE_BOARDS_HATE_DOWN = "UPDATE Boards SET board_hate = board_hate - 1 WHERE board_num = ?";
	public static final String DELETE_BOARDS_RECOMMEND = "DELETE FROM Boards_Recommend WHERE board_num = ?";
	// 댓글 작성, 수정, 삭제, 불러오기
	public static final String INSERT_BOARDS_COMMENT_WRITE = "INSERT INTO Comments VALUES(?, ?, comment_num_Seq.nextval, ?, SYSDATE, ?)";
	public static final String SELECT_BOARDS_COMMENT_LIST = "SELECT * FROM Comments WHERE board_num = ? ORDER BY comment_regdate DESC";
	public static final String UPDATE_BOARDS_COMMENT_WRITE ="UPDATE Comments SET comment_content = ? WHERE comment_num = ?";
	public static final String SELECT_BOARDS_COMMENT_UPDATE = "SELECT * FROM Comments WHERE comment_num = ?";
	public static final String DELETE_BOARDS_COMMENT_WRITE = "DELETE FROM Comments WHERE comment_num = ?";
	
	// 파일 업로드 / 삭제
	public static final String SELECT_BOARDS_FILE_CHECK = "SELECT board_num FROM Boards WHERE mem_num = ? AND board_subject = ?";
	public static final String INSERT_BOARDS_FILE = "INSERT INTO file_db VALUES(?, file_id_seq.nextval, ?, ?)";
	public static final String DELETE_BOARDS_FILE = "DELETE FROM file_db WHERE board_num = ?";
	
	public static final String FIND_ID = "select * from members where mem_name = ? AND mem_email = ? AND mem_phone = ?";
	public static final String FIND_PW = "select * from members where mem_id = ? AND mem_email = ? AND mem_phone = ?";
	public static final String CHANGE_PW = "update members set mem_pw=? where mem_num = ?";
	public static final String CHANGE_PW_P = "select * from members where mem_id = ? AND mem_name = ?";
	public static final String CHANGE_ALIVE = "update members set mem_alive = 1 where mem_num = ?";
	
	public static final String ALL_SELECT_MEMBER =
	"select ROWNUM, m.* FROM (select b.mem_num, b.mem_alive, b.mem_super, b.mem_id, b.mem_pw, b.mem_name, to_char(b.mem_birth, 'yyyy-mm-dd') mem_birth, b.mem_email, b.mem_gender, b.mem_phone, b.mem_school, b.mem_part, b.mem_job, to_char(b.mem_create, 'yyyy-mm-dd') mem_create from members b ORDER BY b.mem_num ASC) m "
	+ "order by ROWNUM DESC";
	public static final String ALL_SELECT_MEMBER2 =
	"select ROWNUM, m.* FROM (select b.mem_num, b.mem_alive, b.mem_super, b.mem_id, b.mem_pw, b.mem_name, to_char(b.mem_birth, 'yyyy-mm-dd') mem_birth, b.mem_email, b.mem_gender, b.mem_phone, b.mem_school, b.mem_part, b.mem_job, to_char(b.mem_create, 'yyyy-mm-dd') mem_create from members b ORDER BY b.mem_num ASC) m "
	+ "where ROWNUM <= ?-(?*?)+? order by ROWNUM DESC";
	
	public static final String ALL_SELECT_ALIVE_MEMBER = 
	"select ROWNUM, m.* FROM (select b.mem_num, b.mem_alive, b.mem_super, b.mem_id, b.mem_pw, b.mem_name, to_char(b.mem_birth, 'yyyy-mm-dd') mem_birth, b.mem_email, b.mem_gender, b.mem_phone, b.mem_school, b.mem_part, b.mem_job, to_char(b.mem_create, 'yyyy-mm-dd') mem_create from members b ORDER BY b.mem_num ASC) m "
	+ "where mem_alive=0 order by ROWNUM DESC";
	public static final String ALL_SELECT_ALIVE_MEMBER2 = 
	"select ROWNUM, m.* FROM (select b.mem_num, b.mem_alive, b.mem_super, b.mem_id, b.mem_pw, b.mem_name, to_char(b.mem_birth, 'yyyy-mm-dd') mem_birth, b.mem_email, b.mem_gender, b.mem_phone, b.mem_school, b.mem_part, b.mem_job, to_char(b.mem_create, 'yyyy-mm-dd') mem_create from members b ORDER BY b.mem_num ASC) m "
	+ "where mem_alive=0 AND ROWNUM <= ?-(?*?)+? order by ROWNUM DESC";
	
	public static final String ALL_SELECT_BOARD = 
	"SELECT ROWNUM, m.* FROM (SELECT b.* FROM Boards b where b.CATEGORY_id LIKE ? ORDER BY b.board_num ASC) m "
	+ "order by ROWNUM DESC";
	public static final String ALL_SELECT_BOARD2 = 
	"SELECT ROWNUM, m.* FROM (SELECT b.* FROM Boards b where b.CATEGORY_id LIKE ? ORDER BY b.board_num ASC) m "
	+ "where ROWNUM <= ?-(?*?)+? order by ROWNUM DESC";
	
	public static final String HIT_SELECT_BOARD = 
	"SELECT ROWNUM, m.* FROM (SELECT b.* FROM Boards b where b.CATEGORY_id LIKE '%' ORDER BY b.board_like ASC) m "
	+ "order by ROWNUM DESC";
	
	public static final String FIND_MEMBER =
	"SELECT * from members where mem_num = ?";
	public static final String UPDATE_MEMBER = 
	"UPDATE MEMBERS SET MEM_PW = ?, MEM_EMAIL = ?, MEM_SCHOOL = ?, MEM_PART = ?, MEM_JOB = ?, MEM_PHONE = ? "
	+"WHERE MEM_NUM = ?";
	
	public static final String USER_DELETE_WRITECOUNT =
	"select * from BOARDS where mem_num = ?";
	public static final String USER_DELETE_COMMENTCOUNT = 
	"select * from comments where mem_num = ?";
	
	public static final String USER_DELETE_FILE =
	"delete from file_db t1 where EXISTS (select 1 from BOARDS t2 where t1.board_num = t2.board_num AND t2.mem_num = ?)";
	public static final String USER_DELETE_COMMENT1 =
	"delete from comments where mem_num = ?";
	public static final String USER_DELETE_COMMENT2 =
	"delete from comments t1 where EXISTS (select 1 from BOARDS t2 where t1.board_num = t2.board_num AND t2.mem_num = ?)";
	public static final String USER_DELETE_RECOMMEND1 = 
	"delete from BOARDS_RECOMMEND t1 where EXISTS (select 1 from BOARDS t2 where t1.board_num = t2.board_num AND t2.mem_num = ?)";
	public static final String USER_DELETE_RECOMMEND2 = 
	"delete from BOARDS_RECOMMEND where mem_num = ?";
	public static final String USER_DELETE_WRITE =
	"delete from BOARDS where mem_num = ?";
	public static final String USER_DELETE = 
	"delete from MEMBERS where mem_num = ?";
	
}