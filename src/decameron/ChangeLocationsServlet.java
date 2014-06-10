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
 * Servlet implementation class ChangeLocationsServlet
 */
@WebServlet("/ChangeLocationsServlet")
public class ChangeLocationsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangeLocationsServlet() {
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
		int locs= st.getNumberLocations();
		st.removeLocations();
		for(int i=0; i< locs; i++){
			String name= request.getParameter("name" + i);
			String lat= request.getParameter("lat" + i);
			String lon= request.getParameter("lon" + i);
			if(!name.isEmpty() && !lat.isEmpty() && !lon.isEmpty()){
				st.addLocation(Double.parseDouble(lat), Double.parseDouble(lon), name);
			}
		}
		RequestDispatcher dispatch = request.getRequestDispatcher("mapPreview.jsp");
		dispatch.forward(request, response);
	}

}
