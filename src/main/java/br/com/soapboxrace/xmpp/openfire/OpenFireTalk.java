package br.com.soapboxrace.xmpp.openfire;

import br.com.soapboxrace.dao.factory.DaoFactory;
import br.com.soapboxrace.engine.Session;
import br.com.soapboxrace.jaxb.util.MarshalXML;
import br.com.soapboxrace.jaxb.util.UnmarshalXML;
import br.com.soapboxrace.jpa.PersonaEntity;
import br.com.soapboxrace.xmpp.XmppFactory;
import br.com.soapboxrace.xmpp.XmppMessage;
import br.com.soapboxrace.xmpp.XmppParse;
import br.com.soapboxrace.xmpp.jaxb.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.List;
import java.util.regex.Matcher;
import java.util.stream.Collectors;

public class OpenFireTalk {

	private Socket socket;
	private BufferedReader reader;
	private BufferedWriter writer;

	public OpenFireTalk(Socket socket) {
		this.socket = socket;
		setReaderWriter();
	}

	public void setSocket(Socket socket) {
		this.socket = socket;
		setReaderWriter();
	}

	public Socket getSocket() {
		return socket;
	}

	private void setReaderWriter() {
		try {
			reader = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
			writer = new BufferedWriter(new OutputStreamWriter(this.socket.getOutputStream()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String read() {
		String msg = null;
		char[] buffer = new char[8192];
		int charsRead = 0;
		try {
			if ((charsRead = reader.read(buffer)) != -1) {
				msg = new String(buffer).substring(0, charsRead);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if (msg != null) {
			System.out.println("S->C [" + msg + "]");
			if (msg.contains("<ping xmlns=\"urn:xmpp:ping\"/>")) {
				XMPP_IQPingType openfirePing = (XMPP_IQPingType) UnmarshalXML.unMarshal(msg, new XMPP_IQPingType());
				write(MarshalXML.marshal(new XMPP_IQPongType(openfirePing.getId())));
			} else if (msg.startsWith("<presence to=\"") && !msg.contains("<status code=\"")) {
				XMPP_PresenceType presenceType = (XMPP_PresenceType) UnmarshalXML.unMarshal(msg, new XMPP_PresenceType()); 
				
				if (presenceType.getToJid().equals(String.format("nfsw.engine.engine@%s/EA_Chat", Session.getXmppIp()))
						&& presenceType.getFromJid().startsWith("channel.")) {
					String from = presenceType.getFromJid();
//					System.out.println(from);
					Matcher matcher = XmppParse.FROM_JID_PATTERN.matcher(from);
					
					if (matcher.matches()) {
                        final String roomId = matcher.group(1) + "__" + matcher.group(2);
                        final XMPP_XType xmpp_xType = presenceType.getX();
                        final XMPP_ItemType xmpp_itemType = xmpp_xType.getItemTypes().get(0);
                        final String[] parts = xmpp_itemType.getJid().split("\\.");
                        final Long personaId = Long.valueOf(parts[1].substring(0, parts[1].indexOf('@')));

                        PersonaEntity personaEntity = DaoFactory.getPersonaDao().findById(personaId);

                        if (personaEntity != null) {
                            String roomMod = roomId.replace("__", " ");
                            
                            if (presenceType.getType() == null) {
                                System.out.println("[XMPP] " + personaEntity.getName() + " joined " + roomMod + ".");
                                
                                List<XmppUser> others = XmppUsers.put(roomId, personaId).stream().filter(xmppUser -> xmppUser.getPersonaId() != personaId)
                                        .collect(Collectors.toList());
                                System.out.println("[XMPP] Other users in " + roomMod + ": " + others.size());
                                
                                if (others.size() >= 1) {
                                    List<PersonaEntity> otherPersonas = others.stream().map(XmppUser::getPersonaId).map(DaoFactory.getPersonaDao()::findById)
                                            .collect(Collectors.toList());
                                    
                                    System.out.println("[XMPP] Other user names: " +
                                                    otherPersonas.stream().map(PersonaEntity::getName).collect(Collectors.joining(", "))
                                    );
                                    
                                    otherPersonas.forEach(otherPersona -> XmppFactory.getXmppSenderInstance(Session.getXmppServerType())
                                                    .send(XmppMessage.createSystemMessage(personaEntity.getName() + " joined the channel."), otherPersona.getId()));
                                    String toClient = String.format(
                                            "There %s %d %s in the channel.",
                                            others.size() == 1 ? "is" : "are",
                                            others.size(),
                                            others.size() == 1 ? "other player" : "other players"
                                    );

                                    XmppFactory.getXmppSenderInstance(Session.getXmppServerType())
                                            .send(XmppMessage.createSystemMessage(toClient), personaId);
                                }
                            } else if (presenceType.getType().equals("unavailable")) {
                                System.out.println("[XMPP] " + personaEntity.getName() + " left " + roomMod + ".");

                                List<XmppUser> users = XmppUsers.remove(roomId, personaId);
                                
                                System.out.println("[XMPP] Remaining users in " + roomMod + ": " + users.size());
                                
                                if (!users.isEmpty()) {
                                    users.forEach(xmppUser -> XmppFactory.getXmppSenderInstance(Session.getXmppServerType())
                                            .send(XmppMessage.createSystemMessage(personaEntity.getName() + " left the channel."), xmppUser.getPersonaId()));
                                }
                            }
                            
//                            if (presenceType.getType() == null) {
//                                System.out.println("[Joined channel " + roomId + "]");
//                                List<XmppUser> users = XmppUsers.put(roomId, personaId);
//
//                                if (users.size() > 1) {
//                                    List<XmppUser> others = users.stream().filter(xmppUser -> xmppUser.getPersonaId() != personaId.intValue()).collect(Collectors.toList());
//                                    String toOthers = String.format(
//                                            "%s joined the channel.",
//                                            personaEntity.getName()
//                                    );
//
//                                    others.forEach(xmppUser ->
//                                            XmppFactory.getXmppSenderInstance(Session.getXmppServerType()).send(XmppMessage.createSystemMessage(toOthers), personaId));
//
//                                    String toClient = String.format(
//                                            "There %s %d %s in the channel.",
//                                            others.size() == 1 ? "is" : "are",
//                                            others.size(),
//                                            others.size() == 1 ? "other player" : "other players"
//                                    );
//
//                                    XmppFactory.getXmppSenderInstance(Session.getXmppServerType()).send(XmppMessage.createSystemMessage(toClient), personaId);
//                                }
//                            } else if (presenceType.getType() != null && presenceType.getType().equals("unavailable")) {
//                                System.out.println("[Left channel " + roomId + "]");
//                                List<XmppUser> remaining = XmppUsers.remove(roomId, personaId);
//
//                                if (remaining.size() > 1) {
//                                    List<XmppUser> others = remaining.stream().filter(xmppUser -> xmppUser.getPersonaId() != personaId.intValue())
//                                            .collect(Collectors.toList());
//                                    String toOthers = String.format(
//                                            "%s left the channel.",
//                                            personaEntity.getName()
//                                    );
//
//                                    others.forEach(xmppUser ->
//                                            XmppFactory.getXmppSenderInstance(Session.getXmppServerType()).send(XmppMessage.createSystemMessage(toOthers), personaId));
//                                }
//                            }
                        }   
                    }
				}
			}
		}
		
		return msg;
	}

	public void write(String msg) {
		try {
			char[] cbuf = new char[msg.length()];
			msg.getChars(0, msg.length(), cbuf, 0);
			System.out.println("C->S [" + msg + "]");
			writer.write(cbuf);
			writer.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void startReader() {
		XmppTalkReader xmppTalkReader = new XmppTalkReader(this);
		xmppTalkReader.start();
	}

	private static class XmppTalkReader extends Thread {

		private OpenFireTalk xmppTalk;

		public XmppTalkReader(OpenFireTalk xmppTalk) {
			this.xmppTalk = xmppTalk;
		}

		@Override
		public void run() {
			while (true) {
				xmppTalk.read();
			}
		}

	}

}
