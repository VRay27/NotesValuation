var noteApp = angular.module('NoteApp');
noteApp.controller('HomeCtrl', function($scope, $stateParams, $state, $auth, $http, $uibModal, toastr, $rootScope, noteUploadAPI, NoteService) {
	$scope.noteAnalyzed = function() {
		NoteService.getGeoDetails($scope.zipCode).then(function(noteInputFormModel){
			NoteService.noteAnalyze(noteInputFormModel);
		},function(errResponse) {
			toastr.error('Error while fetching location details');
		});
		
	};


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

	$scope.populateNoteInputModelFromJS = function(){
		var model = $scope.noteInputFormModel 
		NoteService.noteCalculator(model);
		$scope.noteInputFormModel = model;
	};

});

noteApp.controller('noteInputFormController', function($scope, $rootScope, $state, $uibModalInstance, noteInputFormModel, $auth, NoteService) {
	$scope.noteInputFormModel = noteInputFormModel;
	$scope.hasError = function(field, validation) {
		if (validation) {
			return ($scope.noteInputForm[field].$dirty && $scope.noteInputForm[field].$error[validation]) || ($scope.submitted && $scope.noteInputForm[field].$error[validation]);
		}
		return ($scope.noteInputForm[field].$dirty && $scope.noteInputForm[field].$invalid) || ($scope.submitted && $scope.noteInputForm[field].$invalid);
	};

	$scope.populateNoteInputModelFromJS = function(){
		var model = $scope.noteInputFormModel 
		NoteService.noteCalculator(model);
		$scope.noteInputFormModel = model;
	};
	
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
	$scope.altInputFormats = ['MM/dd/yyyy'];
	$scope.save = function() {
		$scope.populateNoteInputModelFromJS();
		$scope.submitted = true;
		if ($scope.noteInputForm.$valid) {

			if ($auth.isAuthenticated()) {
				$uibModalInstance.close();
				createNoteService();

			} else {
				$uibModalInstance.close($scope.noteInputFormModel);
				$state.go('login', {
					'referer' : 'home',
					'loginState' : 'inputNoteForm'
				});
			}
		};
	};
	$scope.cancel = function() {
		$uibModalInstance.dismiss('cancel');
	};

	function createNoteService() {
		NoteService.createNote($scope.noteInputFormModel).then(function() {
			$state.go('noteDashboard');
		}, function(errResponse) {
			toastr.error('Error while creating NOTE');
		});
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