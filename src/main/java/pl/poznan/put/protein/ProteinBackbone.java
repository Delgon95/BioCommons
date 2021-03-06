package pl.poznan.put.protein;

import java.util.Arrays;

import pl.poznan.put.atom.AtomName;
import pl.poznan.put.pdb.analysis.MoleculeType;
import pl.poznan.put.pdb.analysis.ResidueComponent;

public class ProteinBackbone extends ResidueComponent {
    private static final ProteinBackbone INSTANCE = new ProteinBackbone();

    public static ProteinBackbone getInstance() {
        return ProteinBackbone.INSTANCE;
    }

    private ProteinBackbone() {
        super("backbone", MoleculeType.PROTEIN, Arrays.asList(AtomName.N, AtomName.HN, AtomName.CA, AtomName.HA, AtomName.C, AtomName.O));
    }
}
