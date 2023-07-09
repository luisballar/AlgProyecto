import domain.Shotgun;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import services.ArchivoDAO;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class ShotgunTest {
    Shotgun gun = new Shotgun();
    ArchivoDAO archivoDAO = new ArchivoDAO();
    String path = "texto.txt";
    String nuevoPath = "fragmentos.txt";


    @Test
    public void pruebaLectura() throws IOException {
        System.out.println(gun.totalCaracteres());
    }

    @Test
    public void fragmentadorHacer(){

        try {
            gun.fragmentador(500, 15);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    // genera un archivo vacío para pasarle texto
    @Test
    public void generaArchivo(){
        archivoDAO.generaArchivo(path);

    }
}