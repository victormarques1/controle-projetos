package model;

public class Cliente {
    private int idCliente;
    private String endereco;
    private String email;
    private String fone;
    
    public Cliente(){
    }
    
    public int getIdCliente(){
        return idCliente;
    }
    
    public void setIdCliente(int idCliente){
        this.idCliente = idCliente;
    }
    
    public String getEndereco(){
        return endereco;
    }
    
    public void setEndereco(String endereco){
        this.endereco = endereco;
    }
    
    public String getEmail(){
        return email;
    }
    
    public void setEmail(String email){
        this.email = email;
    }
    
    public String getFone(){
        return fone;
    }
    
    public void setFone(String fone){
        this.fone = fone;
    }
}
