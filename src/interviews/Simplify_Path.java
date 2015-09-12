package interviews;

import java.util.LinkedList;

public class Simplify_Path {
	/**
	 * Given an absolute path for a file (Unix-style), simplify it.
	 * 
	 * For example, path = "/home/", => "/home" path = "/a/./b/../../c/", => "/c"
	 */
	public String simplifyPath(String path) {
        String[] words = path.split("/");
        LinkedList<String> q = new LinkedList<String>();
        for(String s: words){
        	// deal with the case of more than one .
        	// and case multiple slashes together
            if(s.length() == 0 || s.equals(".")) continue;
            // in the case of /../, remove the first, the string we just added
            else if(s.equals("..")){
                if(!q.isEmpty()) q.removeFirst();
            } 
            else q.addFirst(s);
        }
        // all popped case
        if(q.isEmpty()) q.add("");
        StringBuilder sb = new StringBuilder();
        // remove from the beginning, that is, the oldest end
        while(!q.isEmpty()){
            sb.append("/" + q.removeLast());
        }
        return sb.toString();
    }
}
