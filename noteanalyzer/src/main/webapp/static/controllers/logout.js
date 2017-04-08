angular.module('MyApp')
  .controller('LogoutCtrl', function($location, $auth, toastr) {
    if (!$auth.isAuthenticated()) { return; }
    	localStorage.removeItem('token');
        toastr.info('You have been logged out');
        $location.path('/');
      
  });