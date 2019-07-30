package utils.time;


public class Time {

    public static enum FORMAT {
        MM_SS,
        HH_MM,
        HH_MM_SS,

        DD_HH_MM_SS;

        public String getFormat(long ms) {
            long sec = ms/1000;
            long min = sec/60;
            long hrs = min/60;
            long day = hrs/24;

            long remSec = sec%60;
            long remMin = min%60;
            long remHrs = hrs%24;

            switch(this) {
                case MM_SS      : return min+"::"+remSec; 
                case HH_MM      : return hrs+"::"+remMin;
                case HH_MM_SS   : return hrs+"::"+remMin+"::"+remSec;
                case DD_HH_MM_SS: return day+"::"+remHrs+"::"+remMin+"::"+remSec;
            }
            return "invalid format";
        }
    }

    public static long getTimeMS() {
        return System.currentTimeMillis();
    }

    public static String getFormat(long msec,FORMAT format) {
        return format.getFormat(msec);
    }
}