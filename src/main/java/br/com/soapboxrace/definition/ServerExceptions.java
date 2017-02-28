package br.com.soapboxrace.definition;

public interface ServerExceptions {	
	class PersonaIdMismatchException extends Exception {
		private static final long serialVersionUID = 1L;

		public PersonaIdMismatchException (Long expected, Long result) {
	        super("Expected personaId: " + expected + ", but got personaId: " + result);
	    }
	}
	
	class EngineException extends Exception {
		private static final long serialVersionUID = 1L;

		private EngineExceptionCode code;
		
		public EngineException (String message) {
			super(message);
		}

		public EngineException(String message, EngineExceptionCode code)
		{
			this(message);
			
			this.code = code;
		}

		public EngineException(EngineExceptionCode code)
		{
			this.code = code;
		}

		public EngineExceptionCode getCode()
		{
			return code;
		}
	}
}