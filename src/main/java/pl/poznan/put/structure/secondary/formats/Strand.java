package pl.poznan.put.structure.secondary.formats;

import java.io.Serializable;
import java.util.List;

import pl.poznan.put.structure.secondary.DotBracketSymbol;

public class Strand implements Serializable {
    private final DotBracket parent;
    private final String name;
    private final int from;
    private final int to;

    public Strand(DotBracket parent, String name, int from, int to) {
        super();
        this.parent = parent;
        this.name = name;
        this.from = from;
        this.to = to;

        assert to >= from;
    }

    @Override
    public String toString() {
        return ">strand_" + name + "\n" + getSequence() + "\n" + getStructure();
    }

    public String getName() {
        return name;
    }

    public int getFrom() {
        return from;
    }

    public int getTo() {
        return to;
    }

    public int getLength() {
        return to - from;
    }

    public String getSequence() {
        return parent.getSequence().substring(from, to);
    }

    public String getRSequence() {
        char[] cs = getSequence().toCharArray();
        for (int i = 0; i < cs.length; i++) {
            cs[i] = cs[i] == 'A' || cs[i] == 'G' ? 'R' : 'Y';
        }
        return new String(cs);
    }

    public String getStructure() {
        return parent.getStructure().substring(from, to);
    }

    public List<DotBracketSymbol> getSymbols() {
        return parent.getSymbols().subList(from, to);
    }

    public TerminalMissing getMissingBegin() {
        int i = from;
        for (; i < to; i++) {
            DotBracketSymbol symbol = parent.getSymbol(i);
            if (!symbol.isMissing()) {
                break;
            }
        }
        return new TerminalMissing(parent.getSymbols().subList(from, i));
    }

    public TerminalMissing getMissingEnd() {
        int i = to - 1;
        for (; i >= from; i--) {
            DotBracketSymbol symbol = parent.getSymbol(i);
            if (!symbol.isMissing()) {
                break;
            }
        }
        return new TerminalMissing(parent.getSymbols().subList(i + 1, to));
    }

    public int getPseudoknotOrder() {
        int order = 0;
        for (DotBracketSymbol symbol : getSymbols()) {
            order = Math.max(order, symbol.getOrder());
        }
        return order;
    }

    public boolean contains(DotBracketSymbol symbol) {
        return getSymbols().contains(symbol);
    }

    /**
     * Check if this strand is "single strand" which means that it does not have
     * any base-pair embedded inside its structure.
     *
     * @return True if there is no base-pair inside of this strand. An opening
     *         or closing bracket is allowed as long as it points somewhere
     *         outside this strand.
     */
    public boolean isSingleStrand() {
        List<DotBracketSymbol> symbols = getSymbols();

        for (int i = 1; i < symbols.size() - 1; i++) {
            DotBracketSymbol symbol = symbols.get(i);
            if (symbol.isPairing() && symbols.contains(symbol.getPair())) {
                return false;
            }
        }

        return true;
    }

    public boolean containsMissing() {
        for (DotBracketSymbol symbol : getSymbols()) {
            if (symbol.isMissing()) {
                return true;
            }
        }

        return false;
    }

    public boolean containsFully(Strand other) {
        return from <= other.from && to >= other.to;
    }

    public String getDescription() {
        return from + 1 + " " + to + " " + getSequence() + " " + getStructure() + " " + getRSequence();
    }
}