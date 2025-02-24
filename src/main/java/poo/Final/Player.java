package poo.Final;

public class Player {
    private int coordx, coordy;
    private String direcao = "esquerda";
    private final int [][] coordsInicial = {{13},{23}};
    private String avatar;

    public void movePlayer(){
        switch(direcao){
            case "direita" -> coordx++;
            case "esquerda" -> coordx--;
            case "cima" -> coordy++;
            case "baixo" -> coordy--;
            default -> coordx--;
        }

    }

    public String getAvatar() {
        return avatar;
    }


    public String getDirecao() {
        return direcao;
    }

    public void setDirecao(String direcao) {
        this.direcao = direcao;
    }

    public int getCoordy() {
        return coordy;
    }

    public void setCoordy(int coordy) {
        this.coordy = coordy;
    }

    public int getCoordx() {
        return coordx;
    }

    public void setCoordx(int coordx) {
        this.coordx = coordx;
    }

    public void comer(Mapa map){
        if(map.getMapa()[coordx][coordy] == 1){
            map.bolaComida(coordx, coordy);
        }

    }


}
