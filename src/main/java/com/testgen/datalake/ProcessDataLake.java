package com.testgen.datalake;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class ProcessDataLake {
    private ProcessDataLake processDataLake;
    private Map<Integer,List<ProcessDataElement>> levelwiseProcessData;

    private ProcessDataLake(){
        levelwiseProcessData = new TreeMap<>();
    }

    public ProcessDataLake getInstance(){
        if(null ==this.processDataLake){
            this.processDataLake = new ProcessDataLake();
        }
        return this.processDataLake;
    }
    public void addProcessData(int level,ProcessDataElement processDataElement){
        if(levelwiseProcessData.containsKey(level)){
         levelwiseProcessData.get(level).add(processDataElement);
        }else{
            List<ProcessDataElement> processDataList = new ArrayList<>();
            processDataList.add(processDataElement);
            levelwiseProcessData.put(level,processDataList);
        }
    }
} 
