package eu.IncomeManager.Utils.Language;

import eu.IncomeManager.Utils.Formatters;

import java.util.Date;

/**
 * Created by adrian on 2/18/14.
 */
public class RomanianLang extends DefaultLang {
    public RomanianLang(){
        income_organizer="Administrator venituri  v0.4.1";
        product_name="Nume produs / Firma";
        product_price="Pret produs";
        cashback="Reducere";
        add_new_product="Adauga produs nou";
        pruchase_date="Data achizitionarii";
        date_format_like_MM_dd="Formatul de data acceptat este dd-MM";
        lang_active_for_strig_conversion="dd-MM-yyyy";
        lang_active_for_string_conversion2="dd-MM-yyyy";
        enter_products_company_name="Numele companiei produsului";
        remove_last_product="Sterge ultimul produs";
        add_buyback_product="Adauga reducere";
        must_be_completed="(*) Trebuie completat inainte de adaugare !!!";

        NR="NR.";
        quantity="Cantitate";

        income="Venituri";
        expenses="Cheltuieli";

        month_income="Venit pe luna";
        extra_income="Venit suplimentar";
        last_month_income="Venit de luna trecuta";
        save_new_income="Salveaza";

        today_savings="Economie pe azi";
        week_savings="Economie pe saptamana";
        month_savings="Economie pe luma";

        buy_today="Produse_t cumparate";
        products_from_date="Afis produse din data";
        add_today_buy="Adaugare produse cumparate azi";
        afis_products="Afiseaza produse";

        bill_name="Nume factura";
        bill_value="Valoare factura";
        month_bills="Facturi lunare";
        add_bill="Adauga factura";
        remove_last_bill="Stergere ultima factura";
        bill_date="Data factura";

        today_expenses="Cheltuieli pe azi";
        week_expenses="Cheltuieli pe saptamana";
        month_expenses="Cheltuieli pe luna";

        loans="Imprumuturi";
        person_name="Nume";
        value="Valoare";
        date_made="Realizata in";
        date_limit="Pana in data";
        interest_type="Tip Dobanda";
        interest_value="Valoare Dobanda";
        end_loan="Imprumut incheiat";

        give_new_loan="Imprumut nou";
        pay_loan="Plata imprumut";
        add_new_made_loan="Imprumut acordat";
        pay_total="Plateste total";
        pay_partial="Plateste partial";

        combolist=new String[]{"Fara dobanda","Dobanda pe zi","Dobanda pe luna"};
        add_loan="Imprumut nou";
        cancel_loan="Anuleaza";

        add_buyBack="Adauga reducere";
        cashback_name="Nume reducere";
        cashback_value="Valoare reducere";
        cashback_date="Data reducere";

        bill_date="Data facturi";
        show_bills="Arata facturi";

        luxuries="Produse de lux";
        add_luxuries="Adauga produs";
        remove_luxuries="Sterge ultimul produs";
        date="Data";
        total_luxuries="Total produs de lux";
        total_bills="Total facturi";

        statistics="Statistica";
        statistics_for_month="Statistica pe luna";
        show_statistics="Afiseaza statistici";

        setari="Setari";
        iesire="Iesire";
        localIp="Ip desktop";
        mobileIp="Ip telefon";
        noIpDetected="Nu s-a detectat Ip-ul.";
        save="Salveaza setari";
        cancel="Anuleaza";

        language="Limba";
        save_data_base="Salveaza baza de date in fisiere.";
        load_data_base="Incarca baza de date din fisier.";
    }

    @Override
    public String dateSet(Date date) {
        return Formatters.DATE_FORMAT_RO_SHORT.format(date);
    }
}
