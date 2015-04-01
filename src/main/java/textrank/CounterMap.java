/**
 * 
 */
package textrank;

import java.io.Serializable;
import java.util.Map;

import com.google.common.collect.Maps;

/**
 * @author Jennifer
 * 
 */
public class CounterMap implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3903452740943758085L;

	private Map<String, Integer> count = Maps.newHashMap();

	public CounterMap() {
	}

	public CounterMap(int capacitySize) {
		count = Maps.newHashMapWithExpectedSize(capacitySize);
	}

	public synchronized void incr(String key) {
		if (count.containsKey(key)) {
			count.put(key, count.get(key) + 1);
		} else {
			count.put(key, 1);
		}
	}
	
	public synchronized int get(String key) {
		Integer value =  count.get(key);
		if (null == value)
			return 0;
		return value;
	}
	
	public Map<String, Integer> countAll() {
		return count;
	}
}
