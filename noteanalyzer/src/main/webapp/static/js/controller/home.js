var noteApp = angular.module('NoteApp');
noteApp.controller('HomeCtrl', function($scope, $stateParams, $state, $auth, noteInputFormModel, $http, $uibModal, toastr, $rootScope, noteUploadAPI, fileUpload) {
 $scope.noteAnalyzed = function(modalName) {
  var modalInstance = $uibModal.open({
   templateUrl: 'static/template/note-form.html',
   controller: 'noteInputFormController',
  });
  modalInstance.result.then(function(response) {
   $scope.selected = response;
  }, function() {
   console.log('Modal dismissed at: ' + new Date());
  });
 };


 if ($stateParams.loginState === 'inputNoteForm') {
  console.log("login done");
  // $scope.noteAnalyzed(); hit the service to update the records and forward to dashboard
  console.log('noteInputFormModel' + noteInputFormModel);
  $state.go('noteDashboard');
 } else {
  noteInputFormModel = {};
 }

 $scope.uploadFile = function() {
  if ($auth.isAuthenticated()) {
   fileUpload.uploadFileToUrl($scope.myFile, noteUploadAPI);
  } else {
   $rootScope.noteUploadFile = $scope.myFile;
   $state.go('login', {
    'referer': 'home',
    'loginState': 'noteFileUpload'
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

noteApp.controller('noteInputFormController', function($scope, $state, $uibModalInstance, noteInputFormModel, $auth) {
 $scope.noteInputFormModel = noteInputFormModel;
 $scope.noteTypeList = [{
  key: "ABC_KEY",
  label: "ABC"
 }, {
  key: "XYZ_KEY",
  label: "XYZ"
 }, {
  key: "CUSTOM_KEY",
  label: "CUSTOM"
 }];

 $scope.save = function() {
  console.log('selected type of note: ' + noteInputFormModel.typeOfNote.key);
  if ($auth.isAuthenticated()) {
   $uibModalInstance.close();
   console.log('ok');
   $state.go('noteDashboard');
  } else {
   $uibModalInstance.dismiss('cancel');
   $state.go('login', {
    'referer': 'home',
    'loginState': 'inputNoteForm'
   });
  }
 };
 $scope.cancel = function() {
  $uibModalInstance.dismiss('cancel');
  console.log('cancel');
 };
});
noteApp.constant('noteUploadAPI', 'api/noteUpload');

noteApp.directive('fileModel', ['$parse', function($parse) {
 return {
  restrict: 'A',
  link: function(scope, element, attrs) {
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
   transformRequest: angular.identity,
   headers: {
    'Content-Type': undefined
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