package br.com.soapboxrace.xmpp.offline;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class XmppTalk
{
    private Socket socket;
    private BufferedReader reader;
    private BufferedWriter writer;
    private int personaId;

    public XmppTalk(Socket socket)
    {
        this.socket = socket;
        setReaderWriter();
    }

    public void setSocket(Socket socket)
    {
        this.socket = socket;
        setReaderWriter();
    }

    public Socket getSocket()
    {
        return this.socket;
    }

    private void setReaderWriter()
    {
        try {
            this.reader = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            this.writer = new BufferedWriter(new OutputStreamWriter(this.socket.getOutputStream()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String read()
    {
        String msg = null;
        char[] buffer = new char[' '];
        int charsRead = 0;
        try {
            if ((charsRead = this.reader.read(buffer)) != -1) {
                msg = new String(buffer).substring(0, charsRead);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return msg;
    }

    public void write(String msg)
    {
        try {
            this.writer.write(msg);
            this.writer.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getPersonaId()
    {
        return this.personaId;
    }

    public void setPersonaId(int personaId)
    {
        this.personaId = personaId;
    }
}