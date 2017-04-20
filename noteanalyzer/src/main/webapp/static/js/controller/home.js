angular.module('NoteApp')
  .controller('HomeCtrl', function($scope, $http,$uibModal,toastr) {
   $scope.items = ['item1', 'item2', 'item3'];
   $scope.noteAnalyzed = function (modalName) {
       var modalInstance = $uibModal.open({
           templateUrl: 'static/template/note-form.html',
           controller: 'noteInutFormController',
           resolve: {
               'items': function() { return $scope.items; }
           }
       });
       console.log('modal opened');
       modalInstance.result.then(function (response) {
           $scope.selected = response;
           console.log(response);
       }, function () {
           console.log('Modal dismissed at: ' + new Date());
       });
   };
   

   // Global handler for onSuccess that adds the uploaded files to the list
   $scope.onFileUploadSuccess = function (response) {
     console.log('AppCtrl.onSuccess', response);
     $scope.responseData = response.data;
     $scope.uploads = $scope.uploads.concat(response.data.files);
   };

   // Global handler for onSuccess that adds the uploaded files to the list
   $scope.onFileUploadFailure = function (response) {
     console.log('AppCtrl.Failure', response);
     toastr.error('Unable to upload file');
   };

  });

angular.module('NoteApp').controller('noteInutFormController', function noteInutFormController ($scope,$state, $uibModalInstance, items,noteInputFormModel) {
  $scope.items = items;
  $scope.noteInputFormModel = noteInputFormModel;
  $scope.selected = {
      item: $scope.items[0]
  };
  $scope.save = function () {
      $uibModalInstance.close($scope.selected.item);
      console.log('ok');
      $state.go('noteDashboard');
  };
  $scope.cancel = function () {
      $uibModalInstance.dismiss('cancel');
      console.log('cancel');
  };
});