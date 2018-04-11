package com.thoughtworks.ddd.order.interfaces.controller;

import com.thoughtworks.ddd.order.APIBaseTest;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class OrderControllerTest extends APIBaseTest {


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

        this.mockMvc.perform(get(location + "/payments")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
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

    @Test
    public void shouldCancelTheOrder() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(post("/api/orders")
                .contentType(MediaType.APPLICATION_JSON)
                .content(withJson("order.json")))
                .andExpect(status().isCreated())
                .andReturn();

        String location = mvcResult.getResponse().getHeader("location");

        this.mockMvc.perform(post(location + "/payments")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

        this.mockMvc.perform(post(location + "/status/canceled")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

    }

    private String id(String location) {
        return location.substring(location.lastIndexOf("/") + 1);
    }
}