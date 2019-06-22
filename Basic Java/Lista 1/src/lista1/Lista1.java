package lista1;
import java.util.*;

public class Lista1 {
    private static final String[] J = {
        "zero ", "jeden ","dwa ","trzy ","cztery ","pięć ",
        "sześć ","siedem ","osiem ","dziewięć " 
    };
    private static final String[] N = {
        "", "jedenaście ","dwanaście ","trzynaście ","czterynaście ","pietnaście ",
        "szesnaście ","siedemnaście" ,"osiemnaście" ,"dziewiętnaście " 
    };
    private static final String[] D = {
        "", "dziesięć ","dwadzieścia ","trzydzieści ","czterdzieści ","pięćdziesiąt ",
        "sześćdziesiąt ","siedemdziesiąt ","osiemdziesąt ","dziewięćdziesiąt " 
    };
    private static final String[] S = {
        "", "sto ","dwieście ","trzysta ","czterysta ","pięćset ",
        "sześćset ","siedemset ","osiemset ","dziewięćset " 
    };
    public static void setek(int liczba){
        int setki = liczba%1000/100;
        int dzies = liczba%100/10;
        int jedn = liczba%10;
        if(setki > 0)
            System.out.println(S[setki]);
        if(dzies == 1 && jedn > 0)
            System.out.println(N[jedn]);
        else if(dzies > 0)
            System.out.println(D[dzies]);
        if(dzies != 1 && jedn > 0)
            System.out.println(J[jedn]);
    }
    public static void odmiana(int liczba, String[] forma) {
        if(liczba > 0){
            setek(liczba);
            if(liczba == 1){
                System.out.println(forma[0]);
            }
            else if(liczba < 5){
                System.out.println(forma[1]);  
            }
            else{
                System.out.println(forma[2]);    
            }
        }
    }
    public static void main(String[] args) {
        
        int len = args.length;
        for (int i = 0; i != len; i++) {
            int number;
            try{
                number = new Integer(args[i]);
            }
            catch(NumberFormatException e){
                System.err.println("Nieprawidłowa liczba \n");
                continue;
            }
            if(number < 0){
                System.out.println("minus");
                number = number*-1;
            }
            if(number == 0){
                System.out.println("zero\n");
            }
            else{
                int bln = number/1000000000;
                int mln = number%1000000000/1000000;
                int tys = number%1000000/1000;
                odmiana(bln,new String[]{"miliard ","miliardy ","miliardów "});
                odmiana(mln,new String[]{"milion ","milion y","milionów "});
                odmiana(tys,new String[]{"tysiąc ","tysiące ","tysięcy "});
                setek(number);
                System.out.println("\n");
            }
         }
    }
}
