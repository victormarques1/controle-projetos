/*
 Criando conexão e acesso ao MySQL
 */
package factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    private static Connection con;
    
    public static Connection getConnection(){
        try{
            String banco = "bdcontrole";
            String usuario = "root";
            String senha = "root";
            con = DriverManager.getConnection("jdbc:mysql://localhost/"+ banco, usuario, senha);
            return con;
        } catch (SQLException e){
            System.out.println("Erro ao conectar com o Banco de Dados!! Número: " +
                    e.getErrorCode() + " - Mensagem: " + e.getMessage());
            return null;
        }
    }
}
