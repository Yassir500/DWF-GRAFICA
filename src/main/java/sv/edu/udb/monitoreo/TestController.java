package sv.edu.udb.monitoreo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.List;

@RestController
public class TestController {

    // Lista para simular consumo de memoria RAM
    private List<byte[]> memoryLoad = new ArrayList<>();

    @GetMapping("/api/test")
    public String testEndpoint() {
        return "Petición exitosa - Generando métricas para el Grupo 04";
    }

    @GetMapping("/api/load-memory")
    public String loadMemory() {
        // Añade un bloque de 10MB a la lista cada vez que se llama
        memoryLoad.add(new byte[10 * 1024 * 1024]);
        return "Consumiendo 10MB de RAM adicionales. Bloques totales: " + memoryLoad.size();
    }

    @GetMapping("/api/clear")
    public String clearMemory() {
        memoryLoad.clear();
        System.gc(); // Sugiere al sistema limpiar la basura
        return "Memoria liberada";
    }
}