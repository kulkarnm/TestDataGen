package com.testgen.userjourney.config.dataset;

public enum ParamSource {
    //calculated external input
    EXTERNAL,
    //constant value of external input
    EXTERNAL_CONST,
    //already internally calculated input found in Data-lake
    INTERNAL_PROCESS_OUTPUT ;
}
