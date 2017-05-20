<!DOCTYPE html>
<html >
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>Note Analyzer</title>
   <script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
 <script src="https://cdnjs.cloudflare.com/ajax/libs/angular.js/1.6.4/angular.min.js"></script>
 
  <link href="https://cdnjs.cloudflare.com/ajax/libs/angular-toastr/2.1.1/angular-toastr.css" rel="stylesheet"/>
  <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet"/>
  <link href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/3.5.2/animate.css" rel="stylesheet"/>
  <link href="https://cdnjs.cloudflare.com/ajax/libs/angular-ui-grid/4.0.4/ui-grid.css" rel="stylesheet"/>
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-formhelpers/2.3.0/css/bootstrap-formhelpers.css" />
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/angular-xeditable/0.7.1/css/xeditable.min.css" />
  <link rel="shortcut icon" href="static/img/favicon.ico" />
  <!-- <link href="static/css/animate.css" rel="stylesheet">
  <link href="static/css/angular-toastr.css" rel="stylesheet">
  <link rel="styleSheet" href="static/css/ui-grid.min.css" />
  <link href="static/css/bootstrap.min.css" rel="stylesheet">
  <link href="static/css/bootstrap-formhelpers.css" rel="stylesheet">
 -->
   <link rel="styleSheet" href="static/css/app.css" />
  <link href="static/css/styles.css" rel="stylesheet">
  <link rel="styleSheet" href="static/css/custom.css" /> 
</head>
<body>
<div ng-controller="NavbarCtrl" class="navbar navbar-default navbar-static-top">
  <div class="navbar-header">
    <a class="navbar-brand" href="/"><i class="ion-ios7-pulse-strong"></i> Note Analyzer</a>
  </div>
  <ul class="nav navbar-nav">
    <li><a href="/notes/#!/">Home</a></li>
    <li ng-if="isAuthenticated()"><a href="/notes/#!/profile">Profile</a></li>
  </ul>
  <ul ng-if="isAuthenticated()" class="nav navbar-nav">
    <li><a href="/notes/#!/noteDashboard">My Dashboard</a></li>
  </ul>
  <ul ng-if="isAuthenticated()" class="nav navbar-nav">
    <li><a href="/notes/#!/subscription">Subscription</a></li>
  </ul>
  <ul ng-if="!isAuthenticated()" class="nav navbar-nav pull-right">
    <li><a href="/notes/#!/login">Login</a></li>
    <li><a href="/notes/#!/signup">Sign up</a></li>
  </ul>
  <ul ng-if="isAuthenticated()" class="nav navbar-nav pull-right">
    <li><a href="/notes/#!/logout">Logout</a></li>
  </ul>
   
</div>

<div ui-view ></div>

<!-- Third-party Libraries -->
<!-- <script src="static/lib/jquery-3.2.1.min.js"></script>
<script src="static/lib/angular.js"></script> -->
 
<script src="https://cdnjs.cloudflare.com/ajax/libs/angular.js/1.6.4/angular-animate.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/angular-messages/1.6.4/angular-messages.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/angular-resource/1.6.4/angular-resource.min.js"></script>

  <!-- Angular Material Library -->
  
<script src="https://cdnjs.cloudflare.com/ajax/libs/angular-sanitize/1.6.4/angular-sanitize.min.js"></script>
<!-- <script src="https://cdnjs.cloudflare.com/ajax/libs/angular-ui-router/0.4.2/angular-ui-router.min.js"></script> -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/angular-ui-router/1.0.3/angular-ui-router.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/angular-toastr/1.7.0/angular-toastr.tpls.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/angular-touch/1.6.4/angular-touch.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/angular-ui-grid/4.0.4/ui-grid.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" ></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/angular-ui-bootstrap/2.5.0/ui-bootstrap-tpls.min.js" ></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-formhelpers/2.3.0/js/bootstrap-formhelpers.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/angular-xeditable/0.7.1/js/xeditable.min.js"></script>
<!-- <script src="static/lib/angular-animate.js"></script>
<script src="static/lib/angular-messages.js"></script>
<script src="static/lib/angular-resource.js"></script>
<script src="static/lib/angular-sanitize.js"></script>
<script src="static/lib/angular-ui-router.js"></script>
<script src="static/lib/angular-toastr.tpls.js"></script>
<script src="static/lib/angular-touch.min.js"></script>
<script src="static/lib/ui-grid.js"></script>
<script src="static/lib/ui-bootstrap-tpls.js"></script>
<script src="static/js/bootstrap-formhelpers.js"></script> -->

<!-- Application Code -->
<script src="static/note.js"></script>
<script src="static/js/directives/passwordStrength.js"></script>
<script src="static/js/directives/passwordMatch.js"></script>
<script src="static/js/constant/constant.js"></script>
<script src="static/js/controller/home.js"></script>
<script src="static/js/controller/login.js"></script>
<script src="static/js/controller/signup.js"></script>
<script src="static/js/controller/logout.js"></script>
<script src="static/js/controller/profile.js"></script>
<script src="static/js/controller/note-detail.js"></script>
<script src="static/js/service/user_service.js"></script>

</body>
<script type="text/javascript">
	angular.element(document).ready(function () {
    	 angular.bootstrap(document, ['NoteApp']);
    });
</script>
</html>
