angular.module('NoteApp')
  .controller('HomeCtrl', function($scope, $http,$uibModal,toastr,upload,$rootScope,noteDownloadTemplateAPI) {
   $scope.items = ['item1', 'item2', 'item3'];
   $rootScope.noteDownloadTemplateAPI = noteDownloadTemplateAPI;
   $scope.noteAnalyzed = function (modalName) {
       var modalInstance = $uibModal.open({
           templateUrl: 'static/template/note-form.html',
           controller: 'noteInputFormController',
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
   $rootScope.uploadNoteBatch = function(){
     upload({
       url: '/upload',
       method: 'POST',
       data: {
         anint: 123,
         aBlob: new Blob([1,2,3]), // Only works in newer browsers
         aFile: $scope.noteFile, // a jqLite type="file" element, upload() will extract all the files from the input and put them into the FormData object before sending.
       }
     }).then(
       function (response) {
         console.log(response.data); // will output whatever you choose to return from the server on a successful upload
       },
       function (response) {
           console.error('testingmiddle'); //  Will return if status code is above 200 and lower than 300, same as $http
       },function (response) {
         console.error('testinglast'); //  Will return if status code is above 200 and lower than 300, same as $http
     }
     );
   }

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

angular.module('NoteApp').controller('noteInputFormController', function ($scope,$state, $uibModalInstance,noteInputFormModel,$auth) {
 // $scope.items = items;
  $scope.noteInputFormModel = noteInputFormModel;
 /* $scope.selected = {
      item: $scope.items[0]
  };*/
  $scope.noteTypeList = [{key : "ABC_KEY", label : "ABC"},
  {key : "XYZ_KEY", label : "XYZ"},
  {key : "CUSTOM_KEY", label : "CUSTOM"}];
  
  $scope.save = function () {
    console.log('selected type of note: '+noteInputFormModel.typeOfNote.key);
      if($auth.isAuthenticated()){
      $uibModalInstance.close();
      console.log('ok');
      $state.go('noteDashboard');
      }else{
        $state.go('login');
        $uibModalInstance.close();
      }
  };
  $scope.cancel = function () {
      $uibModalInstance.dismiss('cancel');
      console.log('cancel');
  };
});
angular.module('NoteApp').constant('noteDownloadTemplateAPI','api/downloadNoteTemplate');
