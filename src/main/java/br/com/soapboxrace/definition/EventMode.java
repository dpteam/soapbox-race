package br.com.soapboxrace.definition;

public enum EventMode
{
	Sprint(9), Circuit(4), Drag(19), Pursuit_SP(12), Pursuit_MP(24), MeetingPlace(22);

	private final int eventModeId;

	public int getEventModeId() {
		return eventModeId;
	}

	EventMode(int eventModeId) {
		this.eventModeId = eventModeId;
	}

	public static EventMode forId(Integer eventModeId) {
		for (EventMode e : EventMode.values()) {
			if (e.getEventModeId() == eventModeId) {
				return e;
			}
		}
		return null;
	}
}