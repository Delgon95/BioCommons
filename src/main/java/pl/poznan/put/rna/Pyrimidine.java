package pl.poznan.put.rna;

import java.util.List;

import pl.poznan.put.atom.AtomName;
import pl.poznan.put.types.Quadruplet;

public abstract class Pyrimidine extends Base {
    private static final Quadruplet<AtomName> CHI_ATOMS = new Quadruplet<AtomName>(AtomName.O4p, AtomName.C1p, AtomName.N1, AtomName.C2);

    protected Pyrimidine(List<AtomName> atoms, String longName, char oneLetterName, String... names) {
        super(atoms, longName, oneLetterName, names);
    }

    @Override
    public Quadruplet<AtomName> getChiAtoms() {
        return Pyrimidine.CHI_ATOMS;
    }
}