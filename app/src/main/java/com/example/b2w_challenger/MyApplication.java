package com.example.b2w_challenger;

import android.app.Application;

import com.example.b2w_challenger.services.DaggerServiceComponent;
import com.example.b2w_challenger.services.ServiceComponent;
import com.example.b2w_challenger.services.ServiceModule;

public class MyApplication extends Application {
    private ServiceComponent serviceComponent;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public ServiceComponent getServiceComponent() {
        return serviceComponent;
    }

    public void setServiceComponent(String baseUrl) {
        serviceComponent = DaggerServiceComponent
                .builder().serviceModule(new ServiceModule(baseUrl)).build();
    }
}