import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Arrays;

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

    public void ordenarClasificacion() {
    // Arrays.sort ignora los null, así que primero creamos un array solo con los equipos no nulos
    int numNoNulos = 0;
    for (Equipo e : equipos) {
        if (e != null) numNoNulos++;
    }

    Equipo[] tmp = new Equipo[numNoNulos];
    int idx = 0;
    for (Equipo e : equipos) {
        if (e != null) tmp[idx++] = e;
    }

    // Ordenamos usando compareTo de Equipo
    Arrays.sort(tmp);
    // Si quieres que el primero tenga más puntos, invertimos el array
    for (int i = 0; i < tmp.length / 2; i++) {
        Equipo temp = tmp[i];
        tmp[i] = tmp[tmp.length - 1 - i];
        tmp[tmp.length - 1 - i] = temp;
    }

    // Volvemos a copiar al array original
    for (int i = 0; i < equipos.length; i++) {
        equipos[i] = (i < tmp.length) ? tmp[i] : null;
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
