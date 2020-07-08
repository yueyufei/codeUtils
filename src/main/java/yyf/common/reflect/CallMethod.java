package yyf.common.reflect;


import java.lang.reflect.Method;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
 
/**
 * 方法超过执行时间后抛出异常
 * @author yyf
 * */
public class CallMethod {
	
	/***
	 * 方法参数说明
	 * @param target 调用方法的当前对象
	 * @param methodName 方法名称
	 * @param parameterTypes 调用方法的参数类型
	 * @param params 参数  可以传递多个参数
	 * 
	 * */
	public static Object callMethod(final Object target , final String methodName ,final Class<?>[] parameterTypes,final Object[]params,final Integer waitTime,final TimeUnit timeUtil){
		ExecutorService executorService = Executors.newSingleThreadExecutor();  
        FutureTask<String> future = new FutureTask<String>(new Callable<String>() {  
            public String call() throws Exception { 
            	String value = null  ; 
            	try {
					Method method = null ; 
					Object returnValue = null;
					if(params==null||parameterTypes==null) {
						method=target.getClass().getDeclaredMethod(methodName);
						returnValue = method.invoke(target);
					}else {
						method = target.getClass().getDeclaredMethod(methodName , parameterTypes );  
						returnValue = method.invoke(target, params) ;  
					}
					value = returnValue != null ? returnValue.toString() : null ;
				} catch (Exception e) {
					e.printStackTrace() ;
					throw e ; 
				}
                return value ;
            }  
        });  
          
        executorService.execute(future);  
        String result = null;  
        try{
        	/**获取方法返回值 并设定方法执行的时间为10秒*/
            result = future.get(waitTime , timeUtil);  
            
        }catch (InterruptedException e) {  
            future.cancel(true);  
            System.out.println("方法执行中断"); 
        }catch (ExecutionException e) {  
            future.cancel(true);  
            System.out.println("Excuti on异常");  
        }catch (TimeoutException e) {  
            future.cancel(true);  
            throw new RuntimeException("invoke timeout" , e );
        }
        executorService.shutdownNow(); 
        return result ;
	}
	
	public void call(){
		try {
			Thread.sleep( 11000 ); 
		} catch (Exception e) {
		}
		System.out.println("hhhhh");
	}
	
	public static void main(String[] args) {
//		System.out.println( callMethod(new CallMethod(), "call" , new Class<?>[]{Integer.class}, new Object[]{ 1523 } ,10,TimeUnit.SECONDS) ) ; 
		callMethod(new CallMethod(), "call" , null, null ,10,TimeUnit.SECONDS); 
	}
}
