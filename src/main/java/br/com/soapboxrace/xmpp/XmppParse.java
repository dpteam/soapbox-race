package br.com.soapboxrace.xmpp;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class XmppParse
{
    public static final Pattern FROM_JID_PATTERN = Pattern.compile(
            "channel.(\\w+)__(\\d+)@conference.(.*)/nfsw.(\\d+)"
    );

    public static void main(String[] args)
    {
        String jid = "channel.en__2@conference.192.168.6.2/nfsw.102";

        Matcher matcher = FROM_JID_PATTERN.matcher(jid);
        
        System.out.println(matcher.matches());
        System.out.println(matcher.group(1));
        System.out.println(matcher.group(2));
        System.out.println(matcher.group(4));
    }
}
