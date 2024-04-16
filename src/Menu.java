import java.time.LocalDate;
import java.util.Scanner;
import Librerias.EstructurasDatos.Lineales.LECola;


public class Menu
{
    private LECola<Corredor> colaCorredores = null;
    private Corredor ganador = null;
    private Carrera carrera = null;
    public Menu()
    {
        this.seleccionarOpcion();
    }

    public void mostrarMenu()
    {
        System.out.println("\n\n");
        System.out.println("\t\tMenu Principal\n");
        System.out.println("1. Registrar datos generales de la carrera");
        System.out.println("2. Registrar datos de los corredores");
        System.out.println("3. Mostrar datos de un corredor");
        System.out.println("4. Listado de tiempos de carrera (sin ordenar)");
        System.out.println("5. Clasificacion general de la carrera");
        System.out.println("6. Mostrar resumen final");
        System.out.println("0. Salir");
    }

    public void seleccionarOpcion()
    {
        Scanner sc = new Scanner(System.in);
        int opcion = -1;

        while (opcion != 0)
        {
            mostrarMenu();
            System.out.print("Seleccione una opcion: ");

            try
            {
                opcion = sc.nextInt();
            }catch (Exception e){
                System.out.println("Error al introducir la opcion vuelva a intentarlo");
            }

            switch (opcion)
            {
                case 1:
                    pedirDatosCarrera();
                    pause();
                    break;
                case 2:
                    if(carrera != null)
                    {
                        pedirDatosCorredores();
                        pause();
                    }else{
                        System.out.println("Primero debes registrar los datos de la carrera");
                        pause();
                    }
                    break;
                case 3:
                    if(carrera != null && !colaCorredores.esVacia())
                    {
                        buscarCorredor();
                        pause();
                    }else {
                        System.out.println("Primero debes registrar los datos de la carrera y los corredores");
                        pause();
                    }
                    break;
                case 4:
                    if(carrera != null && !colaCorredores.esVacia())
                    {
                        mostrarTiempos();
                        pause();
                    }else {
                        System.out.println("Primero debes registrar los datos de la carrera y los corredores");
                        pause();
                    }
                    break;
                case 5:
                    if(carrera != null && !colaCorredores.esVacia())
                    {
                        mostrarTiemposOrdenados();
                        pause();
                    }else {
                        System.out.println("Primero debes registrar los datos de la carrera y los corredores");
                        pause();
                    }
                    break;
                case 6:
                    if(carrera != null && !colaCorredores.esVacia())
                    {
                        mostrarResumenFinal();
                        pause();
                    }else {
                        System.out.println("Primero debes registrar los datos de la carrera y los corredores");
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
        System.out.print("Introduce el nombre de la carrera: ");
        String nombre = sc.nextLine();
        System.out.print("Introduce la distancia de la carrera: ");
        int distancia = sc.nextInt();
        sc.nextLine();
        System.out.print("Introduce la fecha de la carrera ");
        LocalDate fecha = crearFecha();
        System.out.print("Introduce la poblacion donde se realiza la carrera: ");
        String poblacion = sc.nextLine();
        colaCorredores = new LECola<Corredor>();
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
            try
            {
                System.out.println();
                Scanner sc = new Scanner(System.in);
                System.out.print("Introduce el dorsal del corredor: ");
                int dorsal = sc.nextInt();
                sc.nextLine();
                System.out.print("Introduce el nombre del corredor: ");
                String nombre = sc.nextLine();
                System.out.print("Introduce el tiempo del corredor en la carrera en formado hh:mm:ss : ");
                String tiempo = sc.nextLine();
                String[] tiempoArray = tiempo.split(":");
                int horas = Integer.parseInt(tiempoArray[0]);
                int minutos = Integer.parseInt(tiempoArray[1]);
                int segundos = Integer.parseInt(tiempoArray[2]);

                Corredor corredor = new Corredor(dorsal, nombre, horas, minutos, segundos);
                colaCorredores.encolar(corredor);

                System.out.print("Desea introducir otro corredor? 1. Si 0. No: ");
                opc = sc.nextInt();
            }catch (Exception e)
            {
                System.out.println("Error al introducir los datos");
            }
        }
    }

    public void buscarCorredor()
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduce el dorsal del corredor: ");
        int dorsal = sc.nextInt();
        LECola<Corredor> colaAux = new LECola<Corredor>();
        Corredor corredor = null;
        boolean encontrado = false;
        while(!colaCorredores.esVacia())
        {
            corredor = colaCorredores.desencolar();
            if(corredor.getDorsal() == dorsal)
            {
                System.out.println("Dorsal\t\tNombre\t\t\tTiempo");
                System.out.println(corredor.toString());
                encontrado = true;
            }
            colaAux.encolar(corredor);
        }
        while(!colaAux.esVacia())
        {
            colaCorredores.encolar(colaAux.desencolar());
        }
        if(!encontrado)
        {
            System.out.println("Corredor no encontrado");
        }
    }

    public void mostrarTiempos(){
        LECola<Corredor> colaAux = new LECola<Corredor>();
        System.out.println("Dorsal\t\tNombre\t\t\tTiempo");
        Corredor corredor = null;
        while(!colaCorredores.esVacia())
        {
            corredor = colaCorredores.desencolar();
            System.out.println(corredor.toString());
            colaAux.encolar(corredor);
        }
        while(!colaAux.esVacia()){
            colaCorredores.encolar(colaAux.desencolar());
        }
    }


    public void mostrarTiemposOrdenados(){
        System.out.println("Dorsal\t\tNombre\t\t\tTiempo");
        colaCorredores.imprimirOrdenadoAsc();
    }

    public void mostrarResumenFinal(){
        Corredor ganador;
        LECola<Corredor> colaAux = new LECola<Corredor>();
        colaAux = colaCorredores.ordenarDesc();
        colaAux.reverse();
        ganador = colaAux.desencolar();

        System.out.println("\t\tCARRERA: ");
        System.out.println("Nombre de la carrera: \"" + carrera.getNombre()+ "\"");
        System.out.println("Distancia de la carrera: " + carrera.getDistancia());
        System.out.println("Poblacion de la carrera: " + carrera.getPoblacion());
        System.out.println("Fecha de la carrera: " + carrera.getFecha());
        System.out.println("Distancia de la carrera: " + carrera.getDistancia());

        System.out.println("\n\n\t\tRESUMEN FINAL: ");
        System.out.println("-Número de corredores participantes en la carrera: " + colaCorredores.getTalla());
        System.out.println("-Vencedor/a de la carrera: " + ganador.getNombre() + "(Dorsal " + ganador.getDorsal() + ")" + "-> Tiempo total: " + ganador.getTiempoH() + ":" + ganador.getTiempoM() + ":" + ganador.getTiempoS());

        System.out.println("-Tiempo medio empleado por los corredores: " + tiempoMedio());
    }



    public String tiempoMedio(){
        LECola<Corredor> colaAux = new LECola<Corredor>();
        int horas = 0;
        int minutos = 0;
        int segundos = 0;
        while(!colaCorredores.esVacia())
        {
            Corredor corredor = colaCorredores.desencolar();
            horas += corredor.getTiempoH();
            minutos += corredor.getTiempoM();
            segundos += corredor.getTiempoS();
            colaAux.encolar(corredor);
        }
        while(!colaAux.esVacia())
        {
            colaCorredores.encolar(colaAux.desencolar());
        }
        horas = horas/colaCorredores.getTalla();
        minutos = minutos/colaCorredores.getTalla();
        segundos = segundos/colaCorredores.getTalla();

        return horas + ":" + minutos + ":" + segundos;

    }

    public void pause() {
        System.out.println("Presiona Enter para continuar...");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
    }
}
