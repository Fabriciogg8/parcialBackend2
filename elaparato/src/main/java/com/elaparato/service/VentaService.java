package com.elaparato.service;

import com.elaparato.dto.ProductoDTO;
import com.elaparato.dto.VentaDTO;
import com.elaparato.model.Producto;
import com.elaparato.model.Venta;
import com.elaparato.repository.IProductoRepository;
import com.elaparato.repository.IVentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

import java.util.ArrayList;
import java.util.List;
import java.util.ArrayList;
import java.util.List;

@Service
public class VentaService implements IVentaService {

    @Autowired
    private IVentaRepository ventaRepo;

    @Autowired
    private IProductoRepository productoRepo;

    @Override
    public List<Venta> getVentas() {
        return ventaRepo.findAll();
    }

    @Override
    public void saveVenta(VentaDTO ventaDTO) {
        Venta venta = new Venta();
        venta.setFecha(ventaDTO.getFecha());

        List<Producto> productos = new ArrayList<>();
        for (ProductoDTO prodDTO : ventaDTO.getProductos()) {
            Producto producto = productoRepo.findById(prodDTO.getIdProducto())
                    .orElseThrow(() -> new IllegalArgumentException("Producto con ID " + prodDTO.getIdProducto() + " no existe."));
            productos.add(producto);
        }

        venta.setListaProductos(productos);
        ventaRepo.save(venta);
    }

    @Override
    public void editVenta(VentaDTO ventaDTO) {
        Venta venta = ventaRepo.findById(ventaDTO.getId())
                .orElseThrow(() -> new IllegalArgumentException("Venta con ID " + ventaDTO.getId() + " no existe."));

        venta.setFecha(ventaDTO.getFecha());

        List<Producto> productos = new ArrayList<>();
        for (ProductoDTO prodDTO : ventaDTO.getProductos()) {
            Producto producto = productoRepo.findById(prodDTO.getIdProducto())
                    .orElseThrow(() -> new IllegalArgumentException("Producto con ID " + prodDTO.getIdProducto() + " no existe."));
            productos.add(producto);
        }

        venta.setListaProductos(productos);
        ventaRepo.save(venta);
    }

    @Override
    public void deleteVenta(int id) {
        ventaRepo.deleteById(id);
    }

    @Override
    public Venta findVenta(int id) {
        return ventaRepo.findById(id).orElse(null);
    }
}