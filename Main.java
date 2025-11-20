public class Main{
    public static void main (String[]args){
        char[][]mapa;
        mapa= new char[12][16];
        for(int r=0;r<12;r++){
            for(int c=0;c<16;c++){
                mapa[r][c]='*';
            }
            
        }
        
        HiloGusano gus=new HiloGusano(mapa);
        MonitorMapa monitor = new MonitorMapa(mapa);
        gus.start();
        monitor.start();
        try{
            gus.join();
        }
        catch(InterruptedException e){
            System.out.println("interupt");
        }
        monitor.terminar();
    }
}
