
var noteApp = angular.module('NoteApp', ['ngResource', 'xeditable', 'ngMessages', 'ngAnimate', 'toastr', 'ui.router', 'ngAnimate', 'ui.grid', 'ui.grid.moveColumns', 'ui.grid.selection', 'ui.grid.resizeColumns', 'ui.bootstrap', 'ui.grid.edit', 'isteven-multi-select','ui.grid.pagination']);

noteApp.config(function($stateProvider, $urlRouterProvider) {

	/**
	 *  'material.svgAssetsCache',
	 * Helper auth functions
	 */
	var skipIfLoggedIn = function() {
		if (sessionStorage.getItem('token')) {
			return true;
		} else {
			return false;
		}
	};

	var loginRequired = function() {
		if (sessionStorage.getItem('token')) {
			return false;
		} else {
			return true;
		}
	};

	/**
	 * App routes
	 */
	$stateProvider
		.state('home', {
			url : '/',
			controller : 'HomeCtrl',
			cache: false,
			params : {
				'referer' : null,
				'loginState' : null
			},
			templateUrl : 'static/template/home.html'
		}).state('noteDashboard', {
		url : '/noteDashboard',
		controller : 'NoteDetailCtrl',
		controllerAs : 'vm',
		cache: false,
		params : {
			'referer' : null,
			'loginState' : null
		},
		resolve : {
			loginRequired : loginRequired,
			skipIfLoggedIn : skipIfLoggedIn
		},
		templateUrl : 'static/template/note-dashboard.html'
	})
		.state('login', {
			url : '/login',
			templateUrl : 'static/template/login.html',
			controller : 'LoginCtrl',
			cache: false,
			resolve : {
				skipIfLoggedIn : skipIfLoggedIn
			},
			params : {
				'referer' : null,
				'loginState' : null
			}
		})
		.state('signup', {
			url : '/signup',
			templateUrl : 'static/template/signup.html',
			controller : 'SignupCtrl',
			cache: false,
			resolve : {
				skipIfLoggedIn : skipIfLoggedIn
			}
		})
		.state('logout', {
			url : '/logout',
			template : null,
			cache: false,
			controller : 'LogoutCtrl'
		})
		.state('changePassword', {
			url : '/changePassword',
			templateUrl : 'static/template/changePassword.html',
			controller : 'SignupCtrl',
			cache: false,
			resolve : {
				skipIfLoggedIn : skipIfLoggedIn
			}
			
		}).state('noteInputForm', {
			url : '/noteInputForm',
			templateUrl : 'static/template/note-form-new.html',
			/*controller : 'noteInputFormController',*/
			cache: false,
			resolve : {
				skipIfLoggedIn : skipIfLoggedIn
			}
			
		})
		.state('profile', {
			url : '/profile',
			templateUrl : 'static/template/profile_new.html',
			controller : 'ProfileCtrl',
			cache: false,
			params : {
				'referer' : null,
				'loginState' : null
			},
			resolve : {
				loginRequired : loginRequired,
				skipIfLoggedIn : skipIfLoggedIn
			}
		}).state('noteDetail', {
			url : '/noteDetail',
			templateUrl : 'static/template/noteDetailNew.html',
			controller : 'noteInputFormController',
			cache: false,
			params : {
				'referer' : null,
				'loginState' : null
			},
			resolve : {
				loginRequired : loginRequired,
				skipIfLoggedIn : skipIfLoggedIn
			}
		}).state('calculator', {
			url : '/calculator',
			templateUrl : 'static/template/calculator.html',
			controller : 'HomeCtrl',
			cache: false,
			params : {
				'referer' : null,
				'loginState' : null
			},
			resolve : {
				loginRequired : loginRequired
			}
		});
	$urlRouterProvider.otherwise('/');


});

noteApp.config(['$httpProvider', function($httpProvider) {
	$httpProvider.interceptors.push('APIInterceptor');
}]);

noteApp.run(function(editableOptions) {
	  editableOptions.theme = 'bs3'; // bootstrap3 theme. Can be also 'bs2', 'default'
	});



noteApp.service('APIInterceptor', [function() {
	var service = this;
	//var resourceCache = $cacheFactory.get('resourceCache');
	//ar httpCache = $cacheFactory.get('$http');
	service.request = function(config) {
		/*config.headers['Content-Type'] = "application/json";*/
		config.headers['X-Requested-With'] = "XMLHttpRequest";
		if (sessionStorage.getItem('token')) {
			config.headers['X-Authorization'] = 'Bearer ' + sessionStorage.getItem('token');
		}
		 //disable IE ajax request caching
		config.headers['If-Modified-Since'] = 'Mon, 26 Jul 1997 05:00:00 GMT';
	    // extra
		config.headers['Cache-Control'] = 'no-cache';
		config.headers['Pragma'] = 'no-cache';
		return config;
	};
	service.response = function(response) {
		//resourceCache.remove(response.config.url);
		//httpCache.remove(response.config.url);
	      //console.log('cache removed', response.config.url);
	      return response;
	};
}]);

noteApp.run(function($rootScope, $state, $location, $auth){
    $rootScope.$on('$stateChangeStart', function(event, toState, toParams, fromState, skipIfLoggedIn){
        let loggedIn = $auth.isAuthenticated();
        if  (!skipIfLoggedIn && !loggedIn){
        	$state.go('login');
        }
    });
});

noteApp.config(['$compileProvider', function ($compileProvider) {
	  $compileProvider.debugInfoEnabled(true);
	}]);

noteApp.factory('$auth', function($window,$state,toastr) {
	var auth = this;
	var isAuthenticated = function() {
		if (!auth.isAuthed()) {
			auth.logout();
			return false;
		}
		return true;
	}
	auth.parseJwt = function(token) {
		var base64Url = token.split('.')[1];
		var base64 = base64Url.replace('-', '+').replace('_', '/');
		return JSON.parse($window.atob(base64));
	};

	auth.saveToken = function(token) {
		$window.sessionStorage.setItem('token', token);
	};

	auth.logout = function() {
		$window.sessionStorage.removeItem('user');
		$window.sessionStorage.removeItem('token');
	};

	auth.getToken = function() {
		return $window.sessionStorage.getItem('token');
	};
	
	auth.checkLoginFromServer = function(status) {
		/*if(status===401){
			auth.logout();
			toastr.error('Your session has been invalid. Please login again.');
			$state.go('login');
		}*/
	};
	

	auth.isAuthed = function() {
		var token = auth.getToken();
		if (token) {
			var params = auth.parseJwt(token);
			return Math.round(new Date().getTime() / 1000) <= params.exp;
		} else {
			return false;
		}
	}
	auth.getUser = function(){
		return $window.sessionStorage.getItem('user');
	}
	
	auth.setUser = function(user){
		$window.sessionStorage.setItem('user', user);
	}
	auth.encodeString = function (str) {
	    // first we use encodeURIComponent to get percent-encoded UTF-8,
	    // then we convert the percent encodings into raw bytes which
	    // can be fed into btoa.
	    return $window.btoa(encodeURIComponent(str).replace(/%([0-9A-F]{2})/g,
	        function toSolidBytes(match, p1) {
	            return String.fromCharCode('0x' + p1);
	    }));
	}
	return {
		isAuthenticated : isAuthenticated,
		saveToken : auth.saveToken,
		logout : auth.logout,
		getToken : auth.getToken,
		getUser : auth.getUser,
		setUser : auth.setUser,
		checkLoginFromServer:auth.checkLoginFromServer,
		encodeString:auth.encodeString
	};
	
});

noteApp.factory('WaitingDialog', function($window,$state,toastr) {
	var $dialog = $(
			'<div class="modal fade" data-backdrop="static" data-keyboard="false" tabindex="-1" role="dialog" aria-hidden="true" style="padding-top:15%; overflow-y:visible;">' +
			'<div class="modal-dialog modal-m">' +
			'<div class="modal-content">' +
				'<div class="modal-header"><h3 style="margin:0;"></h3></div>' +
				'<div class="modal-body">' +
					'<div class="progress progress-striped active" style="margin-bottom:0;"><div class="progress-bar" style="width: 100%"></div></div>' +
				'</div>' +
			'</div></div></div>');
	

	return {
		// Creating modal dialog's DOM
		/**
		 * Opens our dialog
		 * @param message Custom message
		 * @param options Custom options:
		 * 				  options.dialogSize - bootstrap postfix for dialog size, e.g. "sm", "m";
		 * 				  options.progressType - bootstrap postfix for progress bar type, e.g. "success", "warning".
		 */
		show: function (message, options) {
			// Assigning defaults
			if (typeof options === 'undefined') {
				options = {};
			}
			if (typeof message === 'undefined') {
				message = 'Loading';
			}
			var settings = $.extend({
				dialogSize: 'm',
				progressType: '',
				onHide: null // This callback runs after the dialog was hidden
			}, options);

			// Configuring dialog
			$dialog.find('.modal-dialog').attr('class', 'modal-dialog').addClass('modal-' + settings.dialogSize);
			$dialog.find('.progress-bar').attr('class', 'progress-bar');
			if (settings.progressType) {
				$dialog.find('.progress-bar').addClass('progress-bar-' + settings.progressType);
			}
			$dialog.find('h3').text(message);
			// Adding callbacks
			if (typeof settings.onHide === 'function') {
				$dialog.off('hidden.bs.modal').on('hidden.bs.modal', function (e) {
					settings.onHide.call($dialog);
				});
			}
			// Opening dialog
			$dialog.modal();
		},
		/**
		 * Closes dialog
		 */
		hide: function () {
			$dialog.modal('hide');
		}
	};

});
