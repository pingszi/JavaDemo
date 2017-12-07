package indi.pings.JavaDemo.jdk8.effective.optional;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Properties;

/**
 *********************************************************
 ** @desc  ：  Optional示例                                           
 ** @author  Pings                                     
 ** @date    2017年12月7日  
 ** @version v1.0                                                                                  
 * *******************************************************
 */
public class OptionalDemo {
	
	/**综合示例，读取配置文件属性，并转换成int，普通版本*/
	public int readDuration(Properties props, String name) {
		String value = props.getProperty(name);
		if (value != null) {
			try {
				int i = Integer.parseInt(value);
				if (i > 0) {
					return i;
				}
			} catch (NumberFormatException nfe) {
			}
		}
		return 0;
	}
	
	/**综合示例，读取配置文件属性，并转换成int，optional版本*/
	public int readDurationOptional(Properties props, String name) {
		return Optional.ofNullable(props.getProperty(name)).flatMap(OptionalDemo::stringToInt).filter(i -> i > 0).orElse(0);
	}
	
	/**异常使用Optional*/
	public static Optional<Integer> stringToInt(String s) {
		try {
			return Optional.of(Integer.parseInt(s));
		} catch (NumberFormatException e) {
			return Optional.empty();
		}
	}

	public static void main(String[] args) {
		Map<String, Object> map = new HashMap<>();
		//map.put("key", "test");
		Optional<Object> value = Optional.ofNullable(map.get("key"));
		System.out.println(value.orElse("empty"));
	}
}
