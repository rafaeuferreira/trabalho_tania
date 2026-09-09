/**
 * Um token da expressão: um número ou um símbolo (operador ou parêntese).
 */
public class Token {

    public enum Tipo {
        NUMERO,
        SOMA,       // +
        SUBTRACAO,  // -
        MULTIPLICACAO, // *
        DIVISAO,    // /
        PARENTESE_ESQ, // (
        PARENTESE_DIR  // )
    }

    private final Tipo tipo;
    private final double valor; // usado apenas quando tipo == NUMERO

    public Token(Tipo tipo) {
        this.tipo = tipo;
        this.valor = 0.0;
    }

    public Token(double valor) {
        this.tipo = Tipo.NUMERO;
        this.valor = valor;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public double getValor() {
        return valor;
    }

    public boolean isOperador() {
        return tipo == Tipo.SOMA || tipo == Tipo.SUBTRACAO
                || tipo == Tipo.MULTIPLICACAO || tipo == Tipo.DIVISAO;
    }

    /** Precedencia: * e / valem mais que + e -. Usada no Shunting Yard. */
    public int precedencia() {
        switch (tipo) {
            case MULTIPLICACAO:
            case DIVISAO:
                return 2;
            case SOMA:
            case SUBTRACAO:
                return 1;
            default:
                return 0;
        }
    }

    @Override
    public String toString() {
        switch (tipo) {
            case NUMERO:
                return String.valueOf(valor);
            case SOMA: return "+";
            case SUBTRACAO: return "-";
            case MULTIPLICACAO: return "*";
            case DIVISAO: return "/";
            case PARENTESE_ESQ: return "(";
            case PARENTESE_DIR: return ")";
            default: return "?";
        }
    }
}
