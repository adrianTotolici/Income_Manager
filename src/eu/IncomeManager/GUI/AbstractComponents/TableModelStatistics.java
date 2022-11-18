package eu.IncomeManager.GUI.AbstractComponents;

import eu.IncomeManager.Utils.Formatters;
import eu.IncomeManager.dataBase.Produse;

import javax.swing.table.AbstractTableModel;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by adrian on 4/20/14.
 */
public class TableModelStatistics extends AbstractTableModel {
    List<String> tableHeaders;

    int curretnRow;
    private LinkedList<Produse> linkedList=new LinkedList<Produse>();

    @Override
    public int getRowCount() {
        return linkedList.size();
    }

    @Override
    public int getColumnCount() {
        return tableHeaders.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        curretnRow=rowIndex;
        switch (columnIndex){
            case 0:
                return rowIndex+1;
            case 1:
                return linkedList.get(rowIndex).getNume();
            case 2:
                return Formatters.NUMBER_2DECIMALS.format(linkedList.get(rowIndex).getPret());
            case 3:
                return Formatters.NUMBER_2DECIMALS.format(linkedList.get(rowIndex).getCantitate());
            case 4:
                double pret = linkedList.get(rowIndex).getPret();
                double cantitate=linkedList.get(rowIndex).getCantitate();
                return Formatters.NUMBER_2DECIMALS.format(pret*cantitate);
        }
        return null;
    }

    @Override
    public String getColumnName(int column) {
        return tableHeaders.get(column);
    }

    public void initTable(List<String> tableHeaders){
        this.tableHeaders=tableHeaders;
    }

    public void addProduseStatistics(Produse produseTStatistics){
        linkedList.add(produseTStatistics);
        fireTableDataChanged();
    }

    public void removeAllProduseStatistics(){
        linkedList=new LinkedList<Produse>();
        fireTableDataChanged();
    }
}
