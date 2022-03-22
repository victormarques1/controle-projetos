package controller;

import dao.PessoaFisicaDao;
import java.util.List;
import model.Cliente;
import model.PessoaFisica;

public class PessoaFisicaController {
    public int incluir (String nome, String endereco, String cpf, String email, String fone){
        PessoaFisica pf = new PessoaFisica();
        pf.setNome(nome);
        pf.setEndereco(endereco);
        pf.setCpf(cpf);
        pf.setEmail(email);
        pf.setFone(fone);
        int erro = new PessoaFisicaDao().inserir(pf);
        return erro;
    }
    
    public int alterar (int id, String nome, String endereco, String cpf, String email, String fone){
        PessoaFisica pf = new PessoaFisica();
        pf.setIdCliente(id);
        pf.setNome(nome);
        pf.setEndereco(endereco);
        pf.setCpf(cpf);
        pf.setEmail(email);
        pf.setFone(fone);
        int erro = new PessoaFisicaDao().alterar(pf);
        return erro;
    }
    
    public int excluir (int id){
        PessoaFisica pf = new PessoaFisica();
        pf.setIdCliente(id);
        int erro = new PessoaFisicaDao().excluir(pf);
        return erro;
    }
    
    public List<PessoaFisica> lstPessoaFisica(){
        PessoaFisicaDao pfDao = new PessoaFisicaDao();
        return pfDao.getLista();
    }
    
    public List<PessoaFisica> getPessoaFisicaNome(String nome){
        PessoaFisicaDao pfDao = new PessoaFisicaDao();
        return pfDao.getClienteNome(nome);
    }
    
    public Cliente getPessoaFisicaId(int id){
        PessoaFisicaDao pfDao = new PessoaFisicaDao();
        return pfDao.getClienteId(id);
    }
    
    public List<PessoaFisica> getPessoaFisicaEmail(String email){
        PessoaFisicaDao pfDao = new PessoaFisicaDao();
        return pfDao.getClienteEmail(email);
    }
    
    public List<PessoaFisica> getPessoaFisicaFone(String fone){
        PessoaFisicaDao pfDao = new PessoaFisicaDao();
        return pfDao.getClienteFone(fone);
    }
}
