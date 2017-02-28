package br.com.soapboxrace.xmpp.offline;

class XmppHandShake
{
    private XmppTalk xmppTalk;
    private int pkgCount = 0;

    XmppHandShake(XmppTalk xmppTalk)
    {
        this.xmppTalk = xmppTalk;
        handShakeBeforeSsl();
        handShakeAfterSsl();
    }

    private void handShakeBeforeSsl()
    {
        String[] packets = new String[2];
        packets[0] = "<stream:stream xmlns='jabber:client' xml:lang='en' xmlns:stream='http://etherx.jabber.org/streams' from='192.168.6.2' id='174159513' version='1.0'><stream:features><starttls xmlns='urn:ietf:params:xml:ns:xmpp-tls'/></stream:features>";
        packets[1] = "<proceed xmlns='urn:ietf:params:xml:ns:xmpp-tls'/>";
        do {
            this.xmppTalk.read();
            this.xmppTalk.write(packets[this.pkgCount]);
            this.pkgCount += 1;
        } while (this.pkgCount < packets.length);
//        TlsWrapper.wrapXmppTalk(this.xmppTalk);
        this.pkgCount = 0;
    }

    private void handShakeAfterSsl()
    {
        int personaId = 0;
        String[] packets = new String[4];
        packets[0] = "<stream:stream xmlns='jabber:client' xml:lang='en' xmlns:stream='http://etherx.jabber.org/streams' from='192.168.6.2' id='5000000000000A' version='1.0'><stream:features/>";
        packets[1] = "";
        packets[2] = "<iq id='EA-Chat-2' type='result' xml:lang='en'/>";
        packets[3] = "";
        do {
            String read = this.xmppTalk.read();
            if (this.pkgCount == 1) {
                int start = read.indexOf("<username>nfsw.");
                read = read.substring(start);
                read = read.replaceAll("\\D+", "");
                personaId = Integer.valueOf(read);
                packets[1] = ("<iq id='EA-Chat-1' type='result' xml:lang='en'><query xmlns='jabber:iq:auth'><username>nfsw." + personaId + "</username><password/><digest/><resource/><clientlock xmlns='http://www.jabber.com/schemas/clientlocking.xsd'/></query></iq>");
            }
            this.xmppTalk.write(packets[this.pkgCount]);
            this.pkgCount += 1;
        } while (this.pkgCount < 3);
        for (int i = 0; i < 3; i++) {
            this.xmppTalk.read();
        }
        packets[3] = ("<presence from='channel.en__1@conference.192.168.6.2' to='nfsw." + personaId + "@192.168.6.2/EA-Chat' type='error'><error code='401' type='auth'><not-authorized xmlns='urn:ietf:params:xml:ns:xmpp-stanzas'/></error><x xmlns='http://jabber.org/protocol/muc'/></presence>");

        this.xmppTalk.write(packets[3]);
        this.xmppTalk.setPersonaId(personaId);
        XmppSrv.addXmppClient(personaId, this.xmppTalk);
    }
}