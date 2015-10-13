package interviews;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class Uber_EventListener实现 {
	// 用一个HashMap<String, List<Event>>来实现
	// 要考虑输入的有效性，然后postEvent是对所有该event的listener都doEvent
	private Map<String, List<Event>> map;
	
	public Uber_EventListener实现() {
		this.map = new HashMap<String, List<Event>>();
	}
	
	public void register(String eventName, Event event) {
		if (map.containsKey(eventName)) {
			map.get(eventName).add(event);
		} else {
			List<Event> events = new ArrayList<Event>();
			events.add(event);
			map.put(eventName, events);
		}
	};

	public void unregister(String eventName, Event event) {
		if (map.containsKey(eventName)) {
			List<Event> list = map.get(eventName);
			if (list.contains(event)) {
				list.remove(event);
			}
		}
	};

	public void postEvent(String eventName, Object Data) {
		List<Event> events = map.get(eventName);
		if (events != null) {
			for (Event e : events) {
				e.doEvent(Data);
			}
		}
	};

}
class Event {
	public void doEvent(Object data) {

	}
}