package indi.pings.JavaDemo.jvm.javac;

import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.element.TypeElement;
import javax.lang.model.SourceVersion;

/**
 *********************************************************
 ** @desc  ：  自定义编译器插件，检查代码的命名是否符合规范                                           
 ** @author  Pings                                     
 ** @date    2017年10月20日  
 ** @version v1.0                                                                                  
 * *******************************************************
 */
//**可以用"*"表示支持所有Annotations
@SupportedAnnotationTypes("*")
//**支持JDK 1.6及以上版本的Java代码
@SupportedSourceVersion(SourceVersion.RELEASE_6)
public class NameCheckProcessor extends AbstractProcessor {
	
	private NameChecker nameChecker;

	/**
     *********************************************************
     ** @desc  ：  初始化名称检查插件
     ** @author  Pings
     ** @date    2017-10-20
     ** @param   processingEnv 注解处理器框架提供的上下文环境
     ** @version v1.0
     * *******************************************************
     */
	@Override
	public synchronized void init(ProcessingEnvironment processingEnv) {
		super.init(processingEnv);
		
		this.nameChecker = new NameChecker(processingEnv);
	}

	/**
     *********************************************************
     ** @desc  ：  对输入的语法树的各个节点进行名称检查
     ** @author  Pings
     ** @date    2017-10-20
     ** @param   annotations 本注解处理器所要处理的注解集合
     ** @param   roundEnv 当前这个Round中的语法树节点， 每个语法树节点在这里表示为一个Element，
     ** 在JDK 1.6的javax.lang.model包中定义了16类Element， 包括了Java代码中最常用的元素， 
     ** 包（PACKAGE） 、 枚举（ENUM） 、 类（CLASS） 、 注解（ANNOTATION_TYPE） 、 接口（INTERFACE） 、 枚举值（ENUM_CONSTANT） 、 字段（FIELD） 、
     ** 参数（PARAMETER） 、 本地变量（LOCAL_VARIABLE） 、 异常（EXCEPTION_PARAMETER） 、 方法（METHOD） 、 构造函数（CONSTRUCTOR） 、 
     ** 静态语句块（STATIC_INIT， 即static{}块） 、 实例语句块（INSTANCE_INIT， 即{}块） 、 参数化类型（TYPE_PARAMETER， 既泛型尖括号内的类型） 和未定义的其他语法树节点（OTHER） 
     ** 
     ** Javac编译器在执行注解处理器代码时要调用的过程
     ** 注解处理器在运行的时候都是单例的， 如果不需要改变或生成语法树的内容， process（） 方法就可以返回一个值为false的布尔值， 通知编译器这个Round中的代码未发生变化， 
     ** 无须构造新的JavaCompiler实例， 在这次实战的注解处理器中只对程序命名进行检查， 不需要改变语法树的内容， 因此process（） 方法的返回值都是false；
     ** @version v1.0
     * *******************************************************
     */
	@Override
	public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
		if (!roundEnv.processingOver()) {
			roundEnv.getRootElements().forEach(element -> {
				this.nameChecker.checkNames(element);
			});
		}
		
		return false;
	}

}
