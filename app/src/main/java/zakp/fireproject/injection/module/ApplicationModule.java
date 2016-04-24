package zakp.fireproject.injection.module;

import android.app.Application;
import android.content.Context;

import com.squareup.otto.Bus;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import zakp.fireproject.injection.ApplicationContext;


@Module
public class ApplicationModule {

    protected final Application application;


    public ApplicationModule(Application application) {
        this.application = application;
    }

    @Provides
    Application provideApplication() {
        return application;
    }

    @Provides
    @ApplicationContext
    Context proviceContext() {
        return application;
    }

    @Provides
    @Singleton
    Bus provideEventBus() {
        return new Bus();
    }




}
