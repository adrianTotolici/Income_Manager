package eu.IncomeManager.Utils;

/**
 * Created by adrian on 06.03.2014.
 */
public  class  Constante {
    public static String SETTING_FILE_PATH="";
    public static String IMPRUMUT_DAT="dat";
    public static String IMPRUMUT_LUAT="primit";
    public static String DEFAULT_NAME="";

    public static int TEXT_INPUT_LENGTH=15;

    public static int soketMobile=7878;
    public static int soketDesktop=8887;

    public static String mobileName="mobileManager";
    public static String desktopName="desktopManager";

    public static String desktopIP="0";  // laptop
    public static String mobileIP="0";  // phone
    public static String language="en";

    public static synchronized void setDesktopIP(String desktopIP) {
        Constante.desktopIP = desktopIP;
    }

    public static synchronized void setMobileIP(String mobileIP) {
        Constante.mobileIP = mobileIP;
    }

    public static synchronized void setLanguage(String language) {
        Constante.language = language;
    }

    // --- XML TAG CONSTANT-------------------

    public static String depozitXMLTAG="depozit";
    public static String billXMLTAG="bill";
    public static String incomeXMLTAG="income";
    public static String produsXMLTAG="produse";
    public static String luxuriesXMLTAG="luxuries";
    public static String loansXMLTAG="loans";

    public static String depozitFile="/depozit.xml";
    public static String billsFile="/bills.xml";
    public static String incomeFile="/income.xml";
    public static String produseFile="/produse.xml";
    public static String luxuriesFile="/luxuries.xml";
    public static String loansFile="/loans.xml";

    public static String database="database";
    public static String nume="nume";
    public static String idDepozit="idDepozit";
    public static String value="value";
    public static String data="data";
    public static String pret="pret";
    public static String extra="extra";
    public static String savings="savings";
    public static String company="company";
    public static String quantity="quantyti";
    public static String reducere="reducere";
    public static String dateMade="dateMade";
    public static String dateLimit="dateLimit";
    public static String dobanda="dobanda";
    public static String dobandaType="dobandaType";
    public static String tipImprumut="tipImprumut";
    public static String incheiat="incheiat";
}
