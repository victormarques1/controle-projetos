package tableModel;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.PessoaJuridica;

public class PessoaJuridicaTableModel extends AbstractTableModel {
    private List<PessoaJuridica> lstPessoaJuridica;
    
    public PessoaJuridicaTableModel(){
        lstPessoaJuridica = new ArrayList<PessoaJuridica>();
    }
    
    public PessoaJuridicaTableModel(List<PessoaJuridica> lista){
        this();
        lstPessoaJuridica.addAll(lista);
    }
    
    @Override
    public int getRowCount(){
        return lstPessoaJuridica.size();
    }
    
    @Override
    public int getColumnCount(){
        return 3;
    }
    
    @Override
    public String getColumnName(int column){
        switch(column){
            case 0: return "NomeFantasia";
            case 1: return "Email";
            case 2: return "Telefone";
            default: return "";
        }
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex){
        PessoaJuridica pj = lstPessoaJuridica.get(rowIndex);
        if (columnIndex == 0)
            return pj.getNomeFantasia();
        else if(columnIndex == 1)
            return pj.getEmail();
        else if (columnIndex == 2)
            return pj.getFone();
        return "";
    }
    
    public PessoaJuridica getPessoaJuridica(int pos){
        if(pos >= lstPessoaJuridica.size())
            return null;
        return lstPessoaJuridica.get(pos);
    }
}
