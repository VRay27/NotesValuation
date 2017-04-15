angular.module('NoteApp')
  .controller('HomeCtrl', function($scope, $http,$modal) {
   $scope.items = ['item1', 'item2', 'item3'];
   $scope.noteAnalyzed = function (modalName) {
       var modalInstance = $modal.open({
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
  });

angular.module('NoteApp').controller('noteInutFormController', function noteInutFormController ($scope,$state, $modalInstance, items,noteInputFormModel) {
  $scope.items = items;
  $scope.noteInputFormModel = noteInputFormModel;
  $scope.selected = {
      item: $scope.items[0]
  };
  $scope.save = function () {
      $modalInstance.close($scope.selected.item);
      console.log('ok');
      $state.go('noteDashboard');
  };
  $scope.cancel = function () {
      $modalInstance.dismiss('cancel');
      console.log('cancel');
  };
});