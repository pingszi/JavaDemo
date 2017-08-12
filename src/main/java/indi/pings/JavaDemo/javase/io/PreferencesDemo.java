package indi.pings.JavaDemo.javase.io;

import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

/**
 * @Description: 保存用户设置
 * @author ping 
 * @date 2014年9月9日
 * @version V1.0
 */
public class PreferencesDemo {
	
	public static void main(String[] args) throws BackingStoreException {
		Preferences prefs = Preferences.userNodeForPackage(PreferencesDemo.class);
		prefs.put("Location", "0z");
		prefs.put("Footwear", "Ruby Slippers");
		prefs.putInt("Companions", 4);
		prefs.putBoolean("Are there witches?", true);
		
		int usageCount = prefs.getInt("UsageCount", 0);
		usageCount++;
		prefs.putInt("UsageCount", usageCount);
		
		for(String key : prefs.keys()){
			System.out.println(key + ": " + prefs.get(key, null));
		}
		
		System.out.println("How many companions does Dorothy have?" + prefs.getInt("Companions", 0));
		
		prefs.clear();
	}
}
