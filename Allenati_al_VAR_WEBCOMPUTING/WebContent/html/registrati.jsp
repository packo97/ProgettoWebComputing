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
	
	<script src="https://www.paypal.com/sdk/js?client-id=ASFVMS2x577TUF62mTrvTCl0G50REiy6OUM3HSyvwPunGbxv2OaZVMO1V-aSABkhStjc2msrCyQKWcxw"> // Required. Replace SB_CLIENT_ID with your sandbox client ID.</script>
	
	
	<meta charset="UTF-8">
	<title>ALLENATI AL VAR - Pagina registrati</title>
</head>
<body>
<div class="row">
	<!--  <div class="column col-sm-4">
		<img id="imgIndex" src="../img/logo.png">
	</div>
	-->
	<div class="column col-sm-3"></div>
	<div class="column col-sm-6 wrapper fadeInDown">
		  	<div id="formContent">
	
		    	<div class="fadeIn first">
		      		<img src="../img/referee.png" id="icon" alt="User Icon" />
		    	</div>
	
		    	<form id="myForm" method="POST" action="login?registrazione=true">
		      		<input type="text" id="nome" class="fadeIn second" name="nome" placeholder="nome" value="${nome}" required>
		      		<input type="text" id="cognome" class="fadeIn third" name="cognome" placeholder="cognome" value="${cognome}" required>
		      		<input type="email" id="email" class="fadeIn second" name="email" placeholder="email" value="${email}" required>
		      		<c:if test="${emailSbagliato != null}">
		      
		      			<div id="formatErrato" class='alert alert-danger alert-dismissible fade show' role='alert'><strong>L'email inserita è già stata utilizzata!</strong></div>
		      
		      		</c:if>
		      		
		      		
		      		<input type="password" id="password" class="fadeIn third" name="password" placeholder="password" value="${password}" required>
		      		<input type="password" id="conferma_password" class="fadeIn third" name="conferma_password" placeholder="conferma password" value="${confPassword}" required>
		      		<c:if test="${passwordSbagliata != null}">
		      
		      			<div id="formatErrato" class='alert alert-danger alert-dismissible fade show' role='alert'><strong>La password non corrisponde!</strong></div>
		      
		      	</c:if>
		      		
		      		<div class="custom-control custom-switch col-xs-2 text-xs-center">
		      			<c:if test="${amministratoreSi == null}">
						<input type="checkbox" class="custom-control-input" id="switch1" name="amministratore">
	    				<label class="custom-control-label" for="switch1" onclick="showPayments()" > Amministratore</label>
	    				</c:if>
	    				
	    				<c:if test="${amministratoreSi != null}">
						<input type="checkbox" class="custom-control-input" id="switch1" name="amministratore" checked>
	    				<label class="custom-control-label" for="switch1" onclick="showPayments()"> Amministratore</label>
	    				</c:if>
	    				
					</div>
					
					
		      		<button class="btn btn-primary" id="loginBTN" type="submit">Registrati</button>
		      		
		      		<div id="formFooter">
						<div class="d-flex justify-content-center links">
							Torna al login<a class="btn btn-secondary" type="submit" href="gestorePagine?pagina=index">Login</a>
						</div>
					</div>
		    	</form>
		
		
			</div>
		</div>
	<div class="column col-sm-3"></div>
</div>


  
</body>
</html>