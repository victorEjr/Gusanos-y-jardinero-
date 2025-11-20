public class HiloGusano extends Thread{
    private char[][] jardin;
    private int filas;
    private int columnas;
    private volatile boolean pausa = false;
    
    public HiloGusano(char[][] mundo){
        this.jardin=mundo;
        filas=jardin.length;
        columnas=jardin[0].length;
    }

    public synchronized void pausar(){
        pausa = true;
        System.out.println("Gusano Pausado.");
    }
    
    public synchronized void continuar(){
        pausa = false;
        notify();
        System.out.println("Gusano Reanudado");
    }
    
    public void caminaRenglon(int r){
        for(int x=0;x<columnas;x++){
            jardin[r][x]='W';
        }
    }
    
    public void caminaColumna(int c){
        for(int x=0;x<filas;x++){
            jardin[x][c]='W';
        }
    }
    
    public void comerColumna(int c, int traga){
        for(int x=0;x<traga;x++){
            jardin[x][c]='C';
        }
    }
    
    public void comerRenglon(int r, int traga){
        for(int x=0;x<traga;x++){
            jardin[r][x]='C';
        }
    }
    
    @Override
    public void run(){
        int vida=20;
        int ini=0;
        while(vida>0){
            try{
                synchronized(this){ 
                while(pausa){
                    wait(); 
                }
            }

                synchronized(jardin){
                    caminaColumna(ini);
                }
                ini++;
                sleep(400);
                synchronized(jardin){
                    comerRenglon(ini,3);
                }
                ini++;
                sleep(600);
                vida--;
            }
            catch(InterruptedException e){
                System.out.println("Interrupción");
                Thread.currentThread().interrupt();
                break; // Terminar el bucle si es interrumpido
            }
            catch(ArrayIndexOutOfBoundsException e){
                ini=0;
            }
        }//fin while
    }

}