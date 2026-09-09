import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

/**
 * Calcula uma expressao em RPN usando uma pilha de double.
 * Numero: empilha. Operador: desempilha dois, opera, empilha o resultado.
 */
public class AvaliadorRPN {

    public static double avaliar(List<Token> tokensRPN) {
        Deque<Double> pilha = new ArrayDeque<>();

        for (Token token : tokensRPN) {
            if (token.getTipo() == Token.Tipo.NUMERO) {
                pilha.push(token.getValor());
                continue;
            }

            if (pilha.size() < 2) {
                throw new IllegalArgumentException("Expressão RPN inválida (operandos insuficientes).");
            }

            double b = pilha.pop(); // operando da direita (foi empilhado por último)
            double a = pilha.pop(); // operando da esquerda
            double resultado;

            switch (token.getTipo()) {
                case SOMA:
                    resultado = a + b;
                    break;
                case SUBTRACAO:
                    resultado = a - b;
                    break;
                case MULTIPLICACAO:
                    resultado = a * b;
                    break;
                case DIVISAO:
                    resultado = a / b;
                    break;
                default:
                    throw new IllegalStateException("Token inesperado em RPN: " + token);
            }
            pilha.push(resultado);
        }

        if (pilha.size() != 1) {
            throw new IllegalArgumentException("Expressão RPN inválida (sobraram valores na pilha).");
        }
        return pilha.pop();
    }
}
