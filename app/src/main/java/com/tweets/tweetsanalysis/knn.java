package com.tweets.tweetsanalysis;

public class knn{

    public static double euclidian(double x[], double[] y){

        double dist=0, diff;
        for(int i=0;i<x.length;i++){
            diff = (x[i]-y[i]);
            dist += diff*diff*(1000-i);
        }

        dist = Math.sqrt(dist);

        return dist;
    }

    public static int main(double[][] d,double[] test){

        double d0=0, d1=0;
            d0 = euclidian(d[0],test);
            d1 = euclidian(d[1],test);
        if(d0<d1)
            return 0;
        else
            return 1;
    }
}
