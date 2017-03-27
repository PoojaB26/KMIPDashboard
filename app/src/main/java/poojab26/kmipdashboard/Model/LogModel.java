package poojab26.kmipdashboard.Model;

/**
 * Created by pblead26 on 24-Jan-17.
 */


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class LogModel {

    @SerializedName("day")
    @Expose
    private String day;
    @SerializedName("time")
    @Expose
    private String time;
    @SerializedName("classname")
    @Expose
    private String classname;
    @SerializedName("function_name")
    @Expose
    private String functionName;
    @SerializedName("log_level")
    @Expose
    private String logLevel;
    @SerializedName("log")
    @Expose
    private String log;



    @SerializedName("logFormat")
    @Expose
    private String logFormat;

    public LogModel(){};



    public LogModel(String day, String time, String classname, String functionName, String logLevel, String log, String logFormat) {
        super();
        this.day = day;
        this.time = time;
        this.classname = classname;
        this.functionName = functionName;
        this.logLevel = logLevel;
        this.log = log;
        this.logFormat = logFormat;

    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }
    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }

    public String getFunctionName() {
        return functionName;
    }

    public void setFunctionName(String functionName) {
        this.functionName = functionName;
    }

    public String getLogLevel() {
        return logLevel;
    }

    public void setLogLevel(String logLevel) {
        this.logLevel = logLevel;
    }

    public String getLog() {
        return log;
    }

    public void setLog(String log) {
        this.log = log;
    }

    public String getLogFormat() {
        return logFormat;
    }

    public void setLogFormat(String logFormat) {
        this.logFormat = logFormat;
    }
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}

