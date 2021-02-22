package Main;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class DatabaseScores {

    String connectionString;
    String user;
	String pass;
	
	public DatabaseScores() {
		connectionString = "jdbc:mysql://localhost:3306/scores";
        user = "root";
        pass = "tigers10";
	}

	//Returns the results of a table in an arraylist of tuple pairs.
    public ArrayList<Tuple<String, Integer>> readDatabase(String table) {
		ArrayList<Tuple<String, Integer>> results = new ArrayList<Tuple<String, Integer>>();
		try{  
			Connection con=DriverManager.getConnection(connectionString, user, pass);
			Statement stmt=con.createStatement();
			ResultSet rs=stmt.executeQuery("SELECT name, score FROM "+table+" ORDER BY score DESC;");
			while(rs.next()) {
				results.add(new Tuple<String, Integer>(rs.getString(1), rs.getInt(2)));
			}
			con.close();
		}
		catch(Exception e){
			System.out.println(e);
		}  
		return results;
	}
	
	public void insertScore(String table, String name, int score) {
		try{  
			Connection con=DriverManager.getConnection(connectionString, user, pass);  

			String insert = " insert into "+ table +" (name, score)"
			+ " values (?, ?)";

			PreparedStatement preparedStmt = con.prepareStatement(insert);
			preparedStmt.setString(1, name);
			preparedStmt.setInt(2, score);
			preparedStmt.execute();
			con.close();
		}
		catch(Exception e){
			System.out.println(e);
		}  
	}

	//Check if a score is in the top ten scores for its table.
	public boolean checkTopTen(String table, int score) {
		try {
			Connection con=DriverManager.getConnection(connectionString, user, pass);
			Statement stmt=con.createStatement();  
			ResultSet rs=stmt.executeQuery("SELECT COUNT(score) FROM "+table+" WHERE score>="+String.valueOf(score)); 
			rs.next();
			if (rs.getInt(1)>10) {
				con.close();
				return false;
			}
			else {
				con.close();
				return true;
			}
		}
		catch(Exception e) {
			System.out.println(e);
		}
		return false;
	}
}
