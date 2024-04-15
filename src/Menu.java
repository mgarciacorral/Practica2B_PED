import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
import Librerias.EstructurasDatos.Lineales.ArrayCola;
import Librerias.EstructurasDatos.Lineales.LECola;

public class Menu
{
    private LECola<Corredor> colaCorredores = null;
    private Carrera carrera = null;
    public Menu()
    {
        this.seleccionarOpcion();
    }

    public void mostrarMenu()
    {
        System.out.println("\t\tMenu Principal\n");
        System.out.println("1. Registrar datos generales de la carrera");
        System.out.println("2. Registrar datos de los corredores");
        System.out.println("3. Mostrar datos de un corredor");
        System.out.println("4. Listado de tiempos de carrera (sin ordenar)");
        System.out.println("5. Clasificacion general de la carrera");
        System.out.println("6. Mostrar resumen final");
        System.out.println("5. Salir");
    }

    public void seleccionarOpcion()
    {
        Scanner sc = new Scanner(System.in);
        int opcion = -1;

        while (opcion != 0)
        {
            mostrarMenu();
            System.out.println("Seleccione una opcion: ");
            opcion = sc.nextInt();

            switch (opcion)
            {
                case 1:
                    pedirDatosCarrera();
                    break;
                case 2:
                    if(carrera != null)
                    {
                        pedirDatosCorredores();
                        pause();
                    }
                    break;
                case 3:
                    if(carrera != null && colaCorredores != null)
                    {
                        buscarCorredor();
                        pause();
                    }
                    break;
                case 4:
                    if(carrera != null && colaCorredores != null)
                    {
                        //aqui la llamada a la opcion
                        pause();
                    }
                    break;
                case 5:
                    if(carrera != null && colaCorredores != null)
                    {
                        //aqui la llamada a la opcion
                        pause();
                    }
                    break;
                case 6:
                    if(carrera != null && colaCorredores != null)
                    {
                        //aqui la llamada a la opcion
                        pause();
                    }
                    break;
                case 0:
                    System.out.println("Salir");
                    break;
                default:
                    System.out.println("Opcion no valida");
                    break;
            }
        }
    }

    public void pedirDatosCarrera()
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduce el nombre de la carrera: ");
        String nombre = sc.nextLine();
        System.out.println("Introduce la distancia de la carrera: ");
        int distancia = sc.nextInt();
        sc.nextLine();
        System.out.println("Introduce la fecha de la carrera ");
        LocalDate fecha = crearFecha();
        System.out.println("Introduce la poblacion donde se realiza la carrera: ");
        String poblacion = sc.nextLine();

        colaCorredores = new LECola<>();
        carrera = new Carrera(distancia, nombre, poblacion, fecha);
    }

    public LocalDate crearFecha()
    {
        Scanner sc = new Scanner(System.in);
        System.out.print("en formato AAAA-MM-DD: ");
        String f= sc.nextLine();
        try{
            return LocalDate.parse(f);
        } catch (Exception e) {
            System.out.println("Fecha no válida");
            return crearFecha();
        }
    }

    public void pedirDatosCorredores()
    {
        int opc = -1;
        while(opc != 0)
        {
            System.out.println();
            Scanner sc = new Scanner(System.in);
            System.out.print("Introduce el dorsal del corredor: ");
            int dorsal = sc.nextInt();
            sc.nextLine();
            System.out.print("Introduce el nombre del corredor: ");
            String nombre = sc.nextLine();
            System.out.println("Introduce el tiempo del corredor en la carrera en formado hh:mm:ss : ");
            String tiempo = sc.nextLine();
            String[] tiempoArray = tiempo.split(":");
            int horas = Integer.parseInt(tiempoArray[0]);
            int minutos = Integer.parseInt(tiempoArray[1]);
            int segundos = Integer.parseInt(tiempoArray[2]);

            Corredor corredor = new Corredor(dorsal, nombre, horas, minutos, segundos);
            colaCorredores.encolar(corredor);

            System.out.print("Desea introducir otro corredor? 1. Si 0. No: ");
            opc = sc.nextInt();
        }
    }

    public void buscarCorredor()
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduce el dorsal del corredor: ");
        int dorsal = sc.nextInt();
        ArrayCola<Corredor> colaAux = new ArrayCola<Corredor>(colaCorredores.getTalla());
        Corredor corredor = null;
        while(!colaCorredores.esVacia())
        {
            corredor = colaCorredores.desencolar();
            if(corredor.getDorsal() == dorsal)
            {
                corredor.mostrarDatos();
            }
            colaAux.encolar(corredor);
        }
        while(!colaAux.esVacia())
        {
            colaCorredores.encolar(colaAux.desencolar());
        }
    }

    public void pause() {
        System.out.println("Presiona Enter para continuar...");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
    }
}
