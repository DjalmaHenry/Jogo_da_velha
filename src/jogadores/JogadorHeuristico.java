package jogadores;

public class JogadorHeuristico extends Jogador {

    public JogadorHeuristico(String nome) {
        super(nome);
    }

    @Override
    public int[] jogar(int[][] tabuleiro) {
        // search for alpha beta prunning, check empty space, best move and play
        int[] bestMove = new int[2];
        int bestValue = Integer.MIN_VALUE;
        int depth = 0;
        int alpha = Integer.MIN_VALUE;
        int beta = Integer.MAX_VALUE;
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
                    value = minmax(tabuleiroCopy, depth, false, alpha, beta);
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

    // minmax function with alpha and beta params
    public int minmax(int[][] tabuleiro, int depth, boolean isMax, int alpha, int beta) {
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
                        best = Math.max(best, minmax(tabuleiro, depth + 1, !isMax, alpha, beta));
                        tabuleiro[i][j] = -1;
                        alpha = Math.max(alpha, best);
                        if (beta <= alpha) {
                            break;
                        }
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
                        best = Math.min(best, minmax(tabuleiro, depth + 1, !isMax, alpha, beta));
                        tabuleiro[i][j] = -1;
                        beta = Math.min(beta, best);
                        if (beta <= alpha) {
                            break;
                        }
                    }
                }
            }
            return best;
        }
    }

    // evaluate function to board N x N matrix
    public int evaluate(int[][] tabuleiro) {
        // check rows
        for (int i = 0; i < tabuleiro.length; i++) {
            for (int j = 0; j < tabuleiro.length - 3; j++) {
                int sum = 0;
                for (int k = 0; k < 4; k++) {
                    sum += tabuleiro[i][j + k];
                }
                if (sum == 4) {
                    return 1;
                }
                if (sum == -4) {
                    return -1;
                }
            }
        }
        // check columns
        for (int i = 0; i < tabuleiro.length - 3; i++) {
            for (int j = 0; j < tabuleiro.length; j++) {
                int sum = 0;
                for (int k = 0; k < 4; k++) {
                    sum += tabuleiro[i + k][j];
                }
                if (sum == 4) {
                    return 1;
                }
                if (sum == -4) {
                    return -1;
                }
            }
        }
        // check diagonals
        for (int i = 0; i < tabuleiro.length - 3; i++) {
            for (int j = 0; j < tabuleiro.length - 3; j++) {
                int sum = 0;
                for (int k = 0; k < 4; k++) {
                    sum += tabuleiro[i + k][j + k];
                }
                if (sum == 4) {
                    return 1;
                }
                if (sum == -4) {
                    return -1;
                }
            }
        }
        for (int i = 0; i < tabuleiro.length - 3; i++) {
            for (int j = 3; j < tabuleiro.length; j++) {
                int sum = 0;
                for (int k = 0; k < 4; k++) {
                    sum += tabuleiro[i + k][j - k];
                }
                if (sum == 4) {
                    return 1;
                }
                if (sum == -4) {
                    return -1;
                }
            }
        }
        return 0;
    }

    // check if there are moves left
    public boolean isMovesLeft(int[][] tabuleiro) {
        for (int i = 0; i < tabuleiro.length; i++) {
            for (int j = 0; j < tabuleiro.length; j++) {
                if (tabuleiro[i][j] == -1) {
                    return true;
                }
            }
        }
        return false;
    }

}
