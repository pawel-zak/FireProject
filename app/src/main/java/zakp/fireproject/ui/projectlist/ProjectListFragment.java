package zakp.fireproject.ui.projectlist;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import javax.inject.Inject;

import zakp.fireproject.R;
import zakp.fireproject.helper.EventPosterHelper;
import zakp.fireproject.helper.FragmentHelper;
import zakp.fireproject.ui.task.TaskFragment;

/**
 * Created by Paweł Żak on 20.04.2016.
 */
public class ProjectListFragment extends Fragment implements ProjectListMvpView {


    @Inject
    EventPosterHelper eventPosterHelper;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        FragmentHelper.inject(getActivity(), this);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_project_list, container, false);

        eventPosterHelper.getBus().post(new TabEvent("Projekty"));
        Button button = (Button) view.findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentHelper.replaceFragment(getActivity(), new TaskFragment(), true);
            }
        });


        return view;


    }


    public class TabEvent {
        String title;

        public TabEvent(String title) {
            this.title = title;
        }

        public String getTitle() {
            return title;
        }
    }
}
