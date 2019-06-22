package z9;


import java.math.BigInteger;


public class LucasNumbers {

    public static BigInteger[] intTable (BigInteger first, BigInteger second, int n) {
        BigInteger[] lst = new BigInteger[n+1];

        for (int i = 0; i <= n; i++) {
            if (i == 0)
                lst[0] = first;
            else if (i == 1)
                lst[1] = second;
            else
                lst[i] = lst[i-1].add(lst[i-2]);
        }

        return lst;
    }

    public static String lucasTable (BigInteger first, BigInteger second, int n) {
        StringBuilder s = new StringBuilder("");
        s.append("<table width=\"100%\">");

        s.append("<tr>");
        for (int i = 0; i <= n; i++) {
            s.append("<th>" + i + "</th>");
        }
        s.append("</tr>");

        BigInteger[] lucasNumbers = intTable(first, second, n);

        s.append("<tr>");
        for (BigInteger i : lucasNumbers)
            s.append("<td align=\"center\">" + i + "</td>");
        s.append("</tr>");

        s.append("</table>");

        return s.toString();
    }

}
