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
public class Factura implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int numero;
    private String fecha;
    private int total;

    // Relación cliente - facturas
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "fk_cliente")
    private Cliente cliente;

    // Relación factura - detalles
    @OneToMany(mappedBy = "factura",
               cascade = CascadeType.ALL,
               orphanRemoval = true)
    @JoinColumn(name = "factura_id")
    @Builder.Default
    private Set<DetalleFactura> detalles = new HashSet<>();

}
