package org.proyectdemo.controller;

import org.proyectdemo.Util.ItemDataTransferObjects;
import org.proyectdemo.Util.OrdenDTO;
import org.proyectdemo.Util.ProductoDTO;
import org.proyectdemo.Util.Resource;
import org.proyectdemo.service.UsuarioService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins = "*")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    // Trae usuarios para picker (DTO)
    @GetMapping("/picker")
    public Resource<List<ItemDataTransferObjects>> getUsuariosParaPicker() {
        try {
            List<ItemDataTransferObjects> usuarios = usuarioService.obtenerUsuariosConvert();
            if (usuarios.isEmpty()) {
                return Resource.error("No se encontraron usuarios", List.of());
            }
            return Resource.success(usuarios);
        } catch (Exception e) {
            return Resource.error("Error al obtener usuarios: " + e.getMessage(), null);
        }
    }

    // Trae órdenes de un usuario para picker (DTO)
    @GetMapping("/{id}/ordenes/picker")
    public Resource<List<OrdenDTO>> getOrdenesParaPicker(@PathVariable Long id) {
        System.out.println("GET /usuarios/" + id + "/ordenes/picker called");
        try {
            List<OrdenDTO> ordenes = usuarioService.obtenerOrdenes(id);
            System.out.println("Ordenes encontradas: " + ordenes.size());
            return ordenes.isEmpty()
                    ? Resource.error("No se encontraron órdenes para el usuario " + id, List.of())
                    : Resource.success(ordenes);
        } catch (Exception e) {
            e.printStackTrace();
            return Resource.error("Error al obtener órdenes: " + e.getMessage(), null);
        }
    }

    // Trae productos de una orden en formato DTO (estructura cruda)
    @GetMapping("/{idUsuario}/ordenes/{idOrden}/productos")
    public Resource<List<ProductoDTO>> getProductosDeOrden(
            @PathVariable Long idUsuario,
            @PathVariable Long idOrden
    ) {
        try {
            List<ProductoDTO> productos = usuarioService.obtenerProductosDeOrdenDTO(idUsuario, idOrden);
            if (productos.isEmpty()) {
                return Resource.error(
                        "No se encontraron productos para la orden " + idOrden + " del usuario " + idUsuario,
                        List.of()
                );
            }
            return Resource.success(productos);
        } catch (Exception e) {
            return Resource.error("Error al obtener productos: " + e.getMessage(), null);
        }
    }
}
