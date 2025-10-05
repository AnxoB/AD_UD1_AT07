import java.io.Serializable;

public class Equipo implements Comparable<Equipo>, Serializable{
    private String nombre;
    private int victorias;
    private int derrotas;
    private int puntosFavor;
    private int puntosContra;
    private int puntos;
    private int partidosJugados;
    private int diferenciaPuntos;

    Equipo(String nombre, int victorias, int derrotas, int puntosFavor, int puntosContra) {
        this.nombre = nombre;
        this.victorias = victorias;
        this.derrotas = derrotas;
        this.puntosFavor = puntosFavor;
        this.puntosContra = puntosContra;
    }

    Equipo(String nombre){
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public int getDiferenciaPuntos() {
        diferenciaPuntos = puntosFavor - puntosContra;
        return diferenciaPuntos;
    }
    public int getPartidosJugados() {
        partidosJugados = victorias + derrotas;
        return partidosJugados;
    }
    public int getPuntos() {
        puntos = (victorias * 2) + (derrotas * 1);
        return puntos;
    }

    @Override
    public int compareTo(Equipo o) {
        if (this.getPuntos() < o.getPuntos()) {
            return -1;

        } else if(this.getPuntos() > o.getPuntos()){
            return 1;
        } else {
            if (this.getDiferenciaPuntos() < o.getDiferenciaPuntos()) {
                return -1;
            } else if (this.getDiferenciaPuntos() > o.getDiferenciaPuntos()) {
                return 1;
            } else {
                return 0;
            }
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true; // misma referencia
        if (obj == null) return false; // null check
        if (getClass() != obj.getClass()) return false; // mismo tipo
        Equipo other = (Equipo) obj;
        if (this.nombre == null) {
            return other.nombre == null;
        }
        // comparar ignorando mayúsculas/minúsculas
        return this.nombre.equalsIgnoreCase(other.nombre);
    }

    @Override
    public int hashCode() {
        // coherente con equals que ignora mayúsculas/minúsculas
        return (nombre == null) ? 0 : nombre.toLowerCase().hashCode();
    }

    @Override
    public String toString() {
        return nombre;
    }
}
