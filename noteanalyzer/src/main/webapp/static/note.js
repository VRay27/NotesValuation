
var noteApp = angular.module('NoteApp', ['ngResource', 'ngMessages', 'ngAnimate', 'toastr', 'ui.router', 'ngAnimate', 'ui.grid', 'ui.grid.moveColumns', 'ui.grid.selection', 'ui.grid.resizeColumns', 'ui.bootstrap', 'ui.grid.edit', 'ui.grid.pagination']);

noteApp.config(function($stateProvider, $urlRouterProvider) {

	/**
	 * Helper auth functions
	 */
	var skipIfLoggedIn = function() {
		if (localStorage.getItem('token')) {
			return true;
		} else {
			return false;
		}
	};

	var loginRequired = function() {
		if (localStorage.getItem('token')) {
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
			params : {
				'referer' : null,
				'loginState' : null
			},
			templateUrl : 'static/template/home.html'
		}).state('noteDashboard', {
		url : '/noteDashboard',
		controller : 'NoteDetailCtrl',
		controllerAs : 'vm',
		params : {
			'referer' : null,
			'loginState' : null
		},
		resolve : {
			loginRequired : loginRequired
		},
		templateUrl : 'static/template/note-dashboard.html'
	})
		.state('login', {
			url : '/login',
			templateUrl : 'static/template/login.html',
			controller : 'LoginCtrl',
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
			resolve : {
				skipIfLoggedIn : skipIfLoggedIn
			}
		})
		.state('logout', {
			url : '/logout',
			template : null,
			controller : 'LogoutCtrl'
		})
		.state('profile', {
			url : '/profile',
			templateUrl : 'static/template/profile.html',
			controller : 'ProfileCtrl',
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
	$httpProvider.useApplyAsync(true);
}]);

noteApp.service('APIInterceptor', [function() {
	var service = this;

	service.request = function(config) {
		/*config.headers['Content-Type'] = "application/json";*/
		config.headers['X-Requested-With'] = "XMLHttpRequest";
		if (localStorage.getItem('token')) {
			config.headers['X-Authorization'] = 'Bearer ' + localStorage.getItem('token');
		}
		return config;
	};
	service.response = function(res) {
		/* if(res.config.url.indexOf(API) === 0 && res.data.token) {
		   auth.saveToken(res.data.token);
		 }*/

		return res;
	};
}]);

noteApp.factory('$auth', function($window) {
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
		$window.localStorage.setItem('token', token);
	};

	auth.logout = function() {
		$window.localStorage.removeItem('token');
	};

	auth.getToken = function() {
		return $window.localStorage.getItem('token');
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
	return {
		isAuthenticated : isAuthenticated,
		saveToken : auth.saveToken,
		logout : auth.logout,
		getToken : auth.getToken
	}
});