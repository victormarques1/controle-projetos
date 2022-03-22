package tableModel;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.PessoaFisica;

public class PessoaFisicaTableModel extends AbstractTableModel{
    private List<PessoaFisica> lstPessoaFisica;
    
    public PessoaFisicaTableModel(){
        lstPessoaFisica = new ArrayList<PessoaFisica>();
    }
    
    public PessoaFisicaTableModel(List<PessoaFisica> lista){
        this();
        lstPessoaFisica.addAll(lista);
    }
    
    @Override
    public int getRowCount(){
        return lstPessoaFisica.size();
    }
    
    @Override
    public int getColumnCount(){
        return 3;
    }
    
    @Override
    public String getColumnName(int column){
        switch(column){
            case 0: return "Nome";
            case 1: return "Email";
            case 2: return "Telefone";
            default: return "";
        }
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex){
        PessoaFisica pf = lstPessoaFisica.get(rowIndex);
        if (columnIndex == 0)
            return pf.getNome();
        else if(columnIndex == 1)
            return pf.getEmail();
        else if (columnIndex == 2)
            return pf.getFone();
        return "";
    }
    
    public PessoaFisica getPessoaFisica(int pos){
        if(pos >= lstPessoaFisica.size())
            return null;
        return lstPessoaFisica.get(pos);
    }
}
