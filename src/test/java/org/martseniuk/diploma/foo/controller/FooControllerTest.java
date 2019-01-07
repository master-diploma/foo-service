package org.martseniuk.diploma.foo.controller;

import org.martseniuk.diploma.foo.feign.BarFeignClient;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Roman_Martseniuk
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class FooControllerTest {

    private static final String GET_DEFAULT_CONFIG = "/api/config/default";
    private static final String GET_PROFILE_CONFIG = "/api/config/profile";
    private static final String GET_MOUNT_CONFIG = "/api/config/mount";
    private static final String GET_RELOAD_CONFIG = "/api/config/reload";
    private static final String GET_DYNAMIC_CONFIG = "/api/config/dynamic";
    private static final String GET_SECRET_CONFIG = "/api/config/secret";
    private static final String CALL_DUMB_CLIENT = "/api/rpc/dumb-client";
    private static final String CALL_FEIGN_CLIENT = "/api/rpc/feign-client";
    private static final String POST_MESSAGE = "/api/async/message?setting=Hello";

    @Autowired
    private WebApplicationContext webApplicationContext;

    @MockBean
    private BarFeignClient barFeignClient;

    @MockBean
    private RabbitTemplate rabbitTemplate;

    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }

    @Test
    public void testGetDefaultConfig() throws Exception {
        mockMvc.perform(get(GET_DEFAULT_CONFIG)
                .content("config:foo.config.default, src:application.yml"))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetProfileConfig() throws Exception {
        mockMvc.perform(get(GET_PROFILE_CONFIG)
                .content("config:foo.config.profile, src:application.yml"))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetMountConfig() throws Exception {
        mockMvc.perform(get(GET_MOUNT_CONFIG)
                .content("config:foo.config.mount, src:application.yml"))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetReloadConfig() throws Exception {
        mockMvc.perform(get(GET_RELOAD_CONFIG)
                .content("config:foo.config.reload, src:application.yml"))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetDynamicConfig() throws Exception {
        mockMvc.perform(get(GET_DYNAMIC_CONFIG)
                .content("config:foo.config.dynamic, src:application.yml"))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetSecretConfig() throws Exception {
        mockMvc.perform(get(GET_SECRET_CONFIG)
                .content("config:foo.config.secret, src:application.yml"))
                .andExpect(status().isOk());
    }

    @Test
    public void testCallFeignClient() throws Exception {
        when(barFeignClient.getSetting()).thenReturn("");
        mockMvc.perform(get(CALL_FEIGN_CLIENT))
                .andExpect(status().isOk());
    }

    @Test
    public void testPostMessage() throws Exception {
        doNothing().when(rabbitTemplate).convertAndSend(anyString(), anyString());
        mockMvc.perform(get(POST_MESSAGE))
                .andExpect(status().isOk());
    }
}