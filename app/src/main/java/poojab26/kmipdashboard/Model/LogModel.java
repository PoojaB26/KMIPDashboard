package poojab26.kmipdashboard.Model;

/**
 * Created by pblead26 on 24-Jan-17.
 */


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class LogModel {

    @SerializedName("timestamp")
    @Expose
    private String timestamp;
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

    public LogModel(){};
    public LogModel(String timestamp, String classname, String functionName, String logLevel, String log) {
        super();
        this.timestamp = timestamp;
        this.classname = classname;
        this.functionName = functionName;
        this.logLevel = logLevel;
        this.log = log;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
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

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}

