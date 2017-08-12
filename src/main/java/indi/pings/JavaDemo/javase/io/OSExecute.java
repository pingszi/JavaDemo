package indi.pings.JavaDemo.javase.io;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * @Description: 执行系统命令
 * @author ping 
 * @date 2014年9月3日
 * @version V1.0
 */
public class OSExecute {
    
	/**
	 * @Description: 执行系统命令
	 * @param command
	 * @return void
	 * @throws
	 */
	public static void command(String command){
		boolean err = false;
		try{
			Process process = new ProcessBuilder(command.split(" ")).start();
			BufferedReader results  = new BufferedReader(new InputStreamReader(process.getInputStream()));
			String s;
			while((s = results.readLine()) != null){
				System.out.println(s);
			}
			BufferedReader errors  = new BufferedReader(new InputStreamReader(process.getErrorStream()));
			while((s = errors.readLine()) != null){
				System.err.println(s);
				err = true;
			}
		}catch(Exception e){
			if(command.startsWith("CMD /C"))
				command("CMD /C " + command);
			else
				throw new RuntimeException(e);
		}
		if(err)
			throw new RuntimeException("Errors executing" + command);
	}
}
