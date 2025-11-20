public class MonitorMapa extends Thread{
    private char[][] mapa;
    private boolean terminar;
    private boolean pausa = true;

    
    public MonitorMapa(char[][] mundo){
        this.mapa=mundo; terminar=false;
    }
    
        public synchronized void pausar(){
        pausa = true;
        System.out.println("Mapa Pausado.");
    }
    
        public synchronized void continuar(){
        pausa = false;
        notify();
        System.out.println("Mapa Reanudado");
    }
    
    
    public void run(){
        int r, c; int filas=mapa.length;
        int columnas=mapa[0].length;
        while(!terminar){
            try{
                synchronized(this){
                    while(pausa){
                        wait();
                    }
                }
                System.out.println("===========================================");
                for(r=0;r<filas;r++){
                    for(c=0;c<columnas;c++){
                        System.out.printf("|%2c",mapa[r][c]);
                    }
                    System.out.print("\n");
                }
                sleep(2000);
            }
            catch(InterruptedException e){
                System.out.println("Interrupción");
            }
        }
    }
    
    public void terminar(){ terminar=true; interrupt();}
}