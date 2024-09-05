package org.example;

import lombok.*;
import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Cliente implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String apellido;
    private int dni;

    // Relación cliente - domicilio
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_domicilio")
    private Domicilio domicilio;

    // Relación cliente - facturas
    @OneToMany(mappedBy = "cliente")
    @JoinColumn(name = "cliente_id")
    @Builder.Default
    private Set<Factura> facturas = new HashSet<>();
}
