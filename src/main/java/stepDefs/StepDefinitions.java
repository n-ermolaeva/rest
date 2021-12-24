package stepDefs;

import dto.CreateItemRsDto;
import helpers.ApiHelper;
import helpers.BaseResponse;
import org.testng.asserts.SoftAssert;
import ru.sbtqa.tag.datajack.Stash;

import java.util.*;

public class StepDefinitions {

    public void sendResCreateItemsRequest(String saveKey,
                                          Map<String, Object> map) throws Exception {
        BaseResponse<CreateItemRsDto> response = null;
        try {
            response = ApiHelper.createItems(map);
        } catch (Exception e) {
            throw new Exception("Problem with REST api createItems()\n" + e);
        }
        Stash.put(saveKey,
                response.getResults());
    }

    public void checkCreatedItem(String createItemRs,
                                 Map<String, Object> createItemRq) {
        CreateItemRsDto createItemRsDto = Stash.getValue(createItemRs);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(createItemRsDto.getName().equals(createItemRq.get("name")),
                "Название товара из запроса не совпадает с тем, что прошло в ответ");
        softAssert.assertTrue(createItemRsDto.getDescription().equals(createItemRq.get("description")),
                "Описание товара в запросе не совпадает с тем, что пришло в ответ");
        softAssert.assertTrue(createItemRsDto.getSection().equals(createItemRq.get("section")),
                "Описание товара в запросе не совпадает с тем, что пришло в ответ");
        softAssert.assertAll();
    }

}


