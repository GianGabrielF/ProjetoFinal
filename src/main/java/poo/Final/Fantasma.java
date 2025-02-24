package poo.Final;

public class Fantasma {
    private int coordx, coordy;
    private String direcao = "esquerda";
    private final int [][] coordsInicial = {{},{}};
    private String avatar;


    public void moveGhost(){
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
}
