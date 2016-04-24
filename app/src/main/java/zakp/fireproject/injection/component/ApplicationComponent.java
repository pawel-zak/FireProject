package zakp.fireproject.injection.component;

import android.app.Application;
import android.content.Context;

import com.squareup.otto.Bus;

import javax.inject.Singleton;

import dagger.Component;
import zakp.fireproject.data.local.PreferencesHelper;
import zakp.fireproject.helper.EventPosterHelper;
import zakp.fireproject.injection.ApplicationContext;
import zakp.fireproject.injection.module.ApplicationModule;


@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    @ApplicationContext
    Context context();

    Application application();

    PreferencesHelper preferencesHelper();

    Bus eventBus();

    EventPosterHelper eventPosterHelper();


}
