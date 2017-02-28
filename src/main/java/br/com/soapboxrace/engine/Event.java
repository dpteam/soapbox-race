package br.com.soapboxrace.engine;

import br.com.soapboxrace.bo.EventBO;
import br.com.soapboxrace.dao.factory.DaoFactory;
import br.com.soapboxrace.dao.factory.IEventDataDao;
import br.com.soapboxrace.dao.factory.IPersonaDao;
import br.com.soapboxrace.jaxb.events.AbstractArbitrationPacket;
import br.com.soapboxrace.jaxb.events.packets.DragArbitrationPacket;
import br.com.soapboxrace.jaxb.events.packets.PursuitArbitrationPacket;
import br.com.soapboxrace.jaxb.events.packets.RouteArbitrationPacket;
import br.com.soapboxrace.jaxb.events.packets.TeamEscapeArbitrationPacket;
import br.com.soapboxrace.jaxb.util.MarshalXML;
import br.com.soapboxrace.jaxb.util.UnmarshalXML;

public class Event extends Router {

	private EventBO eventBO = new EventBO();
	private IPersonaDao personaDao = DaoFactory.getPersonaDao();
	private IEventDataDao eventDataDao = DaoFactory.getEventDataDao();

	public String launched() {
		Long eventSessionId = Long.valueOf(getParam("eventSessionId"));
		setSessionEntry("EventSessionId", eventSessionId);
		return eventBO.launched(getUserId(), eventSessionId);
	}

	public String arbitration() {
		if (getHttpSessionVo(getUserId()).getEventSessionId() == 0L)
			return "";
		String input = readInputStream();
		AbstractArbitrationPacket packet;
		
		if (input.contains("Pursuit")) {
			packet = (PursuitArbitrationPacket) UnmarshalXML.unMarshal(
					input,
					new PursuitArbitrationPacket()
			);
		} else if (input.contains("Route")) {
			packet = (RouteArbitrationPacket) UnmarshalXML.unMarshal(
					input,
					new RouteArbitrationPacket()
			);
		} else if (input.contains("Drag")) {
			packet = (DragArbitrationPacket) UnmarshalXML.unMarshal(
					input,
					new DragArbitrationPacket()
			);
		} else if (input.contains("TeamEscape")) {
			packet = (TeamEscapeArbitrationPacket) UnmarshalXML.unMarshal(
					input,
					new TeamEscapeArbitrationPacket()
			);
		} else {
			throw new IllegalArgumentException("Unknown pac");
		}
		
		Object arbitration = eventBO.arbitration(getUserId(), packet);
		if (arbitration == null)
			return "";
		setSessionEntry("EventSessionId", 0L);
		return MarshalXML.marshal(arbitration);
	}

	public String bust() {
		final Long loggedPersonaId = getLoggedPersonaId();
		final Long eventSessionId = Long.valueOf(getParam("eventSessionId"));
		
		return MarshalXML.marshal(
				eventBO.busted(
						eventDataDao.findByEventSessionIdAndPersonaId(eventSessionId, loggedPersonaId),
						personaDao.findById(loggedPersonaId),
						eventSessionId,
						(PursuitArbitrationPacket) UnmarshalXML.unMarshal(readInputStream(), new PursuitArbitrationPacket())
				)
		);
//		PursuitEventResultType bust = (PursuitEventResultType) eventBO.bust(getLoggedPersonaId());
//		return MarshalXML.marshal(bust);
	}

	public String abort() {
		return "";
	}
}