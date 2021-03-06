package decameron;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class SelectServlet
 */
@WebServlet("/SelectServlet")
public class SelectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SelectServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		int giorno =Story.ALL;
		int number= Story.NA;		//if "N/A" selected
		if(!request.getParameter("giorno").equals("all")) giorno = Integer.parseInt(request.getParameter("giorno"));
		if(request.getParameter("number").equals("all")){
			number= Story.ALL;
		}else if(!request.getParameter("number").equals("N/A")){
			number = Integer.parseInt(request.getParameter("number"));
		}
		Story st= new Story(giorno, request.getParameter("teller"), number);
		if(st.getID() == ErrorTypes.STORY_NOT_YET_CREATED){
			RequestDispatcher dispatch = request.getRequestDispatcher("noStory.jsp");
			dispatch.forward(request, response);
			return;
		}
		session.setAttribute("story", st);
		RequestDispatcher dispatch = request.getRequestDispatcher("mapAndInfo.jsp?storyID=" +st.getID());
		dispatch.forward(request, response);
	}

}
