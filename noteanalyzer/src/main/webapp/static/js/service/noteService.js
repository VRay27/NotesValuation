'use strict';


noteApp.factory('NoteService', ['$http', 'toastr', '$q', '$rootScope', '$uibModal', function($http, toastr, $q, $rootScope, $uibModal) {
	var factory = {
		createNote : createNote,
		noteAnalyze : noteAnalyze,
		getNoteDetail : getNoteDetail,
		deleteNote : deleteNote,
		editNote : editNote,
		uploadNoteFile : uploadNoteFile,
		noteCalculator : noteCalculator,
		getGeoDetails : getGeoDetails
	};

	return factory;

	function noteCalculator(noteInputFormModel) {
		 var principal = noteInputFormModel.upb;
		 var term  = noteInputFormModel.originalTerm;
		 var interestRate   = noteInputFormModel.rate;
		 var payment = noteInputFormModel.pdiPayment;
		 if(principal && term && interestRate){
			 interestRate = interestRate/1200;
			 noteInputFormModel.pdiPayment= principal * interestRate / (1 - (Math.pow(1/(1 + interestRate), term)));
		 } else if(principal && term && payment){
			 noteInputFormModel.rate = calculateRate(term,payment,principal);
		 } else if(principal && interestRate && payment){
			 noteInputFormModel.term = getNPER(interestRate, payment, principal);
		 }else if(term && interestRate && payment){
			 noteInputFormModel.principal = getIPMT(interestRate, payment, term);
		 }
		 
		return noteInputFormModel;
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
		);
		return deferred.promise;
	}
	
	function editNote(noteDetailModel) {
		var deferred = $q.defer();
		$http.post('api/editNote', noteDetailModel)
			.then(
				function(response) {
					deferred.resolve(response.data);
				},
				function(errResponse) {
					console.error('Error while edit note');
					deferred.reject(errResponse);
				}
		);
		return deferred.promise;
	}
	
	function deleteNote(noteId) {
		var deferred = $q.defer();
		$http.delete('api/deleteNote/'+noteId)
			.then(
				function(response) {
					deferred.resolve(response.data);
				},
				function(errResponse) {
					console.error('Error while delete note '+noteId);
					deferred.reject(errResponse);
				}
		);
		return deferred.promise;
	}
	
	function getNoteDetail(noteId) {
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
		);
		return deferred.promise;
	}
	
	function noteAnalyze(noteInputFormModel) {
		$http.post('analyzeNote' , noteInputFormModel).then(function(response) {

			var noteInputFormModel = response.data;
			var modalInstance = $uibModal.open({
				templateUrl : 'static/template/note-form.html',
				controller : 'noteInputFormController',
				resolve : {
					'noteInputFormModel' : noteInputFormModel
				}
			});
			modalInstance.result.then(function(response) {
				$rootScope.submitInputFormModel = response;
			}, function() {
				console.log('Modal dismissed at: ' + new Date());
			});
		}, function(response) {
			toastr.error('Unable to process your request');
		});
	}
	
	function uploadNoteFile(file, uploadUrl) {
		var fd = new FormData();
		fd.append('file', file);

		$http.post(uploadUrl, fd, {
			transformRequest : angular.identity,
			headers : {
				'Content-Type' : undefined
			}
		}).then(function(response) {
			toastr.success('File has been uploaded Successfully!!');
		}, function(response) {
			toastr.error('Unable to upload file. Please try after sometime.');
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

	 function calculateRate(term, payment, principal, future, type, guess) {

		  guess = (guess === undefined) ? 0.01 : guess;
		  future = (future === undefined) ? 0 : future;
		  type = (type === undefined) ? 0 : type;

		

		  // Set maximum epsilon for end of iteration
		  var epsMax = 1e-6;

		  // Set maximum number of iterations
		  var iterMax = 100;
		  var iter = 0;
		  var close = false;
		  var rate = guess;

		  while (iter < iterMax && !close) {
		    var t1 = Math.pow(rate + 1, term);
		    var t2 = Math.pow(rate + 1, term - 1);

		    var f1 = future + t1 * principal + payment * (t1 - 1) * (rate * type + 1) / rate;
		    var f2 = term * t2 * principal - payment * (t1 - 1) *(rate * type + 1) / Math.pow(rate,2);
		    var f3 = term * payment * t2 * (rate * type + 1) / rate + payment * (t1 - 1) * type / rate;

		    var newRate = rate - f1 / (f2 + f3);

		    if (Math.abs(newRate - rate) < epsMax) close = true;
		    iter++
		    rate = newRate;
		  }

		  if (!close) return Number.NaN + rate;
		  return rate;
		};
		
		

		function getNPER(rate, payment, present, future, type) {
		  // Initialize type
		  var type = (typeof type === 'undefined') ? 0 : type;

		  // Initialize future value
		  var future = (typeof future === 'undefined') ? 0 : future;

		  // Evaluate rate and periods (TODO: replace with secure expression evaluator)
		  rate = eval(rate);

		  // Return number of periods
		  var num = payment * (1 + rate * type) - future * rate;
		  var den = (present * rate + payment * (1 + rate * type));
		  return Math.log(num / den) / Math.log(1 + rate);
		}
		
		function  getIPMT(rate, period, periods, present, future, type) {
			  // Credits: algorithm inspired by Apache OpenOffice

			  // Initialize type
			  var type = (typeof type === 'undefined') ? 0 : type;

			  // Evaluate rate and periods (TODO: replace with secure expression evaluator)
			  rate = eval(rate);
			  periods = eval(periods);

			  // Compute payment
			  var payment = PMT(rate, periods, present, future, type);
			  
			  // Compute interest
			  var interest;
			  if (period === 1) {
			    if (type === 1) {
			      interest = 0;
			    } else {
			      interest = -present;
			    }
			  } else {
			    if (type === 1) {
			      interest = FV(rate, period - 2, payment, present, 1) - payment;
			    } else {
			      interest = FV(rate, period - 1, payment, present, 0);
			    }
			  }
			  
			  // Return interest
			  return interest * rate;
			}
		
		function getIPMT (rate, period, periods, present, future, type) {
			  // Credits: algorithm inspired by Apache OpenOffice

			  future = future || 0;
			  type = type || 0;

			 

			  // Compute payment
			  var payment = getPMT(rate, periods, present, future, type);

			  // Compute interest
			  var interest;
			  if (period === 1) {
			    if (type === 1) {
			      interest = 0;
			    } else {
			      interest = -present;
			    }
			  } else {
			    if (type === 1) {
			      interest = getFV(rate, period - 2, payment, present, 1) - payment;
			    } else {
			      interest = getFV(rate, period - 1, payment, present, 0);
			    }
			  }

			  // Return interest
			  return interest * rate;
			};
			
			 function getPMT(rate, periods, present, future, type) {
				  // Credits: algorithm inspired by Apache OpenOffice

				  future = future || 0;
				  type = type || 0;

				  rate = utils.parseNumber(rate);
				  periods = utils.parseNumber(periods);
				  present = utils.parseNumber(present);
				  future = utils.parseNumber(future);
				  type = utils.parseNumber(type);
				  if (utils.anyIsError(rate, periods, present, future, type)) {
				    return error.value;
				  }

				  // Return payment
				  var result;
				  if (rate === 0) {
				    result = (present + future) / periods;
				  } else {
				    var term = Math.pow(1 + rate, periods);
				    if (type === 1) {
				      result = (future * rate / (term - 1) + present * rate / (1 - 1 / term)) / (1 + rate);
				    } else {
				      result = future * rate / (term - 1) + present * rate / (1 - 1 / term);
				    }
				  }
				  return -result;
				};

	function getFV (rate, periods, payment, value, type) {
					  // Credits: algorithm inspired by Apache OpenOffice

					  value = value || 0;
					  type = type || 0;

					 
					  // Return future value
					  var result;
					  if (rate === 0) {
					    result = value + payment * periods;
					  } else {
					    var term = Math.pow(1 + rate, periods);
					    if (type === 1) {
					      result = value * term + payment * (1 + rate) * (term - 1) / rate;
					    } else {
					      result = value * term + payment * (term - 1) / rate;
					    }
					  }
					  return -result;
					};

}]);

