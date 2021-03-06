package salesassistant.com.salesassistant;

/**
 * Project constants
 */
public final class Constants {

    public static final String ITEM_ID_KEY = "ItemIdKey";
    public static final String DATE_FORMAT_ISO8601 = "yyyy-MM-dd HH:mm:ss";
    public static final String TIME_ZONE_ISO8601 = "UTC";

    public static final String SYNC_BACKUP = "backup";
    public static final String SYNC_RESTORE = "restore";

    private Constants(){
        throw new IllegalStateException();
    }
}
