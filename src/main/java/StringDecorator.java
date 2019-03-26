public class StringDecorator {

    public static String td(String text){
        return "<td>"+ text + "</td>";
    }

    public static String openTr(){
        return "<tr>";
    }
    public static String closeTr(){
        return "</tr>";
    }

    public static String openTable(){
        return "<table>";
    }

    public static String closeTable(){
        return "</table>";
    }

    public static String DOCType(){
        return "<!DOCTYPE HTML> \n <html lang=\"pl\"> \n <meta charset=\"utf-8\">";
    }

    public static String openHeadAndTitle(){
        return "<head> \n <title>";
    }

    public static String closeHeadAndTitle(){
        return "</title> \n </head>";
    }

    public static String openHeader(){
        return "<h1>";
    }

    public static String closeHeader(){
        return "</h1>";
    }

    public static String openLink(){
        return  "<a href=\" ";
    }
    public static String closeLink(){
        return "\" target=\"_blank\">Link</a>";
    }

    public static String th(String text){
        return "<th>" + text + "</th>";
    }

    public static String isAutomation(String text){
        if((text.toLowerCase().contains("automation")) ||
                (text.toLowerCase().contains("automatyczny")) ||
                (text.toLowerCase().contains("automatyzujący"))) {
            return "[A] " + text;
        }
        return text;
    }

}