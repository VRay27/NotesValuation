
var noteApp = angular.module('NoteApp');
noteApp.controller('HomeCtrl', function($scope, $stateParams, $state,$document, $auth, $http, $uibModal, toastr, $rootScope, noteUploadAPI, NoteService,UtilityService) {
	NoteService.setInputFormModel(null);
	$scope.noteAnalyzed = function() {
		var noteInputFormModel = {};
		 noteInputFormModel.zipCode = $scope.zipCode;
		NoteService.noteAnalyze(noteInputFormModel).then(function(response) {
			NoteService.setInputFormModel(response);
			$state.go('noteInputForm');
		},function(errResponse) {
			toastr.error('Error while fetching details for this note.');
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
			toastr.error('Error while creating NOTE');
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
	};

	$scope.clearCalcField = function(inputField){
		if($scope.noteInputFormModel[inputField]){
			$scope.noteInputFormModel[inputField] ='';
		}else{
			$scope.noteInputFormModel = {};	
		}
	}
	
});

noteApp.controller('noteInputFormController', function($scope, $rootScope, $state, $auth, $filter,NoteService,toastr,WaitingDialog,$window,UserService) {
	$scope.noteInputFormModel = NoteService.getInputFormModel();
	 if($scope.noteInputFormModel && $scope.noteInputFormModel.zipCode){
		 $window.localStorage.setItem('zipCode', $scope.noteInputFormModel.zipCode); 
	 }else{
		 var noteInputFormModel = {};
		 $scope.noteInputFormModel = noteInputFormModel;
		 noteInputFormModel.zipCode = $window.localStorage.getItem('zipCode');
		 NoteService.noteAnalyze(noteInputFormModel).then(function(response) {
	 			NoteService.setInputFormModel(response);
	 			$scope.noteInputFormModel = response;
	 			$scope.noteInputFormModel.selCity = noteInputFormModel.addressModel.cityList[0];
	 			$scope.noteInputFormModel.selState = noteInputFormModel.addressModel.stateList[0];
	 			$scope.noteInputFormModel.selPropType=noteInputFormModel.selPropType || noteInputFormModel.propTypeList[0].propertyTypeCode;
	 			$scope.noteInputFormModel.selNoteType= noteInputFormModel.selNoteType || noteInputFormModel.noteTypeList[0].noteType;
	 			$scope.noteInputFormModel.selLoanType=noteInputFormModel.selLoanType || noteInputFormModel.loanTypeList[0].loanTypeCode;
	 			
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
		var model = $scope.noteInputFormModel 
		NoteService.noteCalculator(model);
		$scope.noteInputFormModel = model;
	};
	
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
				}, function(response) {
					toastr.error('We are fetch the details for zipcode');
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

noteApp.filter('getDefaultValueForNull', function(){
    return function(obj){
    	if(obj){
    		return obj;
    	}
    	return  'No data available';
    }
});

noteApp.filter('sanitizeInput', function() {
    return function(value) {
    	if(value){
    		var temp = value.toString().replace('$', '');
    		 temp = temp.replace('%', '');
    		return temp.replace(',', '');	
    	}
        return value;
    }
})