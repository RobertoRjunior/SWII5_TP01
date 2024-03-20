/*
 * Roberto Junior Cb3000419
 */

package gerenciador;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/SaveServlet")
public class SaveServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		String country = request.getParameter("country");

		if (name == "" || password == "" || email == "" || country == "") {
			out.println("<script>alert('Por favor, preencha todos os campos');</script>");
			out.println("<script>setTimeout(function(){ window.location.href = 'index.html'; }, 1000);</script>");
			return;
		}

		Emp e = new Emp();
		e.setName(name);
		e.setPassword(password);
		e.setEmail(email);
		e.setCountry(country);

		int status = EmpDao.save(e);
		if (status > 0) {
			out.println("<script>alert('Registro salvo com sucesso!');</script>");
			out.println("<script>setTimeout(function(){ window.location.href = 'index.html'; }, 1000);</script>");
		} else {
			out.println("<script>alert('Não foi possível salvar registro');</script>");
			out.println("<script>setTimeout(function(){ window.location.href = 'index.html'; }, 1000);</script>");
		}

		out.close();
	}

}
