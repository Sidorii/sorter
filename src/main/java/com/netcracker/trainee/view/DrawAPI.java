package com.netcracker.trainee.view;

import com.netcracker.trainee.analyzer.AnalysisResult;

import java.util.Set;

public interface DrawAPI {

    void drawResult(Set<AnalysisResult> results);

    void flush();
}
