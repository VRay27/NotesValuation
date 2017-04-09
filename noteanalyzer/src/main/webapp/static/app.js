var noteApp = angular.module('MyApp', ['ngResource', 'ngMessages', 'ngAnimate', 'toastr', 'ui.router'])
  .config(function($stateProvider, $urlRouterProvider) {

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
        url: '/',
        controller: 'HomeCtrl',
        templateUrl: 'static/partials/home.html'
      })
      .state('login', {
        url: '/login',
        templateUrl: 'static/partials/login.html',
        controller: 'LoginCtrl',
        resolve: {
          skipIfLoggedIn: skipIfLoggedIn
        }
      })
      .state('signup', {
        url: '/signup',
        templateUrl: 'static/partials/signup.html',
        controller: 'SignupCtrl',
        resolve: {
          skipIfLoggedIn: skipIfLoggedIn
        }
      })
      .state('logout', {
        url: '/logout',
        template: null,
        controller: 'LogoutCtrl'
      })
      .state('profile', {
        url: '/profile',
        templateUrl: 'static/partials/profile.html',
        controller: 'ProfileCtrl',
        resolve: {
          loginRequired: loginRequired
        }
      });
    $urlRouterProvider.otherwise('/');


  });
noteApp.config(['$httpProvider', function($httpProvider) {
  $httpProvider.interceptors.push('APIInterceptor');
}]);
noteApp.service('APIInterceptor', [function() {
  var service = this;

  service.request = function(config) {
    config.headers['Content-Type'] = "application/json";
    config.headers['X-Requested-With'] = "XMLHttpRequest";
    if (localStorage.getItem('token')) {
      config.headers['X-Authorization'] = 'Bearer ' + localStorage.getItem('token');
    }
    return config;
  };
}]);
noteApp.factory('$auth', function() {
  var isAuthenticated = function() {
    if (localStorage.getItem('token')) {
      return true;
    } else {
      return false;
    }
  }
  return {
    isAuthenticated: isAuthenticated
  }
})