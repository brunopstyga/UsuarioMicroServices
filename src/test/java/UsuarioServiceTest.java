import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.proyectdemo.Util.ItemDataTransferObjects;
import org.proyectdemo.Util.OrdenDTO;
import org.proyectdemo.Util.ProductoDTO;
import org.proyectdemo.model.Usuario;
import org.proyectdemo.service.UsuarioService;

import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;

public class UsuarioServiceTest {
    private UsuarioService usuarioService;

    @BeforeEach
    void setUp() {
        usuarioService = new UsuarioService();
    }

    @Test
    void testObtenerUsuarios() {
        List<Usuario> usuarios = usuarioService.obtenerUsuarios();
        assertNotNull(usuarios);
        assertFalse(usuarios.isEmpty(), "La lista de usuarios no debería estar vacía");
    }

    @Test
    void testObtenerUsuariosConvert() {
        List<ItemDataTransferObjects> usuariosDTO = usuarioService.obtenerUsuariosConvert();
        assertNotNull(usuariosDTO);
        assertFalse(usuariosDTO.isEmpty(), "Debe devolver al menos un usuario convertido");
    }

    @Test
    void testObtenerOrdenes() {
        List<OrdenDTO> ordenes = usuarioService.obtenerOrdenes(1L);
        assertNotNull(ordenes);
        assertFalse(ordenes.isEmpty(), "El usuario con id=1 debería tener órdenes");
    }

    @Test
    void testObtenerProductosDeOrdenDTO() {
        List<ProductoDTO> productos = usuarioService.obtenerProductosDeOrdenDTO(1L, 101L);
        assertNotNull(productos);
        assertEquals(2, productos.size(), "La orden 101 debería tener 2 productos");
    }
}
