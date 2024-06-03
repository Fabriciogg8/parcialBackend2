package com.elaparato.controller;

import com.elaparato.dto.VentaDTO;
import com.elaparato.model.Producto;
import com.elaparato.model.Venta;
import com.elaparato.service.IProductoService;
import com.elaparato.service.IVentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ventas")
public class VentaController {

    @Autowired
    private IVentaService ventServ;

    //crear una nueva venta
    @PostMapping("/create")
    public ResponseEntity<String> createVenta(@RequestBody VentaDTO ventaDTO) {
        try {
            ventServ.saveVenta(ventaDTO);
            return ResponseEntity.ok("Venta creada correctamente");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    //obtener todas las ventas
    @GetMapping("/getall")
    public List<Venta> getVentas () {
        return ventServ.getVentas();
    }

    //Modificar los datos de una venta
    @PutMapping("/edit")
    public ResponseEntity<String> editVenta(@RequestBody VentaDTO ventaDTO) {
        try {
            ventServ.editVenta(ventaDTO);
            return ResponseEntity.ok("Venta editada correctamente");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
    @DeleteMapping("/delete/{id}")
    public String deleteVenta(@PathVariable int id) {
        ventServ.deleteVenta(id);
        return "Venta eliminada correctamente";
    }
    @GetMapping("/find/{id}")
    public ResponseEntity<Venta> findVenta(@PathVariable int id) {
        Venta venta = ventServ.findVenta(id);
        if (venta != null) {
            return ResponseEntity.ok(venta);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

}

