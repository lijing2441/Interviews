package interviews;

public class Flatten_json_to_A_List_of_Map {
	/**
	 * Convert json to a list of map
	 * {
	    "uuid" : "abc",
	    "properties" : {
	        "sessionName" : "Test",
	        "waypoints" : [
	        	{"uuid" : "def", "properties": {"latitue": 3}}
	     	]
	     }
	   }
	   转化成：List<Map<String, Object>> =>
	   例子的输出：{
	      {"uuid": "abc", "properties": {"sessionName": "Test session name", "waypoints": ["def"]}},

  		  {"uuid": "def", "properties": {"latitude": 3}},
	 */
	
}
