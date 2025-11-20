import java.util.Scanner;

public class Main {
    
    private static HiloGusano gus = null;
    private static MonitorMapa monitor = null;
    private static char[][] mapa;
    private static final int r = 12;
    private static final int c = 16;
    

    private static void inicializarMapa() {
        mapa = new char[r][c];
        for(int r=0; r<r; r++){
            for(int c=0; c<c; c++){
                mapa[r][c]='*';
            }
        }
    }

    public static void main(String[] args) {
        
        Scanner scanner = new Scanner(System.in);
        int opcion = 0;
        boolean funcionando = false;

        do {
            System.out.println("\n Menú");
            System.out.println("1. Iniciar Gusano y Monitor");
            System.out.println("2. Pausar Gusano");
            System.out.println("3. Continuar Gusano ");
            System.out.println("4. Terminar Ejecución y Salir");
            System.out.print("Selecciona una opción: ");

            opcion = scanner.nextInt();
            
            switch (opcion) {
                case 1: 
                    if (!funcionando) {
                        inicializarMapa();
                        gus = new HiloGusano(mapa);
                        monitor = new MonitorMapa(mapa);
                        
                        gus.start();
                        monitor.start();
                        funcionando = true;
                        System.out.println("Hilos iniciados. El gusano está moviéndose.");
                    } else {
                        System.out.println("Los hilos ya están en ejecución.");
                    }
                    break;

                case 2: 
                    if (funcionando) {
                        monitor.pausar();
                        gus.pausar();
                    } else {
                        System.out.println("Primero debes iniciar los hilos.");
                    }
                    break;

                case 3:
                    if (funcionando) {
                        monitor.continuar();
                        gus.continuar();
                    } else {
                        System.out.println("Primero debes iniciar los hilos.");
                    }
                    break;

                case 4: 
                    System.out.println("Terminando el programa...");
                    if (funcionando) {
                        gus.interrupt();
                        try {
                            gus.join();
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                        

                        monitor.terminar();
                        try {
                            monitor.join();
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }
                    System.out.println("Todos los hilos se han detenido.");
                    break;

                default:
                    System.out.println("Seleccione una opción válida.");
            }
        } while (opcion != 4);

        scanner.close();
    }
}