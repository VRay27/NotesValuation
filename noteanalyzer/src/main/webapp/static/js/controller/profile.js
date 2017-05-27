angular.module('NoteApp')
  .controller('ProfileCtrl', function($scope, $auth, toastr, UserService, $state) {
    $scope.getProfile = function() {
    	UserService.getUserDetail()
        .then(function(response) {
          $scope.user = response;
        })
        .catch(function(response) {
          toastr.error('We are unable to fetch your profile record. Please try after sometime.');
          $auth.checkLoginFromServer(response.status);
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
    
    $scope.changePassword = function(){
    	$state.go('changePassword');
    }
  });


angular.module('NoteApp')
.controller('SubscriptionCtrl', function($scope, $auth, toastr, UserService, $state) {
	
	
});
