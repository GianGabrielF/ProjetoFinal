package poo.Final;

public class Player extends Entity {
    private int pontos = 0;
    private boolean modoEspecial = false;
    private int tempoModoEspecial = 0;

    private final int TEMPO_ESPECIAL = 120;
    private final int COLISAO = 2;
    private final int PONTO = 1;
    private final int ESPECIAL = 4;



    public Player() {
        super('c', 22, 13,true);
    }


    public void comer(Mapa map){
        if(map.getMapa()[coordy][coordx] == PONTO){
            map.bolaComida(coordx, coordy);
            aumentarPontos(10);
        } else if (map.getMapa()[coordy][coordx] == ESPECIAL){
            map.bolaComida(coordx, coordy);
            ativarModoEspecial();
            aumentarPontos(100);
        }

    }

    public void ativarModoEspecial(){
        this.modoEspecial = true;
        this.tempoModoEspecial = TEMPO_ESPECIAL;
    }

    public void reduzirModoEspecial(int ghostEaten) {
        if (modoEspecial) {
            this.tempoModoEspecial--;
            if (this.tempoModoEspecial <= 0) {
                modoEspecial = false;
                ghostEaten = 0;
            }
        }
    }

    public int getTempoModoEspecial() {
        return tempoModoEspecial;
    }

    public void setTempoModoEspecial(int tempoModoEspecial) {
        this.tempoModoEspecial = tempoModoEspecial;
    }

    public void setModoEspecial(boolean modoEspecial) {
        this.modoEspecial = modoEspecial;
    }

    public void setPontos(int pontos) {
        this.pontos = pontos;
    }

    public boolean isModoEspecial() {
        return modoEspecial;
    }

    public int getPontos() {
        return pontos;
    }

    public void aumentarPontos(int pontos) {
        this.pontos += pontos;
    }
}
