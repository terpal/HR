package hr.softeng.com;

import java.sql.*;
import java.util.Observable;

public class HRModel extends Observable {
	
	Connection conn = null;
	Member user = new Member();	
	
	public void dbconnect(){
		try{
		   // JDBC driver name and database URL
		   // final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
			
			//Class.forName("com.mysql.jdbc.Driver");
		   final String DB_URL = "jdbc:mysql://localhost:3306/test";

		   //  Database credentials
		   final String USER = "root";
		   final String PASS = "root";
		   
		   
		   //STEP 2: Register JDBC driver
		   //Class.forName("com.mysql.jdbc.Driver");

		   //STEP 3: Open a connection
		   //System.out.println("Connecting to a selected database...");
		   //Class.forName("com.mysql.jdbc.Driver").newInstance();
		   conn = DriverManager.getConnection(DB_URL, USER, PASS);
		   //System.out.println("Connected database successfully...");
		   
		}catch(SQLException se){
			//Handle errors for JDBC
			se.printStackTrace();
		}catch(Exception e){
			//Handle errors for Class.forName
			e.printStackTrace();
		}//end try
	}//end dbconnect
	
	public void disConn(){
	      try{
	    	  if(conn!=null)
	        	 conn.close();
	      }catch(SQLException se){
	    	  se.printStackTrace();
	      }//end finally try
	}//end disConn
	
	public void createPointTable() {	
		Statement stmt = null;
		dbconnect();
		   try{

		      //STEP 4: Execute a query
		      //System.out.println("Creating Points table");
		      stmt = conn.createStatement();
		      
		      String sql = "CREATE TABLE IF NOT EXISTS Points" +
		                   " (id INTEGER not NULL, " +
		                   "tmima VARCHAR(80) not NULL, " +
		                   "points DOUBLE, " + 
		                   "timestamp DATETIME)"; 

		      stmt.execute(sql);
		      //System.out.println("Created Points table");
		      stmt.close();
		      disConn();
		   }catch(SQLException se){
		      //Handle errors for JDBC
		      se.printStackTrace();
		   }catch(Exception e){
		      //Handle errors for Class.forName
		      e.printStackTrace();
		   }//end try
	}
	
	public void createAccTable() { 
		Statement stmt = null;
		dbconnect();
		   try{

		      //STEP 4: Execute a query
		      //System.out.println("Creating Accounts table in given database...");
		      stmt = conn.createStatement();
		      
		      String sql = "CREATE TABLE IF NOT EXISTS Accounts" +
		                   " (id INTEGER not NULL AUTO_INCREMENT, " +
		                   "first VARCHAR(40), " + 
		                   "last VARCHAR(80), " + 
		                   "username VARCHAR(40), " + 
		                   "pass VARCHAR(40), " + 
		                   "class VARCHAR(20) " +
		                   "PRIMARY KEY ( id ))"; 

		      stmt.executeUpdate(sql);
		      //System.out.println("Created table Accounts in given database");
		      stmt.close();
		      
		      String query = " INSERT INTO Accounts (first, last, username, pass, class)"
		    	        + " VALUES (?, ?, ?, ?, ?), (?, ?, ?, ?, ?)";
		      
		      PreparedStatement preparedStmt = conn.prepareStatement(query);
		      preparedStmt.setString (1, "Γιάννης");
		      preparedStmt.setString (2, "Παπαδόπουλος");
		      preparedStmt.setString (3, "John");
		      preparedStmt.setString (4, "qwerty");
		      preparedStmt.setString (5, "HR");
		 
		      preparedStmt.setString (6, "Γιώργος");
		      preparedStmt.setString (7, "Παπαδόπουλος");
		      preparedStmt.setString (8, "George");
		      preparedStmt.setString (9, "qwerty123");
		      preparedStmt.setString (10, "Proistamenos");
		   
		      preparedStmt.execute();
		       
		      conn.close();
		      disConn();
		   }catch(SQLException se){
		      //Handle errors for JDBC
		      se.printStackTrace();
		   }catch(Exception e){
		      //Handle errors for Class.forName
		      e.printStackTrace();
		   }//end try
	}
	
	
	public void Login(String username, String pass){ 
		
		dbconnect();
		
		PreparedStatement stmt = null; 
		try{

	      //STEP 4: Execute a query
	      System.out.println("Logging in");

	      //stmt = conn.createStatement();
	      
	      String sql = "SELECT name, surname, class FROM Accounts WHERE name =? AND password =? ";
	      String pas = String.valueOf(pass);
	      stmt = conn.prepareStatement(sql);
	      stmt.setString(1,username);
	      stmt.setString(2, pas);
	      ResultSet rs = stmt.executeQuery(); 
	      
	      /*
	      // emfanizei ta apotelesmata...
	      int columns = rs.getMetaData().getColumnCount();
	      
	      StringBuilder message = new StringBuilder();

	      while (rs.next()) {
	          for (int i = 1; i <= columns; i++) {
	              message.append(rs.getString(i) + " ");
	          }
	          message.append("\n");
	      }

	      System.out.println(message);  // print table contents
	      */
	      String cla = "1";
	      String first = null;
	      String last = null;
	      while (rs.next()) {
	          cla = rs.getString("class");
	          first = rs.getString("name");
	          last = rs.getString("surname");
	          
	      }

	      disConn();
	      
	      if (cla != "1"){
	    	  
	    	  System.out.format("Successfull login\n");
	    	  
	    	  String tempfirst = first; // first
	    	  String templast = last;  // last
	    	  String tempclass = cla;
	        // print the results
	        //System.out.format("%s, %s, %s\n", tempfirst, templast, tempclass);
	        user.First = tempfirst;
	        user.Last = templast;
	        user.Class = tempclass;
	        
	        setChanged();
	        notifyObservers(cla);
	        //succLogin();
	      }
	      else{
	    	  //System.out.format("Wrong username/password\n");
	    	  setChanged();
		      notifyObservers(cla);
	      }
	      stmt.close();
	   }catch(SQLException se){
	      //Handle errors for JDBC
	      se.printStackTrace();
	   }catch(Exception e){
	      //Handle errors for Class.forName
	      e.printStackTrace();
	   }
	}//end addPerson
	

	//ftiaxnei neo pinaka analoga me to ti graftike sto "newTableTextbox"
	public void createTable(String Tname){
		Statement stmt = null;
		dbconnect();
		   try{

		      //STEP 4: Execute a query
		      //System.out.println("Creating table in given database...");
		      stmt = conn.createStatement();
		      
		      String sql = "CREATE TABLE " + Tname +
		                   " (id INTEGER not NULL AUTO_INCREMENT, " +
		                   "first VARCHAR(40), " + 
		                   "last VARCHAR(80), " + 
		                   "points DOUBLE, " + 
		                   "PRIMARY KEY ( id ))"; 

		      stmt.executeUpdate(sql);
		      //System.out.println("Created table "+Tname+" in given database");
		      stmt.close();
		   }catch(SQLException se){
		      //Handle errors for JDBC
		      se.printStackTrace();
		   }catch(Exception e){
		      //Handle errors for Class.forName
		      e.printStackTrace();
		   }//end try
	}
	
	//antoistoixo me to pano apla diagrafei ton pinaka pou anagrafete sto texbox
	public void deleteTable(String Tname){

		Statement stmt = null;
			try{

		      //STEP 4: Execute a query
		      //System.out.println("Deleting table in given database...");
		      stmt = conn.createStatement();
		      
		      String sql = "DROP TABLE " + Tname;
		 
		      stmt.executeUpdate(sql);
		      //System.out.println("Table "+Tname+" deleted in given database");
		      stmt.close();
		   }catch(SQLException se){
		      //Handle errors for JDBC
		      se.printStackTrace();
		   }catch(Exception e){
		      //Handle errors for Class.forName
		      e.printStackTrace();
		   }

	}//end Delete
	
//antoistoixo me to pano alla thelei 2 koutakia, to ena gia to palio onoma tou pinaka kai ena gia to kainourgio
	public void changeTable(String Tname,String NTname) {

		Statement stmt = null;
		try{

	      //STEP 4: Execute a query
	      //System.out.println("Changing table name in given database...");
	      stmt = conn.createStatement();
	      
	      String sql = "ALTER TABLE "+ Tname +" RENAME TO " + NTname;
	 
	      stmt.executeUpdate(sql);
	      //System.out.println("Table "+Tname+" change to "+NTname+" in given database...");
	      stmt.close();
	   }catch(SQLException se){
	      //Handle errors for JDBC
	      se.printStackTrace();
	   }catch(Exception e){
	      //Handle errors for Class.forName
	      e.printStackTrace();
	   }
	}//end change
	
//prosthetoi atomo se yparxon pinaka, zitaei 3 koutakia, ena gia to onoma tou pinaka (tmima) kai alla 2 gia onoma/epitheto
	//meta to atomo pernei aftomata id apo ton pinaka pou kataxorithike giati einai autoincrement stous pinakes
	public void addPerson(String Tname, String PFname, String PLname){
		
		Statement stmt = null;
		try{

	      //STEP 4: Execute a query
	      //System.out.println("Adding person now...");
	      stmt = conn.createStatement();
	      
	      String sql = "INSERT INTO "+ Tname +" (first,last) VALUES ("+ PFname +","+PLname+")";
	 
	      stmt.executeUpdate(sql);
	      //System.out.println("New employe "+PFname+" "+PLname+" has been added");
	      stmt.close();
	   }catch(SQLException se){
	      //Handle errors for JDBC
	      se.printStackTrace();
	   }catch(Exception e){
	      //Handle errors for Class.forName
	      e.printStackTrace();
	   }
	}//end addPerson
	
	//antoistoixo me to pano gia diagrafi atomou
	public void deletePerson(String Tname, String name, String surname){
		
		Statement stmt = null;
		try{

	      //STEP 4: Execute a query
	      //System.out.println("Deleting person...");
	      stmt = conn.createStatement();
	      
	      String sql = "DELETE FROM "+ Tname +" WHERE id = "+name;
	 
	      stmt.executeUpdate(sql);
	      //System.out.println("Person was deleted...");
	      stmt.close();
	   }catch(SQLException se){
	      //Handle errors for JDBC
	      se.printStackTrace();
	   }catch(Exception e){
	      //Handle errors for Class.forName
	      e.printStackTrace();
	   }
	}//end addPerson
	
	//dexete to tmima kai to id enos ypalilou kai tou prosthetei pontous ston pinaka Points pou exoume
	//dimiourgisei apo prin, kataxorei kai imerominia
	public void givePoints(String Tname, int id, double Pnts){
		
		PreparedStatement ps = null;
		
		//System.out.println(Tname+Integer.toString(id)+String.valueOf(Pnts));
		
		
	    try{

	      //STEP 4: Execute a query
	      //System.out.println("Adding points");

	      java.util.Date date= new java.util.Date(); //(1) kai afto borei na thelei diagrafi an ginoun allages ta "date" se "string"

	          ps = conn.prepareStatement (
	        		  "INSERT INTO Points (id, tmima, points, timestamp) VALUES(?,?,?,?)"
	        		  );
	          ps.setInt (1, id);
	          ps.setString (2, Tname);
	          ps.setDouble (3, Pnts);
	          ps.setString (4, new Timestamp(date.getTime()).toString()); //(1) anti gia tis alles 2 allages borei afto na prepei na ginei setDate kai na ginei allagi sto "new Timestamp..." ktlp
	          ps.executeUpdate ();
	      
	          //System.out.println("Points where added");
	      ps.close();
	      /*
	      setChanged();
	      notifyObservers();
	      */
	      
	   }catch(SQLException se){
	      //Handle errors for JDBC
	      se.printStackTrace();
	   }catch(Exception e){
	      //Handle errors for Class.forName
	      e.printStackTrace();
	   }
	}//end addPerson
	
	//dexete tmima kai id kai vriskei tous pontous tou atomou kai tous dixnei
	public void lookPoints(String Tname, int id){

		Statement stmt = null;
		try{

	      //STEP 4: Execute a query
	      //System.out.println("Showing the points");

	      stmt = conn.createStatement();
	      
	      String sql = "SELECT points,timestamp FROM Points WHERE id = "+Tname+" AND Tname = "+Tname;
	        		  
	      ResultSet rs = stmt.executeQuery(sql);
	      
	      //System.out.println("Points where added");
	      double sumpoints = 0;
	      int counter = 0;
	      while (rs.next())
	      {
	    	  int temppoints = rs.getInt("points");
	    	  Date tempdate = rs.getDate("timestamp"); //(1) an xtipaei errors afto prepei na ginei "getString"
	    	  sumpoints = sumpoints + temppoints;
	    	  counter++;
	        // print the results
	        //System.out.format("%s, %s\n", temppoints, tempdate);//(2) edo anti gia system out tha ginete i ektiposi se pinaka
	      }														//sto gui gia na fenontai oi pontoi
	      sumpoints = sumpoints / counter;
	      //System.out.format("%d\n", sumpoints); //(2) kai edo o mesos oros antoistoixa
	      stmt.close();
	   }catch(SQLException se){
	      //Handle errors for JDBC
	      se.printStackTrace();
	   }catch(Exception e){
	      //Handle errors for Class.forName
	      e.printStackTrace();
	   }
	}//end addPerson
	
}