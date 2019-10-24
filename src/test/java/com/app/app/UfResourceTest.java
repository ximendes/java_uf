package com.app.app;
import com.app.app.model.dto.UfDTO;
import com.app.app.service.UfService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UfResourceTest {

    private ZonedDateTime zoned = ZonedDateTime.of(2019, 1, 1, 0, 0, 0, 0, ZoneId.systemDefault());
    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UfService ufService;

    @Before
    public void init() {
        UfDTO uf = UfDTO.builder().id(1L).nome("Santa Catarina").sigla("SC").dataHora(zoned).build();
        when(ufService.findById(1L)).thenReturn(uf);
    }

    @Test
    public void testFindById() throws Exception {

        mockMvc.perform(get("/api/uf/1"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.id", is(1)))
               .andExpect(jsonPath("$.nome", is("Santa Catarina")))
               .andExpect(jsonPath("$.sigla", is("SC")))
               .andExpect(jsonPath("$.dataHora", is("2019-01-01T00:00:00-02:00")));

        verify(ufService, times(1)).findById(1L);

    }

    @Test
    public void testSaveUfTest() throws Exception {

        UfDTO uf = UfDTO.builder().id(1L).nome("Santa Catarina").sigla("SC").build();
        when(ufService.save(any(UfDTO.class))).thenReturn(uf);

        mockMvc.perform(post("/api/uf/")
               .content(objectMapper.writeValueAsString(uf))
               .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.id", is(1)))
               .andExpect(jsonPath("$.nome", is("Santa Catarina")))
               .andExpect(jsonPath("$.sigla", is("SC")));

        verify(ufService, times(1)).save(any(UfDTO.class));

    }

    @Test
    public void testDeleteById() throws Exception {
        doNothing().when(ufService).deleteById(1L);

        mockMvc.perform(delete("/api/uf/1"))
               .andExpect(status().isNoContent());

        verify(ufService, times(1)).deleteById(1L);
    }
}
