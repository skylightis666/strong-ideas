package org.strong.ideas;

import java.util.Set;

public class Edge {
    private final int begin;
    private final int end;
    private final Set<Character> state;

    public Edge(int begin, int end, Set<Character> state) {
        this.begin = begin;
        this.end = end;
        this.state = state;
    }

    public Set<Character> getState() {
        return state;
    }

    public int getBegin() {
        return begin;
    }

    public int getEnd() {
        return end;
    }
}