package br.com.soapboxrace.engine;

import br.com.soapboxrace.achievements.AchievementUtils;
import br.com.soapboxrace.dao.factory.DaoFactory;
import br.com.soapboxrace.dao.factory.IAchievementDao;
import br.com.soapboxrace.dao.factory.IPersonaAchievementDao;
import br.com.soapboxrace.dao.factory.IPersonaDao;
import br.com.soapboxrace.definition.PresetExceptions;
import br.com.soapboxrace.definition.ServerExceptions;
import br.com.soapboxrace.jaxb.announcements.LoginAnnouncementContext;
import br.com.soapboxrace.jaxb.announcements.LoginAnnouncementDefinitionType;
import br.com.soapboxrace.jaxb.announcements.LoginAnnouncementType;
import br.com.soapboxrace.jaxb.announcements.LoginAnnouncementsDefinitionType;
import br.com.soapboxrace.jaxb.util.MarshalXML;
import br.com.soapboxrace.jpa.PersonaEntity;
import br.com.soapboxrace.xmpp.IXmppSender;
import br.com.soapboxrace.xmpp.XmppFactory;
import br.com.soapboxrace.xmpp.openfire.RestApiCli;
import org.igniterealtime.restclient.entity.GroupEntity;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;

public class Default extends Router {

	public String getfriendlistfromuserid() {
		return "<PersonaFriendsList>" +
				"<friendPersona>" +
				"<FriendPersona>" +
				"<iconIndex>25</iconIndex>" +
				"<level>60</level>" +
				"<name>DEVNilzao</name>" +
				"<originalName>Nilzao</originalName>" +
				"<personaId>100</personaId>" +
				"<presence>1</presence>" +
				"<socialNetwork>nilzao_thinker</socialNetwork>" +
				"<userId>1</userId>" +
				"</FriendPersona>" +
				"</friendPersona>" +
				"</PersonaFriendsList>";
	}

	public String systeminfo() {
		String xmppIp = Session.getXmppIp();
		
//		String announcementBlock = String
//				.format("&lt;response status='1' ticket='0'&gt;&lt;ChatBroadcast&gt;&lt;ChatBlob&gt;&lt;FromName&gt;System&lt;/FromName&gt;"
//						+ "&lt;FromPersonaId&gt;0&lt;/FromPersonaId&gt;&lt;FromUserId&gt;0&lt;/FromUserId&gt;&lt;Message&gt;%s"
//						+ "&lt;/Message&gt;&lt;ToId&gt;0&lt;/ToId&gt;&lt;Type&gt;2&lt;/Type&gt;&lt;/ChatBlob&gt;&lt;/ChatBroadcast&gt;&lt;/response&gt;", 
//						"Hello there!");
//
//		XmppFactory.getXmppSenderInstance(Session.getXmppServerType()).send(announcementBlock, 102L);
		
		String timeString = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSSSSS+00:00").format(new Date());
		timeString = timeString.replace(" ", "T");
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("<SystemInfo>\n");
		stringBuilder.append("  <Branch>debug</Branch>\n");
		stringBuilder.append("  <ChangeList>620384</ChangeList>\n");
		stringBuilder.append("  <ClientVersion>637</ClientVersion>\n");
		stringBuilder.append("  <ClientVersionCheck>true</ClientVersionCheck>\n");
		stringBuilder.append("  <Deployed>08/20/2013 11:24:40</Deployed>\n");
		stringBuilder.append("  <EntitlementsToDownload>true</EntitlementsToDownload>\n");
		stringBuilder.append("  <ForcePermanentSession>true</ForcePermanentSession>\n");
		stringBuilder.append("  <JidPrepender>nfsw</JidPrepender>\n");
		stringBuilder.append("  <LauncherServiceUrl>http://10.100.15.202/LauncherService/onlineconfig.aspx</LauncherServiceUrl>\n");
		stringBuilder.append("  <NucleusNamespace>nfsw-live</NucleusNamespace>\n");
		stringBuilder.append("  <NucleusNamespaceWeb>nfs_web</NucleusNamespaceWeb>\n");
		stringBuilder.append("  <PersonaCacheTimeout>900</PersonaCacheTimeout>\n");
		stringBuilder.append("  <PortalDomain/>\n");
		stringBuilder.append("  <PortalSecureDomain/>\n");
		stringBuilder.append("  <PortalStoreFailurePage/>\n");
		stringBuilder.append("  <PortalTimeOut>60000</PortalTimeOut>\n");
		stringBuilder.append("  <ShardName>US</ShardName>\n");
		stringBuilder.append("  <Time>");
		// stringBuilder.append("2010-01-01T12:00:00.0000000+00:00");
		stringBuilder.append(timeString);
		stringBuilder.append("</Time>\n");
		stringBuilder.append("  <Version>1599</Version>\n");
		stringBuilder.append("</SystemInfo>\n");
		stringBuilder.append("");
		String xmlTmp = stringBuilder.toString();
		return xmlTmp;
	}

	public String carclasses() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("<ArrayOfCarClass>\n");
		stringBuilder.append("  <CarClass>\n");
		stringBuilder.append("    <CarClassHash>-2142411446</CarClassHash>\n");
		stringBuilder.append("    <MaxRating>999</MaxRating>\n");
		stringBuilder.append("    <MinRating>750</MinRating>\n");
		stringBuilder.append("  </CarClass>\n");
		stringBuilder.append("  <CarClass>\n");
		stringBuilder.append("    <CarClassHash>-406473455</CarClassHash>\n");
		stringBuilder.append("    <MaxRating>599</MaxRating>\n");
		stringBuilder.append("    <MinRating>500</MinRating>\n");
		stringBuilder.append("  </CarClass>\n");
		stringBuilder.append("  <CarClass>\n");
		stringBuilder.append("    <CarClassHash>-405837480</CarClassHash>\n");
		stringBuilder.append("    <MaxRating>749</MaxRating>\n");
		stringBuilder.append("    <MinRating>600</MinRating>\n");
		stringBuilder.append("  </CarClass>\n");
		stringBuilder.append("  <CarClass>\n");
		stringBuilder.append("    <CarClassHash>415909161</CarClassHash>\n");
		stringBuilder.append("    <MaxRating>399</MaxRating>\n");
		stringBuilder.append("    <MinRating>250</MinRating>\n");
		stringBuilder.append("  </CarClass>\n");
		stringBuilder.append("  <CarClass>\n");
		stringBuilder.append("    <CarClassHash>872416321</CarClassHash>\n");
		stringBuilder.append("    <MaxRating>249</MaxRating>\n");
		stringBuilder.append("    <MinRating>0</MinRating>\n");
		stringBuilder.append("  </CarClass>\n");
		stringBuilder.append("  <CarClass>\n");
		stringBuilder.append("    <CarClassHash>1866825865</CarClassHash>\n");
		stringBuilder.append("    <MaxRating>499</MaxRating>\n");
		stringBuilder.append("    <MinRating>400</MinRating>\n");
		stringBuilder.append("  </CarClass>\n");
		stringBuilder.append("</ArrayOfCarClass>");
		String xmlTmp = stringBuilder.toString();
		return xmlTmp;
	}

	public String getrebroadcasters() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("<ArrayOfUdpRelayInfo>");
		stringBuilder.append("<UdpRelayInfo>");
		stringBuilder.append("<Host>");
		stringBuilder.append(Session.getFreeRoamUdpIp());
		stringBuilder.append("</Host>");
		stringBuilder.append("<Port>");
		stringBuilder.append(Session.getFreeRoamUdpPort());
		stringBuilder.append("</Port>");
		stringBuilder.append("</UdpRelayInfo>");
		stringBuilder.append("</ArrayOfUdpRelayInfo>");
		String xmlTmp = stringBuilder.toString();
		return xmlTmp;
	}

	public String getregioninfo() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("<RegionInfo>\n");
		stringBuilder.append("  <CountdownProposalInMilliseconds>3000</CountdownProposalInMilliseconds>\n");
		stringBuilder.append("  <DirectConnectTimeoutInMilliseconds>1000</DirectConnectTimeoutInMilliseconds>\n");
		stringBuilder.append("  <DropOutTimeInMilliseconds>15000</DropOutTimeInMilliseconds>\n");
		stringBuilder.append("  <EventLoadTimeoutInMilliseconds>30000</EventLoadTimeoutInMilliseconds>\n");
		stringBuilder.append("  <HeartbeatIntervalInMilliseconds>1000</HeartbeatIntervalInMilliseconds>\n");
		stringBuilder.append("  <UdpRelayBandwidthInBps>9600</UdpRelayBandwidthInBps>\n");
		stringBuilder.append("  <UdpRelayTimeoutInMilliseconds>60000</UdpRelayTimeoutInMilliseconds>\n");
		stringBuilder.append("</RegionInfo>");
		String xmlTmp = stringBuilder.toString();
		return xmlTmp;
	}

	public String loginAnnouncements() {
		LoginAnnouncementsDefinitionType loginAnnouncementsDefinition = new LoginAnnouncementsDefinitionType();
		loginAnnouncementsDefinition.setImagesPath("http://192.168.6.2:1337/announcements");

		LoginAnnouncementDefinitionType announcement = new LoginAnnouncementDefinitionType();
		announcement.setContext(LoginAnnouncementContext.NotApplicable);
		announcement.setId(1337);
		announcement.setImageChecksum(-1);
		announcement.setImageUrl("snap.jpg");
		announcement.setType(LoginAnnouncementType.ImageOnly);
		
		loginAnnouncementsDefinition.setAnnouncements(Collections.singletonList(announcement));
		
		return MarshalXML.marshal(loginAnnouncementsDefinition);
	}

	public String getsocialsettings() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("<SocialSettings>\n");
		stringBuilder.append("  <AppearOffline>false</AppearOffline>\n");
		stringBuilder.append("  <DeclineGroupInvite>0</DeclineGroupInvite>\n");
		stringBuilder.append("  <DeclineIncommingFriendRequests>false</DeclineIncommingFriendRequests>\n");
		stringBuilder.append("  <DeclinePrivateInvite>0</DeclinePrivateInvite>\n");
		stringBuilder.append("  <HideOfflineFriends>false</HideOfflineFriends>\n");
		stringBuilder.append("  <ShowNewsOnSignIn>false</ShowNewsOnSignIn>\n");
		stringBuilder.append("  <ShowOnlyPlayersInSameChatChannel>false</ShowOnlyPlayersInSameChatChannel>\n");
		stringBuilder.append("</SocialSettings>");
		String xmlTmp = stringBuilder.toString();
		return xmlTmp;
	}

	public String getusersettings() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("<User_Settings>\n");
		stringBuilder.append("  <CarCacheAgeLimit>600</CarCacheAgeLimit>\n");
		stringBuilder.append("  <IsRaceNowEnabled>true</IsRaceNowEnabled>\n");
		stringBuilder.append("  <MaxCarCacheSize>250</MaxCarCacheSize>\n");
		stringBuilder.append("  <MinRaceNowLevel>2</MinRaceNowLevel>\n");
		stringBuilder.append("  <VoipAvailable>false</VoipAvailable>\n");
		stringBuilder.append("  <activatedHolidaySceneryGroups>\n");
		stringBuilder.append("    <string>SCENERY_GROUP_NEWYEARS</string>\n");
		stringBuilder.append("  </activatedHolidaySceneryGroups>\n");
		stringBuilder.append("  <activeHolidayIds>\n");
		stringBuilder.append("    <long>5</long>\n");
		stringBuilder.append("  </activeHolidayIds>\n");
		stringBuilder.append("  <disactivatedHolidaySceneryGroups>\n");
		stringBuilder.append("    <string>SCENERY_GROUP_NEWYEARS_DISABLE</string>\n");
		stringBuilder.append("  </disactivatedHolidaySceneryGroups>\n");
		stringBuilder.append("  <firstTimeLogin>false</firstTimeLogin>\n");
		stringBuilder.append("  <maxLevel>60</maxLevel>\n");
		stringBuilder.append("  <starterPackApplied>false</starterPackApplied>\n");
		stringBuilder.append("  <userId>" + getUserId() + "</userId>\n");
		stringBuilder.append("</User_Settings>");
		String xmlTmp = stringBuilder.toString();
		return xmlTmp;
	}

	public String getblockeduserlist() {
		return "<ArrayOflong/>";
	}

	public String getblockersbyusers() {
		return "<ArrayOflong/>";
	}

	public String heartbeat() {
		return "";
	}

	public String newsArticles() {
		return "<ArrayOfNewsArticleTrans />";
	}

	public String getsocialnetworkinfo() {
		return "<SocialNetworkInfo />";
	}

	public String setsocialsettings() {
		return "";
	}

	public String addfriendrequest()
	{
		return "";
	}

	public String achievementtrigger() {
	    Long personaId = Long.valueOf(getParam("personaId"));
        IAchievementDao achievementDao = DaoFactory.getAchievementDao();
        IPersonaAchievementDao personaAchievementDao = DaoFactory.getPersonaAchievementDao();
        IPersonaDao personaDao = DaoFactory.getPersonaDao();
        IXmppSender xmppSender = XmppFactory.getXmppSenderInstance(Session.getXmppServerType());

        PersonaEntity personaEntity = personaDao.findById(personaId);
        
        if (personaEntity == null)
            return "Not found";
        
        personaAchievementDao.update(
                personaEntity,
                achievementDao.findByIdentifier("achievement_ACH_USE_NOS"),
                245L,
                updateInfo -> AchievementUtils.broadcastProgress(personaEntity, updateInfo.getPersonaAchievement(), updateInfo.getRanks(), updateInfo.getScore())
        );
        
		return "";
	}
}