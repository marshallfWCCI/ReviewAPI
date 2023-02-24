package com.wcci.reviews.restControllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wcci.reviews.entities.Category;
import com.wcci.reviews.entities.HashTag;
import com.wcci.reviews.entities.Review;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD) // reset the database for each test
@AutoConfigureMockMvc
public class CategoryControllerTest {
    @Autowired
    private MockMvc mvc;

    @Test
    public void getCategories() throws Exception {
        // You *can* expect multiple things
        // In this case I expect a list of zero categories.
        mvc.perform(MockMvcRequestBuilders.get("/categories").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("[]")))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void addCategory() throws Exception {
        final Category category = new Category("Romance", "Happily-ever-after");

        // If I perform a POST against /categories
        // and I telling Spring that I want JSON-format back
        // and I telling Spring that I'm giving it JSON-formatted text
        // and pass in {"name": "Romance", "description": "..."}
        // then I'll get an HTTP OK (code 200) in response.
        mvc.perform(MockMvcRequestBuilders.post("/categories")
                        .accept(MediaType.APPLICATION_JSON) // I'm expecting JSON back because I'm a program and want recordized date
                        .contentType(MediaType.APPLICATION_JSON) // I'm a program and sending you JSON-encoded data
                        .content(getJsonContent(category)))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void addCategories() throws Exception {
        final Category category1 = new Category("Romance", "Happily-ever-after");
        final Category category2 = new Category("Climatology", "*Not* Happily-ever-after");

        // If I do an http POST to /categories and pass in {"name": "Romance", "description": "Happily-ever-after"}
        // then I expect to get an "OK" back.
        mvc.perform(MockMvcRequestBuilders.post("/categories")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(getJsonContent(category1)))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());

        // If I do an http POST to /categories and pass in {"name": "Climatology", "description": "Not Happily-ever-after"}
        // then I expect to get an "OK" back.
        mvc.perform(MockMvcRequestBuilders.post("/categories")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(getJsonContent(category2)))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());

        // At this point, we have two records in MySQL (or something similar)
        // If I do an http GET to /categories, and pass in nothing else, I expect to get
        // [{"name": "Romance", "description": "Happily-ever-after"},
        //  {"name": "Climatology", "description": "Not Happily-ever-after"}]
        mvc.perform(MockMvcRequestBuilders.get("/categories").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(getJsonContent(new Category[]{category1, category2})))
                .andDo(MockMvcResultHandlers.print());

        // And then if I do an http DELETE to /categories/Romance, I expect to get an OK back
        mvc.perform(MockMvcRequestBuilders.delete("/categories/" + category1.getName()).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        // If I do an http GET to /categories, and pass in nothing else, I expect to get
        // [{"name": "Climatology", "description": "Not Happily-ever-after"}]
        mvc.perform(MockMvcRequestBuilders.get("/categories").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(getJsonContent(new Category[]{category2})))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public final void failures() throws Exception {
        // Nonexistent entities return 404 "NOT FOUND" errors
        mvc.perform(MockMvcRequestBuilders.get("/reviews/99").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
        mvc.perform(MockMvcRequestBuilders.get("/categories/99").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
        mvc.perform(MockMvcRequestBuilders.get("/tags/99").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
        mvc.perform(MockMvcRequestBuilders.delete("/categories/99").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
        mvc.perform(MockMvcRequestBuilders.delete("/reviews/99").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public final void addReview() throws Exception {
        final Category category = new Category("Climatology", "*Not* Happily-ever-after");

        final Review review = new Review(category,
                "Climate Change 2022: Impacts, Adaptation, and Vulnerability",
                "IPCC",
                "I did not think I could be more scared");
        final String withoutId = getJsonContent(review);

        review.setId(1);

        // Create a review
        mvc.perform(MockMvcRequestBuilders.post("/reviews")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(withoutId))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(getJsonContent(review)))
                .andDo(MockMvcResultHandlers.print());

        // And then find it
        final Review[] reviews = new Review[]{review};
        mvc.perform(MockMvcRequestBuilders.get("/reviews").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(getJsonContent(reviews)))
                .andDo(MockMvcResultHandlers.print());

        // And find it by number
        mvc.perform(MockMvcRequestBuilders.get("/reviews/1").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(getJsonContent(review)))
                .andDo(MockMvcResultHandlers.print());

        // And then I can use PUT to change the review
        review.setCategory(category);
        mvc.perform(MockMvcRequestBuilders.put("/reviews/1")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(getJsonContent(review)))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());

        mvc.perform(MockMvcRequestBuilders.get("/reviews/1").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(getJsonContent(review)))
                .andDo(MockMvcResultHandlers.print());

        // The review starts with no tags
        mvc.perform(MockMvcRequestBuilders.get("/reviews/1/tags").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().json("[]"))
                .andDo(MockMvcResultHandlers.print());

        mvc.perform(MockMvcRequestBuilders.get("/tags").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().json("[]"))
                .andDo(MockMvcResultHandlers.print());

        final String tag1 = "best_seller";
        mvc.perform(MockMvcRequestBuilders.post("/reviews/1/tags/" + tag1).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        review.getTags().add(new HashTag(tag1));

        mvc.perform(MockMvcRequestBuilders.get("/tags").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(getJsonContent(new HashTag[]{new HashTag(tag1)})))
                .andDo(MockMvcResultHandlers.print());

        mvc.perform(MockMvcRequestBuilders.get("/tags/" + tag1).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(getJsonContent(new Review[]{review})))
                .andDo(MockMvcResultHandlers.print());

        mvc.perform(MockMvcRequestBuilders.delete("/reviews/1/tags/" + tag1).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        mvc.perform(MockMvcRequestBuilders.get("/tags/" + tag1).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(getJsonContent(new Review[]{})))
                .andDo(MockMvcResultHandlers.print());

        review.removeTag(new HashTag(tag1));

        mvc.perform(MockMvcRequestBuilders.get("/categories").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(getJsonContent(new Category[]{category})))
                .andDo(MockMvcResultHandlers.print());

        mvc.perform(MockMvcRequestBuilders.get("/categories/" + category.getName()).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(getJsonContent(new Review[]{review})))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public final void addTwoReviews() throws Exception {
        final Category category = new Category("Football", "Something");

        final Review review1 = new Review(category, "Jimmy",
                "Football Review", "body");
        final Review review2 = new Review(category, "Jimmy2",
                "Football Review2", "body2");
        final Review[] reviews = new Review[]{review1, review2};

        final String reviewWithoutID1 = getJsonContent(review1); // This is the review *without* an ID
        review1.setId(1);
        final String reviewWithID1 = getJsonContent(review1); // This is the review *with* an ID

        final String r2 = getJsonContent(review2);
        review2.setId(2);
        final String reviewWithID2 = getJsonContent(review2);

        // Create a new review using POST and then verify that the returned object contains an ID element
        mvc.perform(MockMvcRequestBuilders.post("/reviews")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(reviewWithoutID1))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(reviewWithID1))
                .andDo(MockMvcResultHandlers.print());

        mvc.perform(MockMvcRequestBuilders.post("/reviews")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(r2))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(reviewWithID2))
                .andDo(MockMvcResultHandlers.print());

        //GET reviews
        mvc.perform(MockMvcRequestBuilders.get("/reviews")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(getJsonContent(reviews)))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public final void addReviewWithTag() throws Exception {
        final Category category = new Category("Climatology", "*Not* Happily-ever-after");

        final Review review = new Review(category,
                "Climate Change 2022: Impacts, Adaptation, and Vulnerability",
                "IPCC",
                "I did not think I could be more scared");
        review.addTag(new HashTag("Do_not_read"));
        final String withoutId = getJsonContent(review);

        review.setId(1);

        // Create a review
        mvc.perform(MockMvcRequestBuilders.post("/reviews")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(withoutId))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(getJsonContent(review)))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public final void addReviewWithTag2() throws Exception {
        final Category category = new Category("Climatology", "*Not* Happily-ever-after");

        final Review review = new Review(category,
                "Climate Change 2022: Impacts, Adaptation, and Vulnerability",
                "IPCC",
                "I did not think I could be more scared");
        review.addTag(new HashTag("Do_not_read"));
        review.addTag(new HashTag("Do_not_even_look_at"));
        final String withoutId = getJsonContent(review);

        review.setId(1);

        // Create a review
        mvc.perform(MockMvcRequestBuilders.post("/reviews")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(withoutId))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(getJsonContent(review)))
                .andDo(MockMvcResultHandlers.print());
    }

    private static String getJsonContent(Object o) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(o);
    }
}
