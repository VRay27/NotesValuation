angular.module('NoteApp')
  .controller('LogoutCtrl', function($location, $auth, toastr,UtilityService,UserService) {
	  UtilityService.setActiveHeader('home');
    if (!$auth.isAuthenticated()) {
      return;
    }
    $auth.logout();
    UserService.logout().then(function(response) {
    	toastr.success('You have been successfully logged out');
    	$location.path('/');
	},function(errResponse) {
		$location.path('/');
	});
  });