public class CriterioPrecoMinimo implements CriterioBusca{
  public  boolean testar(Produto p, String valor){
        try {
            double precoMinimo = Double.parseDouble(valor);
            return p.getPreco() >= precoMinimo;
        } catch (NumberFormatException e) {
        System.out.println("Valor inválido para preço");
        return false;
}
    
    }
}