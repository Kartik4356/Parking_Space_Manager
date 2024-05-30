<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Your Parker</title>
  <style>
    body {
      font-family: Arial, sans-serif;
      margin: 0;
      padding: 0;
      background-image :url('porsh.jpeg');
           background-size: cover;
      
    }

    header {
      background-color: #000000db;
      color: white;
      padding: 1px;
      text-align: center;
    }
    
    .z{
    color: white;}

    nav {
      background-color: #000000a8;
      color: #FFFFFF;
      width: 250px;
      height: 640px;
      padding: 20px;
      box-sizing: border-box;
      float: left;
    }

    nav a {
      color: #FFFFFF;
      text-decoration: none;
      padding: 10px;
      display: inline-block;
    }

    nav a:hover {
      background-color: #777;
      border-radius:10px;
    }

    .a1 {
      border: 2px solid rgba(255, 255, 255, 0.75);
      border-radius: 5px;
      width: 60px;
      text-align: center;
      margin-top: 30px;
    }

    .a2 {
      border: 2px solid rgba(255, 255, 255, 0.75);
      border-radius: 5px;
      width: 60px;
      text-align: center;
      margin: 0px 0px 0px 30px;
    }

    .content {
      margin-left: 250px;
      padding: 20px;
      box-sizing: border-box;
    }

 
    
  </style>
</head>
<body>

  <header>
    <h1>Easy Parker</h1>
  </header>

  <nav>
    <a href="in.jsp" class="a1" target="iframe">In</a>
    <a href="out.jsp" class="a2" target="iframe">Out</a><br><br><br>
	<p><a href="firstpg" target="iframe">Dashboard</a></p>  
    <p><a href="Space" target="iframe">Vehicle in parking</a></p>
    <p><a href="Empp.jsp" target="iframe">Employee</a></p>  
    <p><a href="Logout">Signout</a></p>

  </nav>

  <div class="content">
    <iframe src="firstpg" name="iframe" width="100%" height="585px" title="Iframe"></iframe>
    <p></p>
  </div>

  
</body>
</html>
