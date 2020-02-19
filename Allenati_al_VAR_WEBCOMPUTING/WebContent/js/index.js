window.addEventListener("load", function() {
	clearForm();
});

function clearForm(){
	setTimeout(function(){$('#username').val('');},50);
	setTimeout(function(){$('#pwd').val('');},50);
}


function showPayments(){
	
	if($("#paypal-button-container").length == 0 && $("#switch1").prop("checked")==false ){
		$("#formContent").append("<div id='paypal-button-container'></div>");
		$("#loginBTN").remove();
	}
	else {
		$("#paypal-button-container").remove();
		$("<button class='btn btn-primary' id='loginBTN' type='submit'> Registrati </button>").insertBefore($("#formFooter"));
	}
		loadPaypal();
}



$("document").ready(loadPaypal);

function loadPaypal(){
	
	  paypal.Buttons({
		    createOrder: function(data, actions) {
		      // This function sets up the details of the transaction, including the amount and line item details.
		      return actions.order.create({
		        purchase_units: [{
		          amount: {
		            value: '100.00'
		          }
		        }]
		      });
		    },
		    onApprove: function(data, actions) {
		      // This function captures the funds from the transaction.
		      return actions.order.capture().then(function(details) {
		        // This function shows a transaction success message to your buyer.
		    	$("#paypal-button-container").remove();
		    	$("<button class='btn btn-primary' id='loginBTN' type='submit'> Registrati </button>").insertBefore($("#formFooter"));
		        alert('Pagamento avvenuto con successo da ' + details.payer.name.given_name);
		        
		      });
		    }
		  }).render('#paypal-button-container');
		  //This function displays Smart Payment Buttons on your web page.
}










