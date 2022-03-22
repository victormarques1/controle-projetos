package dao;

import factory.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Cliente;
import model.PessoaFisica;

public class PessoaFisicaDao implements ClienteDao{
    private Connection con;
    
    public PessoaFisicaDao(){
        con = (Connection) ConnectionFactory.getConnection();
    }
    
    @Override
    public int inserir(Cliente cliente){
    int ai = 0;
    PessoaFisica pessoaFisica = (PessoaFisica) cliente;
    Object parCliente[] = {pessoaFisica.getEndereco(), pessoaFisica.getEmail(),
        pessoaFisica.getFone()};
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    
    try{
        con.setAutoCommit(false);
        String sql1 = "INSERT INTO Cliente(endereco, email, fone) VALUES (?,?,?)";
        pstmt = con.prepareStatement(sql1);
        
        for(int i=0; i < parCliente.length; i++)
            pstmt.execute();
        
        rs = pstmt.executeQuery("SELECT LAST_INSERT_ID()");
        
        if (rs.next()){
            ai = rs.getInt(1);
        }
        
        Object parPessoaFisica[] = {ai, pessoaFisica.getCpf(), pessoaFisica.getNome()};
        String sql2 = "INSERT INTO PessoaFisica (idPessoaFisica, cpf, nome) values (?,?,?)";
        pstmt = con.prepareStatement(sql2);
        
        for(int i=0; i<parPessoaFisica.length; i++)
            pstmt.setObject(i+1, parPessoaFisica[i]);
        pstmt.execute();
        con.commit();
        return -1;
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
    
    @Override
    public int alterar (Cliente cliente){
        PessoaFisica pessoaFisica = (PessoaFisica) cliente;
        Object parCliente[] = {pessoaFisica.getEndereco(), pessoaFisica.getEmail(),
            pessoaFisica.getFone(), pessoaFisica.getIdCliente()};
        PreparedStatement pstmt = null;
        try{
            con.setAutoCommit(false);
            String sql1 = "UPDATE Cliente SET endereco=?, email=?, fone=? WHERE idCliente=?";
            pstmt = con.prepareStatement(sql1);
            for(int i=0; i<parCliente.length; i++)
                pstmt.setObject(i+1, parCliente[i]);
            pstmt.execute();
            Object parPessoaFisica[] = {pessoaFisica.getCpf(), pessoaFisica.getNome(), pessoaFisica.getIdCliente()};
            String sql2 = "UPDATE PessoaFisica SET cpf=?, nome=?, WHERE idPessoaFisica=?";
            pstmt = con.prepareStatement(sql2);
            for(int i=0; i<parPessoaFisica.length; i++)
                pstmt.execute();
            con.commit();
            return -1;
        } catch (SQLException e){
            try{
                con.rollback();
                return e.getErrorCode();
            } catch (SQLException ex){
                return ex.getErrorCode();
            }
        } finally {
            try{
                pstmt.close();
                con.setAutoCommit(true);
                con.close();
            } catch (SQLException e){
                return e.getErrorCode();
            }
        }
    }
    
    @Override
    public int excluir(Cliente cliente){
        PessoaFisica pessoaFisica = (PessoaFisica) cliente;
        PreparedStatement pstmt = null;
        try{
            con.setAutoCommit(false);
            String sql1 = "DELETE FROM Cliente WHERE idCliente = ?";
            pstmt = con.prepareStatement(sql1);
            pstmt.setInt(1, cliente.getIdCliente());
            pstmt.execute();
            con.commit();
            return -1;
        } catch (SQLException e){
            try{
                con.rollback();
                return e.getErrorCode();
            } catch (SQLException ex){
                return ex.getErrorCode();
            } 
        } finally {
            try{
                pstmt.close();
                con.setAutoCommit(true);
                con.close();
            } catch (SQLException e){
                return e.getErrorCode();
            }
        }
    }
    
    @Override 
    public List<PessoaFisica> getLista(){
        try{
            List<PessoaFisica> lstPessoaFisica = new ArrayList<>();
            String sql1 = "SELECT * FROM Cliente INNER JOIN PessoaFisica " + "ON (idCliente=idPessoaFisica) ORDER BY Nome";
            PreparedStatement pstmt = con.prepareStatement(sql1);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                PessoaFisica pf = new PessoaFisica();
                pf.setIdCliente(rs.getInt("idCliente"));
                pf.setNome(rs.getString("nome"));
                pf.setEndereco(rs.getString("endereco"));
                pf.setCpf(rs.getString("cpf"));
                pf.setEmail(rs.getString("email"));
                pf.setFone(rs.getString("fone"));
                lstPessoaFisica.add(pf);
            }
            rs.close();
            pstmt.close();
            return lstPessoaFisica;
        } catch (SQLException e){
            System.out.println("Erro número: "+ e.getErrorCode() + " - Mensagem: "+ e.getMessage());
            return null;
        }
    }
    
    @Override
    public Cliente getClienteId(int id){
        try{
            PessoaFisica pf = null;
            String sql1 = "SELECT * FROM Cliente INNER JOIN PessoaFisica" + "ON(idCliente=idPessoaFisica) WHERE idCliente =?";
            PreparedStatement pstmt = con.prepareStatement(sql1);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                pf = new PessoaFisica();
                pf.setIdCliente(rs.getInt("idCliente"));
                pf.setNome(rs.getString("nome"));
                pf.setEndereco(rs.getString("endereco"));
                pf.setCpf(rs.getString("cpf"));
                pf.setEmail(rs.getString("email"));
                pf.setFone(rs.getString("fone"));
            }
            rs.close();
            pstmt.close();
            return pf;
        } catch (SQLException e){
            System.out.println("Erro número: "+ e.getErrorCode() + " - Mensagem: " + e.getMessage());
            return null;
        }
    }
    
    @Override
    public List<PessoaFisica> getClienteNome(String nome){
        try{
            List<PessoaFisica> lstPessoaFisica = new ArrayList<>();
            String sql1 = "SELECT * FROM Cliente INNER JOIN PessoaFisica Pf " + "ON (idCliente=idPessoaFisica) WHERE Pf.nome LIKE ?";
            PreparedStatement pstmt = con.prepareStatement(sql1);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                PessoaFisica pf = new PessoaFisica();
                pf.setIdCliente(rs.getInt("idCliente"));
                pf.setNome(rs.getString("nome"));
                pf.setEndereco(rs.getString("endereco"));
                pf.setCpf(rs.getString("cpf"));
                pf.setEmail(rs.getString("email"));
                pf.setFone(rs.getString("fone"));
                lstPessoaFisica.add(pf);
            }
            rs.close();
            pstmt.close();
            return lstPessoaFisica;
        } catch (SQLException e){
            System.out.println("Erro número: "+ e.getErrorCode() + " - Mensagem: "+ e.getMessage());
            return null;
        }
    }
    
    public List<PessoaFisica> getClienteEmail(String email){
        try{
            PessoaFisica Pf = null;
            List<PessoaFisica> lstPessoaFisica = new ArrayList<>();
            String sql1 = "SELECT * FROM Cliente INNER JOIN PessoaFisica " + "ON (idCliente=idPessoaFisica) WHERE email LIKE ?";
            PreparedStatement pstmt = con.prepareStatement(sql1);
            pstmt.setString(1, "%" + email + "%");
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                PessoaFisica pf = new PessoaFisica();
                pf.setIdCliente(rs.getInt("idCliente"));
                pf.setNome(rs.getString("nome"));
                pf.setEndereco(rs.getString("endereco"));
                pf.setCpf(rs.getString("cpf"));
                pf.setEmail(rs.getString("email"));
                pf.setFone(rs.getString("fone"));
                lstPessoaFisica.add(pf);
            }
            rs.close();
            pstmt.close();
            return lstPessoaFisica;
        } catch (SQLException e){
            System.out.println("Erro número: "+ e.getErrorCode() + " - Mensagem: "+ e.getMessage());
            return null;
        }
    }
    
    public List<PessoaFisica> getClienteFone(String fone){
        try{
            PessoaFisica Pf = null;
            List<PessoaFisica> lstPessoaFisica = new ArrayList<>();
            String sql1 = "SELECT * FROM Cliente INNER JOIN PessoaFisica " + "ON (idCliente=idPessoaFisica) WHERE fone LIKE ?";
            PreparedStatement pstmt = con.prepareStatement(sql1);
            pstmt.setString(1, "%" + fone + "%");
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                PessoaFisica pf = new PessoaFisica();
                pf.setIdCliente(rs.getInt("idCliente"));
                pf.setNome(rs.getString("nome"));
                pf.setEndereco(rs.getString("endereco"));
                pf.setCpf(rs.getString("cpf"));
                pf.setEmail(rs.getString("email"));
                pf.setFone(rs.getString("fone"));
                lstPessoaFisica.add(pf);
            }
            rs.close();
            pstmt.close();
            return lstPessoaFisica;
        } catch (SQLException e){
            System.out.println("Erro número: "+ e.getErrorCode() + " - Mensagem: "+ e.getMessage());
            return null;
        }
    }
}


