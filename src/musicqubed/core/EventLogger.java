package musicqubed.core;

import musicqubed.data.FlurryData;
import musicqubed.data.XMLBuilder;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by ars on 11/27/14.
 */
public class EventLogger {

    private static final String FLURRY_DATE_FORMAT = "YYYY-mm-dd_HH:mm:ss";
    private static final String REPORTS_DATE_FORMAT = "YYYY-mm-dd__HH-mm";
    private static String pathToUsageReports = "usage/";

    private static int eventCounter;
    private static XMLBuilder flurrySession;

    public static void init(){
        flurrySession = new XMLBuilder(FlurryData.Tags.Flurry);
        eventCounter = 1;
        logDevice();
        createEventsSection();
    }

    private static void logDevice(){
        flurrySession
                .createParentElement(FlurryData.Tags.Device)
                .createChildElement(FlurryData.Parameters.title, System.getProperty("test.device"))
                .createChildElement(FlurryData.Parameters.time_stamp, getTimeStamp())
                .returnToParentElement();
    }

    private static void createEventsSection(){
        flurrySession
                .createParentElement(FlurryData.Tags.Events);
    }

    public static void FL_FACEBOOK_STARTED(){
        logCustomEvent(FlurryData.Events.FL_FACEBOOK_STARTED);
        eventAdded();
        eventCounter ++;
    }

    public static void FL_FACEBOOK_SUCCESS(){
        logCustomEvent(FlurryData.Events.FL_FACEBOOK_SUCCESS);
        eventAdded();
        eventCounter ++;
    }




    private static void logCustomEvent(FlurryData.Events event){
        flurrySession
                .createParentElement(FlurryData.Tags.Event, eventCounter)
                .createChildElement(FlurryData.Parameters.title, event)
                .createChildElement(FlurryData.Parameters.username, System.getProperty("test.username"))
                .createChildElement(FlurryData.Parameters.time_stamp, getTimeStamp())
                .createChildElement(FlurryData.Parameters.connectivity, "WIFI");
    }

    private static void logParameter(FlurryData.Parameters parameter, String value){
        flurrySession
                .createChildElement(parameter, value);
    }

    private static void eventAdded(){
        flurrySession
                .returnToParentElement();
    }

    public static void saveSession(){
        DateFormat dateFormat = new SimpleDateFormat(REPORTS_DATE_FORMAT);
        flurrySession.build(pathToUsageReports + "usageReport" + dateFormat.format(new Date()) + ".xml");
    }

    private static String getTimeStamp() {
        DateFormat dateFormat = new SimpleDateFormat(FLURRY_DATE_FORMAT);
        return dateFormat.format(new Date());
    }


}
