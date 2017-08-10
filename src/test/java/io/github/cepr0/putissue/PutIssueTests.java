package io.github.cepr0.putissue;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.endsWith;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Cepro
 *         2017-08-10
 */
public class PutIssueTests extends BaseTest {

    @Test
    public void putManyToOneTest() throws Exception {

        mvc.perform(put(BASE_PATH + "/persons/1")
                .content("{\n" +
                        "  \"name\": \"person1u\",\n" +
                        "  \"address\": \"" + BASE_PATH + "/addresses/2\"\n" +
                        "}"))
                .andDo(print())
                .andExpect(jsonPath("$.name", is("person1u")))
                .andExpect(status().isOk());

        mvc.perform(get(BASE_PATH + "/persons/1/address"))
                .andDo(print())
                .andExpect(jsonPath("$.street", is("address2")))
                .andExpect(status().isOk());
    }

    @Test
    public void putOneToManyTest() throws Exception {

        String parent1 = "/parents/1";
        String child3 = "/children/3";
        String child4 = "/children/4";
        String collection = "children";

        putOneToMany(parent1, child3, child4, collection);
    }

    @Test
    public void putBiOneToManyTest() throws Exception {

        String parent1 = "/biParents/1";
        String child3 = "/biChildren/3";
        String child4 = "/biChildren/4";
        String collection = "biChildren";

        putOneToMany(parent1, child3, child4, collection);
    }

    private void putOneToMany(String parent1, String child3, String child4, String collection) throws Exception {
        mvc.perform(put(BASE_PATH + parent1)
                .content("{\n" +
                        "  \"name\": \"parent1u\",\n" +
                        "  \"children\": [\n" +
                        "      \"" + BASE_PATH + child3 + "\",\n" +
                        "      \"" + BASE_PATH + child4 + "\"\n" +
                        "    ]\n" +
                        "}"))
                .andDo(print())
                .andExpect(jsonPath("$.name", is("parent1u")))
                .andExpect(status().isOk());

        mvc.perform(get(BASE_PATH + parent1 + "/children"))
                .andDo(print())
                .andExpect(jsonPath("$.." + collection + ".*", hasSize(2)))
                .andExpect(jsonPath("$._embedded." + collection + "[0]." + SELF_LINK, endsWith(child3)))
                .andExpect(jsonPath("$._embedded." + collection + "[1]." + SELF_LINK, endsWith(child4)))
                .andExpect(status().isOk());
    }
}
