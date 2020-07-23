<?xml version="1.0" encoding="UTF-8"?>
<suite name="NopCommerce" parallel="false">

    <listeners>
        <listener class-name="org.uncommons.reportng.HTMLReporter"/>
        <listener class-name="org.uncommons.reportng.JUnitXMLReporter"/>
        <listener class-name="configReportHTML.ExtentReportListener"/>
        <listener class-name="configReportHTML.ReportNGListener"/>
        <listener class-name="commons.RetryListener"/>
        <listener class-name="configReportHTML.ExtentTestListener"/>
    </listeners>
<!--    <test name="Run on FIREFOX">-->
<!--        <parameter name="browser" value="firefox"></parameter>-->
<!--        <parameter name="version" value="0.26.0"></parameter>-->
<!--        <classes>-->
<!--            <class name="account.Login"></class>-->
<!--        </classes>-->
<!--    </test>-->

    <test name="Run on CHROME">
        <parameter name="browser" value="chrome"></parameter>
        <classes>
            <class> name=""</class>
        </classes>
    </test>

<!--    <test name="Run on CHROME HEADLESS">-->
<!--        <parameter name="browser" value="chromeheadless"></parameter>-->
<!--        <classes>-->

<!--        </classes>-->
<!--    </test>-->

<!--    <test name="Run on SAFARI">-->
<!--        <parameter name="browser" value="safari"></parameter>-->
<!--        <classes>-->

<!--        </classes>-->
<!--    </test>-->
</suite>
