package eu.IncomeManager.Utils;

import org.apache.commons.io.FileUtils;

import java.io.*;
import java.net.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * Created by adrian on 21.02.2014.
 */
public class Utils {

    /**
     * metoda ce returneaza luna
     *
     * @param date
     * @return
     */
    public static int getMonth(Date date){
        Calendar calendar= Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.MONTH)+1;
    }

    public static Date getLastMonth(Date date){
        Calendar calendar= Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH,-1);
        return calendar.getTime();
}

    public static Date setDay(int day, Date date){
//        date.setDate(day);
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH,day);
        return calendar.getTime();
    }

    public static Date setHour(int hour, Date date){
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY,hour);
        return calendar.getTime();
    }

    public static Date setMonth(int month, Date date){
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH,+month);
        return calendar.getTime();
    }

    /**
     * metoda ce returneaza ziua din luna
     *
     * @param date
     * @return
     */
    public static int getDay(Date date){
        Calendar calendar= Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    public static int getHour(Date date){
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.HOUR_OF_DAY);
    }

    public static int getMinutes(Date date){
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.MINUTE);
    }

    public static int getSeconds(Date date){
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.SECOND);
    }

    public static int getMillisecond(Date date){
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.MILLISECOND);
    }

    /**
     * metoda ce returneaza anul
     *
     * @param date
     * @return
     */
    public static int getYear(Date date){
        Calendar calendar= Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.YEAR);
    }

    /**
     * metoda ce creaza o data din ziua,luna si anul dat ca parametri
     *
     * @param day
     * @param month
     * @param year
     * @return
     */
    public static Date getLastDateOfTheMonth(int day, int month, int year){
        Date date;
        Calendar calendar= Calendar.getInstance();
        calendar.set(Calendar.DATE, day);
        calendar.set(Calendar.MONTH, month-1);
        calendar.set(Calendar.YEAR, year);
        date=calendar.getTime();
        return date;
    }

    /**
     * metoada ce returneaza ziua saptamani din data curenta
     *
     */
    public static int getDayOfWeek(Date date){
        Calendar calendar= Calendar.getInstance();
        calendar.setTime(date);
        int day_of_week=calendar.get(Calendar.DAY_OF_WEEK);
        if (day_of_week==1){
            return 7;
        }
        day_of_week=day_of_week-1;
        return day_of_week;
    }

    /**
     * metoda prin care se returneaza numarul de zile ale saptamani din care face parte data
     *
     */
    public static int getNrDaysOfCurrentWeek(Date date){
        int curent_day=getDay(date);
        int day_of_the_week=getDayOfWeek(date);
        int nr_de_zile_ramase=7-day_of_the_week;
        int nr_zile_pe_luna=getDaysFromMonth(date);

        int month=getMonth(date);
        int year=getYear(date);

        Date last_date_of_month=getLastDateOfTheMonth(nr_zile_pe_luna, month, year);
        int last_day_of_the_week_on_month=getDayOfWeek(last_date_of_month);


        if (curent_day==nr_zile_pe_luna){
            return 1;
        }

        if ((curent_day<7)&&(nr_de_zile_ramase+curent_day<=7)){
            return nr_de_zile_ramase+curent_day;
        }


        if ((last_day_of_the_week_on_month<7)&&(nr_zile_pe_luna-curent_day<=7)){
            return last_day_of_the_week_on_month;
        }
        return 7;
    }

    /**
     * metoda ce returneaza data primei zile din saptamana
     *
     */
    public static int getFirstDayOfTheWeek(Date date){
        int day_of_week=getDayOfWeek(date);
        int day=getDay(date);

        if (day_of_week==1){
            return day;
        }

        return day-(day_of_week-1);
    }

    /**
     * metoda ce returneaza numarul maxim de zile
     *
     */
    public static int getDaysFromMonth(Date date){
        Calendar calendar= Calendar.getInstance();
        calendar.setTime(date);
        int days=calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        return days;
    }

    public static Date getSimpleDate(Date date, boolean activeYear, boolean activeMonth,boolean activeDay,
                                     boolean activeHours, boolean activeMinutes, boolean activeSeconds, boolean activeMilliseconds){

        Calendar calendar= Calendar.getInstance();

        if (activeYear){
            int year = getYear(date);
            calendar.set(Calendar.YEAR, year);
        }
            else calendar.set(Calendar.YEAR, 0);

        if (activeMonth){
            int month = getMonth(date);
            calendar.set(Calendar.MONTH, month - 1);
        } else calendar.set(Calendar.MONTH,0);

        if (activeDay){
            int day = getDay(date);
            calendar.set(Calendar.DATE, day);
        } else calendar.set(Calendar.DATE,1);

        if (activeHours){
            int hours= getHour(date);
            calendar.set(Calendar.HOUR_OF_DAY, hours);
        } else calendar.set(Calendar.HOUR_OF_DAY,0);

        if (activeMinutes){
            int minutes=getMinutes(date);
            calendar.set(Calendar.MINUTE, minutes);
        } else calendar.set(Calendar.MINUTE,0);

        if (activeSeconds){
            int seconds=getSeconds(date);
            calendar.set(Calendar.SECOND,seconds);
        } else calendar.set(Calendar.SECOND,0);

        if (activeMilliseconds){
            int millisecond=getMillisecond(date);
            calendar.set(Calendar.MILLISECOND,millisecond);
        } else calendar.set(Calendar.MILLISECOND,0);

        return calendar.getTime();
    }
    /**
     * metoda care verifica daca o data este data curenta
     *
     */
    public static boolean verify_is_curent_day(Date date){
        Date today=new Date();

        int zi_date=getDay(date);
        int luna_date=getMonth(date);
        int an_date=getYear(date);

        int zi_today=getDay(today);
        int luna_today=getMonth(today);
        int an_today=getYear(today);

        boolean is_curent_day=false;

        if ((zi_date==zi_today)&&(luna_date==luna_today)&&(an_date==an_today)){
            is_curent_day=true;
        }
        return is_curent_day;
    }

    public static boolean verify_is_curent_month(Date date){
        Date today=new Date();
        int luna_date=getMonth(date);
        int an_date=getYear(date);

        int luna_today=getMonth(today);
        int an_today=getYear(today);

        boolean is_curent_month=false;

        if ((luna_date==luna_today)&&(an_date==an_today)){
            is_curent_month=true;
        }
        return is_curent_month;
    }

    public static Date convertStringtoDate(String string){
        Date returnDate=null;
        try {
            returnDate=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", Locale.ROOT).parse(string);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return returnDate;
    }

    /**
     * Get a diff between two dates
     *
     * @param date1 the oldest date
     * @param date2 the newest date
     * @param timeUnit the unit in which you want the diff
     * @return the diff value, in the provided unit
     */
    public static long getDateDiff(Date date1, Date date2, TimeUnit timeUnit) {
        long diffInMillies = date2.getTime() - date1.getTime();
        return timeUnit.convert(diffInMillies, TimeUnit.MILLISECONDS);
    }

    public static String getLocalIp(){
        String ipAdress=null;
            try {
                for (Enumeration en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements();) {
                    NetworkInterface intf = (NetworkInterface) en.nextElement();
                    for (Enumeration enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements();) {
                        InetAddress inetAddress = (InetAddress) enumIpAddr.nextElement();
                        if (!inetAddress.isLoopbackAddress() && inetAddress instanceof Inet4Address) {
                            ipAdress=inetAddress.getHostAddress().toString();
                        }
                    }
                }
            } catch (SocketException ex) {
                ex.printStackTrace();
            }
        return ipAdress;
    }

    public static void iniSettings(){
            FileReader isr = null;
            File objFile = new File(Constante.SETTING_FILE_PATH);
            try {
                isr = new FileReader(objFile);
            } catch (FileNotFoundException e) {
                System.err.println("Settings file not found in res folder!");
                System.exit(-1);
            }
            BufferedReader reader = new BufferedReader(isr);
            String line;

            try {
                while (true) {
                    line = reader.readLine();
                    if (line==null){
                        break;
                    }
                    if (line.startsWith("ipDesktop: ")) {
                        String[] currentLine = line.split(" ");
                        Constante.desktopIP=currentLine[1];

                    } else if (line.startsWith("ipMobile: ")) {
                        String[] currentLine = line.split(" ");
                        Constante.mobileIP=currentLine[1];

                    } else if (line.startsWith("ln: ")) {
                        String[] currentLine = line.split(" ");
                        Constante.language=currentLine[1];
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.err.println("Error reading the file");
                System.exit(-1);
            }
        try {
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void saveSettings(){
        try {
            PrintWriter writer = new PrintWriter(Constante.SETTING_FILE_PATH, "UTF-8");
            writer.println("ipDesktop: "+Constante.desktopIP);
            writer.println("ipMobile: "+Constante.mobileIP);
            writer.println("ln: "+Constante.language);
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }

    public static void createSetingsFile(){
        String dirFile="";
        String pathFile="";
        String dataBaseDir="";
        String rootPath=getPath();

        dirFile="/IncomeManager2";
        dataBaseDir="/DataBaseBackup";
        pathFile="/IncomeManager2/settings.txt";

        String pathname = rootPath + dirFile+dataBaseDir;
        boolean success = new File(pathname).mkdirs();
        if (!success) {
            System.out.println("Cant create files !");
        }

        Constante.SETTING_FILE_PATH=rootPath+pathFile;
        File f = new File(Constante.SETTING_FILE_PATH);
        if(!f.exists() && !f.isDirectory()) {
            saveSettings();
        }
    }

    public static String getPath(){
        String os=detectEnvoirment();
        String dirFile="";
        String rootPath="";

        if (os.equals("win")){
            rootPath="C:/";
        }else if (os.equals("linux")){
            rootPath=System.getProperty("user.home");
        }

        return rootPath;
    }

    public static void exportFileToHdd(String scr, String dest){
        try {
            //name of source file
            File sourceFile = new File(scr);
            String name = sourceFile.getName();

            File targetFile = new File(dest +"/"+ name);
            System.out.println("Copying file : " + sourceFile.getName() + " from Java Program");

            //copy file from one location to other

            FileUtils.copyFile(sourceFile, targetFile);
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String detectEnvoirment(){
        String OS = System.getProperty("os.name").toLowerCase();
            if (OS.contains("win")) {
                return "win";
            } else if (OS.indexOf("mac")>0) {
                return "mac";
            } else if (OS.indexOf("nix") >= 0 || OS.indexOf("nux") >= 0 || OS.indexOf("aix") > 0 ) {
                return "linux";
            } else if (OS.indexOf("sunos") >= 0) {
                return "sunos";
            } else {
                return null;
            }
        }

    public static void writeToFile(String fileName,String directories, String dataString){
        String rootPath="";
        String os = detectEnvoirment();
        String dirFile="";
        String pathFile="";

        if (os.equals("win")){
            rootPath="C:/";
        }else if (os.equals("linux")){
            rootPath=System.getProperty("user.home");
        }
        dirFile="/IncomeManager2"+directories;
        pathFile="/IncomeManager2/"+directories+"/"+fileName;

        String pathname = rootPath + dirFile;
        boolean success = new File(pathname).mkdirs();
        if (!success) {
            System.out.println("Cant create files !");
        }

        String filePath=rootPath+pathFile;
        File f = new File(filePath);
        if(!f.isDirectory()) {
            try {
                PrintWriter writer = new PrintWriter(filePath, "UTF-8");
                writer.println(dataString);
                writer.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
    }

    public static void deleteFile(String filePath){
        File file=new File(filePath);
        if (file.exists()) {
            file.delete();
        }
    }
}
