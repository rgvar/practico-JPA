package org.example;

import lombok.*;
import javax.persistence.*;
import java.io.Serializable;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "factura")
@EqualsAndHashCode(exclude = "factura")
@Builder
@Entity
public class DetalleFactura implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int cantidad;
    private int subtotal;

    // Relación artículo - detalles
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "fk_articulo")
    private Articulo articulo;

    // Relación factura - detalles
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "fk_factura")
    private Factura factura;
}
