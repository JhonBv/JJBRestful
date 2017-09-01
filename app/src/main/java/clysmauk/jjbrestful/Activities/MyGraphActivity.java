package clysmauk.jjbrestful.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import clysmauk.jjbrestful.Models.ProductModel;
import clysmauk.jjbrestful.R;

public class MyGraphActivity extends AppCompatActivity {

    //jb UI components.
    private ListView productListView;
    private ListView sellerListView;

    private ArrayList productArray;
    private ArrayList productNameArray;
    private ArrayList productSellerNameArray;

    private ListAdapter productListAdapter;
    private ListAdapter sellerListAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_graph);

        //JB Initialize UI components
        productListView = (ListView) findViewById(R.id.productListView);
        sellerListView = (ListView) findViewById(R.id.sellerListView);

        //jb Initialize Product array.
        productArray = new ArrayList();

        // Initialize an array to put product names which we are going to show in the ListView
        productNameArray = new ArrayList();

        // Initialize an array to put film names which we are going to show in the second ListView
        productSellerNameArray = new ArrayList();




        for(int i=0 ; i<4 ; i++ ){
            // Initialize a Film object in order to set it's attributes.
            // We have to create a Product object each time this loop traverse
            ProductModel product = new ProductModel();

            // Here we set the film name and star name attribute for a film in one loop
            product.setProductName("Product name " + i);
            product.setProductSeller("Sold by " + i);

            // Pass the ProductModel object to the array of Film objects
            productArray.add(product);

            // Add the product name to array of film products
            productNameArray.add(product.getProductName());

            // Add the seller name to array of product names
            productSellerNameArray.add(product.getSeller());
        }//end For loop()



        // Plug the data set (productNameArray) to the adapter
        productListAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, productNameArray);

        // Plug the adapter with the UI component (productListView)
        productListView.setAdapter(productListAdapter);

        // Plug the data set (sellerNameArray) to the adapter
        sellerListAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, productSellerNameArray);

        // Plug the adapter with the UI component (sellerListView)
        sellerListView.setAdapter(sellerListAdapter);

    }//end of onCreate()

}//end Class
