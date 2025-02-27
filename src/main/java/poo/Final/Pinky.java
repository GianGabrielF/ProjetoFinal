package poo.Final;

public class Pinky extends Fantasma {

    public Pinky(int coordy, int coordx) {
        super('P', coordy, coordx, 0, 0); // Scatter no canto superior esquerdo (ajuste conforme necessidade)
        this.direcao = "direita";
    }

    @Override
    protected String determinarDirecao(int pacmanX, int pacmanY, int blinkyX, int blinkyY, Mapa map) {
        if (modoOlhos) {
            // Retornar para a casa dos fantasmas (posição fixa)
            return calcularMelhorRota(13, 11, map); // Ajuste conforme o comportamento do Pinky
        } else if (modoFuga) {
            // Fugir do Pac-Man (ir para o canto oposto)
            return calcularMelhorRota(scatterX, scatterY, map);
        } else {
            // Perseguir o Pac-Man, mas 4 casas à frente na direção do Pac-Man
            int targetX = pacmanX;
            int targetY = pacmanY;

            switch (direcao) {
                case "direita":
                    targetX = pacmanX + 4;
                    break;
                case "esquerda":
                    targetX = pacmanX - 4;
                    break;
                case "cima":
                    targetY = pacmanY - 4;
                    break;
                case "baixo":
                    targetY = pacmanY + 4;
                    break;
            }

            return calcularMelhorRota(targetX, targetY, map);
        }
    }

    @Override
    public void respawn() {
        this.coordx = 14; // Posição inicial do Pinky (ajuste conforme o mapa)
        this.coordy = 14; // Posição inicial do Pinky (ajuste conforme o mapa)
        this.direcao = "esquerda"; // Direção inicial (ajuste conforme o comportamento desejado)
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