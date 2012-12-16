package executor;

import java.io.*;
import java.util.*;
import java.util.logging.*;
import schemaparser.*;

public class TaskExecutor {

    private ExecutableTask execTask;
    private String taskOutput;
    private String taskError;
    private int exitVal;

    /**
     * Constructor.
     *
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

            BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));
            BufferedReader err = new BufferedReader(new InputStreamReader(process.getErrorStream()));

            // Wait for task to finish execution
            exitVal = process.waitFor();
            System.out.println("Process exitValue: " + exitVal);

            // Get output and error streams from process
            // Then create output and error messages
            String line;

            while ((line = in.readLine()) != null) {
                System.out.println(line);
                taskOutput += line;
            }


            while ((line = err.readLine()) != null) {
                System.out.println(line);
                taskError += line;
            }

        } catch (InterruptedException | IOException ex) {
            Logger.getLogger(TaskExecutor.class.getName()).log(Level.SEVERE, null, ex);
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
            System.arraycopy(args, 0, cmdArray, 1, args.length);

            // Get the runtime environment
            Runtime runtime = Runtime.getRuntime();
            // Execute the task
            System.out.print("Executing " + cmdArray[0] + " with arguments ");
            for (int i = 1; i < cmdArray.length; i++) {
                System.out.print(cmdArray[i] + " ");
            }
            System.out.print("\n");


            Process process = runtime.exec(cmdArray);


            BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));
            BufferedReader err = new BufferedReader(new InputStreamReader(process.getErrorStream()));

            // Wait for task to finish execution
            exitVal = process.waitFor();
            System.out.println("Process exitValue: " + exitVal);

            // Get output and error streams from process
            // Then create output and error messages
            String line;
            while ((line = in.readLine()) != null) {
                System.out.println(line);
                taskOutput += line;
            }

            while ((line = err.readLine()) != null) {
                System.out.println(line);
                taskError += line;
            }


        } catch (InterruptedException | IOException ex) {
            Logger.getLogger(TaskExecutor.class.getName()).log(Level.SEVERE, null, ex);
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
