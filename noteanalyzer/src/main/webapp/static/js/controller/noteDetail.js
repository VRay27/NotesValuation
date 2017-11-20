var noteApp = angular.module('NoteApp');
noteApp.controller('NoteDetailCtrl', function($scope, $stateParams, $state, $document, $auth, $http, toastr, $rootScope, noteUploadAPI, NoteService, UtilityService, $window, $filter, UserService) {
	$scope.noteInputFormModel = NoteService.getNoteDetailModel();
	
	$scope.convertNumberFilter = function() {
		if ($scope.noteInputFormModel) {
			$scope.noteInputFormModel.upb = $filter('number')($scope.noteInputFormModel.upb);
			$scope.noteInputFormModel.pdiPayment = $filter('number')($scope.noteInputFormModel.pdiPayment);
			$scope.noteInputFormModel.tdiPayment = $filter('number')($scope.noteInputFormModel.tdiPayment);
			$scope.noteInputFormModel.originalPrincipleBalance = $filter('number')($scope.noteInputFormModel.originalPrincipleBalance);
			$scope.noteInputFormModel.notePrice = $filter('number')($scope.noteInputFormModel.notePrice);
			$scope.noteInputFormModel.originalPropertyValue = $filter('number')($scope.noteInputFormModel.originalPropertyValue);
			$scope.noteInputFormModel.estimatedMarketValue = $filter('number')($scope.noteInputFormModel.estimatedMarketValue);
			if (!isNaN($scope.noteInputFormModel.hoaFees) && angular.isNumber(+$scope.noteInputFormModel.hoaFees) && $scope.noteInputFormModel.hoaFees != 0.0) {
				$scope.noteInputFormModel.hoaFees = $filter('number')($scope.noteInputFormModel.hoaFees);
			}else{
				$scope.noteInputFormModel.hoaFees='';
			}
			if (!isNaN($scope.noteInputFormModel.propertyDetailModel.marketValue) && angular.isNumber(+$scope.noteInputFormModel.propertyDetailModel.marketValue)) {
				$scope.noteInputFormModel.propertyDetailModel.marketValue = $filter('number')($scope.noteInputFormModel.propertyDetailModel.marketValue);
			}
			if (!isNaN($scope.noteInputFormModel.propertyDetailModel.lastSoldPrice) && angular.isNumber(+$scope.noteInputFormModel.propertyDetailModel.lastSoldPrice)) {
				$scope.noteInputFormModel.propertyDetailModel.lastSoldPrice = $filter('number')($scope.noteInputFormModel.propertyDetailModel.lastSoldPrice);
			}
		}
	}
	$scope.addCurrencyAndRateSymbol = function(){
		if($scope.noteInputFormModel){
			$scope.noteInputFormModel.rate = UtilityService.addSymbol($scope.noteInputFormModel.rate, '%');
			$scope.noteInputFormModel.upb = UtilityService.addSymbol($scope.noteInputFormModel.upb);
			$scope.noteInputFormModel.pdiPayment = UtilityService.addSymbol($scope.noteInputFormModel.pdiPayment);
			$scope.noteInputFormModel.originalPrincipleBalance = UtilityService.addSymbol($scope.noteInputFormModel.originalPrincipleBalance);
			$scope.noteInputFormModel.notePrice = UtilityService.addSymbol($scope.noteInputFormModel.notePrice);
			$scope.noteInputFormModel.estimatedMarketValue = UtilityService.addSymbol($scope.noteInputFormModel.estimatedMarketValue);
			$scope.noteInputFormModel.hoaFees = UtilityService.addSymbol($scope.noteInputFormModel.hoaFees);
			$scope.noteInputFormModel.tdiPayment = UtilityService.addSymbol($scope.noteInputFormModel.tdiPayment);
		}
	}
	
	if ($scope.noteInputFormModel && $scope.noteInputFormModel.noteId) {
		$window.localStorage.setItem('noteId', $scope.noteInputFormModel.noteId);
		$scope.convertNumberFilter();
		$scope.addCurrencyAndRateSymbol();
	} else {
		NoteService.getNoteDetail($window.localStorage.getItem('noteId')).then(function(response) {
			NoteService.setNoteDetailModel(response);
			$scope.noteInputFormModel = NoteService.getNoteDetailModel();
			$scope.convertNumberFilter();
			$scope.populateDefaultPropertyType();
			$scope.addCurrencyAndRateSymbol();
		}, function(response) {
			$auth.checkLoginFromServer(response.status);
			toastr.error("We are unable to find details for this note. Please try after sometime.")
		});
	}

	$scope.isSubscribed = function() {
		var user = $auth.getUser();
		if (user && "P1" == user.subscriptionName) {
			return true;
		}
		return false;
	}

	$scope.propertyTypeChange = function(){
	if($scope.noteInputFormModel && $scope.noteInputFormModel.selPropType){
		var selectedPropertyType = $scope.noteInputFormModel.selPropType;
		if( selectedPropertyType == 'DUPLEX' || selectedPropertyType == 'FOURPLEX' || selectedPropertyType == 'APARTMENT' || selectedPropertyType == 'TRIPLEX'){
			angular.element( document.querySelector('#numberOfUnits')).removeAttr('disabled');
			if( selectedPropertyType == 'DUPLEX'){
				angular.element( document.querySelector('#numberOfUnits')).val('2');
			}else if( selectedPropertyType == 'FOURPLEX'){
				angular.element( document.querySelector('#numberOfUnits')).val('4');
			}else if( selectedPropertyType == 'TRIPLEX'){
				angular.element( document.querySelector('#numberOfUnits')).val('3');
			}
			$scope.noteInputFormModel.hoaFees = '';
		}else{
			angular.element( document.querySelector('#numberOfUnits')).attr('disabled','disabled');
			angular.element( document.querySelector('#numberOfUnits')).val('');
			$scope.noteInputFormModel.noOfPropUnits='';
		}
		
		if( selectedPropertyType == 'TH' || selectedPropertyType == 'CONDO'){
			angular.element( document.querySelector('#hoaFees')).removeAttr('disabled');
			$scope.noteInputFormModel.noOfPropUnits='';
		}else{
			angular.element( document.querySelector('#hoaFees')).attr('disabled','disabled');
			angular.element( document.querySelector('#hoaFees')).val('');
			$scope.noteInputFormModel.hoaFees = '';
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
		$scope.noteInputFormModel.hoaFees = $filter('sanitizeInput')($scope.noteInputFormModel.hoaFees);
		$scope.noteInputFormModel.originalPrincipleBalance = $filter('sanitizeInput')($scope.noteInputFormModel.originalPrincipleBalance);
		$scope.noteInputFormModel.notePrice = $filter('sanitizeInput')($scope.noteInputFormModel.notePrice);
		$scope.noteInputFormModel.originalPropertyValue = $filter('sanitizeInput')($scope.noteInputFormModel.originalPropertyValue);
		$scope.noteInputFormModel.estimatedMarketValue = $filter('sanitizeInput')($scope.noteInputFormModel.estimatedMarketValue);
		$scope.noteInputFormModel.propertyDetailModel.marketValue = $filter('sanitizeInput')($scope.noteInputFormModel.propertyDetailModel.marketValue);
		$scope.noteInputFormModel.propertyDetailModel.lastSoldPrice = $filter('sanitizeInput')($scope.noteInputFormModel.propertyDetailModel.lastSoldPrice);
	}

	$scope.populateNoteInputModelFromJS = function(){
		angular.element( document.querySelector('.modifiedField')).trigger('change');
		angular.element( document.querySelector('#originalTermId')).removeClass('noteInputCalculatedField');
		angular.element( document.querySelector('#orginalLoanBalanceId')).removeClass('noteInputCalculatedField');
		angular.element( document.querySelector('#interestRateId')).removeClass('noteInputCalculatedField');
		//angular.element( document.querySelector('#paymentId')).removeClass('noteInputCalculatedField');
		$scope.noteInputFormModel.pdiPayment = '';
		var model = $scope.noteInputFormModel;
		var elem = NoteService.notePaymentCalculator(model);
		$scope.noteInputFormModel = model;
		if(elem == 'rate'){
			angular.element( document.querySelector('#interestRateId')).addClass('noteInputCalculatedField');
		}else if(elem == 'pdiPayment'){
			angular.element( document.querySelector('#paymentId')).addClass('noteInputCalculatedField');
			angular.element( document.querySelector('#pdiPayment')).attr('readonly','readonly');
		}else if(elem == 'originalTerm'){
			angular.element( document.querySelector('#originalTermId')).addClass('noteInputCalculatedField');
		}else if(elem == 'originalPrincipleBalance'){
			angular.element( document.querySelector('#orginalLoanBalanceId')).addClass('noteInputCalculatedField');
		}
		$scope.noteInputFormModel.pdiPayment = $filter('number')($scope.noteInputFormModel.pdiPayment);
		$scope.noteInputFormModel.pdiPayment = UtilityService.addSymbol($scope.noteInputFormModel.pdiPayment);
		$scope.populateUPBFromJS();
	};
	
	
	$scope.populateUPBFromJS = function(){
		angular.element( document.querySelector('#interestRateId')).removeClass('noteInputCalculatedField');
		//angular.element( document.querySelector('#paymentId')).removeClass('noteInputCalculatedField');
		angular.element( document.querySelector('#unpaidBalanceId')).removeClass('noteInputCalculatedField');
		angular.element( document.querySelector('#remainingNoOfPaymentId')).removeClass('noteInputCalculatedField');
			var sampleNoteModel = {
					  pdiPayment:$scope.noteInputFormModel.pdiPayment,
					originalTerm:$scope.noteInputFormModel.remainingPayment,
						  	rate:$scope.noteInputFormModel.rate
			}
			NoteService.noteOriginalBalCalculator(sampleNoteModel);
			var unPaidBal = sampleNoteModel.originalPrincipleBalance;
			if(unPaidBal){
				$scope.noteInputFormModel.upb = sampleNoteModel.originalPrincipleBalance;
				angular.element( document.querySelector('#unpaidBalanceId')).addClass('noteInputCalculatedField');	
			}
			$scope.noteInputFormModel.upb = $filter('number')($scope.noteInputFormModel.upb);
			$scope.noteInputFormModel.upb = UtilityService.addSymbol($scope.noteInputFormModel.upb);
	}

	
	$scope.populateRemainingNumberOfPayment = function(){
		if($scope.noteInputFormModel && $scope.noteInputFormModel.noteDate &&$scope.noteInputFormModel.noteDate.length==10 
				&& $scope.noteInputFormModel.lastPaymentRecievedDate && $scope.noteInputFormModel.lastPaymentRecievedDate.length==10){
			$scope.noteInputFormModel.noteDate = $filter('date')($scope.noteInputFormModel.noteDate, 'MM/dd/yyyy');
			$scope.noteInputFormModel.lastPaymentRecievedDate = $filter('date')($scope.noteInputFormModel.lastPaymentRecievedDate, 'MM/dd/yyyy');
			var numberOfMonth =  UtilityService.getNumberOfMonth(new Date($scope.noteInputFormModel.noteDate), new Date($scope.noteInputFormModel.lastPaymentRecievedDate));
			if($scope.noteInputFormModel.originalTerm && $scope.noteInputFormModel.noteDate && $scope.noteInputFormModel.lastPaymentRecievedDate){
				if(numberOfMonth>0){
					$scope.noteInputFormModel.remainingPayment = $scope.noteInputFormModel.originalTerm - numberOfMonth;	
				}else{
					$scope.noteInputFormModel.remainingPayment = $scope.noteInputFormModel.originalTerm;
				}
				
				$scope.populateUPBFromJS();
			}
		}
	}

	$scope.cancel = function() {
		$scope.noteInputFormModel = {};
		$state.go('noteDashboard');
	}


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
	
	$scope.checkForNonPerformingNote  = function(){
		if($scope.noteInputFormModel){
			if($scope.noteInputFormModel.selNoteType  == 'N'){
				$scope.noteInputFormModel.roi ='';
				$scope.noteInputFormModel.yieldValue ='';
			}
		}
	}

	$scope.saveNote = function() {
		$scope.sanitizeNoteInputModelFromJS();
		$scope.noteInputFormModel.noteDate = $filter('date')($scope.noteInputFormModel.noteDate, 'MM/dd/yyyy');
		$scope.noteInputFormModel.lastPaymentRecievedDate = $filter('date')($scope.noteInputFormModel.lastPaymentRecievedDate, 'MM/dd/yyyy');
		NoteService.getYield($scope.noteInputFormModel);
		$scope.checkForNonPerformingNote();
		NoteService.updateNote($scope.noteInputFormModel).then(function(response) {
			$scope.noteInputFormModel = response;
			$scope.convertNumberFilter();
			$scope.addCurrencyAndRateSymbol();
			toastr.success("Note has been save successfully.")
		}, function(response) {
			$auth.checkLoginFromServer(response.status);
			toastr.error("We are unable to save note. Please try after sometime.")
		});

	}

	$scope.updateAndSaveMarketValue = function() {
		$scope.sanitizeNoteInputModelFromJS();
		NoteService.getYield($scope.noteInputFormModel);
		$scope.checkForNonPerformingNote();
		$scope.noteInputFormModel.noteDate = $filter('date')($scope.noteInputFormModel.noteDate, 'MM/dd/yyyy');
		$scope.noteInputFormModel.lastPaymentRecievedDate = $filter('date')($scope.noteInputFormModel.lastPaymentRecievedDate, 'MM/dd/yyyy');
		NoteService.updateAndSaveMarketValue($scope.noteInputFormModel).then(function(response) {
			$scope.noteInputFormModel = response;
			$scope.convertNumberFilter();
			$scope.addCurrencyAndRateSymbol();
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
				$scope.addCurrencyAndRateSymbol();
				toastr.success("Note has been updated successfully.")
			}, function(response) {
				$auth.checkLoginFromServer(response.status);
				toastr.error("We are unable to update note. Please try after sometime.")
			})
		}, function(response) {
			$auth.checkLoginFromServer(response.status);
			toastr.error("We are unable to update user subscripton. Please try after sometime.")
		});

	}


	$scope.deleteNote = function() {
		NoteService.deleteNote($scope.noteInputFormModel).then(function(response) {
			$state.go('noteDashboard');
			toastr.success("Note has been deleted successfully.")
		}, function(response) {
			$auth.checkLoginFromServer(response.status);
			toastr.error("We are unable to delete this note. Please try after sometime.")
		});

	}
});