angular.module('NoteApp')
  .controller('SignupCtrl', function($scope, $location, $auth, toastr) {
    $scope.signup = function() {
      ///call a service for sign up
      //localStorage.setItem('token', 'asasd12e1');
      $auth.saveToken('needtobe removed');
      $location.path('/');
      toastr.info('You have successfully created a new account and have been signed-in');

    };
  });