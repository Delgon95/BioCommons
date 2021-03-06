package pl.poznan.put.rna.torsion;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import pl.poznan.put.pdb.analysis.MoleculeType;
import pl.poznan.put.torsion.AverageTorsionAngleType;
import pl.poznan.put.torsion.MasterTorsionAngleType;
import pl.poznan.put.torsion.TorsionAngleType;

public enum RNATorsionAngleType implements MasterTorsionAngleType {
    ALPHA(Alpha.getInstance()),
    BETA(Beta.getInstance()),
    GAMMA(Gamma.getInstance()),
    DELTA(Delta.getInstance()),
    EPSILON(Epsilon.getInstance()),
    ZETA(Zeta.getInstance()),
    NU0(Nu0.getInstance()),
    NU1(Nu1.getInstance()),
    NU2(Nu2.getInstance()),
    NU3(Nu3.getInstance()),
    NU4(Nu4.getInstance()),
    ETA(Eta.getInstance()),
    THETA(Theta.getInstance()),
    ETA_PRIM(EtaPrim.getInstance()),
    THETA_PRIM(ThetaPrim.getInstance()),
    CHI(Chi.getPurineInstance(), Chi.getPyrimidineInstance()),
    PSEUDOPHASE_PUCKER(PseudophasePuckerType.getInstance());

    private final List<TorsionAngleType> angleTypes;

    RNATorsionAngleType(TorsionAngleType... angleTypes) {
        this.angleTypes = Arrays.asList(angleTypes);
    }

    @Override
    public Collection<? extends TorsionAngleType> getAngleTypes() {
        return angleTypes;
    }

    private static final MasterTorsionAngleType[] MAIN = new MasterTorsionAngleType[] { ALPHA, BETA, GAMMA, DELTA, EPSILON, ZETA, CHI, PSEUDOPHASE_PUCKER };
    private static final AverageTorsionAngleType AVERAGE_TORSION_INSTANCE = new AverageTorsionAngleType(MoleculeType.RNA, RNATorsionAngleType.MAIN);

    public static MasterTorsionAngleType[] mainAngles() {
        return RNATorsionAngleType.MAIN;
    }

    public static AverageTorsionAngleType getAverageOverMainAngles() {
        return RNATorsionAngleType.AVERAGE_TORSION_INSTANCE;
    }

    @Override
    public String getLongDisplayName() {
        assert angleTypes.size() > 0;
        return angleTypes.get(0).getLongDisplayName();
    }

    @Override
    public String getShortDisplayName() {
        assert angleTypes.size() > 0;
        return angleTypes.get(0).getShortDisplayName();
    }

    @Override
    public String getExportName() {
        assert angleTypes.size() > 0;
        return angleTypes.get(0).getExportName();
    }
}
