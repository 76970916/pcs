package com.spe.dcs.login;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import com.spe.dcs.app.Resource;
import com.spe.dcs.app.net.RespEntity;

import javax.inject.Inject;

public class LoginViewModel extends ViewModel {
    private static final String TAG = "LoginViewModel";

    private MediatorLiveData<Resource<RespEntity>> loginLiveData = new MediatorLiveData<>();
    @Inject
    public LoginRepository loginRepository;

    @Inject
    public LoginViewModel() {
        Log.d(TAG, "LoginViewModel 被创建");
    }

    public LiveData<Resource<RespEntity>> getLoginLiveData() {
        return loginLiveData;
    }


    public LiveData<Resource<RespEntity>> login(String username, String password) {
        LiveData<Resource<RespEntity>> source = loginRepository.login(username, password);
        loginLiveData.addSource(source, resource -> {
            if (resource != null) {
                loginLiveData.setValue(source.getValue());
            } else {
                loginLiveData.setValue(Resource.error("查询出错！", null));
            }
        });
        return loginLiveData;
    }


}
