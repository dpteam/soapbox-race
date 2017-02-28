package br.com.soapboxrace.engine;

import br.com.soapboxrace.definition.ServerExceptions;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Announcements extends Router
{
    public void handle() throws ServerExceptions.EngineException
    {
        String fileName = getTarget().split("/")[2];
        
        if (!fileName.endsWith(".jpg"))
            throw new ServerExceptions.EngineException("No.");
        
        Path path = Paths.get("announcements/".concat(fileName));
        File file = path.toFile();
        
        if (!file.exists())
            throw new ServerExceptions.EngineException("Unknown announcement!");

        try {
            byte[] bytes = Files.readAllBytes(path);
            
            getResponse().setContentLength(bytes.length);
            getResponse().getOutputStream().write(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
