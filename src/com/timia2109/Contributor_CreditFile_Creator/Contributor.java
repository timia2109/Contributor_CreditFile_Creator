package com.timia2109.Contributor_CreditFile_Creator;

/**
 * Wrapper for the Username. Better for sorting
 */
public class Contributor implements Comparable<Contributor> {

    private String name;

    public Contributor(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public int compareTo(Contributor o) {
        return name.compareToIgnoreCase( o.getName() );
    }

    @Override
    public String toString() {
        return getName();
    }
}
