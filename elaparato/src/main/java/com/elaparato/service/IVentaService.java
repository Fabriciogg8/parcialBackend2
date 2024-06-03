package com.elaparato.service;

import com.elaparato.dto.VentaDTO;
import com.elaparato.model.Producto;
import com.elaparato.model.Venta;

import java.util.List;

public interface IVentaService {

    public List<Venta> getVentas();

    public void saveVenta(VentaDTO ventaDTO);

    public void editVenta(VentaDTO ventaDTO);

    public void deleteVenta(int id);

    public Venta findVenta(int id);
}