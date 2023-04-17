import java.util.Random;

public class Missao {

    public static char[] get_pontuacao;
    // ***************************************************************************************
    // Atributos
    // ***************************************************************************************
    private String texto;
    private int x;
    private int y;
    private int destino_x;
    private int destino_y;
    private boolean missao_ativa;
    private boolean missao_completa;

    private static int pontuacao; //<---
    

    // ***************************************************************************************
    // Operações/Métodos
    // ***************************************************************************************
    
    // ============================================================
    // Inicia uma missão, atribuindo um destino aleatório a ela
    // ============================================================
    public void iniciar_missao() {
        // Ativa a missão
        this.missao_ativa = true;

        // Gera um objetivo aleatório
        Random gerador = new Random();
        this.destino_x = gerador.nextInt(4) + 1; //<---
        this.destino_y = gerador.nextInt(4) + 1; //<---

        // garante que ele vai estar entre 1 e 5
        if (destino_x < 0) destino_x = -destino_x;
        if (destino_y < 0) destino_y = -destino_y;
    }

    // ============================================================
    // Contador de pontuação do jogador 
    // ============================================================
    public static void incrementar_pontuacao(){ //<---
        pontuacao += 1; //<---
    } //<---

    // ============================================================
    // Completa uma missão, definindo seus atributos de controle
    // ============================================================
    public void completar_missao() {
        this.missao_completa = true;
        this.missao_ativa = false;
        incrementar_pontuacao(); //<---
    }

    // ============================================================
    // Getters e setters
    // ============================================================
    public String get_texto() {
        return texto + " - Destino: (" + destino_x + ", " + destino_y + ") " + "Pontuação do jogador: " + pontuacao  ; //<---
    }

    public int get_x() {
        return x;
    }

    public int get_y() {
        return y;
    }


    public int get_destino_x() {
        return destino_x;
    }

    public int get_destino_y() {
        return destino_y;
    }

    public boolean get_ativa() {
        return missao_ativa;
    }

    public boolean get_completa() {
        return missao_completa;
    }

    // ***************************************************************************************
    // Construtor
    // ***************************************************************************************
    public Missao (String texto, int x, int y) {
        // Inicializa todos os atributos
        this.texto = texto;
        this.x = x;
        this.y = y;
        this.destino_x = -1;
        this.destino_y = -1;
        this.missao_ativa = false;
        this.missao_completa = false;
    }
}
