package vasaal.example.vasaal;
import java.sql.*;

import javax.servlet.http.HttpSession;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class VasaalApplication {

    public static void main(String[] args) 
    {
    	
    	// début de connexion entre session et Java
//    	HttpSession session = request.getSession();
//    	session.getAttribute("username", username);
    	
        try {
        	System.out.println("trying connection to DB");
            // Charger le pilote JDBC
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("test for Class.forName");

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
            Statement st1 = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
            	      ResultSet.CONCUR_UPDATABLE);
            st1.executeUpdate("INSERT INTO client(nom, prenom, entreprise, adresse, telephone, mail) "
            		+ "VALUES ('Lou', 'Ve', 'Romus', '1, plaza de la Genesia, Roma', '555-666-2222', 'canis@lupus.rome')");
            st1.close();
            Statement st2 = connection.createStatement();
            ResultSet resultSetAllCustomers = st2.executeQuery("SELECT * FROM client ORDER BY client.id ASC;");
            
            while (resultSetAllCustomers.next()) {
            	
            System.out.print("id : " + resultSetAllCustomers.getInt("id") + ", ");
            System.out.print("prenom : " + resultSetAllCustomers.getString("prenom") + ", ");
            System.out.println("nom : " + resultSetAllCustomers.getString("nom") + ", ");
            
			}
            st2.close();
            Statement st3 = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
          	      ResultSet.CONCUR_UPDATABLE);
            ResultSet rs3 = st3.executeQuery("SELECT * FROM client WHERE nom='Lou'");
            rs3.next();
            int i = 1;
            while (rs3.next()) {
            	
				System.out.println("Nombre de duplicatas de nom 'Lou': " + i + ", à la ligne id: "+ rs3.getInt("id"));
				rs3.deleteRow(); System.out.println("YEET");
				i++;
			}
            st3.close();

            

            
            // Modifier des données existantes; /!\ à faire via un Statement createStatement("UPDATE ...") ?
//            try {
//            	resultSetAllCustomers.moveToInsertRow();
//**********************************
//            	System.out.println(resultSetAllCustomers.getRow());
//            	// crée/modifier les valeurs entre ces deux méthodes moveToInsertRow() et updateRow();
//            	resultSetAllCustomers.updateString("prenom", "clarapabelle");
//            	resultSetAllCustomers.updateString("nom", "null");
//            	
//            	System.out.println(resultSetAllCustomers.getRow());
//            	resultSetAllCustomers.updateInt("id", resultSetAllCustomers.getInt("id"));
//            	System.out.println(resultSetAllCustomers.getRow());
//            	
//            	resultSetAllCustomers.insertRow();
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
            
            // Fermer la connexion
            connection.close();
        } catch (Exception e) {
//        	System.out.println("An error occured.");
            e.printStackTrace();
        }
//        SpringApplication.run(VasaalApplication.class, args);
        System.out.println("Arrivée à la fin du code sans erreurs.");
    }
    
    
}