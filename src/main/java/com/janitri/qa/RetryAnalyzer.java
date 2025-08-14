package com.janitri.qa;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;


public class RetryAnalyzer implements IRetryAnalyzer {
    private int count = 0;
    private static final int maxTry = 2; // Retry failed tests 2 times

    @Override
    public boolean retry(ITestResult result) {
        if (count < maxTry) {
            count++;
            System.out.println("Retrying test: " + result.getName() + " | Attempt: " + count);
            return true;
        }
        return false;
    }
}
