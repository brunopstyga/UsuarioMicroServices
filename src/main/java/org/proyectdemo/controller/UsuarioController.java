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


    /**
     * Endpoint: GET /usuarios/picker
     * Returns a list of users in a simplified DTO format,
     * suitable for use in a dropdown or picker.
     * Each user is represented with an id and a label (full name).
     *
     * @return a {@link Resource} containing the user list or an error if none were found.
     */
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



    /**
     * Endpoint: GET /usuarios/{id}/ordenes/picker
     * Returns all orders associated with a specific user.
     * Each order is represented as an {@link OrdenDTO}
     * including its id, date, and products.
     *
     * @param id the user id
     * @return a {@link Resource} containing the list of orders, or an error if none were found.
     */
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



    /**
     * Endpoint: GET /usuarios/{idUsuario}/ordenes/{idOrden}/productos
     *
     * Returns the products of a specific order belonging to a user.
     * Each product is represented as a {@link ProductoDTO}
     * with id, name, quantity, and unit price.
     *
     * @param idUsuario the user id (owner of the order)
     * @param idOrden   the order id
     * @return a {@link Resource} containing the list of products, or an error if none were found.
     */
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
