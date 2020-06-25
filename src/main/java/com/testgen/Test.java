package com.testgen;

import com.testgen.exceptions.NoDataSetDefinitionForProcessException;
import com.testgen.userjourney.config.dataset.RequestConfig;
import com.testgen.userjourney.config.process.AbstractProcessElementConfig;
import com.testgen.userjourney.config.process.ProcessElementConfigBuilder;
import com.testgen.userjourney.config.process.RequestBuilder;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Test {
    public static void main(String[] args) throws NoDataSetDefinitionForProcessException {
        AbstractProcessElementConfig rootElement = ProcessElementConfigBuilder.execute("processes/root-process-config.json");
        rootElement.getTask();
    }
}
