package com.elaparato.controller;
import com.elaparato.model.Producto;
import com.elaparato.service.IProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/productos")
public class ProductoController {

    @Autowired
    private IProductoService prodServ;

    //crear un nuevo producto
    @PostMapping("/create")
    public String createProducto(@RequestBody Producto prod) {
        prodServ.saveProducto(prod);
        return "Producto creado correctamente";
    }

    //obtener todos los productos
    @GetMapping("/getall")
    public List<Producto> getProductos () {
        return prodServ.getProductos();
    }

    //Modificar los datos de un producto
    @PutMapping("/edit")
    public ResponseEntity<String> editProducto(@RequestBody Producto prod) {
        try {
            prodServ.editProducto(prod);
            return ResponseEntity.ok("Producto editado correctamente");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
    @DeleteMapping("/delete/{id}")
    public String deleteProducto(@PathVariable int id) {
        prodServ.deleteProducto(id);
        return "Producto eliminado correctamente";
    }

    @GetMapping("/find/{id}")
    public Producto findProducto(@PathVariable int id) {
        return prodServ.findProducto(id);
    }
}