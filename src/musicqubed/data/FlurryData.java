package musicqubed.data;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ars on 11/27/14.
 */
public class FlurryData {

    public enum Tag {
        Flurry,
        Device,
        Events,
        Event
    }

    public enum Event {
        FL_FACEBOOK_STARTED,
        FL_FACEBOOK_SUCCESS,
        FL_FACEBOOK_FAILED,

        FL_GOOGLE_PLUS_STARTED,
        FL_GOOGLE_PLUS_SUCCESS,
        FL_GOOGLE_PLUS_FAILED
    }

    public enum Parameter {
        title,
        username,
        time_stamp,
        connectivity,
        error
    }

    public enum ErrorMessage {
        CANCELLED,
        NOT_COMPLETED
    }

}
