import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.proyectdemo.Util.ItemDataTransferObjects;
import org.proyectdemo.Util.OrdenDTO;
import org.proyectdemo.Util.ProductoDTO;
import org.proyectdemo.model.Orden;
import org.proyectdemo.model.Producto;
import org.proyectdemo.model.Usuario;
import org.proyectdemo.repository.UsuarioRepository;
import org.proyectdemo.service.UsuarioService;
import org.proyectdemo.service.UsuarioServiceMongoDB;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.when;

public class UsuarioServiceMongoDbTest {

    private UsuarioServiceMongoDB usuarioServiceMongoDB;

    @BeforeEach
    void setUp() {
        UsuarioRepository usuarioRepository = Mockito.mock(UsuarioRepository.class);
        usuarioServiceMongoDB = new UsuarioServiceMongoDB(usuarioRepository);


        Usuario usuario = new Usuario();
        usuario.setId(1L);
        usuario.setNombre("Bruno");
        usuario.setApellido("Gonzalez");
        usuario.setEdad(28);
        usuario.setActivo(true);

        Orden orden = new Orden();
        orden.setOrdenId(101L);
        orden.setFecha("2025-09-19");

        Producto producto1 = new Producto();
        producto1.setProductoId(String.valueOf(1L));
        producto1.setNombre("Producto A");
        producto1.setCantidad(1);
        producto1.setPrecioUnitario(100.0);

        Producto producto2 = new Producto();
        producto2.setProductoId(String.valueOf(2L));
        producto2.setNombre("Producto B");
        producto2.setCantidad(1);
        producto2.setPrecioUnitario(50.0);

        orden.setProductos(List.of(producto1, producto2));
        usuario.setOrdenes(List.of(orden));

        when(usuarioRepository.findAll()).thenReturn(List.of(usuario));
        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuario));
    }

    /**
     * Tests that obtenerUsuarios() returns a non-null, non-empty list of users
     * loaded from the usuarios.json file.
     */
    @Test
    void testObtenerUsuarios() {
        List<Usuario> usuarios = usuarioServiceMongoDB.obtenerUsuarios();
        Assertions.assertNotNull(usuarios);
        assertFalse(usuarios.isEmpty(), "La lista de usuarios no debería estar vacía");
    }

    /**
     * Tests that obtenerUsuariosConvert() returns a non-null, non-empty list
     * of ItemDataTransferObjects representing users in {id, label} format.
     */
    @Test
    void testObtenerUsuariosConvert() {
        List<ItemDataTransferObjects> usuariosDTO = usuarioServiceMongoDB.obtenerUsuariosConvert();
        Assertions.assertNotNull(usuariosDTO);
        assertFalse(usuariosDTO.isEmpty(), "Debe devolver al menos un usuario convertido");
    }

    /**
     * Tests that obtenerOrdenes() returns a non-null, non-empty list of orders
     * for a given user id. Checks that user with id=1 has orders.
     */
    @Test
    void testObtenerOrdenes() {
        List<OrdenDTO> ordenes = usuarioServiceMongoDB.obtenerOrdenes(1L);
        Assertions.assertNotNull(ordenes);
        assertFalse(ordenes.isEmpty(), "El usuario con id=1 debería tener órdenes");
    }

    /**
     * Tests that obtenerProductosDeOrdenDTO() returns a non-null list of products
     * for a given user and order. Checks that order 101 has exactly 2 products.
     */
    @Test
    void testObtenerProductosDeOrdenDTO() {
        List<ProductoDTO> productos = usuarioServiceMongoDB.obtenerProductosDeOrdenDTO(1L, 101L);
        Assertions.assertNotNull(productos);
        assertEquals(2, productos.size(), "La orden 101 debería tener 2 productos");
    }
}
