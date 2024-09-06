package org.example;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {
    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("example-unit");
        EntityManager em = emf.createEntityManager();
        System.out.println("Factory y Entity Manager creados ... ");

        try {
            em.getTransaction().begin();

            // Creación categorías
            Categoria maquinas = Categoria.builder()
                    .denominacion("Máquinas")
                    .build();
            Categoria repuestos = Categoria.builder()
                    .denominacion("Repuestos")
                    .build();

            // Creación artículos
            Articulo cultivadora = Articulo.builder()
                    .denominacion("Cultivadora")
                    .cantidad(4)
                    .precio(2500)
                    .build();
            Articulo fertilizadora = Articulo.builder()
                    .denominacion("Fertilizadora")
                    .cantidad(2)
                    .precio(5000)
                    .build();
            Articulo pua = Articulo.builder()
                    .denominacion("Pua")
                    .cantidad(100)
                    .precio(30)
                    .build();
            Articulo disco = Articulo.builder()
                    .denominacion("Disco arado")
                    .cantidad(200)
                    .precio(10)
                    .build();

            // Relaciones Categorías-Artículos
            maquinas.getArticulos().add(cultivadora);
            maquinas.getArticulos().add(fertilizadora);
            repuestos.getArticulos().add(pua);
            repuestos.getArticulos().add(disco);

            cultivadora.getCategorias().add(maquinas);
            fertilizadora.getCategorias().add(maquinas);
            pua.getCategorias().add(repuestos);
            disco.getCategorias().add(repuestos);

            // Creación clientes
            Cliente cliente1 = Cliente.builder()
                    .nombre("Rodrigo")
                    .apellido("Vargas")
                    .dni(41443045)
                    .build();
            Cliente cliente2 = Cliente.builder()
                    .nombre("José")
                    .apellido("de San Martin")
                    .dni(17780252)
                    .build();
            Cliente cliente3 = Cliente.builder()
                    .nombre("Julio Argentino")
                    .apellido("Roca")
                    .dni(18430707)
                    .build();

            // Creación domicilios
            Domicilio domicilio1 = Domicilio.builder()
                    .numero(4785)
                    .nombreCalle("Tomás Godoy Cruz")
                    .build();
            Domicilio domicilio2 = Domicilio.builder()
                    .numero(300)
                    .nombreCalle("General Espejo")
                    .build();
            Domicilio domicilio3 = Domicilio.builder()
                    .numero(273)
                    .nombreCalle("Coronel Rodriguez")
                    .build();

            // Relaciones Cliente-Domicilio
            cliente1.setDomicilio(domicilio1);
            cliente2.setDomicilio(domicilio2);
            cliente3.setDomicilio(domicilio3);

            domicilio1.setCliente(cliente1);
            domicilio2.setCliente(cliente2);
            domicilio3.setCliente(cliente3);

            // Creación detalles factura
            DetalleFactura detallePuasFactura1 = DetalleFactura.builder()
                    .cantidad(25)
                    .subtotal(750)
                    .build();
            DetalleFactura detalleDiscosFactura1 = DetalleFactura.builder()
                    .cantidad(20)
                    .subtotal(200)
                    .build();
            DetalleFactura detalleCultivadoraFactura1 = DetalleFactura.builder()
                    .cantidad(1)
                    .subtotal(2500)
                    .build();
            DetalleFactura detalleFertilizadoraFactura2 = DetalleFactura.builder()
                    .cantidad(1)
                    .subtotal(5000)
                    .build();
            DetalleFactura detalleCultivadoraFactura3 = DetalleFactura.builder()
                    .cantidad(2)
                    .subtotal(5000)
                    .build();
            DetalleFactura detalleFertilizadoraFactura3 = DetalleFactura.builder()
                    .cantidad(1)
                    .subtotal(5000)
                    .build();
            DetalleFactura detallePuasFactura3 = DetalleFactura.builder()
                    .cantidad(50)
                    .subtotal(1500)
                    .build();

            // Creación Facturas
            Factura factura1 = Factura.builder()
                    .numero(500)
                    .fecha("12-08-2024")
                    .total(3450)
                    .build();
            Factura factura2 = Factura.builder()
                    .numero(501)
                    .fecha("21-08-2024")
                    .total(5000)
                    .build();
            Factura factura3 = Factura.builder()
                    .numero(502)
                    .fecha("04-09-2024")
                    .total(11500)
                    .build();

            // Relaciones factura-detalles

            // Detalles factura 1
            detalleCultivadoraFactura1.setFactura(factura1);
            detallePuasFactura1.setFactura(factura1);
            detalleDiscosFactura1.setFactura(factura1);
            // Detalles factura 2
            detalleFertilizadoraFactura2.setFactura(factura2);
            // Detalles factura 3
            detalleCultivadoraFactura3.setFactura(factura3);
            detalleFertilizadoraFactura3.setFactura(factura3);
            detallePuasFactura3.setFactura(factura3);

            // Factura 1
            factura1.getDetalles().add(detalleCultivadoraFactura1);
            factura1.getDetalles().add(detalleDiscosFactura1);
            factura1.getDetalles().add(detallePuasFactura1);
            // Factura 2
            factura2.getDetalles().add(detalleFertilizadoraFactura2);
            // Factura 3
            factura3.getDetalles().add(detalleCultivadoraFactura3);
            factura3.getDetalles().add(detalleFertilizadoraFactura3);
            factura3.getDetalles().add(detallePuasFactura3);

            // Relaciones artículo-detalles

            // Cultivadoras
            detalleCultivadoraFactura1.setArticulo(cultivadora);
            detalleCultivadoraFactura3.setArticulo(cultivadora);

            cultivadora.getDetalles().add(detalleCultivadoraFactura1);
            cultivadora.getDetalles().add(detalleCultivadoraFactura3);

            // Fertilizadoras
            detalleFertilizadoraFactura2.setArticulo(fertilizadora);
            detalleFertilizadoraFactura3.setArticulo(fertilizadora);

            fertilizadora.getDetalles().add(detalleFertilizadoraFactura2);
            fertilizadora.getDetalles().add(detalleFertilizadoraFactura3);

            // Discos
            detalleDiscosFactura1.setArticulo(disco);

            disco.getDetalles().add(detalleDiscosFactura1);

            // Púas
            detallePuasFactura1.setArticulo(pua);
            detallePuasFactura3.setArticulo(pua);

            pua.getDetalles().add(detallePuasFactura1);
            pua.getDetalles().add(detallePuasFactura3);

            // Relación factura-cliente

            // Cliente 1
            cliente1.getFacturas().add(factura1);
            factura1.setCliente(cliente1);

            // Cliente 2
            cliente2.getFacturas().add(factura2);
            factura2.setCliente(cliente2);

            // Cliente 3
            cliente3.getFacturas().add(factura3);
            factura3.setCliente(cliente3);


            // Persistir facturas
            em.persist(factura1);
            em.persist(factura2);
            em.persist(factura3);

            em.getTransaction().commit();

        } catch (Exception e) {

            em.getTransaction().rollback();
            System.out.println(e.getMessage());
            System.out.println("No se pudo concretar la operación ... ");

        }

        em.close();
        emf.close();

    }
}
