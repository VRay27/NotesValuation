var noteApp = angular.module('NoteApp');
noteApp.controller('NoteDetailCtrl', function($scope, $stateParams, $state,$document, $auth, $http, toastr, $rootScope, noteUploadAPI, NoteService,UtilityService,$window) {
 $scope.noteInputFormModel = NoteService.getNoteDetailModel();
 if($scope.noteInputFormModel && $scope.noteInputFormModel.noteId){
	 $window.localStorage.setItem('noteId', $scope.noteInputFormModel.noteId); 
 }else{
     NoteService.getNoteDetail($window.localStorage.getItem('noteId')).then(function(response) {
         NoteService.setNoteDetailModel(response);
         $scope.noteInputFormModel = NoteService.getNoteDetailModel();
     }, function(response) {
         $auth.checkLoginFromServer(response.status);
         toastr.error("We are unable to find details for this note. Please try after sometime.")
     });
 }
 
  $scope.cancel = function(){
	  $state.go('noteDashboard');
  }
  $scope.updateNote = function(){
	  NoteService.updateNote($scope.noteInputFormModel).then(function(response) {
		  $scope.noteInputFormModel = response;
		  toastr.success("Note has been updated successfully.")
	  },function(response) {
		  toastr.error("We are unable to update note. Please try after sometime.")
	  });
	    
  }
 
  $scope.subscribeNote = function(){
	  $scope.noteInputFormModel.isSubscribe = true;
	  NoteService.subscribeNote($scope.noteInputFormModel).then(function(response) {
		  $scope.noteInputFormModel = response;
		  toastr.success("Note has been updated successfully.")
	  },function(response) {
		  toastr.error("We are unable to update note. Please try after sometime.")
	  });
	    
  }
  
  
  $scope.deleteNote = function(){
	  NoteService.deleteNote($scope.noteInputFormModel).then(function(response) {
		  $state.go('noteDashboard');
		  toastr.success("Note has been deleted successfully.")
	  },function(response) {
		  toastr.error("We are unable to delete this note. Please try after sometime.")
	  });
	    
  }
});