package dao;

import java.util.List;
import model.Cliente;

public interface ClienteDao {
    public abstract int inserir(Cliente cliente);
    public abstract int alterar(Cliente cliente);
    public abstract int excluir(Cliente cliente);
    public abstract List getLista();
    public abstract Cliente getClienteId(int id);
    public abstract List getClienteNome(String nome);
}
