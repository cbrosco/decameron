package decameron;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.HttpSession;
import javax.servlet.RequestDispatcher;
/**
 * Servlet implementation class LocationServlet
 */
@WebServlet("/LocationServlet")
public class LocationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LocationServlet() {
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
		String num= (String)session.getAttribute("numLocations");
		Story st= (Story)session.getAttribute("story");
		int locs= Integer.parseInt(num);
		for(int i=0; i< locs; i++){
			String name= request.getParameter("name" + i);
			String lat= request.getParameter("lat" + i);
			String lon= request.getParameter("lon" + i);
			if(!name.isEmpty() && !lat.isEmpty() && !lon.isEmpty()){
				try{
				double latitude= Double.parseDouble(lat);
				double longitude= Double.parseDouble(lon);
				st.addLocation(latitude, longitude, name);
				} catch (NumberFormatException e){
					session.setAttribute("error", ErrorTypes.NOT_A_COORDINATE);
					RequestDispatcher dispatch = request.getRequestDispatcher("location.jsp");
					dispatch.forward(request, response);
					return;
				}
			}
		}
		RequestDispatcher dispatch = request.getRequestDispatcher("mapPreview.jsp");
		dispatch.forward(request, response);
	}

}
