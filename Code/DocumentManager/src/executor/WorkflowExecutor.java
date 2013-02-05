package executor;

import java.io.File;
import java.util.ArrayList;
import java.util.UUID;
import schemaparser.*;

public class WorkflowExecutor {

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

    // methods which implements the logic for the workflow execution
    public int execute(ArrayList<String> inputFiles, ArrayList<String> outputFiles) {
        int returnValue = 0;
        workflowError = "";
        workflowOutput = "";
        for (int i = 0; i < inputFiles.size(); ++i) {
            String entryPoint = inputFiles.get(i);
            String endPoint = outputFiles.get(i);
            String previousFile = null;
            for (int stage = 0; stage < execTasks.size(); ++stage) {
                String inputFile, outputFile;
                if (stage == 0) {
                    inputFile = entryPoint;
                } else {
                    inputFile = previousFile;
                }
                if (stage == execTasks.size() - 1) {
                    outputFile = endPoint;
                } else {
                    outputFile = UUID.randomUUID().toString();
                }
                ExecutableTask task = execTasks.get(stage);
                task.setInputFile(inputFile);
                task.setOutputFile(outputFile);
                TaskExecutor te = new TaskExecutor(task);
                te.execute();
                if (te.getExitVal() != 0) {
                    returnValue = -1;
                    workflowError += "Could not process " + entryPoint + ".";
                    workflowError += "Error at " + task.getExecInfo().getExecName() + ":";
                    workflowError += te.getTaskError();
                    workflowError += "\n";
                    if (!inputFile.equals(entryPoint)) {
                        // Delete temporary file used in intermediate stage
                        File f = new File(inputFile);
                        f.delete();
                    }
                    if (!inputFile.equals(endPoint)) {
                        // Delete temporary file used in intermediate stage
                        File f = new File(outputFile);
                        f.delete();
                    }
                    // This will quit from current file being processed, and continue to next set of 
                    break;
                }
                previousFile = outputFile;
                if (!inputFile.equals(entryPoint)) {
                    // Delete temporary file used in intermediate stage
                    File f = new File(inputFile);
                    f.delete();
                }
            }
            workflowOutput += "Sucessfully worked " + entryPoint + " into " + endPoint + "\n";
        }
        return returnValue;
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
