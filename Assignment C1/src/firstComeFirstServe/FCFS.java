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
	public void display_name()		//remove if you dont want gantt chart
	{
		System.out.print(name+ "\t|"+ "\t");
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
		//while(!queue.isEmpty())//use if you are fine with popping from queue. uncomment queue.remove(). comment for loop
		for(Process pro: queue)	//alternative method if you don't want to pop queue. useful in retaining values in queue if they want to be used again
		{
			/*queue.remove().*/pro.display_process();	//pops variables to print them
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
	
	public void Gantt_Chart()		//remove if you dont want gantt chart
	{
		System.out.println("\nGantt_Chart is: \n");
		System.out.print(0);
		for (Process pro: queue)
		{
			System.out.print("\t");
			System.out.print(pro.tat+ "\t");
		}
		System.out.println("\n---------------------------------------------------------");
		while(!queue.isEmpty())
		{
			queue.remove().display_name();
		}
		System.out.println();
		System.out.println("---------------------------------------------------------");
	}
	
	public static void main(String args[])
	{
		FCFS fcfs= new FCFS();
		fcfs.get_Processes();
		fcfs.Schedule();
		fcfs.Display();
		fcfs.Gantt_Chart();		//remove if you dont want gantt chart
	}
}