var noteApp = angular.module('NoteApp');
noteApp.controller('NoteDetailCtrl', function($scope, $stateParams, $state,$document, $auth, $http, toastr, $rootScope, noteUploadAPI, NoteService,UtilityService) {
  $scope.noteDetailModel = NoteService.getNoteDetailModel();
  $scope.cancel = function(){
	  $state.go('noteDashboard');
  }
  $scope.updateNote = function(){
	  NoteService.updateNote($scope.noteDetailModel).then(function(response) {
		  $scope.noteDetailModel = response;
		  toastr.success("Note has been updated successfully.")
	  },function(response) {
		  toastr.error("We are unable to update note. Please try after sometime.")
	  });
	    
  }
  
  $scope.deleteNote = function(){
	  NoteService.deleteNote($scope.noteDetailModel).then(function(response) {
		  $state.go('noteDashboard');
		  toastr.success("Note has been deleted successfully.")
	  },function(response) {
		  toastr.error("We are unable to delete this note. Please try after sometime.")
	  });
	    
  }
});