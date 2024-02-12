/*
 *Roberto Junior*/

package gerenciador;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ViewServlet")
public class ViewServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<body style=\"font-family: Arial, sans-serif; background-color: #F5F5F5; text-align: center;\">");

		out.println("<h1 style=\"color: #4CAF50; font-size: 36px; margin-top: 25px\">Lista de funcionários</h1>");

		List<Emp> list = EmpDao.getAllEmployees();

		out.print("<table border='1' width='100%'");
		out.print(
				"<tr><th>Id</th><th>Nome</th><th>Senha</th><th>Email</th><th>País</th><th>Editar</th><th>Remover</th></tr>");
		for (Emp e : list) {
			out.print("<tr><td>" + e.getId() + "</td><td>" + e.getName() + "</td><td>" + e.getPassword() + "</td><td>"
					+ e.getEmail() + "</td><td>" + e.getCountry() + "</td><td><a href='EditServlet?id=" + e.getId()
					+ "'>edit</a></td><td><a href='DeleteServlet?id=" + e.getId() + "'>delete</a></td></tr>");
		}
		out.print("</table>");
		out.println(
				"<input onclick='window.location.href=\"index.html\"' value='Adicionar novo funcionário' style=\"padding: 10px; border-radius: 5px; background-color: #4CAF50; color: white; border: none; cursor: pointer; font-size: 16px; margin-top: 20px\"/>");

		out.print("</body>");

		out.close();
	}
}
