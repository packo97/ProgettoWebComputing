<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>

	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"> 
	 <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
  	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
	
	<link rel="stylesheet" href="../css/index.css" type="text/css">
	<script type="text/javascript" src="../js/index.js"></script>
	
	<meta charset="UTF-8">
	<title>ALLENATI AL VAR - Login page</title>
</head>

<body>

	<div align="center" class="row">
	
		<div class="column col-sm-3"></div>
		<div class="column col-sm-6 wrapper fadeInDown">
		  	
		  	<div id="formContent">
			 	<div class="fadeIn first">
		      		<img src="../img/referee.png" id="icon" alt="User Icon" />
		    	</div>
		    	
		    	<form method="POST" action="login?login=true">
		      	
			      	<c:if test="${registrazioneEffettuata != null}">
			      		<div class="alert alert-success alert-dismissible fade show" id="formatRegistrazione" role="alert">
							<strong>Registrazione avvenuta con successo!</strong>
						</div>
			      	</c:if>
		      	
		      		<input type="email" id="email" class="fadeIn second" name="email" placeholder="email" required/>	
		      		<input type="password" id="password" class="fadeIn second" name="password" placeholder="password" required/>
			      	
			      	<c:if test="${loginSbagliato != null}">
			      		<div id="loginErrato" class="inactive">
			      			<div id="formatErrato" class='alert alert-danger alert-dismissible fade show' role='alert'><strong>Email e/o password errati!</strong></div>
			      		</div>
			      	</c:if>
		      	
		      		<br>
		      		<button class="btn btn-primary" id="loginBTN" type="submit">Login</button>
		    	</form>
		
		    	<div id="formFooter">
					<div class="d-flex justify-content-center links">
						Non hai un account?<a id="buttonRegistrati" class="btn btn-secondary" type="submit" href="gestorePagine?pagina=registrati">Registrati</a>
					</div>
				</div>
			</div>
		</div>
		<div class="column col-sm-3"></div>
	</div>
</body>
</html>