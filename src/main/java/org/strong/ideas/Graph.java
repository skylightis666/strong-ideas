package org.strong.ideas;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class Graph {
    LinkedList<Vertex> vertices = new LinkedList<>();

    public Graph() {
        Set<Character> intSymbols = Set.of('0', '1');
        vertices.add(new Vertex(List.of(new Edge(1, 2, intSymbols))));
        vertices.add(new Vertex(List.of(new Edge(2, 2, intSymbols))));
        vertices.add(new Vertex(List.of(new Edge(3, 4, intSymbols))));
        vertices.add(new Vertex(List.of(new Edge(4, 4, intSymbols))));

        Set<Character> dot = Set.of('.', '-');
        vertices.add(new Vertex(List.of(new Edge(2, 3, dot))));
    }

    public Integer getSize() {
        return vertices.size();
    }
}