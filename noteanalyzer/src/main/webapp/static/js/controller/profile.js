angular.module('NoteApp')
  .controller('ProfileCtrl', function($scope, $auth, toastr, UserService, $state,WaitingDialog) {
    $scope.getProfile = function() {
    	WaitingDialog.show();
    	UserService.getUserDetail()
        .then(function(response) {
          $scope.user = response;
          $auth.setUser(response);
          angular.element("#welcomeUserName").html(response.displayName);
        }).then(function(value) {
        	WaitingDialog.hide();
        })
        .catch(function(response) {
          toastr.error('We are unable to fetch your profile record. Please try after sometime.');
          $auth.checkLoginFromServer(response.status);
        });
    };
    $scope.updateProfile = function() {
    	WaitingDialog.show();
    	UserService.updateUser($scope.user)
        .then(function() {
          toastr.success('Profile has been updated');
        }).then(function(value) {
        	WaitingDialog.hide();
        })
        .catch(function(response) {
          toastr.error('We are unable to update your profile record. Please try after sometime.');
          $auth.checkLoginFromServer(response.status);
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
