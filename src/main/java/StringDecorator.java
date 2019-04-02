public class StringDecorator {

    public static String td25(String text){
        return "<td width=\"25%\">"+ text + "</td>";
    }

    public static String td15(String text){
        return "<td width=\"15%\">"+ text + "</td>";
    }

    public static String td20(String text){
        return "<td width=\"20%\">"+ text + "</td>";
    }

    public static String td10(String text){
        return "<td width=\"10%\">"+ text + "</td>";
    }

    public static String h1(String text){
        return "<h1>"+ text + "</h1>";
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

    public static String closeHTML(){
        return "</meta>\n</html>";
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

    public static String th25(String text){
        return "<th width=\"25%\" align=\"left\">" + text + "</th>";
    }

    public static String th15(String text){
        return "<th width=\"15%\" align=\"left\">" + text + "</th>";
    }

    public static String th20(String text){
        return "<th width=\"20%\" align=\"left\">" + text + "</th>";
    }

    public static String th10(String text){
        return "<th width=\"10%\" align=\"left\">" + text + "</th>";
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