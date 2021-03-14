package xuexuankr.starter.com.jobtrend;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	IndexPage page = new IndexPage();
    	PositionPage position = new PositionPage();
    	ExecutorService threadPool = new ThreadPoolExecutor(2,
                Runtime.getRuntime().availableProcessors(),
                3,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.DiscardOldestPolicy());
    	
    	try {
			List<String> urls = page.IndexUri();
			System.out.println("IndexURL "+ urls.size());
			for (String s : urls)
			{
				final List<PositionSummary> l = page.GetPositionSummary(s);
				System.out.println("page position "+ l.size());
				{
					//2,add log
					//3,check exception
					//4,check core usage美团线程池机制
					threadPool.execute(()->{
						int number = 0;
						for (PositionSummary p : l)
						{
							PositionDetails details = position.GetPositionDetails(p.uri);
							System.out.println(Thread.currentThread().getName() +" "+ p.getCompany()+" "+number++);
							for(int i = 0;i < details.getDescription().size(); ++i)
							{
								//System.out.println(details.getDescription().get(i).toString());
							}	
							System.out.println("================");
						}
					});
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	threadPool.shutdown();
    	try
    	{
    		if (!threadPool.awaitTermination(60, TimeUnit.SECONDS))
    		{
    			threadPool.shutdownNow();
    			if (!threadPool.awaitTermination(60, TimeUnit.SECONDS))
    			{
    				System.err.println("fail to terminate threadpool.");
    			}
    		}
    	}
    	catch(InterruptedException e)
    	{
    		threadPool.isShutdown();
    	}
    }
}
