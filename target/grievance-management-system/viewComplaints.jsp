<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.*" %>

<h2>All Complaints</h2>

<table border="1">
<tr>
    <th>ID</th>
    <th>Employee ID</th>
    <th>Description</th>
    <th>Status</th>
    <th>Action</th>
</tr>

<%
List<String[]> complaints = (List<String[]>) request.getAttribute("complaints");

if (complaints != null) {
    for (String[] c : complaints) {
%>
<tr>
    <td><%= c[0] %></td>
    <td><%= c[1] %></td>
    <td><%= c[2] %></td>
    <td><%= c[3] %></td>
    <td>
        <form action="updateStatus" method="post">
            <input type="hidden" name="id" value="<%= c[0] %>">

            <select name="status">
                <option value="OPEN">OPEN</option>
                <option value="IN_PROGRESS">IN_PROGRESS</option>
                <option value="RESOLVED">RESOLVED</option>
            </select>

            <button type="submit">Update</button>
        </form>
    </td>
</tr>
<%
    }
}
%>
</table>

<br>
<a href="adminDashboard.jsp">Back</a>