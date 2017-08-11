var noteApp = angular.module('NoteApp');
noteApp.controller('NoteDetailCtrl', function($scope, $stateParams, $state,$document, $auth, $http, $uibModal, toastr, $rootScope, noteUploadAPI, NoteService,UtilityService) {
  $scope.noteDetailModel = NoteService.getNoteDetailModel();
  $scope.cancel = function(){
	  $state.go('noteDashboard');
  }
  
});