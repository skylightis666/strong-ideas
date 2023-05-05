package org.strong.ideas;

import java.util.List;

public class Vertex {
    List<Edge> edges;

    public Vertex(List<Edge> e) {
        edges = e;
    }
    int getSize(){
        return edges.size();
    }
}