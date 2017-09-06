package indi.pings.JavaDemo.jvm.GC;

/**
 *********************************************************
 ** @desc  ：  GC什么情况下回收对象
 **          对象在进行可达性分析后发现没有与GC Roots相连接的引用链， 那它将会被第一次标记并且进行一次筛选， 筛选的条件是此对象是否有必要执行finalize()方法。 
 *           当对象没有覆盖finalize()方法， 或者finalize()方法已经被虚拟机调用过， 虚拟机将这两种情况都视为“没有必要执行”,会回收该对象。
 *           如果这个对象被判定为有必要执行finalize() 方法， 那么这个对象将会放置在一个叫做F-Queue的队列之中， 并在稍后由一个由虚拟机自动建立的、 低优先级的Finalizer线程去执行它。 
 *           稍后GC将对F-Queue中的对象进行第二次小规模的标记， 如果对象要在finalize() 中成功拯救自己——只要重新与引用链上的任何一个对象建立关联即可， 譬如把自己（this关键字）
 *           赋值给某个类变量或者对象的成员变量，那在第二次标记时它将被移除出“即将回收” 的集合； 如果对象这时候还没有逃脱， 那基本上它就真的被回收了。 
 **          任何一个对象的finalize()方法都只会被系统自动调用一次， 如果对象面临下一次回收， 它的finalize()方法不会被再次执行                              
 ** @author  Pings                                     
 ** @date    2017年8月25日  
 ** @version v1.0                                                                                  
 * *******************************************************
 */
public class FinalizeEscapeGC {
	
	public static FinalizeEscapeGC SAVE_HOOK = null;

	public void isAlive() {
		System.out.println("yes,i am still alive： ) ");
	}

	@Override
	protected void finalize() throws Throwable {
		super.finalize();
		System.out.println("finalize mehtod executed！ ");
		FinalizeEscapeGC.SAVE_HOOK = this;
	}

	public static void main(String[] args) throws Throwable {
		SAVE_HOOK = new FinalizeEscapeGC();
		//**对象第一次成功拯救自己
		SAVE_HOOK = null;
		System.gc();
		//**因为finalize方法优先级很低， 所以暂停0.5秒以等待它
		Thread.sleep(500);
		if (SAVE_HOOK != null) {
			SAVE_HOOK.isAlive();
		} else {
			System.out.println("no,i am dead： (");
		}
		
		//**下面这段代码与上面的完全相同， 但是这次自救却失败了SAVE_HOOK=null;
		SAVE_HOOK = null;
		System.gc();
		//**因为finalize方法优先级很低， 所以暂停0.5秒以等待它
		Thread.sleep(500);
		if (SAVE_HOOK != null) {
			SAVE_HOOK.isAlive();
		} else {
			System.out.println("no,i am dead： (");
		}
	}
}
/*
finalize mehtod executed！ 
yes,i am still alive： ) 
no,i am dead： (
*/
