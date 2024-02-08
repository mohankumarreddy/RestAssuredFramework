package reporting;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.testng.ITestListener;

import java.util.Arrays;

public class Setup implements ITestListener {

    private static ExtentReports extentReports;
    public static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();

    public void onStart(org.testng.ITestContext context) {

        String fileName = ExtentReportManager.getReportNameWithTimeStamp();
        String fullReportPath = System.getProperty("user.dir") + "\\reports\\" + fileName;
        extentReports = ExtentReportManager.createInstance(fullReportPath, "API Automation Test Report", "API Automation Test Report");

    }

    public void onFinish(org.testng.ITestContext context) {

        if(extentReports != null) {
            extentReports.flush();
        }
    }

    public void onTestStart(org.testng.ITestResult result) {

    ExtentTest test = extentReports.createTest("Test Name - " + result.getTestClass().getName() + " - " + result.getMethod().getMethodName());
    extentTest.set(test);
    }

    public void onTestFailure(org.testng.ITestResult result) {

        ExtentReportManager.logFailDetails(result.getThrowable().getMessage());
        String stackTrace = Arrays.toString(result.getThrowable().getStackTrace());
        stackTrace = stackTrace.replaceAll(",","<br>");
        String formattedTrace = "<details>\n" +
                "<summary>Click Here To See Exception Logs</summary>\n" +
                " "+stackTrace+"\n" +
                "</details>";
        ExtentReportManager.logExceptionDetails(formattedTrace);
    }


}
