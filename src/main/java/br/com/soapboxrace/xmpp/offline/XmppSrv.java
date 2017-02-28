package br.com.soapboxrace.xmpp.offline;

import br.com.soapboxrace.engine.Session;
import br.com.soapboxrace.jaxb.util.MarshalXML;
import br.com.soapboxrace.xmpp.IXmppSender;
import br.com.soapboxrace.xmpp.jaxb.XMPP_MessageType;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ConcurrentHashMap;

public class XmppSrv implements IXmppSender
{
    public static ConcurrentHashMap<Long, XmppTalk> xmppClients = new ConcurrentHashMap();

    public static void addXmppClient(long personaId, XmppTalk xmppClient)
    {
        xmppClients.put(personaId, xmppClient);
    }

    public static void sendMsg(long personaId, String msg)
    {
        if (xmppClients.containsKey(personaId)) {
            XmppTalk xTalk = xmppClients.get(personaId);
            if (xTalk != null) {
                xTalk.write(msg);
            } else {
                System.err.println("xmppClient with the personaId " + personaId + " is attached to a null XmppTalk instance!");
            }
        } else {
            System.err.println("xmppClients doesn't contain personaId " + personaId);
        }
    }

    public static void removeXmppClient(int personaId)
    {
        xmppClients.remove(personaId);
    }

    public static void main(String[] args) throws Exception
    {
        new XmppSrv();
    }

    public XmppSrv()
    {
        XmppSrvRun xmppSrvRun = new XmppSrvRun();
        xmppSrvRun.start();
    }

    public static XmppTalk get(Long personaId)
    {
        return xmppClients.get(personaId);
    }

    @Override
    public void send(String msg, Long to)
    {
        XMPP_MessageType message = new XMPP_MessageType();
        message.setToPersonaId(to);
        message.setBody(msg);
        message.setFrom(String.format("nfsw.engine.engine@conference.%s/EA_Chat", Session.getXmppIp()));
        message.setId("JN_1337");
        message.setSubject(SubjectCalc.calculateHash(message.getTo().toCharArray(), message.getBody().toCharArray()));

        sendMsg(to, MarshalXML.marshal(message));
    }

    @Override
    public void send(Object object, Long to)
    {
        send(MarshalXML.marshal(object), to);
    }

    @Override
    public void createUpdatePersona(Long id, String password)
    {
    }

    private static class Capitalizer
            extends Thread
    {
        private Socket socket;
        private XmppTalk xmppTalk;

        Capitalizer(Socket socket)
        {
            this.socket = socket;
            this.xmppTalk = new XmppTalk(this.socket);
        }

        @Override
        public void run()
        {
            try {
                String input;
                new XmppHandShake(this.xmppTalk);
                XmppHandler xmppHandler = new XmppHandler(this.xmppTalk);
                while ((input = xmppHandler.read()) != null) {
                    if (input.contains("</stream:stream>")) {
                        xmppTalk.write("</stream:stream>");
                        break;
                    }
                }
            } finally {
                try {
                    this.socket.close();
                } catch (IOException e) {
                    System.out.println("Couldn't close a socket, what's going on?");
                }
                XmppSrv.removeXmppClient(this.xmppTalk.getPersonaId());
            }
        }
    }

    private static class XmppSrvRun
            extends Thread
    {
        private XmppSrvRun()
        {
        }

        @Override
        public void run()
        {
            try {
                System.out.println("Xmpp server is running.");
                System.out.println("");
                ServerSocket listener = new ServerSocket(5222);
                try {
                    do {
                        new Capitalizer(listener.accept()).start();
                    } while (true);
                } catch (Throwable var2_3) {
                    listener.close();
                    throw var2_3;
                }
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
        }
    }

}