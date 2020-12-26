'use strict';

angular.module('NoteApp').factory('UserService', ['$http', '$q','WaitingDialog', function($http, $q,WaitingDialog){


    var factory = {
        createUser: createUser,
        updateUser:updateUser,
        getUserDetail:getUserDetail,
        changePassword:changePassword,
        changePasswordWithLoginUser:changePasswordWithLoginUser,
        getCityStateFromZipCode:getCityStateFromZipCode,
        updateSubscription : updateSubscription,
        logout : logout
    };

    return factory;

    function logout() {
    	WaitingDialog.show();
        var deferred = $q.defer();
        $http.get('logout')
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                deferred.reject(errResponse);
            }
        ).then(
				function() {
					WaitingDialog.hide();
				});;
        return deferred.promise;
    }

    
    function getCityStateFromZipCode(zipCode) {
    	WaitingDialog.show();
        var deferred = $q.defer();
        $http.get('getStateCityList/'+zipCode)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                deferred.reject(errResponse);
            }
        ).then(
				function() {
					WaitingDialog.hide();
				});;
        return deferred.promise;
    }


    function createUser(user) {
    	WaitingDialog.show();
        var deferred = $q.defer();
        $http.post('createUser', user)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while creating User');
                deferred.reject(errResponse);
            }
        ).then(
				function() {
					WaitingDialog.hide();
				});;
        return deferred.promise;
    }

    function changePasswordWithLoginUser(user,resetToken) {
    	WaitingDialog.show();
        var deferred = $q.defer();
        $http.post('api/changePassword', user)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while changing password');
                deferred.reject(errResponse);
            }
        ).then(
				function() {
					WaitingDialog.hide();
				});
        return deferred.promise;
    }

    
    function changePassword(user,resetToken) {
    	WaitingDialog.show();
        var deferred = $q.defer();
        $http.post('changePassword?resetToken='+resetToken, user)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while changing password');
                deferred.reject(errResponse);
            }
        ).then(
				function() {
					WaitingDialog.hide();
				});
        return deferred.promise;
    }


    function updateUser(userModel) {
    	WaitingDialog.show();
        var deferred = $q.defer();
        $http.post('api/updateUser', userModel)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while updating User');
                deferred.reject(errResponse);
            }
        ).then(
				function() {
					WaitingDialog.hide();
				});
        return deferred.promise;
    }

    function updateSubscription() {
    	WaitingDialog.show();
        var deferred = $q.defer();
        $http.post('api/updateSubscription')
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while updating User SUbscription');
                deferred.reject(errResponse);
            }
        ).then(
				function() {
					WaitingDialog.hide();
				});
        return deferred.promise;
    }

    
    function getUserDetail() {
    	
        var deferred = $q.defer();
        $http.get('api/userDetail')
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while fetching User details');
                deferred.reject(errResponse);
            }
        ).then(
				function() {
					WaitingDialog.hide();
				});
        return deferred.promise;
    }

}]);
