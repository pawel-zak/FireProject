package zakp.fireproject.helper;

import android.os.Handler;
import android.os.Looper;

import com.squareup.otto.Bus;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Provides helper methods to post event to an Otto event bus
 */
@Singleton
public class EventPosterHelper {

    private final Bus bus;

    @Inject
    public EventPosterHelper(Bus bus) {
        this.bus = bus;
    }

    public Bus getBus() {
        return bus;
    }

    /**
     * Helper method to post an event from a different thread to the main one.
     */
    public void postEventSafely(final Object event) {
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                bus.post(event);
            }
        });
    }
}