angular.module('NoteApp')
  .controller('SignupCtrl', function($scope, $state, $auth, toastr,UserService,$window,$location) {
    $scope.signup = function() {
    	var user = $scope.user;
    	user.password = $window.btoa($scope.user.password);
      UserService.createUser(user).then(function(response){
    	  $state.go('login');
          toastr.info('You have successfully created a new account. Please sign in using your Email and password');
      },function(response){
    	  if(response.status == 409){
    		  toastr.error('A User with email this already exist. Please use another email id.');
    	  }else{
    		  toastr.error('We are unable to create user. Please try after some time');
    	  }
      })
      
    };
    
    $scope.changePassword = function() {
    	var user = $scope.user;
      	user.password = $window.btoa($scope.user.password);
      	var obj = $location.search();
        UserService.changePassword(user,obj.resetToken).then(function(response){
      	  $state.go('login');
          toastr.info('You have successfully change your password. Please sign in using your Email and password');
        },function(response){
      		  toastr.error('We are unable to process your request. Please try after some time');
      	  });
    };
    
  });


