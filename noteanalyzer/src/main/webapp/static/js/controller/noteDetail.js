var noteApp = angular.module('NoteApp');
noteApp.controller('NoteDetailCtrl', function($scope, $stateParams, $state, $document, $auth, $http, toastr, $rootScope, noteUploadAPI, NoteService, UtilityService, $window, $filter, UserService) {
	$scope.noteInputFormModel = NoteService.getNoteDetailModel();
	if ($scope.noteInputFormModel && $scope.noteInputFormModel.noteId) {
		$window.localStorage.setItem('noteId', $scope.noteInputFormModel.noteId);
	} else {
		NoteService.getNoteDetail($window.localStorage.getItem('noteId')).then(function(response) {
			NoteService.setNoteDetailModel(response);
			$scope.noteInputFormModel = NoteService.getNoteDetailModel();
			$scope.convertNumberFilter();
			$scope.populateDefaultPropertyType();
		}, function(response) {
			$auth.checkLoginFromServer(response.status);
			toastr.error("We are unable to find details for this note. Please try after sometime.")
		});
	}

	$scope.isSubscribed = function() {
		var user = $auth.getUser();
		if ("P1" == user.subscriptionName) {
			return true;
		}
		return false;
	}

	$scope.propertyTypeChange = function() {
		if ($scope.noteInputFormModel) {
			var selectedPropertyType = $scope.noteInputFormModel.selPropType;
			if (selectedPropertyType == 'DUPLEX' || selectedPropertyType == 'FOURPLEX' || selectedPropertyType == 'APARTMENT' || selectedPropertyType == 'TRIPLEX') {
				angular.element(document.querySelector('#numberOfUnits')).removeAttr('disabled');
			} else {
				angular.element(document.querySelector('#numberOfUnits')).attr('disabled', 'disabled');
				angular.element( document.querySelector('#numberOfUnits')).val('');
			}

			if (selectedPropertyType == 'TH' || selectedPropertyType == 'CONDO') {
				angular.element(document.querySelector('#hoaFees')).removeAttr('disabled');
			} else {
				angular.element(document.querySelector('#hoaFees')).attr('disabled', 'disabled');
				angular.element( document.querySelector('#hoaFees')).val('');
			}
		}
	}

	$scope.populateDefaultPropertyType = function() {
		if ($scope.noteInputFormModel.propTypeList) {
			$scope.noteInputFormModel.selPropType = $scope.noteInputFormModel.selPropType || $scope.noteInputFormModel.propTypeList[0].propertyTypeCode;
			$scope.propertyTypeChange();
		}
	}

	$scope.sanitizeNoteInputModelFromJS = function() {
		$scope.noteInputFormModel.rate = $filter('sanitizeInput')($scope.noteInputFormModel.rate);
		$scope.noteInputFormModel.upb = $filter('sanitizeInput')($scope.noteInputFormModel.upb);
		$scope.noteInputFormModel.pdiPayment = $filter('sanitizeInput')($scope.noteInputFormModel.pdiPayment);
		$scope.noteInputFormModel.tdiPayment = $filter('sanitizeInput')($scope.noteInputFormModel.tdiPayment);
		$scope.noteInputFormModel.originalPrincipleBalance = $filter('sanitizeInput')($scope.noteInputFormModel.originalPrincipleBalance);
		$scope.noteInputFormModel.notePrice = $filter('sanitizeInput')($scope.noteInputFormModel.notePrice);
		$scope.noteInputFormModel.originalPropertyValue = $filter('sanitizeInput')($scope.noteInputFormModel.originalPropertyValue);
		$scope.noteInputFormModel.remainingPayment = $filter('sanitizeInput')($scope.noteInputFormModel.remainingPayment);
		$scope.noteInputFormModel.estimatedMarketValue = $filter('sanitizeInput')($scope.noteInputFormModel.estimatedMarketValue);
		$scope.noteInputFormModel.propertyDetailModel.marketValue = $filter('sanitizeInput')($scope.noteInputFormModel.propertyDetailModel.marketValue);

	}

	$scope.convertNumberFilter = function() {
		if($scope.noteInputFormModel){
		$scope.noteInputFormModel.upb = $filter('number')($scope.noteInputFormModel.upb);
		$scope.noteInputFormModel.pdiPayment = $filter('number')($scope.noteInputFormModel.pdiPayment);
		$scope.noteInputFormModel.tdiPayment = $filter('number')($scope.noteInputFormModel.tdiPayment);
		$scope.noteInputFormModel.originalPrincipleBalance = $filter('number')($scope.noteInputFormModel.originalPrincipleBalance);
		$scope.noteInputFormModel.notePrice = $filter('number')($scope.noteInputFormModel.notePrice);
		$scope.noteInputFormModel.originalPropertyValue = $filter('number')($scope.noteInputFormModel.originalPropertyValue);
		$scope.noteInputFormModel.remainingPayment = $filter('number')($scope.noteInputFormModel.remainingPayment);
		$scope.noteInputFormModel.estimatedMarketValue = $filter('number')($scope.noteInputFormModel.estimatedMarketValue);
		if (!isNaN($scope.noteInputFormModel.propertyDetailModel.marketValue) && angular.isNumber(+$scope.noteInputFormModel.propertyDetailModel.marketValue)) {
			$scope.noteInputFormModel.propertyDetailModel.marketValue = $filter('number')($scope.noteInputFormModel.propertyDetailModel.marketValue);
		}
		}
	}
	
	$scope.convertNumberFilter();

	$scope.populateNoteInputModelFromJS = function() {
		angular.element(document.querySelector('.modifiedField')).trigger('change');
		angular.element(document.querySelector('#originalTermId')).removeClass('noteInputCalculatedField');
		angular.element(document.querySelector('#orginalLoanBalanceId')).removeClass('noteInputCalculatedField');
		angular.element(document.querySelector('#interestRateId')).removeClass('noteInputCalculatedField');
		angular.element(document.querySelector('#paymentId')).removeClass('noteInputCalculatedField');
		var model = $scope.noteInputFormModel;
		var elem = NoteService.noteCalculator(model);
		$scope.noteInputFormModel = model;
		if (elem == 'rate') {
			angular.element(document.querySelector('#interestRateId')).addClass('noteInputCalculatedField');
		} else if (elem == 'pdiPayment') {
			angular.element(document.querySelector('#paymentId')).addClass('noteInputCalculatedField');
		} else if (elem == 'originalTerm') {
			angular.element(document.querySelector('#originalTermId')).addClass('noteInputCalculatedField');
		} else if (elem == 'originalPrincipleBalance') {
			angular.element(document.querySelector('#orginalLoanBalanceId')).addClass('noteInputCalculatedField');
		}
	};

	$scope.cancel = function() {
		$scope.noteInputFormModel = {};
		$state.go('noteDashboard');
	}

	$scope.dateOptions = {
		formatYear : 'yy',
		maxDate : new Date(2100, 12, 31),
		minDate : new Date(1800, 12, 31),
		startingDay : 1
	};

	$scope.noteDate = {
		opened : false
	};
	$scope.noteDate = function() {
		$scope.noteDate.opened = true;
	};

	$scope.lastPaymentRecievedDate = {
		opened : false
	};
	$scope.lastPaymentRecievedDate = function() {
		$scope.lastPaymentRecievedDate.opened = true;
	};

	$scope.altInputFormats = ['MM/dd/yyyy'];

	$scope.updateOrginalTerm = function() {
		if ($scope.noteInputFormModel && $scope.noteInputFormModel.loanTypeList) {
			var len = $scope.noteInputFormModel.loanTypeList.length;
			for (var i = 0; i < len; i++) {
				var loanTypeCode = $scope.noteInputFormModel.loanTypeList[i].loanTypeCode;
				if (loanTypeCode == $scope.noteInputFormModel.selLoanType) {
					$scope.noteInputFormModel.originalTerm = $scope.noteInputFormModel.loanTypeList[i].termMonths;
					break;
				}
			}
		}
	}

	$scope.updateNote = function() {
		$scope.sanitizeNoteInputModelFromJS();
		$scope.noteInputFormModel.noteDate = $filter('date')($scope.noteInputFormModel.noteDate, 'MM/dd/yyyy');
		$scope.noteInputFormModel.lastPaymentRecievedDate = $filter('date')($scope.noteInputFormModel.lastPaymentRecievedDate, 'MM/dd/yyyy');
		NoteService.getYield($scope.noteInputFormModel);
		NoteService.updateNote($scope.noteInputFormModel).then(function(response) {
			$scope.noteInputFormModel = response;
			$scope.convertNumberFilter();
			toastr.success("Note has been save successfully.")
		}, function(response) {
			toastr.error("We are unable to update note. Please try after sometime.")
		});

	}

	$scope.updateMarketValue = function(grid, row) {
		var noteDetailModel = {
			noteId : $scope.noteInputFormModel.noteId
		}
		NoteService.updateMarketValue(noteDetailModel).then(function(response) {
			$scope.noteInputFormModel = response;
			$scope.convertNumberFilter();
			toastr.success("Note has been updated successfully.")
		}, function(response) {
			$auth.checkLoginFromServer(response.status);
			toastr.error("We are unable to find details for this note. Please try after sometime.")
		})
	}

	$scope.subscribeNote = function() {
		$scope.sanitizeNoteInputModelFromJS();
		$scope.noteInputFormModel.noteDate = $filter('date')($scope.noteInputFormModel.noteDate, 'MM/dd/yyyy');
		$scope.noteInputFormModel.lastPaymentRecievedDate = $filter('date')($scope.noteInputFormModel.lastPaymentRecievedDate, 'MM/dd/yyyy');
		NoteService.getYield($scope.noteInputFormModel);
		UserService.updateSubscription().then(function(response) {
			$auth.setUser(response);
			NoteService.subscribeNote($scope.noteInputFormModel).then(function(response) {
				$scope.noteInputFormModel = response;
				$scope.convertNumberFilter();
				toastr.success("Note has been updated successfully.")
			}, function(response) {
				toastr.error("We are unable to update note. Please try after sometime.")
			})
		}, function(response) {
			toastr.error("We are unable to update user subscripton. Please try after sometime.")
		});

	}


	$scope.deleteNote = function() {
		NoteService.deleteNote($scope.noteInputFormModel).then(function(response) {
			$state.go('noteDashboard');
			toastr.success("Note has been deleted successfully.")
		}, function(response) {
			toastr.error("We are unable to delete this note. Please try after sometime.")
		});

	}
});