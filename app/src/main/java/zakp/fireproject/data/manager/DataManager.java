package zakp.fireproject.data.manager;


import javax.inject.Inject;

import zakp.fireproject.data.local.PreferencesHelper;
import zakp.fireproject.helper.EventPosterHelper;


public class DataManager {

    protected final PreferencesHelper preferencesHelper;
    protected final EventPosterHelper eventPosterHelper;


    @Inject
    public DataManager(PreferencesHelper mPreferencesHelper, EventPosterHelper mEventPoster) {
        this.preferencesHelper = mPreferencesHelper;
        this.eventPosterHelper = mEventPoster;
    }
}
