<head>
    <title>Employee Dashboard</title>
</head>
<body>

<h2>Employee Dashboard</h2>
<p>Welcome Employee</p>

<hr>

<h3>Raise a Complaint</h3>

<form action="raiseComplaint" method="post">
    Subject: <input type="text" name="subject" required><br><br>

    Description:<br>
    <textarea name="description" rows="5" cols="40" required></textarea><br><br>

    <input type="submit" value="Submit Complaint">
</form>

<br><br>
<a href="logout">Logout</a>

</body>
</html>

