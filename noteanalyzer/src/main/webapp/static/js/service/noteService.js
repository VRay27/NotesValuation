'use strict';


noteApp.factory('NoteService', ['$http', 'toastr', '$q', '$rootScope', '$uibModal', 'WaitingDialog','UtilityService',function($http, toastr, $q, $rootScope, $uibModal,WaitingDialog,UtilityService) {
	var noteInputFormModel;
	var NoteDetailModel;
	var factory = {
		createNote : createNote,
		noteAnalyze : noteAnalyze,
		getNoteDetail : getNoteDetail,
		deleteNote : deleteNote,
		editNote : editNote,
		uploadNoteFile : uploadNoteFile,
		noteCalculator : noteCalculator,
		getGeoDetails : getGeoDetails,
		setInputFormModel : setInputFormModel,
		getInputFormModel : getInputFormModel,
		setNoteDetailModel : setNoteDetailModel,
		getNoteDetailModel : getNoteDetailModel,
		getYield : getYield
		
		
	};

	return factory;
	
	function getYield(model){
		var newRate = UtilityService.calculateRate(model.remainingNoOfPayment,model.pdiPayment*-1, model.notePrice) * 1200;
		model.yieldValue = UtilityService.round(newRate,2);
	}
	
	function setInputFormModel(model){
		noteInputFormModel = model;
	}
	function getInputFormModel(){
		return noteInputFormModel;
	}
	
	function setNoteDetailModel(NoteDetailModel){
		NoteDetailModel = NoteDetailModel;
	}
	function getNoteDetailModel(){
		return NoteDetailModel;
	}


	function noteCalculator(noteInputFormModel) {
		 var principal = noteInputFormModel.upb;
		 var term  = noteInputFormModel.originalTerm;
		 var interestRate   = noteInputFormModel.rate;
		 var payment = noteInputFormModel.pdiPayment;
		 var isAllPresent = principal && term && interestRate && payment;
		 if(!(isAllPresent)) {
			 	interestRate = interestRate / 1200;
			 	payment = payment * -1;
				if (principal && term && interestRate) {
					var pay = principal * interestRate / (1 - (Math.pow(1 / (1 + interestRate), term)));
					noteInputFormModel.pdiPayment = UtilityService.round(pay,2);
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
					noteInputFormModel.upb = UtilityService.round(newPrinciple, 2);
					return 'upb';
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
	
	function editNote(noteDetailModel) {
		WaitingDialog.show();
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
		).then(
				function() {
					WaitingDialog.hide();
				});
		return deferred.promise;
	}
	
	function deleteNote(noteId) {
		WaitingDialog.show();
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
		).then(
				function() {
					WaitingDialog.hide();
				});
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
	
	function noteAnalyze(noteInputFormModel) {
		WaitingDialog.show();
		var deferred = $q.defer();
		$http.post('analyzeNote' , noteInputFormModel).then(
				function(response) {
					deferred.resolve(response.data);
				},
				function(errResponse) {
					console.error('Error while creating note');
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
		}, function(response) {
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

