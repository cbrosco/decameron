<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Administration</title>
</head>
<body>
<h1>Admin</h1>
Enter data for a new map:
<br>
<form action="StoryCreationServlet" method="post">
Day:
<select name="giorno">
<option value="1" selected>1</option>
<option value="2">2</option>
<option value="3" >3</option>
<option value="4">4</option>
<option value="5">5</option>
<option value="6">6</option>
<option value="7">7</option>
<option value="8">8</option>
<option value="9">9</option>
<option value="10">10</option>
</select>
<br>
Story number:
<select name="num">
<option value="1" selected>1</option>
<option value="2">2</option>
<option value="3" >3</option>
<option value="4">4</option>
<option value="5">5</option>
<option value="6">6</option>
<option value="7">7</option>
<option value="8">8</option>
<option value="9">9</option>
<option value="10">10</option>
</select>
<br>
Story teller:
<select name="teller">
<option value="Pampinea" selected>Pampinea</option>
<option value="Filomena ">Filomena</option>
<option value="Neifile" >Neifile</option>
<option value="Filostrato">Filostrato </option>
<option value="Fiammetta">Fiammetta</option>
<option value="Elissa">Elissa</option>
<option value="Dioneo">Dioneo</option>
<option value="Lauretta">Lauretta</option>
<option value="Emilia">Emilia</option>
<option value="Panfilo">Panfilo</option>
</select>
<br>
Extra info:<br>
<textarea rows="4" cols="30" name= "extra">
</textarea>
<br>
How many locations will you be entering?
<input type="text" name="numberOfLocs"><br>

<input type="submit" value="Continue">
</form>

</body>
</html>