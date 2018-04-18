package com.company;

public class Bid {
    private String orientations;
    private Integer count;
    private Double price;
    Bid(){
        orientations = "B";
        count = 1000;
        price = 100.0;
    }
    Bid(String orientations, Integer count, Double price){
        setOrientations(orientations);
        setCount(count);
        setPrice(price);
    }

    public String getOrientations() {
        return orientations;
    }

    public void setOrientations(String orientations) {
        this.orientations = orientations;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        if (count>1000)
        this.count = 1000;
        else{
            if (count<1)
                this.count = 1;
            else
                this.count = count;
        }
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        if(price>100.0)
        this.price = 100.0;
        else {
            if (price<1.0)
                this.price = 1.0;
            else
                this.price = price;
        }
    }
}
