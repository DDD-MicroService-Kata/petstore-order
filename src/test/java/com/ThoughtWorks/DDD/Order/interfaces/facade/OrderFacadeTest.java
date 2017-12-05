package com.ThoughtWorks.DDD.Order.interfaces.facade;

import com.ThoughtWorks.DDD.Order.APIBaseTest;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import org.junit.Rule;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;

import java.text.Format;

import static com.github.tomakehurst.wiremock.client.ResponseDefinitionBuilder.okForJson;
import static com.github.tomakehurst.wiremock.client.WireMock.ok;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class OrderFacadeTest extends APIBaseTest {
    @Rule
    public WireMockRule petServiceMock = new WireMockRule(9018);

    @Test
    public final void shouldGetTheOrderAfterJustCreated() throws Exception {
        stubFor(WireMock.put(urlEqualTo("/api/pets/status"))
                .willReturn(ok()));

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
                        "        'id': 1," +
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

}