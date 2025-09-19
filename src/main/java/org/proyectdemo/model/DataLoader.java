package org.proyectdemo.model;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.proyectdemo.repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.List;

@Component
public class DataLoader implements CommandLineRunner {

    private final UsuarioRepository usuarioRepository;
    private final ObjectMapper objectMapper;

    public DataLoader(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
        this.objectMapper = new ObjectMapper();
    }

    /**
     * Runs on application startup.
     * <p>
     * Checks if the users collection is empty. If so, reads the
     * usuarios.json file from resources, deserializes it into a list of {@link Usuario},
     * and saves them to MongoDB. Otherwise, it prints a message indicating that
     * the collection already has data.
     *
     * @param args command-line arguments (not used)
     * @throws Exception if the JSON file cannot be read or parsed
     */
    @Override
    public void run(String... args) throws Exception {
        if (usuarioRepository.count() == 0) {
            InputStream is = getClass().getResourceAsStream("/usuarios.json");
            if (is == null) {
                throw new RuntimeException("No se encontró el archivo usuarios.json en resources");
            }

            List<Usuario> usuarios = objectMapper.readValue(is, new TypeReference<List<Usuario>>() {});
            usuarioRepository.saveAll(usuarios);
            System.out.println("Usuarios cargados en MongoDB");
        } else {
            System.out.println("La colección de usuarios ya tiene datos");
        }
    }
}
