package org.proyectdemo.service;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.proyectdemo.Util.ItemDataTransferObjects;
import org.proyectdemo.Util.OrdenDTO;
import org.proyectdemo.Util.ProductoDTO;
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

    /**
     * Lee usuarios desde usuarios.json
     */
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
     * Devuelve usuarios en formato {id, label}
     */
    public List<ItemDataTransferObjects> obtenerUsuariosConvert() {
        return obtenerUsuarios().stream()
                .sorted(Comparator.comparing(Usuario::getNombre))
                .map(u -> new ItemDataTransferObjects(u.getId(), u.getNombre(), u.getApellido()))
                .toList();
    }

    /**
     * Devuelve las órdenes de un usuario en formato {id, label}
     */
    public List<OrdenDTO> obtenerOrdenes(Long idUsuario) {
        return obtenerUsuarios().stream()
                .filter(u -> Objects.equals(u.getId(), idUsuario))
                .findFirst()
                .map(u -> u.getOrdenes().stream()
                        .map(o -> new OrdenDTO(
                                o.getOrdenId(),
                                o.getFecha(),
                                o.getProductos().stream()
                                        .map(p -> new ProductoDTO(
                                                p.getProductoId(),
                                                p.getNombre(),
                                                p.getCantidad(),
                                                p.getPrecioUnitario()
                                        )).toList()
                        )).toList())
                .orElse(List.of());
    }

    /**
     * Devuelve los productos de una orden en formato {id, label, cantidad, precioUnitario}
     */
    public List<ProductoDTO> obtenerProductosDeOrdenDTO(Long idUsuario, Long idOrden) {
        return obtenerOrdenes(idUsuario).stream()
                .filter(o -> o.getId().equals(idOrden))
                .findFirst()
                .map(OrdenDTO::getProductos)
                .orElse(List.of());
    }

}

