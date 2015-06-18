package common.component.mina;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.Test;

import common.component.mina.QueueData;

public class QueueDataTest {
	static QueueData a=new QueueData();
	@Test
	public void testPop() throws InterruptedException {
		Thread thread=new Thread(){
			public void run() {
				push(10);
			};
		};
		
		thread.start();
		
		
		Thread thread1=new Thread(){
			public void run() {
				push(20);
			};
		};
		thread1.start();
		
		Thread thread2=new Thread(){
			public void run() {
				pop();
			};
		};
		
		thread2.start();
	}


	@Test
	public void testPush() {
		common.component.mina.Queue a=new common.component.mina.Queue();
		for(int k=0;k<10;k++){
			a.push(""+k);
		}
		long start=System.currentTimeMillis();
		for(int k=0;k<10;k++){
			System.out.println(a.pop());
		}
	}
	
	private void push(int start){
		for(int k=0;k<10;k++){
			int temp=k+start;
			a.push(temp);
			System.out.println(temp+" . ");
		}
		System.out.println("-------------");
	}

	private void pop(){
		while(true){
			System.out.println(a.pop());
		}
	}
	
	@Test
	public void testQueue(){
		AtomicInteger nextId = new AtomicInteger();
		nextId.set(10);
		int id=nextId.getAndDecrement();
		
		System.out.println(id);
		System.out.println(nextId.get());
		System.out.println(nextId.decrementAndGet());

		int j = 0;// 必须初始化，否则编译不通过
		System.out.println(j + '0');// 获取ascii
		System.out.println(j + "0");// 转为string

		int[] i = new int[1];

		System.out.println(i[0]);// 默认初始化

		switch (i[0]) {
		case 0:
			System.out.println("zero");
		case 1:
			System.out.println("one");
		case 2:
			System.out.println("two");
		default:
			System.out.println("hah");
		}

		System.out.println(0xff ^ 0xf3);

		// 队列是先进先出
		Queue<String> q = new LinkedList<String>();
		// 这里offer和add类似
		long start=System.currentTimeMillis();
		
		for(int k=0;k<10000;k++){
			q.offer(""+k);
		}
	
		System.out.println("Queue offer"+(System.currentTimeMillis()-start)+"ms");

		
		start=System.currentTimeMillis();
		while(!q.isEmpty()){
			// 队列头部弹出
			q.poll();
		}
		System.out.println("Queue poll"+(System.currentTimeMillis()-start)+"ms");
		

		// 栈你是后进先出
		Stack<String> s = new Stack<String>();
		start=System.currentTimeMillis();
		for(int k=0;k<10000;k++){
			s.push(""+k);
		}
		System.out.println("stack push"+(System.currentTimeMillis()-start)+"ms");

		start=System.currentTimeMillis();
		while(!s.isEmpty()){
			// 队列头部弹出
			s.pop();
		}
		System.out.println("stack pop"+(System.currentTimeMillis()-start)+"ms");
		
		
		start=System.currentTimeMillis();
		for(int k=0;k<10000;k++){
			s.add(""+k);
		}
		System.out.println("stack add"+(System.currentTimeMillis()-start)+"ms");

		
		start=System.currentTimeMillis();
		while(!s.isEmpty()){
			// 队列头部弹出
			s.pop();
		}
		System.out.println("stack pop"+(System.currentTimeMillis()-start)+"ms");
		
		start=System.currentTimeMillis();
		common.component.mina.Queue a=new common.component.mina.Queue();
		for(int k=0;k<10;k++){
			a.push(""+k);
		}
		System.out.println("eastcom queue push"+(System.currentTimeMillis()-start)+"ms");
		start=System.currentTimeMillis();
		for(int k=0;k<10;k++){
			a.pop();
		}
		System.out.println("eastcom queue pop"+(System.currentTimeMillis()-start)+"ms");
		
	}
	
}
