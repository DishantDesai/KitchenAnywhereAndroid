package com.kitchen_anywhere.kitchen_anywhere.foodie;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.google.android.gms.common.internal.service.Common;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.kitchen_anywhere.kitchen_anywhere.API.PostalApIController;
import com.kitchen_anywhere.kitchen_anywhere.API.PostalCodeController;
import com.kitchen_anywhere.kitchen_anywhere.Cart;
import com.kitchen_anywhere.kitchen_anywhere.DishDetails;
import com.kitchen_anywhere.kitchen_anywhere.R;
import com.kitchen_anywhere.kitchen_anywhere.adapter.FoodAdapter;
import com.kitchen_anywhere.kitchen_anywhere.chef.ChefOrderPage;
import com.kitchen_anywhere.kitchen_anywhere.helper.constant;
import com.kitchen_anywhere.kitchen_anywhere.interfaces.redirection;
import com.kitchen_anywhere.kitchen_anywhere.model.FoodModel;
import com.kitchen_anywhere.kitchen_anywhere.model.postalCodeModels.PostalCode;

import java.util.ArrayList;

public class FoodieHomePageFragment extends Fragment implements redirection {
    ImageSlider imageSlider;
    private FoodAdapter dishAdapter;
    private RecyclerView  recyclerViewDishList;
    private MaterialToolbar topAppBar;
    TextView badgeCounter,viewMore;
    MenuItem menuItem;
    private boolean isInitial=true;
    private RequestQueue queue;

    //    private NotificationBadge badge;
    public FoodieHomePageFragment() {
        // Required empty public constructor
    }

     ProgressBar progressBar;
    ListView d_list;
    FloatingActionButton addDish;
    public ListView listView ;

    public Handler h;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_foodie_home_page_fragment, container, false);
        progressBar = (ProgressBar)view.findViewById(R.id.foodie_loading);
        topAppBar = (MaterialToolbar)view.findViewById(R.id.topAppBar);
        viewMore = (TextView)view.findViewById(R.id.view_more);
        viewMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getActivity(), ViewMoreDishes.class);
                startActivity(intent);
//                getActivity().finish();
            }
        });
//        badge = (NotificationBadge)view.findViewById(R.id.badge);
//        topAppBar.setMenu();
        topAppBar.setOnMenuItemClickListener(new MaterialToolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch(item.getItemId()){
                    case R.id.action_cart:
                        // TODO
                        startActivity(new Intent(getActivity(), Cart.class));
                        break;
                    // TODO: Other cases
                }
                return true;
            }
        });

        imageSlider = view.findViewById(R.id.image_slider);
//        progressBar.setVisibility(View.INVISIBLE);
        ArrayList<SlideModel> images =  new ArrayList<>();
        images.add(new SlideModel("https://www.wingsworldcuisine.ie/wp-content/uploads/2022/02/W2_Offer-Early-Bird-Offer-01012022.jpg",null));
        images.add(new SlideModel("https://www.wingsworldcuisine.ie/wp-content/uploads/2021/11/Wings-dubling-student-offer_19112021.jpg",null));
        images.add(new SlideModel("https://www.french-waterways.com/waterpress/wp-content/imagez/Copy-of-FW_French-dishes-1000x563.png",null));
        imageSlider.setImageList(images, ScaleTypes.FIT);
        getDish(view);

        return view;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);//Make sure you have this line of code.
        queue = Volley.newRequestQueue(this.getContext());

    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {


        inflater.inflate(R.menu.top_app_bar_menu, menu);
        menuItem = menu.findItem(R.id.action_cart);


        // get the view from the nav item
        View view =  menuItem.setActionView(R.layout.notification_badge).getActionView();
        // get the text view of the action view for the nav item
        badgeCounter = view.findViewById(R.id.badge_counter);
        // set the pending notifications value
        badgeCounter.setText("12");
//        if (constant.cartItems.size() == 0) {
//            // if no pending notification remove badge
//            menuItem.setActionView(null);
//        }
//        else {
//            // if notification than set the badge icon layout
//            menuItem.setActionView(R.layout.notification_badge);
//            // get the view from the nav item
//            View view = menuItem.getActionView();
//            // get the text view of the action view for the nav item
//            badgeCounter = view.findViewById(R.id.badge_counter);
//            // set the pending notifications value
//            badgeCounter.setText(String.valueOf(constant.cartItems.size()));
//        }
        super.onCreateOptionsMenu(menu,inflater);
    }

    PostalApIController ctr;
    public void getDish(View view)
    {

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        recyclerViewDishList =  view.findViewById(R.id.dish_list_recycle_view);

        ArrayList<FoodModel> foodlist = new ArrayList<>();
            FirebaseFirestore.getInstance().collection("Dish")
                    .get().addOnCompleteListener(
                    new OnCompleteListener<QuerySnapshot>()
                    {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if(task.isSuccessful())
                            {

                                ArrayList<FoodModel> foodlist = new ArrayList<>();

                                for(QueryDocumentSnapshot doc : task.getResult())
                                {
                                    System.out.println("price"+ doc.getData().get("price").getClass());
                                    double mainPrice = Double.parseDouble(doc.getData().get("price").toString());
                                    double maxLimit = Double.parseDouble(doc.getData().get("maxLimit").toString());
                                    double pending_limit = Double.parseDouble(doc.getData().get("pending_limit").toString());
                                    int categoryId = Integer.parseInt(doc.getData().get("categoryId").toString());


                                    foodlist.add (new FoodModel( doc.getId().toString(), doc.getData().get("dishTitle").toString(),
                                            doc.getData().get("description").
                                                    toString(),doc.getData().get("typeOfDish").toString(),
                                            mainPrice,
                                            doc.getData().get("dishImageLink").toString(),5,
                                            doc.getData().get("chef_id").toString(),
                                            new ArrayList<>(),categoryId,
                                            maxLimit,pending_limit,true,true,
//                                            constant.CurrentUser.getPostal_code()
                                            doc.getData().get("postal_code").toString()
                                    ));

                                }
                                constant.alldishdata = foodlist;
                                recyclerViewDishList.setLayoutManager(linearLayoutManager);
                                ArrayList<FoodModel> homeScreenDishItems = new ArrayList<FoodModel>();
                                if(constant.alldishdata.size() > 5){
                                    for(int i = 0; i < 5; i++) {
                                        homeScreenDishItems.add(constant.alldishdata.get(i));
                                    }
                                }else{
                                    homeScreenDishItems = (ArrayList<FoodModel>) constant.alldishdata;
                                }
                                dishAdapter = new FoodAdapter(getActivity(), homeScreenDishItems);
                                recyclerViewDishList.setAdapter(dishAdapter);
                                progressBar.setVisibility(View.INVISIBLE);
                                recyclerViewDishList.setVisibility(View.VISIBLE);

                                String PCode = constant.CurrentUser.getPostal_code().substring(0,3)+"+"+constant.CurrentUser.getPostal_code().substring(3);
                                 ctr = new PostalApIController();
                                StringRequest stringRequest = ctr.searchPostalRequest(PCode,FoodieHomePageFragment.this);
                                stringRequest.setTag("postal");
                                queue.add(stringRequest);
                            }
                        }
                    }
            );


    }


    @Override
    public void onResume() {
        super.onResume();
        getActivity().invalidateOptionsMenu();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void callFucntion() {
        constant.Matchdishdata.clear();
        for (FoodModel fm: constant.alldishdata) {
            String FBPostal = fm.getpostal_code();
            FBPostal = FBPostal.substring(0,3)+" "+FBPostal.substring(3);
            System.out.println(FBPostal + "-------------------- " + ctr.postalList.size());
            for (PostalCode pc: ctr.postalList) {
                System.out.println(FBPostal + "  ================  " + pc.getPostalCode());
                if(FBPostal.equals(pc.getPostalCode()))
                {
                    System.out.println("Matches ----------------------------------------------------" + FBPostal);
                    constant.Matchdishdata.add(fm);
                }

            }

        }

        System.out.println("--------------------------------------------------------------------------------------------" + constant.Matchdishdata.size());

        if(constant.Matchdishdata.size() != 0)
        {
            dishAdapter.updateData(constant.Matchdishdata);
        }
//        dishAdapter.updateData(constant.Matchdishdata);
    }
}