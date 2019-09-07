package jsons;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ErrorResponse {

    @SerializedName("executionTime")
    @Expose
    private Double executionTime;
    @SerializedName("result")
    @Expose
    private ErrorResult result;

    public Double getExecutionTime() {
        return executionTime;
    }

    public void setExecutionTime(Double executionTime) {
        this.executionTime = executionTime;
    }

    public ErrorResponse withExecutionTime(Double executionTime) {
        this.executionTime = executionTime;
        return this;
    }

    public ErrorResult getResult() {
        return result;
    }

    public void setResult(ErrorResult result) {
        this.result = result;
    }

    public ErrorResponse withResult(ErrorResult result) {
        this.result = result;
        return this;
    }

}
