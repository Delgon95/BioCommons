package pl.poznan.put.rna.base;

import java.util.Arrays;

import pl.poznan.put.atom.AtomName;
import pl.poznan.put.rna.Pyrimidine;

public class Uracil extends Pyrimidine {
    private static final Uracil INSTANCE = new Uracil();

    public static Uracil getInstance() {
        return Uracil.INSTANCE;
    }

    private Uracil() {
        super(Arrays.asList(new AtomName[] { AtomName.N1, AtomName.C6, AtomName.H6, AtomName.C2, AtomName.O2, AtomName.N3, AtomName.H3, AtomName.C4, AtomName.O4, AtomName.C5, AtomName.H5 }), "Uracil", 'U', "U", "URA", "URI");
    }
}