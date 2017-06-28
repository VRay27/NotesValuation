'use strict';


noteApp.factory('NoteService', ['$http', 'toastr', '$q', '$rootScope', '$uibModal', 'WaitingDialog',function($http, toastr, $q, $rootScope, $uibModal,WaitingDialog) {
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
		 if(!(principal && term && interestRate && payment)) {
			 	interestRate = interestRate / 1200;
			 	payment = payment * -1;
				if (principal && term && interestRate) {
					var pay = principal * interestRate / (1 - (Math.pow(1 / (1 + interestRate), term)));
					noteInputFormModel.pdiPayment = round(pay,2);
				} else if (principal && term && payment) {
					var newRate = calculateRate(term,payment, principal) * 1200;
					noteInputFormModel.rate = round(newRate,2);
				} else if (principal && interestRate && payment) {
					var newTerm = getNPER(interestRate, payment, principal);
					noteInputFormModel.originalTerm = round(newTerm, 2);
				} else if (term && interestRate && payment) {
					var newPrinciple = getPV(interestRate, term, payment);
					noteInputFormModel.upb = round(newPrinciple, 2);
				}
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
		WaitingDialog.show();
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
		}).then(function() {
			WaitingDialog.hide();
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
		
	
	 function getPV (rate, periods, payment, future, type) {
							  future = future || 0;
							  type = type || 0;

							  // Return present value
							  if (rate === 0) {
							    return -payment * periods - future;
							  } else {
							    return (((1 - Math.pow(1 + rate, periods)) / rate) * payment * (1 + rate * type) - future) / Math.pow(1 + rate, periods);
							  }
							};
							
	function round(value, decimals) {
		if(value){
			return Number(Math.round(value+'e'+decimals)+'e-'+decimals);
		}
	}
}]);

