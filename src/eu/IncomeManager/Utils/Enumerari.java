package eu.IncomeManager.Utils;

/**
 * Created by adrian on 06.03.2014.
 */
public class Enumerari {

    public enum DobandaType{
        FaraDobanda(0),
        DobandaPeZi(1),
        DobandaPeSuma(2);

        private Integer id;
        DobandaType(Integer id){
            this.id=id;
        }
    }

    public enum Language{
        Romanian("ro"),
        Englaza("en");

        private String lang;
        Language(String  lang){
            this.lang=lang;
        }

        public String getLang() {
            return lang;
        }
    }

}
