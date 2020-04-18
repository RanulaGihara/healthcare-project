package model;

import java.sql.*;

public class payments {

	public Connection connect() {
		Connection con = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/healthcare_app", "root", "");
			// For testing
			System.out.print("Successfully connected");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return con;

	}

	// insert method which insert data to payments table
	public String insertPayment(String payment_method, String amount, String date, String commission,
			String credit_card_num) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database";
			}

			String query = " insert into payments(`id`,`payment_method`,`amount`,`date`,`commission`,`credit_card_num`)"
					+ " values (?, ?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);

			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, payment_method);
			preparedStmt.setDouble(3, Double.parseDouble(amount));
			preparedStmt.setString(4, date);
			preparedStmt.setDouble(5, Double.parseDouble(commission));
			preparedStmt.setString(6, credit_card_num);

			preparedStmt.execute();
			con.close();
			output = "Inserted successfully";
		} catch (Exception e) {
			output = "Error while inserting";
			System.err.println(e.getMessage());
		}
		return output;
	}

	// View inserted data
	public String readItems() {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}

			// displaying html table
			output = "<table border=\"1\"><tr><th>payment method</th><th>amount</th><th>date</th><th>commission</th><th>credit card number</th><th>Update</th><th>Delete</th></tr>";
			String query = "select * from payments";  
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			// iterate through the rows in the result set
			while (rs.next()) {
				String id = Integer.toString(rs.getInt("id"));
				String payment_method = rs.getString("payment_method");
				String amount = Double.toString(rs.getDouble("amount"));
				String date = rs.getString("date");
				String commission = Double.toString(rs.getDouble("commission"));
				String credit_card_num = rs.getString("credit_card_num");

				// Add into the html table
				output += "<tr><td>" + payment_method + "</td>";
				output += "<td>" + amount + "</td>";
				output += "<td>" + date + "</td>";
				output += "<td>" + commission + "</td>";
				output += "<td>" + credit_card_num + "</td>";

				// buttons
				output += "<td><input name=\"btnUpdate\" type=\"button\" value=\"Update\" class=\"btn btn-secondary\"></td>"
						+ "<td><form method=\"post\" action=\"items.jsp\">"
						+ "<input name=\"btnRemove\" type=\"submit\" value=\"Deletex\"class=\"btn btn-danger\">"
						+ "<input name=\"itemID\" type=\"hidden\" value=\"" + id + "\">" + "</form></td></tr>";
			}
			con.close();
			// Complete the html table
			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading the items.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	// update payments table
	public String updatePayment(String id, String payment_method, String amount, String date, String commission,
			String credit_card_num) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for updating.";
			}

			String query = "UPDATE payments SET payment_method=?,amount=?,date=?,commission=?,credit_card_num=? WHERE id=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);

			preparedStmt.setString(1, payment_method);
			preparedStmt.setDouble(2, Double.parseDouble(amount));
			preparedStmt.setString(3, date);
			preparedStmt.setDouble(4, Double.parseDouble(commission));
			preparedStmt.setString(5, credit_card_num);
			preparedStmt.setInt(6, Integer.parseInt(id));

			preparedStmt.execute();
			con.close();
			output = "Updated successfully";
		} catch (Exception e) {
			output = "Error while updating the item.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	//deleting table
	public String deleteItem(String id) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}
			// create a prepared statement
			String query = "delete from payments where id=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(id));
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Deleted successfully";
		} catch (Exception e) {
			e.printStackTrace();
			output = "Error while deleting the item.";
			System.err.println(e.getMessage());
		}
		return output;
	}
}
