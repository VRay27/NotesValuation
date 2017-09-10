angular.module('NoteApp')
	.controller('ProfileCtrl', function($scope, $auth, toastr, UserService, $state, WaitingDialog) {
		$scope.getProfile = function() {
			WaitingDialog.show();
			UserService.getUserDetail()
				.then(function(response) {
					$scope.userModel = response;
					$auth.setUser(response);
					angular.element("#welcomeUserName").html(response.displayName);
				}).then(function(value) {
				WaitingDialog.hide();
			})
				.catch(function(response) {
					toastr.error('We are unable to fetch your profile record. Please try after sometime.');
					$auth.checkLoginFromServer(response.status);
				});
		};
		$scope.updateProfile = function() {
			var oldUserData = $scope.userModel;
			WaitingDialog.show();
			UserService.updateUser($scope.userModel)
				.then(function(response) {
					$scope.userModel = response;
					$scope.userModel.addressModel = oldUserData.addressModel;
					$auth.setUser(response);
					angular.element("#welcomeUserName").html(response.displayName);
					toastr.success('Profile has been updated');
				}, function(response) {
					$scope.userModel = oldUserData;
				}).then(function(value) {
				WaitingDialog.hide();
			})
				.catch(function(response) {
					toastr.error('We are unable to update your profile record. Please try after sometime.');
					$auth.checkLoginFromServer(response.status);
				});
		};

		$scope.getProfile();

		$scope.changePassword = function() {
			$state.go('changePassword');
		}
		$scope.populateCityState = function() {
			if ($scope.userModel.zipCode) {
				UserService.getCityStateFromZipCode($scope.userModel.zipCode)
					.then(function(response) {
						$scope.userModel.addressModel = response;
					}, function(response) {
						toastr.error('We are unable to fetch the details for this zipcode');
						$scope.userModel.addressModel= {};
					});
			}
		}


	});


angular.module('NoteApp')
	.controller('SubscriptionCtrl', function($scope, $auth, toastr, UserService, $state) {});