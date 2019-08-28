<%@ page import="by.epam.dao.BankAccountDao" %>
<%@ page import="by.epam.payments.BankAccount" %>
<%@ page import="java.util.List" %>
<%@ page import="by.epam.payments.Client" %>
<%@ page import="by.epam.dao.ClientDao" %>
<%-- Created by IntelliJ IDEA. --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
  <head>
    <title>Web App</title>
    <link href="../stylesheet.css" rel="stylesheet">
  </head>
<body>
<div align="center">
    <h1>Bank Accounts List</h1>

<%
  BankAccountDao bankAccountDao = new BankAccountDao();
  List<BankAccount> bankAccounts = bankAccountDao.findAll();
  ClientDao clientDao = new ClientDao();
  List<Client> clients = clientDao.findAll();
  %>

<table class="table_out">
  <tr>
    <th>id</th>
    <th>Owner</th>
    <th>AccountNumber</th>
    <th>isBlocked</th>
  </tr>
    <% for (int i = 0; i < bankAccounts.size(); i++) { %>
  <tr>
    <td><%=bankAccounts.get(i).getIdAccount() %></td>
    <td><%=clients.get((bankAccounts.get(i).getOwnerId())-1).getName() %></td>
    <td><%=bankAccounts.get(i).getAccountNumber() %></td>
    <td><%=bankAccounts.get(i).getBlocked() %></td>
  </tr>
    <% } %>
</table>

    <br>
    <form action="delete" method="post" class="form">
       <input type="text" class="inputs" name="id" size="10" placeholder="Id">
       <input type="submit" class="btn" value="DELETE BY ID">
    </form>

    <form action="add" method="post" class="form">
         <input type="text" class="inputs" name="id" placeholder="Id">
         <input type="text" class="inputs" name="owner" placeholder="Owner">
         <input type="text" class="inputs" name="accNumber" placeholder="Account Number">
         <input type="text" class="inputs" name="isBlocked" placeholder="isBlocked">
         <input type="submit" class="btn" value="ADD NEW ACCOUNT">
    </form>

    <form action="update" method="post" class="form">
         <input type="text" class="inputs" name="id" placeholder="Id">
         <input type="text" class="inputs" name="owner" placeholder="Owner">
         <input type="text" class="inputs" name="accNumber" placeholder="Account Number">
         <input type="text" class="inputs" name="isBlocked" placeholder="isBlocked">
         <input type="submit" class="btn" value="UPDATE ACCOUNT">
    </form>

    <br>
    <form action="../index.html">
         <button class="btn" type="submit">Press Here to Enter Ajax</button>
    </form>

    <br>
    <form action="logout" method="post">
         <input class="btn" type="submit" value="LOGOUT" >
    </form>

</div>

</body>
</html>