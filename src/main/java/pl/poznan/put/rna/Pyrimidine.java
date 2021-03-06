package pl.poznan.put.rna;

import java.util.List;

import pl.poznan.put.atom.AtomName;
import pl.poznan.put.rna.torsion.Chi;
import pl.poznan.put.types.Quadruplet;

public abstract class Pyrimidine extends Base {
    protected Pyrimidine(List<AtomName> atoms, String longName,
            char oneLetterName, String... names) {
        super(atoms, longName, oneLetterName, names);
        torsionAngleTypes.add(Chi.getPyrimidineInstance());
    }

    @Override
    public Quadruplet<AtomName> getChiAtoms() {
        return Chi.PYRIMIDINE_ATOMS;
    }
}
