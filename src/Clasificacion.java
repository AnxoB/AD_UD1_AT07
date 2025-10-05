import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Clasificacion implements Serializable {
    private Equipo[] equipos;

    Clasificacion() {
        equipos = new Equipo[18];
    }

    Clasificacion(int numEquipos) {
        equipos = new Equipo[numEquipos];
    }

    public void addEquipo(Equipo e){
        for (int i = 0; i < equipos.length; i++) {
            if (equipos[i] == null) {
                equipos[i] = e;
                break;
            }
        }
    }

    public void removeEquipo(String nombre) {
        for (int i = 0; i < equipos.length; i++) {
            if (equipos[i] != null && equipos[i].getNombre().equals(nombre)) {
                for (int j = i; j < equipos.length - 1; j++) {
                    equipos[j] = equipos[j + 1];
                }
                equipos[equipos.length - 1] = null; 
            }
        }
    }

    public static void saveClasificacion(Clasificacion clasificacion, String nombreArchivo) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(nombreArchivo))) {
            oos.writeObject(clasificacion);
            System.out.println("Clasificación guardada en " + nombreArchivo);
        } catch (IOException e) {
            System.out.println("Error al guardar la clasificación: " + e.getMessage());
        }
    }

    public static Clasificacion loadClasificacion(String nombreArchivo) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(nombreArchivo))) {
            Clasificacion c = (Clasificacion) ois.readObject();
            System.out.println("Clasificación cargada desde " + nombreArchivo);
            return c;
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error al cargar la clasificación: " + e.getMessage());
            return null;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Equipo equipo : equipos) {
            if (equipo != null) {
                sb.append(equipo.toString()).append("\n");
            }
        }
        return sb.toString();
    }
}
