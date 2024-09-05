package org.example;

import lombok.*;
import javax.persistence.*;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Domicilio implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombreCalle;
    private int numero;

    // Relación cliente - domicilio
    @OneToOne(mappedBy = "domicilio")
    private Cliente cliente;
}
