package dao;

import factory.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Projeto;

public class ProjetoDao implements IProjetoDao {
    private Connection con;
    
    public ProjetoDao(){
        con = (Connection) ConnectionFactory.getConnection();
    }
    
    @Override
    public int inserir(Projeto projeto){
    int ai = 0;
    Object parProjeto[] = {projeto.getMatricula(), projeto.getNome(),
        projeto.getEscopo(), projeto.getDataInicio(), projeto.getDataPrevConclusao(),
        projeto.getTipoCliente(), projeto.getCliente()};
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    
    try{
        con.setAutoCommit(false);
        String sql1 = "INSERT INTO Projeto(matricula, nome, escopo, dataInicio, dataPrevConclusao, tipoCliente, cliente) VALUES (?,?,?)";
        pstmt = con.prepareStatement(sql1);
        
        for(int i=0; i < parProjeto.length; i++)
            pstmt.execute();
        
        rs = pstmt.executeQuery("SELECT LAST_INSERT_ID()");
        
        if (rs.next()){
            ai = rs.getInt(1);
        }
    } catch (SQLException e){
        try{
            con.rollback();
            return e.getErrorCode();
        } catch (SQLException ex){
            return ex.getErrorCode();
        }
    } finally {
        try{
            rs.close();
            pstmt.close();
            con.setAutoCommit(true);
            con.close();
        } catch (SQLException e){
            return e.getErrorCode();
        }
    }
    }
    
    
}
