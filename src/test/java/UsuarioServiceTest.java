import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.proyectdemo.Util.ItemDataTransferObjects;
import org.proyectdemo.Util.OrdenDTO;
import org.proyectdemo.Util.ProductoDTO;
import org.proyectdemo.model.Usuario;
import org.proyectdemo.service.UsuarioService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class UsuarioServiceTest {
    private UsuarioService usuarioService;

    @BeforeEach
    void setUp() {
        usuarioService = new UsuarioService();
    }

    /**
     * Tests that obtenerUsuarios() returns a non-null, non-empty list of users
     * loaded from the usuarios.json file.
     */
    @Test
    void testObtenerUsuarios() {
        List<Usuario> usuarios = usuarioService.obtenerUsuarios();
        Assertions.assertNotNull(usuarios);
        assertFalse(usuarios.isEmpty(), "La lista de usuarios no debería estar vacía");
    }

    /**
     * Tests that obtenerUsuariosConvert() returns a non-null, non-empty list
     * of ItemDataTransferObjects representing users in {id, label} format.
     */
    @Test
    void testObtenerUsuariosConvert() {
        List<ItemDataTransferObjects> usuariosDTO = usuarioService.obtenerUsuariosConvert();
        Assertions.assertNotNull(usuariosDTO);
        assertFalse(usuariosDTO.isEmpty(), "Debe devolver al menos un usuario convertido");
    }

    /**
     * Tests that obtenerOrdenes() returns a non-null, non-empty list of orders
     * for a given user id. Checks that user with id=1 has orders.
     */
    @Test
    void testObtenerOrdenes() {
        List<OrdenDTO> ordenes = usuarioService.obtenerOrdenes(1L);
        Assertions.assertNotNull(ordenes);
        assertFalse(ordenes.isEmpty(), "El usuario con id=1 debería tener órdenes");
    }

    /**
     * Tests that obtenerProductosDeOrdenDTO() returns a non-null list of products
     * for a given user and order. Checks that order 101 has exactly 2 products.
     */
    @Test
    void testObtenerProductosDeOrdenDTO() {
        List<ProductoDTO> productos = usuarioService.obtenerProductosDeOrdenDTO(1L, 101L);
        Assertions.assertNotNull(productos);
        assertEquals(2, productos.size(), "La orden 101 debería tener 2 productos");
    }
}
