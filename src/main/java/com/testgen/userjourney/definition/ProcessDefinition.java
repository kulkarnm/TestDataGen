package com.testgen.userjourney.definition;

import java.util.ArrayList;
import java.util.List;

public class ProcessDefinition {
    private List<ProcessElementDefinition> listOfDescreteProcesses;
    private ProcessDefinition singleDefintion;

    private ProcessDefinition(){
        listOfDescreteProcesses = new ArrayList<>();
    }
    public ProcessDefinition getInstance(){
        if (null == singleDefintion){
            singleDefintion = new ProcessDefinition();
        }
        return singleDefintion;
    }
    public void addToProcesses(ProcessElementDefinition processElementDefinition){
        this.listOfDescreteProcesses.add(processElementDefinition);
    }
} 
