
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(classes = org.proyectdemo.Application.class)
@AutoConfigureMockMvc
public class UsuarioControllerTest {

    @Autowired
    private MockMvc mockMvc;


    /**
     * Tests the GET /usuarios/picker endpoint.
     * Ensures that the API returns a successful response with an array of users
     * formatted as {id, label}, and validates the first user entry.
     */
    @Test
    void testGetUsuariosParaPicker() throws Exception {
        mockMvc.perform(get("/usuarios/picker"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("SUCCESS"))
                .andExpect(jsonPath("$.data").isArray())
                .andExpect(jsonPath("$.data[0].label").value("Bruno Gonzalez"))
                .andExpect(jsonPath("$.data[0].id").value(1));
    }

    /**
     * Tests the GET /usuarios/{id}/ordenes/picker endpoint.
     * Ensures that the API returns the list of orders for a given user,
     * with each order represented as {id, label}. Validates the first order.
     */
    @Test
    void testGetOrdenesParaPicker() throws Exception {
        mockMvc.perform(get("/usuarios/1/ordenes/picker"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("SUCCESS"))
                .andExpect(jsonPath("$.data").isArray())
                .andExpect(jsonPath("$.data[0].label").value("Orden 101 - 2025-09-10"))
                .andExpect(jsonPath("$.data[0].id").value(101));
    }

    /**
     * Tests the GET /usuarios/{idUsuario}/ordenes/{idOrden}/productos endpoint.
     * Ensures that the API returns the list of products for a specific order
     * in the format {id, label, cantidad, precioUnitario}, and validates the first product.
     */
    @Test
    void testGetProductosDeOrden() throws Exception {
        mockMvc.perform(get("/usuarios/1/ordenes/101/productos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("SUCCESS"))
                .andExpect(jsonPath("$.data").isArray())
                .andExpect(jsonPath("$.data[0].label").value("Teclado Mecánico"))
                .andExpect(jsonPath("$.data[0].id").value("A123"))
                .andExpect(jsonPath("$.data[0].cantidad").value(1))
                .andExpect(jsonPath("$.data[0].precioUnitario").value(120.50));
    }
}
