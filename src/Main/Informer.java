/**
 * class Informer
 * @package Main
 * @author Andrei Akhramchuk
 * @version 2.0;
 */
package Main;

import java.util.Random;

public class Informer {

    /**
     * previous generated integer
     */
    public static int previus = 0;

    /**
     * Generate random joke info without repetition
     * @return String with info
     */
    public static String generateRandomJokeInfo() {
        Random rn = new Random();
        int rand = rn.nextInt(15) + 1;
        if (previus == rand) {
            if (rand >=15) {
                rand--;
            } else rand++;
        }
        switch (rand) {

            case 1:
                previus = rand;
                return "V nasi galaxii muze byt az 500 milionu planet vhodnych pro zivot.";
            case 2:
                previus = rand;
                return "Vime, ze existuje dalsich 170 miliard galaxii.";
            case 3:
                previus = rand;
                return "Ve vesmiru muzes nalezt giganticky oblak tvoreny alkoholem.";
            case 4:
                previus = rand;
                return "Nejvyssi hora v nasi Slunecni soustave se nachazi na Marsu.";
            case 5:
                previus = rand;
                return "V nasi galaxii je zhruba 400 miliard hvezd.";
            case 6:
                previus = rand;
                return "Vsichni jsme stvoreni z hvezdneho prachu.";
            case 7:
                previus = rand;
                return "Je mozne, ze existuje nekonecne mnozstvi vesmiru.";
            case 8:
                previus = rand;
                return "Jurij Gagarin, prvni clovek ve vesmiru.";
            case 9:
                previus = rand;
                return " Neil Armstrong a Buzz Aldrin - prvni lide na Mesici.";
            case 10:
                previus = rand;
                return "Co se stalo 20. cervence 1969 ?";
            case 11:
                previus = rand;
                return "Okolo planety Uran obiha nejmene 27 mesicu.";
            case 12:
                previus = rand;
                return "Gravitace na Marsu je o 62 % mensi nez na Zemi.";
            case 13:
                previus = rand;
                return "Kazdy den vznikne 275 milionu novych hvezd.";
            case 14:
                previus = rand;
                return "Denne spadne na Zemi asi 10 000 tun vesmirneho prachu a 200 000 meteoritu.";
            case 15:
                previus = rand;
                return "Pokud byste do lzicky nalili hmotu z neutronove hvezdy, byla by jeji hmotnost pres 100 milionu tun.";
        }

        return "Zadna vtipna informace :(";
    }
}
