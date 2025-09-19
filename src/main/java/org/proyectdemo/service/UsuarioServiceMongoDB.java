package org.proyectdemo.service;

import org.proyectdemo.Util.ItemDataTransferObjects;
import org.proyectdemo.Util.OrdenDTO;
import org.proyectdemo.Util.ProductoDTO;
import org.proyectdemo.model.Usuario;
import org.proyectdemo.repository.UsuarioRepository;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class UsuarioServiceMongoDB {

    private final UsuarioRepository usuarioRepository;

    public UsuarioServiceMongoDB(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public List<Usuario> obtenerUsuarios() {
        return usuarioRepository.findAll();
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
     * Retrieves all orders of a specific user in DTO format.
     * <p>
     * Finds the user by ID, then maps each order into an {@link OrdenDTO},
     * which contains its ID, label (id + fecha), and list of {@link ProductoDTO}.
     * If no user or orders are found, an empty list is returned.
     *
     * @param idUsuario the ID of the user
     * @return a list of {@link OrdenDTO} representing the user’s orders
     */
    public List<OrdenDTO> obtenerOrdenes(Long idUsuario) {
        return obtenerUsuarios().stream()
                .filter(u -> Objects.equals(u.getId(), idUsuario))
                .findFirst()
                .map(u -> Optional.ofNullable(u.getOrdenes()).orElse(List.of()))
                .orElse(List.of()) // si no existe el usuario
                .stream()
                .map(o -> new OrdenDTO(
                        o.getOrdenId(),
                        o.getFecha(),
                        Optional.ofNullable(o.getProductos()).orElse(List.of())
                                .stream()
                                .map(p -> new ProductoDTO(
                                        p.getProductoId(),
                                        p.getNombre(),
                                        p.getCantidad(),
                                        p.getPrecioUnitario()
                                ))
                                .toList()
                ))
                .toList();
    }

    /**
     * Devuelve productos de una orden específica de un usuario.
     */
    public List<ProductoDTO> obtenerProductosDeOrdenDTO(Long idUsuario, Long idOrden) {
        return obtenerOrdenes(idUsuario).stream()
                .filter(o -> Objects.equals(o.getId(), idOrden))
                .findFirst()
                .map(OrdenDTO::getProductos)
                .orElse(List.of());
    }

}
