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
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.provider.MediaStore;
import android.provider.Settings;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.ShowableListMenu;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

import at.area23.archon.R;
import at.area23.archon.models.GlobalAppSettings;

import static android.R.*;
import static android.R.id.*;
import static android.R.id;


public class BaseMainActivity extends AppCompatActivity {

    volatile boolean endRecursion = false;
    protected volatile boolean started = false;
    protected volatile int startedTimes = 0;
    protected volatile int errNum = 0;
    protected String tmp = "";

    // public GlobalAppSettings globalVariable;
    protected Menu myMenu;
    protected AtomicInteger atomInt;
    protected HashMap<Integer, android.view.View> viewMap;
    protected android.view.View rootView = null;
    protected volatile boolean atomicSoundLock = false;
    protected GlobalAppSettings globalAppSettings;
    volatile Integer syncLock;
    protected volatile String sound2Play = "";

    protected final static Handler playL8rHandler = new Handler(Looper.getMainLooper());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (globalAppSettings == null) {
            globalAppSettings = (GlobalAppSettings) getApplicationContext();
        }
        // RessourceViewHashMap(rootView, viewMap);
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
            @NotNull android.view.View aView,
            @NotNull java.util.HashMap<Integer, android.view.View> viewHashMap,
            String mapMsg,
            AtomicInteger loopCnt,
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

                loopCnt.set(loopCnt.incrementAndGet());
                int recCount = hashMapViewRecursivley(childView, viewHashMap, mapMsg, loopCnt, ++rDepth);
            }
        }

        return loopCnt.intValue();
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

        AtomicInteger loops = new AtomicInteger();
        loops.set(0);

        int runnedCycles = hashMapViewRecursivley(rootView, viewHashMap, mapMsg, loops, 0);
        if (runnedCycles != 0 && viewHashMap.size() > 0) {
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

        android.R.id rObj = new android.R.id();
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
     * setLanguage
     * @param language - the langzage
     * @return true in case of succcess, otherwise false
     */
    protected boolean setLanguage(String language, MenuItem item) {
        return setLocale(new Locale(language), item);
    }

    /**
     * setLanguage
     * @param aLocale - a locale
     * @param item - a menu item
     * @return true in case of succcess, otherwise false
     */
    protected boolean setLocale(Locale aLocale, MenuItem item) {
        if (item != null) {
            item.setChecked(true);
        }
        // if (globalVariable.getLocale().getLanguage() != aLocale.getLanguage()) {
            //Overwrites application locale in GlobalAppSettings with english
        //     globalVariable.setLocale(aLocale);
        // }
        return true;
    }


    /**
     * showAbout() starts about activity
     */
    public void showAbout() {
        // try {
        //     Thread.currentThread().sleep(10);
        // } catch (Exception exInt) {
        //     errHandler(exInt);
        // }
        // tDbg.setText(R.string.help_text);
        Intent intent = new Intent(this, AboutActivity.class);
        startActivity(intent);
    }

    /**
     * showHelp() prints out help text
     */
    public void showHelp() {
        Intent intent = new Intent(this, AboutActivity.class);
        startActivity(intent);
    }


    /**
     * screenShot
     *
     * @param view2Bmp view2Bmp
     */
    public void screenShot(View view2Bmp) {

        view2Bmp = getWindow().getDecorView().getRootView();
        view2Bmp.buildDrawingCache(false);

        String path = Environment.getExternalStorageDirectory().toString();
        // path = Environment.getStorageDirectory().toString();
        path = getApplicationContext().getExternalFilesDir(null).getAbsolutePath();

        Date currentTime = Calendar.getInstance().getTime();
        String datePartS0 = String.valueOf(Calendar.getInstance().get(Calendar.YEAR));
        String datePartS1 = (Calendar.getInstance().get(Calendar.MONTH) < 10) ?
                "0" : "" + String.valueOf(Calendar.getInstance().get(Calendar.MONTH));
        String datePartS2 = (Calendar.getInstance().get(Calendar.DAY_OF_MONTH) < 10) ?
                "0" : "" + String.valueOf(Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
        String saveName = datePartS0 + "-" + datePartS1 + "-" + datePartS2 + "_archon_" +
                String.valueOf(currentTime.getTime()) + ".jpg";
        OutputStream fileOutStream = null;
        File file = new File(path, saveName);

        try {
            fileOutStream = new FileOutputStream(file);
            Bitmap pictureBitmap = view2Bmp.getDrawingCache(false); // view2Bmp.getDrawingCache(true);
            pictureBitmap.compress(Bitmap.CompressFormat.JPEG, 92, fileOutStream);
            fileOutStream.flush();
            fileOutStream.close();

            MediaStore.Images.Media.insertImage(getContentResolver(), file.getAbsolutePath(), file.getName(), file.getName());

        } catch (Exception saveEcx) {
            showError(saveEcx, true);
            saveEcx.printStackTrace();
        }
        try {
            playL8rHandler.postDelayed(delayPlayScreenshot, 100);
        }
        catch (Exception playl8rEx) {
            showError(playl8rEx, false);
            playl8rEx.printStackTrace();
        }
    }

    /**
     * delayPlayScreenshot = new Runnable() -> { playSound("sound_menu_screenshot"); }
     */
    protected final Runnable delayPlayScreenshot = new Runnable() {
        @Override
        // @SuppressLint("InlinedApi")
        public void run() { playSound("sound_menu_screenshot"); }
    };

    /**
     * delayPlayMouthClick = new Runnable() -> { playSound("sound_mouth_click"); }
     */
    protected final Runnable delayPlayMouthClick = new Runnable() {
        @Override
        // @SuppressLint("InlinedApi")
        public void run() { playSound("sound_mouth_click"); }
    };

    /**
     * delayPlayCCC  = new Runnable() -> { playSound("sound_ccc"); }
     */
    protected final Runnable delayPlayCCC = new Runnable() {
        @Override
        // @SuppressLint("InlinedApi")
        public void run() { playSound("sound_ccc"); }
    };

    /**
     * delayPlayARGH = new Runnable() -> { playSound("sound_argh"); }
     */
    protected final Runnable delayPlayARGH = new Runnable() {
        @Override
        // @SuppressLint("InlinedApi")
        public void run() { playSound("sound_argh"); }
    };

    /**
     * delayPlayGameOver = new Runnable() -> { playGameOver()(); }
     */
    protected final Runnable delayPlaySound = new Runnable() {
        @Override
        // @SuppressLint("InlinedApi")
        public void run() {
            if (sound2Play != "sound_menu_screenshot.wav") {
                playSound(sound2Play);
                syncLock = Integer.valueOf(1);
                synchronized (syncLock) {
                    sound2Play = "";
                }
            }
        }
    };

    /**
     * playMediaFromUri plays any sound media from an internet uri
     * @param url - the full quaöofoed url accessor
     */
    public void playMediaFromUri(String url) {
        MediaPlayer mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioAttributes(
                new AudioAttributes.Builder()
                        .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                        .setUsage(AudioAttributes.USAGE_MEDIA)
                        .build()
        );
        try {
            mediaPlayer.setDataSource(url);
            mediaPlayer.prepare(); // might take long! (for buffering, etc)
            mediaPlayer.start();
        } catch (Exception exi) {
            showError(exi, true);
        }
    }

    /**
     * Play sound file stored in res/raw/ directory
     * @param rawName - resource name or resource id
     *   Name
     *     Syntax  :  android.resource://[package]/[res type]/[res name]
     *     Example : @<code>Uri.parse("android.resource://com.my.package/raw/sound1");</code>
     *   Resource id
     *     Syntax  : android.resource://[package]/[resource_id]
     *     Example : @<code>Uri.parse("android.resource://com.my.package/" + R.raw.sound1); </code>
     *
     */
    public void playRawSound(int rId, String rawName) {
        try {
            Resources res = getResources();
            int resId = rId;
            if (rawName != null) {
                resId = getSoundRId(rawName);
            }

            if (resId != rId) {
                String RESOURCE_PATH = ContentResolver.SCHEME_ANDROID_RESOURCE + "://";
                String path = RESOURCE_PATH + getPackageName() + File.separator + resId;
                Uri soundUri = Uri.parse(path);
                showMessage("RawSound: Uri=" + soundUri.toString() + " path=" + path);
            }

            final MediaPlayer mMediaPlayer = new MediaPlayer();
            mMediaPlayer.setVolume(1.0f, 1.0f);
            mMediaPlayer.setLooping(false);
            mMediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    // Toast.makeText(getApplicationContext(),
                    //         "start playing sound", Toast.LENGTH_SHORT).show();
                    mMediaPlayer.start();
                }
            });
            mMediaPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener() {
                @Override
                public boolean onError(MediaPlayer mp, int what, int extra) {
                    // Toast.makeText(getApplicationContext(), String.format(Locale.US,
                    //         "Media error what=%d extra=%d", what, extra), Toast.LENGTH_LONG).show();
                    return false;
                }
            });


            // 2. Load using content provider, passing file descriptor.
            ContentResolver resolver = getContentResolver();
            AssetFileDescriptor fd = res.openRawResourceFd(resId);
            mMediaPlayer.setDataSource(fd.getFileDescriptor(), fd.getStartOffset(), fd.getLength());
            fd.close();
            mMediaPlayer.prepareAsync();

            // See setOnPreparedListener above
            mMediaPlayer.start();

        } catch (Exception ex) {
            // showException(ex);
            showMessage(String.join("MediaPlayer: " , ex.getMessage()));
            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    /**
     * getSoundRId
     * @param rawSoundName - raw sound name
     * @return getRessources.getIdentifier(rawSoundName, ...):
     */
    public int getSoundRId(String rawSoundName) {
        // Build path using resource number
        int resID = getResources().getIdentifier(rawSoundName, "raw", getPackageName());
        return resID;
    }

    /**
     * playSound
     *  plays a sound
     * @param rawSoundName - resource name
     *      Syntax  :  android.resource://[package]/[res type]/[res name]
     *      Example : @<code>Uri.parse("android.resource://com.my.package/raw/sound1");</code>
     */
    public void playSound(String rawSoundName) {
        int resID = getSoundRId(rawSoundName);
        playRawSound(resID, rawSoundName);
    }


    /**
     * playScreenshot
     *  plays take screen shot sound
     * @param fromRawOrInetUrl - boolean
     *  if true, play sound  raw from apk binary,
     *  otherwise (false) play sound from github repository url
     */
    public void playScreenshot(boolean fromRawOrInetUrl) {
        if (fromRawOrInetUrl)
            playSound("sound_menu_screenshot");
        else
            playMediaFromUri("https://github.com/heinrichelsigan/Archon/blob/master/app/src/main/res/raw/sound_menu_screenshot.wav?raw=true");
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

    /**
     * exitApp() exit game
     */
    public void exitApp() {
        finishAffinity();
        System.exit(0);
    }

}