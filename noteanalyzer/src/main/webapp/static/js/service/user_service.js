'use strict';

angular.module('NoteApp').factory('UserService', ['$http', '$q', function($http, $q){


    var factory = {
        createUser: createUser,
        updateUser:updateUser,
        getUserDetail:getUserDetail
    };

    return factory;


    function createUser(user) {
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
        );
        return deferred.promise;
    }


    function updateUser(user) {
        var deferred = $q.defer();
        $http.put('updateUser', user)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while updating User');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }

    function getUserDetail(id) {
        var deferred = $q.defer();
        $http.delete('userDetail/'+id)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while fetching User details');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }

}]);
