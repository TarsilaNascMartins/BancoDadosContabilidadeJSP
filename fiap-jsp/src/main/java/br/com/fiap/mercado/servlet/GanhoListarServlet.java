package br.com.fiap.mercado.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.fiap.mercado.view.dao.GanhoDAO;
import br.com.fiap.mercado.view.model.Ganho;

/**
 * Servlet implementation class GanhoListarServlet
 */
@WebServlet("/GanhoListar")
public class GanhoListarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GanhoListarServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		GanhoDAO ganhoDAO = new GanhoDAO();		
		
		List<Ganho> ganho = ganhoDAO.getAll();
		
		request.setAttribute("ganho", ganho);
		request.getRequestDispatcher("ganho-lista.jsp").forward(request,response); 
	}

}
