package com.ryanpatrick.mhrisearmorsetsearcher.util;

import android.content.Context;
import android.content.res.Configuration;

public class Utilities {
    public static boolean isTablet(Context context){
        boolean xLarge =
                ((context.getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK)
                == Configuration.SCREENLAYOUT_SIZE_XLARGE);
        boolean large =
                ((context.getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK)
                == Configuration.SCREENLAYOUT_SIZE_LARGE);
        return (xLarge || large);
    }

}
