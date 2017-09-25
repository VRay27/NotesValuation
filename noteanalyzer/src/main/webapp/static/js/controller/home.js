
var noteApp = angular.module('NoteApp');
noteApp.controller('HomeCtrl', function($scope, $stateParams, $state,$document, $auth, $http, toastr, $rootScope, noteUploadAPI, NoteService,UtilityService,WaitingDialog) {
	UtilityService.setActiveHeader('home');
	NoteService.setInputFormModel(null);
	$scope.noteAnalyzed = function() {
		var noteInputFormModel = {};
		 noteInputFormModel.zipCode = $scope.zipCode;
		NoteService.noteAnalyze(noteInputFormModel).then(function(response) {
			NoteService.setInputFormModel(response);
			$state.go('noteInputForm');
		},function(errResponse) {
			if(errResponse.status==404){
				toastr.error('Zipcode not found');	
			}else{
				toastr.error('Error while fetching details for this note.');	
			}
			
		});
		
	};
	if(UtilityService.getParameterByName('verificationToken')){
			$state.go('login');
			toastr.success('Successfuly verified the user. Please login using your credential.');
	}

	if ($stateParams.loginState === 'inputNoteForm') {
		NoteService.createNote($rootScope.submitInputFormModel).then(function() {
			$rootScope.submitInputFormModel = {};
			$state.go('noteDashboard');
		}, function(errResponse) {
			toastr.error('Error while creating Note');
		});
	}

	$scope.uploadFile = function() {
		if ($auth.isAuthenticated()) {
			NoteService.uploadNoteFile($scope.myFile, noteUploadAPI);
		} else {
			$rootScope.noteUploadFile = $scope.myFile;
			$state.go('login', {
				'referer' : 'home',
				'loginState' : 'noteFileUpload'
			});
		}
	};

	if ($stateParams.loginState === 'noteFileUpload') {
		if ($rootScope.noteUploadFile) {
			NoteService.uploadNoteFile($rootScope.noteUploadFile, noteUploadAPI);
			$rootScope.noteUploadFile = undefined;
			$state.go('noteDashboard');
		}
	}

	$scope.populateNoteInputModelFromJS = function(inputField){
		if($scope.noteInputFormModel){
		var model = $scope.noteInputFormModel
		angular.element( document.querySelector('#originalPrincipleBalance')).removeClass('notesuccess noteError');
		angular.element( document.querySelector('#rate')).removeClass('notesuccess noteError');
		angular.element( document.querySelector('#originalTerm')).removeClass('notesuccess noteError');
		angular.element( document.querySelector('#pdiPayment')).removeClass('notesuccess noteError');
		var isAllPresent = model.originalPrincipleBalance && model.originalTerm && model.rate &&  model.pdiPayment;
		 
		 if(model[inputField] && isAllPresent){
				model[inputField] ='';
				isAllPresent =  model.originalPrincipleBalance && model.originalTerm && model.rate &&  model.pdiPayment;
			}
		 
		 if(isAllPresent){
			 alert('Please click on search icon of the field you want to calculate.');
		 }else{
			var calculatedField =  NoteService.noteCalculator($scope.noteInputFormModel);
			var elem = angular.element( document.querySelector('#'+calculatedField) );
			if($scope.noteInputFormModel[calculatedField]){
				elem.addClass('notesuccess');
			}else{
				elem.addClass('noteError');
			}
		 }
		}
	};

	$scope.clearCalcField = function(inputField){
		if($scope.noteInputFormModel){
		if($scope.noteInputFormModel[inputField]){
			$scope.noteInputFormModel[inputField] ='';
		}else{
			$scope.noteInputFormModel = {};	
		}
    	}
	}
	
	$scope.isNoteCSVAllowed = function(){
		return $auth.isPrivilegeExists("NOTE_CSV"); 
	}
	
});

noteApp.controller('CalculatorCtrl', function($scope, toastr, NoteService,UtilityService) {
	UtilityService.setActiveHeader('calculator');
	$scope.populateNoteInputModelFromJS = function(inputField){
		if($scope.noteInputFormModel){
		var model = $scope.noteInputFormModel
		angular.element( document.querySelector('#originalPrincipleBalance')).removeClass('notesuccess noteError');
		angular.element( document.querySelector('#rate')).removeClass('notesuccess noteError');
		angular.element( document.querySelector('#originalTerm')).removeClass('notesuccess noteError');
		angular.element( document.querySelector('#pdiPayment')).removeClass('notesuccess noteError');
		var isAllPresent = model.originalPrincipleBalance && model.originalTerm && model.rate &&  model.pdiPayment;
		 
		 if(model[inputField] && isAllPresent){
				model[inputField] ='';
				isAllPresent =  model.originalPrincipleBalance && model.originalTerm && model.rate &&  model.pdiPayment;
			}
		 
		 if(isAllPresent){
			 alert('Please click on search icon of the field you want to calculate.');
		 }else{
			var calculatedField =  NoteService.noteCalculator($scope.noteInputFormModel);
			var elem = angular.element( document.querySelector('#'+calculatedField) );
			if($scope.noteInputFormModel[calculatedField]){
				elem.addClass('notesuccess');
			}else{
				elem.addClass('noteError');
			}
		 }
		}
	};

	$scope.clearCalcField = function(inputField){
		if($scope.noteInputFormModel){
		if($scope.noteInputFormModel[inputField]){
			$scope.noteInputFormModel[inputField] ='';
		}else{
			$scope.noteInputFormModel = {};	
		}
    	}
	}
	
});



noteApp.controller('noteInputFormController', function($scope, $rootScope, $state, $auth, $filter,NoteService,toastr,WaitingDialog,$window,UserService,UtilityService) {
	$scope.noteInputFormModel = NoteService.getInputFormModel();
	UtilityService.setActiveHeader('home');
	 if($scope.noteInputFormModel && $scope.noteInputFormModel.zipCode){
		 $window.localStorage.setItem('zipCode', $scope.noteInputFormModel.zipCode); 
	 }else{
		 var noteInputFormModel = {};
		 $scope.noteInputFormModel = noteInputFormModel;
		 noteInputFormModel.zipCode = $window.localStorage.getItem('zipCode');
		 NoteService.noteAnalyze(noteInputFormModel).then(function(response) {
	 			NoteService.setInputFormModel(response);
	 			$scope.noteInputFormModel = response;
	 			$scope.noteInputFormModel.selCity = $scope.noteInputFormModel.addressModel.cityList[0];
	 			$scope.noteInputFormModel.selState = $scope.noteInputFormModel.addressModel.stateList[0];
	 			$scope.populateDefaultNoteTypeList();
	 			$scope.populateDefaultLoanTypeList();
	 			$scope.populateDefaultPropertyType();
	 			$scope.updateOrginalTerm();
	 		},function(errResponse) {
	 			toastr.error('Error while fetching details for this zip code.'+noteInputFormModel.zipCode);
	 		});
	 }
		 
	$scope.updateOrginalTerm = function(){
		if($scope.noteInputFormModel.loanTypeList){
		var len =$scope.noteInputFormModel.loanTypeList.length;
		for (var i = 0; i < len; i++) {
		    var loanTypeCode = $scope.noteInputFormModel.loanTypeList[i].loanTypeCode;
		    if(loanTypeCode == $scope.noteInputFormModel.selLoanType){
		    	$scope.noteInputFormModel.originalTerm = $scope.noteInputFormModel.loanTypeList[i].termMonths;
		    	break;
		    }
		}
		}
	}
	
	
	$scope.propertyTypeChange = function(){
		var selectedPropertyType = $scope.noteInputFormModel.selPropType;
		if( selectedPropertyType == 'DUPLEX' || selectedPropertyType == 'FOURPLEX' || selectedPropertyType == 'APARTMENT' || selectedPropertyType == 'TRIPLEX'){
			angular.element( document.querySelector('#numberOfUnits')).removeAttr('disabled');
			if( selectedPropertyType == 'DUPLEX'){
				angular.element( document.querySelector('#numberOfUnits')).val('2');
			}else if( selectedPropertyType == 'FOURPLEX'){
				angular.element( document.querySelector('#numberOfUnits')).val('4');
			}else if( selectedPropertyType == 'TRIPLEX'){
				angular.element( document.querySelector('#numberOfUnits')).val('3');
			}
		}else{
			angular.element( document.querySelector('#numberOfUnits')).attr('disabled','disabled');
			angular.element( document.querySelector('#numberOfUnits')).val('');
		}
		
		if( selectedPropertyType == 'TH' || selectedPropertyType == 'CONDO'){
			angular.element( document.querySelector('#hoaFees')).removeAttr('disabled');
		}else{
			angular.element( document.querySelector('#hoaFees')).attr('disabled','disabled');
			angular.element( document.querySelector('#hoaFees')).val('');
		}
	}
	
	$scope.populateRemainingNumberOfPayment = function(){
		if($scope.noteInputFormModel){
			$scope.noteInputFormModel.noteDate = $filter('date')($scope.parent.noteDate, 'MM/dd/yyyy');
			$scope.noteInputFormModel.lastPaymentRecievedDate = $filter('date')($scope.parent.lastPaymentRecievedDate, 'MM/dd/yyyy');
			var numberOfMonth =  UtilityService.getNumberOfMonth(new Date($scope.noteInputFormModel.noteDate), new Date($scope.noteInputFormModel.lastPaymentRecievedDate));
			if($scope.noteInputFormModel.originalTerm){
				$scope.noteInputFormModel.remainingPayment = $scope.noteInputFormModel.originalTerm - numberOfMonth;
				$scope.populateUPBFromJS();
			}
		}
	}
	
	$scope.sanitizeNoteInputModelFromJS = function(){
		$scope.noteInputFormModel.rate = $filter('sanitizeInput')($scope.noteInputFormModel.rate);
		$scope.noteInputFormModel.upb  = $filter('sanitizeInput')($scope.noteInputFormModel.upb);
		$scope.noteInputFormModel.pdiPayment = $filter('sanitizeInput')($scope.noteInputFormModel.pdiPayment);
		$scope.noteInputFormModel.tdiPayment = $filter('sanitizeInput')($scope.noteInputFormModel.tdiPayment);
		$scope.noteInputFormModel.originalPrincipleBalance = $filter('sanitizeInput')($scope.noteInputFormModel.originalPrincipleBalance);
		$scope.noteInputFormModel.notePrice = $filter('sanitizeInput')($scope.noteInputFormModel.notePrice);
		$scope.noteInputFormModel.originalPropertyValue = $filter('sanitizeInput')($scope.noteInputFormModel.originalPropertyValue);
		$scope.noteInputFormModel.remainingPayment = $filter('sanitizeInput')($scope.noteInputFormModel.remainingPayment);
		$scope.noteInputFormModel.estimatedMarketValue = $filter('sanitizeInput')($scope.noteInputFormModel.estimatedMarketValue);
		
	}

	$scope.populateNoteInputModelFromJS = function(){
		angular.element( document.querySelector('.modifiedField')).trigger('change');
		angular.element( document.querySelector('#originalTermId')).removeClass('noteInputCalculatedField');
		angular.element( document.querySelector('#orginalLoanBalanceId')).removeClass('noteInputCalculatedField');
		angular.element( document.querySelector('#interestRateId')).removeClass('noteInputCalculatedField');
		//angular.element( document.querySelector('#paymentId')).removeClass('noteInputCalculatedField');
		$scope.noteInputFormModel.pdiPayment = '';
		var model = $scope.noteInputFormModel;
		var elem = NoteService.notePaymentCalculator(model);
		$scope.noteInputFormModel = model;
		if(elem == 'rate'){
			angular.element( document.querySelector('#interestRateId')).addClass('noteInputCalculatedField');
		}else if(elem == 'pdiPayment'){
			angular.element( document.querySelector('#paymentId')).addClass('noteInputCalculatedField');
			angular.element( document.querySelector('#pdiPayment')).attr('readonly','readonly');
		}else if(elem == 'originalTerm'){
			angular.element( document.querySelector('#originalTermId')).addClass('noteInputCalculatedField');
		}else if(elem == 'originalPrincipleBalance'){
			angular.element( document.querySelector('#orginalLoanBalanceId')).addClass('noteInputCalculatedField');
		}
		$scope.populateUPBFromJS();
	};
	
	
	$scope.populateUPBFromJS = function(){
		angular.element( document.querySelector('#interestRateId')).removeClass('noteInputCalculatedField');
		//angular.element( document.querySelector('#paymentId')).removeClass('noteInputCalculatedField');
		angular.element( document.querySelector('#unpaidBalanceId')).removeClass('noteInputCalculatedField');
		angular.element( document.querySelector('#remainingNoOfPaymentId')).removeClass('noteInputCalculatedField');
			var sampleNoteModel = {
					  pdiPayment:$scope.noteInputFormModel.pdiPayment,
					originalTerm:$scope.noteInputFormModel.remainingPayment,
						  	rate:$scope.noteInputFormModel.rate
			}
			NoteService.noteOriginalBalCalculator(sampleNoteModel);
			var unPaidBal = sampleNoteModel.originalPrincipleBalance;
			if(unPaidBal){
				$scope.noteInputFormModel.upb = sampleNoteModel.originalPrincipleBalance;
				angular.element( document.querySelector('#unpaidBalanceId')).addClass('noteInputCalculatedField');	
			}
	}

	$scope.populateDefaultPropertyType = function() {
		if ($scope.noteInputFormModel.propTypeList) {
			$scope.noteInputFormModel.propTypeList = $filter('orderBy')($scope.noteInputFormModel.propTypeList, 'propertyTypeValue');
			$scope.noteInputFormModel.selPropType = $scope.noteInputFormModel.selPropType || $scope.noteInputFormModel.propTypeList[0].propertyTypeCode;
			$scope.propertyTypeChange();
		}
	}
	
	$scope.populateDefaultLoanTypeList = function() {
		if ($scope.noteInputFormModel.loanTypeList) {
			$scope.noteInputFormModel.loanTypeList = $filter('orderBy')($scope.noteInputFormModel.loanTypeList, 'loanTypeValue');
			$scope.noteInputFormModel.selLoanType=$scope.noteInputFormModel.selLoanType || $scope.noteInputFormModel.loanTypeList[0].loanTypeCode;
		}
	}
	
	$scope.populateDefaultNoteTypeList = function() {
		if ($scope.noteInputFormModel.noteTypeList) {
			$scope.noteInputFormModel.noteTypeList = $filter('orderBy')($scope.noteInputFormModel.noteTypeList, 'description');
			$scope.noteInputFormModel.selNoteType= $scope.noteInputFormModel.selNoteType || $scope.noteInputFormModel.noteTypeList[0].noteType;
		}
	}


	$scope.cancel = function(){
		$scope.noteInputFormModel = {};
		$state.go('home');
	}
	
	$scope.dateOptions = {
		formatYear : 'yy',
		maxDate : new Date(2100, 12, 31),
		minDate : new Date(1800, 12, 31),
		startingDay : 1
	};

	$scope.noteDate = {
		opened : false
	};
	$scope.noteDate = function() {
		$scope.noteDate.opened = true;
	};
	
	$scope.parent = {noteDate:$scope.noteInputFormModel.noteDate,lastPaymentRecievedDate:$scope.noteInputFormModel.noteDate};
	
	$scope.lastPaymentRecievedDate = {
			opened : false
		};
	$scope.lastPaymentRecievedDate = function() {
			$scope.lastPaymentRecievedDate.opened = true;
	};
	
	$scope.populateCityState = function() {
		if ($scope.noteInputFormModel.zipCode) {
			UserService.getCityStateFromZipCode($scope.noteInputFormModel.zipCode)
				.then(function(response) {
					$scope.noteInputFormModel.addressModel = response;
					$scope.noteInputFormModel.selCity = $scope.noteInputFormModel.addressModel.cityList[0];
		 			$scope.noteInputFormModel.selState = $scope.noteInputFormModel.addressModel.stateList[0];
				}, function(response) {
					if(response.status==404){
						toastr.error('Zipcode not found');
					}else{
						toastr.error('Error occurred while fetching for zipcode');	
					}
					$scope.noteInputFormModel.addressModel= {};
				});
		}
	}

	
	$scope.altInputFormats = ['MM/dd/yyyy'];
	$scope.createNote = function() {
		$scope.sanitizeNoteInputModelFromJS();
		$scope.noteInputFormModel.noteDate = $filter('date')($scope.parent.noteDate, 'MM/dd/yyyy');
		$scope.noteInputFormModel.lastPaymentRecievedDate = $filter('date')($scope.parent.lastPaymentRecievedDate, 'MM/dd/yyyy');
		NoteService.getYield($scope.noteInputFormModel);
		$scope.submitted = true;

			if ($auth.isAuthenticated()) {
				createNoteService();
			} else {
				$rootScope.submitInputFormModel = $scope.noteInputFormModel;
				$state.go('login', {
					'referer' : 'home',
					'loginState' : 'inputNoteForm'
				});
			}
	};

	function createNoteService() {
		WaitingDialog.show();
		NoteService.createNote($scope.noteInputFormModel).then(function() {
			$state.go('noteDashboard');
			NoteService.setInputFormModel(null);
		}, function(errResponse) {
			WaitingDialog.hide();
			$scope.lastPaymentRecievedDate.opened = false;
			$scope.noteDate.opened = false;
			toastr.error('Error while creating note. Please try after sometime.');
		})
	}


});



noteApp.directive('fileModel', ['$parse', function($parse) {
	return {
		restrict : 'A',
		link : function(scope, element, attrs) {
			var model = $parse(attrs.fileModel);
			var modelSetter = model.assign;

			element.bind('change', function() {
				scope.$apply(function() {
					modelSetter(scope, element[0].files[0]);
				});
			});
		}
	};
}]);

noteApp.service('fileUpload', ['$http', 'toastr', function($http, toastr) {
	this.uploadFileToUrl = function(file, uploadUrl) {
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
}]);

noteApp.controller('NavbarCtrl', function($scope, $auth) {
	$scope.isAuthenticated = function() {
		return $auth.isAuthenticated();
	};
});

noteApp.filter('getDefaultValueForNull', function($auth){
    return function(obj){
    	var user = $auth.getUser();
    		if(user){
    			var subscription = user.subscriptionName;
    			if("P1" == subscription){
    				if(!obj){
    					return "No Data Available";
    				}else{
    					return obj;
    				}
    			}else{
    				return  'Available through subscription';
    			}
    		}else{
    			return "Unable to find user deatils";
    		}
    	}
});

noteApp.filter('getDefaultValueForPercentage', function($auth){
    return function(obj){
    	var user = $auth.getUser();
    		if(user){
    			var subscription = user.subscriptionName;
    			if("P1" == subscription){
    				if(!obj){
    					return "No Data Available";
    				}else{
    					return obj+'%';
    				}
    			}else{
    				return  'Available through subscription';
    			}
    		}else{
    			return "Unable to find user deatils";
    		}
    	}
});

noteApp.filter('sanitizeInput', function() {
    return function(value) {
    	if(value){
    		var temp = value.toString().replace(/\$/g, '');
    		 temp = temp.replace(/\%/g, '');
    		return temp.replace(/\,/g, '');	
    	}
        return value;
    }
});


