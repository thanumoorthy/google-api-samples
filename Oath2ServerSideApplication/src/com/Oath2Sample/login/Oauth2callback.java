package com.Oath2Sample.login;

import java.io.FileReader;
import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.http.HttpRequestFactory;

import com.google.api.client.auth.oauth2.CredentialStoreRefreshListener;
import com.google.api.client.auth.oauth2.DataStoreCredentialRefreshListener;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeRequestUrl;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeTokenRequest;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;



/**
 * Servlet implementation class Oauth2callback
 */
@WebServlet("/Oauth2callback")
public class Oauth2callback extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String AuthCode = null;
	static String CLIENT_SECRET_FILE = "WEB-INF/client_secret.json" ;	
	static String  REDIRECT_URI = "http://localhost:8080/Oath2SampleThanu/Oauth2callback";
	static String userEmail ="";
	//static String
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Oauth2callback() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		if (  request.getParameter("error") == null && request.getParameter("code") != null ) {		
			 AuthCode = (String)request.getParameter("code");
			 System.out.println("I got Authorization Code " + AuthCode);
			 GoogleClientSecrets clientSecrets =
					    GoogleClientSecrets.load(
					        JacksonFactory.getDefaultInstance(), new FileReader(getServletContext().getRealPath("/") + CLIENT_SECRET_FILE));
					GoogleTokenResponse tokenResponse =
					          new GoogleAuthorizationCodeTokenRequest(
					              new NetHttpTransport(),
					              JacksonFactory.getDefaultInstance(),
					              "https://www.googleapis.com/oauth2/v4/token",
					              clientSecrets.getDetails().getClientId(),
					              clientSecrets.getDetails().getClientSecret(),
					              AuthCode,
					              REDIRECT_URI)
					              .execute();
					
					
					
					
					String accessToken = tokenResponse.getAccessToken();
					
					long tokenGotTime  = System.currentTimeMillis();
					System.out.println("token got time is " + tokenGotTime);
					HttpSession session=request.getSession();
					
					
					String RefreshToken = tokenResponse.getRefreshToken();
					
					session.setAttribute("AccessToken",accessToken);
					session.setAttribute("RefreshToken",RefreshToken);
					session.setAttribute("tokenGotTime",tokenGotTime);
					
					System.out.println("I got   Access Token " +   " " +  accessToken);
					System.out.println("I got   RefreshToken " +   " " +  RefreshToken);
					System.out.println("I got   tokenGotTime " +   " " +  tokenGotTime);
					GoogleCredential credential = new GoogleCredential().setAccessToken(accessToken);

					//Userinfoplus userinfo = oauth2.userinfo().get().execute();
				    //System.out.println(userinfo.toPrettyString());
					/*Oauth2 oauth2 = new Oauth2.Builder(new NetHttpTransport(), new JacksonFactory(), credential).setApplicationName(
					          "Oauth2").build();
					 Userinfoplus userinfo = oauth2.userinfo().get().execute();*/
					
					//GoogleCredential credentialrefresh = new GoogleCredential().setRefreshToken(RefreshToken);					
			        //HttpRequestFactory requestFactory = credentialrefresh.getTransport().createRequestFactory(credentialrefresh);
				    //return requestFactory.buildGetRequest(url).execute();
					
					//new GoogleAuthorizationCodeTokenRequest(transport, jsonFactory, clientId, clientSecret, code, redirectUri)
					
					//new GoogleAuthorizationCodeRequestUrl(authorizationServerEncodedUrl, clientId, redirectUri, scopes)
					
					/* session.setAttribute("userName",userinfo.getGivenName());
					 session.setAttribute("email",userinfo.getEmail());
					 session.setAttribute("familyName",userinfo.getFamilyName());
					 session.setAttribute("gender",userinfo.getGender());
					 session.setAttribute("googleplus",userinfo.getLink());
					 session.setAttribute("picture",userinfo.getPicture());*/
					 
					 /*System.out.println("userinfo from server " + userinfo.toPrettyString());*/
					 response.sendRedirect("dashboard.jsp");
					 
			
			
			
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
