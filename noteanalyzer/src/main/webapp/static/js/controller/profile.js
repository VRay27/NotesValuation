angular.module('NoteApp')
  .controller('ProfileCtrl', function($scope, $auth, toastr, Account, $state) {
    $scope.getProfile = function() {
      Account.getProfile()
        .then(function(response) {
          $scope.user = response.data;
        })
        .catch(function(response) {
          toastr.error(response.data.message, response.status);
          $state.go('login');
        });
    };
    $scope.updateProfile = function() {
      Account.updateProfile($scope.user)
        .then(function() {
          toastr.success('Profile has been updated');
        })
        .catch(function(response) {
          toastr.error(response.data.message, response.status);
        });
    };

    $scope.getProfile();
  });

angular.module('NoteApp')
.factory('Account', function($http) {
  return {
    getProfile: function() {
      return $http.get('api/me');
    },
    updateProfile: function(profileData) {
      return $http.put('api/me', profileData);
    }
  };
});