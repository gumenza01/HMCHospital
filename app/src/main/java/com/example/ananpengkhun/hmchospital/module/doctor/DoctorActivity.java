package com.example.ananpengkhun.hmchospital.module.doctor;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;

import com.example.ananpengkhun.hmchospital.R;
import com.example.ananpengkhun.hmchospital.common.HMCBaseActivity;
import com.example.ananpengkhun.hmchospital.constants.HMCconstants;
import com.google.firebase.messaging.FirebaseMessaging;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DoctorActivity extends HMCBaseActivity implements DoctorContractor.View {

    @BindView(R.id.content_container) FrameLayout contentContainer;
    private DoctorContractor.DoctorPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor);
        ButterKnife.bind(this);
        presenter = new DoctorPresenter(this, DoctorActivity.this);
        init();
    }

    private void init() {
        FirebaseMessaging.getInstance().subscribeToTopic(HMCconstants.TOPIC_DOCTOR);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(contentContainer.getId(),MainDoctorFragment.newInstant(),"MainDoctorFragment")
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void setPresenter(DoctorContractor.DoctorPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        FirebaseMessaging.getInstance().unsubscribeFromTopic(HMCconstants.TOPIC_DOCTOR);
    }
}
