angular.module('NoteApp')
  .controller('SignupCtrl', function($scope, $state, $auth, toastr,UserService,$window,$location) {
    $scope.signup = function() {
    	var user = {};
    	user.password = $auth.encodeString($scope.user.password);
    	user.displayName = $scope.user.displayName;
    	user.email = $scope.user.email;
    	
      UserService.createUser(user).then(function(response){
    	  $state.go('home');
          toastr.info('We have sent you a verification mail, please verify your email and then sign in using your Email and password');
      },function(response){
    	  if(response.status == 302){
    		  toastr.error('A User with this email id is already exist. Please use another email id for signup.');
    	  }else if(response.status == 409){
    		  toastr.error('A User with this email id is register but not yet verified, Please check your inbox and click on verification link.');
    	  }else{
    		  toastr.error('We are unable to create user. Please try after some time');
    	  }
      })
      
    };
    
    $scope.changePassword = function() {
    	var user = {};
    	user.password = $auth.encodeString($scope.user.password);
    	var obj = $location.search();
    	if(!obj.resetToken){
    		 UserService.changePasswordWithLoginUser(user).then(function(response){
    	      	  $state.go('login');
    	          toastr.info('You have successfully change your password. Please sign in using your email and password');
    	        },function(response){
    	      		  toastr.error('We are unable to process your request. Please try after some time');
    	      	  });
    	}else{
        UserService.changePassword(user,obj.resetToken).then(function(response){
      	  $state.go('login');
          toastr.info('You have successfully change your password. Please sign in using your email and password');
        },function(response){
        	  if(response.status == 400){
        		  toastr.error('Invalid reset token');
        	  }else if(response.status == 302){
        		  toastr.error('Reset token already been used. Please try resetting password again.');
        	  }else if(response.status == 404){
        		  toastr.error('No user is associated with this token.');
        	  }else{
        		  toastr.error('We are unable to reset password. Please try after some time');
        	  }
      	  });
    	};
    };
    
  });


