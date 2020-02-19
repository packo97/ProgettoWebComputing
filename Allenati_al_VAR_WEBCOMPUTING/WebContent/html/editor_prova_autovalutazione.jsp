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

	<link rel="stylesheet" href="../css/home.css" type="text/css">
	<link rel="stylesheet" href="../css/editor_prova_autovalutazione.css" type="text/css">
	<link rel="stylesheet" href="../css/risultatoRicerca.css" type="text/css">
	
	<script type="text/javascript" src="../js/risultatoRicerca.js"></script>
	<script type="text/javascript" src="../js/editor_prova_autovalutazione.js"></script>
	

	
	<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
  	<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
	
	
	<meta charset="UTF-8">
	<title>ALLENATI AL VAR - Editor Prova Autovalutazione</title>

</head>
<body>
	<%@include file="header_default.jsp" %>
	<div id="filtri"></div>
	
	<div id="jumbotron_totale"class="jumbotron">
		
		<div class="row">
		
			<div id="jumbotronSx" class="contenitore column col-sm-6 jumbotron">
					
				<div class="row">
					<div class="column col-sm-12">
						<a id="buttonAggiungiSelezionati"><img src="../img/icona_aggiungi_selezionati.png"></a>
						<a id="buttonAggiungiTutti"><img src="../img/icona_aggiungi_tutti.png"></a>
						<a id="buttonStorico" selezionato="false"><img src="../img/icona_archivio.png"></a>
						<div class="input-group mb-3">
    						<input id="inputTextTestoRicerca" type="search" class="form-control" placeholder="Cerca un video...">
						    <div class="input-group-append">
						      <button id="buttonSearch" class="btn btn-success" type="submit">Cerca</button>  
						    </div>
						 </div>
					</div>
				</div>
				
			</div>
			
			
			<div id="jumbotronDx" class="contenitore column col-sm-6 jumbotron">
				
				<div class="row">
					<div id="comandiDX1" class="column col-sm-9">
						
						<a id="buttonRimuoviSelezionati" onclick=""><img src="../img/icona_rimuovi_selezionati.png"></a>
						<a id="buttonRimuoviTutti" onclick=""><img src="../img/icona_rimuovi_tutti.png"></a>
						<a id="buttonUndo" onclick=""><img src="../img/icona_undo.png"></a>
						<a id="buttonRedo" onclick=""><img src="../img/icona_redo.png"></a>
						
						
					</div>
					<div id="comandiDX2" class="column col-sm-3">
						
						
						<a id="buttonCrea" onclick="creaProvaAutovalutazione()"><img src="../img/icona_carica.png"></a>
						
					</div>
				</div>
				
				
			</div>
			
		</div>
	</div>
	
	
	<!-- INIZIO CUSTOM ALERT -->
<div class="modal fade" id="myModalRipeti">
    <div class="modal-dialog modal-sm">
      <div class="modal-content">
      
        <!-- Modal Header -->
       <div class="modal-header">
         <h4 class="modal-title">Sei sicuro?</h4>
         <button type="button" class="close" data-dismiss="modal">&times;</button>
       </div>
       
       <!-- Modal body -->
       <div class="modal-body">
         Stai per iniziare una prova di autovalutazione.
       </div>
       
       <!-- Modal footer -->
       <div class="modal-footer">
         <button type="button" class="btn btn-danger" data-dismiss="modal">Annulla</button>
         <a class="btn btn-success" href="prova_autovalutazione?standard=true">Inizia</a>
       </div>
       
     </div>
   </div>
</div>

<!-- FINE CUSTOM ALERT -->


	
</body>
</html>