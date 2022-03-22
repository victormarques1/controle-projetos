package controller;

import dao.PessoaJuridicaDao;
import java.util.List;
import model.Cliente;
import model.PessoaJuridica;

public class PessoaJuridicaController {
    public int incluir (String nomeFantasia, String razaoSocial, String endereco, String email, String cnpj, String fone){
        PessoaJuridica pj = new PessoaJuridica();
        pj.setNomeFantasia(nomeFantasia);
        pj.setRazaoSocial(razaoSocial);
        pj.setEndereco(endereco);
        pj.setEmail(email);
        pj.setCnpj(cnpj);
        pj.setFone(fone);
        int erro = new PessoaJuridicaDao().inserir(pj);
        return erro;
    }
    
    public int alterar (int id, String nomeFantasia, String razaoSocial, String endereco, String email, String cnpj, String fone){
        PessoaJuridica pj = new PessoaJuridica();
        pj.setIdCliente(id);
        pj.setNomeFantasia(nomeFantasia);
        pj.setRazaoSocial(razaoSocial);
        pj.setEndereco(endereco);
        pj.setEmail(email);
        pj.setCnpj(cnpj);
        pj.setFone(fone);
        int erro = new PessoaJuridicaDao().alterar(pj);
        return erro;
    }
    
    public int excluir (int id){
        PessoaJuridica pj = new PessoaJuridica();
        pj.setIdCliente(id);
        int erro = new PessoaJuridicaDao().excluir(pj);
        return erro;
    }
    
    public List<PessoaJuridica> lstPessoaJuridica(){
        PessoaJuridicaDao pjDao = new PessoaJuridicaDao();
        return pjDao.getLista();
    }
    
    public List<PessoaJuridica> getPessoaJuridicaNomeFantasia(String nomeFantasia){
        PessoaJuridicaDao pjDao = new PessoaJuridicaDao();
        return pjDao.getClienteNome(nomeFantasia);
    }
    
    public Cliente getPessoaJuridicaId(int id){
        PessoaJuridicaDao pjDao = new PessoaJuridicaDao();
        return pjDao.getClienteId(id);
    }
    
    public List<PessoaJuridica> getPessoaJuridicaEmail(String email){
        PessoaJuridicaDao pjDao = new PessoaJuridicaDao();
        return pjDao.getClienteEmail(email);
    }
    
    public List<PessoaJuridica> getPessoaJuridicaFone(String fone){
        PessoaJuridicaDao pjDao = new PessoaJuridicaDao();
        return pjDao.getClienteFone(fone);
    }
}
