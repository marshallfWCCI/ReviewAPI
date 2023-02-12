package com.wcci.reviews.restControllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wcci.reviews.entities.Category;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@AutoConfigureMockMvc
public class CategoryControllerTest {
    @Autowired
    private MockMvc mvc;

    @Test
    public void getCategories() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/categories").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("[]")));
    }

    @Test
    public void addCategory() throws Exception {
        final Category category = new Category("Romance", "Happily-ever-after");

        mvc.perform(MockMvcRequestBuilders.post("/categories")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(category)))
                .andExpect(status().isOk());
    }

    @Test
    public void addCategories() throws Exception {
        final Category category1 = new Category("Romance", "Happily-ever-after");
        final Category category2 = new Category("Climatology", "*Not* Happily-ever-after");

        mvc.perform(MockMvcRequestBuilders.post("/categories")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(category1)))
                .andExpect(status().isOk());
        mvc.perform(MockMvcRequestBuilders.post("/categories")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(category2)))
                .andExpect(status().isOk());

        final Category[] categories = new Category[]{category1, category2};
        final String jsonContent = new ObjectMapper().writeValueAsString(categories);

        mvc.perform(MockMvcRequestBuilders.get("/categories").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(jsonContent));
    }
}
