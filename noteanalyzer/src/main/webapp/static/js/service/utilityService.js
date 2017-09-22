'use strict';


noteApp.factory('UtilityService', ['$http', 'toastr', '$q', '$rootScope', '$uibModal', 'WaitingDialog', '$window', function($http, toastr, $q, $rootScope, $uibModal, WaitingDialog, $window) {
	var factory = {
		calculateRate : calculateRate,
		getNPER : getNPER,
		getPV : getPV,
		round : round,
		getParameterByName : getParameterByName,
		getNumberOfMonth : getNumberOfMonth,
		setActiveHeader : setActiveHeader
	};

	return factory;

	function getNumberOfMonth(startDate, endDate) {
		if (Object.prototype.toString.call(startDate) === "[object Date]" && Object.prototype.toString.call(endDate) === "[object Date]") {
			// it is a date
			if (isNaN(endDate.getTime()) || isNaN(startDate.getTime())) { // d.valueOf() could also work
				// date is not valid
				return '';
			} else {
				// date is valid
				var numberOfMonths = (endDate.getYear() - startDate.getYear()) * 12 + (endDate.getMonth() - startDate.getMonth()) - 1;
				if(numberOfMonths > 0){
					return numberOfMonths;					
				}
				else{
					return 0;	
				}
			}
		}
		return '';

	}
	
	function setActiveHeader(id){
		angular.element(document.querySelector(".nav li")).removeClass("active");
		if(angular.element( document.querySelector('#'+id))){
			angular.element( document.querySelector('#'+id)).addClass("active");	
		}
	 }

	function calculateRate(term, payment, principal, future, type, guess) {
		guess = (guess === undefined) ? 0.01 : guess;
		future = (future === undefined) ? 0 : future;
		type = (type === undefined) ? 0 : type;



		// Set maximum epsilon for end of iteration
		var epsMax = 1e-6;

		// Set maximum number of iterations
		var iterMax = 100;
		var iter = 0;
		var close = false;
		var rate = guess;

		while (iter < iterMax && !close) {
			var t1 = Math.pow(rate + 1, term);
			var t2 = Math.pow(rate + 1, term - 1);

			var f1 = future + t1 * principal + payment * (t1 - 1) * (rate * type + 1) / rate;
			var f2 = term * t2 * principal - payment * (t1 - 1) * (rate * type + 1) / Math.pow(rate, 2);
			var f3 = term * payment * t2 * (rate * type + 1) / rate + payment * (t1 - 1) * type / rate;

			var newRate = rate - f1 / (f2 + f3);

			if (Math.abs(newRate - rate) < epsMax)
				close = true;
			iter++
			rate = newRate;
		}

		if (!close) return Number.NaN + rate;
		return rate;
	};

	function getNPER(rate, payment, present, future, type) {
		// Initialize type
		var type = (typeof type === 'undefined') ? 0 : type;

		// Initialize future value
		var future = (typeof future === 'undefined') ? 0 : future;

		// Evaluate rate and periods (TODO: replace with secure expression evaluator)
		rate = eval(rate);

		// Return number of periods
		var num = payment * (1 + rate * type) - future * rate;
		var den = (present * rate + payment * (1 + rate * type));
		return Math.log(num / den) / Math.log(1 + rate);
	}

	function getPV(rate, periods, payment, future, type) {
		future = future || 0;
		type = type || 0;

		// Return present value
		if (rate === 0) {
			return -payment * periods - future;
		} else {
			return (((1 - Math.pow(1 + rate, periods)) / rate) * payment * (1 + rate * type) - future) / Math.pow(1 + rate, periods);
		}
	};

	function round(value, decimals) {
		if (value) {
			return Number(Math.round(value + 'e' + decimals) + 'e-' + decimals);
		}
	}

	function getParameterByName(name, url) {
		if (!url)
			url = $window.location.href;
		name = name.replace(/[\[\]]/g, "\\$&");
		var regex = new RegExp("[?&]" + name + "(=([^&#]*)|&|#|$)"),
			results = regex.exec(url);
		if (!results) return null;
		if (!results[2]) return '';
		return decodeURIComponent(results[2].replace(/\+/g, " "));
	}
}]);