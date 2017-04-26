var app = angular.module('NoteApp');
app.controller('LoginCtrl', function($scope, $rootScope, $state, $location, toastr, loginService, loginModel, $stateParams, $auth) {
  $scope.login = function() {
    loginModel.username = $scope.user.email;
    loginModel.password = $scope.user.password;
    loginService.doLogin(loginModel).then(function(response) {
      $auth.saveToken(response.token)
      //localStorage.setItem('token', response.token);
      toastr.success('You have successfully signed in with give n user name');
      if ($stateParams.referer) {
        $state.go($stateParams.referer, {
          'loginState': $stateParams.loginState
        });
      } else {
        $location.path('/');
      }
    }, function(response) {
      toastr.error(response.message);
      $auth.logout();
      //localStorage.removeItem('token');
      $location.path('/');
    });
  };

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

  return {
    doLogin: doLogin
  }
});