package poo.Final;


public class Blinky extends Fantasma {

    public Blinky(int coordy, int coordx) {
        super('B', coordy, coordx, 27, 0); // Scatter no canto superior direito
    }

    @Override
    protected String determinarDirecao(int pacmanX, int pacmanY, int blinkyX, int blinkyY, Mapa map) {
        if (modoOlhos) {
            // Retornar para a casa dos fantasmas (posição fixa)
            return calcularMelhorRota(13, 11, map);
        } else if (modoFuga) {
            // Fugir do Pac-Man (ir para o canto oposto)
            return calcularMelhorRota(scatterX, scatterY, map);
        } else {
            // Perseguir diretamente o Pac-Man
            return calcularMelhorRota(pacmanX, pacmanY, map);
        }
    }

    @Override
    public void respawn() {
        this.coordx = 11; // Posição inicial do Blinky no mapa
        this.coordy = 13;
        this.direcao = "esquerda"; // Blinky normalmente sai para a esquerda
    }

    private String calcularMelhorRota(int destinoX, int destinoY, Mapa map) {
        int menorDistancia = Integer.MAX_VALUE;
        String melhorDirecao = direcao; // Manter a direção se não encontrar outra melhor

        String[] direcoes = {"cima", "baixo", "esquerda", "direita"};
        int[][] deltas = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};

        for (int i = 0; i < direcoes.length; i++) {
            int novoX = coordx + deltas[i][0];
            int novoY = coordy + deltas[i][1];

            if (map.bounds(novoX, novoY) && map.getMapa()[novoY][novoX] != COLISAO) {
                int distancia = calcularDistancia(novoX, novoY, destinoX, destinoY);
                if (distancia < menorDistancia) {
                    menorDistancia = distancia;
                    melhorDirecao = direcoes[i];
                }
            }
        }

        return melhorDirecao;
    }

    private int calcularDistancia(int x1, int y1, int x2, int y2) {
        return (x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1); // Distância Euclidiana sem raiz
    }
}

