angular.module('NoteApp')
  .controller('SignupCtrl', function($scope, $state, $auth, toastr,UserService,$window) {
    $scope.signup = function() {
    	$scope.user.password = $window.btoa($scope.user.password);
      UserService.createUser($scope.user).then(function(response){
    	  $state.go('login');
          toastr.info('You have successfully created a new account. Please sign in using your Email Id and password');
      },function(response){
    	  if(response.status == 409){
    		  toastr.error('A User with email this already exist. Please use another email id.');
    	  }else{
    		  toastr.error('We are unable to create user. Please try after some time');
    	  }
      })
      
    };
  });