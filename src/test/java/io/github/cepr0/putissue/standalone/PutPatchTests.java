package io.github.cepr0.putissue.standalone;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.util.Locale;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.hateoas.MediaTypes.HAL_JSON;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

/**
 * @author Cepro, 2017-08-13
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class PutPatchTests {
    
    @Autowired private ManRepo manRepo;
    @Autowired private WorkRepo workRepo;
    @Autowired private WebApplicationContext context;
    @Autowired private RepositoryRestConfiguration configuration;
    
    private String basePath;
    private MockMvc mvc;
    
    @Before
    public void setUp() throws Exception {
        
        basePath = configuration.getBaseUri().getPath();
        
        mvc = webAppContextSetup(context)
                .defaultRequest(MockMvcRequestBuilders
                        .get(basePath)
                        .locale(Locale.US)
                        .contentType(APPLICATION_JSON)
                        .accept(HAL_JSON))
                .build();
    }
    
    /**
     * PUT does not update (i.e. replace) associated resources from v.2.5.7
     */
    @Test
    @Transactional
    public void putManyToOneTest() throws Exception {
        update(put(basePath + "/men/1"));
    }
    
    /**
     * PATCH update (replace) as expected in v.2.5.7 (and above)
     */
    @Test
    @Transactional
    public void patchManyToOneTest() throws Exception {
        update(patch(basePath + "/men/1"));
    }
    
    private void update(MockHttpServletRequestBuilder updateRequest) throws Exception {
        
        // update Man - set his name to 'man1u' and change his work to work#2 (i.e. to 'position2')
        mvc.perform(updateRequest
                .content("{\n" +
                        "  \"name\": \"man1u\",\n" +
                        "  \"work\": \"" + basePath + "/works/2\"\n" +
                        "}"))
           .andDo(print())
           .andExpect(jsonPath("$.name", is("man1u")))
           .andExpect(status().isOk());
        
        // Expect that 'position' of new work is 'position2', i.e. work has been updated
        mvc.perform(get(basePath + "/men/1/work"))
           .andDo(print())
           .andExpect(jsonPath("$.position", is("position2")))
           .andExpect(status().isOk());
    }
}
