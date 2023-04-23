import java.util.Scanner;

class Controle {

    // ***************************************************************************************
    // Atributos
    // ***************************************************************************************
    private static View tela;
    private static Jogador player;
    private static GerenciadorMissoes missoes;
    private static String mapa[][] = new String[20][50]; //<---
    private static boolean mensagem; //<---
    

    

    // ***************************************************************************************
    // Operações/Métodos
    // ***************************************************************************************

    public static void set_mensagem(boolean mensagem) { //<---
        Controle.mensagem = mensagem; //<---
    } //<---


    public static boolean get_mensagem() { //<---
        return mensagem; //<---
    } //<---



    // ============================================================
    // Método que controla o laço principal do jogo
    // ============================================================
    public static void operacao() {

        //// Limpa a tela
        tela.limpar_tela();

        //IF para verificar se a mensagem é TRUE ou FALSE //<---
        if(mensagem == true){ //<---
            System.out.print("\nParabéns você concluiu a missão"); //<---
            mensagem = false; //<---
        } //<---

        //// Mostra as missões ativas
        /* Obtém a lista de missões */
        String missoes_ativas = missoes.mostrar_missoes();

        /* Apresenta para o usuário */
        tela.mostrar_missoes_ativas(missoes_ativas);

        // Mostra os dados do usuario
        tela.mostrar_infos(player.get_x(), player.get_y());

        //// Mostra o mapa
        tela.desenhar_mapa(mapa, player.get_x(), player.get_y());

        //// Pede um direção para andarw
        String direcao = tela.obter_direcao();

        //// Processa a entrada do usuário
        processar_comando(direcao);
    }


    // ============================================================ //<---
    // Método que recebe a posição da missão //<---
    // ============================================================ //<---
    public static boolean eh_destino(int x, int y) { //<---
        return missoes.eh_destino(x, y); //<---
    } //<---


    // ============================================================
    // Método que realiza os procedimentos necessários para cada
    // comando do usuário
    // ============================================================
    public static void processar_comando(String comando) {

        /* Nova posição caso se mova */
        int nova_posicao_x = player.get_x();
        int nova_posicao_y = player.get_y();

        if (comando.equalsIgnoreCase("a")) { // <---
            nova_posicao_x--;
        } else if (comando.equalsIgnoreCase("d")) { // <---
            nova_posicao_x++;
        } else if (comando.equalsIgnoreCase("w")) { // <---
            nova_posicao_y--;
        } else if (comando.equalsIgnoreCase("s")) { // <---
            nova_posicao_y++;
        } 
        
        //IF para receber a tecla "q" e encerrar o programa //<---
        else if (comando.equalsIgnoreCase("q")) { //<---
            System.out.println(" \n Jogo finalizado");
            System.exit(0); //<---       
        } //<---

        /* Se tiver uma parede onde ele quer ir, não faz nada */
        if (mapa[nova_posicao_y][nova_posicao_x].equals("x")) {
            return;
        }

        /* Se não tiver parede, atualiza a posição do jogador */
        player.set_x(nova_posicao_x);
        player.set_y(nova_posicao_y);

        /* Verificar se tem uma missão no local (início ou fim) */
        missoes.verificar_missoes(player.get_x(), player.get_y());

    }

    // ============================================================
    // Verifica no gerenciador de missões se existe uma missão
    // na posição especificada
    // ============================================================
    public static boolean existe_missao(int x, int y) {
        return missoes.existe_missao(x, y);
    }

    // ***************************************************************************************
    // Main
    // ***************************************************************************************

    public static void main(String[] args) {
        //// Inicializa os atributos
        /* Interações com o usuário */
        tela = new View();

        /* Nosso jogador */
        player = new Jogador(25, 10);

        /* Nossas missões */
        missoes = new GerenciadorMissoes();

        //Definido a mensagem para FALSE como padrão //<---
        mensagem = false; //<---

        //// Inicia o jogo
        while(true) {
            operacao();
            //IF para encerrar o programa quando todas as missões forem feitas //<---
            if (Missao.get_pontuacao() == 3 ){ //<---
                operacao(); //<---
                System.out.println("\n Todas as missões foram concluidas e você zerou o jogo"); //<--
                System.exit(0); //<---
            } //<---
        }
    }
}