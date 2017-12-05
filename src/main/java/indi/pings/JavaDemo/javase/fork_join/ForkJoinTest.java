package indi.pings.JavaDemo.javase.fork_join;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.stream.Collectors;

/**
 *********************************************************
 ** @desc  ：  查找带a的字符串                                           
 ** @author  Pings                                      
 ** @date    2017-08-07
 ** @version v1.0                                                                                
 * *******************************************************
 */
public class ForkJoinTest extends RecursiveTask<List<String>> {
	
	private static final long serialVersionUID = 1L;
	
	private static ForkJoinTest test;
	private int threshold; //**阀值
	private List<String> list; //**待拆分的list

	public ForkJoinTest(int threshold, List<String> list) {
		this.threshold = threshold;
		this.list = list;
	}

	/**
	 *********************************************************
	 ** @desc ：    获取ForkJoinTest实例                                         
	 ** @author Pings                                      
	 ** @date   2017-08-07                                      
	 ** @param  list        
	 ** @param  threshold    
	 ** @return ForkJoinTest                                  
	 * *******************************************************
	 */
	@Override
	protected List<String> compute() {
		//**当end与start之间的差小于阈值时，开始进行实际筛选
		if(list.size() < this.threshold) {
			List<String> rst = this.list.parallelStream().filter(s -> s.contains("a")).collect(Collectors.toList());
			System.out.println("运行线程：" + Thread.currentThread().getId() + ", 结果为：" + rst);
			return rst;
		} else {
			int middle = list.size() / 2;
			List<String> leftList = list.subList(0, middle);
			List<String> rigthList = list.subList(middle, list.size());
			
			ForkJoinTest left = new ForkJoinTest(threshold, leftList);
			ForkJoinTest right = new ForkJoinTest(threshold, rigthList);
			
			//**并行执行两个“小任务”
			left.fork();
			right.fork();
			
			//**把两个“小任务”的结果合并起来
            List<String> join = left.join();
            join.addAll(right.join());
            return join;
		}
	}
	
	/**
	 *********************************************************
	 ** @desc ：    获取ForkJoinTest实例                                         
	 ** @author Pings                                      
	 ** @date   2017-08-07                                      
	 ** @param  list        
	 ** @param  threshold    
	 ** @return ForkJoinTest                                  
	 * *******************************************************
	 */
    public static RecursiveTask<List<String>> getInstance(List<String> list, int threshold) {
        if (test == null) {
            synchronized (ForkJoinTest.class) {
                if (test == null) {
                	test = new ForkJoinTest(threshold, list);
                }
            }
        }
        return test;
    }
    
    public static void main(String[] args) throws InterruptedException, ExecutionException {
    	String[] strings = {"a", "ah", "b", "ba", "ab", "ac", "sd", "fd", "ar", "te", "se", "te",
                "sdr", "gdf", "df", "fg", "gh", "oa", "ah", "qwe", "re", "ty", "ui"};
        List<String> stringList = new ArrayList<>(Arrays.asList(strings));
        
        ForkJoinPool pool = new ForkJoinPool();
        RecursiveTask<List<String>> test = ForkJoinTest.getInstance(stringList, 20);
        
        //**提交可分解的ForkJoinTask任务
        ForkJoinTask<List<String>> future = pool.submit(test);
        System.out.println(future.get());
        
        //**关闭线程池
        pool.shutdown();
	}

}
