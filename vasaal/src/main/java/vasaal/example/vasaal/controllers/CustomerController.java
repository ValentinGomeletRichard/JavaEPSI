package vasaal.example.vasaal.controllers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.sql.Statement;

import lombok.Data;

@Data
public class CustomerController {

	public CustomerController() {
		// TODO Auto-generated constructor stub
	}
	
	public void showAllCustomers() {
        try {
            // Charger le pilote JDBC
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Connexion à la base de données

            Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/msprclean",
                "root",
                ""
            );
            if (connection != null) {
                System.out.println("Connexion à la base de donnée réussi !");

            }
            else {
            	System.out.println("Connexion échouée");
            }
            // Utiliser la connexion ici
            Statement st = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
            	      ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = st.executeQuery("SELECT * FROM client ORDER BY client.id ASC;");

            System.out.println(st);
            System.out.println(rs);
            
//            for (ResultSet r : rs) {}
				
			
            rs.next();
            
            System.out.print("id : " + rs.getInt("id") + ", ");
            System.out.print("prenom : " + rs.getString("prenom") + ", ");
            System.out.println("nom : " + rs.getString("nom") + ", ");
            
//            try {
//            	rs.moveToInsertRow(); //**********************************
//            	System.out.println(rs.getRow());
//            	// crée/modifier les valeurs entre ces deux méthodes moveToInsertRow() et updateRow();
//            	rs.updateString("prenom", "clarapabelle");
//            	rs.updateString("nom", "null");
//            	
//            	System.out.println(rs.getRow());
//            	rs.updateInt("id", rs.getInt("id"));
//            	System.out.println(rs.getRow());
//            	
//            	rs.insertRow();
//            	
//            } catch (SQLFeatureNotSupportedException e) {
//            	System.out.println("Feature is not supported in this version");
//            	e.printStackTrace();
//            } catch (SQLException e) {
//            	System.out.println("SQL Did not like that");
//            	e.printStackTrace();
//            }
//            rs.deleteRow(); // marche pas ? parce qu'on se trouve sur l'insert row ? c'est quoi l'insert row, et la différence avec le current row ?
 //****************************************
            
            System.out.print("id : " + rs.getInt("id") + ", ");
            System.out.print("prenom : " + rs.getString("prenom") + ", ");
            System.out.println("nom : " + rs.getString("nom") + ", ");

            rs.next();
            System.out.println(rs.getRow());

            System.out.print("id : " + rs.getInt("id") + ", ");
            System.out.print("prenom : " + rs.getString("prenom") + ", ");
            System.out.println("nom : " + rs.getString("nom") + ", ");            
            rs.next();
            
            System.out.print("id : " + rs.getInt("id") + ", ");
            System.out.print("prenom : " + rs.getString("prenom") + ", ");
            System.out.println("nom : " + rs.getString("nom") + ", ");                        
            // Fermer la connexion
            connection.close();
        } catch (Exception e) {
//        	System.out.println("An error occured.");
            e.printStackTrace();
        }
	}
		

}
