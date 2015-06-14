package common.component.mina;

import org.apache.log4j.Logger;

public class QueueData {

    //消息缓存，其中消息处理线程会逐条获取其中的消息，根据其中的属性进行一定的业务逻辑处理
    private static Queue queue=new Queue();//服务端缓存
	
	protected Logger log = Logger.getLogger(this.getClass());
	
	public void push(Object msg){
		long tstart=System.currentTimeMillis();
		synchronized(queue){
			queue.push(msg);
			queue.notifyAll();
		}
		log.debug("pushMsg cost time："+(System.currentTimeMillis()-tstart));
	}
	
	public Object pop(){
		long tstart=System.currentTimeMillis();
		
		Object obj = null;
		synchronized(queue){
			try {
				if (queue.isEmpty()){
					queue.wait();
				}
				
				obj=queue.pop();
				
			} catch (InterruptedException e) {
				log.error(e);
			}
		}
		
		log.debug("popMsg cost time："+(System.currentTimeMillis()-tstart));
		return obj;
	}
}
