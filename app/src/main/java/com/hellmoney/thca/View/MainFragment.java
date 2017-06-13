package com.hellmoney.thca.View;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hellmoney.thca.R;
import com.hellmoney.thca.model.Request;

import java.util.List;

public class MainFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private RecyclerView recyclerView;
    private MainContentAdapter mMainContentAdapter;
    private LinearLayoutManager linearLayoutManager;
    private List<Request> mRequests;



    public MainFragment() {
    }

    public static MainFragment newInstance(String param1, String param2) {
        MainFragment fragment = new MainFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_main, container, false);

        //TODO 데이터를 mRequest에 담으면 끝

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        mMainContentAdapter = new MainContentAdapter(mRequests);
        linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(mMainContentAdapter);

        return view;
    }

    public void onButtonPressed(Uri uri) {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    private static class MainViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView mEstimateCount;
        private TextView mEstimateEndTime;
        private ImageView mLoanTypeImageView;
        private TextView mRequestAddress;
        private TextView mRequestAddressApt;
        private TextView mRequestAddressSize;
        private TextView mRequestAddressPrice;
        private TextView mRequestJobType;
        private TextView mOverDue;

        private Request mRequest;

        public MainViewHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.main_item, parent, false));

            mEstimateCount = (TextView) itemView.findViewById(R.id.estimateCount);
            mEstimateEndTime = (TextView) itemView.findViewById(R.id.endTime);
            mLoanTypeImageView = (ImageView) itemView.findViewById(R.id.loanTypeImageView);
            mRequestAddress = (TextView) itemView.findViewById(R.id.requestAddress);
            mRequestAddressApt = (TextView) itemView.findViewById(R.id.requestAddressApt);
            mRequestAddressSize = (TextView) itemView.findViewById(R.id.requestAddressSize);
            mRequestAddressPrice = (TextView) itemView.findViewById(R.id.requestPrice);
            mRequestJobType = (TextView) itemView.findViewById(R.id.requestJobType);
            mOverDue = (TextView) itemView.findViewById(R.id.OverDueRecord);
        }

        public void bindRequest(Request request) {

            mRequest = request;
            mEstimateCount.setText(mRequest.getCountEstimate());
            mEstimateEndTime.setText(mRequest.getEndTime());

            //TODO 사진넣어주세요용 만약 에이젼트사진 하면.

            mRequestAddress.setText(mRequest.getAddress());
            mRequestAddressApt.setText(mRequest.getApt());
            mRequestAddressSize.setText(mRequest.getSize());
            mRequestAddressPrice.setText(mRequest.getPrice());
            mRequestJobType.setText(mRequest.getJobType());
            mOverDue.setText(mRequest.getOverDue());
        }

        @Override
        public void onClick(View v) {
            //만약 내가 해당 리스트 아이템을 클릭했을 경우 발생하는 이벤트를 넣는 곳

        }
    }

    private class MainContentAdapter extends RecyclerView.Adapter<MainViewHolder> {

        private List<Request> mRequests;


        public MainContentAdapter(List<Request> requests) {
            this.mRequests = requests;
        }

        @Override
        public MainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new MainViewHolder(LayoutInflater.from(parent.getContext()), parent);
        }

        @Override
        public void onBindViewHolder(MainViewHolder holder, int position) {
            Request request = mRequests.get(position);

            //TODO 시간흐름에 따라 저장하는 방식으로 진행 할 것
            holder.bindRequest(request);
        }

        @Override
        public int getItemCount() {
            return mRequests.size();
        }
    }

}
