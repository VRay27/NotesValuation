var app = angular.module('NoteApp');
app.controller('LoginCtrl', function($scope, $rootScope, $state, $location, toastr, loginService, loginModel, $stateParams, $auth,WaitingDialog,UserService) {
  $scope.login = function() {
	WaitingDialog.show();
    loginModel.username = $scope.user.email;
    loginModel.password = $auth.encodeString($scope.user.password);
    loginService.doLogin(loginModel).then(function(loginResponse) {
      $auth.saveToken(loginResponse.token);
      UserService.getUserDetail().then(function(userResponse){
    	  $auth.setUser(userResponse);
    	  angular.element("#welcomeUserName").html(userResponse.displayName);
    	  toastr.success('You have successfully sign in.');
      if ($stateParams.referer) {
        $state.go($stateParams.referer, {
          'loginState': $stateParams.loginState
        });
      } else {
        $location.path('/');
      }}).then(function(){
      	WaitingDialog.hide();
      });
    }, function(response) {
    	if(response.data && response.data.status==403){
    		toastr.error('Please verify your email.');
    	}else{
    		toastr.error('Please enter valid email and password.');	
    	}
      
      $auth.logout();
    }).then(function(){
      	WaitingDialog.hide();
    });
  };
  
 $scope.resetPassword = function(){
	 WaitingDialog.show();
	 loginModel.username = $scope.user.email;
	 loginService.resetPassword(loginModel).then(function(response) {
		 toastr.success('We have sent an email to your given email id with password reset instruction.');
		 $('#forgotPasswordModal').modal('hide');
	 },function(response){
		 if(response.status==404){
			 toastr.error('No user is registered with this email.');
		 }else{
		  toastr.error('We are unable to process your request. Please try after sometime.');
		 }
	 }).then(function(){
	    	WaitingDialog.hide();
	    });
 } 

});
app.service("loginService", function($http, $q, WaitingDialog) {
	
	
  var doLogin = function(loginModel) {
	  var deferred = $q.defer();
	  return $http.post('api/auth/login', loginModel)
      .then(function(response) {
    	  deferred.resolve(response.data);
    	  return deferred.promise;
      }, function(response) {
        deferred.reject(response);
        return deferred.promise;
      });
	 
  };
  
  var resetPassword = function(loginModel) {
	  var deferred = $q.defer();
	  return $http.post('resetPassword', loginModel)
	      .then(function(response) {
	        deferred.resolve(response.data);
	        return deferred.promise;
	      }, function(response) {
	        deferred.reject(response);
	        return deferred.promise;
	      });
	  
	  };

  return {
    doLogin: doLogin,
    resetPassword:resetPassword
  }
});