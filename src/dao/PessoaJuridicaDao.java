package dao;

import factory.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Cliente;
import model.PessoaJuridica;

public class PessoaJuridicaDao implements ClienteDao  {
    private Connection con;
    
    public PessoaJuridicaDao(){
        con = (Connection) ConnectionFactory.getConnection();
    }
    
    @Override
    public int inserir(Cliente cliente){
    int ai = 0;
    PessoaJuridica pessoaJuridica = (PessoaJuridica) cliente;
    Object parCliente[] = {pessoaJuridica.getEndereco(), pessoaJuridica.getEmail(),
        pessoaJuridica.getFone()};
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
        
        Object parPessoaJuridica[] = {ai, pessoaJuridica.getCnpj(), pessoaJuridica.getNomeFantasia(), pessoaJuridica.getRazaoSocial()};
        String sql2 = "INSERT INTO PessoaJuridica (idPessoaJuridica, cnpj, nomeFantasia, razaoSocial) values (?,?,?,?)";
        pstmt = con.prepareStatement(sql2);
        
        for(int i=0; i<parPessoaJuridica.length; i++)
            pstmt.setObject(i+1, parPessoaJuridica[i]);
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
        PessoaJuridica pessoaJuridica = (PessoaJuridica) cliente;
        Object parCliente[] = {pessoaJuridica.getEndereco(), pessoaJuridica.getEmail(),
            pessoaJuridica.getFone(), pessoaJuridica.getIdCliente()};
        PreparedStatement pstmt = null;
        try{
            con.setAutoCommit(false);
            String sql1 = "UPDATE Cliente SET endereco=?, email=?, fone=? WHERE idCliente=?";
            pstmt = con.prepareStatement(sql1);
            for(int i=0; i<parCliente.length; i++)
                pstmt.setObject(i+1, parCliente[i]);
            pstmt.execute();
            Object parPessoaJuridica[] = {pessoaJuridica.getCnpj(), pessoaJuridica.getNomeFantasia(), pessoaJuridica.getRazaoSocial(), 
                pessoaJuridica.getIdCliente()};
            String sql2 = "UPDATE PessoaJuridica SET cnpj=?, nomeFantasia=?, razaoSocial=?,  WHERE idPessoaJuridica=?";
            pstmt = con.prepareStatement(sql2);
            for(int i=0; i<parPessoaJuridica.length; i++)
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
        PessoaJuridica pessoaJuridica = (PessoaJuridica) cliente;
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
    public List<PessoaJuridica> getLista(){
        try{
            List<PessoaJuridica> lstPessoaJuridica = new ArrayList<>();
            String sql1 = "SELECT * FROM Cliente INNER JOIN PessoaJuridica " + "ON (idCliente=idPessoaJuridica) ORDER BY NomeFantasia";
            PreparedStatement pstmt = con.prepareStatement(sql1);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                PessoaJuridica pj = new PessoaJuridica();
                pj.setIdCliente(rs.getInt("idCliente"));
                pj.setNomeFantasia(rs.getString("nomeFantasia"));
                pj.setEndereco(rs.getString("endereco"));
                pj.setCnpj(rs.getString("cnpj"));
                pj.setRazaoSocial(rs.getString("razaoSocial"));
                pj.setEmail(rs.getString("email"));
                pj.setFone(rs.getString("fone"));
                lstPessoaJuridica.add(pj);
            }
            rs.close();
            pstmt.close();
            return lstPessoaJuridica;
        } catch (SQLException e){
            System.out.println("Erro número: "+ e.getErrorCode() + " - Mensagem: "+ e.getMessage());
            return null;
        }
    }
    
    @Override
    public Cliente getClienteId(int id){
        try{
            PessoaJuridica pj = null;
            String sql1 = "SELECT * FROM Cliente INNER JOIN PessoaJuridica" + "ON(idCliente=idPessoaJuridica) WHERE idCliente =?";
            PreparedStatement pstmt = con.prepareStatement(sql1);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                pj = new PessoaJuridica();
                pj.setIdCliente(rs.getInt("idCliente"));
                pj.setNomeFantasia(rs.getString("nomeFantasia"));
                pj.setEndereco(rs.getString("endereco"));
                pj.setCnpj(rs.getString("cnpj"));
                pj.setEmail(rs.getString("email"));
                pj.setFone(rs.getString("fone"));
            }
            rs.close();
            pstmt.close();
            return pj;
        } catch (SQLException e){
            System.out.println("Erro número: "+ e.getErrorCode() + " - Mensagem: " + e.getMessage());
            return null;
        }
    }
    
    @Override
    public List<PessoaJuridica> getClienteNome(String nome){
        try{
            List<PessoaJuridica> lstPessoaJuridica = new ArrayList<>();
            String sql1 = "SELECT * FROM Cliente INNER JOIN PessoaJuridica Pj " + "ON (idCliente=idPessoaJuridica) WHERE Pj.nomeFantasia LIKE ?";
            PreparedStatement pstmt = con.prepareStatement(sql1);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                PessoaJuridica pj = new PessoaJuridica();
                pj.setIdCliente(rs.getInt("idCliente"));
                pj.setNomeFantasia(rs.getString("nomeFantasia"));
                pj.setEndereco(rs.getString("endereco"));
                pj.setCnpj(rs.getString("cnpj"));
                pj.setRazaoSocial(rs.getString("razaoSocial"));
                pj.setEmail(rs.getString("email"));
                pj.setFone(rs.getString("fone"));
                lstPessoaJuridica.add(pj);
            }
            rs.close();
            pstmt.close();
            return lstPessoaJuridica;
        } catch (SQLException e){
            System.out.println("Erro número: "+ e.getErrorCode() + " - Mensagem: "+ e.getMessage());
            return null;
        }
    }
    
    public List<PessoaJuridica> getClienteEmail(String email){
        try{
            PessoaJuridica Pj = null;
            List<PessoaJuridica> lstPessoaJuridica = new ArrayList<>();
            String sql1 = "SELECT * FROM Cliente INNER JOIN PessoaJuridica " + "ON (idCliente=idPessoaJuridica) WHERE email LIKE ?";
            PreparedStatement pstmt = con.prepareStatement(sql1);
            pstmt.setString(1, "%" + email + "%");
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                PessoaJuridica pj = new PessoaJuridica();
                pj.setIdCliente(rs.getInt("idCliente"));
                pj.setNomeFantasia(rs.getString("nomeFantasia"));
                pj.setEndereco(rs.getString("endereco"));
                pj.setCnpj(rs.getString("cnpj"));
                pj.setRazaoSocial(rs.getString("razaoSocial"));
                pj.setEmail(rs.getString("email"));
                pj.setFone(rs.getString("fone"));
                lstPessoaJuridica.add(pj);
            }
            rs.close();
            pstmt.close();
            return lstPessoaJuridica;
        } catch (SQLException e){
            System.out.println("Erro número: "+ e.getErrorCode() + " - Mensagem: "+ e.getMessage());
            return null;
        }
    }
    
    public List<PessoaJuridica> getClienteFone(String fone){
        try{
            PessoaJuridica Pj = null;
            List<PessoaJuridica> lstPessoaJuridica = new ArrayList<>();
            String sql1 = "SELECT * FROM Cliente INNER JOIN PessoaJuridica " + "ON (idCliente=idPessoaJuridica) WHERE fone LIKE ?";
            PreparedStatement pstmt = con.prepareStatement(sql1);
            pstmt.setString(1, "%" + fone + "%");
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                PessoaJuridica pj = new PessoaJuridica();
                pj.setIdCliente(rs.getInt("idCliente"));
                pj.setNomeFantasia(rs.getString("nomeFantasia"));
                pj.setEndereco(rs.getString("endereco"));
                pj.setCnpj(rs.getString("cnpj"));
                pj.setRazaoSocial(rs.getString("razaoSocial"));
                pj.setEmail(rs.getString("email"));
                pj.setFone(rs.getString("fone"));
                lstPessoaJuridica.add(pj);
            }
            rs.close();
            pstmt.close();
            return lstPessoaJuridica;
        } catch (SQLException e){
            System.out.println("Erro número: "+ e.getErrorCode() + " - Mensagem: "+ e.getMessage());
            return null;
        }
    }
}
