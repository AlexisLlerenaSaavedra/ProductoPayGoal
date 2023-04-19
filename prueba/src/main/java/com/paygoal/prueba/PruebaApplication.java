package com.paygoal.prueba;

import com.paygoal.prueba.models.Producto;
import com.paygoal.prueba.services.ProductoService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class PruebaApplication {

	public static void main(String[] args) {
		SpringApplication.run(PruebaApplication.class, args);
	}
	@Bean
	public CommandLineRunner initData(ProductoService productoService){
		return args ->{
			Producto producto1=new Producto(1,"Producto1","Descripcion de producto 1",15.30,20);
			Producto producto2=new Producto(2,"Producto2","Descripcion de producto 2",10.30,60);
			Producto producto3=new Producto(3,"Producto3","Descripcion de producto 3",150.30,50);
			Producto producto4=new Producto(4,"Producto4","Descripcion de producto 4",105.30,80);

			productoService.saveProducto(producto1);
			productoService.saveProducto(producto2);
			productoService.saveProducto(producto3);
			productoService.saveProducto(producto4);
		};
	}

}
