package sudoku;

import java.util.HashMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Tabuleiro {
    private final HashMap<Integer, String> casas = new HashMap<>();
    private final String[] numeros = {"1", "2", "3", "4", "5", "6", "7", "8", "9"};

    public void SetCasaS(String keyString, String value) { 
        int key = Integer.parseInt(Stream.of(keyString.split(",")).reduce("", (c, b) -> c + b));
        if(casas.get(key).equals(" ")) {
            casas.replace(key, value);
        }
    }

    public void SetCasa(int key, String value){ 
        casas.replace(key, value);
    }

    public Tabuleiro() {
        int i = 1;
        int j = 1;
        while(i < 10){ 
            while (j < 10) {
                casas.put(((i * 10) + j)," ");
                j++;
            }
            i++;
        }
    }

    public Tabuleiro(HashMap<Integer, String> base) {
        int i = 1;
        while(i < 10){ 
            int j = 0;
            while (j < 10) {
                casas.put(((i * 10) + j)," ");
                j++;
            }
            i++;
        }
        base.keySet().stream().forEach(key -> SetCasa(key, base.get(key)));
    }

    public String GetcasaString(){ 
        int i = 1;
        String menssagem = "-------------------\n|";
        while(i < 10){ 
            int j = 1;
            while (j < 10) {
                menssagem = (menssagem +casas.get((i * 10) + j) + "|");
                j++;
            }
            menssagem = (menssagem + "\n-------------------\n");
            if(i + j <= 18){
                menssagem = (menssagem + "|");
            }
            i++;
        }
        return menssagem;
    }
    public boolean valido() {
        int i = 0;
        while(i < 10) { 
            if(!((Linha(i)) || (Coluna(i)) || (Quadrado(i)))) {
                return false;
            }
            i++;
        }
        return true;
    }

    private boolean Linha(int linha) {
        return Stream.of(numeros).allMatch(n -> casas.keySet().stream().filter(key -> key % 10 == linha).collect(Collectors.toMap(key -> key ,key -> casas.get(key))).containsValue(n));   
    }

    private  boolean Coluna(int coluna) { 
        return Stream.of(numeros).allMatch(n -> casas.keySet().stream().filter(key -> key % 10 == coluna).collect(Collectors.toMap(key -> key ,key -> casas.get(key))).containsValue(n));
    }

    private  boolean Quadrado(int quadrado) { 
        return Stream.of(numeros).allMatch(n -> casas.keySet().stream().filter(key -> (quadrado * 11 <= key && key <= quadrado * 11 )).collect(Collectors.toMap(key -> key ,key -> casas.get(key))).containsValue(n));   
    }
}