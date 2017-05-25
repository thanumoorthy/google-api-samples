<!DOCTYPE html>
<html>
<head>
<script>
	function openNewTab() {
		
		console.log("I am here");
		window.location.href = "https://accounts.google.com/o/oauth2/v2/auth?scope=email%20profile&response_type=code&access_type=offline&state=security_token%3D138r5719ru3e1%26url%3Dhttp://localhost:8080/Oath2SampleThanu&redirect_uri=http://localhost:8080/Oath2SampleThanu/Oauth2callback&client_id=895537274156-3s3qj6serg3vv62luqbvkp3k088jn0vr.apps.googleusercontent.com";

	}
</script>

<link rel="stylesheet" type="text/css" href="logintheme.css" />

</head>
<body>
<div style="width: 300px;margin-left: 350px;">
 <div class="auth-form-header p-0">
        <h1>Sign in to WebSite</h1>
  </div>
<div class="auth-form-body mt-3">
 <label for="login_field">
          Username or email address
  </label>
<input autocapitalize="off" autocorrect="off" autofocus="autofocus" 
        class="form-control input-block" id="login_field" name="login" tabindex="1" type="text">
        
        <label for="password">
          Password <a href="/password_reset" class="label-link">Forgot password?</a>
        </label>
    <input class="form-control form-control input-block" id="password" name="password" tabindex="2" type="password">
    
    <input class="btn btn-primary btn-block" data-disable-with="Signing in" name="commit" tabindex="3" type="submit" value="Sign in">
    <br><br>
    <button class="loginBtn loginBtn--google btn btn-block" onClick="openNewTab()">
		Login with Google</button>    
</div>


</div>
</body>
</html>