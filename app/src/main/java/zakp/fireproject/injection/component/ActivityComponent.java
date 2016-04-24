package zakp.fireproject.injection.component;


import dagger.Component;
import zakp.fireproject.injection.PerActivity;
import zakp.fireproject.injection.module.ActivityModule;
import zakp.fireproject.ui.login.LogInActivity;
import zakp.fireproject.ui.main.MainActivity;
import zakp.fireproject.ui.projectlist.ProjectListFragment;
import zakp.fireproject.ui.task.TaskFragment;

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(MainActivity activity);


    void inject(LogInActivity activity);


    void inject(TaskFragment fragment);

    void inject(ProjectListFragment fragment);
    //use this if adding new Fragment or Activity
    //void inject(SomeActivity someActivity);
    //void inject(SomeFragment someFragment);

}
