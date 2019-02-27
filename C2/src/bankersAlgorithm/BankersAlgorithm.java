package bankersAlgorithm;

//import java.util.ArrayList;	
import java.util.Scanner;

public class BankersAlgorithm {
	private int allocate[][], max[][], available[][], need[][];	//matrices
	private int process, resource;
	
	private void input() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter number of processes: ");
		process = sc.nextInt();
		System.out.println("Enter number of resources: ");
		resource = sc.nextInt();
		//initializing arrays
		max = new int[process][resource];				//MAX matrix			
		allocate = new int[process][resource];			//allocation matrix
		available = new int[1][resource];				//available matrix
		need = new int[process][resource];				//need matrix(to be calculated)
		System.out.println("Enter MAX Matrix: ");
		for(int i = 0; i < process; i++) {
			for(int j = 0; j < resource; j++) {
				max[i][j] = sc.nextInt();
			}
		}
		System.out.println("Enter Allocation Matrix: ");
		for(int i = 0; i < process; i++) {
			for(int j = 0; j < resource; j++) {
				allocate[i][j] = sc.nextInt();
			}
		}
		System.out.println("Enter Available Matrix: ");
		for(int j = 0; j < resource; j++) {
			available[0][j] = sc.nextInt();
		}
		sc.close();
	}
	
	private int[][] calc_need() {
		//calculating the need matrix
		for(int i = 0; i < process; i++) {
			for(int j = 0; j < resource; j++) {
				need[i][j] = max[i][j] - allocate[i][j];
			}
		}
		return need;
	}
	
	private boolean check(int i) {
		//checking to see if all resources of a process can be allocated
		for(int j = 0; j < resource; j++) {
			if(available[0][j] < need[i][j])
				return false;
		}
		return true;
	}
	
	public void isSafe() {
		input();
		calc_need();
		//System.out.println(calc_need());
		boolean done[] = new boolean[process];
		int temp = 0;
		while(temp < process) {					//until all processes have been allocated
			boolean allocated = false;
			for(int i = 0; i < process; i++) {
				if(!done[i] && check(i)) {		//try to allocate
					for(int k = 0; k < resource; k++) {
						available[0][k] = available[0][k] - need[i][k] + max[i][k];
					}
					System.out.println("Allocated Process: " + i);
					allocated = done[i] = true;
					temp++;
				}
				if(!allocated) break;	//if no allocation
			}
			if (temp == process)
				System.out.println("\nAllocated Safely");
			else
				System.out.println("All processes could not be allocated safely");
		}
	}
	public static void main(String[] args) {
		new BankersAlgorithm().isSafe();
	}
}
