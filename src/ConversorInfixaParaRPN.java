import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * Converte a lista de tokens da notacao infixa para RPN usando o
 * algoritmo Shunting Yard.
 */
public class ConversorInfixaParaRPN {

    public static List<Token> converter(List<Token> tokensInfixos) {
        List<Token> saida = new ArrayList<>();
        Deque<Token> pilhaOperadores = new ArrayDeque<>();

        for (Token token : tokensInfixos) {
            switch (token.getTipo()) {
                case NUMERO:
                    saida.add(token);
                    break;

                case SOMA:
                case SUBTRACAO:
                case MULTIPLICACAO:
                case DIVISAO:
                    // Tira da pilha os operadores de precedencia maior ou
                    // igual antes de empilhar o novo. Isso resolve tambem a
                    // associatividade a esquerda (8 - 3 - 2 = (8 - 3) - 2).
                    while (!pilhaOperadores.isEmpty()
                            && pilhaOperadores.peek().isOperador()
                            && pilhaOperadores.peek().precedencia() >= token.precedencia()) {
                        saida.add(pilhaOperadores.pop());
                    }
                    pilhaOperadores.push(token);
                    break;

                case PARENTESE_ESQ:
                    pilhaOperadores.push(token);
                    break;

                case PARENTESE_DIR:
                    // Manda para a saida tudo que foi empilhado depois do
                    // parentese de abertura correspondente.
                    while (!pilhaOperadores.isEmpty()
                            && pilhaOperadores.peek().getTipo() != Token.Tipo.PARENTESE_ESQ) {
                        saida.add(pilhaOperadores.pop());
                    }
                    if (pilhaOperadores.isEmpty()) {
                        throw new IllegalArgumentException("Parênteses desbalanceados na expressão.");
                    }
                    pilhaOperadores.pop(); // descarta o '(' correspondente
                    break;
            }
        }

        // Esvazia a pilha no final.
        while (!pilhaOperadores.isEmpty()) {
            Token topo = pilhaOperadores.pop();
            if (topo.getTipo() == Token.Tipo.PARENTESE_ESQ) {
                throw new IllegalArgumentException("Parênteses desbalanceados na expressão.");
            }
            saida.add(topo);
        }

        return saida;
    }

    /** Formata uma lista de tokens em RPN como string legível, ex.: "2 3 +". */
    public static String paraString(List<Token> tokensRPN) {
        StringBuilder sb = new StringBuilder();
        for (Token t : tokensRPN) {
            sb.append(t.toString()).append(' ');
        }
        return sb.toString().trim();
    }
}
