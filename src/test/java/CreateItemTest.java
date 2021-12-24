import org.testng.annotations.Test;
import stepDefs.StepDefinitions;

import java.util.HashMap;
import java.util.Map;

public class CreateItemTest {

    @Test(description = "Тест - Создание товара")
    public void createItems() throws Exception {
        // строковая переменная нужна чтобы положить потом в нее ответ на запрос
        String saveCreateItemRs = "";
        //формируем тело запроса
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("name", "Апельсиновое платье");
        requestBody.put("section", "Платья");
        requestBody.put("description", "коллекция Весна-Лето 2021");
        //отправляем запрос, получаем ответ и кладем ответ в переменную saveCreateItemRs
        StepDefinitions stepDefinitions = new StepDefinitions();
        stepDefinitions.sendResCreateItemsRequest(saveCreateItemRs, requestBody);
        //множественная проверка параметров в запросе с ответом
        stepDefinitions.checkCreatedItem(saveCreateItemRs,requestBody);
    }
}
