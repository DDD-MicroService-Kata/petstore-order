package com.ThoughtWorks.DDD.Order.interfaces.facade;

import com.ThoughtWorks.DDD.Order.APIBaseTest;
import com.ThoughtWorks.DDD.Order.domain.pet.PetPurchaseService;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import org.junit.*;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class OrderFacadeTest extends APIBaseTest {

    @MockBean
    private PetPurchaseService petPurchaseService;

    @Before
    public void setUp() throws Exception {
        doNothing().when(petPurchaseService).lockPetOfOrder(anyString());
    }

    @Test
    public final void shouldGetTheOrderAfterJustCreated() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(post("/api/orders")
                .contentType(MediaType.APPLICATION_JSON)
                .content(withJson("order.json")))
                .andExpect(status().isCreated())
                .andReturn();

        String location = mvcResult.getResponse().getHeader("location");
        this.mockMvc.perform(get(location)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{" +
                        "    'data': {" +
                        "        'id': " + id(location) + "," +
                        "        'attributes': {" +
                        "            'customer': {" +
                        "                'name': 'Ryan'," +
                        "                'address': {" +
                        "                    'province': 'Sichuan'," +
                        "                    'city': 'Chengdu'," +
                        "                    'area': 'Gaoxin'," +
                        "                    'street': 'Tianfu Avenue Middle Rd'," +
                        "                    'moreDetails': 'ThoughtWorks Software Technologies (Chengdu) Ltd. Room 5, 7th Floor, Area E1-1 Tianfu Software Park, No.1268 '" +
                        "                }" +
                        "            }," +
                        "            'shop': {" +
                        "                'brand': 'Cat Room'" +
                        "            }," +
                        "            'pet': {" +
                        "                'price': 1000," +
                        "                'amount': 1," +
                        "                'description': 'a pretty Cat'," +
                        "                'petId': '10010'" +
                        "            }" +
                        "        }" +
                        "    }" +
                        "}"));
    }

    private String id(String location) {
        return location.substring(location.lastIndexOf("/") + 1);
    }

    @Test
    public void shouldPayTheOrderAfterJustCreated() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(post("/api/orders")
                .contentType(MediaType.APPLICATION_JSON)
                .content(withJson("order.json")))
                .andExpect(status().isCreated())
                .andReturn();

        String location = mvcResult.getResponse().getHeader("location");

        this.mockMvc.perform(post(location + "/payments")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @After
    public void tearDown() throws Exception {

    }
}