$("document").ready(function(){
	
	$(".buttonRemovePreferiti").off('click').click(function(e){
	
		var url_video = $(this).parent().parent().parent().attr("url");
		var riga_cliccata = $(this).parent().parent().parent();
		
		var c = {
				azione : "rimuovi_preferiti",
				url: url_video,
		};

		$.ajax({
			type: "POST",
			url: "gestoreVideo",		
			datatype : "json",
			data: JSON.stringify(c),
			success: function(data){
				var c = JSON.parse(data);
				$(riga_cliccata).remove();		
			}	
		});	
			  
			  
	});
});