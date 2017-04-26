angular.module('NoteApp')
  .controller('LogoutCtrl', function($location, $auth, toastr) {
    if (!$auth.isAuthenticated()) {
      return;
    }
    $auth.logout();
    toastr.info('You have been successfully logged out');
    $location.path('/');

  });