package pl.poznan.put.rna.torsion;

import pl.poznan.put.atom.AtomName;
import pl.poznan.put.constant.Unicode;
import pl.poznan.put.pdb.analysis.MoleculeType;
import pl.poznan.put.torsion.AtomBasedTorsionAngleType;
import pl.poznan.put.types.Quadruplet;

public class Epsilon extends AtomBasedTorsionAngleType {
    private static final Epsilon INSTANCE = new Epsilon();

    public static Epsilon getInstance() {
        return Epsilon.INSTANCE;
    }

    private Epsilon() {
        super(MoleculeType.RNA, Unicode.EPSILON, new Quadruplet<AtomName>(AtomName.C4p, AtomName.C3p, AtomName.O3p, AtomName.P), new Quadruplet<Integer>(0, 0, 0, 1));
    }
}
