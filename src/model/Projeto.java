package model;

import java.util.Date;

public class Projeto {
    private int idProjeto;
    private String matricula;
    private String nome;
    private String escopo;
    private Date dataInicio;
    private Date dataPrevConclusao;
    private Date dataConclusao;
    private int tipoCliente;
    private Cliente cliente;

    
    public int getIdProjeto() {
        return idProjeto;
    }

    public void setIdProjeto(int idProjeto) {
        this.idProjeto = idProjeto;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEscopo() {
        return escopo;
    }

    public void setEscopo(String escopo) {
        this.escopo = escopo;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataPrevConclusao() {
        return dataPrevConclusao;
    }

    public void setDataPrevConclusao(Date dataPrevConclusao) {
        this.dataPrevConclusao = dataPrevConclusao;
    }

    public Date getDataConclusao() {
        return dataConclusao;
    }

    public void setDataConclusao(Date dataConclusao) {
        this.dataConclusao = dataConclusao;
    }
    
    public int getTipoCliente() {
        return tipoCliente;
    }
    
    public void setTipoCliente(int tipoCliente) {
        this.tipoCliente = tipoCliente;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
        
    
}

