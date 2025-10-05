import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        Clasificacion clasificacion = new Clasificacion();

        System.out.println("Menú:");
        System.out.println("1. Añadir equipo");
        System.out.println("2. Mostrar clasificación");
        System.out.println("3. Guardar clasificación");
        System.out.println("4. Cargar clasificación");
        System.out.println("5. Salir");

        int opcion;
        opcion = sc.nextInt();
        sc.nextLine();

        while (opcion != 5) {
            switch (opcion) {
                case 1:
                    System.out.println("Nombre del equipo:");
                    String nombre = sc.nextLine();
                    System.out.println("Número de victorias:");
                    int victorias = sc.nextInt();
                    sc.nextLine();
                    System.out.println("Número de derrotas:");
                    int derrotas = sc.nextInt();
                    sc.nextLine();
                    System.out.println("Puntos a favor:");
                    int puntosFavor = sc.nextInt();
                    sc.nextLine();
                    System.out.println("Puntos en contra:");
                    int puntosContra = sc.nextInt();
                    sc.nextLine();
                    Equipo equipo = new Equipo(nombre, victorias, derrotas, puntosFavor, puntosContra);
                    clasificacion.addEquipo(equipo);
                    System.out.println("Equipo añadido.");
                
                case 2:
                    System.out.println(clasificacion.toString());
                    break;
                case 3:
                    Clasificacion.saveClasificacion(clasificacion, "clasificacion.dat");
                    break;
                case 4:
                    clasificacion = Clasificacion.loadClasificacion("clasificacion.dat");
                    break;
                default:
                    System.out.println("Opción no válida");
            }
            System.out.println("Menú:");
            System.out.println("1. Añadir equipo");
            System.out.println("2. Mostrar clasificación");
            System.out.println("3. Guardar clasificación");
            System.out.println("4. Cargar clasificación");
            System.out.println("5. Salir");
            opcion = sc.nextInt();
            sc.nextLine();
        }
    }
}
