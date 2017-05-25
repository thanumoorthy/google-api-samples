package com.Oath2Sample.login;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeTokenRequest;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;


public class ThanuSample {
	
	static String CLIENT_SECRET_FILE = "C:\\Users\\Thanu\\Downloads\\client_secret.json";
	static String  authCode = "4/c3hZTAj-1miOsdJO4Dd0dMRTWW9ijyCFpO20M6ADFVY";
	static String  REDIRECT_URI = "http://localhost:8080/Oath2SampleThanu/Oauth2callback";
	
	
	
	public static void main(String arg[]) throws FileNotFoundException, IOException {
		// Exchange auth code for access token
		GoogleClientSecrets clientSecrets =
		    GoogleClientSecrets.load(
		        JacksonFactory.getDefaultInstance(), new FileReader(CLIENT_SECRET_FILE));
		GoogleTokenResponse tokenResponse =
		          new GoogleAuthorizationCodeTokenRequest(
		              new NetHttpTransport(),
		              JacksonFactory.getDefaultInstance(),
		              "https://www.googleapis.com/oauth2/v4/token",
		              clientSecrets.getDetails().getClientId(),
		              clientSecrets.getDetails().getClientSecret(),
		              authCode,
		              REDIRECT_URI)  // Specify the same redirect URI that you use with your web
		                             // app. If you don't have a web version of your app, you can
		                             // specify an empty string.
		              .execute();
		
		String accessToken = tokenResponse.getAccessToken();
		System.out.println("I got Access Token " + accessToken);
	}

}
