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
                    }
                }
            },
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
	                     max: 11,
	                     message: 'The unpaid balance must be less than 11 characters long'
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
	                     max: 11,
	                     message: 'The rate must be less than 11 characters long'
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
	                     max: 11,
	                     message: 'The  payment must be less than 11 characters long'
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
	                     max: 20,
	                     message: 'The tax and Insurance must be less than 11 characters long'
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
	                     max: 11,
	                     message: 'The original principal bal must be less than 11 characters long'
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
	                     max: 11,
	                     message: 'The Note Price must be less than 11 characters long'
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
	                     max: 20,
	                     message: 'The remaining no of payment must be less than 20 characters long'
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
	                     max: 10,
	                     message: 'The No of Late Payment must be less than 10 characters long'
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
	                     max: 10,
	                     message: 'The note position value must be less than 10 characters long'
	            	 }
             }}, 
             borrowerCreditScore:{
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
	                     max: 11,
	                     message: 'The Borrower Credit Score must be less than 20 characters long'
	            	 }
             }},
             noteScoreByUser:{
            	 group:'.col-sm-3',
            	 validators: {
            		 between: {
                         min: 0,
                         max: 100,
                         message: 'The note score must be between 0 and 100'
                     },
	            	 notEmpty: {
	                        message: 'The remaining no of payment value is required and can\'t be empty.'
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
	                     max: 11,
	                     message: 'The Estimated market value must be less than 11 characters long'
	            	 },
	            	 notEmpty: {
	                        message: 'The Estimated market value is required and can\'t be empty.'
	                    }
             }},
             lastPaymentRecievedDate :{
            	 group:'.col-sm-3',
            	 validators: {
            		 date: {
                         format: 'MM/DD/YYYY',
                         message: 'The  date is not in valid(MM/DD/YYYY) format'
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
                     max: 10,
                     message: 'The No of Late Payment must be less than 10 characters long'
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
                         max: 10,
                         message: 'The No of Late Payment must be less than 10 characters long'
                	 }
            	 }
        	 },creditScore:{
            	 group:'.col-sm-3',
            	 validators: {
            		 between: {
                         min: 0,
                         max: 100,
                         message: 'The credit score must be between 0 and 100'
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
                         max: 10,
                         message: 'The No of Late Payment must be less than 10 characters long'
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
       if($('#noteInputForm').bootstrapValidator('validate').has('.has-error').length){
	       	return false;
   	}else{
   		$("#updateNoteBtnHidden").trigger( "click" );
   	};
 });
  
  // Validate the form manually
 $('#deleteNoteBtn').click(function() {
	
  		$("#deleteBtnHidden").trigger( "click" );
});
  
  
  // Validate the form manually
 $('#subscribeNoteBtn').click(function() {
	   $('.panel-collapse:not(".in")')
	    .collapse('show');
      if($('#noteInputForm').bootstrapValidator('validate').has('.has-error').length){
	       	return false;
  	}else{
  		$("#subscribeNoteBtnHidden").trigger( "click" );
  	};
});
  
  // Validate the form manually
 $('#createNoteBtn').click(function() {
	   $('.panel-collapse:not(".in")')
	    .collapse('show');
      if($('#noteInputForm').bootstrapValidator('validate').has('.has-error').length){
	       	return false;
  	}else{
  		$("#createNoteBtnHidden").trigger( "click" );
  	};
      });
    
   $('#resetNoteBtn').click(function() {
   	$('#noteInputForm').bootstrapValidator('resetForm', true);
   });
    
   $('#noteInputForm').click(function(){
	   if($('#selNoteDate').val()){
	   	$('#noteInputForm').bootstrapValidator('revalidateField', 'noteDate');
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
	 });
   
   $('.closeall').click(function(){
 	  $('.panel-collapse.in')
 	    .collapse('hide');
 	});
 	$('.openall').click(function(){
 	  $('.panel-collapse:not(".in")')
 	    .collapse('show');
 	});
  
});
