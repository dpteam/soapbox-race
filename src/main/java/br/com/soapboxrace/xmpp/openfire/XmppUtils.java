package br.com.soapboxrace.xmpp.openfire;

import java.util.regex.Pattern;

public class XmppUtils
{
    public static final Pattern FROM_JID_PATTERN = Pattern.compile(
            "channel.(\\w+)__(\\d+)@conference.(.*)/nfsw.(\\d+)"
    );
}
