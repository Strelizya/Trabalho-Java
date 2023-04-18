import java.util.ArrayList;

public class GerenciadorMissoes {
    // ***************************************************************************************
    // Atributos
    // ***************************************************************************************

    // Lista de missões
    ArrayList<Missao> missoes;

    // ***************************************************************************************
    // Operações/Métodos
    // ***************************************************************************************

    // ============================================================
    // Verifica se o jogador está no inicio ou no fim de uma missao
    // ============================================================
    void verificar_missoes(int jogador_x, int jogador_y) {

        // --------------------------------------------------
        // Descobre se o jogador está no inicio de uma missao
        // --------------------------------------------------

        //// Percorre a lista de missões
        for (int i = 0; i < missoes.size(); i++) {

            //// Compara o início da missão com a posição do jogador
            if ((jogador_x == missoes.get(i).get_x()) &&
                (jogador_y == missoes.get(i).get_y())) {

                /* Se a missão já foi iniciada, não precisa iniciar novamente */
                if (missoes.get(i).get_ativa() == true) break;

                /* Iniciar missão */
                missoes.get(i).iniciar_missao();

            }
        }

        // -----------------------------------------------
        // Descobre se o jogador está no fim de uma missao
        // -----------------------------------------------
        for (int i = 0; i < missoes.size(); i++) {

            //// Compara o fim da missão com a posição do jogador
            if ((jogador_x == missoes.get(i).get_destino_x()) &&
                (jogador_y == missoes.get(i).get_destino_y())) {

                /* Se a missão não está em andamento, não pode completar */
                if (missoes.get(i).get_ativa() == false) break;

                /* Completar missão */
                this.completar_missao(i);

                //Setando a mensagem para TRUE após a missão ser concluida //<---
                Controle.mensagem = true; //<---

            }
        }
    }

    // ============================================================
    // Mostra todas as missoes em andamento
    // ============================================================
    String mostrar_missoes() {

        String missoes_ativas = "";

        //// Percorre a lista de missões
        for(int i = 0; i < missoes.size(); i++) {

            /* Se a missão está ativa, obtém seu texto */
            if (missoes.get(i).get_ativa() == true) {
                missoes_ativas += missoes.get(i).get_texto() + System.lineSeparator();
            }
        }

        /* Devolve a lista com todas as missões ativas */
        return missoes_ativas;
    }

    // ============================================================
    // Realiza os procedimentos para completar uma missão
    // ============================================================
    private void completar_missao(int i) {
        /* Altera o status da missão */
        missoes.get(i).completar_missao();
    }

    // ===================================================================
    // Verifica se existe uma missão ainda não realizada na coordenada x,y
    // ===================================================================
    boolean existe_missao(int x, int y) {

        //// Percorre a lista de missões
        for(int i = 0; i < missoes.size(); i++) {

            /* Compara a posição da missão com a posição x,y */
            if ((missoes.get(i).get_x() == x) &&
                (missoes.get(i).get_y() == y)) {

                /* Se a missão não foi realizada, retorna true */
                if ((missoes.get(i).get_ativa() == false) &&
                    (missoes.get(i).get_completa() == false)) {
                    return true;
                }
            }
        }

        /* Se não encontrou nenhuma missão, retorna false */
        return false;
    }

    // ============================================================
    // Verifica se a posição é o destino de uma missão ativa
    // ============================================================
    boolean eh_destino(int x, int y) {

        //// Percorre a lista de missões
        for(int i = 0; i < missoes.size(); i++) {

            /* Compara a posição da missão com a posição x,y */
            if ((missoes.get(i).get_destino_x() == x) &&
                (missoes.get(i).get_destino_y() == y)) {

                /* Se a missão está ativa, retorna true */
                if (missoes.get(i).get_ativa() == true) {
                    return true;
                }
            }
        }

        /* Se não encontrou nenhuma missão, retorna false */
        return false;
    }


    // ***************************************************************************************
    // Construtor
    // ***************************************************************************************
    public GerenciadorMissoes() {
        /* Cria a lista de missões */
        missoes = new ArrayList<Missao>();

        /* Adiciona missões */
        missoes.add(new Missao("Missão: Encontre o anel de fogo", 1, 1));
        missoes.add(new Missao("Missão: Encontre a pedra de jade", 4, 4));
        missoes.add(new Missao("Missão: Encontre a lótus cinza", 4, 1));
    }

}
