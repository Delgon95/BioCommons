package pl.poznan.put.torsion;

import org.apache.commons.math3.geometry.euclidean.threed.Vector3D;
import org.apache.commons.math3.util.FastMath;

import pl.poznan.put.circular.Angle;
import pl.poznan.put.circular.exception.InvalidCircularValueException;
import pl.poznan.put.pdb.PdbAtomLine;
import pl.poznan.put.types.Quadruplet;

/**
 * A class to calculate and manage dihedral angles for given BioJava structure.
 *
 * @author Tomasz Zok (tzok[at]cs.put.poznan.pl)
 */
public final class TorsionAnglesHelper {
    /**
     * Calculate one dihedral angle value. By default use the atan method.
     *
     * @param atoms
     *            A 4-tuple of atoms.
     * @return Value of the tosion angle.
     * @throws InvalidCircularValueException
     */
    public static Angle calculateTorsionAngle(Quadruplet<PdbAtomLine> atoms) throws InvalidCircularValueException {
        return TorsionAnglesHelper.calculateTorsionAngle(atoms.a, atoms.b, atoms.c, atoms.d);
    }

    /**
     * Calculate one dihedral angle value. By default use the atan method.
     *
     * @param a1
     *            Atom 1.
     * @param a2
     *            Atom 2.
     * @param a3
     *            Atom 3.
     * @param a4
     *            Atom 4.
     * @return Value of the torsion angle.
     * @throws InvalidCircularValueException
     */
    public static Angle calculateTorsionAngle(PdbAtomLine a1, PdbAtomLine a2,
            PdbAtomLine a3, PdbAtomLine a4) throws InvalidCircularValueException {
        return TorsionAnglesHelper.calculateTorsionAtan(a1, a2, a3, a4);
    }

    /**
     * Calculate one dihedral angle value for given four atoms. Use cos^-1 and a
     * check for pseudovector
     *
     * @param a1
     *            Atom 1.
     * @param a2
     *            Atom 2.
     * @param a3
     *            Atom 3.
     * @param a4
     *            Atom 4.
     * @return Dihedral angle between atoms 1-4.
     * @throws InvalidCircularValueException
     */
    public static Angle calculateTorsionAcos(PdbAtomLine a1, PdbAtomLine a2,
            PdbAtomLine a3, PdbAtomLine a4) throws InvalidCircularValueException {
        if (a1 == null || a2 == null || a3 == null || a4 == null) {
            return Angle.invalidInstance();
        }

        Vector3D d1 = TorsionAnglesHelper.atomDistance(a1, a2);
        Vector3D d2 = TorsionAnglesHelper.atomDistance(a2, a3);
        Vector3D d3 = TorsionAnglesHelper.atomDistance(a3, a4);

        Vector3D u1 = d1.crossProduct(d2);
        Vector3D u2 = d2.crossProduct(d3);

        double ctor = u1.dotProduct(u2) / FastMath.sqrt(u1.dotProduct(u1) * u2.dotProduct(u2));
        ctor = ctor < -1 ? -1 : ctor > 1 ? 1 : ctor;
        double torp = Math.acos(ctor);
        if (u1.dotProduct(u2.crossProduct(d2)) < 0) {
            torp = -torp;
        }
        return new Angle(torp);
    }

    /**
     * Calculate one dihedral angle value for given four atoms.
     *
     * @param a1
     *            Atom 1.
     * @param a2
     *            Atom 2.
     * @param a3
     *            Atom 3.
     * @param a4
     *            Atom 4.
     * @return Dihedral angle between atoms 1-4.
     * @throws InvalidCircularValueException
     */
    public static Angle calculateTorsionAtan(PdbAtomLine a1, PdbAtomLine a2,
            PdbAtomLine a3, PdbAtomLine a4) throws InvalidCircularValueException {
        if (a1 == null || a2 == null || a3 == null || a4 == null) {
            return Angle.invalidInstance();
        }

        Vector3D v1 = TorsionAnglesHelper.atomDistance(a1, a2);
        Vector3D v2 = TorsionAnglesHelper.atomDistance(a2, a3);
        Vector3D v3 = TorsionAnglesHelper.atomDistance(a3, a4);

        Vector3D tmp1 = v1.crossProduct(v2);
        Vector3D tmp2 = v2.crossProduct(v3);
        Vector3D tmp3 = v1.scalarMultiply(v2.getNorm());
        return new Angle(FastMath.atan2(tmp3.dotProduct(tmp2), tmp1.dotProduct(tmp2)));
    }

    public static double subtractTorsions(double a1, double a2) {
        if (Double.isNaN(a1) || Double.isNaN(a2)) {
            return Double.NaN;
        }

        double full = 2 * Math.PI;
        double a1Mod = (a1 + full) % full;
        double a2Mod = (a2 + full) % full;
        double diff = Math.abs(a1Mod - a2Mod);
        diff = Math.min(diff, full - diff);
        return diff;
    }

    public static Vector3D atomDistance(PdbAtomLine a, PdbAtomLine b) {
        Vector3D va = new Vector3D(a.getX(), a.getY(), a.getZ());
        Vector3D vb = new Vector3D(b.getX(), b.getY(), b.getZ());
        return vb.subtract(va);
    }

    private TorsionAnglesHelper() {
    }
}
