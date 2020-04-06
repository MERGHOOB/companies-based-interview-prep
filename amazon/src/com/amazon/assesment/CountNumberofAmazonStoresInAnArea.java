// IMPORT LIBRARY PACKAGES NEEDED BY YOUR PROGRAM
// SOME CLASSES WITHIN A PACKAGE MAY BE RESTRICTED
// DEFINE ANY CLASS AND METHOD NEEDED
package com.amazon.assesment;
import java.util.ArrayList;
import java.util.List;
// CLASS BEGINS, THIS CLASS IS REQUIRED
public class CountNumberofAmazonStoresInAnArea
{
    // METHOD SIGNATURE BEGINS, THIS METHOD IS REQUIRED
    int numberAmazonGoStores(int rows, int columns, List<List<Integer> > grid)
    {

        if(rows <= 0) {
            return 0;
        }

        if(columns <=0) {
            return 0;
        }

        int clusters  = 0;

        for(int i =0 ; i<rows; i++) {

            for(int j=0; j<columns; j++) {
                if(grid.get(i).get(j) != 1) {
                    continue;
                }
                clusters ++;
//                grid.get(i).remove(j);
                grid.get(i).set(j,2);

                travelNeighbours(grid, i, j);


            }
        }

        return clusters;
        // WRITE YOUR CODE HERE
    }

    void  travelNeighbours(List<List<Integer>>grid, int i, int j) {

        travelNeighbour(grid, i+1, j);
        travelNeighbour(grid, i-1, j);
        travelNeighbour(grid, i, j-1);
        travelNeighbour(grid, i, j+1);


    }

    void travelNeighbour(List<List<Integer>>grid, int i, int j) {

        if((i < 0) ||
                (i >= grid.size()) ||
                (j<0) ||
                (j>=grid.get(i).size())
        ) {
            return;
        }

        if(grid.get(i).get(j) != 1) {
            return;
        }

        grid.get(i).set(j, 2);

        travelNeighbours(grid, i,j);

    }

    public static void main(String[] args) {

        List<List<Integer>> grid = new ArrayList<>();

        List<Integer> element = new ArrayList<Integer>() {{
            add(1);

            add(1);

            add(0);


            add(0);
        }};

        grid.add(0, element);
        element = new ArrayList<Integer>() {{
            add(0);

            add(0);

            add(0);


            add(0);
        }};

        grid.add(1, element);        element = new ArrayList<Integer>() {{
            add(0);

            add(0);

            add(1);


            add(1);
        }};

        grid.add(2, element);
        element = new ArrayList<Integer>() {{
            add(0);

            add(0);

            add(0);


            add(0);
        }};
        grid.add(3, element);


        int i = new CountNumberofAmazonStoresInAnArea().numberAmazonGoStores(4, 4, grid);
        System.out.println(i);

    }


    // METHOD SIGNATURE ENDS
}