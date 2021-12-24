package helpers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import dto.CreateItemRsDto;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.SneakyThrows;

import java.io.IOException;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class ApiHelper {
    private static final String BASE_URL = "http://shop.bugred.ru/api/items/";
    private static final String CREATE_ITEM = "create/";

    private static final ObjectMapper mapper = new ObjectMapper();

    static {
        RestAssured.requestSpecification = new RequestSpecBuilder()
                .setBaseUri(BASE_URL)
                .setContentType(ContentType.JSON)
                .log(LogDetail.ALL)
                .build();
    }

    @SneakyThrows
    //метод для отправки пост запроса
    private static Response sendPost(String endPoint, Map<String, Object> body) {
        RequestSpecification requestSpecification = given().body(body);
        Response response = requestSpecification.when().post(endPoint).thenReturn();
        response.prettyPrint();
        return response;
    }

    public static BaseResponse<CreateItemRsDto> createItems(Map<String, Object> body) {
        Response response = sendPost(CREATE_ITEM, body);
        BaseResponse<CreateItemRsDto> createItemsResponse = null;
        try {
            // переводим наш ответ из формата json в джава объект
            createItemsResponse = mapper.readValue(response.asString(), new TypeReference<BaseResponse<CreateItemRsDto>>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return createItemsResponse;
    }

}

