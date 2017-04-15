var noteApp = angular.module('NoteApp', ['ngResource', 'ngMessages', 'ngAnimate', 'toastr', 'ui.router', 'ngAnimate', 'ui.grid', 'ui.grid.moveColumns', 'ui.grid.selection', 'ui.grid.resizeColumns', 'ui.bootstrap', 'ui.grid.edit', 'ui.grid.pagination'])
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
        templateUrl: 'static/template/home.html'
      }).state('noteDashboard', {
        url: '/noteDashboard',
        controller: 'NoteDetailCtrl',
        controllerAs:'vm',
        templateUrl: 'static/template/note-dashboard.html'
      })
      .state('login', {
        url: '/login',
        templateUrl: 'static/template/login.html',
        controller: 'LoginCtrl',
        resolve: {
          skipIfLoggedIn: skipIfLoggedIn
        }
      })
      .state('signup', {
        url: '/signup',
        templateUrl: 'static/template/signup.html',
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
        templateUrl: 'static/template/profile.html',
        controller: 'ProfileCtrl',
        resolve: {
          loginRequired: loginRequired
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