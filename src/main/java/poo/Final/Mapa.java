package poo.Final;

import java.util.Arrays;

public class Mapa {
    private int [][] mapa;


    private String[] esqueleto = {
            "+------------++------------+",
            "|            ||            |",
            "| +--+ +---+ || +---+ +--+ |",
            "| |  | |   | || |   | |  | |",
            "| +--+ +---+ ++ +---+ +--+ |",
            "|                          |",
            "| +--+ ++ +------+ ++ +--+ |",
            "| +--+ || +--++--+ || +--+ |",
            "|      ||    ||    ||      |",
            "+----+ |+--+ || +--+| +----+",
            "     | |+--+ ++ +--+| |     ",
            "     | ||          || |     ",
            "     | || +--==--+ || |     ",
            "+----+ ++ |      | ++ +----+",
            "          |      |          ",
            "+----+ ++ +------+ ++ +----+",
            "     | ||          || |     ",
            "     | || #------# || |     ",
            "+----+ ++ #--++--# ++ +----+",
            "|            ||            |",
            "| +--+ +---+ || +---+ +--+ |",
            "| +-+| +---+ ++ +---+ |+-+ |",
            "|   ||                ||   |",
            "+-+ || ++ +------+ ++ || +-+",
            "+-+ ++ || +--++--+ || ++ +-+",
            "|      ||    ||    ||      |",
            "| +----++--+ || +--++----+ |",
            "| +--------+ ++ +--------+ |",
            "|                          |",
            "+--------------------------+"
    };

    public Mapa() {
        mapa = new int[][]{
               //0,1,2,3,4,5,6,7,8,9, 11, 13, 15, 17, 19, 21, 23, 25, 27
               //                   10, 12, 14, 16, 18, 20, 22, 24, 26,
                {2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2}, //0
                {2,1,1,1,1,1,1,1,1,1,1,1,1,2,2,1,1,1,1,1,1,1,1,1,1,1,1,2}, //1
                {2,1,2,2,2,2,1,2,2,2,2,2,1,2,2,1,2,2,2,2,2,1,2,2,2,2,1,2}, //2
                {2,4,2,2,2,2,1,2,2,2,2,2,1,2,2,1,2,2,2,2,2,1,2,2,2,2,4,2}, //3
                {2,1,2,2,2,2,1,2,2,2,2,2,1,2,2,1,2,2,2,2,2,1,2,2,2,2,1,2}, //4
                {2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2}, //5
                {2,1,2,2,2,2,1,2,2,1,2,2,2,2,2,2,2,2,1,2,2,1,2,2,2,2,1,2}, //6
                {2,1,2,2,2,2,1,2,2,1,2,2,2,2,2,2,2,2,1,2,2,1,2,2,2,2,1,2}, //7
                {2,1,1,1,1,1,1,2,2,1,1,1,1,2,2,1,1,1,1,2,2,1,1,1,1,1,1,2}, //8
                {2,2,2,2,2,2,1,2,2,2,2,2,0,2,2,0,2,2,2,2,2,1,2,2,2,2,2,2}, //9
                {2,2,2,2,2,2,1,2,2,2,2,2,0,2,2,0,2,2,2,2,2,1,2,2,2,2,2,2}, //10
                {2,2,2,2,2,2,1,2,2,0,0,0,0,0,0,0,0,0,0,2,2,1,2,2,2,2,2,2}, //11
                {2,2,2,2,2,2,1,2,2,0,2,2,2,3,3,2,2,2,0,2,2,1,2,2,2,2,2,2}, //12
                {2,2,2,2,2,2,1,2,2,0,2,3,3,3,3,3,3,2,0,2,2,1,2,2,2,2,2,2}, //13 //14
                {0,0,0,0,0,0,1,0,0,0,2,3,3,3,3,3,3,2,0,0,0,1,0,0,0,0,0,0}, //15
                {2,2,2,2,2,2,1,2,2,0,2,2,2,2,2,2,2,2,0,2,2,1,2,2,2,2,2,2}, //16
                {2,2,2,2,2,2,1,2,2,0,0,0,0,0,0,0,0,0,0,2,2,1,2,2,2,2,2,2}, //17
                {2,2,2,2,2,2,1,2,2,0,2,2,2,2,2,2,2,2,0,2,2,1,2,2,2,2,2,2}, //18
                {2,2,2,2,2,2,1,2,2,0,2,2,2,2,2,2,2,2,0,2,2,1,2,2,2,2,2,2}, //19
                {2,1,1,1,1,1,1,1,1,1,1,1,1,2,2,1,1,1,1,1,1,1,1,1,1,1,1,2}, //20
                {2,1,2,2,2,2,1,2,2,2,2,2,1,2,2,1,2,2,2,2,2,1,2,2,2,2,1,2}, //21
                {2,1,2,2,2,2,1,2,2,2,2,2,1,2,2,1,2,2,2,2,2,1,2,2,2,2,1,2}, //22
                {2,4,1,1,2,2,1,1,1,1,1,1,1,0,0,1,1,1,1,1,1,1,2,2,1,1,4,2}, //23
                {2,2,2,1,2,2,1,2,2,1,2,2,2,2,2,2,2,2,1,2,2,1,2,2,1,2,2,2}, //24
                {2,2,2,1,2,2,1,2,2,1,2,2,2,2,2,2,2,2,1,2,2,1,2,2,1,2,2,2}, //25
                {2,1,1,1,1,1,1,2,2,1,1,1,1,2,2,1,1,1,1,2,2,1,1,1,1,1,1,2}, //26
                {2,1,2,2,2,2,2,2,2,2,2,2,1,2,2,1,2,2,2,2,2,2,2,2,2,2,1,2}, //27
                {2,1,2,2,2,2,2,2,2,2,2,2,1,2,2,1,2,2,2,2,2,2,2,2,2,2,1,2}, //28
                {2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2}, //29
                {2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2}  //30
        };
    }

    public boolean bolaComida(int x, int y){
        if(this.mapa[y][x] == 1){
            this.mapa[y][x] = 0;
            return true;
        } else if (this.mapa[y][x] == 4) {
            this.mapa[y][x] = 0;
            return true;
        }
        return false;
    }

    public String[] getEsqueleto() {
        return esqueleto;
    }

    public String[] drawMap(){
        String[] drawnMap = esqueleto.clone();
        for(int i=0;i< 29;i++){
            for(int j=0;j< 27;j++){
                if(mapa[i][j] == 1){
                    char[] vetor = new char[27];
                    vetor = drawnMap[i].toCharArray();
                    vetor[j] = '.';
                    drawnMap[i] = new String(vetor);
                } else if (mapa[i][j] == 4){
                    char[] vetor = new char[27];
                    vetor = drawnMap[i].toCharArray();
                    vetor[j] = '*';
                    drawnMap[i] = new String(vetor);
                }
            }
        }
        return drawnMap;
    }



    public int[][] getMapa() {
        return mapa;
    }

    public void setMapa(int[][] mapa) {
        this.mapa = mapa;
    }

    public boolean bounds(int x, int y){
        if(x<0 || y <0){
            return false;
        }
        if(x>27 || y>30){
            return false;
        }
        return true;
    }
}
