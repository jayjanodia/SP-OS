package firstComeFirstServe;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class Process{
	public String name;
	public int ts;//service time also known as burst time
	public int tw;//waiting time
	public int tat;//Turn Around time
	public Process(String name, int ts)
	{
		this.name=name;
		this.ts=ts;
		tw=0;
		tat=0;
	}
	public void display_process()
	{
		System.out.println(name+ "\t"+ Integer.toString(ts)+ "\t"+ Integer.toString(tw)+ "\t"+ Integer.toString(tat));
	}
};

public class FCFS {
	private Queue<Process> queue = new LinkedList<Process>();
	
	private void get_Processes()
	{
		//take input from User
		try{
			Scanner sc= new Scanner(System.in);
			System.out.println("Enter number of processes");
			int n=sc.nextInt();
			System.out.println("Enter process name and service(burst) time for "+ Integer.toString(n)+ " : ");
			String name;
			int ts;
			for(int i=0;i<n;i++)
			{
				System.out.println("name: ");
				name= sc.next();
				System.out.println("Service(Burst) time: ");
				ts=sc.nextInt();
				queue.add(new Process(name, ts));
			}
			sc.close();
		}
		catch(Exception e){
			System.out.println(e);
		}
	}
	public void Display()
	{
		//display all the processes
		System.out.println("Name"+ "\tTs"+ "\tTw"+ "\tTaT");
		while(!queue.isEmpty())
		{
			queue.remove().display_process();	//pops variables to print them
		}
	}
	
	public void Schedule()
	{
		//calculation of waiting time and turn around time
		int tw=0;
		for(Process pro: queue)	//queue contains multiple processes. pro represents each process in the queue
		{
			pro.tw=tw;
			pro.tat=tw+pro.ts;
			tw=pro.tat;
		}
	}
	public static void main(String args[])
	{
		FCFS fcfs= new FCFS();
		fcfs.get_Processes();
		fcfs.Schedule();
		fcfs.Display();
	}
}