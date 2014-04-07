
package tmf.org.dsmapi.commons.utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

/**
 *
 * @author user
 */
public class FormatUtils {
    public static final String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss";
    
    public static Date parse(String input) throws java.text.ParseException {

        //NOTE: SimpleDateFormat uses GMT[-+]hh:mm for the TZ which breaks
        //things a bit.  Before we go on we have to repair this.
        SimpleDateFormat df = new SimpleDateFormat(DATE_FORMAT);

        return df.parse(input);

    }
    
    public static String format(Date input) throws java.text.ParseException {

        //NOTE: SimpleDateFormat uses GMT[-+]hh:mm for the TZ which breaks
        //things a bit.  Before we go on we have to repair this.
        SimpleDateFormat df = new SimpleDateFormat(DATE_FORMAT);

        return df.format(input);

    }
    

    public static String getDBString(List<String> list)
    {
        String str="";
        boolean first = true;
        for(String s:list)
        {
            if(first)
            {
                first = false;
                str += s;
            }
            else
            {
                str += ","+s;
            }
        }
        return str;
    }
    
    public static List<String> getDisplayList(String str)
    {
        List<String> strList = new ArrayList<String>();
        
        if(str != null && str.length() >0)
        {
            StringTokenizer st = new StringTokenizer(str,",");

            while(st.hasMoreTokens())
            {
                strList.add(st.nextToken());
            }
        }
        
        return strList;
    }    
    
    public static String getLinearTypeId(String str)
    {
        String s = null;
        if(str != null && str.length()>0)
        {
            s = str.substring(0, str.indexOf(","));
//            StringTokenizer st = new StringTokenizer(str,",");
//            s = st.nextToken();
        }
        return s;
    }
    
    public static String getLinearTypeValue(String str)
    {
        String s = null;
        if(str != null && str.length()>0)
        {
            s = str.substring(str.indexOf(",")+1, str.length());
        }
        return s;
    }
}
