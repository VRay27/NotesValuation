var app = angular.module('NoteApp');
app.controller('LoginCtrl', function($scope, $rootScope, $state, $location, toastr, loginService, loginModel, $stateParams, $auth,$window) {
  $scope.login = function() {
    loginModel.username = $scope.user.email;
    loginModel.password = $auth.encodeString($scope.user.password);
    loginService.doLogin(loginModel).then(function(response) {
      $auth.saveToken(response.token)
      toastr.success('You have successfully signed in with give n user name');
      if ($stateParams.referer) {
        $state.go($stateParams.referer, {
          'loginState': $stateParams.loginState
        });
      } else {
        $location.path('/');
      }
    }, function(response) {
      toastr.error('Please enter valid EmailId and password.');
      $auth.logout();
    });
  };
  
 $scope.resetPassword = function(){
	 loginModel.username = $scope.user.email;
	 loginService.resetPassword(loginModel).then(function(response) {
		 toastr.success('We have sent an email to your given email id with password reset instruction.');
	 },function(response){
		 if(response.status==404){
			 toastr.error('No user is registered with this email.');
		 }else{
		  toastr.error('We are unable to process your request. Please try after sometime.');
		 }
	 });
 } 

});
app.service("loginService", function($http, $q) {

  var deferred = $q.defer();

  var doLogin = function(loginModel) {
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