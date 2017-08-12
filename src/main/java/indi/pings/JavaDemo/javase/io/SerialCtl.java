package indi.pings.JavaDemo.javase.io;

import java.io.*;

/**
 * @Description: 对象序列化小技巧
 * @author ping 
 * @date 2014年9月3日
 * @version V1.0
 */
public class SerialCtl implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String a;
	private transient String b;
	
	public SerialCtl(String a, String b){
		this.a = a;
		this.b = b;
	}
	
	public String toString(){
		return a + "\n" + b;
	}
	
	/** 
	 * 添加writeObject方法后，会替换默认的序列化方法
	 */
	private void writeObject(ObjectOutputStream stream) throws IOException{
		//**调用默认的序列化操作
		stream.defaultWriteObject();
		//**显示序列化transient的b属性
		stream.writeObject(b);
	}
	
	/** 
	 * 添加readObject方法后，会替换默认的反序列化方法
	 */
	private void readObject(ObjectInputStream stream) throws ClassNotFoundException, IOException{
		//**调用默认的反序列化操作
		stream.defaultReadObject();	
		//**显示反序列化transient的b属性
		b = (String)stream.readObject();
	}
	
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		SerialCtl sc = new SerialCtl("test1", "test2");
		System.out.println("Before:\n" + sc);
		ByteArrayOutputStream buf = new ByteArrayOutputStream();
		ObjectOutputStream o = new ObjectOutputStream(buf);
		o.writeObject(sc);
		
		ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream(buf.toByteArray()));
		SerialCtl sc2 = (SerialCtl) in.readObject();
		System.out.println("After:\n" + sc2);
	}
}
