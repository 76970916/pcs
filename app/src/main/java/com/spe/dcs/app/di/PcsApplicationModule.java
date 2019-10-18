package com.spe.dcs.app.di;

import android.content.Context;

import com.spe.dcs.app.db.PcsDatabase;
import com.spe.dcs.app.PcsSharedPreferences;
import com.spe.dcs.app.db.SysDatabase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**

 */
@Module
public class PcsApplicationModule {
    private Context context;

    public PcsApplicationModule(Context context) {
        this.context = context;
    }

    // Dagger will only look for methods annotated with @Provides
    @Provides

    public Context provideContext() {
        return context;
    }

    @Singleton
    @Provides
    PcsSharedPreferences provideDcsSharedPreferences(Context context) {
        return new PcsSharedPreferences(context);
    }

    @Singleton
    @Provides
    PcsDatabase provideDatabase(Context context) {
        return PcsDatabase.getDatabase(context);
    }

    @Singleton
    @Provides
    SysDatabase provideSysDatabase(Context context) {
        return SysDatabase.getSysDatabase(context);
    }
}
