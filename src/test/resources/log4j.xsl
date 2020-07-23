<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE log4j:configuration SYSTEM "log4j.dtd">

<log4j:configuration xmlns:log4j="http://jakarta.apache.org/log4j/">

    <appender name="console" class="org.apache.log4j.ConsoleAppender">
        <param name="Target" value="System.out" />
        <layout class="org.apache.log4j.PatternLayout">
            <param name="ConversionPattern" value="%d %-5p %c - %m%n" />
        </layout>
    </appender>

    <appender name="logfile" class="org.apache.log4j.FileAppender">
        <param name="File" value="JobChatReport.txt" />
        <param name="Append" value="false" />
        <layout class="org.apache.log4j.PatternLayout">
            <param name="ConversionPattern" value="%d %-5p %c - %m%n" />
        </layout>
    </appender>

    <appender name="testng" class="commons.TestNGAppender">
        <param name="Threshold" value="INFO" />
        <layout class="org.apache.log4j.PatternLayout">
            <param name="ConversionPattern" value="%d %-5p %c - %m%n" />
        </layout>
    </appender>

    <category name="">
        <priority value="all" />
        <appender-ref ref="console" />
        <appender-ref ref="logfile" />
        <appender-ref ref="testng" />
    </category>


    <root>
        <priority value="warn" />
    </root>

</log4j:configuration>