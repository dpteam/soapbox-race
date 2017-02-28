package br.com.soapboxrace.xmpp.openfire;

import com.google.common.collect.Lists;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class XmppUsers
{
    private static Map<String, List<XmppUser>> userMap = new HashMap<>();

    public static Map<String, List<XmppUser>> getUserMap()
    {
        return userMap;
    }

    public static List<XmppUser> put(String roomId, Long personaId)
    {
        userMap.computeIfAbsent(roomId, rid -> Lists.newArrayList()).add(new XmppUser(personaId));
        
        return userMap.get(roomId);
    }

    public static List<XmppUser> remove(String roomId, Long personaId)
    {
        XmppUser xmppUser = userMap.computeIfAbsent(roomId, rid -> Lists.newArrayList()).stream().filter(user -> user.getPersonaId() == personaId)
                .findFirst()
                .orElse(null);

        if (xmppUser != null) {
            System.out.println("[XMPP] Removing " + personaId + " from " + roomId);
            userMap.get(roomId).remove(xmppUser);
        }
        
        return userMap.get(roomId);
    }
}
