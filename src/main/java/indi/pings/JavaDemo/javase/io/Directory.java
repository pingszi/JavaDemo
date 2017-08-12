package indi.pings.JavaDemo.javase.io;

import java.io.*;
import java.util.*;
import java.util.regex.*;


/**
 * @Description: I/O实用工具类
 * @author ping 
 * @date 2014年9月2日
 * @version V1.0
 */
public final class Directory {
    /**
     * @Description: 返回指定目录下符合条件的文件数组
     * @param dir
     * @param regex
     * @return File[]
     * @throws
     */
	public static File[] local(File dir, final String regex){
		return  dir.listFiles(new FilenameFilter() {
			
			private Pattern pattern = Pattern.compile(regex);
			@Override
			public boolean accept(File dir, String name) {
				return pattern.matcher(new File(name).getName()).matches();
			}
		});
	}
	
	/**
	 * 
	 * @Description: 返回指定目录下符合条件的文件数组
	 * @param path
	 * @param regex
	 * @return File[]
	 * @throws
	 */
	public static File[] local(String path, final String regex){
		return local(new File(path), regex);
	}
	
	public static class TreeInfo implements Iterable<File>{
		public List<File> files = new ArrayList<>();
		public List<File> dirs = new ArrayList<>();
		
		@Override
		public Iterator<File> iterator(){
			return files.iterator();
		}
		
		void addAll(TreeInfo other){
			files.addAll(other.files);
			dirs.addAll(other.dirs);
		}
		
		public String toString(){
			return "dirs: " + dirs + "\n\nfiles: " + files;
		}
		
		/**
		 * @Description: 获取指定目录及其子目录所有的符合条件的文件
		 * @param start
		 * @param regex
		 * @return TreeInfo
		 * @throws
		 */
		public static TreeInfo walk(String start, String regex){
			return recurseDirs(new File(start), regex);
		}
		
		/**
		 * @Description: 获取指定目录及其子目录所有的符合条件的文件
		 * @param start
		 * @param regex
		 * @return TreeInfo
		 * @throws
		 */
		public static TreeInfo walk(File start, String regex){
			return recurseDirs(start, regex);
		}
		
		/**
		 * @Description: 获取指定目录及其子目录所有的文件
		 * @param start
		 * @return TreeInfo
		 * @throws
		 */
		public static TreeInfo walk(File start){
			return recurseDirs(start, ".*");
		}
		
		/**
		 * @Description: 获取指定目录及其子目录所有的文件
		 * @param start
		 * @return TreeInfo
		 * @throws
		 */
		public static TreeInfo walk(String start){
			return recurseDirs(new File(start), ".*");
		}
		
		static TreeInfo recurseDirs(File startDir, String regex){
			TreeInfo result = new TreeInfo();
			for(File item : startDir.listFiles()){
				if(item.isDirectory()){
					result.dirs.add(item);
					result.addAll(recurseDirs(item, regex));
				}else
					if(item.getName().matches(regex))
						result.files.add(item);
			}
			return result;
		}
	}
}
