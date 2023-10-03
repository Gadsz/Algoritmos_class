import java.util.Random; //definição pra random das bombas
import java.util.Scanner; //scanner usado

public class Minado {

    // Vairiáveis

    // variáveis privadas, só podem ser alteradas dentro da class onde ela está
    private int QuantidadeBombas; // cuida da quantidade de bombas
    private int TamanhoTabuleiro; // cuida do tamanho do tabuleiro
    private boolean SituacaoJogo; // boolean que cuida da situação do jogo (verdadeiro ou falso)
    private char[][] Tabuleiro; // cuida do tamanho do tabuleiro
    private char[][] TabuleiroVisivel; // células visiveis pelo jogador

    public Minado(int TamanhoTabuleiro, int QuantidadeBombas) {
        this.TamanhoTabuleiro = TamanhoTabuleiro; // Isso define o tamanho do tabuleiro do jogo.
        this.QuantidadeBombas = QuantidadeBombas; // preciso desses 2 pra inicializar as propriedades do objeto.
        this.Tabuleiro = new char[TamanhoTabuleiro][TamanhoTabuleiro]; //
        this.TabuleiroVisivel = new char[TamanhoTabuleiro][TamanhoTabuleiro];
        this.SituacaoJogo = true; // começa o jogo puxando a variável booleana de true ou false
        IniciarTabuleiro(); // inicio
        ColocarBombas(); // aqui ele coloca as bombas em jogo e inica o tabuleiro de forma usa a classe
                         // random
    }
                            //SELECIONAR DIFICULDADE E CONFIGURAÇÕES DO JOGO
    public static void main(String[] args) {

        int TamanhoTabuleiro = 9, 
            NumBombas = 6; // define as variáveis em jogo para que você possa alterar como o
                                                 // jogo ficou

        Minado campo_minado = new Minado(TamanhoTabuleiro, NumBombas); // cria a principal classe do jogo minado
        campo_minado.Jogar(); // esse main cria a instância do jogo de campo minado
                            //FIM DO SELECIONAR DIFICULDADE E CONFIGURAÇÕES DO JOGO
    }

    public void IniciarTabuleiro() { // retorna nenhum valor por estar iniciando no void

        // Criando o tabuleiro com as bombas

        for (int i = 0; i < TamanhoTabuleiro; i++) { // loop em for sobre i sendo menor que o tabuleiro do jogo
            for (int j = 0; j < TamanhoTabuleiro; j++) { // segundo loop em for
                Tabuleiro[i][j] = ' '; // configura que todas as células comecem em branco sem bombas, isso dentro dos
                                       // loops com as variaveis i e j
                TabuleiroVisivel[i][j] = '-'; // isso faz com que as celulas fiquem invisiveis no inicio, o traço efetua
                                              // isso com a variavel visivel que tem no começo
            }
        }
    }

    public void ColocarBombas() {
        Random randomico = new Random(); // inicia as bombas usando a variável util random
        int BombasInseridas = 0; // inicia a variável com 0

        // Colocação das bombas

        while (BombasInseridas < QuantidadeBombas) { // responsável por colocar randomicamente as bombas no tabuleiro do
                                                     // jogo
            int x = randomico.nextInt(TamanhoTabuleiro); // eixo x
            int y = randomico.nextInt(TamanhoTabuleiro); // eixo y

            if (Tabuleiro[x][y] != '*') { // verifica se condição tem bomba nos eixos com o caracter *
                Tabuleiro[x][y] = '*'; // incrementa uma bomba na célula
                BombasInseridas++; // aumenta o contador de bombas
            }
        }
    }

    public void TabuleiroVisivel() {
        System.out.println("Tabuleiro de CAMPO MINADO:");

        // Criando tabuleiro visivel

        for (int i = 0; i < TamanhoTabuleiro; i++) { 
            // passa pelas colunas com i=0 até ser menor que o tabuleiro
            for (int j = 0; j < TamanhoTabuleiro; j++) { 
                // passa pelas colunas com j=0 até ser menor que o tabuleiro
                System.out.print(TabuleiroVisivel[i][j] + " "); // cria um espaço entre cada celula i e j
            }
            System.out.println(); // imprime uma linha vazia para que seja impresso uma embaixo da outra
        }
    }

    public void Jogar() { // todo esse bloco permite que o jogador interaja com o campo minado
        Scanner entrada = new Scanner(System.in); // cria o scanner pra permitir a entrada de a partir do console
        int linha, coluna; // criado pra armazenar as coordenadas pra revelar ao jogador

        while (SituacaoJogo) { // puxa a variável do começo do jogo indicando que está true

            TabuleiroVisivel(); // puxa a variável pra mostrar o que é visivel e como esta o jogo
            System.out.print("Digite a linha de 0 a " + (TamanhoTabuleiro - 1) + ": ");
            linha = entrada.nextInt(); // input para dar entrada das coordenadas puxando o tamanho do tabuleiro que vai
                                       // de 0 a 9

            System.out.print("Digite a coluna de 0 a " + (TamanhoTabuleiro - 1) + ": "); // subtrai o 1 pra obter o
                                                                                         // valor maximo desse tabuleiro
            coluna = entrada.nextInt(); // input para dar entrada das coordenadas puxando o tamanho do tabuleiro que vai
                                        // de 0 a 9

            if (linha < 0 || linha >= TamanhoTabuleiro || coluna < 0 || coluna >= TamanhoTabuleiro) {
                System.out.println("Célula inválida: insira novamente."); // verifica se o tamanho inserido pelo jogador
                                                                          // é válido pro tamanho de tabuleiro em jogo
                continue;
            }

            if (Tabuleiro[linha][coluna] == '*') { // verifica se existe uma bomba na célula onde o jogador selecionou
                System.out.println("Uma bomba explodiu, você perdeu!");
                SituacaoJogo = false; // muda a situação do jogo pra false indicando que o jogo acabou
            } else {
                Descobrircel(linha, coluna);
                FimJ();
            }
        }
        entrada.close(); // fim dessa parte e fecha o scanner
    }

    private void FimJ() {
    }

    public void Descobrircel(int linha, int coluna) { // revela a celular selecionada pelo jogador

        int BombasAdj, i, j; // bombas adjacentes posicionadas no tabuleiro e pra loops depois

        if (TabuleiroVisivel[linha][coluna] != '-') { // essa linha verifica se o código ja foi descoberto e se sim vai
                                                      // retornar o texto
            System.out.println("Esta célula já foi descoberta.");
            return;
        }

        BombasAdj = ContarQntBombasAdj(linha, coluna); // conta quantas bombas estão adjacentes no que o usuário colocou
                                                       // e armazena o resultado em bombasadj
        TabuleiroVisivel[linha][coluna] = (char) (BombasAdj + '0'); // atualiza a celular visivel convertendo bombasadj
                                                                    // para o char 0 trocando o - por 0
        if (BombasAdj == 0) { // verificar se tem bombas, verificando se bombasadj é igual a 0

            for (i = linha - 1; i <= linha + 1; i++) { // esses loops percorrem uma matrix 3x3
                for (j = coluna - 1; j <= coluna + 1; j++) { // percorre 3 colunas para verificar o que esta em volta
                                                             // pra ver se tem bombas ou não
                    if (i >= 0 && i < TamanhoTabuleiro && j >= 0 && j < TamanhoTabuleiro) { // verifica se tá no limite
                                                                                            // do tabuleiro
                        Descobrircel(i, j); // revela as células
                    }
                }
            }
        }
    }

    public int ContarQntBombasAdj(int linha, int coluna) { // contar quantas bombas estão próximas de uma celula

        int BombasAdj = 0; // inicia a variável bombasadj
        int i, j; // variáveis das colunas e linhas

        for (i = linha - 1; i <= linha + 1; i++) { // linhas
            for (j = coluna - 1; j <= coluna + 1; j++) { // colunas
                if (i >= 0 && i < TamanhoTabuleiro && j >= 0 && j < TamanhoTabuleiro && Tabuleiro[i][j] == '*') {
                    // verifca se o input do usuario esta dentro dos limites do tabuleirO
                    BombasAdj++;
                }
            }
        }
        return BombasAdj;
    }

    public void FimJogo() {
        boolean vitoria = true;
        int i, j;

        for (i = 0; i < TamanhoTabuleiro; i++) {
            for (j = 0; j < TamanhoTabuleiro; j++) { // verifica se não tem bombas nas celulas
                if (Tabuleiro[i][j] != '*' && TabuleiroVisivel[i][j] == '-') { // e se essa bomba não foi revelada e se
                                                                               // bater
                    vitoria = false;// o jogo vai pra false mostrando que o jogador perdeu
                    break;
                }
            }
        }

        if (vitoria) { // caso a vitoria retrone como veradeiro no boolean ele marca que o jogador
                       // ganhou printando a mensagem de vencedor
            System.out.println("Parabéns você ganhou!");
            SituacaoJogo = false; // encerra o jogo mudando a situação pra false
        }
    }

}