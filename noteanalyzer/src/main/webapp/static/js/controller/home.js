var noteApp = angular.module('NoteApp');
noteApp.controller('HomeCtrl', function($scope, $stateParams, $state, $auth, $http, $uibModal, toastr, $rootScope, noteUploadAPI, fileUpload,NoteService) {
	$scope.noteAnalyzed = function(modalName) {
		$scope.noteInputFormModel = {};
		$http.get('analyzeNote/' + $scope.zipCode).then(function(response) {
			
			$scope.noteInputFormModel = response.data;
			var modalInstance = $uibModal.open({
				templateUrl : 'static/template/note-form.html',
				controller : 'noteInputFormController',
			    resolve: {
			          'noteInputFormModel': $scope.noteInputFormModel
			      }
			});
			modalInstance.result.then(function(response) {
				$scope.selected = response;
				console.log('Modal result selected at: ' + $scope.selected);
			}, function() {
				console.log('Modal dismissed at: ' + new Date());
			});
		}, function(response) {
			toastr.error('Unable to process your request');
		});

	};


	if ($stateParams.loginState === 'inputNoteForm') {
		$rootScope.$broadcast("callCreateNote", {});
		//$stateParams.loginState =='';
		/*NoteService.createNote($scope.noteInputFormModel).then(function(){
			$state.go('noteDashboard');
		},function(errResponse){
			console.error('Error while creating NOTE');
		});*/
	} else {
		$scope.noteInputFormModel = {};
	}

	$scope.uploadFile = function() {
		if ($auth.isAuthenticated()) {
			fileUpload.uploadFileToUrl($scope.myFile, noteUploadAPI);
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
			fileUpload.uploadFileToUrl($rootScope.noteUploadFile, noteUploadAPI);
			$rootScope.noteUploadFile = undefined;
			$state.go('noteDashboard');
		}
	}


});

noteApp.controller('noteInputFormController', function($scope,$rootScope, $state, $uibModalInstance, noteInputFormModel, $auth,NoteService) {
	$scope.noteInputFormModel = noteInputFormModel;
	$scope.hasError = function(field, validation) {
		if (validation) {
			return ($scope.noteInputForm[field].$dirty && $scope.noteInputForm[field].$error[validation]) || ($scope.submitted && $scope.noteInputForm[field].$error[validation]);
		}
		return ($scope.noteInputForm[field].$dirty && $scope.noteInputForm[field].$invalid) || ($scope.submitted && $scope.noteInputForm[field].$invalid);
	};

	$scope.dateOptions = {
		/*dateDisabled: disabled,*/
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
		$scope.submitted = true;
		if ($scope.noteInputForm.$valid) {
			
			if ($auth.isAuthenticated()) {
				$uibModalInstance.close();
				createNoteService();
				
			} else {
				$uibModalInstance.dismiss('cancel');
				$state.go('login', {
					'referer' : 'home',
					'loginState' : 'inputNoteForm'
				});
			}
		};
	};
	$scope.cancel = function() {
		$uibModalInstance.dismiss('cancel');
		console.log('cancel');
	};
	
 function createNoteService(){
	 NoteService.createNote($rootScope.noteInputFormModel).then(function(){
			$state.go('noteDashboard');
		},function(errResponse){
			console.error('Error while creating NOTE');
		});
 }
 

});


noteApp.constant('noteUploadAPI', 'api/noteUpload');

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

noteApp.factory('NoteService', ['$http', 'toastr','$q', function($http, toastr,$q) {
	 var factory = {
		        createNote: createNote
		    };

	return factory;
	
	  function createNote(noteInputFormModel) {
	        var deferred = $q.defer();
	        $http.post('analyzeNote/createNote', noteInputFormModel)
	            .then(
	            function (response) {
	                deferred.resolve(response.data);
	            },
	            function(errResponse){
	                console.error('Error while creating note');
	                deferred.reject(errResponse);
	            }
	        );
	        return deferred.promise;
	    }
	
	}]);


noteApp.controller('NavbarCtrl', function($scope, $auth) {
	$scope.isAuthenticated = function() {
		return $auth.isAuthenticated();
	};
});