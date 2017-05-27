'use strict';


noteApp.factory('NoteService', ['$http', 'toastr', '$q', '$rootScope', '$uibModal', function($http, toastr, $q, $rootScope, $uibModal) {
	var factory = {
		createNote : createNote,
		noteAnalyze : noteAnalyze,
		getNoteDetail : getNoteDetail,
		deleteNote : deleteNote,
		editNote : editNote,
		uploadNoteFile : uploadNoteFile
	};

	return factory;

	function createNote(noteInputFormModel) {
		var deferred = $q.defer();
		$http.post('api/createNote', noteInputFormModel)
			.then(
				function(response) {
					deferred.resolve(response.data);
				},
				function(errResponse) {
					console.error('Error while creating note');
					deferred.reject(errResponse);
				}
		);
		return deferred.promise;
	}
	
	function editNote(noteDetailModel) {
		var deferred = $q.defer();
		$http.post('api/editNote', noteDetailModel)
			.then(
				function(response) {
					deferred.resolve(response.data);
				},
				function(errResponse) {
					console.error('Error while edit note');
					deferred.reject(errResponse);
				}
		);
		return deferred.promise;
	}
	
	function deleteNote(noteId) {
		var deferred = $q.defer();
		$http.delete('api/deleteNote/'+noteId)
			.then(
				function(response) {
					deferred.resolve(response.data);
				},
				function(errResponse) {
					console.error('Error while delete note '+noteId);
					deferred.reject(errResponse);
				}
		);
		return deferred.promise;
	}
	
	function getNoteDetail(noteId) {
		var deferred = $q.defer();
		$http.get('api/getNoteDetail/'+noteId)
			.then(
				function(response) {
					deferred.resolve(response.data);
				},
				function(errResponse) {
					console.error('Error while fetching note '+noteId);
					deferred.reject(errResponse);
				}
		);
		return deferred.promise;
	}
	
	function noteAnalyze(zipCode) {
		$http.get('analyzeNote/' + zipCode).then(function(response) {

			var noteInputFormModel = response.data;
			var modalInstance = $uibModal.open({
				templateUrl : 'static/template/note-form.html',
				controller : 'noteInputFormController',
				resolve : {
					'noteInputFormModel' : noteInputFormModel
				}
			});
			modalInstance.result.then(function(response) {
				$rootScope.submitInputFormModel = response;
			}, function() {
				console.log('Modal dismissed at: ' + new Date());
			});
		}, function(response) {
			toastr.error('Unable to process your request');
		});
	}
	
	function uploadNoteFile(file, uploadUrl) {
		var fd = new FormData();
		fd.append('file', file);

		$http.post(uploadUrl, fd, {
			transformRequest : angular.identity,
			headers : {
				'Content-Type' : undefined
			}
		}).then(function(response) {
			toastr.success('File has been uploaded Successfully!!');
		}, function(response) {
			toastr.error('Unable to upload file. Please try after sometime.');
		});
	}

}]);

