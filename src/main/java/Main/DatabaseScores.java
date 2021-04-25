package Main;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

//Class used for all interactions with the database
public class DatabaseScores {

	//The string used to connect to the database
    private String connectionString;
	//To hold the database's login information
    private String user;
	private String pass;
	
	//Initialize all information needed to access the database, ready for use.
	public DatabaseScores() {
		connectionString = "jdbc:mysql://localhost:3306/scores?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        user = "root";
        pass = "tigers10";
	}

	//Returns the results of a table in an arraylist of tuple pairs.
	//'table' The string name of the database table wanting to be accessed.
    public ArrayList<Tuple<String, Integer>> readDatabase(String table) {
		ArrayList<Tuple<String, Integer>> results = new ArrayList<Tuple<String, Integer>>();
		//Form a connection with the database.
		try{  
			Connection con=DriverManager.getConnection(connectionString, user, pass);
			//Form a query using 'table'
			Statement stmt=con.createStatement();
			ResultSet rs=stmt.executeQuery("SELECT name, score FROM "+table+" ORDER BY score DESC;");
			//Store each row in the 'results' arraylist.
			while(rs.next()) {
				results.add(new Tuple<String, Integer>(rs.getString(1), rs.getInt(2)));
			}
			//Close the connection
			con.close();
		}
		catch(Exception e){
			System.out.println(e);
		}  
		return results;
	}
	
	//To insert a name and score into a specific table
	//'table': String name of the table to access
	//'name': The name of the player to be stored
	//'score': The score of the player to be stored
	public void insertScore(String table, String name, int score) {
		try{  
			//Form a connection with the database
			Connection con=DriverManager.getConnection(connectionString, user, pass);  
			//Build a statement to insert the data into the correct table.
			String insert = " insert into "+ table +" (name, score)"
			+ " values (?, ?)";

			//Execute the statement
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

	//Returns true if a given score is in the top 10 of a given table
	//'table': The table to be checked
	//'score': The score to be analysed if it is top 10.
	public boolean checkTopTen(String table, int score) {
		try {
			//Form a connection
			Connection con=DriverManager.getConnection(connectionString, user, pass);
			//Create a statement to count the number of database scores larger than the given score
			Statement stmt=con.createStatement();  
			ResultSet rs=stmt.executeQuery("SELECT COUNT(score) FROM "+table+" WHERE score>="+String.valueOf(score)); 
			rs.next();
			//If the number of larger scores is more than 10, return false.
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
