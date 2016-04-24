package zakp.fireproject.ui.base;


import android.app.Activity;
import android.support.v4.app.Fragment;

import javax.inject.Inject;

/**
 * A simple {@link Fragment} subclass.
 */
public class BaseFragment extends Fragment {

    @Inject
    protected Activity activity;

    public BaseFragment() {
        // Required empty public constructor
    }

}
