<h4 align="center"> Caratteristiche </h4>
<div class="row">
	<div class="column col-sm-12">
		<h4><span class="badge">Dove vuoi cercare</span></h4>
		<div class="form-group">
   			<select class="form-control" onchange="cercaConFiltri()" id="cercaIn">
   				<option value=""></option>
      			<option value="risposte_utente">Video a cui hai risposto</option>
      			<option value="preferiti">Preferiti</option>
      			<option value="prove_di_autovalutazione">Prove di autovalutazione</option>
			</select>
      	</div>
     	</div>
</div>
	
<div class="row">
	<div class="column col-sm-12">
		<h4><span class="badge">Categorie</span></h4>
		<div class="form-group">
   			<select class="form-control" onchange="cercaConFiltri()" id="categoria">
   				<option value=""></option>
       			<option value="DOGSO">DOGSO</option>
    			<option value="SPA">SPA</option>
    			<option value="FALLO DI MANO">FALLO DI MANO</option>
    			<option value="RIGORE">RIGORE</option>
    			<option value="ESPULSIONE">ESPULSIONE</option>
    			<option value="AMMONIZIONE">AMMONIZIONE</option>
   			</select>
   		</div>
	</div>
		
	<div class="column col-sm-12">
		<h4><span class="badge ">Difficolta'</span></h4>
		<div class="form-group">
   			<select class="form-control" onchange="cercaConFiltri()" id="difficolta">
      			<option value=""></option>
    			<option value="FACILE">FACILE</option>
    			<option value="NORMALE">NORMALE</option>
    			<option value="DIFFICILE">DIFFICILE</option>
   			</select>
   		</div>
	</div>
		
	<div class="column col-sm-12">
		<h4><span class="badge">Squadra in casa</span></h4>
		<div class="form-group">
   			<input type="text" class="form-control" onchange="cercaConFiltri()" id="squadraA">
   		</div>
   		<h4><span class="badge"> Squadra in trasferta </span></h4>
		<div class="form-group">
   			<input type="text" class="form-control" onchange="cercaConFiltri()" id="squadraB">
   		</div>
	</div>	
</div>

<h4 align="center"> Tempo </h4>
<div class="row">
	<div class="column col-sm-12">
		<h4><span class="badge">Data</span></h4>
		<div class="form-group">
   			<select class="form-control" onchange="cercaConFiltri()" id="periodo">
   				<option value=""></option>
       			<option value="Ultima ora">Ultima ora</option>
    			<option value="Oggi">Oggi</option>
    			<option value="Questa settimana">Questa settimana</option>
    			<option value="Questo mese">Questo mese</option>
    			<option value="Quest'anno">Quest'anno</option>
   			</select>
   		</div>
	</div>

	<div class="column col-sm-12">
		<h4><span class="badge">Durata</span></h4>
		<div class="slidecontainer">
			<input type="range" min="0" max="180" value="0" class="slider" id="durataMin" onchange="cercaConFiltri()" oninput="mostraDurataMin()">
			<p class="badge">Durata minima: <span id="valoreDurataMin">0</span></p>
		</div>
		<div class="slidecontainer">
			<input type="range" min="0" max="180" value="180" class="slider" id="durataMax" onchange="cercaConFiltri()" oninput="mostraDurataMax()">
			<p class="badge">Durata massima: <span id="valoreDurataMax">180</span></p>
		</div>
	</div>
	
</div>

<h4 align="center"> Interazioni </h4>
<div class="row">
	<div class="column col-sm-12">
		<h4><span class="badge">Risposte</span></h4>
		<label class="container"> Più giusti
		  <input type="checkbox" name="radioRisposte" id="piuGiusti" onchange="cercaConFiltri()" onclick="uncheckOtherButton1()">
		  <span class="checkmark"></span>
		</label>
		<label class="container">Più sbagliati
		  <input type="checkbox" name="radioRisposte" id="piuSbagliati" onchange="cercaConFiltri()" onclick="uncheckOtherButton2()">
		  <span class="checkmark"></span>
		</label>
	</div>
	
	<div class="column col-sm-12">
		<h4><span class="badge">Numero commenti</span></h4>
		<div class="slidecontainer">
			<input type="range" min="0" max="300" value="0" class="slider" id="numeroCommentiMin" onchange="cercaConFiltri()" oninput="mostraCommentiMin()">
			<p class="badge">Numero minimo commenti: <span id="valoreCommentiMin">0</span></p>
		</div>
		<div class="slidecontainer">
			<input type="range" min="0" max="300" value="300" class="slider" id="numeroCommentiMax" onchange="cercaConFiltri()"  oninput="mostraCommentiMax()">
			<p class="badge">Numero massimo commenti: <span id="valoreCommentiMax">300</span></p>
		</div>
	</div>
</div>
</div>
</div>
