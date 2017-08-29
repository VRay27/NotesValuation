var noteApp = angular.module('NoteApp');
noteApp.controller('NoteDetailCtrl', function($scope, $stateParams, $state,$document, $auth, $http, toastr, $rootScope, noteUploadAPI, NoteService,UtilityService,$window) {
 $scope.noteInputFormModel = NoteService.getNoteDetailModel();
 if($scope.noteInputFormModel && $scope.noteInputFormModel.noteId){
	 $window.localStorage.setItem('noteId', $scope.noteInputFormModel.noteId); 
 }else{
     NoteService.getNoteDetail($window.localStorage.getItem('noteId')).then(function(response) {
         NoteService.setNoteDetailModel(response);
         $scope.noteInputFormModel = NoteService.getNoteDetailModel();
        // $scope.noteInputFormModel.selNoteType='R';
     }, function(response) {
         $auth.checkLoginFromServer(response.status);
         toastr.error("We are unable to find details for this note. Please try after sometime.")
     });
 }
 if($scope.noteInputFormModel){
	 $scope.noteInputFormModel.selNoteType='R';
 }
  $scope.cancel = function(){
	  $state.go('noteDashboard');
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
  
  
  $scope.updateNote = function(){
	  NoteService.updateNote($scope.noteInputFormModel).then(function(response) {
		  $scope.noteInputFormModel = response;
		  angular.element( document.querySelector('#selNoteDate')).val($scope.noteInputFormModel.noteDate);
		  angular.element( document.querySelector('#lastPaymentRecievedDate')).val($scope.noteInputFormModel.lastPaymentRecievedDate);
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