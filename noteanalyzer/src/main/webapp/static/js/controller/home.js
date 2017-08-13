
var noteApp = angular.module('NoteApp');
noteApp.controller('HomeCtrl', function($scope, $stateParams, $state,$document, $auth, $http, $uibModal, toastr, $rootScope, noteUploadAPI, NoteService,UtilityService) {
	$scope.noteAnalyzed = function() {
		var noteInputFormModel = {};
		noteInputFormModel.address = {};
		 noteInputFormModel.address.zipCode = $scope.zipCode;
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
		angular.element( document.querySelector('#upb')).removeClass('notesuccess noteError');
		angular.element( document.querySelector('#rate')).removeClass('notesuccess noteError');
		angular.element( document.querySelector('#originalTerm')).removeClass('notesuccess noteError');
		angular.element( document.querySelector('#pdiPayment')).removeClass('notesuccess noteError');
		var isAllPresent = model.upb && model.originalTerm && model.rate &&  model.pdiPayment;
		 
		 if(model[inputField] && isAllPresent){
				model[inputField] ='';
				isAllPresent =  model.upb && model.originalTerm && model.rate &&  model.pdiPayment;
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

noteApp.controller('noteInputFormController', function($scope, $rootScope, $state, $auth, $filter,NoteService,toastr,WaitingDialog) {
	$scope.noteInputFormModel = NoteService.getInputFormModel();
	
	$scope.clearNoteInputModelFromJS = function(){
		$scope.noteInputFormModel.streetAddress = '';
		$scope.noteInputFormModel.noteDate = '';
		$scope.noteInputFormModel.upb = '';
		$scope.noteInputFormModel.rate = '';
		$scope.noteInputFormModel.pdiPayment = '';
		$scope.noteInputFormModel.tdiPayment = '';
		$scope.noteInputFormModel.originalPrincipleBalance = '';
		$scope.noteInputFormModel.salesPrice = '';
		$scope.noteInputFormModel.noOfLatePayment = '';
		$scope.noteInputFormModel.notePosition = '';
		$scope.noteInputFormModel.borrowerCreditScore = '';
		$scope.noteInputFormModel.notePrice = '';
		$scope.noteInputFormModel.remainingNoOfPayment = '';
		$scope.noteInputFormModel.originalPropertyValue = '';
		$scope.noteInputFormModel.userScore = '';
		$scope.noteInputFormModel.salePrice = '';
		
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
	
	$scope.performingList = [
	    {code : "Y", displayValue : "Performing"},
	    {code : "N", displayValue : "Non-Performing"},
	    {code : "U", displayValue : "Unknown"}
	];
	$scope.altInputFormats = ['MM/dd/yyyy'];
	$scope.createNote = function() {
		
		
		$scope.noteInputFormModel.noteDate = $filter('date')($scope.noteInputFormModel.noteDate, 'MM/dd/yyyy');
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
		}, function(errResponse) {
			WaitingDialog.hide();
			toastr.error('Error while creating note');
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