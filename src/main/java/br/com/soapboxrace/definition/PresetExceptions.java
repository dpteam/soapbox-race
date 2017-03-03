package br.com.soapboxrace.definition;

import br.com.soapboxrace.definition.ServerExceptions.EngineException;

public class PresetExceptions
{
    public static final EngineException PERSONA_NOT_FOUND = new EngineException(
            "Persona not found!",
            EngineExceptionCode.PersonaNotFound
    );
    
    public static final EngineException CAR_NOT_FOUND = new EngineException(
            "Car not found!",
            EngineExceptionCode.CustomCarDoesntExist
    );
    
    public static final EngineException CAR_NOT_OWNED_BY_PERSONA = new EngineException(
            "Car not owned by persona",
            EngineExceptionCode.CarNotOwnedByDriver
    );
    
    public static final EngineException CANT_SELL_LAST_CAR = new EngineException(
            "Can't sell last car",
            EngineExceptionCode.CantDeleteLastOwnedCar
    );
    
    public static final EngineException INVALID_CREDENTIALS = new EngineException(
            "Invalid username/password!",
            EngineExceptionCode.PasswordIncorrect
    );
}
