package com.timia2109.cc_creditfile_creator;

/**
 * Wrapper for the Username. Better for sorting
 */
public class Constributor implements Comparable<Constributor> {

    private String name;

    public Constributor(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public int compareTo(Constributor o) {
        return name.compareToIgnoreCase( o.getName() );
    }

    @Override
    public String toString() {
        return getName();
    }
}
