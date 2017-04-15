<!DOCTYPE html>
<html >
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>Note Analyzer</title>
  
  
  <link href="static/css/animate.css" rel="stylesheet">
  <link href="static/css/angular-toastr.css" rel="stylesheet">
  <link href="static/css/styles.css" rel="stylesheet">
  <link rel="styleSheet" href="static/css/ui-grid.min.css" />
  <link rel="styleSheet" href="static/css/app.css" />
  <link href="static/css/bootstrap.min.css" rel="stylesheet">
  <link rel="styleSheet" href="static/css/custom.css" />
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
  <ul ng-if="isAuthenticated()" class="nav navbar-nav">
    <li><a href="/notes/#/subscription">Subscription</a></li>
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
<script src="static/lib/angular.js"></script>
<script src="static/lib/angular-animate.js"></script>
<script src="static/lib/angular-messages.js"></script>
<script src="static/lib/angular-resource.js"></script>
<script src="static/lib/angular-sanitize.js"></script>
<script src="static/lib/angular-ui-router.js"></script>
<script src="static/lib/angular-toastr.tpls.js"></script>
<script src="static/lib/angular-touch.min.js"></script>
<script src="static/lib/ui-grid.js"></script>
<script src="static/lib/ui-bootstrap-tpls-0.13.4.js"></script>
<!-- Application Code -->
<script src="static/note.js"></script>
<script src="static/js/directives/passwordStrength.js"></script>
<script src="static/js/directives/passwordMatch.js"></script>
<script src="static/js/controller/home.js"></script>
<script src="static/js/controller/login.js"></script>
<script src="static/js/controller/signup.js"></script>
<script src="static/js/controller/logout.js"></script>
<script src="static/js/controller/profile.js"></script>
<script src="static/js/controller/navbar.js"></script>
<script src="static/js/service/account.js"></script>
<script src="static/js/controller/note-detail.js"></script>
<script src="static/js/constant/constant.js"></script>
</body>
<script type="text/javascript">
	angular.element(document).ready(function () {
    	 angular.bootstrap(document, ['NoteApp']);
    });
</script>
</html>
