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
 * Servlet implementation class SaveMapServlet
 */
@WebServlet("/SaveMapServlet")
public class SaveMapServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SaveMapServlet() {
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
		Story st= (Story)session.getAttribute("story");
		st.updateExtraInfo(request.getParameter("extra"));
		boolean saved= st.addStoryToDB();
		
		if(saved){
		RequestDispatcher dispatch = request.getRequestDispatcher("saveSuccessful.jsp");
		dispatch.forward(request, response);
		} else{
			session.setAttribute("story", null);
			RequestDispatcher dispatch = request.getRequestDispatcher("saveFailed.jsp");
			dispatch.forward(request, response);
		}
		
	}

}
