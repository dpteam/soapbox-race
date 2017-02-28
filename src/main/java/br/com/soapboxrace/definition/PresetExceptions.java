package br.com.soapboxrace.definition;

import br.com.soapboxrace.definition.ServerExceptions.EngineException;

public class PresetExceptions
{
    public static final EngineException PERSONA_NOT_FOUND = new EngineException(
            "Persona not found!",
            EngineExceptionCode.PersonaNotFound
    );
    
    public static final EngineException INVALID_CREDENTIALS = new EngineException(
            "Invalid username/password!",
            EngineExceptionCode.PasswordIncorrect
    );
}
