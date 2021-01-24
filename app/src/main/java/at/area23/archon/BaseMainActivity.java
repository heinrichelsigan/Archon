/*
 *
 * @author           Heinrich Elsigan
 * @version          V 1.0.1
 * @since            API 27 Oreo 8.1
 *
 */

package at.area23.archon;

import android.app.Activity;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.provider.Settings;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.ShowableListMenu;

import java.lang.reflect.Field;
import java.util.Objects;
import java.util.HashMap;
import at.area23.archon.R;


public class BaseMainActivity extends AppCompatActivity {

    volatile boolean endRecursion = false;
    protected volatile boolean started = false;
    protected volatile int startedTimes = 0;
    protected volatile int errNum = 0;
    protected String tmp = "";

    protected Menu myMenu;
    protected HashMap<Integer, android.view.View> viewMap;
    protected android.view.View rootView = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        RessourceViewHashMap(rootView, viewMap);
    }


    /**
     * hashMapViewRecursivley - adds recursivley all child views to a ressource hash map
     *
     * @param aView - current view in the hierarchy to be parsed
     * @param viewHashMap - Hashmap, that contains ressource Ids als keys and views with children as values
     * @param mapMsg - a full informational message, what views have been added to ressource view HashMap
     * @param loopCnt - totally loop calls for that recursion method
     * @param rDepth - recursion depth (maybe you neeed that to avoid stack overflows
     * // TODO: at here CancellactinToken, that cancels full recursion, when
     * // operation takes more than a defined time interval or when recursive depth level is too hug
     *
     * @return number of loop calls of tecursive method
     */
    protected int hashMapViewRecursivley(
            android.view.View aView,
            java.util.HashMap<Integer, android.view.View> viewHashMap,
            String mapMsg,
            int loopCnt,
            int rDepth
    ) {

        int childCount = 0;
        int viewId = aView.getId();

        if (!(viewHashMap.containsKey(viewId))) {
            viewHashMap.put(viewId, aView);
            mapMsg += rDepth + "\t:" + "view(" + viewId + ") \t -> " + this.getAll4RId(viewId) + "\r\n";
        }

        if (aView instanceof ViewGroup) {

            ViewGroup viewGroup = (ViewGroup)aView;
            int childrenSize = viewGroup.getChildCount();

            for (childCount = 0; childCount < childrenSize; childCount++) {

                int childId = viewGroup.getChildAt(childCount).getId();
                View childView  = (View)(viewGroup.getChildAt(childCount));

                if (!(viewHashMap.containsKey(childId))) {
                    viewHashMap.put(childId, childView);
                    mapMsg += rDepth + "\t:" + "view(" + viewId + ") \t -> child(" + childId + ") \t -> " +
                            this.getAll4RId(viewId) + "\r\n";
                }
                loopCnt += hashMapViewRecursivley(childView, viewHashMap, mapMsg, ++loopCnt, ++rDepth);
            }
        }

        return loopCnt;
    }


    /**
     * InitViewHashMap - init a Hashmap, containing ressource Id as key, view as value for all view groups in current application with children
     * @param rootView - the root view of curremt window, current activity
     * @param viewHashMap - HashMap(unique ressource Id => android view with childrem)
     *
     * @return false to allow normal menu processing to proceed, true to consume it here.
     */
    protected int RessourceViewHashMap(
            android.view.View rootView,
            java.util.HashMap<Integer, android.view.View> viewHashMap
    ) {

        String mapMsg = "";

        if (viewHashMap == null) { // init new HashMap, when null and not initialized
            viewHashMap = new java.util.HashMap<Integer, android.view.View>();
            mapMsg += "Initializing new viewMap ctor.\r\n";
        } else { // Clear Ressource Hash Map first to avoid duplicated entries in HashMap, when method is called more than once
            mapMsg += "Clearing existing viewMap with " + viewHashMap.size() + " entries.\r\n";
            viewHashMap.clear();
        }
        if (rootView == null) {
            mapMsg += "rootView is null, ";
            rootView = getWindow().getDecorView().getRootView();
            mapMsg += "getting rootView from decor view of current window.\r\n";
        }

        int loops = 0;
        int runnedCycles = hashMapViewRecursivley(rootView, viewHashMap, mapMsg, loops, 0);
        if (runnedCycles > 0 && viewHashMap.size() > 0) {
            mapMsg = "HashMap builded with " + viewHashMap.size() + " R.Ids as keys after running " + runnedCycles + " total cycles.\r\n" + mapMsg;
            showMessage(mapMsg, false);
        } else {
            mapMsg += "HashMap not builed after running " + runnedCycles + " cycles.\r\n";
            showMessage(mapMsg, true);
        }

        return runnedCycles;
    }


    /**
     * onCreateOptionsMenu
     * @param menu - the menu item, that has been selected
     * @return true, if menu successfully created, otherwise false
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        myMenu = menu;
        int menuId = -1;

        try {
            menuId = getApplicationContext().getResources().getIdentifier(
                    "menu_main",
                    "menu",
                    getApplicationContext().getPackageName());

            if (menuId >= 0) {
                getMenuInflater().inflate(menuId, menu);
                return true;
            }
        } catch (Exception menuEx) {
            showException(menuEx);
        }

        return false;
    }

    /**
     * onOptionsItemSelected
     * @param  item - the menu item, that has been selected
     * @return false to allow normal menu processing to proceed, true to consume it here.
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int  mItemId = (item != null) ?  item.getItemId() : -1;
        if (mItemId >= 0) {
            boolean consumedNoFwd = actionMenuItem(mItemId, item, myMenu);
            if (consumedNoFwd)
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * actionMenuItem - you must implement that method for your purpose
     * @param itemId - ressource Id of menu item
     * @param item - MenuItem item entity
     * @param parentMenu - parent Menu instance, where the menu item belongs to
     * @return true --> Event Consumed here, now It should not be forwarded for other event
     * 			false --> Forward for other event to get consumed
     */
    public boolean actionMenuItem(int itemId, MenuItem item, Menu parentMenu) {

        // we fall through by default
        return false;
    }


    /**
     * getRNameFromId
     * @param id the ressource Identifier of an graphical view element (android.view)
     * @param objectById referebce to the created instance at runtime
     * @return R.id reflected name
     */
    public String getRNameFromId(int id, Object objectById) {
        // objectById = null;
        Class clss = null;
        try {
            clss = Class.forName(getPackageName() + ".R$id");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Class<R.id> c = R.id.class;

        at.area23.archon.R.id rObj = (at.area23.archon.R.id) new Object();
        // id object = new id();
        // R.id object = new R.id()
        Field[] fields;
        try {
            fields =  clss.getDeclaredFields();

            for (Field field : fields) { // Iterate through whatever fields R.id has
                field.setAccessible(true);
                String reflectedName = field.getName();
                String reflectedString = reflectedName;
                int resId = 0;
                try {
                    resId = (int) field.get(null);
                    reflectedString = this.getResources().getString(resId);
                } catch (NullPointerException ne) {
                    showError(ne, true);
                }
                catch (Exception e) {
                    showError(e, true);
                }

                try {
                    objectById = (Object) field.get(rObj);
                } catch (IllegalAccessException e) {
                    showError(e,true);
                }
                /*
                int nyRUd = getApplicationContext().getResources().getIdentifier(
                    reflectedName, "id", getApplicationContext().getPackageName());
                */
                if (id == resId) {
                    return reflectedName;
                }
            }
        } catch (Exception exf) {
            showError(exf, true);
        }
        return null;
    }


    /**
     * getAll4RId - get all names, types, meta-data, xml for an existing ressource Id
     * @param rId - the ressource Id
     * @return - combined string information for that ressource Id
     */
    public String getAll4RId(int rId) {
        String allStr = "";
        try {
            allStr += "\r\ngetString(" + rId + ") = " + getApplicationContext().getResources().getString(rId) + "\r\n";
        } catch (Exception exi) { showError(exi, false); }
        try {
            allStr += "\r\nResource Name = " + getApplicationContext().getResources().getResourceName(rId) + "\r\n";
        } catch (Exception exi) { showError(exi, false); }
        try {
            allStr += "\r\ngetXml(" + rId + ") = " + getApplicationContext().getResources().getXml(rId) +  "\r\n";
        } catch (Exception exi) { showError(exi, false); }
        return allStr;
    }


    /**
     * showMessage shows a new Toast dynamic message
     * @param text to display
     * @param tooShort if set to yes, message inside toast widget appears only very shortly
     */
    public void showMessage(CharSequence text, boolean tooShort) {
        if (text != null && text != "") {
            Context context = getApplicationContext();
            Toast toast = Toast.makeText(context, text,
                    tooShort ? Toast.LENGTH_SHORT : Toast.LENGTH_LONG);
            toast.show();
        }
    }

    /**
     * showMessage shows a new Toast dynamic message
     * @param text to display
     */
    public void showMessage(CharSequence text) { showMessage(text, false); }


    /**
     * showError simple dummy error handler
     * @param myErr java.lang.Throwable
     * @param showMessage triggers, that a Toast Widget shows the current Error / Exception
     */
    public void showError(java.lang.Throwable myErr, boolean showMessage) {
        if (myErr != null) {
            CharSequence text = "CRITICAL ERROR #" + String.valueOf((++errNum)) + " " + myErr.getMessage() + "\nMessage: " + myErr.getLocalizedMessage() + "\n";
            if (showMessage)
                showMessage(text);
            myErr.printStackTrace();
        }
    }

    /**
     * showError simple dummy error handler
     * @param myEx - tje exceütion, that has been thrown
     */
    public void showException(java.lang.Exception myEx) { showError(myEx, true); }

}