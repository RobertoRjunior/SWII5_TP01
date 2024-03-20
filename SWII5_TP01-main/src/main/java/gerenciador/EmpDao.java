/*
 * Roberto Junior
 */

package gerenciador;

import java.util.*;
import java.sql.*;

public class EmpDao {
	public static Connection getConnection() {
		Connection con = null;
		try {

			String url = "jdbc:sqlserver://localhost\\MSSQLSERVER;databaseName=novoFuncionario;encrypt=true;trustServerCertificate=true;integratedSecurity=true";
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con = DriverManager.getConnection(url);
		} catch (Exception e) {
			System.out.println(e);
		}
		return con;
	}

	public static int save(Emp e) {
		int status = 0;
		
		try {
			Connection con = EmpDao.getConnection();
			if (con != null) {
				PreparedStatement ps = con
						.prepareStatement("INSERT INTO funcionario (name, password, email, country) VALUES (?,?,?,?)");
				ps.setString(1, e.getName());
				ps.setString(2, e.getPassword());
				ps.setString(3, e.getEmail());
				ps.setString(4, e.getCountry());
				status = ps.executeUpdate();
				con.close();
			} else {
				System.out.println("Connection is null!");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return status;
	}

	public static int update(Emp e) {
		int status = 0;
		try {
			Connection con = EmpDao.getConnection();
			PreparedStatement ps = con
					.prepareStatement("UPDATE funcionario SET name = ?, password = ?, email = ?, country = ? WHERE id = ?;");
			ps.setString(1, e.getName());
			ps.setString(2, e.getPassword());
			ps.setString(3, e.getEmail());
			ps.setString(4, e.getCountry());
			ps.setInt(5, e.getId());

			status = ps.executeUpdate();

			con.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return status;
	}

	public static int delete(int id) {
		int status = 0;
		try {
			Connection con = EmpDao.getConnection();
			PreparedStatement ps = con.prepareStatement("DELETE FROM funcionario WHERE id = ?");
			ps.setInt(1, id);
			status = ps.executeUpdate();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return status;
	}

	public static Emp getEmployeeById(int id) {
		Emp e = new Emp();

		try {
			Connection con = EmpDao.getConnection();
			PreparedStatement ps = con.prepareStatement("SELECT * FROM funcionario WHERE id = ?;");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				e.setId(rs.getInt(1));
				e.setName(rs.getString(2));
				e.setPassword(rs.getString(3));
				e.setEmail(rs.getString(4));
				e.setCountry(rs.getString(5));
			}
			con.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return e;
	}

	public static List<Emp> getAllEmployees() {
		List<Emp> list = new ArrayList<Emp>();

		try {
			Connection con = EmpDao.getConnection();
			PreparedStatement ps = con.prepareStatement("SELECT * FROM funcionario");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Emp e = new Emp();
				e.setId(rs.getInt(1));
				e.setName(rs.getString(2));
				e.setPassword(rs.getString(3));
				e.setEmail(rs.getString(4));
				e.setCountry(rs.getString(5));
				list.add(e);
			}
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}
}
