import java.util.List;
import java.util.ArrayList;

public class Serie extends Midia {
    private  List<Temporada> temporadas;

     public Serie(String titulo){
        super(titulo);
        temporadas = new ArrayList<>();
    }
    public void adicionar(Temporada temp) {
        temporadas.add(temp);
    }
    @Override
    public long getDuracao() {
        long duracao=0;
        for(Temporada t:temporadas){
            duracao= duracao + t.getDuracao();
        }
        return duracao;
    }

    @Override
    public String info() {
        return "Serie: " + super.info() + "; Temporadas: " + temporadas.size() + " Duração: " + getDuracao();
    }
}