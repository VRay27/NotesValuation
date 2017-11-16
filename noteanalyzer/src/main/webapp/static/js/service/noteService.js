'use strict';


noteApp.factory('NoteService', ['$http', 'toastr', '$q','$filter', '$rootScope', '$uibModal', 'WaitingDialog','UtilityService',function($http, toastr, $q, $filter,$rootScope, $uibModal,WaitingDialog,UtilityService) {
	var noteInputFormModel;
	var noteDetailModel;
	var factory = {
		createNote : createNote,
		noteAnalyze : noteAnalyze,
		getNoteDetail : getNoteDetail,
		deleteNote : deleteNote,
		updateNote : updateNote,
		uploadNoteFile : uploadNoteFile,
		noteCalculator : noteCalculator,
		getGeoDetails : getGeoDetails,
		setInputFormModel : setInputFormModel,
		getInputFormModel : getInputFormModel,
		setNoteDetailModel : setNoteDetailModel,
		getNoteDetailModel : getNoteDetailModel,
		getYield : getYield,
		updateMarketValue : updateMarketValue,
		subscribeNote : subscribeNote,
		noteOriginalBalCalculator : noteOriginalBalCalculator,
		notePaymentCalculator : notePaymentCalculator,
		updateAndSaveMarketValue : updateAndSaveMarketValue
		
		
	};

	return factory;
	
	function getYield(model){
		if(model.selNoteType != 'N'){
			var newRate = UtilityService.calculateRate(model.remainingPayment,model.pdiPayment*-1,model.notePrice) * 1200;
			model.yieldValue = UtilityService.round(newRate,2);
			model.yieldValue = newRate;
		} 
		if(!model.yieldValue){
			model.yieldValue ='';
		}
		
	}
	

	   function calculateRateNew_2( nper,  pmt,  pv,  fv,  type,  guess) {
	 // Sets default values for missing parameters
		    fv = typeof fv !== 'undefined' ? fv : 0;
		    type = typeof type !== 'undefined' ? type : 0;
		    guess = typeof guess !== 'undefined' ? guess : 0.1;
	  var FINANCIAL_MAX_ITERATIONS = 20;
	  var FINANCIAL_PRECISION = 0.0000001;

	  var y, y1, xN = 0, f = 0, i = 0;

	  var rate = guess;

	  //find root by Newtons method (https://en.wikipedia.org/wiki/Newton%27s_method), not secant method!
	  //Formula see: https://wiki.openoffice.org/wiki/Documentation/How_Tos/Calc:_Derivation_of_Financial_Formulas#PV.2C_FV.2C_PMT.2C_NPER.2C_RATE

	  f = Math.pow(1 + rate, nper);
	  y = pv * f + pmt * ((f - 1) / rate) * (1 + rate * type) + fv;

	  //first derivative:
	  //y1 = (pmt * nper * type * Math.pow(rate,2) * f - pmt * f - pmt * rate * f + pmt * nper * rate * f + pmt * rate + pmt + nper * pv * Math.pow(rate,2) * f) / (Math.pow(rate,2) * (rate+1));
	  y1 = (f * ((pmt * nper * type + nper * pv) * Math.pow(rate,2) + (pmt * nper - pmt) * rate - pmt) + pmt * rate + pmt) / (Math.pow(rate,3) + Math.pow(rate,2));

	  xN = rate - y/y1;

	  while ((Math.abs(rate - xN) > FINANCIAL_PRECISION) && (i < FINANCIAL_MAX_ITERATIONS)) {

	   rate = xN;

	   f = Math.pow(1 + rate, nper);
	   y = pv * f + pmt * ((f - 1) / rate) * (1 + rate * type) + fv;

	   //first derivative:
	   //y1 = (pmt * nper * type * Math.pow(rate,2) * f - pmt * f - pmt * rate * f + pmt * nper * rate * f + pmt * rate + pmt + nper * pv * Math.pow(rate,2) * f) / (Math.pow(rate,2) * (rate+1));
	   y1 = (f * ((pmt * nper * type + nper * pv) * Math.pow(rate,2) + (pmt * nper - pmt) * rate - pmt) + pmt * rate + pmt) / (Math.pow(rate,3) + Math.pow(rate,2));

	   xN = rate - y/y1;
	   ++i;

	   
	  }

	  rate = xN;    
	  return rate;

	 }
	
    function calculateRateNew( nper,  pmt,  pv,  fv,  type,  guess) {
	      var FINANCIAL_MAX_ITERATIONS = 20;//Bet accuracy with 128
	      var FINANCIAL_PRECISION = 0.0000001;//1.0e-8

	      var y, y0, y1, x0, x1 = 0, f = 0, i = 0;
	      var rate = .1;
    
	      if (Math.abs(rate) < FINANCIAL_PRECISION) {
	         y = pv * (1 + nper * rate) + pmt * (1 + rate * type) * nper + fv;
	      } else {
	         f = Math.exp(nper * Math.log(1 + rate));
	         y = pv * f + pmt * (1 / rate + type) * (f - 1) + fv;
	      }
    
	      y0 = pv + pmt * nper + fv;
	      y1 = pv * f + pmt * (1 / rate + type) * (f - 1) + fv;

	      // find root by Newton secant method
	      i = x0 = 0.0;
	      x1 = rate;
	      while ((Math.abs(y0 - y1) > FINANCIAL_PRECISION) && (i < FINANCIAL_MAX_ITERATIONS)) {
	         rate = (y1 * x0 - y0 * x1) / (y1 - y0);
	         x0 = x1;
	         x1 = rate;

	         if (Math.abs(rate) < FINANCIAL_PRECISION) {
	            y = pv * (1 + nper * rate) + pmt * (1 + rate * type) * nper + fv;
	         } else {
	            f = Math.exp(nper * Math.log(1 + rate));
	            y = pv * f + pmt * (1 / rate + type) * (f - 1) + fv;
	         }

	         y0 = y1;
	         y1 = y;
	         ++i;
	      }
	      return rate;
	   }
	
	function setInputFormModel(model){
		noteInputFormModel = model;
	}
	function getInputFormModel(){
		return noteInputFormModel;
	}
	
	function setNoteDetailModel(noteDetailInputmodel){
		noteDetailModel = noteDetailInputmodel;
	}
	function getNoteDetailModel(){
		return noteDetailModel;
	}


	function noteCalculator(noteInputFormModel) {
		 var principal = $filter('sanitizeInput')(noteInputFormModel.originalPrincipleBalance);
		 var term  = $filter('sanitizeInput')(noteInputFormModel.originalTerm);
		 var interestRate   = $filter('sanitizeInput')(noteInputFormModel.rate);
		 var payment = $filter('sanitizeInput')(noteInputFormModel.pdiPayment);
		 var isAllPresent = principal && term && interestRate && payment;
		 if(!(isAllPresent)) {
			 	interestRate = interestRate / 1200;
			 	if(payment > 0){
			 		payment = payment * -1;
			 	}else{
			 		payment = payment * 1;
			 	}
				if (principal && term && interestRate) {
					var pay = principal * interestRate / (1 - (Math.pow(1 / (1 + interestRate), term)));
					noteInputFormModel.pdiPayment = UtilityService.round(pay,2) *-1;
					return 'pdiPayment';
				} else if (principal && term && payment) {
					var newRate = UtilityService.calculateRate(term,payment, principal) * 1200;
					noteInputFormModel.rate = UtilityService.round(newRate,2);
					return 'rate';
				} else if (principal && interestRate && payment) {
					var newTerm = UtilityService.getNPER(interestRate, payment, principal);
					noteInputFormModel.originalTerm = UtilityService.round(newTerm, 2);
					return 'originalTerm';
				} else if (term && interestRate && payment) {
					var newPrinciple = UtilityService.getPV(interestRate, term, payment);
					noteInputFormModel.originalPrincipleBalance = UtilityService.round(newPrinciple, 2);
					return 'originalPrincipleBalance';
				}
			}
	}
	
	function notePaymentCalculator(noteInputFormModel) {
		 var principal = $filter('sanitizeInput')(noteInputFormModel.originalPrincipleBalance);
		 var term  = $filter('sanitizeInput')(noteInputFormModel.originalTerm);
		 var interestRate   = $filter('sanitizeInput')(noteInputFormModel.rate);
		 var payment = $filter('sanitizeInput')(noteInputFormModel.pdiPayment);
		 var isAllPresent = principal && term && interestRate && payment;
		 if(!(isAllPresent)) {
			 	interestRate = interestRate / 1200;
			 	if(payment > 0){
			 		payment = payment * -1;
			 	}else{
			 		payment = payment * 1;
			 	}
				if (principal && term && interestRate) {
					var pay = principal * interestRate / (1 - (Math.pow(1 / (1 + interestRate), term)));
					noteInputFormModel.pdiPayment = UtilityService.round(pay,2) * -1;
					return 'pdiPayment';
				}
			}
	}
	
	function noteOriginalBalCalculator(noteInputFormModel) {
		 var principal = $filter('sanitizeInput')(noteInputFormModel.originalPrincipleBalance);
		 var term  = $filter('sanitizeInput')(noteInputFormModel.originalTerm);
		 var interestRate   = $filter('sanitizeInput')(noteInputFormModel.rate);
		 var payment = $filter('sanitizeInput')(noteInputFormModel.pdiPayment);
		 var isAllPresent = principal && term && interestRate && payment;
		 if(!(isAllPresent)) {
			 	interestRate = interestRate / 1200;
			 	if(payment > 0){
			 		payment = payment * -1;
			 	}else{
			 		payment = payment * 1;
			 	}
				 if (term && interestRate && payment) {
					var newPrinciple = UtilityService.getPV(interestRate, term, payment);
					noteInputFormModel.originalPrincipleBalance = UtilityService.round(newPrinciple, 2);
					return 'originalPrincipleBalance';
				}
			}
	}
	
	function createNote(noteInputFormModel) {
		
		var deferred = $q.defer();
		$http.post('api/createNote', noteInputFormModel)
			.then(
				function(response) {
					deferred.resolve(response.data);
				},
				function(errResponse) {
					console.error('Error while creating note');
					deferred.reject(errResponse);
				}
		)
		return deferred.promise;
	}
	
	function updateNote(noteDetailModel) {
		WaitingDialog.show();
		var deferred = $q.defer();
		$http.post('api/saveNote', noteDetailModel)
			.then(
				function(response) {
					deferred.resolve(response.data);
				},
				function(errResponse) {
					console.error('Error while edit note');
					deferred.reject(errResponse);
				}
		).then(
				function() {
					WaitingDialog.hide();
				});
		return deferred.promise;
	}
	
	function subscribeNote(noteDetailModel) {
		WaitingDialog.show();
		var deferred = $q.defer();
		$http.post('api/subscribeNote', noteDetailModel)
			.then(
				function(response) {
					deferred.resolve(response.data);
				},
				function(errResponse) {
					console.error('Error while edit note');
					deferred.reject(errResponse);
				}
		).then(
				function() {
					WaitingDialog.hide();
				});
		return deferred.promise;
	}
	
	function deleteNote(noteDetailModel) {
		var deferred = $q.defer();
		$http.post('api/deleteNote',noteDetailModel)
			.then(
				function(response) {
					deferred.resolve(response.data);
				},
				function(errResponse) {
					console.error('Error while delete note ');
					deferred.reject(errResponse);
				}
		)
		return deferred.promise;
	}
	
	function getNoteDetail(noteId) {
		WaitingDialog.show();
		var deferred = $q.defer();
		$http.get('api/getNoteDetail/'+noteId)
			.then(
				function(response) {
					deferred.resolve(response.data);
				},
				function(errResponse) {
					console.error('Error while fetching note '+noteId);
					deferred.reject(errResponse);
				}
		).then(
				function() {
					WaitingDialog.hide();
				});
		return deferred.promise;
	}
	
	function updateAndSaveMarketValue(model) {
		WaitingDialog.show();
		var deferred = $q.defer();
		$http.post('api/updateAndSaveMarketValue', model)
			.then(
				function(response) {
					deferred.resolve(response.data);
				},
				function(errResponse) {
					console.error('Error while updating note ');
					deferred.reject(errResponse);
				}
		).then(
				function() {
					WaitingDialog.hide();
				});
		return deferred.promise;
	}
	
	function updateMarketValue(model) {
		WaitingDialog.show();
		var deferred = $q.defer();
		$http.post('api/updateMarketValue', model)
			.then(
				function(response) {
					deferred.resolve(response.data);
				},
				function(errResponse) {
					console.error('Error while updating note ');
					deferred.reject(errResponse);
				}
		).then(
				function() {
					WaitingDialog.hide();
				});
		return deferred.promise;
	}
	
	function noteAnalyze(noteInputFormModel) {
		WaitingDialog.show();
		var deferred = $q.defer();
		$http.post('analyzeNote' , noteInputFormModel).then(
				function(response) {
					deferred.resolve(response.data);
				},
				function(errResponse) {
					deferred.reject(errResponse);
				}
		).then(
				function() {
					WaitingDialog.hide();
				});
		return deferred.promise;
	}
	
	function uploadNoteFile(file, uploadUrl) {
		WaitingDialog.show();
		var fd = new FormData();
		fd.append('file', file);

		$http.post(uploadUrl, fd, {
			transformRequest : angular.identity,
			headers : {
				'Content-Type' : undefined
			}
		}).then(function(response) {
			toastr.success('File has been uploaded Successfully!!');
		}, function(errResponse) {
			toastr.error('Unable to upload file. Please try after sometime.');
		}).then(
				function() {
					WaitingDialog.hide();
				});
	}

	
	function getGeoDetails(zipCode){
		var noteInputFormModel= {address:{}};
		noteInputFormModel.address.zipCode = zipCode;
		var zip = zipCode;
	    var lat;
	    var lng;
	    var deferred = $q.defer();
	    var geocoder = new google.maps.Geocoder();
	    geocoder.geocode({ 'address': zip }, function (results, status) {
	        if (status == google.maps.GeocoderStatus.OK) {
	            geocoder.geocode({'latLng': results[0].geometry.location}, function(results, status) {
	            if (status == google.maps.GeocoderStatus.OK) {
	                if (results[1]) {
	                     getCityState(results,noteInputFormModel);
	                     deferred.resolve(noteInputFormModel);
	                }
	            }else{
	            	deferred.reject(status);
	            }
	        });
	        }else{
	        	deferred.reject(status);
	        }
	    }); 
	    return deferred.promise;
	}
	
	function getCityState(results,noteInputFormModel)
    {
        var a = results[0].address_components;
        var city, state,i;
        for(i = 0; i <  a.length; ++i)
        {
           var t = a[i].types;
           if(compIsType(t, 'administrative_area_level_1')){
              state = a[i].long_name; //store the state
           	  noteInputFormModel.address.state= state;
           }
           else if(compIsType(t, 'locality')){
              city = a[i].long_name; //store the city
           	  noteInputFormModel.address.city= city;
           }
        }
        return (city + ', ' + state)
    }

function compIsType(t, s) {
	var z;
       for(z = 0; z < t.length; ++z) 
          if(t[z] == s)
             return true;
       return false;
    }

	
}]);

