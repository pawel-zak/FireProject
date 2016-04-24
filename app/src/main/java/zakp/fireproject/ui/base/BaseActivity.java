package zakp.fireproject.ui.base;

import android.support.v7.app.AppCompatActivity;

import zakp.fireproject.app.App;
import zakp.fireproject.injection.component.ActivityComponent;
import zakp.fireproject.injection.component.DaggerActivityComponent;
import zakp.fireproject.injection.module.ActivityModule;


public class BaseActivity extends AppCompatActivity {

    private ActivityComponent activityComponent;

    public ActivityComponent getActivityComponent() {
        if (activityComponent == null) {
            activityComponent = DaggerActivityComponent.builder()
                    .activityModule(new ActivityModule(this))
                    
                    .applicationComponent(App.get(this).getApplicationComponent())
                    .build();
        }

        return activityComponent;
    }

}
