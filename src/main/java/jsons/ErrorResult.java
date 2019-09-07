package jsons;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
public class ErrorResult {

    @SerializedName("error")
    @Expose
    private ErrorMassage error;

    public ErrorMassage getError() {
        return error;
    }

    public void setError(ErrorMassage error) {
        this.error = error;
    }

    public ErrorResult withError(ErrorMassage error) {
        this.error = error;
        return this;
    }
}
