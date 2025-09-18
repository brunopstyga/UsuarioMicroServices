
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
    @Test
    void testGetUsuariosParaPicker() throws Exception {
        mockMvc.perform(get("/usuarios/picker"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("SUCCESS"))
                .andExpect(jsonPath("$.data").isArray())
                .andExpect(jsonPath("$.data[0].label").value("Bruno Gonzalez"))
                .andExpect(jsonPath("$.data[0].id").value(1));
    }

    @Test
    void testGetOrdenesParaPicker() throws Exception {
        mockMvc.perform(get("/usuarios/1/ordenes/picker"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("SUCCESS"))
                .andExpect(jsonPath("$.data").isArray())
                .andExpect(jsonPath("$.data[0].label").value("Orden 101 - 2025-09-10"))
                .andExpect(jsonPath("$.data[0].id").value(101));
    }

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
