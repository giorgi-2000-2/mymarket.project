package org.example.Api;
import io.restassured.response.Response;
import org.example.utils.APIManager;
import org.example.utils.ConfigReader;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static org.example.utils.ExtentReportManager.getTest;

public class ApiTest {

    private Map<String, String> getHeaders() {
        Map<String, String> headers = new HashMap<>();
        headers.put("Accept", "application/json");
        return headers;
    }

    private String uniqueTitle(String base) {
       return base + "-" + UUID.randomUUID().toString();
   }

    @Test(groups = "no-screenshot")
    public void testGetProductE2ETest() {
        String title = uniqueTitle(ConfigReader.get("UNIQ.TITLE"));
        Map<String, Object> createBody = new HashMap<>();
        createBody.put("title", title);
        createBody.put("price", ConfigReader.get("DEF.PRICE"));
        createBody.put("description", ConfigReader.get("DEF.DESCRIPTION"));
        createBody.put("categoryId", ConfigReader.get("DEF.CATID"));
        createBody.put("images", new String[]{ConfigReader.get("DEF.IMAGE")});
        getTest().info("მომზადდა სატესტო მონაცემები ");

        getTest().info(" პროდუქტის შექმნა");
        Response created = APIManager.post("/products", getHeaders(), createBody);

        APIManager.assertWithLog(created.statusCode(), 201, "პროდუქტიs შექმნა");
        int id = created.jsonPath().getInt("id");

        getTest().info("პროდუქტის წაკითხვა და შემოწმება");
        Response fetched = APIManager.get("/products/{id}", getHeaders(), id, 200);
        APIManager.assertWithLog(fetched.jsonPath().getString("title"), title, "სათაური ");
        APIManager.assertWithLog(fetched.jsonPath().getInt("price"), 25, "ფასი ");

        getTest().info("პროდუქტის განახლება");
        Map<String, Object> updateBody = new HashMap<>();
        String updatetitle = uniqueTitle("განახლებული");
        updateBody.put("title", updatetitle);
        updateBody.put("price", 55);
        updateBody.put("description", "Updated description text");
        updateBody.put("categoryId", 1);
        updateBody.put("images", new String[]{"https://placehold.co/600x400"});

        Response updated = APIManager.put("/products/{id}", getHeaders(), id, updateBody);
        APIManager.assertWithLog(updated.statusCode(), 200, "განახლება ჩავარდა");
        APIManager.assertWithLog(updated.jsonPath().getString("title"), updatetitle, "განახლებული სათაური ");

        getTest().info(" პროდუქტის წაშლა");
        APIManager.delete("/products/{id}", getHeaders(), id, 200);

        getTest().info(" წაშლის დადასტურება");
        Response verify = APIManager.get("/products/{id}", getHeaders(), id, 404);
        APIManager.assertWithLog(verify.statusCode(), 404, "წაშლის შემდგომი შემოწმება ");

    }

    @Test(groups = "no-screenshot")
    public void testGetProductById() {


        Map<String, Object> body = new HashMap<>();
        String expectedTitle = uniqueTitle("get-by-id"); // ← ერთხელ
        body.put("title", expectedTitle);
        body.put("price", 10);
        body.put("description", "desc");
        body.put("categoryId", 1);
        body.put("images", new String[]{"https://placehold.co/600x400"});
        Response created = APIManager.post("/products", getHeaders(), body);
        Assert.assertEquals(created.statusCode(), 201, "Setup: product creation failed");

        int id = created.jsonPath().getInt("id");
        Response response = APIManager.get("/products/{id}", getHeaders(), id, 200);
        APIManager.assertWithLog(response.jsonPath().getInt("id"), id," შედარება ");
        APIManager.assertWithLog(response.jsonPath().getString("title"), expectedTitle," შედარება ");
        APIManager.assertWithLog(response.jsonPath().getInt("price"), 10," შედარება ");
    }

    @Test(groups = "no-screenshot")
    public void testGetProductsWithQuery() {
        Map<String, Object> params = new HashMap<>();
        params.put("offset", 0);
        params.put("limit", 5);
        getTest().info(" პროდუქტების ლიმიტია – 5");
        Response response = APIManager.getParams("/products", getHeaders(), params);

        List<Map<String, Object>> products = response.jsonPath().getList("");
        getTest().info("სტატუს კოდის და სიის შემოწმება");
        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertTrue(products.size() <= 5, "პროდუქტების რაოდენობა 5-ზე მეტია");
        Assert.assertFalse(products.isEmpty(), "პროდუქტების სია ცარიელია");
        getTest().info("ყველა პროდუქტის ველების შემოწმება — " + products.size() + " პროდუქტი");
        for (Map<String, Object> product : products) {
            Assert.assertNotNull(product.get("id"), "id არ არის");
            Assert.assertNotNull(product.get("title"), "title არ არის");
            Assert.assertNotNull(product.get("price"), "price არ არის");
        }
    }


    @Test(groups = "no-screenshot")
    public void testCreateProduct() {
        String expectedTitle=uniqueTitle("test-product");
        Map<String, Object> body = new HashMap<>();
        body.put("title", expectedTitle);
        body.put("price", 100);
        body.put("description", "desc");
        body.put("categoryId", 1);
        body.put("images", new String[]{ConfigReader.get("DEF.IMAGE")});
        getTest().info("პროდუქტის შექმნა — title: " + expectedTitle);
        Response response = APIManager.post("/products", getHeaders(), body);
        APIManager.assertWithLog(response.statusCode(), 201," შედარება ");
        APIManager.assertWithLog(response.jsonPath().getString("title"),expectedTitle," შედარება ");
        getTest().pass("პროდუქტი შეიქმნა — id: " + response.jsonPath().getInt("id"));
    }


    @Test(groups = "no-screenshot")
    public void testCreateWithoutPrice() {
        Map<String, Object> body = new HashMap<>();
        body.put("title", "wrong");
        getTest().info("პროდუქტის შექმნა ფასის გარეშე");
        Response response = APIManager.post("/products", getHeaders(), body);
        APIManager.assertWithLog(response.statusCode(), 400," შედარება ");
    }

    @Test(groups = "no-screenshot")
    public void testDeleteProduct() {
        Map<String, Object> body = new HashMap<>();
        body.put("title", uniqueTitle("to-delete"));
        body.put("price", 10);
        body.put("description", "desc");
        body.put("categoryId", 1);
        body.put("images", new String[]{"https://placehold.co/600x400"});
        getTest().info("პროდუქტის შექმნა");
        Response created = APIManager.post("/products", getHeaders(), body);
        int id = created.jsonPath().getInt("id");
        APIManager.delete("/products/{id}", getHeaders(), id, 200);
        getTest().info("პროდუქტის წაშლა — id: " + id);
        Response deleted = APIManager.get("/products/{id}", getHeaders(), id, 404);
        getTest().info("წაშლის დადასტურება — id: " + id);
        APIManager.assertWithLog(deleted.statusCode(), 404, "წაშლა დადასტურდა");
    }


    @Test(groups = "no-screenshot")
    public void testCreateAndGetE2E() {
        String title = uniqueTitle("e2e-test");

        Map<String, Object> body = new HashMap<>();
        body.put("title", title);
        body.put("price", 50);
        body.put("description", "desc");
        body.put("categoryId", 1);
        body.put("images", new String[]{"https://placehold.co/600x400"});
        getTest().info("პროდუქტის შექმნა " + title);
        Response post = APIManager.post("/products", getHeaders(), body);
        Assert.assertEquals(post.statusCode(), 201, "POST failed: " + post.body().asString());
        int id = post.jsonPath().getInt("id");
        getTest().info("პროდუქტის წაკითხვა " + id);
        Response get = APIManager.get("/products/{id}", getHeaders(), id, 200);
        Assert.assertEquals(get.jsonPath().getString("title"), title);
    }

    @Test(groups = "no-screenshot")
    public void testCreateDeleteE2E() {
        Map<String, Object> body = new HashMap<>();
        body.put("title", uniqueTitle("delete-flow"));
        body.put("price", 10);
        body.put("description", "desc");
        body.put("categoryId", 1);
        body.put("images", new String[]{"https://placehold.co/600x400"});
        getTest().info("პროდუქტის შექმნა " );
        Response post = APIManager.post("/products", getHeaders(), body);
        APIManager.assertWithLog(post.statusCode(), 201, " შედარება " );
        int id = post.jsonPath().getInt("id");
        getTest().info("პროდუქტის წაშლა " + id);
        getTest().info("წაშლის დადასტურება " + id);
        APIManager.delete("/products/{id}", getHeaders(), id, 200);
        APIManager.get("/products/{id}", getHeaders(), id, 404);
        Response getAfterDelete = APIManager.get("/products/{id}", getHeaders(), id, 404);
        APIManager.assertWithLog(getAfterDelete.statusCode(), 404, "პროდუქტის წაიშალა – ");


    }


    @Test(groups = "no-screenshot")
    public void testFilterByPriceRange() {
        Map<String, Object> params = new HashMap<>();
        params.put("price_min", 10);
        params.put("price_max", 100);
        getTest().info("ფასის ფილტრი —მინიმალური არის =10, მაქსიმალური არის =100");
        Response response = APIManager.getParams("/products", getHeaders(), params);
        List<Map<String, Object>> products = response.jsonPath().getList("");
        APIManager.assertWithLog(response.statusCode(), 200," შედარება ");
        Assert.assertFalse(products.isEmpty(), "სია ცარიელია");
        getTest().info("შედეგი არის — " + products.size() + " პროდუქტი");


        for (Map<String, Object> product : products) {
            int price = (int) product.get("price");
            Assert.assertTrue(price >= 10 && price <= 100,
                    "ფასი დიაპაზონს გარეთია: " + price);
        }

    }

    @Test(groups = "no-screenshot")
    public void testFilterByCategoryId() {
        Map<String, Object> params = new HashMap<>();
        params.put("categoryId", 1);
        getTest().info("კატეგორიის ფილტრი ");
        Response response = APIManager.getParams("/products", getHeaders(), params);
        List<Map<String, Object>> products = response.jsonPath().getList("");
        APIManager.assertWithLog(response.statusCode(), 200," შედარება ");
        Assert.assertFalse(products.isEmpty(), "სია ცარიელია");
        getTest().info("შედეგი — " + products.size() + " პროდუქტი");
        products.forEach(p -> {
            Map<String, Object> category = (Map<String, Object>) p.get("category");
            Assert.assertEquals(category.get("id"), 1);
        });
    }












}