package pl.poznan.put.protein.torsion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pl.poznan.put.atom.AtomName;
import pl.poznan.put.constant.Unicode;
import pl.poznan.put.pdb.analysis.MoleculeType;
import pl.poznan.put.torsion.AtomBasedTorsionAngleType;
import pl.poznan.put.types.Quadruplet;

public class Chi3 extends AtomBasedTorsionAngleType {
    public static final Quadruplet<AtomName> ARGININE_ATOMS = new Quadruplet<AtomName>(AtomName.CB, AtomName.CG, AtomName.CD, AtomName.NE);
    public static final Quadruplet<AtomName> GLUTAMIC_ACID_ATOMS = new Quadruplet<AtomName>(AtomName.CB, AtomName.CG, AtomName.CD, AtomName.OE1);
    public static final Quadruplet<AtomName> GLUTAMINE_ATOMS = new Quadruplet<AtomName>(AtomName.CB, AtomName.CG, AtomName.CD, AtomName.OE1);
    public static final Quadruplet<AtomName> LYSINE_ATOMS = new Quadruplet<AtomName>(AtomName.CB, AtomName.CG, AtomName.CD, AtomName.CE);
    public static final Quadruplet<AtomName> METHIONINE_ATOMS = new Quadruplet<AtomName>(AtomName.CB, AtomName.CG, AtomName.SD, AtomName.CE);

    private static final Map<Quadruplet<AtomName>, Chi3> INSTANCE_CACHE = new HashMap<Quadruplet<AtomName>, Chi3>();

    public static Chi3 getInstance(Quadruplet<AtomName> atoms) {
        if (!Chi3.INSTANCE_CACHE.containsKey(atoms)) {
            Chi3.INSTANCE_CACHE.put(atoms, new Chi3(atoms));
        }
        return Chi3.INSTANCE_CACHE.get(atoms);
    }

    private Chi3(Quadruplet<AtomName> atoms) {
        super(MoleculeType.PROTEIN, Unicode.CHI3, atoms, new Quadruplet<Integer>(0, 0, 0, 0));
    }

    public static Chi3[] getInstances() {
        List<Chi3> instances = new ArrayList<Chi3>();
        instances.add(Chi3.getInstance(Chi3.ARGININE_ATOMS));
        instances.add(Chi3.getInstance(Chi3.GLUTAMIC_ACID_ATOMS));
        instances.add(Chi3.getInstance(Chi3.GLUTAMINE_ATOMS));
        instances.add(Chi3.getInstance(Chi3.LYSINE_ATOMS));
        instances.add(Chi3.getInstance(Chi3.METHIONINE_ATOMS));
        return instances.toArray(new Chi3[instances.size()]);
    }
}
