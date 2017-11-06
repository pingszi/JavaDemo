package indi.pings.JavaDemo.jvm.javac;

import java.util.EnumSet;

import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.Name;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.util.ElementScanner6;
import javax.tools.Diagnostic.Kind;

/**
 *********************************************************
 ** @desc  ：  程序名称规范检查                                           
 ** @author  Pings                                     
 ** @date    2017年10月20日  
 ** @version v1.0                                                                                  
 * *******************************************************
 */
public class NameChecker {

	private final Messager messager;
	NameCheckScanner nameCheckScanner = new NameCheckScanner();
	
	public NameChecker(ProcessingEnvironment processsingEnv) {
		this.messager = processsingEnv.getMessager();
	}
	
	/**
	 *********************************************************
	 ** @desc ：  对Java程序命名进行检查                                          
	 ** @author Pings                                    
	 ** @date   2017年10月20日                                      
	 ** @param element                                              
	 * *******************************************************
	 */
	public void checkNames(Element element) {
		nameCheckScanner.scan(element);
	}
	
	/**
	 *********************************************************
	 ** @desc  ：  名称检查实现类，将会以Visitor模式访问抽象语法树中的元素                                      
	 ** @author  Pings                                     
	 ** @date    2017年10月20日  
	 ** @version v1.0                                                                                  
	 * *******************************************************
	 */
	private class NameCheckScanner extends ElementScanner6<Void, Void> {

		/**
	     *********************************************************
	     ** @desc  ：  检查java类
	     ** @author  Pings
	     ** @date    2017-10-20
	     ** @version v1.0
	     * *******************************************************
	     */
		@Override
		public Void visitType(TypeElement e, Void p) {
			scan(e.getTypeParameters(), p);
			checkCamelCase(e, true);
			
			return super.visitType(e, p);
		}

		/**
	     *********************************************************
	     ** @desc  ：  检查方法名
	     ** @author  Pings
	     ** @date    2017-10-20
	     ** @version v1.0
	     * *******************************************************
	     */
		@Override
		public Void visitExecutable(ExecutableElement e, Void p) {
			if (e.getKind() == ElementKind.METHOD) {
				Name name = e.getSimpleName();
				
				if(name.contentEquals(e.getEnclosingElement().getSimpleName()))
					messager.printMessage(Kind.WARNING, "一个普通方法" + name + "不应当与类名重复， 避免与构造函数产生混淆", e);
				
				checkCamelCase(e, true);
			}
			
			return super.visitExecutable(e, p);
		}

		/**
	     *********************************************************
	     ** @desc  ：  检查变量名
	     ** @author  Pings
	     ** @date    2017-10-20
	     ** @version v1.0
	     * *******************************************************
	     */
		@Override
		public Void visitVariable(VariableElement e, Void p) {
			if (e.getKind() == ElementKind.ENUM_CONSTANT || e.getConstantValue() != null || heuristicallyConstant(e))
				checkAllCaps(e);
			else 
				checkCamelCase(e, false);
			
			return super.visitVariable(e, p);
		}
		
		/**
		 *********************************************************
		 ** @desc ：  判断一个变量是否是常量                                           
		 ** @author Pings                                    
		 ** @date   2017年10月20日                                      
		 ** @param  e
		 ** @return                                              
		 * *******************************************************
		 */
		private boolean heuristicallyConstant(VariableElement e) {
			if (e.getEnclosingElement().getKind() == ElementKind.INTERFACE)
				return true;
			else if (e.getKind() == ElementKind.FIELD && e.getModifiers().containsAll(EnumSet.of(Modifier.PUBLIC, Modifier.STATIC, Modifier.FINAL))) 
				return true;
			else
				return false;
		}
		
		/**
		 *********************************************************
		 ** @desc ： 检查传入的Element是否符合驼式命名法， 如果不符合， 则输出警告信息                                        
		 ** @author Pings                                    
		 ** @date   2017年10月20日                                      
		 ** @param  e
		 ** @return                                              
		 * *******************************************************
		 */
		private void checkCamelCase(Element e, boolean initialCaps) {
			boolean previousUpper = false;
			boolean conventional = true;
			
			String name = e.getSimpleName().toString();
			int firstCodePoint = name.codePointAt(0);
			
			if (Character.isUpperCase(firstCodePoint)) {
				previousUpper = true;
				
				if (!conventional) {
					messager.printMessage(Kind.WARNING, "名称" + name + "应当以小写字母开头", e);
					return;
				}
			} else if (Character.isLowerCase(firstCodePoint)) {
				if (!conventional) {
					messager.printMessage(Kind.WARNING, "名称" + name + "应当以大写字母开头", e);
					return;
				}
			} else
				conventional = false;
			
			if (conventional) {
				int cp = firstCodePoint;
				
				for (int i = Character.charCount(cp); i < name.length(); i += Character.charCount(cp)) {
					cp = name.codePointAt(i);
					
					if (Character.isUpperCase(cp)) {
						if(previousUpper) {
							conventional = false;
							break;
						}
						
						previousUpper = true;
					} else 
						previousUpper = false;
				} 			
			}
			
			if (!conventional)
				messager.printMessage(Kind.WARNING, "名称" + name + "应当符合驼式命名法（Camel Case Names） ", e);
		}
		
		/**
		 *********************************************************
		 ** @desc ： 大写命名检查，要求第一个字母必须是大写的英文字母，其余部分可以是下划线或大写字母                                        
		 ** @author Pings                                    
		 ** @date   2017年10月20日                                      
		 ** @param  e
		 ** @return                                              
		 * *******************************************************
		 */
		private void checkAllCaps(Element e) {
			String name = e.getSimpleName().toString();
			boolean conventional = true;
			int firstCodePoint = name.codePointAt(0);
			
			if (!Character.isUpperCase(firstCodePoint))
				conventional = false;
			else {
				boolean previousUnderscore = false;
				int cp = firstCodePoint;
				
				for (int i = Character.charCount(cp); i < name.length(); i += Character.charCount(cp)) {
					cp = name.codePointAt(i);
					
					if (cp == (int)'_') {
						if(previousUnderscore) {
							conventional = false;
							break;
						}
						
						previousUnderscore = true;
					} else {
						previousUnderscore = false;
						
						if (!Character.isUpperCase(cp) && !Character.isDigit(cp)) {
							conventional = false;
							break;
						}
					}
				}
			}
			
			if (!conventional)
				messager.printMessage(Kind.WARNING, "常量" + name + "应当全部以大写字母或下划线命名， 并且以字母开头", e);
		}
	}
}
