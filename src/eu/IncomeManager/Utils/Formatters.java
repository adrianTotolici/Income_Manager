package eu.IncomeManager.Utils;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

/**
 * Created by adrian on 23.02.2014.
 */
public class Formatters {
    public static final SimpleDateFormat DATE_FORMAT=new SimpleDateFormat("yyyy-MM-dd");
    public static final SimpleDateFormat DATE_FORMAT_RO=new SimpleDateFormat("dd-MM-yyyy");

    public static final SimpleDateFormat DATE_FORMAT_SHORT=new SimpleDateFormat("MM-dd");
    public static final SimpleDateFormat DATE_FORMAT_RO_SHORT=new SimpleDateFormat("dd-MM");

    public static final SimpleDateFormat DATE_FORMAT_SHORT2=new SimpleDateFormat("MM-yyyy");
    public static final SimpleDateFormat DATE_FORMAT_SIMPLE=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

    public static final DecimalFormat NUMBER_2DECIMALS=new DecimalFormat("###0.##");
}
