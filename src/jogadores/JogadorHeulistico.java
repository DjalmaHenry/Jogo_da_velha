package jogadores;

public class JogadorHeulistico extends Jogador {

    public JogadorHeulistico(String nome) {
        super(nome);
    }

    @Override
    public int[] jogar(int[][] tabuleiro) {
        int[] bestMove = new int[2];
        int bestValue = Integer.MIN_VALUE;
        int depth = 0;
        int value = 0;
        int[][] tabuleiroCopy = new int[tabuleiro.length][tabuleiro.length];
        for (int i = 0; i < tabuleiro.length; i++) {
            for (int j = 0; j < tabuleiro.length; j++) {
                tabuleiroCopy[i][j] = tabuleiro[i][j];
            }
        }
        for (int i = 0; i < tabuleiro.length; i++) {
            for (int j = 0; j < tabuleiro.length; j++) {
                if (tabuleiro[i][j] == -1) {
                    tabuleiroCopy[i][j] = 0;
                    value = minmax(tabuleiroCopy, depth, false);
                    tabuleiroCopy[i][j] = -1;
                    if (value > bestValue) {
                        bestMove[0] = i;
                        bestMove[1] = j;
                        bestValue = value;
                    }
                }
            }
        }
        return bestMove;
    }

    public int minmax(int[][] tabuleiro, int depth, boolean isMax) {
        int score = evaluate(tabuleiro);
        if (score == 1) {
            return score;
        }
        if (score == -1) {
            return score;
        }
        if (isMovesLeft(tabuleiro) == false) {
            return 0;
        }
        if (isMax) {
            int best = Integer.MIN_VALUE;
            for (int i = 0; i < tabuleiro.length; i++) {
                for (int j = 0; j < tabuleiro.length; j++) {
                    if (tabuleiro[i][j] == -1) {
                        tabuleiro[i][j] = 0;
                        best = Math.max(best, minmax(tabuleiro, depth + 1, !isMax));
                        tabuleiro[i][j] = -1;
                    }
                }
            }
            return best;
        } else {
            int best = Integer.MAX_VALUE;
            for (int i = 0; i < tabuleiro.length; i++) {
                for (int j = 0; j < tabuleiro.length; j++) {
                    if (tabuleiro[i][j] == -1) {
                        tabuleiro[i][j] = 1;
                        best = Math.min(best, minmax(tabuleiro, depth + 1, !isMax));
                        tabuleiro[i][j] = -1;
                    }
                }
            }
            return best;
        }
    }

    public int evaluate(int[][] tabuleiro) {
        for (int row = 0; row < 3; row++) {
            if (tabuleiro[row][0] == tabuleiro[row][1] && tabuleiro[row][1] == tabuleiro[row][2]) {
                if (tabuleiro[row][0] == 0) {
                    return +1;
                } else if (tabuleiro[row][0] == 1) {
                    return -1;
                }
            }
        }
        for (int col = 0; col < 3; col++) {
            if (tabuleiro[0][col] == tabuleiro[1][col] && tabuleiro[1][col] == tabuleiro[2][col]) {
                if (tabuleiro[0][col] == 0) {
                    return +1;
                } else if (tabuleiro[0][col] == 1) {
                    return -1;
                }
            }
        }
        if (tabuleiro[0][0] == tabuleiro[1][1] && tabuleiro[1][1] == tabuleiro[2][2]) {
            if (tabuleiro[0][0] == 0) {
                return +1;
            } else if (tabuleiro[0][0] == 1) {
                return -1;
            }
        }

        // check main diagonal
        // for (int i = 0; i < tabuleiro.length; i++) {

        // if (tabuleiro[i][i] == tabuleiro[i + 1][i + 1] && tabuleiro[i + 1][i + 1] ==
        // tabuleiro[i + 2][i + 2]) {
        // if (tabuleiro[i][i] == 0) {
        // return +1;
        // } else if (tabuleiro[i][i] == 1) {
        // return -1;
        // }
        // }

        // }

        if (tabuleiro[0][2] == tabuleiro[1][1] && tabuleiro[1][1] == tabuleiro[2][0]) {
            if (tabuleiro[0][2] == 0) {
                return +1;
            } else if (tabuleiro[0][2] == 1) {
                return -1;
            }
        }

        // check secondary diagonal
        // for (int i = 0; i < tabuleiro.length; i++) {

        // if (tabuleiro[i][i + 2] == tabuleiro[i + 1][i + 1] && tabuleiro[i + 1][i + 1]
        // == tabuleiro[i + 2][i]) {
        // if (tabuleiro[i][i + 2] == 0) {
        // return +1;
        // } else if (tabuleiro[i][i + 2] == 1) {
        // return -1;
        // }
        // }

        // }
        return 0;
    }

    public boolean isMovesLeft(int[][] tabuleiro) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (tabuleiro[i][j] == -1) {
                    return true;
                }
            }
        }
        return false;
    }

}
