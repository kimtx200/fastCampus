package android.study.com.firebase_database_chicken;

import java.util.List;

/**
 * Created by ted_2 on 2016-11-01.
 */

public class Branch {

    public Branch(){

    }

    private List<Menu> Menu;

    private String NAME;

    private String LOGO;

    private long DELIVERY_FEE;

    private String BRANCH;

    public List<Menu> getMENU ()
    {
        return Menu;
    }

    public void setMENU (List<Menu> Menu)
    {
        this.Menu = Menu;
    }

    public String getNAME ()
    {
        return NAME;
    }

    public void setNAME (String NAME)
    {
        this.NAME = NAME;
    }

    public String getLOGO ()
    {
        return LOGO;
    }

    public void setLOGO (String LOGO)
    {
        this.LOGO = LOGO;
    }

    public long getDELIVERY_FEE ()
    {
        return DELIVERY_FEE;
    }

    public void setDELIVERY_FEE (long DELIVERY_FEE)
    {
        this.DELIVERY_FEE = DELIVERY_FEE;
    }

    public String getBRANCH ()
    {
        return BRANCH;
    }

    public void setBRANCH (String BRANCH)
    {
        this.BRANCH = BRANCH;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [MENU = "+Menu+", NAME = "+NAME+", LOGO = "+LOGO+", DELIVERY_FEE = "+DELIVERY_FEE+", BRANCH = "+BRANCH+"]";
    }
}
