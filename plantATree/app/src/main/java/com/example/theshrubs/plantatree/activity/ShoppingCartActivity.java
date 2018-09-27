package com.example.theshrubs.plantatree.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.theshrubs.plantatree.R;
import com.example.theshrubs.plantatree.database.DatabaseHelper;
import com.example.theshrubs.plantatree.models.ShoppingCart;
import com.example.theshrubs.plantatree.models.Tree;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCartActivity extends AppCompatActivity {

    private List<ShoppingCart> cartObjectList = new ArrayList<>();
    private int USER_ID;
    private DatabaseHelper database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shopping_cart);
        this.database = new DatabaseHelper(this);

        if(savedInstanceState == null){
            Bundle extras = getIntent().getExtras();
            if(extras == null){
                USER_ID = 1;
                System.out.println("Bundle extra was NULL user");
            }else{
                USER_ID = extras.getInt("CART_ID");
            }
        }else{
            USER_ID = (Integer) savedInstanceState.getSerializable("CART_ID");
            System.out.println("savedInstance was NULL");
        }

        List<Object> objectList = new ArrayList<>();
        objectList = database.loadAllContents(USER_ID, "ShoppingCart");
        ShoppingCart cartObject = new ShoppingCart();
        for(int i = 0; i < objectList.size(); i++){
            cartObject = (ShoppingCart) objectList.get(i);

//            loading car
            System.out.println("ADDING " + cartObject.toString() + "to cart array");
            cartObjectList.add(cartObject);
        }

        final ListView listView = (ListView) findViewById(R.id.productListView);
        listView.setAdapter(new ShoppingCartAdapter(this, cartObjectList, USER_ID));//, database));

    }


}