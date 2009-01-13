package pulsarhunter.jreaper;

public enum HarmonicType {

    None(0), Principal(10), Integer(8), SimpleNonInteger(6), ComplexNonInteger(4);
    private final int rank;

    private HarmonicType(int rank) {
        this.rank = rank;
    }

    /**
     * Returns the rank of the HarmonicType as an integer, only use this to compare HarmonicTypes.
     * The ranking is as follows:
     * None &lt; ComplexNonInteger &lt; SimpleNonInteger &lt; Integer &lt; Principal
     * @return The rank of this HarmonicType.
     */
    public int getRank() {
        return rank;
    }
}
