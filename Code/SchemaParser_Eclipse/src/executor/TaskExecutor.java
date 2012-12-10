package executor;

import java.io.*;
import schemaparser.*;

public class TaskExecutor {

	private ExecutableTask execTask;
	private String taskOutput;
	private String taskError;
	private int exitVal;
	
	/**
     * Constructor.
     * @param execTask	task to be executed
     */
	public TaskExecutor(ExecutableTask execTask) {
		this.execTask = execTask;
	}
	
	public ExecutableTask getTask() {
		return execTask;
	}
	
	public void setTask(ExecutableTask execTask) {
		this.execTask = execTask;
	}
	
	/**
     * Execute a task with no arguments
     */
	public void execute() {
		try {
			// Get the runtime environment
			Runtime runtime = Runtime.getRuntime();
			// Execute the task
			System.out.println("Executing " + execTask.getExecInfo().getExecName());
			Process process = runtime.exec(execTask.getExecInfo().getExecName());
						
			// Wait for task to finish execution
			exitVal = process.waitFor();
			System.out.println("Process exitValue: " + exitVal);
			
			// Get output and error streams from process
			// Then create output and error messages
			String line;
			BufferedReader in =new BufferedReader(new InputStreamReader(process.getInputStream()));
			while ((line = in.readLine()) != null) {
                System.out.println(line);
                taskOutput += line;
            }
			
			BufferedReader err =new BufferedReader(new InputStreamReader(process.getErrorStream()));
			while ((line = err.readLine()) != null) {
                System.out.println(line);
                taskError += line;
            }
			
		}
		catch (Exception e) {
			e.printStackTrace();
            System.out.println(e.getMessage());
		}
	}
	
	/**
     * Execute a task with an array of arguments
     */
	public void execute(String[] args) {
		try {
			String[] cmdArray = new String[args.length + 1];
			// Add the task name (executable) in the first position
			cmdArray[0] = execTask.getExecInfo().getExecName();
			// Then add the arguments of the task
			for (int i=0; i<args.length; i++)
				cmdArray[i+1] = args[i];
			
			// Get the runtime environment
			Runtime runtime = Runtime.getRuntime();
			// Execute the task
			System.out.print("Executing " + cmdArray[0] + " with arguments ");
			for (int i=1; i<cmdArray.length; i++)
				System.out.print(cmdArray[i] + " ");
			System.out.print("\n");
			
			Process process = runtime.exec(cmdArray);
			
			// Wait for task to finish execution
			exitVal = process.waitFor();
			System.out.println("Process exitValue: " + exitVal);
			
			// Get output and error streams from process
			// Then create output and error messages
			String line;
			BufferedReader in =new BufferedReader(new InputStreamReader(process.getInputStream()));
			while ((line = in.readLine()) != null) {
				System.out.println(line);
				taskOutput += line;
			}
						
			BufferedReader err =new BufferedReader(new InputStreamReader(process.getErrorStream()));
			while ((line = err.readLine()) != null) {
				System.out.println(line);
				taskError += line;
			}			
		}
		catch (Exception e) {
			e.printStackTrace();
            System.out.println(e.getMessage());
		}
	}
	
	public String getTaskOutput() {
		return taskOutput;
	}
	
	public String getTaskError() {
		return taskError;
	}
	
	public int getExitVal() {
		return exitVal;
	}
}
