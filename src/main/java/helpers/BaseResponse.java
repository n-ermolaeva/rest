package helpers;

import com.fasterxml.jackson.annotation.JsonProperty;
import dto.CreateItemRsDto;

import java.io.Serializable;

public class BaseResponse<T extends Serializable> implements Serializable {

    @JsonProperty("method")
    private String method;

    @JsonProperty("status")
    private String status;

    @JsonProperty("result")
    private T result;

    public T getResults() {
        return result;
    }

}
