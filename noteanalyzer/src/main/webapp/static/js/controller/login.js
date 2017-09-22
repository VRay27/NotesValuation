var app = angular.module('NoteApp');
app.controller('LoginCtrl', function($scope, $rootScope, $state, $location, toastr, loginService, loginModel, $stateParams, $auth,WaitingDialog,UserService,$window,UtilityService) {
	UtilityService.setActiveHeader('login');
	$scope.login = function() {
	WaitingDialog.show();
    loginModel.username = $scope.user.email;
    loginModel.password = $auth.encodeString($scope.user.password);
    loginService.doLogin(loginModel).then(function(loginResponse) {
      $auth.saveToken(loginResponse.token);
      UserService.getUserDetail().then(function(userResponse){
    	  $auth.setUser(userResponse);
    	  $rootScope.loggedInUserDisplayName = userResponse.displayName;
    	  angular.element("#welcomeUserName").html(userResponse.displayName);
    	  toastr.success('You have successfully sign in.');
      if ($stateParams.referer) {
        $state.go($stateParams.referer, {
          'loginState': $stateParams.loginState
        });
      } else {
    	  var host = $window.location.host;
          var landingUrl = "http://" + host + "/notes/#!/";
          $window.location.href = landingUrl;
      }}).then(function(){
      	WaitingDialog.hide();
      });
    }, function(response) {
    	if(response.data && response.data.status==403 && response.data.message=='UnverifiedUser'){
    		toastr.error('Please verify your email');
    	}else if (response.data && response.data.status==403 && response.data.message=='BlockedUser'){
    		toastr.error('You have been blocked. Please reset your password.');
    	}else{
    		toastr.error('Please enter your valid email id and password.');	
    	}
      WaitingDialog.hide();
      $auth.logout();
    })
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
			}).then(
			function() {
				WaitingDialog.hide();
				return deferred.promise;
			});

	};

	return {
		doLogin : doLogin,
		resetPassword : resetPassword
	}
});