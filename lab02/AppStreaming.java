import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
public class AppStreaming{
    public static void main(String[] args){
        List<Midia> midias = new ArrayList<>();
        Scanner scan=new Scanner(System.in);
        int opcao;
        do{
            System.out.println("Menu (selecione uma opção):");
            System.out.println("1- Adicionar filme:");
            System.out.println("2- Adicionar serie:");
            System.out.println("3- Exibir Midias:");
            System.out.println("4- Sair:");
            opcao=scan.nextInt();
            switch(opcao){
                case 1:
                    scan.nextLine(); 
                    System.out.println("Titulo do filme:");
                    String tituloFilme=scan.nextLine();
                    System.out.println(" ");
                    System.out.println("Duracao do filme:");
                    long dura=scan.nextLong();
                    Filme filme = new Filme(tituloFilme,dura);
                    midias.add(filme);
                    break;

                case 2:
                    scan.nextLine();
                    System.out.println("Titulo da serie:");
                    String tituloSerie=scan.nextLine();
                    Serie serie = new Serie(tituloSerie);
                    Temporada Temp1= new Temporada(1);
                    serie.adicionar(Temp1);
                    Temporada Temp2=new Temporada(2);
                    serie.adicionar(Temp2);
                    System.out.println("Nome episodio 1 da temporada 1:");
                    String epUm=scan.nextLine();
                    System.out.println("duração episodio 1 da temporada 1:");
                    long dEpUm=scan.nextLong();
                    Episodio episodio1 = new Episodio(epUm,dEpUm);
                    Temp1.adicionar(episodio1);
                    
                    scan.nextLine();
                    System.out.println("Nome episodio 2 da temporada 1:");
                    String epDos=scan.nextLine();
                    System.out.println("duração episodio 2 da temporada 1:");
                    long dEpDos=scan.nextLong();
                    Episodio episodio2 = new Episodio(epDos,dEpDos);
                    Temp1.adicionar(episodio2);

                    scan.nextLine();
                    System.out.println("Nome episodio 1 da temporada 2:");
                    String epUmd=scan.nextLine();
                    System.out.println("duração episodio 1 da temporada 2:");
                    long dEpUmd=scan.nextLong();
                    Episodio episodio3 = new Episodio(epUmd,dEpUmd);
                    Temp2.adicionar(episodio3);

                    scan.nextLine();
                    System.out.println("Nome episodio 2 da temporada 2:");
                    String epDosd=scan.nextLine();
                    System.out.println("duração episodio 2 da temporada 2:");
                    long dEpDosd=scan.nextLong();
                    Episodio episodio4 = new Episodio(epDosd,dEpDosd);
                    Temp2.adicionar(episodio4);
                    midias.add(serie);
                    break;

                case 3:
                    for(Midia m:midias){
                        System.out.println(m.info());
                    }
                    break;
                default:
                    System.out.println("selecione uma oção valida!");
                    break;
                }
            }while(opcao!=4);
        
    }
}