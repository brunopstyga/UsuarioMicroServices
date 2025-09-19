package org.proyectdemo.service;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.proyectdemo.Util.ItemDataTransferObjects;
import org.proyectdemo.Util.OrdenDTO;
import org.proyectdemo.Util.ProductoDTO;
import org.proyectdemo.model.Orden;
import org.proyectdemo.model.Producto;
import org.proyectdemo.model.Usuario;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

@Service
public class UsuarioService {

    private final ObjectMapper objectMapper;

    public UsuarioService() {
        this.objectMapper = new ObjectMapper();
    }

    public List<Usuario> obtenerUsuarios() {
        try (InputStream is = getClass().getResourceAsStream("/usuarios.json")) {
            if (is == null) {
                throw new RuntimeException("No se encontró el archivo usuarios.json");
            }
            return objectMapper.readValue(is, new TypeReference<>() {});
        } catch (Exception e) {
            e.printStackTrace();
            return List.of();
        }
    }

    /**
     * Retrieves users in a simplified DTO format ({id, label}).
     * <p>
     * The label is built as "nombre apellido", sorted alphabetically by nombre.
     *
     * @return a list of {@link ItemDataTransferObjects} for use in pickers
     */
    public List<ItemDataTransferObjects> obtenerUsuariosConvert() {
        return obtenerUsuarios().stream()
                .sorted(Comparator.comparing(Usuario::getNombre))
                .map(u -> new ItemDataTransferObjects(u.getId(), u.getNombre(), u.getApellido()))
                .toList();
    }

    /**
     * Retrieves all orders of a specific user as {@link OrdenDTO}.
     * <p>
     * Finds the user by id and maps each order to a DTO, including its products as {@link ProductoDTO}.
     * Protects against null lists for orders and products.
     *
     * @param idUsuario the ID of the user whose orders are retrieved
     * @return a list of {@link OrdenDTO} representing the user's orders
     */
    public List<OrdenDTO> obtenerOrdenes(Long idUsuario) {
        return obtenerUsuarios().stream()
                .filter(u -> Objects.equals(u.getId(), idUsuario))
                .findFirst()
                .map(u -> {
                    List<Orden> ordenes = u.getOrdenes() != null ? u.getOrdenes() : List.of();
                    return ordenes.stream()
                            .map(o -> {
                                List<Producto> productos = o.getProductos() != null ? o.getProductos() : List.of();
                                List<ProductoDTO> productosDTO = productos.stream()
                                        .map(p -> new ProductoDTO(
                                                p.getProductoId(),
                                                p.getNombre(),
                                                p.getCantidad(),
                                                p.getPrecioUnitario()
                                        )).toList();
                                return new OrdenDTO(
                                        o.getOrdenId(),
                                        o.getFecha(),
                                        productosDTO
                                );
                            }).toList();
                })
                .orElse(List.of());
    }


    /**
     * Retrieves all products of a specific order for a given user.
     * <p>
     * Finds the order by user ID and order ID, and returns its products as {@link ProductoDTO}.
     * Returns an empty list if the user has no orders or the order does not exist.
     *
     * @param idUsuario the ID of the user who owns the order
     * @param idOrden   the ID of the order
     * @return a list of {@link ProductoDTO} representing products of the order
     */
    public List<ProductoDTO> obtenerProductosDeOrdenDTO(Long idUsuario, Long idOrden) {
        List<OrdenDTO> ordenes = obtenerOrdenes(idUsuario);
        if (ordenes == null || ordenes.isEmpty()) {
            return List.of();
        }

        return ordenes.stream()
                .filter(o -> o.getId() != null && o.getId().equals(idOrden))
                .findFirst()
                .map(o -> {
                    List<ProductoDTO> productos = o.getProductos();
                    return productos != null ? productos : List.<ProductoDTO>of();
                })
                .orElse(List.of());
    }

}

