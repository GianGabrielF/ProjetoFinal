package poo.Final;

public abstract class Fantasma extends Entity{

        protected int scatterX, scatterY; // Coordenadas do modo "scatter" (canto do mapa)
        protected boolean modoFuga = false; // Se verdadeiro, o fantasma foge
        protected boolean modoOlhos = false; // Se verdadeiro, está retornando à casa dos fantasmas
        protected boolean imune = false;

        public Fantasma(char avatar, int coordy, int coordx, int scatterX, int scatterY) {
            super(avatar, coordy, coordx,false);
            this.scatterX = scatterX;
            this.scatterY = scatterY;
        }

        public abstract void respawn();

        public void moverFantasma(Mapa map, int pacmanX, int pacmanY, int blinkyX, int blinkyY) {
            // Cada fantasma tem um comportamento diferente para decidir a direção
            this.direcaoDesejada = determinarDirecao(pacmanX, pacmanY, blinkyX, blinkyY, map);

            // Se for possível mover na direção desejada, atualiza a direção
            if (podeMover(direcaoDesejada, map)) {
                this.direcao = direcaoDesejada;
            }

            // Move o fantasma conforme a direção escolhida
            move(map);
        }

        protected abstract String determinarDirecao(int pacmanX, int pacmanY, int blinkyX, int blinkyY, Mapa map);

        public void ativarModoFuga() {
            this.modoFuga = true;
        }

        public void desativarModoFuga() {
            this.modoFuga = false;
        }

    public boolean isImune() {
        return imune;
    }

    public void setImune(boolean imune) {
        this.imune = imune;
    }

    public void ativarModoOlhos() {
            this.modoOlhos = true;
        }

        public void desativarModoOlhos() {
            this.modoOlhos = false;
        }

}

