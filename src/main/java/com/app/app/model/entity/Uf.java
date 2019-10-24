package com.app.app.model.entity;

import com.app.app.model.dto.UfDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.ZonedDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "uf")
public class Uf implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String nome;

    private String sigla;

    @Column(name = "data_hora")
    private ZonedDateTime dataHora;

    public UfDTO toDTO(){
        return UfDTO.builder()
                    .id(this.id)
                    .nome(this.nome)
                    .sigla(this.sigla)
                    .dataHora(this.dataHora)
                    .build();
    }


}
