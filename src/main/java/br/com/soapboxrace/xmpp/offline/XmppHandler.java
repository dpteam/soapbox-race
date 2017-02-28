package br.com.soapboxrace.xmpp.offline;

public class XmppHandler
{
    private XmppTalk xmppTalk;

    public XmppHandler(XmppTalk xmppTalk)
    {
        this.xmppTalk = xmppTalk;
    }

    public String read()
    {
        return this.xmppTalk.read();
    }
}