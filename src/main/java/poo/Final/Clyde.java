package poo.Final;

public class Clyde extends Fantasma {

    public Clyde(int coordy, int coordx) {
        super('C', coordy, coordx, 0, 0); // Scatter no canto inferior esquerdo
    }

    @Override
    protected String determinarDirecao(int pacmanX, int pacmanY, int blinkyX, int blinkyY, Mapa map) {
        if (modoOlhos) {
            // Retornar para a casa dos fantasmas (posição fixa)
            return calcularMelhorRota(13, 11, map); // Ajuste conforme o comportamento do Clyde
        } else if (modoFuga) {
            // Fugir do Pac-Man (ir para o canto oposto)
            return calcularMelhorRota(scatterX, scatterY, map);
        } else {
            // Clyde segue o Pac-Man, mas ele muda de comportamento dependendo da distância
            int distancia = calcularDistancia(coordx, coordy, pacmanX, pacmanY);

            // Quando o Clyde está a mais de 8 unidades de distância, ele segue o Pac-Man
            if (distancia > 8) {
                return calcularMelhorRota(pacmanX, pacmanY, map);
            } else {
                // Quando está a menos de 8 unidades de distância, ele vai para a área de Scatter (canto inferior esquerdo)
                return calcularMelhorRota(scatterX, scatterY, map);
            }
        }
    }

    @Override
    public void respawn() {
        this.coordx = 10; // Posição inicial do Clyde (ajuste conforme o mapa)
        this.coordy = 14; // Posição inicial do Clyde (ajuste conforme o mapa)
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