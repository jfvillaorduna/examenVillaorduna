package com.examen.examen.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "persona")
@Getter
@Setter
public class PersonaEntity {
    @Id
    //Profesor, aqui no utilizo generatedValue para considerar el id como documento ingresando manualmente en Postman
    private Long id;
    private String nombre;
    private String apellido;
    private String estado;

    @OneToOne(cascade = {CascadeType.MERGE, CascadeType.DETACH})
    @JoinColumn(name = "direccion_id", referencedColumnName = "id")
    private DireccionEntity direccionEntity;

    @JsonManagedReference
    @OneToMany(mappedBy = "persona", cascade = CascadeType.ALL)
    private List<PedidoEntity> pedidos;
}
