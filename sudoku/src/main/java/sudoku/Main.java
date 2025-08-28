package sudoku;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;
import java.util.stream.Collectors;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        String key;
        Scanner input = new Scanner(System.in);
        int i = 0;
        boolean control = true;

        ArrayList<HashMap<Integer, String>> positions = new ArrayList<>();
         Stream.of(args).forEach(s -> {
            HashMap<Integer, String> map = new HashMap<>();
            String[] array = s.replaceAll(",", "").split("/");
            for (String c : array) {
                map.put(Integer.parseInt(c.split(";")[0]), c.split(";")[1]);
            }
            positions.add(map);
        });

        Tabuleiro tabuleiro = new Tabuleiro(positions.get(i));

        System.out.println(tabuleiro.GetcasaString());
        while (control) { 
            System.out.println("digite: \n(1)novo jogo \n(2)inserir numero \n(3)remover numero \n(4)verificar jogo \n(5)verificar status jogo \n(6)limpar tabuleiro");
            int opcao = input.nextInt();
            switch(opcao) {
                case 1:
                     tabuleiro = new Tabuleiro(positions.get((++i) % positions.size()));                    
                    break;
                
                case 2:
                    System.out.println("digite a posicao no formato linha,coluna");
                    key = input.next();
                    System.out.println("digite o valor");
                    tabuleiro.SetCasaS(key,input.next());
                    break;

                case 3:
                    System.out.println("digite a posicao no formato linha,coluna");
                    key = input.next();
                    if(!positions.get(i).containsKey(Integer.parseInt(key.replace(",", "")))) {
                        tabuleiro.SetCasaS(key," ");
                    }
                    break;

                case 4:
                    System.out.println(tabuleiro.GetcasaString());
                    break;

                case 5:
                    System.out.println(tabuleiro.GetcasaString());
                    if(tabuleiro.valido()) {
                        System.out.println("parabéns você ganhou");
                    } else { 
                        System.out.println("invalido");
                    }
                case 6: 
                    tabuleiro = new Tabuleiro(positions.get(i));
                    break;
                case 7:
                    input.close();
                    control = false;
                    break;
                default:
                    System.out.println("opção inexistente");
            }
        }
    }
}