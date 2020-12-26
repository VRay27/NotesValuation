$(document).ready(function() {
	var $active = $('#accordion .panel-collapse.in').prev().addClass('active');
	$active.find('a').append('<span class="glyphicon glyphicon-minus pull-right"></span>');
	$('#accordion .panel-heading').not($active).find('a').prepend('<span class="glyphicon glyphicon-plus pull-right"></span>');
	$('#accordion').on('show.bs.collapse', function (e)
	{
	    $('#accordion .panel-heading.active').removeClass('active').find('.glyphicon').toggleClass('glyphicon-plus glyphicon-minus');
	    $(e.target).prev().addClass('active').find('.glyphicon').toggleClass('glyphicon-plus glyphicon-minus');
	});
	$('#accordion').on('hide.bs.collapse', function (e)
	{
	    $(e.target).prev().removeClass('active').find('.glyphicon').removeClass('glyphicon-minus').addClass('glyphicon-plus');
	});
	    if ($('div').filter(':not(in)')) {
	        $('.panel-title a').addClass('active');
	    }
	
	
    // Generate a simple captcha
    function randomNumber(min, max) {
        return Math.floor(Math.random() * (max - min + 1) + min);
    };
    $('#captchaOperation').html([randomNumber(1, 100), '+', randomNumber(1, 200), '='].join(' '));

    $('#noteInputForm').bootstrapValidator({
//       
        message: 'This value is not valid',
        live: 'enabled',
        excluded: [':disabled', ':hidden', ':not(:visible)'],
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
          noteDate: {
            	group:'.col-sm-3',
                validators: {
                     notEmpty: {
                        message: 'The note date field cannot be empty'
                    },
                    date: {
                        format: 'MM/DD/YYYY',
                        message: 'The note date is not in valid(MM/DD/YYYY) format'
                    },
                    callback: {
                        message: 'The note date is not in valid(MM/DD/YYYY) format',
                        callback: function (value, validator, $field) {
                        	//alert('value'+value+' '+validator+' '+validator+' '+$field);
                        	if(value && value.length>7){
                        		var items = value.split('/');
                               if(items.length > 2 && (items[0].length ==1 && items[0]>0 && items[0]<13) || (items[1].length ==1 && items[1]>0 && items[1]<32)) {
                               	return false;	
                               }
                        }
                        	return true;
                    }
                }
            }},
            streetAddress: {
            	group:'.col-sm-3',
                validators: {
                    notEmpty: {
                        message: 'The street address is required and can\'t be empty.'
                    },
           		 stringLength: {
	                     min: 1,
	                     max: 200,
	                     message: 'The Street Address must be less than 200 characters long'
	            	 }
                }
            },
             upb:{
            	 group:'.col-sm-3',
            	 validators: {
            		 regexp: {
                         regexp: /^[-,.$0-9 ]+$/i,
                         message: 'This value can have numeric $ , and .'
                     },
            		 stringLength: {
	                     min: 1,
	                     max: 15,
	                     message: 'The unpaid balance must be less than 15 characters long'
	            	 },
	            	 notEmpty: {
	                        message: 'The unpaid balance is required and can\'t be empty.'
	                    }
             }},
             rate:{
            	 group:'.col-sm-3',
            	 validators: {
            		 regexp: {
                         regexp: /^[-.%0-9 ]+$/i,
                         message: 'This value can have numeric % , and .'
                     },
            		 stringLength: {
	                     min: 1,
	                     max: 15,
	                     message: 'The rate must be less than 15 characters long'
	            	 },
	            	 notEmpty: {
	                        message: 'The rate is required and can\'t be empty.'
	                    }
             }},
             pdiPayment:{
            	 group:'.col-sm-3',
            	 validators: {
            		 regexp: {
                         regexp: /^[-,.$0-9 ]+$/i,
                         message: 'This value can have numeric $ , and .'
                     },
            		 stringLength: {
	                     min: 1,
	                     max: 15,
	                     message: 'The  payment must be less than 15 characters long'
	            	 },
	            	 notEmpty: {
	                        message: 'The  payment is required and can\'t be empty.'
	                    }
             }},
             tdiPayment:{
            	 group:'.col-sm-3',
            	 validators: {
            		 regexp: {
                         regexp: /^[-,.$0-9 ]+$/i,
                         message: 'This value can have numeric $ , and .'
                     },
            		 stringLength: {
	                     min: 0,
	                     max: 15,
	                     message: 'The tax and Insurance must be less than 15 characters long'
	            	 }
             }},
             
             originalPrincipalBal:{
            	 group:'.col-sm-3',
            	 validators: {
            		 regexp: {
                         regexp: /^[-,.$0-9 ]+$/i,
                         message: 'This value can have numeric $ , and .'
                     },
            		 stringLength: {
	                     min: 1,
	                     max: 15,
	                     message: 'The original principal bal must be less than 15 characters long'
	            	 },
	            	 notEmpty: {
	                        message: 'The original principal balance is required and can\'t be empty.'
	                    }
             }},
             notePrice:{
            	 group:'.col-sm-3',
            	 validators: {
            		 regexp: {
                         regexp: /^[-,.$0-9 ]+$/i,
                         message: 'This value can have numeric $ , and .'
                     },
            		 stringLength: {
	                     min: 1,
	                     max: 15,
	                     message: 'The Note Price must be less than 15 characters long'
	            	 },
	            	 notEmpty: {
	                        message: 'The Note Price is required and can\'t be empty.'
	                    }
             }}, 
             remainingPayment:{
            	 group:'.col-sm-3',
            	 validators: {
            		 numeric: {
                         message: 'This value is not a valid number.',
                         // The default separators
                         thousandsSeparator: '',
                         decimalSeparator: '.'
                     },
            		 stringLength: {
	                     min: 1,
	                     max: 15,
	                     message: 'The remaining no of payment must be less than 15 characters long'
	            	 },
	            	 notEmpty: {
	                        message: 'The remaining no of payment value is required and can\'t be empty.'
	                    }
             }},
             noOfLatePayment:{
            	 group:'.col-sm-3',
            	 validators: {
            		 numeric: {
                         message: 'This value is not a valid number.',
                         // The default separators
                         thousandsSeparator: '',
                         decimalSeparator: '.'
                     },
            		 stringLength: {
	                     min: 0,
	                     max: 15,
	                     message: 'The No of Late Payment must be less than 15 characters long'
	            	 }
             }},
             notePosition:{
            	 group:'.col-sm-3',
            	 validators: {
            		 numeric: {
                         message: 'This value is not a valid number.',
                         // The default separators
                         thousandsSeparator: '',
                         decimalSeparator: '.'
                     },
            		 stringLength: {
	                     min: 1,
	                     max: 15,
	                     message: 'The note position value must be less than 15 characters long'
	            	 }
             }}, 
             borrowerCreditScore:{
            	 group:'.col-sm-3',
            	 validators: {
            		 between: {
                         min: 0,
                         max: 1000,
                         message: 'The borrower credit score must be between 0 and 1000'
            		 }
             }},
             noteScoreByUser:{
            	 group:'.col-sm-3',
            	 validators: {
            		 between: {
                         min: 0,
                         max: 100,
                         message: 'The note score must be between 0 and 100'
                     }
             }},
             estimatedMarketValue:{
            	 group:'.col-sm-3',
            	 validators: {
            		 regexp: {
                         regexp: /^[-,.$0-9 ]+$/i,
                         message: 'This value can have numeric $ , and .'
                     },
            		 stringLength: {
	                     min: 1,
	                     max: 15,
	                     message: 'The Estimated market value must be less than 15 characters long'
	            	 },
	            	 notEmpty: {
	                        message: 'The Estimated market value is required and can\'t be empty.'
	                    }
             }},
             lastPaymentRecievedDate :{
            	 group:'.col-sm-3',
            	 validators: {
            		 notEmpty: {
                         message: 'The date field cannot be empty'
                     },
            		 date: {
                         format: 'MM/DD/YYYY',
                         message: 'The  date is not in valid(MM/DD/YYYY) format'
                     },
                     callback: {
                         message: 'The  date is not in valid(MM/DD/YYYY) format',
                         callback: function (value, validator, $field) {
                         	if(value && value.length>7){
                         		var items = value.split('/');
                                if(items.length > 2 && (items[0].length ==1 && items[0]>0 && items[0]<13) || (items[1].length ==1 && items[1]>0 && items[1]<32)) {
                                	return false;	
                                }
                         }
                         	return true;
                     }
                 }
             }},
             numberOfUnits:{
             group:'.col-sm-3',
        	 validators: {
        		 numeric: {
                     message: 'This value is not a valid number.',
                     // The default separators
                     thousandsSeparator: '',
                     decimalSeparator: '.'
                 },
        		 stringLength: {
                     min: 0,
                     max: 15,
                     message: 'The No of Late Payment must be less than 15 characters long'
            	 }
        	 }},
        	 hoaFees:{
                 group:'.col-sm-3',
            	 validators: {
            		 regexp: {
                         regexp: /^[-,.$0-9 ]+$/i,
                         message: 'This value can have numeric $ , and .'
                     },
            		 stringLength: {
                         min: 0,
                         max: 15,
                         message: 'The No of Late Payment must be less than 15 characters long'
                	 }
            	 }
        	 },creditScore:{
            	 group:'.col-sm-3',
            	 validators: {
            		 between: {
                         min: 0,
                         max: 850,
                         message: 'The credit score must be between 0 and 850'
            		 }
        	 }},
        	 borrowerName:{
                 group:'.col-sm-3',
            	 validators: {
            		 regexp: {
                         regexp: /^[a-zA-Z0-9\-\s]+$/,
                         message: 'This value can have aplha numeric only'
                     },
            		 stringLength: {
                         min: 0,
                         max: 40,
                         message: 'The name must be less than 40 characters long'
                	 }
            	 }
        	 },
        	 noOfLatePaymentByBorrower:{
                 group:'.col-sm-3',
            	 validators: {
            		 numeric: {
                         message: 'This value is not a valid number.',
                         // The default separators
                         thousandsSeparator: '',
                         decimalSeparator: '.'
                     },
            		 stringLength: {
                         min: 0,
                         max: 15,
                         message: 'The No of Late Payment must be less than 15 characters long'
                	 }
            	 }},
            	 
            	 zipCode:{
                     group:'.col-sm-3',
                	 validators: {
                		 numeric: {
                             message: 'This value is not a valid number.',
                             // The default separators
                             thousandsSeparator: '',
                             decimalSeparator: ''
                         },
                		 stringLength: {
                             min: 1,
                             max: 15,
                             message: 'The zipcode must be less than 15 characters long'
                    	 },
                    	 notEmpty: {
 	                        message: 'The  zipcode is required and can\'t be empty.'
 	                    }
                	 }},
                	 zipCode:{
                         group:'.col-sm-3',
                    	 validators: {
                    		 numeric: {
                                 message: 'This value is not a valid number.',
                                 // The default separators
                                 thousandsSeparator: '',
                                 decimalSeparator: ''
                             },
                    		 stringLength: {
                                 min: 1,
                                 max: 15,
                                 message: 'The zipcode must be less than 15 characters long'
                        	 },
                        	 notEmpty: {
     	                        message: 'The  zipcode is required and can\'t be empty.'
     	                    }
                    	 }},
                    	 city:{
                             group:'.col-sm-3',
                        	 validators: {
                            	 notEmpty: {
         	                        message: 'The  city is required and can\'t be empty.'
         	                    }
                        	 }},
                        	 state:{
                                 group:'.col-sm-3',
                            	 validators: {
                                	 notEmpty: {
             	                        message: 'The  state is required and can\'t be empty.'
             	                    }
                            	 }},
                            	 originalTerm:{
                                     group:'.col-sm-3',
                                	 validators: {
                                		 numeric: {
                                             message: 'This value is not a valid number.',
                                             // The default separators
                                             thousandsSeparator: '',
                                             decimalSeparator: '.'
                                         },
                                		 stringLength: {
                                             min: 1,
                                             max: 15,
                                             message: 'The originalTerm must be less than 15 characters long'
                                    	 },
                                    	 notEmpty: {
                 	                        message: 'The  originalTerm is required and can\'t be empty.'
                 	                    }
                                	 }}
	            	
           /*  captcha: {
                validators: {
                    callback: {
                        message: 'Wrong answer',
                        callback: function(value, validator) {
                            var items = $('#captchaOperation').html().split(' '), sum = parseInt(items[0]) + parseInt(items[2]);
                            return value == sum;
                        }
                    }
                }
            } */
        }
    });

  
    // Validate the form manually
   $('#createNoteBtn').click(function() {
	   $('.panel-collapse:not(".in")')
	    .collapse('show');
	   $("input").trigger("change");
        if($('#noteInputForm').bootstrapValidator('validate').has('.has-error').length){
	       	return false;
    	}else{
    		$("#createNoteBtnHidden").trigger( "click" );
    	};
        });
   
   // Validate the form manually
  $('#updateNoteBtn').click(function() {
	   $('.panel-collapse:not(".in")')
	    .collapse('show');
	   $("input").trigger("change");
       if($('#noteInputForm').bootstrapValidator('validate').has('.has-error').length){
	       	return false;
   	}else{
   		$("#updateNoteBtnHidden").trigger( "click" );
   	};
 });
  
  // Validate the form manually
 $('#subscribeNoteBtn').click(function() {
	   $('.panel-collapse:not(".in")')
	    .collapse('show');
	   $("input").trigger("change");
      if($('#noteInputForm').bootstrapValidator('validate').has('.has-error').length){
	       	return false;
  	}else{
  		$("#subscribeNoteBtnHidden").trigger( "click" );
  	};
});
  
   $('#resetNoteBtn').click(function() {
   	$('#noteInputForm').bootstrapValidator('resetForm', true);
   });
    
   $('#noteInputForm').click(function(){
	    $("input").trigger("change");
   	//	$("#rate").trigger("blur");
	   if($('#selNoteDate').val()){
	   	$('#noteInputForm').bootstrapValidator('revalidateField', 'noteDate');
	   }
	   if($('#lastPaymentRecievedDate').val()){
		   	$('#noteInputForm').bootstrapValidator('revalidateField', 'lastPaymentRecievedDate');
	   }
	   if($('#originalPrincipalBal').val()){
	   $('#noteInputForm').bootstrapValidator('revalidateField', 'originalPrincipalBal');
	   }
	   if($('#rate').val()){
	   $('#noteInputForm').bootstrapValidator('revalidateField', 'rate');
	   }
	   if($('#pdiPayment').val()){
	   $('#noteInputForm').bootstrapValidator('revalidateField', 'pdiPayment');
	   }
	   if($('#originalTerm').val()){
		   $('#noteInputForm').bootstrapValidator('revalidateField', 'originalTerm');
	   }
	   if($('#originalTerm').val()){
		   $('#noteInputForm').bootstrapValidator('revalidateField', 'originalTerm');
	   }
	   if($('#upb').val()){
		   $('#noteInputForm').bootstrapValidator('revalidateField', 'upb');
	   }
	   if($('#notePrice').val()){
		   $('#noteInputForm').bootstrapValidator('revalidateField', 'notePrice');
	   }
	   
	   if($('#estimatedMarketValue').val()){
		   $('#noteInputForm').bootstrapValidator('revalidateField', 'estimatedMarketValue');
	   }
	   
	 });
   
   $('#loanTypeId').on('change', function() {
	    $('#originalTerm').trigger('change');
	});
   
   $('#originalTerm').change(function(){
	   if($('#originalTerm').val()){
		   $('#noteInputForm').bootstrapValidator('revalidateField', 'originalTerm');
	   }
	 });
   
   $('#selNoteDate').change(function(){
	   if($('#selNoteDate').val()){
		   $('#noteInputForm').bootstrapValidator('revalidateField', 'noteDate');
	   }
	 });
   
   $('#lastPaymentRecievedDate').change(function(){
	   if($('#lastPaymentRecievedDate').val()){
		   $('#noteInputForm').bootstrapValidator('revalidateField', 'lastPaymentRecievedDate');
	   }
	 });
   
   $('#originalPrincipalBal').change(function(){
	   if($('#originalPrincipalBal').val()){
		   $('#noteInputForm').bootstrapValidator('revalidateField', 'originalPrincipalBal');
	   }
	 });
   
   $('#rate').change(function(){
	   if($('#rate').val()){
		   $('#noteInputForm').bootstrapValidator('revalidateField', 'rate');
	   }
	 });
   
   $('#pdiPayment').change(function(){
	   if($('#pdiPayment').val()){
		   $('#noteInputForm').bootstrapValidator('revalidateField', 'pdiPayment');
	   }
	 });
/*   $('.closeall').click(function(){
 	  $('.panel-collapse.in')
 	    .collapse('hide');
 	});
 	$('.openall').click(function(){
 	  $('.panel-collapse:not(".in")')
 	    .collapse('show');
 	});*/
 		
 	$('.closeall').click(function(){
 		 //if ($(this).data("lastState") === null || $(this).data("lastState") === 0) {

 	        // close all
 		$('.panel-collapse').removeData('bs.collapse')
	        .collapse({parent:false, toggle:false})
	        .collapse('hide')
	        .removeData('bs.collapse')
	         // restore single panel behavior
	        .collapse({parent:'#accordionFormat', toggle:false});


 	        // next state will be open all
 	        $('.closeall').data("lastState",1);
 	       $('.openall').data("lastState",1);
 		// }

 	 	});
 	$('.openall').click(function(){
 	 		 // initial state...
 	        // override accordion behavior and open all
 	        $('.panel-collapse').removeData('bs.collapse')
 	        .collapse({parent:false, toggle:false})
 	        .collapse('show')
 	        .removeData('bs.collapse')
 	         // restore single panel behavior
 	        .collapse({parent:'#accordionFormat', toggle:false});

 	        // next state will be close all
 	        $('.closeall').data("lastState",0);
 	        $('.openall').data("lastState",0);
 	 	});
 	$.calculator.setDefaults({showOn: 'button', buttonImageOnly: true, buttonImage: '/notes/static/img/calculator.png',useThemeRoller: true });
 	$('#originalPrincipalBal').calculator();
 	$('#pdiPayment').calculator();
 	$('#rate').calculator();
 	$('#notePrice').calculator();
 	$('#upb').calculator();
 	$('#estimatedMarketValue').calculator();

});
