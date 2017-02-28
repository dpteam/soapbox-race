package br.com.soapboxrace.engine;

import br.com.soapboxrace.bo.EventsBO;
import br.com.soapboxrace.jaxb.EventsPacketType;
import br.com.soapboxrace.jaxb.TreasureHuntEventSessionType;
import br.com.soapboxrace.jaxb.util.MarshalXML;

import java.util.Random;

public class Events extends Router {

	EventsBO eventsBO = new EventsBO();

	public String availableatlevel() {
		Long userId = getUserId();
		String securityToken = getSecurityToken();
		EventsPacketType availableatlevel = eventsBO.availableatlevel(userId, securityToken);
		return MarshalXML.marshal(availableatlevel);
	}

	public String gettreasurehunteventsession() {
		TreasureHuntEventSessionType huntSession = new TreasureHuntEventSessionType();
		huntSession.setCoinsCollected(0);
		huntSession.setNumCoins(2);
		huntSession.setSeed(new Random().nextInt());
		huntSession.setStreak(730);
		huntSession.setStreakBroken(false);
		
		return MarshalXML.marshal(huntSession);
	}
	
	public String notifycoincollected() {
		Long coins = Long.parseLong(getParam("coins"));
		
		if (coins == 3) {
			
		}
		
		return "";
	}

	public String instancedaccolades() {
		return "";
	}
}
