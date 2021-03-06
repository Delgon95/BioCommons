package pl.poznan.put.rna;

import java.util.List;

import pl.poznan.put.atom.AtomName;
import pl.poznan.put.pdb.analysis.MoleculeType;
import pl.poznan.put.pdb.analysis.ResidueComponent;

public abstract class NucleicAcidResidueComponent extends ResidueComponent {
    private final RNAResidueComponentType type;

    protected NucleicAcidResidueComponent(RNAResidueComponentType type, List<AtomName> atoms, List<AtomName> additionalAtoms) {
        super(type.name().toLowerCase(), MoleculeType.RNA, atoms, additionalAtoms);
        this.type = type;
    }

    protected NucleicAcidResidueComponent(RNAResidueComponentType type, List<AtomName> atoms) {
        super(type.name().toLowerCase(), MoleculeType.RNA, atoms);
        this.type = type;
    }

    public RNAResidueComponentType getType() {
        return type;
    }
}
