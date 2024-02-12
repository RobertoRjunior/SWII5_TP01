/*
 * Roberto Junior*/

package gerenciador;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/EditServlet")
public class EditServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<body style=\"font-family: Arial, sans-serif; background-color: #F5F5F5; text-align: center;\">");
		out.println(
				"<h1 style=\"color: #4CAF50; font-size: 36px; margin-top: 25px\">Atualizar dados do funcionário</h1>");
		String sid = request.getParameter("id");
		int id = Integer.parseInt(sid);

		Emp e = EmpDao.getEmployeeById(id);

		out.print("<form action='EditServlet2' method='post'>");
		out.print(
				"<div style=\"display: flex; justify-content: center; align-items: center; flex-direction: column;\">");
		out.print("<table>");
		out.print("<tr><td></td><td><input type='hidden' name='id' value='" + e.getId() + "'/></td></tr>");
		out.print("<tr><td>Name:</td><td><input type='text' name='name' value='" + e.getName()
				+ "' style=\"width: 150px\"/></td></tr>");
		out.print("<tr><td>Password:</td><td><input type='password' name='password' value='" + e.getPassword()
				+ "' style=\"width: 150px\"/></td></tr>");
		out.print("<tr><td>Email:</td><td><input type='email' name='email' value='" + e.getEmail()
				+ "' style=\"width: 150px\"/></td></tr>");
		out.print("<tr><td>Country:</td><td>");
		out.print("<select name='country' style='width:150px'>");
		out.print("<option>India</option>");
		out.print("<option>USA</option>");
		out.print("<option>UK</option>");
		out.print("<option>Other</option>");
		out.print("</select>");
		out.print("</td></tr>");
		out.print("<tr>\r\n" + "  <td colspan='2'>\r\n"
				+ "    <div style=\"display: flex; justify-content: center; text-align: center; gap: 5px\">\r\n"
				+ "      <input type='reset' onclick='window.location.href=\"index.html\"' value='Cancelar' style=\"padding: 10px; border-radius: 5px; background-color: #333333; color: white; border: none; cursor: pointer; font-size: 16px; margin-top: 20px\"/>\r\n"
				+ "      <input type='submit' value='Salvar' style=\"padding: 10px; border-radius: 5px; background-color: #4CAF50; color: white; border: none; cursor: pointer; font-size: 16px; margin-top: 20px\"/>\r\n"
				+ "    </div>\r\n" + "  </td>\r\n" + "</tr>\r\n");
		out.print("</table>");
		out.print("</div>");
		out.print("</form>");
		out.print("</body>");

		out.close();
	}
}
