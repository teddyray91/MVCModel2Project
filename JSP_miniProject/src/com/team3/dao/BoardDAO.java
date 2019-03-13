package com.team3.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.team3.constant.D;
import com.team3.dto.BoardCommentDTO;
import com.team3.dto.BoardDTO;

public class BoardDAO {
	
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;
	
	public BoardDAO() { }
	
	public Connection getConnection() throws NamingException, SQLException {
		Context init = new InitialContext();
		Context Env = (Context)init.lookup("java:/comp/env");
		DataSource ds = (DataSource)Env.lookup("jdbc/oracle");
		
		return ds.getConnection();
	}
	
	public int Insert_Boards_Write(int memNumber, String boardSubject, String boardName, String boardContent, String boardCategory) throws NamingException, SQLException {
		int count = 0;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(D.INSERT_BOARDS_WRITE);
			pstmt.setInt(1, memNumber);
			pstmt.setString(2, boardSubject);
			pstmt.setString(3, boardName);
			pstmt.setString(4, boardContent);
			pstmt.setString(5, boardCategory);
			
			count = pstmt.executeUpdate();
		} finally {
			Close();
		}
		
		return count;
	}
	
	public int Insert_Boards_File(int memNum, String boardSubject, String OriginalFile, String SystemFile) throws NamingException, SQLException {
		int count = 0;
		
		try {
			conn = getConnection();
			conn.setAutoCommit(false);
			
			pstmt = conn.prepareStatement(D.SELECT_BOARDS_FILE_CHECK);
			pstmt.setInt(1, memNum);
			pstmt.setString(2, boardSubject);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				int boardNum = rs.getInt("BOARD_NUM");
				pstmt.close();
				pstmt = conn.prepareStatement(D.INSERT_BOARDS_FILE);
				pstmt.setInt(1, boardNum);
				pstmt.setString(2, OriginalFile);
				pstmt.setString(3, SystemFile);
				count = pstmt.executeUpdate();
			}
			
			conn.commit();
		} catch (Exception e) {
			conn.rollback();
			throw e;
		} finally {
			Close();
		}
		
		return count;
	}
	
	public List<BoardDTO> Update_Boards(int boardNum) throws NamingException, SQLException {
		List<BoardDTO> list = new ArrayList<>();
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(D.SELECT_BOARDS_WRITE_VIEW);
			pstmt.setInt(1, boardNum);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				int memNum = rs.getInt("MEM_NUM");
				int boardNum2 = rs.getInt("BOARD_NUM");
				String boardSubject = rs.getString("BOARD_SUBJECT");
				String boardName = rs.getString("BOARD_NAME");
				String boardContent = rs.getString("BOARD_CONTENT");
				Date d = rs.getDate("BOARD_REGDATE");
				Time t = rs.getTime("BOARD_REGDATE");
				String boardRegDate = new SimpleDateFormat("yyyy-MM-dd").format(d) + " " + new SimpleDateFormat("hh:mm:ss").format(t);
				int boardLike = rs.getInt("BOARD_LIKE");
				int boardHate = rs.getInt("BOARD_HATE");
				int boardViewCount = rs.getInt("BOARD_VIEWCOUNT");
				String boardCategory = rs.getString("CATEGORY_ID");
				
				list.add(new BoardDTO(1, memNum, boardNum2, boardSubject, boardName, boardContent, boardRegDate, boardLike, boardHate, boardViewCount, boardCategory));
			}
		} finally {
			Close();
		}
		
		return list;
	}
	
	public int Update_Boards_Write(int boardNum, String boardSubject, String boardContent) throws NamingException, SQLException {
		int count = 0;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(D.UPDATE_BOARDS_WRITE);
			pstmt.setString(1, boardSubject);
			pstmt.setString(2, boardContent);
			pstmt.setInt(3, boardNum);
		
			count = pstmt.executeUpdate();
		} finally {
			Close();
		}
		
		return count;
	}
	
	public int Delete_Boards_Write(int boardNum) throws NamingException, SQLException {
		int count = 0;
		
		try {
			conn = getConnection();
			conn.setAutoCommit(false);
			
			pstmt = conn.prepareStatement(D.DELETE_BOARDS_COMMENT_WRITE);
			pstmt.setInt(1, boardNum);
			pstmt.executeUpdate();
			
			pstmt.close();
			pstmt = conn.prepareStatement(D.DELETE_BOARDS_FILE);
			pstmt.setInt(1, boardNum);
			pstmt.executeUpdate();
			
			pstmt.close();
			pstmt = conn.prepareStatement(D.DELETE_BOARDS_RECOMMEND);
			pstmt.setInt(1, boardNum);
			pstmt.executeUpdate();
			
			pstmt.close();
			pstmt = conn.prepareStatement(D.DELETE_BOARDS_WRITE);
			pstmt.setInt(1, boardNum);
			count = pstmt.executeUpdate();
			
			conn.commit();
		} catch(Exception e) {
			conn.rollback();
			throw e;
		} finally {
			Close();
		}
		
		return count;
	}
	
	public List<BoardDTO> Select_Boards_All(String BoardCategory, int pagenum, int viewwrite) throws NamingException, SQLException {
		List<BoardDTO> list = new ArrayList<>();
		
		try {			
			conn = getConnection();
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement("select count(*) totalwrite from boards where category_id =? ");
			pstmt.setString(1, BoardCategory);
			rs = pstmt.executeQuery();
			rs.next();
			int totalwrite = rs.getInt("totalwrite");
			rs.close();
			pstmt.close();
			if(pagenum == 0) {
				pstmt = conn.prepareStatement(D.SELECT_BOARDS_ALL2);
				pstmt.setString(1, BoardCategory);
			}else {
				pstmt = conn.prepareStatement(D.SELECT_BOARDS_ALL);
				pstmt.setString(1, BoardCategory);
				pstmt.setInt(2, totalwrite);
				pstmt.setInt(3, pagenum);
				pstmt.setInt(4, viewwrite);
				pstmt.setInt(5, viewwrite);
			}
			
			rs = pstmt.executeQuery();
			conn.commit();
			
			while(rs.next()) {
				String boardRegDate ="";
				int rowNum = rs.getInt("ROWNUM");
				int memNum = rs.getInt("MEM_NUM");
				int boardNum = rs.getInt("BOARD_NUM");
				String boardSubject = rs.getString("BOARD_SUBJECT");
				String boardName = rs.getString("BOARD_NAME");
				String boardContent = rs.getString("BOARD_CONTENT");
				Date d = rs.getDate("BOARD_REGDATE");
				Time t = rs.getTime("BOARD_REGDATE");
				if(new SimpleDateFormat("yyyy-MM-dd").format(d).equals(new SimpleDateFormat("yyyy-MM-DD").format(new Date()))) {
					boardRegDate = new SimpleDateFormat("HH:mm").format(t);
				}else {
					boardRegDate = new SimpleDateFormat("yyyy-MM-dd").format(d);
				}
				int boardLike = rs.getInt("BOARD_LIKE");
				int boardHate = rs.getInt("BOARD_HATE");
				int boardViewCount = rs.getInt("BOARD_VIEWCOUNT");
				String boardCategory = rs.getString("CATEGORY_ID");
				
				list.add(new BoardDTO(rowNum, memNum, boardNum, boardSubject, boardName, boardContent, boardRegDate, boardLike, boardHate, boardViewCount, boardCategory));
			}
		} finally {
			Close();
		}
		
		return list;
	}
	
	public List<BoardDTO> Select_Boards_Write_View(int boardNumber) throws NamingException, SQLException {
		List<BoardDTO> list = new ArrayList<>();
		
		try {
			conn = getConnection();
			conn.setAutoCommit(false);
			
			pstmt = conn.prepareStatement(D.UPDATE_BOARDS_VIEWCOUNT);
			pstmt.setInt(1, boardNumber);
			
			pstmt.executeUpdate();
			
			pstmt.close();
			pstmt = conn.prepareStatement(D.SELECT_BOARDS_WRITE_VIEW);
			pstmt.setInt(1, boardNumber);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				int memNum = rs.getInt("MEM_NUM");
				int boardNum = rs.getInt("BOARD_NUM");
				String boardSubject = rs.getString("BOARD_SUBJECT");
				String boardName = rs.getString("BOARD_NAME");
				String boardContent = rs.getString("BOARD_CONTENT");
				Date d = rs.getDate("BOARD_REGDATE");
				Time t = rs.getTime("BOARD_REGDATE");
				String boardRegDate = new SimpleDateFormat("yyyy-MM-dd").format(d) + " " + new SimpleDateFormat("hh:mm:ss").format(t);
				int boardLike = rs.getInt("BOARD_LIKE");
				int boardHate = rs.getInt("BOARD_HATE");
				int boardViewCount = rs.getInt("BOARD_VIEWCOUNT");
				String boardCategory = rs.getString("CATEGORY_ID");

				list.add(new BoardDTO(1, memNum, boardNum, boardSubject, boardName, boardContent, boardRegDate, boardLike, boardHate, boardViewCount, boardCategory));
			}
			
			conn.commit();
		} catch (Exception e) {
			conn.rollback();
			throw e;
		} finally {
			Close();
		}
		
		return list;
	}
	
	public BoardDTO Insert_Boards_Recommend (int boardNumber, int memNumber, String Type) throws SQLException, NamingException {
		BoardDTO dto = new BoardDTO();
		
		try {
			conn = getConnection();
			conn.setAutoCommit(false);
			
			pstmt = conn.prepareStatement(D.SELECT_BOARDS_RECOMMEND);
			pstmt.setInt(1, boardNumber);
			pstmt.setInt(2, memNumber);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				int LikeCount = rs.getInt("like_count");
				int HateCount = rs.getInt("hate_count");
				
				if(LikeCount == 1 && Type.equals("Hate")) {
					return null;
				}
				if(HateCount == 1 && Type.equals("Like")) {
					return null;
				}
				
				if(LikeCount == 1 && Type.equals("Like")) {
					pstmt.close();
					pstmt = conn.prepareStatement(D.UPDATE_BOARDS_RECOMMEND_LIKE);
					pstmt.setInt(1, 0);
					pstmt.setInt(2, boardNumber);
					pstmt.setInt(3, memNumber);
					int count = pstmt.executeUpdate();
						if(count == 1) {
							pstmt.close();
							pstmt = conn.prepareStatement(D.UPDATE_BOARDS_LIKE_DOWN);
							pstmt.setInt(1, boardNumber);
							int count2 = pstmt.executeUpdate();
							
							if(count2 == 1) {
								rs.close();
								pstmt.close();
								pstmt = conn.prepareStatement(D.SELECT_BOARDS_WRITE_VIEW);
								pstmt.setInt(1, boardNumber);
								rs = pstmt.executeQuery();
								
									if(rs.next()) {
										int Like = rs.getInt("board_like");
										int Hate = rs.getInt("board_hate");
										
										dto.setBoardLike(Like);
										dto.setBoardHate(Hate);
									}
							}
						}
				} else if(LikeCount == 0 && Type.equals("Like")) {
					pstmt.close();
					pstmt = conn.prepareStatement(D.UPDATE_BOARDS_RECOMMEND_LIKE);
					pstmt.setInt(1, 1);
					pstmt.setInt(2, boardNumber);
					pstmt.setInt(3, memNumber);
					int count = pstmt.executeUpdate();
						if(count == 1) {
							pstmt.close();
							pstmt = conn.prepareStatement(D.UPDATE_BOARDS_LIKE_UP);
							pstmt.setInt(1, boardNumber);
							int count2 = pstmt.executeUpdate();
							
							if(count2 == 1) {
								rs.close();
								pstmt.close();
								pstmt = conn.prepareStatement(D.SELECT_BOARDS_WRITE_VIEW);
								pstmt.setInt(1, boardNumber);
								rs = pstmt.executeQuery();
								
									if(rs.next()) {
										int Like = rs.getInt("board_like");
										int Hate = rs.getInt("board_hate");
										
										dto.setBoardLike(Like);
										dto.setBoardHate(Hate);
									}
							}
						}
				}
				if(HateCount == 1 && Type.equals("Hate")) {
					pstmt.close();
					pstmt = conn.prepareStatement(D.UPDATE_BOARDS_RECOMMEND_HATE);
					pstmt.setInt(1, 0);
					pstmt.setInt(2, boardNumber);
					pstmt.setInt(3, memNumber);
					int count = pstmt.executeUpdate();
						if(count == 1) {
							pstmt.close();
							pstmt = conn.prepareStatement(D.UPDATE_BOARDS_HATE_DOWN);
							pstmt.setInt(1, boardNumber);
							int count2 = pstmt.executeUpdate();
							
							if(count2 == 1) {
								rs.close();
								pstmt.close();
								pstmt = conn.prepareStatement(D.SELECT_BOARDS_WRITE_VIEW);
								pstmt.setInt(1, boardNumber);
								rs = pstmt.executeQuery();
								
									if(rs.next()) {
										int Like = rs.getInt("board_like");
										int Hate = rs.getInt("board_hate");
										
										dto.setBoardLike(Like);
										dto.setBoardHate(Hate);
									}
							}
						}
				} else if(HateCount == 0 && Type.equals("Hate")) {
					pstmt.close();
					pstmt = conn.prepareStatement(D.UPDATE_BOARDS_RECOMMEND_HATE);
					pstmt.setInt(1, 1);
					pstmt.setInt(2, boardNumber);
					pstmt.setInt(3, memNumber);
					int count = pstmt.executeUpdate();
						if(count == 1) {
							pstmt.close();
							pstmt = conn.prepareStatement(D.UPDATE_BOARDS_HATE_UP);
							pstmt.setInt(1, boardNumber);
							int count2 = pstmt.executeUpdate();
							
							if(count2 == 1) {
								rs.close();
								pstmt.close();
								pstmt = conn.prepareStatement(D.SELECT_BOARDS_WRITE_VIEW);
								pstmt.setInt(1, boardNumber);
								rs = pstmt.executeQuery();
								
									if(rs.next()) {
										int Like = rs.getInt("board_like");
										int Hate = rs.getInt("board_hate");
										
										dto.setBoardLike(Like);
										dto.setBoardHate(Hate);
									}
							}
						}
				}
			} else {
				if(Type.equals("Like")) {
					pstmt.close();
					pstmt = conn.prepareStatement(D.INSERT_BOARDS_RECOMMEND);
					pstmt.setInt(1, boardNumber);
					pstmt.setInt(2, memNumber);
					pstmt.setInt(3, 1);
					pstmt.setInt(4, 0);
					int count = pstmt.executeUpdate();
						if(count == 1) {
							pstmt.close();
							pstmt = conn.prepareStatement(D.UPDATE_BOARDS_LIKE_UP);
							pstmt.setInt(1, boardNumber);
							int count2 = pstmt.executeUpdate();
							
							if(count2 == 1) {
								rs.close();
								pstmt.close();
								pstmt = conn.prepareStatement(D.SELECT_BOARDS_WRITE_VIEW);
								pstmt.setInt(1, boardNumber);
								rs = pstmt.executeQuery();
								
									if(rs.next()) {
										int Like = rs.getInt("board_like");
										int Hate = rs.getInt("board_hate");
										
										dto.setBoardLike(Like);
										dto.setBoardHate(Hate);
									}
							}
						}
				} else if(Type.equals("Hate")) {
					pstmt.close();
					pstmt = conn.prepareStatement(D.INSERT_BOARDS_RECOMMEND);
					pstmt.setInt(1, boardNumber);
					pstmt.setInt(2, memNumber);
					pstmt.setInt(3, 0);
					pstmt.setInt(4, 1);
					int count = pstmt.executeUpdate();
						if(count == 1) {
							pstmt.close();
							pstmt = conn.prepareStatement(D.UPDATE_BOARDS_HATE_UP);
							pstmt.setInt(1, boardNumber);
							int count2 = pstmt.executeUpdate();
							
							if(count2 == 1) {
								rs.close();
								pstmt.close();
								pstmt = conn.prepareStatement(D.SELECT_BOARDS_WRITE_VIEW);
								pstmt.setInt(1, boardNumber);
								rs = pstmt.executeQuery();
								
									if(rs.next()) {
										int Like = rs.getInt("board_like");
										int Hate = rs.getInt("board_hate");
										
										dto.setBoardLike(Like);
										dto.setBoardHate(Hate);
									}
							}
						}
				}
			}
			
			conn.commit();
		} catch(Exception e) {
			conn.rollback();
			throw e;
		} finally {
			Close();
		}

		return dto;
	}
	
	public List<BoardCommentDTO> Insert_Select_Comment(int memNumber, int boardNumber, String commentName, String commentContent) throws NamingException, SQLException {
		List<BoardCommentDTO> list = new ArrayList<>();
		int count = 0;
		
		try {
			conn = getConnection();
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(D.INSERT_BOARDS_COMMENT_WRITE);
			pstmt.setInt(1, memNumber);
			pstmt.setInt(2, boardNumber);
			pstmt.setString(3, commentContent);
			pstmt.setString(4, commentName);
			
			count = pstmt.executeUpdate();
			
			if(count == 1) {
				pstmt.close();
				pstmt = conn.prepareStatement(D.SELECT_BOARDS_COMMENT_LIST);
				pstmt.setInt(1, boardNumber);
				
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					int memNum = rs.getInt("MEM_NUM");
					int boardNum = rs.getInt("BOARD_NUM");
					int commentNum = rs.getInt("COMMENT_NUM");
					String commentName2 = rs.getString("COMMENT_NAME");
					String commentCon = rs.getString("COMMENT_CONTENT");
					Date d = rs.getDate("COMMENT_REGDATE");
					Time t = rs.getTime("COMMENT_REGDATE");
					String commentRegDate = new SimpleDateFormat("yyyy-MM-dd").format(d) + " " + new SimpleDateFormat("hh:mm:ss").format(t);
					
					list.add(new BoardCommentDTO(memNum, boardNum, commentNum, commentName2, commentCon, commentRegDate));
				}
			}
			
			conn.commit();
		} catch (Exception e) {
			conn.rollback();
			throw e;
		} finally {
			Close();
		}
		
		return list;
	}
	
	public List<BoardCommentDTO> Select_Comment(int boardNumber) throws NamingException, SQLException {
		List<BoardCommentDTO> list = new ArrayList<>();
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(D.SELECT_BOARDS_COMMENT_LIST);
			pstmt.setInt(1, boardNumber);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				int memNum = rs.getInt("MEM_NUM");
				int boardNum = rs.getInt("BOARD_NUM");
				int commentNum = rs.getInt("COMMENT_NUM");
				String commentName = rs.getString("COMMENT_NAME");
				String commentCon = rs.getString("COMMENT_CONTENT");
				Date d = rs.getDate("COMMENT_REGDATE");
				Time t = rs.getTime("COMMENT_REGDATE");
				String commentRegDate = new SimpleDateFormat("yyyy-MM-dd").format(d) + " " + new SimpleDateFormat("hh:mm:ss").format(t);
				
				list.add(new BoardCommentDTO(memNum, boardNum, commentNum, commentName, commentCon, commentRegDate));
			}
		} finally {
			Close();
		}
		
		return list;
	}
	
	public List<BoardCommentDTO> Update_Comment(int commentNumber, String commentContent) throws NamingException, SQLException {
		List<BoardCommentDTO> list = new ArrayList<>();
		int count = 0;
		
		try {
			conn = getConnection();
			conn.setAutoCommit(false);
			
			pstmt = conn.prepareStatement(D.UPDATE_BOARDS_COMMENT_WRITE);
			pstmt.setString(1, commentContent);
			pstmt.setInt(2, commentNumber);
			count = pstmt.executeUpdate();
			
			if(count == 1) {
				pstmt.close();
				pstmt = conn.prepareStatement(D.SELECT_BOARDS_COMMENT_UPDATE);
				pstmt.setInt(1, commentNumber);
				
				rs = pstmt.executeQuery();
				if(rs.next()) {
					int memNum = rs.getInt("MEM_NUM");
					int boardNum = rs.getInt("BOARD_NUM");
					int commentNum = rs.getInt("COMMENT_NUM");
					String commentContent2 = rs.getString("COMMENT_CONTENT");
					Date d = rs.getDate("COMMENT_REGDATE");
					Time t = rs.getTime("COMMENT_REGDATE");
					String commentRegDate = new SimpleDateFormat("yyyy-MM-dd").format(d) + " " + new SimpleDateFormat("hh:mm:ss").format(t);
					String commentName = rs.getString("COMMENT_NAME");
					
					list.add(new BoardCommentDTO(memNum, boardNum, commentNum, commentName, commentContent2, commentRegDate));
				}
			}
			conn.commit();
		} catch (Exception e) {
			conn.rollback();
			throw e;
		} finally {
			Close();
		}
				
		return list;
	}
	
	public List<BoardCommentDTO> Delete_Comment(int commentNumber, int boardNumber) throws NamingException, SQLException {
		List<BoardCommentDTO> list = new ArrayList<>();
		int count = 0;
		
		try {
			conn = getConnection();
			conn.setAutoCommit(false);
			
			pstmt = conn.prepareStatement(D.DELETE_BOARDS_COMMENT_WRITE);
			pstmt.setInt(1, commentNumber);
			
			count = pstmt.executeUpdate();
			
			if(count == 1) {
				pstmt.close();
				pstmt = conn.prepareStatement(D.SELECT_BOARDS_COMMENT_LIST);
				pstmt.setInt(1, boardNumber);
				
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					int memNum = rs.getInt("MEM_NUM");
					int boardNum = rs.getInt("BOARD_NUM");
					int commentNum = rs.getInt("COMMENT_NUM");
					String commentName = rs.getString("COMMENT_NAME");
					String commentCon = rs.getString("COMMENT_CONTENT");
					Date d = rs.getDate("COMMENT_REGDATE");
					Time t = rs.getTime("COMMENT_REGDATE");
					String commentRegDate = new SimpleDateFormat("yyyy-MM-dd").format(d) + " " + new SimpleDateFormat("hh:mm:ss").format(t);
					
					list.add(new BoardCommentDTO(memNum, boardNum, commentNum, commentName, commentCon, commentRegDate));
				}
			}
			
			conn.commit();
		} catch(Exception e) {
			conn.rollback();
			throw e;
		} finally {
			Close();
		}
		
		return list;
	}
	
	public List<BoardDTO> AdminBoardList(String category, int pagenum, int viewwrite) throws NamingException, SQLException {
		List<BoardDTO> list = new ArrayList<>();
		
		try {			
			conn = getConnection();
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement("select count(*) totalwrite from boards where category_id LIKE ? ");
			if(category.equals("all")) pstmt.setString(1, "%");
			else if(category.equals("free")) pstmt.setString(1, "free");
			else if(category.equals("notice")) pstmt.setString(1, "notice");
			else if(category.equals("job")) pstmt.setString(1, "job");
			else if(category.equals("part")) pstmt.setString(1, "part");
			else if(category.equals("study")) pstmt.setString(1, "study");
			rs = pstmt.executeQuery();
			rs.next();
			int totalwrite = rs.getInt("totalwrite");
			rs.close();
			pstmt.close();
			
			if(pagenum == 0) {
				pstmt = conn.prepareStatement(D.ALL_SELECT_BOARD);
				if(category.equals("all")) pstmt.setString(1, "%");
				else if(category.equals("free")) pstmt.setString(1, "free");
				else if(category.equals("notice")) pstmt.setString(1, "notice");
				else if(category.equals("job")) pstmt.setString(1, "job");
				else if(category.equals("part")) pstmt.setString(1, "part");
				else if(category.equals("study")) pstmt.setString(1, "study");
			}else {
				pstmt = conn.prepareStatement(D.ALL_SELECT_BOARD2);
				if(category.equals("all")) pstmt.setString(1, "%");
				else if(category.equals("free")) pstmt.setString(1, "free");
				else if(category.equals("notice")) pstmt.setString(1, "notice");
				else if(category.equals("job")) pstmt.setString(1, "job");
				else if(category.equals("part")) pstmt.setString(1, "part");
				else if(category.equals("study")) pstmt.setString(1, "study");
				pstmt.setInt(2, totalwrite);
				pstmt.setInt(3, pagenum);
				pstmt.setInt(4, viewwrite);
				pstmt.setInt(5, viewwrite);
			}
			
			rs = pstmt.executeQuery();
			conn.commit();
			
			while(rs.next()) {
				String boardRegDate = "";
				int rowNum = rs.getInt("ROWNUM");
				int memNum = rs.getInt("MEM_NUM");
				int boardNum = rs.getInt("BOARD_NUM");
				String boardSubject = rs.getString("BOARD_SUBJECT");
				String boardName = rs.getString("BOARD_NAME");
				String boardContent = rs.getString("BOARD_CONTENT");
				Date d = rs.getDate("BOARD_REGDATE");
				Time t = rs.getTime("BOARD_REGDATE");
				if(new SimpleDateFormat("yyyy-MM-dd").format(d).equals(new SimpleDateFormat("yyyy-MM-DD").format(new Date()))) {
					boardRegDate = new SimpleDateFormat("HH:mm").format(t);
				}else {
					boardRegDate = new SimpleDateFormat("yyyy-MM-dd").format(d);
				}
				int boardLike = rs.getInt("BOARD_LIKE");
				int boardHate = rs.getInt("BOARD_HATE");
				int boardViewCount = rs.getInt("BOARD_VIEWCOUNT");
				String boardCategory = rs.getString("CATEGORY_ID");
				
				list.add(new BoardDTO(rowNum, memNum, boardNum, boardSubject, boardName, boardContent, boardRegDate, boardLike, boardHate, boardViewCount, boardCategory));
			}
		} finally {
			Close();
		}
		
		return list;
	}
	
	public List<BoardDTO> MainContentList(String content) throws NamingException, SQLException {
		List<BoardDTO> list = new ArrayList<>();
		
		try {			
			conn = getConnection();
			if(content.equals("hit")) {
				pstmt = conn.prepareStatement(D.HIT_SELECT_BOARD);
				rs = pstmt.executeQuery();
			}else {
				pstmt = conn.prepareStatement(D.ALL_SELECT_BOARD);
				pstmt.setString(1, content);
				rs = pstmt.executeQuery();
			}
			
			while(rs.next()) {
				int rowNum = rs.getInt("ROWNUM");
				int memNum = rs.getInt("MEM_NUM");
				int boardNum = rs.getInt("BOARD_NUM");
				String boardSubject = rs.getString("BOARD_SUBJECT");
				String boardName = rs.getString("BOARD_NAME");
				String boardContent = rs.getString("BOARD_CONTENT");
				Date d = rs.getDate("BOARD_REGDATE");
				Time t = rs.getTime("BOARD_REGDATE");
				String boardRegDate = new SimpleDateFormat("yyyy-MM-dd").format(d) + " " + new SimpleDateFormat("hh:mm:ss").format(t);
				int boardLike = rs.getInt("BOARD_LIKE");
				int boardHate = rs.getInt("BOARD_HATE");
				int boardViewCount = rs.getInt("BOARD_VIEWCOUNT");
				String boardCategory = rs.getString("CATEGORY_ID");
				
				list.add(new BoardDTO(rowNum, memNum, boardNum, boardSubject, boardName, boardContent, boardRegDate, boardLike, boardHate, boardViewCount, boardCategory));
			}
			conn.commit();
		} finally {
			Close();
		}
		
		return list;
	}
	
	public int UserDeleteWriteView(int memnum) throws NamingException, SQLException{
		int count = 0;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(D.USER_DELETE_WRITECOUNT);
			pstmt.setInt(1, memnum);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				count++;
			}
			
			return count;
		}finally {
			Close();
		}
	}
	public int UserDeleteCommentView(int memnum) throws NamingException, SQLException{
		int count = 0;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(D.USER_DELETE_COMMENTCOUNT);
			pstmt.setInt(1, memnum);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				count++;
			}
			
			return count;
		}finally {
			Close();
		}
	}
	
	void Close() throws SQLException {
		if(rs != null) { rs.close(); }
		if(pstmt != null) { pstmt.close(); }
		if(conn != null) { conn.close(); }
	}
	
}