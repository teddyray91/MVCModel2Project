package com.team3.front;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team3.command.AdminAliveMemberList;
import com.team3.command.AdminBoardList;
import com.team3.command.AdminMemberList;
import com.team3.command.AdminMemberChangeAlive;
import com.team3.command.MemberChangePw;
import com.team3.command.MemberFindId;
import com.team3.command.MemberFindPw;
import com.team3.command.CommentDelete;
import com.team3.command.CommentUpdate;
import com.team3.command.CommentWrite;
import com.team3.command.BoardList;
import com.team3.command.Recommend;
import com.team3.command.BoardWriteDelete;
import com.team3.command.BoardWrite;
import com.team3.command.BoardWriteUpdate;
import com.team3.command.BoardWriteUpdateOk;
import com.team3.command.BoardWriteView;
import com.team3.command.Command;
import com.team3.command.MemberDelete;
import com.team3.command.MemberDeleteView;
import com.team3.command.MemberFindModify;
import com.team3.command.Join;
import com.team3.command.Login;
import com.team3.command.JoinOverlapCheck;
import com.team3.command.MemberModify;
import com.team3.command.ViewMainContent;

@WebServlet({ "*.team3" })
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public FrontController() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		action(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		action(request, response);
	}
	protected void action(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("action Call()");
		request.setCharacterEncoding("utf-8");
		
		Command actionCommand = null;
		String viewPage = null;
		
		String uri = request.getRequestURI();
		String contextpath = request.getContextPath();
		String command = uri.substring(contextpath.length());
		System.out.println("URI : " + uri);
		System.out.println("ContextPath : " + contextpath);
		System.out.println("command : " + command);
		
		switch(command) {
		case "/index.team3":
			actionCommand = new ViewMainContent();
			actionCommand.execute(request, response);
			viewPage = "index.jsp";
			break;
		case "/idOverlap.team3":
			actionCommand = new JoinOverlapCheck();
			actionCommand.execute(request, response);
			break;
		case "/join.team3":
			viewPage = "layout/content/main/join.jsp";
			break;
		case "/joinOk.team3":
			actionCommand = new Join();
			actionCommand.execute(request, response);
			viewPage = "src/joinOk.jsp";
			break;
		case "/login.team3":
			actionCommand = new Login();
			actionCommand.execute(request, response);
			viewPage = "src/login.jsp";
			break;
		case "/logout.team3":
			viewPage = "src/logout.jsp";
			break;
		case "/noticeboard.team3":
			actionCommand = new BoardList();
			request.setAttribute("boardCategory", "notice");
			actionCommand.execute(request, response);
			viewPage = "index.jsp?page=noticeboard";
			break;
		case "/noticeWriteForm.team3":
			viewPage = "index.jsp?page=noticeboardWriteForm";
			break;
		case "/freeboard.team3":
			actionCommand = new BoardList();
			request.setAttribute("boardCategory", "free");
			actionCommand.execute(request, response);
			viewPage = "index.jsp?page=freeboard";
			break;
		case "/freeboardWriteForm.team3":
			viewPage = "index.jsp?page=freeboardWriteForm";
			break;
		case "/studyboard.team3":
			actionCommand = new BoardList();
			request.setAttribute("boardCategory", "study");
			actionCommand.execute(request, response);
			viewPage = "index.jsp?page=studyboard";
			break;
		case "/studyboardWriteForm.team3":
			viewPage = "index.jsp?page=studyboardWriteForm";
			break;
		case "/fileUpload.team3":
			viewPage = "layout/content/fileUpload.jsp";
			break;
		case "/partboard.team3":
			actionCommand = new BoardList();
			request.setAttribute("boardCategory", "part");
			actionCommand.execute(request, response);
			viewPage = "index.jsp?page=partboard";
			break;
		case "/partboardWriteForm.team3":
			viewPage = "index.jsp?page=partboardWriteForm";
			break;
		case "/jobboard.team3":
			actionCommand = new BoardList();
			request.setAttribute("boardCategory", "job");
			actionCommand.execute(request, response);
			viewPage = "index.jsp?page=jobboard";
			break;
		case "/jobboardWriteForm.team3":
			viewPage = "index.jsp?page=jobboardWriteForm";
			break;
		case "/WriteOk.team3": // 게시글 작성 확인 Action
			actionCommand = new BoardWrite();
			actionCommand.execute(request, response);
			viewPage = "src/WriteOk.jsp";
			break;
		case "/WriteView.team3": //게시글 상세보기 Action
			actionCommand = new BoardWriteView();
			actionCommand.execute(request, response);
			viewPage = "index.jsp?page=WriteView";
			break;
		case "/Update.team3": // 게시글 수정을 위한 불러오기 Action
			actionCommand = new BoardWriteUpdate();
			actionCommand.execute(request, response);
			viewPage = "index.jsp?page=Update";
			break;
		case "/UpdateOk.team3": // 게시글 수정확인 Action
			actionCommand = new BoardWriteUpdateOk();
			actionCommand.execute(request, response);
			viewPage = "src/UpdateOk.jsp";
			break;
		case "/Delete.team3": // 게시글 수정확인 Action
			actionCommand = new BoardWriteDelete();
			actionCommand.execute(request, response);
			viewPage = "src/DeleteOk.jsp";
			break;
		case "/BoardRecommend.team3": // 게시글 좋아요, 싫어요 증/감 Action
			actionCommand = new Recommend();
			actionCommand.execute(request, response);
			break;
		case "/WriteComment.team3": // 댓글작성 Action
			actionCommand = new CommentWrite();
			actionCommand.execute(request, response);
			break;
		case "/UpdateComment.team3": // 댓글수정 Action
			actionCommand = new CommentUpdate();
			actionCommand.execute(request, response);
			break;
		case "/DeleteComment.team3": // 댓글삭제 Action
			actionCommand = new CommentDelete();
			actionCommand.execute(request, response);
			break;
		case "/Find_Id.team3":
			viewPage = "layout/content/main/findid.jsp";
			break;
		case "/Action_Find_Id.team3":
			actionCommand = new MemberFindId();
			actionCommand.execute(request, response);
			viewPage = "layout/content/main/findid.jsp?findid_chk=1";
			break;
		case "/Find_Pw.team3":
			viewPage = "layout/content/main/findpw.jsp";
			break;
		case "/Action_Find_Pw.team3":
			actionCommand = new MemberFindPw();
			actionCommand.execute(request, response);
			viewPage = "layout/content/main/findpw.jsp?findpw_chk=1";
			break;
		case "/Action_Change_Pw.team3":
			actionCommand = new MemberChangePw();
			actionCommand.execute(request, response);
			viewPage = "layout/content/main/findpw.jsp?findpw_chk=2&change=1";
			break;
		case "/adminfrom.team3":
			actionCommand = new AdminMemberList();
			actionCommand.execute(request, response);
			viewPage = "index.jsp?page=admin&adminpage=memberlist";
			break;
		case "/changealive.team3":
			actionCommand = new AdminMemberChangeAlive();
			actionCommand.execute(request, response);
			viewPage = "src/ChangeAlive.jsp";
			break;
		case "/adminboard.team3":
			actionCommand = new AdminBoardList();
			actionCommand.execute(request, response);
			viewPage = "index.jsp?page=admin&adminpage=adminboard";
			break;
		case "/adminalive.team3":
			actionCommand = new AdminAliveMemberList();
			actionCommand.execute(request, response);
			viewPage = "index.jsp?page=admin&adminpage=memberalivelist";
			break;
		case "/memmodify.team3":
			actionCommand = new MemberFindModify();
			actionCommand.execute(request, response);
			viewPage = "index.jsp?page=usermodify";
			break;
		case "/membermodify.team3":
			actionCommand = new MemberModify();
			actionCommand.execute(request, response);
			viewPage = "src/updatemember.jsp";
			break;
		case "/memdelete.team3":
			actionCommand = new MemberDeleteView();
			actionCommand.execute(request, response);
			viewPage = "index.jsp?page=userdel";
			break;
		case "/memberdelete.team3":
			actionCommand = new MemberDelete();
			actionCommand.execute(request, response);
			viewPage = "src/memberdel.jsp";
			break;
		case "/error.team3":
			viewPage = "index.jsp?page=admin";
			break;
			default:
				actionCommand = new ViewMainContent();
				actionCommand.execute(request, response);
				viewPage = "index.jsp";
				break;
		}
	
		if(viewPage != null) {
			RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
			dispatcher.forward(request, response);
		}
		
	}

}
