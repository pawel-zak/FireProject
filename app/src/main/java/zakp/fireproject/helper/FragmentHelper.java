package zakp.fireproject.helper;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import java.lang.reflect.InvocationTargetException;

import timber.log.Timber;
import zakp.fireproject.R;
import zakp.fireproject.injection.component.ActivityComponent;
import zakp.fireproject.ui.base.BaseActivity;
import zakp.fireproject.ui.base.BaseFragment;


public class FragmentHelper {

    public static int FRAGMENT_MAIN_CONTAINER = R.id.main_container;

    public static void replaceFragment(Activity a, Fragment fr, boolean isBackStackAddable) {
        replaceFragment(a, FRAGMENT_MAIN_CONTAINER, fr, fr.getArguments(), isBackStackAddable, true);
    }

    public static void replaceFragment(Activity a, Fragment fr) {
        replaceFragment(a, FRAGMENT_MAIN_CONTAINER, fr, fr.getArguments(), false, true);
    }

    public static void replaceFragment(Activity a, Fragment fr, Bundle args, boolean isBackStackAddable) {
        replaceFragment(a, FRAGMENT_MAIN_CONTAINER, fr, args, isBackStackAddable, true);
    }

    public static void replaceFragment(Activity a, int frame, Fragment fr, boolean isBackStackAddable) {
        replaceFragment(a, frame, fr, fr.getArguments(), isBackStackAddable, true);
    }

    public static void replaceFragment(Activity a, int frame, Fragment fr, Bundle args, boolean isBackStackAddable, boolean shouldInject) {
        if (a != null && !a.isFinishing() && a instanceof AppCompatActivity && fr != null) {

            if (shouldInject && a instanceof BaseActivity && fr instanceof BaseFragment) {
                inject(a, fr);
            }

            FragmentManager fm = ((AppCompatActivity) a).getSupportFragmentManager();

            if (fm.findFragmentById(frame) != null) {
                if (fr.getClass().getName().equals(fm.findFragmentById(frame).getClass().getName())) {
                    return;
                }
            }
            if (args == null) {
                args = new Bundle();
            }
            fr.setArguments(args);
            FragmentTransaction tr = fm.beginTransaction();

            if (isBackStackAddable) {
                tr.addToBackStack(fr.getClass().getName());
            }
            tr.replace(frame, fr, fr.getClass().getName()).commitAllowingStateLoss();
        }
    }

    public static void inject(Activity a, Fragment fr) {
        try {
            ActivityComponent ac = ((BaseActivity) a).getActivityComponent();
            ac.getClass().getDeclaredMethod("inject", fr.getClass()).invoke(ac, fr); //hack for cannot resolve method error
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            Timber.e("Exception in Fragment Helper. Issue with inject method from Activity Component " + fr.getClass().getCanonicalName());
            e.printStackTrace();
        }
    }

    public static void popBackStack(Activity a, Fragment fragment) {
        FragmentManager fm = ((AppCompatActivity) a).getSupportFragmentManager();
        fm.popBackStackImmediate(fragment.getClass().getName(), FragmentManager.POP_BACK_STACK_INCLUSIVE);
    }
}