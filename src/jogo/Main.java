/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jogo;

import jogadores.*;

/**
 *
 * @author tarci
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
                
        Jogador[] jogadores = new Jogador[2];
        // jogadores[0] = new JogadorLinhaColuna("LinhaColuna");
        jogadores[0] = new JogadorAleatorio("Aleatório");
        // jogadores[2] = new Manual("Eu");
        jogadores[1] = new JogadorHeuristico("TheBoys");
        // jogadores[1] = new JogadorMinmaxBuscaProfundidade("TheBoys");
        
        Campeonato campeonato = new Campeonato(jogadores, 50);
        Participacao[] p = campeonato.runPontosCorridos();
        
        for(int i = 0; i < p.length; i++){
            System.out.println("\n\n### JOGADOR: " + p[i].getNome() + "###");
            System.out.println("Vitórias: " + p[i].getVitorias().size() + " - " + p[i].getVitorias().toString());
            System.out.println("Empates: " + p[i].getEmpates().size() + " - " + p[i].getEmpates().toString());
            System.out.println("Derrotas: " + p[i].getDerrotas().size() + " - " + p[i].getDerrotas().toString());            
        }
        
    }
    
}
