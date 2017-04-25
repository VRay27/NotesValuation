<!DOCTYPE html>
<html >
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>Note Analyzer</title>
  <!-- <link href="https://cdnjs.cloudflare.com/ajax/libs/angular-toastr/2.1.1/angular-toastr.css" rel="stylesheet"/>
  <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
  <link href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/3.5.2/animate.css" rel="stylesheet"/>
  <link href="https://cdnjs.cloudflare.com/ajax/libs/angular-ui-grid/4.0.4/ui-grid.css" rel="stylesheet"/>
   -->
  <link href="static/css/animate.css" rel="stylesheet">
  <link href="static/css/angular-toastr.css" rel="stylesheet">
  <link rel="styleSheet" href="static/css/ui-grid.min.css" />
  <link href="static/css/bootstrap.min.css" rel="stylesheet">

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

<div ui-view></div>

<!-- Third-party Libraries -->
<script src="static/lib/jquery-3.2.1.min.js"></script>
<script src="static/lib/angular.js"></script>
<!-- <script src="https://cdnjs.cloudflare.com/ajax/libs/angular.js/1.6.4/angular-animate.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/angular-messages/1.6.4/angular-messages.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/angular-resource/1.6.4/angular-resource.js"></script>

<script src="https://cdnjs.cloudflare.com/ajax/libs/angular-sanitize/1.6.4/angular-sanitize.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/angular-ui-router/0.4.2/angular-ui-router.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/angular-toastr/1.7.0/angular-toastr.tpls.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/angular-touch/1.6.4/angular-touch.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/angular-ui-grid/4.0.4/ui-grid.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" ></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/angular-ui-bootstrap/2.5.0/ui-bootstrap-tpls.js" ></script>
 -->
<script src="static/lib/angular-animate.js"></script>
<script src="static/lib/angular-messages.js"></script>
<script src="static/lib/angular-resource.js"></script>
<script src="static/lib/angular-sanitize.js"></script>
<script src="static/lib/angular-ui-router.js"></script>
<script src="static/lib/angular-toastr.tpls.js"></script>
<script src="static/lib/angular-touch.min.js"></script>
<script src="static/lib/ui-grid.js"></script>
<script src="static/lib/ui-bootstrap-tpls.js"></script>
<script src="static/lib/angular-upload.js"></script>
<!-- shim is needed to support non-HTML5 FormData browsers (IE8-9)-->
<!-- <script src="static/lib/ng-file-upload-shim.min.js"></script>
<script src="static/lib/ng-file-upload.min.js"></script> -->
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
