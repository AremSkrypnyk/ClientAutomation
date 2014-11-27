package musicqubed.data;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ars on 11/27/14.
 */
public class FlurryData {

    public enum Tags {
        Flurry,
        Device,
        Events,
        Event
    }

    public enum Events {
        FL_FACEBOOK_STARTED,
        FL_FACEBOOK_SUCCESS
    }

    public enum Parameters {
        title,
        username,
        time_stamp,
        connectivity
    }

}
