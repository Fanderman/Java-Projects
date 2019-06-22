<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="z9.LucasNumbers,java.math.BigInteger" %>

<html>
    <head>
        <title>Lucas Numbers</title>
    </head>
    <body>
        <%
            try
            {
		BigInteger x = new BigInteger(request.getParameter("x"));
		BigInteger y = new BigInteger(request.getParameter("y"));
		int n = Integer.parseInt(request.getParameter("n"));
		out.print(LucasNumbers.lucasTable(x, y, n));
            }
            catch(Exception e){
		out.print(LucasNumbers.lucasTable(new BigInteger("2"), new BigInteger("1"), 10));
            }		
        %>
    </body>
</html>
