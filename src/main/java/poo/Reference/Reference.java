package poo.Reference;

public class Reference {
    private int altura;
    private int largura;

    public String toString() {
        String tipo = "Reference";
        if(tipo == "ASCII"){
            return montarRetangulo("+","+","+","+","|","-");
        } else {
            return montarRetangulo("\u250c","\u2510","\u2514","\u2518","\u2502","\u2500");
        }
    }

    private String montarRetangulo(String canto1, String canto2, String canto3, String canto4, String side, String topBottom){
        StringBuilder s = new StringBuilder();

        for(int i=1; i<=this.altura; i++){
            for(int j=1;j<=this.largura;j++){
                if(i==1 && j==1){
                    s.append(canto1);
                } else if(i==1 && j==this.largura){
                    s.append(canto2);
                } else if (i==this.altura && j==1) {
                    s.append(canto3);
                } else if (i==this.altura && j==this.largura){
                    s.append(canto4);
                } else if (i==1 || i==this.altura){
                    s.append(topBottom);
                } else if (j==1 || j==this.largura){
                    s.append(side);
                } else {
                    s.append(" ");
                }

            }
            s.append(System.lineSeparator());
        }
        return s.toString();
    }
}
