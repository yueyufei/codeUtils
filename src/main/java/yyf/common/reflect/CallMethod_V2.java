package yyf.common.reflect;

import java.lang.reflect.Method;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class CallMethod_V2 {
	public static <T> T callMethodSetTimeout(final String targetClassName,final String methodName ,final Class<?>[] parameterTypes,final Object[]params,final Integer waitTime,final TimeUnit timeUtil,final Boolean isStatic) {
		ExecutorService executorService = Executors.newSingleThreadExecutor();
		@SuppressWarnings("unchecked")
		FutureTask<T> future = new FutureTask<T>(() -> {
			Method method = null;
			T returnValue = null;
			try {
				Class<?> targetClass =  Class.forName(targetClassName);
				if(!isStatic) {
					if(parameterTypes!=null&&params!=null) {
						method = targetClass.getMethod(methodName,parameterTypes);
						returnValue=(T) method.invoke(targetClass.newInstance(),params);
					}else {
						method = targetClass.getMethod(methodName);
						returnValue=(T) method.invoke(targetClass.newInstance());
					}
				}else {
					if(params==null||parameterTypes==null) {
						method=targetClass.getMethod(methodName);
						returnValue = (T) method.invoke(null);
					}else {
						method = targetClass.getMethod(methodName , parameterTypes ); 
						returnValue = (T) method.invoke(null,params);  
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return returnValue;
		});
		executorService.execute(future);
		T result = null;  
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
		return result; 
	}
}
