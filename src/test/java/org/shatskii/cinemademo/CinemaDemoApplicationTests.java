package org.shatskii.cinemademo;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

import java.util.List;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CinemaDemoApplicationTests {


    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    @Order(1)
    public void getAllFreeSeats0() {
        ResponseEntity<String> forEntity = restTemplate.getForEntity("http://localhost:8080/api/v1/seats/all", String.class);
        Assertions.assertEquals(HttpStatus.OK, forEntity.getStatusCode());
    }

    @Test
    @Order(2)
    public void getAllFreeSeats1() {
        ResponseEntity<String> forEntity = restTemplate.getForEntity("http://localhost:8080/api/v1/seats/all", String.class);
        Assertions.assertEquals("[{\"row\":1,\"seats\":[{\"seatNumber\":1,\"isFree\":\"yes\",\"price\":100}," +
                "{\"seatNumber\":3,\"isFree\":\"yes\",\"price\":100}]}," +
                "{\"row\":2,\"seats\":[{\"seatNumber\":2,\"isFree\":\"yes\",\"price\":80}]}," +
                "{\"row\":3,\"seats\":[{\"seatNumber\":1,\"isFree\":\"yes\",\"price\":50}," +
                "{\"seatNumber\":3,\"isFree\":\"yes\",\"price\":50}]}]", forEntity.getBody());
    }

    @Test
    @Order(3)
    public void getCart0() {
        ResponseEntity<String> forEntity = restTemplate.getForEntity("http://localhost:8080/api/v1/cart", String.class);
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, forEntity.getStatusCode());
    }

    @Test
    @Order(4)
    public void getCart1() {
        ResponseEntity<String> forEntity = restTemplate.getForEntity("http://localhost:8080/api/v1/cart", String.class);
        Assertions.assertEquals("{\"info\":\"Вы ничего не добавили в корзину!\"}", forEntity.getBody());
    }

    @Test
    @Order(5)
    public void addTicketToCart() {
        ResponseEntity<String> addResponse = restTemplate.postForEntity("http://localhost:8080/api/v1/cart/add", new int[]{1, 3}, String.class);
        Assertions.assertEquals("{\"tickets\":[{\"row\":1,\"seatNumber\":1},{\"row\":1,\"seatNumber\":3}]}", addResponse.getBody());
        HttpHeaders headers = addResponse.getHeaders();
        List<String> cookies = headers.get("Set-Cookie");
        String sessionCookie = cookies.get(0);
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.add("Cookie", sessionCookie);
        ResponseEntity<String> cartResponse = restTemplate.exchange("http://localhost:8080/api/v1/cart", HttpMethod.GET,
                new HttpEntity<>(requestHeaders), String.class);
        Assertions.assertEquals("{\"totalPrice\":200,\"tickets\":[{\"row\":1,\"seatNumber\":1,\"price\":100},{\"row\":1,\"seatNumber\":3,\"price\":100}]}",
                cartResponse.getBody());
    }
}
