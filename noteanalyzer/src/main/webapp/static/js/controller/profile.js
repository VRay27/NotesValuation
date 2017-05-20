angular.module('NoteApp')
  .controller('ProfileCtrl', function($scope, $auth, toastr, UserService, $state) {
    $scope.getProfile = function() {
    	UserService.getUserDetail($auth.getUserId())
        .then(function(response) {
          $scope.user = response.data;
        })
        .catch(function(response) {
          toastr.error('We are unable to fetch your profile record. Please try after sometime.');
        });
    };
    $scope.updateProfile = function() {
    	UserService.updateUser($scope.user)
        .then(function() {
          toastr.success('Profile has been updated');
        })
        .catch(function(response) {
          toastr.error('We are unable to update your profile record. Please try after sometime.');
        });
    };

    $scope.getProfile();
  });
