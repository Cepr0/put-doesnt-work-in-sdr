package io.github.cepr0.putissue;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.util.Locale;

import static org.springframework.hateoas.MediaTypes.HAL_JSON;
import static org.springframework.http.MediaType.APPLICATION_JSON;

@SuppressWarnings("SpringJavaAutowiredMembersInspection")
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public abstract class BaseTest {

    @Value("${spring.data.rest.base-path}")
    protected String BASE_PATH;
    
    protected String SELF_LINK = "_links.self.href";

    @Autowired private WebApplicationContext context;

    protected MockMvc mvc;

    @Before
    public void setUp() {
        this.mvc = MockMvcBuilders.webAppContextSetup(context)
                .defaultRequest(MockMvcRequestBuilders
                        .get(BASE_PATH)
                        .locale(Locale.US)
                        .contentType(APPLICATION_JSON)
                        .accept(HAL_JSON))
                .build();
    }
}
