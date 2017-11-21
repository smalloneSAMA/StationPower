package com.demo.ssm.po;

public class Relationship_Centrality_BusinessNum {
    private Integer id;

    private double centrality;

    private double averageBusinessNum;

    public Integer getId() {
        return id;
    }

    public double getCentrality() {
        return centrality;
    }

    public double getAverageBusinessNum() {
        return averageBusinessNum;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setCentrality(double centrality) {
        this.centrality = centrality;
    }

    public void setAverageBusinessNum(double averageBusinessNum) {
        this.averageBusinessNum = averageBusinessNum;
    }
}
