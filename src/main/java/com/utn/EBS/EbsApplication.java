package com.utn.EBS;

import Entidades.Producto;
import Entidades.Rubro;
import Entidades.Usuario;
import Enumeraciones.Rol;
import Enumeraciones.TipoProducto;
import Repositorios.RubroRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class EbsApplication {

	public static void main(String[] args) {
		SpringApplication.run(EbsApplication.class, args);
	}
	@Bean
	CommandLineRunner init( RubroRepository rubroRepository){
		/* ACÁ SE TIENEN QUE CARGAR TODOS LOS DATOS */

		/*ALTA RUBROS*/
		/*TOMAMOS COMO RUBROS PARA ESTE SISTEMA: ENTRADA, BEBIDA, PLATOPRINCIPAL Y POSTRE*/
		Rubro rubro01 = Rubro.builder()
				.denominacion("Entrada")
				.build();
		Rubro rubro02 = Rubro.builder()
				.denominacion("Bebida")
				.build();
		Rubro rubro03 = Rubro.builder()
				.denominacion("Plato_Principal")
				.build();
		Rubro rubro04 = Rubro.builder()
				.denominacion("Postre")
				.build();
		Rubro rubro05 = Rubro.builder()
				.denominacion("Cocina")
				.build();
		/*GUARDO LOS RUBROS*/
		rubroRepository.save(rubro01);
		rubroRepository.save(rubro02);
		rubroRepository.save(rubro03);
		rubroRepository.save(rubro04);
		rubroRepository.save(rubro05);


		/*ALTA PRODUCTOS*/
		Producto producto1 = Producto.builder()
				.receta("Papas naturales freidas con aceite de girasol")
				.denominacion("Papas Fritas")
				.precioVenta(800)
				.tiempoEstimadoCocina(20)
				.unidadmedida("500gr")
				.stockAtual(10)
				.stockMinimo(3)
				.tipoProducto(TipoProducto.MANUFACTURADO)
				.build();
		rubro01.agregarProducto(producto1); /*ASIGNO RUBRO*/
		Producto producto2 = Producto.builder()
				.receta("Hamburguesa con pan de papa, un medallón de carne con una capa de chedar")
				.denominacion("Hamburguesa Clásica")
				.precioVenta(1200)
				.tiempoEstimadoCocina(30)
				.unidadmedida("500gr")
				.stockAtual(10)
				.stockMinimo(3)
				.tipoProducto(TipoProducto.MANUFACTURADO)
				.build();
		rubro03.agregarProducto(producto2);/*ASIGNO RUBRO*/
		Producto producto3 = Producto.builder()
				.receta("Ensalada de tomate y lechuga, incluye un sobrecito de sal y limón")
				.denominacion("Ensalada básica")
				.precioVenta(600)
				.tiempoEstimadoCocina(10)
				.unidadmedida("500gr")
				.stockAtual(8)
				.stockMinimo(2)
				.tipoProducto(TipoProducto.MANUFACTURADO)
				.build();
		rubro01.agregarProducto(producto3); /*ASIGNO RUBRO*/
		Producto producto4 = Producto.builder()
				.receta("Papa inicial, antes de ser trabajada")
				.denominacion("Papa insumo")
				.precioCompra(100)
				.tiempoEstimadoCocina(10)/*para pelarla y cortarla*/
				.unidadmedida("200")
				.stockAtual(20)
				.stockMinimo(5)
				.tipoProducto(TipoProducto.INSUMO)
				.build();
		rubro05.agregarProducto(producto4);
		Producto producto5 = Producto.builder()
				.receta("Bebida marca Coca-Cola")
				.denominacion("Coca-Cola chica")
				.precioCompra(500)
				.precioVenta(650)
				.tiempoEstimadoCocina(0)
				.unidadmedida("500ml")
				.stockAtual(20)
				.stockMinimo(5)
				.tipoProducto(TipoProducto.INSUMO)
				.build();
		rubro02.agregarProducto(producto5);






		/*MOSTRAR LOS PRODUCTOS ASOCIADOS A LOS RUBROS*/
		Rubro rubrorecu=  rubroRepository.findById(rubro01.getId()).orElse(null);
		if (rubrorecu != null) {
			System.out.println("Denominación: " + rubrorecu.getDenominacion());
			rubrorecu.mostrarProductos();
		}

		Rubro rubrorecu2=  rubroRepository.findById(rubro02.getId()).orElse(null);
		if (rubrorecu2!= null) {
			System.out.println("Denominación: " + rubrorecu2.getDenominacion());
			rubrorecu2.mostrarProductos();
		}
		Rubro rubrorecu3=  rubroRepository.findById(rubro03.getId()).orElse(null);
		if (rubrorecu3 != null) {
			System.out.println("Denominación: " + rubrorecu3.getDenominacion());
			rubrorecu3.mostrarProductos();
		}

		Rubro rubrorecu4=  rubroRepository.findById(rubro04.getId()).orElse(null);
		if (rubrorecu4!= null) {
			System.out.println("Denominación: " + rubrorecu4.getDenominacion());
			rubrorecu4.mostrarProductos();
		}
		//ALTA USUARIOS
		Usuario usuario1 = Usuario.builder()
				.nombre("Juan Perez")
				.password("Jp1999")
				.rol(Rol.Cliente)
				.build();
		Usuario usuario2 = Usuario.builder()
				.nombre("Alicia García")
				.password("Alicia123")
				.rol(Rol.Cajero)
				.build();

		Usuario usuario3 = Usuario.builder()
				.nombre("Tomas Venegas")
				.password("tomas1765")
				.rol(Rol.Delivery)
				.build();

		Usuario usuario4 = Usuario.builder()
				.nombre("Eduardo Martini")
				.password("MEduardo65")
				.rol(Rol.Administrador)
				.build();
	}
}

