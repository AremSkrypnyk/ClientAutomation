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
    private static String pathForUsageReports = "usage/customUsage/";

    private static int eventCounter;
    private static XMLBuilder flurrySession;

    public static void init(){
        flurrySession = new XMLBuilder(FlurryData.Tag.Flurry);
        eventCounter = 1;
        logDevice();
        createEventsSection();
    }

    private static void logDevice(){
        flurrySession
                .createParentElement(FlurryData.Tag.Device)
                .createChildElement(FlurryData.Parameter.title, System.getProperty("test.device"))
                .createChildElement(FlurryData.Parameter.time_stamp, getTimeStamp())
                .returnToParentElement();
    }

    private static void createEventsSection(){
        flurrySession
                .createParentElement(FlurryData.Tag.Events);
    }




    public static void FL_FACEBOOK_STARTED(){
        logEmptyEvent(FlurryData.Event.FL_FACEBOOK_STARTED);
        logParameter(FlurryData.Parameter.username, "");
        eventAdded();
    }

    public static void FL_FACEBOOK_SUCCESS(){
        logCustomEvent(FlurryData.Event.FL_FACEBOOK_SUCCESS);
        eventAdded();
    }

    public static void FL_FACEBOOK_FAILED(FlurryData.ErrorMessage errorMessage){
        logCustomEvent(FlurryData.Event.FL_FACEBOOK_FAILED);
        logParameter(FlurryData.Parameter.error, errorMessage.toString());
        eventAdded();
    }


    public static void FL_GOOGLE_PLUS_STARTED(){
        logEmptyEvent(FlurryData.Event.FL_GOOGLE_PLUS_STARTED);
        logParameter(FlurryData.Parameter.username, "");
        eventAdded();
    }

    public static void FL_GOOGLE_PLUS_SUCCESS(){
        logCustomEvent(FlurryData.Event.FL_GOOGLE_PLUS_SUCCESS);
        eventAdded();
    }

    public static void FL_GOOGLE_PLUS_FAILED(FlurryData.ErrorMessage errorMessage){
        logCustomEvent(FlurryData.Event.FL_GOOGLE_PLUS_FAILED);
        logParameter(FlurryData.Parameter.error, errorMessage.toString());
        eventAdded();
    }




    private static void logCustomEvent(FlurryData.Event event){
        flurrySession
                .createParentElement(FlurryData.Tag.Event, eventCounter)
                .createChildElement(FlurryData.Parameter.title, event)
                .createChildElement(FlurryData.Parameter.username, System.getProperty("test.username"))
                .createChildElement(FlurryData.Parameter.time_stamp, getTimeStamp())
                .createChildElement(FlurryData.Parameter.connectivity, "WIFI");
        eventCounter ++;
    }

    private static void logEmptyEvent(FlurryData.Event event){
        flurrySession
                .createParentElement(FlurryData.Tag.Event, eventCounter)
                .createChildElement(FlurryData.Parameter.title, event)
                .createChildElement(FlurryData.Parameter.time_stamp, getTimeStamp());
        eventCounter ++;
    }

    private static void logParameter(FlurryData.Parameter parameter, String value){
        flurrySession
                .createChildElement(parameter, value);
    }

    private static void eventAdded(){
        flurrySession
                .returnToParentElement();
    }

    public static void saveSession(){
        DateFormat dateFormat = new SimpleDateFormat(REPORTS_DATE_FORMAT);
        flurrySession.build(pathForUsageReports + "usageReport_" + dateFormat.format(new Date()) + ".xml");
    }

    private static String getTimeStamp() {
        DateFormat dateFormat = new SimpleDateFormat(FLURRY_DATE_FORMAT);
        return dateFormat.format(new Date());
    }


}
