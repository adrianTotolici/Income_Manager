package eu.IncomeManager.Utils.Language;

import eu.IncomeManager.Utils.Formatters;

import java.util.Date;

/**
 * Created by adrian on 2/18/14.
 */
public class DefaultLang {

    public String income_organizer="Income manager v0.4.1";
    public String product_name="Product name / Company";
    public String product_price="Product price";
    public String cashback="Cashback";
    public String add_new_product="Add new product";
    public String pruchase_date="Purchase date";
    public String date_format_like_MM_dd="Add format date like MM-dd";
    public String data_format_like_MM_yyyy="Add format date like MM-yyyy";
    public String lang_active_for_strig_conversion="MM-dd-yyyy";
    public String lang_active_for_string_conversion2="dd-MM-yyyy";
    public String enter_products_company_name="Enter product's company name";
    public String remove_last_product="Remove selected product";
    public String add_buyback_product="Add cashback";
    public String must_be_completed="(*) Must be completed before add !!!";

    public String dateSet(Date date){
        return Formatters.DATE_FORMAT_SHORT.format(date);
    }

    public String NR="NR.";
    public String quantity="Quantity";

    public String income="Income";
    public String expenses="Expenses";

    public String month_income="Month income";
    public String extra_income="Extra income";
    public String last_month_income="Last month income";
    public String save_new_income="Save new income";

    public String today_savings="Today savings";
    public String week_savings="Week savings";
    public String month_savings="Month savings";

    public String buy_today="Product buyed";
    public String add_today_buy="Add today's buyed products";
    public String products_from_date="Products from date";
    public String afis_products="Show products";
    public String select_depozit="Select depozit";

    public String bill_name="Bill name";
    public String bill_value="Bill value";
    public String month_bills="Monthly bills";
    public String add_bill="Add bill";
    public String remove_last_bill="Remove selected Bills";
    public String bill_date="Bill date";

    public String today_expenses="Today Expenses";
    public String week_expenses="Week Expenses";
    public String month_expenses="Month Expenses";

    public String loans="Loans";
    public String person_name="Person name";
    public String value="Value";
    public String date_made="Made on";
    public String date_limit="Payback on";
    public String interest_type="Interest Type";
    public String interest_value="Interest value";
    public String end_loan="End loan";

    public String give_new_loan="Give loan";
    public String pay_loan="Recive loan";
    public String add_new_made_loan="New loan made";
    public String pay_total="Recive all";
    public String pay_partial="Recive partial";
    public String loan="loan value";

    public String[] combolist={"No interests","Interest per day","Interest per month"};
    public String add_loan="Add loan";
    public String cancel_loan="Cancel";

    public String add_buyBack="Add cashback";
    public String cashback_name="Cashback name";
    public String cashback_value="Cashback value";
    public String cashback_date="Cashback date";

    public String bills_date="Bills date";
    public String show_bills="Show Bills";

    public String luxuries="Luxuries";
    public String add_luxuries="Add luxuries";
    public String remove_luxuries="Remove selected luxuries";
    public String date="Date";
    public String total_luxuries="Luxuries total";

    public String total_bills="Total bills";

    public String statistics="Statistics";
    public String statistics_for_month="Month statistics";
    public String show_statistics="Show statistic";

    public String main_depozit="Main depozit";
    public String add_new_depozit="Add new depozit";
    public String cancel_depozit="Cancel depozit";
    public String depozitName="Depozit name";

    public String setari="Settings";
    public String iesire="Exit";
    public String localIp="Desktop ip";
    public String mobileIp="Mobile ip";

    public String noIpDetected="No ip detected on local machine.";
    public String save="Save settings";
    public String cancel="Cancel";
    public String language="Language";

    public String save_data_base="Save data base to file.";
    public String load_data_base="Load data base from file.";
}
