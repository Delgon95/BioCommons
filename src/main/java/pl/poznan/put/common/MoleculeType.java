package pl.poznan.put.common;

import java.util.Arrays;
import java.util.List;

import org.biojava.bio.structure.Chain;
import org.biojava.bio.structure.Group;

import pl.poznan.put.atoms.AtomName;
import pl.poznan.put.helper.Helper;
import pl.poznan.put.nucleic.RNABackboneAtoms;
import pl.poznan.put.nucleic.RNABondRule;
import pl.poznan.put.nucleic.RNATorsionAngle;
import pl.poznan.put.protein.ProteinBackboneAtoms;
import pl.poznan.put.protein.ProteinBondRule;
import pl.poznan.put.protein.ProteinTorsionAngle;

public enum MoleculeType {
    RNA(RNABackboneAtoms.getAtoms(), new RNABondRule(), RNATorsionAngle.values()),
    PROTEIN(ProteinBackboneAtoms.getAtoms(), new ProteinBondRule(), ProteinTorsionAngle.values()),
    UNKNOWN(null, null, null);

    private final List<AtomName> backboneAtoms;
    private final ResidueBondRule bondRule;
    private final TorsionAngle[] torsionAngles;

    private MoleculeType(List<AtomName> backboneAtoms,
            ResidueBondRule bondRule, TorsionAngle[] torsionAngles) {
        this.backboneAtoms = backboneAtoms;
        this.bondRule = bondRule;
        this.torsionAngles = torsionAngles;
    }

    public List<AtomName> getBackboneAtoms() {
        return backboneAtoms;
    }

    public boolean areConnected(Group g1, Group g2) {
        return bondRule.areConnected(g1, g2);
    }

    public List<TorsionAngle> getBackboneTorsionAngles() {
        return Arrays.asList(torsionAngles);
    }

    public static MoleculeType detect(Chain chain) {
        // decide upon first residue only!
        Group residue = chain.getAtomGroup(0);
        return detect(residue);
    }

    public static MoleculeType detect(Group residue) {
        int bestScore = 3; // at least three atoms must match!
        MoleculeType bestType = MoleculeType.UNKNOWN;

        for (MoleculeType type : MoleculeType.values()) {
            int score = 0;
            List<AtomName> listing = type.getBackboneAtoms();

            if (listing != null) {
                for (AtomName atomType : listing) {
                    if (Helper.findAtom(residue, atomType) != null) {
                        score++;
                    }
                }

                if (score > bestScore) {
                    bestScore = score;
                    bestType = type;
                }
            }
        }

        return bestType;
    }
}