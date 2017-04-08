angular.module('MyApp')
  .controller('LoginCtrl', function($scope, $location,  toastr,loginService) {
    $scope.login = function() {
     //call https in post to get accesstoken then put into localstoarge
     localStorage.setItem('token','asasd12e1');
     toastr.success('You have successfully signed in with give n user name');
          $location.path('/');
    };
    $scope.authenticate = function(provider) {
      $auth.authenticate(provider)
        .then(function() {
          toastr.success('You have successfully signed in with ' + provider + '!');
          $location.path('/');
        })
        .catch(function(error) {
          if (error.message) {
            // Satellizer promise reject error.
            toastr.error(error.message);
          } else if (error.data) {
            // HTTP response error from server
            toastr.error(error.data.message, error.status);
          } else {
            toastr.error(error);
          }
        });
    };
  }).service("loginService", function($http, $q) {

  var deferred = $q.defer();

  this.login = function() {
    //put the localhost:8080/notes/api/login here
    return $http.post('https://api.github.com/users/haroldrv')
      .then(function(response) {
        // promise is fulfilled
        deferred.resolve(response.data);
        return deferred.promise;
      }, function(response) {
        // the following line rejects the promise 
        deferred.reject(response);
        return deferred.promise;
      });
  };
});
