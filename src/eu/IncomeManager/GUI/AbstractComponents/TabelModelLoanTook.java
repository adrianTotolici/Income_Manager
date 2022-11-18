package eu.IncomeManager.GUI.AbstractComponents;

import eu.IncomeManager.Utils.Constante;
import eu.IncomeManager.dataBase.Loans;

import javax.swing.table.AbstractTableModel;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by adrian on 06.03.2014.
 */
public class TabelModelLoanTook extends AbstractTableModel {
    List<String> tableHeaders;

    int curretnRow;
    private LinkedList<Loans> linkedList=new LinkedList<Loans>();

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
        if (linkedList.get(rowIndex).getTip_imprumut().equalsIgnoreCase(Constante.IMPRUMUT_LUAT)){
            switch (columnIndex){
                case 0:
                    return rowIndex+1;
                case 1:
                    return linkedList.get(rowIndex).getPerson_name();
                case 2:
                    return linkedList.get(rowIndex).getValue();
                case 3:
                    return linkedList.get(rowIndex).getDate_made();
                case 4:
                    return linkedList.get(rowIndex).getDate_limit();
                case 5:
                    return linkedList.get(rowIndex).getDobanda_type();
                case 6:
                    return linkedList.get(rowIndex).getDobanda();
                case 7:
                    return linkedList.get(rowIndex).isIncheiat();
            }
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

    public void addLoan(Loans loans){
        linkedList.add(loans);
        fireTableDataChanged();
    }
}
