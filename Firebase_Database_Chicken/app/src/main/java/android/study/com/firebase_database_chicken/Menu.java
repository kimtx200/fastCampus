package android.study.com.firebase_database_chicken;

/**
 * Created by ted_2 on 2016-11-01.
 */

public class Menu {

    public Menu(){

    }

    private String MENU_NAME;

    private String MENU_PRICE;

    private String MENU_IMAGE;

    public String getMENU_NAME ()
    {
        return MENU_NAME;
    }

    public void setMENU_NAME (String MENU_NAME)
    {
        this.MENU_NAME = MENU_NAME;
    }

    public String getMENU_PRICE ()
    {
        return MENU_PRICE;
    }

    public void setMENU_PRICE (String MENU_PRICE)
    {
        this.MENU_PRICE = MENU_PRICE;
    }

    public String getMENU_IMAGE ()
    {
        return MENU_IMAGE;
    }

    public void setMENU_IMAGE (String MENU_IMAGE)
    {
        this.MENU_IMAGE = MENU_IMAGE;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [MENU_NAME = "+MENU_NAME+", MENU_PRICE = "+MENU_PRICE+", MENU_IMAGE = "+MENU_IMAGE+"]";
    }
}
