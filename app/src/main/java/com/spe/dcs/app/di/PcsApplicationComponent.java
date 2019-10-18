package com.spe.dcs.app.di;

import com.spe.dcs.app.PcsApplication;
import com.spe.dcs.app.net.NetModule;

import javax.inject.Singleton;

import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

/**

 */
@Singleton
@Component(modules = {
        AndroidSupportInjectionModule.class,
        ActivityBindingModule.class,
        FragmentBindingModule.class,
        PcsApplicationModule.class,
        NetModule.class,
        ViewModelBindingModule.class
})
public interface PcsApplicationComponent extends AndroidInjector<PcsApplication> {
    @Component.Builder
    abstract class Builder extends AndroidInjector.Builder<PcsApplication> {
        public abstract Builder dcsApplicationModule(PcsApplicationModule pcsApplicationModule);
    }
}
