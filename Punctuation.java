public class Punctuation 
{
    public static String clear (String s)
    {
        s.replace(".", "");
        s.replace("-", "");
        s.replace("!", "");
        s.replace("?", "");
        return s;
    }
}
