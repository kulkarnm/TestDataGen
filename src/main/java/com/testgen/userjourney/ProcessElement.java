package com.testgen.userjourney;

//Parent interface - Composite Design pattern
public interface ProcessElement {
    public void addToProcessElements(ProcessElement processElement);
    public ProcessOutput execute();
}
