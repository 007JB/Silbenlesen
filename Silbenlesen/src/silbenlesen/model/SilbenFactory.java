package silbenlesen.model;

import java.util.Random;

public class SilbenFactory {
  public enum Silbe {
    na, ne, ni, no, la, le, li, lo, ma, me, mi, mo,
    //
    Na, Ne, Ni, No, La, Le, Li, Lo, Ma, Me, Mi, Mo,
    //
    en, el, em, on, in, im, il, an, am, al, len, lin, men, lam, mel, mil, man, mon,
    //
    En, El, Em, On, In, Im, Il, An, Am, Al, Len, Lin, Men, Lam, Mel, Mil, Man, Mon,
  };

  public static ISilbenKarte getSilbe() {
    int pick = new Random().nextInt(Silbe.values().length);
    return new SilbenKarte(Silbe.values()[pick].toString());
  }
}
