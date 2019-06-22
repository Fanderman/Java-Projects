<%@ page isErrorPage="true" %>
<%@ page import="z9.LucasNumbers,java.math.BigInteger" %>

<html>
    <head>
        <title>Lucas Numbers</title>
    </head>
    <body>
        <%
            out.print(LucasNumbers.lucasTable(new BigInteger("2"), new BigInteger("1"), 10));
        %>
    </body>
</html>
