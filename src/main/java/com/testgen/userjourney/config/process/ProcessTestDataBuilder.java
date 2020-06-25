package com.testgen.userjourney.config.process;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ProcessTestDataBuilder {
    ExecutorService threadPool;
    private static String currentFolder;
    private Queue<AbstractProcessElementConfig> rootQueue;
    public ProcessTestDataBuilder(){
        this.threadPool = Executors.newCachedThreadPool();
        this.rootQueue=new LinkedList<>();
    }
    public void generateTestData(AbstractProcessElementConfig config){
       this.rootQueue.add(config);
        String processId = config.getProcessId();
        currentFolder=processId;
        createFolder(processId);
        boolean isMulticasting = config.isMultiCasting();
        if(isMulticasting){

        }
    }


    public void createFolder(String folderName){
        Path dirPathObj = Paths.get(folderName);
        boolean dirExists = Files.exists(dirPathObj);
        if(dirExists) {
            System.out.println("! Directory Already Exists !");
        } else {
            try {
                Files.createDirectories(dirPathObj);
                System.out.println("! New Directory Successfully Created !");
            } catch (IOException ioExceptionObj) {
                ioExceptionObj.printStackTrace();
                System.out.println("Problem Occured While Creating The Directory Structure= " + ioExceptionObj.getMessage());
            }
        }
    }
}
