
$("document").ready(loadGrafici);




function loadGrafici(){

		var c = {
				azione : "getStatisticheCategorie",
				utente : $(".container").children("div").attr("id"),
				
		};
		
		
		$.ajax({
			type: "POST",
			url: "gestorePagine",		
			datatype : "json",
			data: JSON.stringify(c),
			success: function(data){
				var statistiche = JSON.parse(data);
				
				new Chart($("#graficoHorizontalBarCategorie"), {
					type: "horizontalBar",
					data: {
						labels: ["Spa", "Dogso", "Rigore", "Fallo di mano", "Ammonizione", "Espulsione"],
						datasets: [{
							label: "Punteggio",
							data: statistiche.punteggiCategorie,
							fill: false,
							backgroundColor: ["rgba(243, 159, 24, .2)", "rgba(244, 73, 53, .2)",
							"rgba(49, 40, 244, .2)", "rgba(20, 243, 47, .2)", "rgba(191, 10, 174, .2)",
							"rgba(139, 244, 249, .2)"
							],
							borderColor: ["rgb(243, 159, 24)", "rgb(244, 73, 53)", "rgb(49, 40, 244)",
							"rgb(20, 243, 47)", "rgb(191, 10, 174)", "rgb(139, 244, 249)"
							],
							borderWidth: 1
						}]
					},
					"options": {
						"scales": {
							"xAxes": [{
								"ticks": {
									"beginAtZero": true
								}
							}]
						}
					}
				});
				
				
				
				
			    new Chart(document.getElementById("graficoPieChartCategorie").getContext('2d'), {
			      type: 'pie',
			      data: {
			        labels: ["Spa", "Dogso", "Rigore", "Fallo di mano", "Ammonizione", "Espulsione"],
			        datasets: [{
			          data: statistiche.punteggiCategorie,
			          backgroundColor: ["rgba(243, 159, 24, .2)", "rgba(244, 73, 53, .2)", "rgba(49, 40, 244, .2)", "rgba(20, 243, 47, .2)", "rgba(191, 10, 174, .2)", "rgba(139, 244, 249, .2)"],
			          hoverBackgroundColor: ["rgb(243, 159, 24)", "rgb(244, 73, 53)", "rgb(49, 40, 244)", "rgb(20, 243, 47)", "rgb(191, 10, 174)", "rgb(139, 244, 249)"]
			        }]
			      },
			      options: {
			        responsive: true
			      }
			    });
				
				
			    
			    new Chart(document.getElementById("graficoLineChartCategorie").getContext('2d'), {
			    	type: 'line',
			    	data: {
			    		labels: ["Gennaio", "Febbraio", "Marzo", "Aprile", "Maggio", "Giugno", "Luglio", "Agosto", "Settembre", "Ottobre", "Novembre", "Dicembre"],
			    		datasets: [{
			    			label: "Spa",
			    			data: statistiche.punteggiCategoriePerMese[0],
			    			backgroundColor: ['rgba(243, 159, 24, .2)'],
			    			borderColor: ['rgb(243, 159, 24)'],
			    			borderWidth: 2
			    		},
					    {
			    			label: "Dogso",
			    			data: statistiche.punteggiCategoriePerMese[1],
			    			backgroundColor: ['rgba(244, 73, 53, .2)'],
			    			borderColor: ['rgb(244, 73, 53)'],
			    			borderWidth: 2
					    },
					    {
			    			label: "Rigore",
			    			data: statistiche.punteggiCategoriePerMese[2],
			    			backgroundColor: ['rgba(49, 40, 244, .2)'],
			    			borderColor: ['rgb(49, 40, 244)'],
			    			borderWidth: 2
			    		},
			    		{
			    			label: "Fallo di mano",
			    			data: statistiche.punteggiCategoriePerMese[3],
			    			backgroundColor: ['rgba(20, 243, 47, .2)'],
			    			borderColor: ['rgb(20, 243, 47)'],
			    			borderWidth: 2
			    		},
			    		{
			    			label: "Ammonizione",
			    			data: statistiche.punteggiCategoriePerMese[4],
			    			backgroundColor: ['rgba(191, 10, 174, .2)'],
			    			borderColor: ['rgb(191, 10, 174)'],
			    			borderWidth: 2
			    		},
			    		{
			    			label: "Espulsione",
			    			data: statistiche.punteggiCategoriePerMese[5],
			    			backgroundColor: ['rgba(139, 244, 249, .2)'],
			    			borderColor: ['rgb(139, 244, 249)'],
			    			borderWidth: 2
			    		}]
			    	},
				    options: {
				    	responsive: true
				    }
			    });
			    
			    
			    new Chart(document.getElementById("graficoPieChartProveAutovalutazione").getContext('2d'), {
				      type: 'pie',
				      data: {
				        labels: ["Positive", "Negative"],
				        datasets: [{
				          data: [statistiche.numeroProvePositive, statistiche.numeroProveNegative],
				          backgroundColor: ['rgba(42, 203, 11, .2)','rgba(246, 49, 53, .2)'],
				          hoverBackgroundColor: ["#2acb0b", "#f63135"],
				          borderColor: ["#2acb0b", "#f63135"],
				          borderWidth: 2
				        }]
				      },
				      options: {
				        responsive: true
				      }
				    });
				
				
			}	
		});	
	
	
	
}

