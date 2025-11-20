public class HiloGusano extends Thread{
    private char [][] jardin;
    private int filas;
    private int columnas;
    
    public HiloGusano(char[][] mundo){
        this.jardin=mundo;
        this.filas=jardin.length;
        this.columnas=jardin[0].length;
    }
    
    public void caminaRenglon(int r){
        for(int x=0;x<columnas;x++){
            jardin[r][x]='W';
        }
    }
    
    public void caminaColumna(int c){
        for(int x=0;x<filas;x++){
            jardin[x][x]='W';
        }
    }
    
    public void comerColumna(int c, int traga){
        for(int x=0;x<traga;x++){
            jardin[x][c]='C';
        }
    }
    
    public void comerRenglon(int r, int traga){
        for(int x=0;x<traga;x++){
            jardin[r][x]='W';
        }
    }
    
    @Override
    public void run(){
        
        int vida=20;
        int ini=0;
        while(vida>0){
        try{
            synchronized(jardin){
                caminaColumna(ini);
        }
        ini++;
        sleep(400);
            synchronized(jardin){
                comerRenglon(ini,3);
                ini++;
        }
        sleep(600);
        vida--;
        }
        catch(InterruptedException e){

        }
        catch(ArrayIndexOutOfBoundsException e){
            
        }
        
    }
    }
}
