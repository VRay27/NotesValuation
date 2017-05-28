
var noteApp = angular.module('NoteApp', ['ngResource', 'xeditable', 'ngMessages', 'ngAnimate', 'toastr', 'ui.router', 'ngAnimate', 'ui.grid', 'ui.grid.moveColumns', 'ui.grid.selection', 'ui.grid.resizeColumns', 'ui.bootstrap', 'ui.grid.edit', 'ui.grid.pagination']);

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
			
		})
		.state('profile', {
			url : '/profile',
			templateUrl : 'static/template/profile.html',
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
		}).state('subscription', {
			url : '/subscription',
			templateUrl : 'static/template/subscription.html',
			controller : 'SubscriptionCtrl',
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
		$window.sessionStorage.removeItem('token');
	};

	auth.getToken = function() {
		return $window.sessionStorage.getItem('token');
	};
	
	auth.checkLoginFromServer = function(status) {
		if(status===401){
			auth.logout();
			toastr.error('Your session has been invalid. Please login again.');
			$state.go('login');
		}
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
		getUser : auth.setUser,
		checkLoginFromServer:auth.checkLoginFromServer,
		encodeString:auth.encodeString
	}
});