<%@page import="com.google.api.services.oauth2.model.Userinfoplus,
com.google.api.services.oauth2.Oauth2,
com.google.api.client.http.javanet.NetHttpTransport,
com.google.api.client.json.jackson2.JacksonFactory,com.google.api.client.googleapis.auth.oauth2.GoogleCredential" %> 
<% 
System.out.println("I am hitting dashboard.jsp");
HttpSession nsession = request.getSession(false);
if(nsession!=null && nsession.getAttribute("AccessToken") != null && nsession.getAttribute("RefreshToken") != null ) {

long currentTime = System.currentTimeMillis();
long tokenGotTime = (Long)session.getAttribute("tokenGotTime") ;
long timeRemainingInSec = (currentTime - tokenGotTime)/(1000);
long timeRemaining = timeRemainingInSec / 60;
System.out.println("tokenGotTime currentTime timeRemaining timeRemainingInSec " +tokenGotTime + " " +  currentTime + " " + timeRemaining + " " + timeRemainingInSec);

if ( timeRemaining  >= 0 &&  timeRemaining <= 50 ) {
System.out.println("Access tocken are valid " + timeRemaining );
String accessToken = (String)session.getAttribute("AccessToken");
System.out.println("Access token is " + accessToken);
GoogleCredential credential = new GoogleCredential().setAccessToken(accessToken);
Oauth2 oauth2 = new Oauth2.Builder(new NetHttpTransport(), new JacksonFactory(), credential).setApplicationName(
        "Oauth2").build();
Userinfoplus userinfo = oauth2.userinfo().get().execute();
session.setAttribute("email",userinfo.getEmail());
session.setAttribute("familyName",userinfo.getFamilyName());
session.setAttribute("gender",userinfo.getGender());
session.setAttribute("googleplus",userinfo.getLink());
session.setAttribute("picture",userinfo.getPicture());

System.out.println(userinfo.toPrettyString());

String userName = userinfo.getGivenName();
String email = userinfo.getEmail();
String familyName = userinfo.getFamilyName();
String gender = userinfo.getGender();
String googleplus = userinfo.getLink();
String picture = userinfo.getPicture();




//nsession.invalidate();
//System.out.println("nsession is " + nsession); && nsession.getAttribute("userName") != null
 %>
<html>
<style>
div {
	display: block;
}

body{
    background-color: #f9f9f9;
     word-wrap: break-word;
     font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", Helvetica, Arial, sans-serif, "Apple Color Emoji", "Segoe UI Emoji", "Segoe UI Symbol";
    font-size: 14px;
    line-height: 1.5;
    color: #24292e;
 }
 
.profile-photo {
border: 5px solid #fff; margin-right: auto; margin-left: auto; box-shadow: 0 0 3px 0 #9ca3a8;
}
.box {
border-bottom: 1px solid #e5e7e8;  margin: 20px 0 0 0; background: #fff; position: relative;

}

</style>

<body>

	<div style="margin-top: 100px">
		<div
			class="box">

			<div style="width: 230px; float: left;">
				<div style="margin-top: 5px; margin-bottom: 20px;">
					<img
						class="profile-photo"
						src="<%= picture %>"
						width="160" height="160">
				</div>
			</div>

			<div style="width: 870px; float: left;">
				<div style="float: left; width: 620px;">
					<h1
						style="float: left; margin: 0; margin-right: 10px; margin-top: 7px; color: #252c33;">
						Welcome to Profile Page</h1>
				</div>

				<div style="float: left;">
					<div style="margin: 20px 0 0 0; font-size: 12px;">

						<table>
							<tr>
								<td>UserName</td>
								<td><%= userName  %></td>
							</tr>
							<tr>
								<td>Email</td>
								<td><%= email %></td>
							</tr>
							<tr>
								<td>FamilyName</td>
								<td><%= familyName %></td>
							</tr>
							<tr>
								<td>gender</td>
								<td><%= gender %></td>
							</tr>
							<tr>
								<td>Google Plus Profile</td>
								<td><a href="><%= googleplus %>">Thanu</a></td>
							</tr>
                         
								
								
							
						</table>
						

                </div>
                <br>
						<a href="signout.jsp">SignOut</a>
					
				</div>

			</div>



		</div>
	</div>

</body>
</html>
<% 
} else {
	System.out.println("Access Token not valid need to refresh..");
}

} else {  
	System.out.println("Session is invalid");
 } %>	

