//package com.example.audioacquisition.Home.fragment;
//
//
//import android.os.Bundle;
//
//import androidx.fragment.app.Fragment;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import com.example.audioacquisition.Home.adapter.DetailAdapter;
//import com.example.audioacquisition.Home.adapter.ThirdAdapter;
//import com.example.audioacquisition.Home.bean.DetailBean;
//import com.example.audioacquisition.Home.bean.ThirdBean;
//import com.example.audioacquisition.R;
//
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * A simple {@link Fragment} subclass.
// */
//public class ThirdFragment extends Fragment {
//
//    private RecyclerView third_rv1;
//    private RecyclerView third_rv2;
//    private RecyclerView third_rv3;
//    private RecyclerView third_rv4;
//    private TextView title1;
//    private TextView title2;
//    private TextView title3;
//    private TextView title4;
//    private ImageView titleimage1;
//    private ImageView titleimage2;
//    private ImageView titleimage3;
//    private ImageView titleimage4;
//
//    //在fragment内部写一个静态方法,返回自己;供外部调用;
//    public static Fragment getInstance(String title) {
//        HorizontalFragment mFragment = new HorizontalFragment();
//        Bundle bundle = new Bundle();
//        bundle.putString("title", title);
//        mFragment.setArguments(bundle);
//        return mFragment;
//    }
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//
//        View view = inflater.inflate(R.layout.fragment_third, container, false);
//
//        third_rv1 = (RecyclerView) view.findViewById(R.id.third_first_rv1);
//        third_rv2 = (RecyclerView) view.findViewById(R.id.third_second_rv2);
//        third_rv3 = (RecyclerView) view.findViewById(R.id.third_third_rv3);
//        third_rv4 = (RecyclerView) view.findViewById(R.id.third_fourth_rv4);
//        title1 = (TextView) view.findViewById(R.id.third_first_name);
//        title2 = (TextView) view.findViewById(R.id.third_second_name);
//        title3 = (TextView) view.findViewById(R.id.third_third_name);
//        title4 = (TextView) view.findViewById(R.id.third_fourth_name);
//        titleimage1 = (ImageView) view.findViewById(R.id.third_first_image);
//        titleimage2 = (ImageView) view.findViewById(R.id.third_second_image);
//        titleimage3 = (ImageView) view.findViewById(R.id.third_third_image);
//        titleimage4 = (ImageView) view.findViewById(R.id.third_fourth_image);
//
//
//        initview1();
//        initview2();
//        initview3();
//        initview4();
//        return view;
//    }
//
//
//    public void initview1() {
//        List<ThirdBean> thirdBeanList1 = new ArrayList<>();
//        ThirdBean thirdBean1 = new ThirdBean();
//        thirdBeanList1.add(thirdBean1);
//
//        ThirdAdapter thirdAdapter1 = new ThirdAdapter(thirdBeanList1);
//        third_rv1.setAdapter(thirdAdapter1); //dialog.show
//        LinearLayoutManager layoutManager1 = new LinearLayoutManager(getContext());
//        third_rv1.setLayoutManager(layoutManager1);
//
//    }
//    public void initview2() {
//        List<ThirdBean> thirdBeanList2 = new ArrayList<>();
//        ThirdBean thirdBean2 = new ThirdBean();
//        thirdBeanList2.add(thirdBean2);
//
//        ThirdAdapter thirdAdapter2 = new ThirdAdapter(thirdBeanList2);
//        third_rv2.setAdapter(thirdAdapter2); //dialog.show
//        LinearLayoutManager layoutManager2 = new LinearLayoutManager(getContext());
//        third_rv2.setLayoutManager(layoutManager2);
//
//    }
//
//    public void initview3() {
//        List<ThirdBean> thirdBeanList3 = new ArrayList<>();
//        ThirdBean thirdBean3 = new ThirdBean();
//        thirdBeanList3.add(thirdBean3);
//
//        ThirdAdapter thirdAdapter3 = new ThirdAdapter(thirdBeanList3);
//        third_rv3.setAdapter(thirdAdapter3); //dialog.show
//        LinearLayoutManager layoutManager3 = new LinearLayoutManager(getContext());
//        third_rv3.setLayoutManager(layoutManager3);
//
//    }
//    public void initview4() {
//        List<ThirdBean> thirdBeanList4 = new ArrayList<>();
//        ThirdBean thirdBean4 = new ThirdBean();
//        thirdBeanList4.add(thirdBean4);
//
//        ThirdAdapter thirdAdapter4 = new ThirdAdapter(thirdBeanList4);
//        third_rv4.setAdapter(thirdAdapter4); //dialog.show
//        LinearLayoutManager layoutManager4 = new LinearLayoutManager(getContext());
//        third_rv4.setLayoutManager(layoutManager4);
//
//    }
//
//
//
//}
