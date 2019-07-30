package utils;

public class Maths {


    public static String subStringBetween(String src, String from, String to) {
        return src.substring(src.indexOf(from) + from.length(), src.indexOf(to));
    }

}