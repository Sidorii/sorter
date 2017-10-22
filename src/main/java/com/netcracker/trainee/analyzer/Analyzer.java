package com.netcracker.trainee.analyzer;

import com.netcracker.trainee.fillers.FillStrategy;
import com.netcracker.trainee.fillers.FillerUtil;
import com.netcracker.trainee.sorters.impl.InsertSorter;

import java.lang.reflect.InvocationTargetException;

public class Analyzer {

    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException {

        Object invResult =  FillerUtil.class.getMethods()[0].invoke(null);

        int[] res = (int[]) invResult;


        FillStrategy strategy = () -> res;

        SorterAnalyzer analyzer = new SorterTimeAnalyzer(strategy);

        AnalysisResult result = analyzer.makeAnalysis(new InsertSorter());

        System.out.println(result);
    }
}
