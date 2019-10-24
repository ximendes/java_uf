package com.app.app.model.dto;


import com.app.app.model.entity.Uf;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.time.ZonedDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UfDTO implements Serializable {

    private Long id;

    @NotEmpty(message = "Nome Obrigatório")
    private String nome;

    @NotEmpty(message = "Sigla Obrigatório")
    private String sigla;

    private ZonedDateTime dataHora;

    public Uf toEntity(){
        return Uf.builder()
                 .id(this.id)
                 .nome(this.nome)
                 .sigla(this.sigla)
                 .dataHora(this.dataHora)
                 .build();
    }
}
