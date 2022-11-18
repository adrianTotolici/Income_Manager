import eu.IncomeManager.GUI.MainPanel;
import eu.IncomeManager.Utils.Utils;
import eu.IncomeManager.communication.Server;

public class Main {
    public static void main(String[] args) {
        Utils.createSetingsFile();
        Utils.iniSettings();

        MainPanel.getInstance().showMainTabPanel();
        Server.getFirstInstance();
    }
}
