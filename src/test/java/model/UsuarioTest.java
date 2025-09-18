package model;

import org.junit.jupiter.api.Test;
import org.proyectdemo.model.Orden;
import org.proyectdemo.model.Usuario;

import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class UsuarioTest {
    @Test
    void crearUsuarioDebeAsignarCampos() {
        Orden orden = new Orden();
        orden.setOrdenId(101L);

        Usuario usuario = new Usuario();
        usuario.setId(1L);
        usuario.setNombre("Bruno");
        usuario.setApellido("Gonzalez");
        usuario.setEmail("bruno@example.com");
        usuario.setEdad(28);
        usuario.setActivo(true);
        usuario.setOrdenes(List.of(orden));

        assertEquals(1L, usuario.getId());
        assertEquals("Bruno", usuario.getNombre());
        assertTrue(usuario.isActivo());
        assertEquals(1, usuario.getOrdenes().size());
    }
}
