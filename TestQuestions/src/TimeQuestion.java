import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class TimeQuestion {

    public static void main(String args[]) throws IOException {
        String time = "23:05:38";
        String[] lowTimes = {"23:05:38",
        "23:05:02",
        "23:04:59",
        "23:04:31",
        "12:36:07",
        "08:59:01",
        "00:00:00"};

        String[] results = solve(lowTimes,time);
        for(String s: results) {
            System.out.println(s);
        }
    }
    public static String[] solve(String[] time, String rs) {
        char[] r = rs.toCharArray();
        String[] res = new String[time.length];
        int h = (r[0]-'0')*10 + r[1] - '0';
        int m = (r[3]-'0')*10 + r[4] - '0';
        int s = (r[6]-'0')*10 + r[7] - '0';
        int i = 0;
        for (String str : time) {
            String ans;
            int h1 = (str.charAt(0)-'0')*10 + str.charAt(1) - '0';
            int m1 = (str.charAt(3)-'0')*10 + str.charAt(4) - '0';
            int s1 = (str.charAt(6)-'0')*10 + str.charAt(7) - '0';
            int vl = (h - h1) * 3600 + (m - m1) * 60 + s - s1;
            int hh = vl/3600, mm = vl/60, ss = vl;
            if(hh > 0) {
                if(hh == 1) ans = hh + " hour ago";
                else ans = hh + " hours ago";
            }
            else if (mm > 0){
                if(mm == 1) ans = mm + " minute ago";
                else ans = mm + " minutes ago";
            }
            else if(ss > 0) {
                if(ss == 1) ans = ss + " second ago";
                else ans = ss + " seconds ago";
            }
            else ans = "now";

            res[i] = ans;
            i++;
        }
        return res;
    }
}
