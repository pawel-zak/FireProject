package zakp.fireproject.app;

import android.app.Application;
import android.content.Context;

import com.firebase.client.Firebase;

import timber.log.Timber;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import zakp.fireproject.BuildConfig;
import zakp.fireproject.R;
import zakp.fireproject.injection.component.ApplicationComponent;
import zakp.fireproject.injection.component.DaggerApplicationComponent;
import zakp.fireproject.injection.module.ApplicationModule;


public class App extends Application {

    ApplicationComponent applicationComponent;


    @Override
    public void onCreate() {
        super.onCreate();

        Firebase.setAndroidContext(this);
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        } else {
            //TODO:
            //implement production tree with crashes logging.
        }


        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/gotham-light.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build());

    }

    public static App get(Context context) {
        return ((App) context.getApplicationContext());
    }

    public ApplicationComponent getApplicationComponent() {
        if (applicationComponent == null) {

            applicationComponent = DaggerApplicationComponent.builder().applicationModule(new ApplicationModule(this))
                    .build();
        }
        return applicationComponent;
    }
}
