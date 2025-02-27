package poo.Final;

public abstract class Entity {

    protected int coordx, coordy;
    protected char avatar;
    protected String direcaoDesejada;
    protected String direcao;
    protected boolean isPlayer;

    protected final int COLISAO = 2;
    protected final int PONTO = 1;
    protected final int APENAS_FANTASMA = 3;
    protected final int ESPECIAL = 4;

    public Entity(char avatar, int coordy, int coordx, boolean isPlayer) {
        this.isPlayer = isPlayer;
        this.avatar = avatar;
        this.coordy = coordy;
        this.coordx = coordx;
    }

    public void move(Mapa map) {
        if (direcao == null) return;

        int newX = coordx;
        int newY = coordy;

        switch (direcao) {
            case "direita" -> newX++;
            case "esquerda" -> newX--;
            case "cima" -> newY--;
            case "baixo" -> newY++;
        }

        // Só move se o próximo passo for permitido
        if (map.bounds(newX, newY) && map.getMapa()[newY][newX] != COLISAO) {
            coordx = newX;
            coordy = newY;
        }
        this.wrapAround(map);
    }


    public String changeDirection(String direction, Mapa map) {
        direcaoDesejada = direction; // Sempre salva a intenção de direção

        // Se já for possível mudar de direção, aplica imediatamente
        if (podeMover(direction, map)) {
            direcao = direction;
        }
        return direcaoDesejada;
    }

    protected boolean podeMover(String direction, Mapa map) {
        return switch (direction) {
            case "direita" -> map.bounds(coordx + 1,coordy) && map.getMapa()[coordy][coordx + 1] != COLISAO  ;
            case "esquerda" -> map.bounds(coordx - 1,coordy) && map.getMapa()[coordy][coordx - 1] != COLISAO ;
            case "cima" -> map.getMapa()[coordy - 1][coordx] != COLISAO ;
            case "baixo" -> map.getMapa()[coordy + 1][coordx] != COLISAO;
            default -> false;
        };
    }


    public void wrapAround(Mapa map) {
        if(!map.bounds(coordx-1,coordy)){
            coordx = map.getMapa()[0].length-2;
        }
        if(!map.bounds(coordx+1,coordy)){
            coordx = 0;
        }
    }

    public int getCoordx() {
        return coordx;
    }

    public void setCoordx(int coordx) {
        this.coordx = coordx;
    }

    public int getCoordy() {
        return coordy;
    }

    public void setCoordy(int coordy) {
        this.coordy = coordy;
    }

    public char getAvatar() {
        return avatar;
    }

    public void setAvatar(char avatar) {
        this.avatar = avatar;
    }

    public String getDirecaoDesejada() {
        return direcaoDesejada;
    }

    public void setDirecaoDesejada(String direcaoDesejada) {
        this.direcaoDesejada = direcaoDesejada;
    }

    public String getDirecao() {
        return direcao;
    }

    public void setDirecao(String direcao) {
        this.direcao = direcao;
    }
}
