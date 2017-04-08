<!DOCTYPE html>
<html >
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>Note Analyzer</title>
  <link href="../../notes/static/images/favicon.png" rel="shortcut icon">
  <link href="//fonts.googleapis.com/css?family=Roboto|Montserrat:400,700|Open+Sans:400,300,600" rel="stylesheet">
  <link href="//maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet">
  <link href="//code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css" rel="stylesheet">
  <link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet">
  <link href="//cdn.jsdelivr.net/animatecss/3.2.0/animate.css" rel="stylesheet">
  <link href="../../notes/static/stylesheets/angular-toastr.css" rel="stylesheet">
  <link href="../../notes/static/stylesheets/styles.css" rel="stylesheet">
</head>
<body>
<div ng-controller="NavbarCtrl" class="navbar navbar-default navbar-static-top">
  <div class="navbar-header">
    <a class="navbar-brand" href="/"><i class="ion-ios7-pulse-strong"></i> Note Analyzer</a>
  </div>
  <ul class="nav navbar-nav">
    <li><a href="/notes/#/">Home</a></li>
    <li ng-if="isAuthenticated()"><a href="/notes/#/profile">Profile</a></li>
  </ul>
  <ul ng-if="!isAuthenticated()" class="nav navbar-nav pull-right">
    <li><a href="/notes/#/login">Login</a></li>
    <li><a href="/notes/#/signup">Sign up</a></li>
  </ul>
  <ul ng-if="isAuthenticated()" class="nav navbar-nav pull-right">
    <li><a href="/notes/#/logout">Logout</a></li>
  </ul>
</div>

<div ui-view></div>

<!-- Third-party Libraries -->
<script src="../../notes/static/vendor/angular.js"></script>
<script src="../../notes/static/vendor/angular-animate.js"></script>
<script src="../../notes/static/vendor/angular-messages.js"></script>
<script src="../../notes/static/vendor/angular-resource.js"></script>
<script src="../../notes/static/vendor/angular-sanitize.js"></script>
<script src="../../notes/static/vendor/angular-ui-router.js"></script>
<script src="../../notes/static/vendor/angular-toastr.tpls.js"></script>


<!-- Application Code -->
<script src="../../notes/static/app.js"></script>
<script src="../../notes/static/directives/passwordStrength.js"></script>
<script src="../../notes/static/directives/passwordMatch.js"></script>
<script src="../../notes/static/controllers/home.js"></script>
<script src="../../notes/static/controllers/login.js"></script>
<script src="../../notes/static/controllers/signup.js"></script>
<script src="../../notes/static/controllers/logout.js"></script>
<script src="../../notes/static/controllers/profile.js"></script>
<script src="../../notes/static/controllers/navbar.js"></script>
<script src="../../notes/static/services/account.js"></script>
</body>
<script type="text/javascript">
	angular.element(document).ready(function () {
    	 angular.bootstrap(document, ['MyApp']);
    });
</script>
</html>
