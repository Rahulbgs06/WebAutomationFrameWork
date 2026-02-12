package utils;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {

	public static ExtentReports extent;

    public static ExtentReports getInstance() {

        if (extent == null) {

            String timestamp =
                    new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());

            String reportPath =
                    "reports/ExtentReport_" + timestamp + ".html";

            ExtentSparkReporter spark =
                    new ExtentSparkReporter(reportPath);

            spark.config().setReportName("Automation Test Report");
            spark.config().setDocumentTitle("Test Results");

            extent = new ExtentReports();
            extent.attachReporter(spark);
        }

        return extent;
    }
}
