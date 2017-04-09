angular.module('MyApp')
  .controller('SignupCtrl', function($scope, $location, $auth, toastr) {
    $scope.signup = function() {
     ///call a service for sign up
      localStorage.setItem('token','asasd12e1');
          $location.path('/');
          toastr.info('You have successfully created a new account and have been signed-in');
       
    };
  }).constant("userDetailsModel",{userName:"",emailId:"",password:"",contactNumber:""});