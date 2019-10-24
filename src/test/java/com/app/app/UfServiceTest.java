package com.app.app;

import com.app.app.model.dto.UfDTO;
import com.app.app.repository.UfRepository;
import com.app.app.service.UfService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;

import java.time.ZonedDateTime;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.AFTER_TEST_METHOD;
import static org.springframework.test.context.jdbc.SqlConfig.TransactionMode.ISOLATED;

public class UfServiceTest extends TestSetup {

    @Autowired
    private UfService ufService;

    @Autowired
    private UfRepository ufRepository;


    @Test
    @Sql(scripts = "/cenarios/cleanDatabase.sql", executionPhase = AFTER_TEST_METHOD, config = @SqlConfig(transactionMode = ISOLATED))
    public void testeSave(){
        UfDTO uf = UfDTO.builder()
                        .nome("Santa Catarina")
                        .sigla("SC")
                        .dataHora(ZonedDateTime.now())
                        .build();

        ufService.save(uf).toEntity();
        assertNotNull(ufRepository.findAll().stream().findFirst().orElse(null));
    }

    @Test
    @Sql(scripts = "/cenarios/insert_uf.sql", config = @SqlConfig(transactionMode = ISOLATED))
    @Sql(scripts = "/cenarios/cleanDatabase.sql", executionPhase = AFTER_TEST_METHOD, config = @SqlConfig(transactionMode = ISOLATED))
    public void testFindAll(){
        List<UfDTO> ufs = ufService.findAll();
        assertEquals(1, ufs.size());
    }
}
