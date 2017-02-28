package br.com.soapboxrace.xmpp.openfire;

import org.igniterealtime.restclient.RestApiClient;
import org.igniterealtime.restclient.entity.AuthenticationToken;
import org.igniterealtime.restclient.entity.UserEntity;

import br.com.soapboxrace.engine.Session;

public class RestApiCli {

	private static int openFireRestPort = 9090;

	private static String openFireAuthToken = "y0gs2EUWSakiz1q5";

	public static void createUpdatePersona(Long personaId, String password) {
		createUpdateUser("nfsw." + personaId.toString(), password);
	}

	public static void createUpdateUser(String user, String password) {
		RestApiClient restApiClient = getClient();
		UserEntity userEntity = new UserEntity(user, null, null, password);
		restApiClient.createUser(userEntity);
	}
	
	public static RestApiClient getClient() {
		return new RestApiClient(Session.getXmppIp(), openFireRestPort, new AuthenticationToken(openFireAuthToken));
	}

	public static int getOpenFireRestPort() {
		return openFireRestPort;
	}

	public static void setOpenFireRestPort(int openFireRestPort) {
		RestApiCli.openFireRestPort = openFireRestPort;
	}

	public static String getOpenFireAuthToken() {
		return openFireAuthToken;
	}

	public static void setOpenFireAuthToken(String openFireAuthToken) {
		RestApiCli.openFireAuthToken = openFireAuthToken;
	}

}
