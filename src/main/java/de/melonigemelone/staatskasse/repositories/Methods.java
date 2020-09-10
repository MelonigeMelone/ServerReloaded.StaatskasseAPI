package de.melonigemelone.staatskasse.repositories;

public class Methods {

    public static boolean isInteger(String value) {
        try{
            Integer.parseInt(value);
            return true;
        }
        catch(NumberFormatException e){
            return false;
        }
    }
    public static boolean isDouble(String value) {
        try{
            Double.parseDouble(value);
            return true;
        }
        catch(NumberFormatException e){
            return false;
        }
    }
}
