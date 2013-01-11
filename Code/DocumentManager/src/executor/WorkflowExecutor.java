package executor;

import java.util.ArrayList;
import schemaparser.*;

public class WorkflowExecutor {
	
	// workflow input and output files
	/* workflowInputFile = input file of the first executable task of the array;
	 * 					 = input file for the entry point */
	private String workflowInputFile;
	/* workflowInputFile = output for of last executable task of the array;
	 * 					 = output file for the exit point */
	private String workflowOutputFile;
	// NOTES:
	// 1) now, every workflow has one input file and one output file; 
	// if you want to process multiple images, the workflow should be executed sequential for every image
	// ***modify this at your convenience
	// 2) all the intermediary input and output files will be temporary and deleted afterwards
	
    // output and error messages after the workflow executor finishes
    private String workflowOutput;
    private String workflowError;
    // exit value of the executor
    private int exitVal;
    
	// array of tasks to be executed in this workflow
	private ArrayList<ExecutableTask> execTasks;
	
	public WorkflowExecutor(ArrayList<ExecutableTask> execTasks) {
		this.execTasks = execTasks;
	}
	
	public void setInputFile(String inputFile) {
		this.workflowInputFile = inputFile;
	}
	
	public void setOutputFile(String outputFile) {
		this.workflowOutputFile = outputFile;
	}
	
	// methods which implements the logic for the workflow execution
	public int execute() {
		return 0;
	}
	
	public String getTaskOutput() {
        return workflowOutput;
    }

    public String getTaskError() {
        return workflowError;
    }

    public int getExitVal() {
        return exitVal;
    }
}
