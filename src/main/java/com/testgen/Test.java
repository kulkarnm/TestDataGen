package com.testgen;

import com.testgen.exceptions.NoDataSetDefinitionForProcessException;
import com.testgen.userjourney.config.process.AbstractProcessElementConfig;
import com.testgen.userjourney.config.process.ProcessElementConfigBuilder;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Test {
    public static void main(String[] args) throws NoDataSetDefinitionForProcessException {
        ExecutorService pool=null;
        try {
            pool = Executors.newFixedThreadPool(5);
            AbstractProcessElementConfig rootElement = ProcessElementConfigBuilder.execute("processes/root-process-config.json");
            CompletableFuture.supplyAsync(rootElement.getTask(pool),pool).get();
        }catch(Exception ex){
            ex.printStackTrace();
        }finally{
            pool.shutdown();
        }

    }
}
