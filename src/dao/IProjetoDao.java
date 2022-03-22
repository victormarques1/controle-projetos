package dao;

import java.util.List;
import model.Projeto;

public interface IProjetoDao {
    public abstract int inserir(Projeto projeto);
    public abstract int alterar(Projeto projeto );
    public abstract int excluir(Projeto projeto );
    public abstract List getLista();
    public abstract Projeto getProjetoId(int id);
    public abstract List getProjetoNome(String nome);
}
