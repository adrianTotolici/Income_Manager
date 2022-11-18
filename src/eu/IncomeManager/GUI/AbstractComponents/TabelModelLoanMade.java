package eu.IncomeManager.GUI.AbstractComponents;

import eu.IncomeManager.Utils.Constante;
import eu.IncomeManager.Utils.Formatters;
import eu.IncomeManager.dataBase.Loans;
import eu.IncomeManager.dataBase.Luxuries;

import javax.swing.table.AbstractTableModel;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by adrian on 06.03.2014.
 */
public class TabelModelLoanMade extends AbstractTableModel {

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
        if (linkedList.get(rowIndex).getTip_imprumut().equalsIgnoreCase(Constante.IMPRUMUT_DAT)){
          switch (columnIndex){
              case 0:
                  return rowIndex+1;
              case 1:
                  return linkedList.get(rowIndex).getPerson_name();
              case 2:
                  return linkedList.get(rowIndex).getValue();
              case 3:
                  return Formatters.DATE_FORMAT_RO.format(linkedList.get(rowIndex).getDate_made());
              case 4:
                  if ((linkedList.get(rowIndex).getDate_limit())!=null){
                      return Formatters.DATE_FORMAT_RO.format(linkedList.get(rowIndex).getDate_limit());
                  }else {
                      return "NO LIMIT";
                  }
              case 5:
                  return linkedList.get(rowIndex).getDobanda_type();
              case 6:
                  return linkedList.get(rowIndex).getDobanda();
          }
        }
        return null;
    }

    public Loans getValueAtRow(int row){
        Loans loans = linkedList.get(row);
        return loans;
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

    public Loans getLoan(int index){
        Loans loans =linkedList.get(index);
        return loans;
    }

    public void removeAllLoans(){
        linkedList=new LinkedList<Loans>();
        fireTableDataChanged();
    }


}
