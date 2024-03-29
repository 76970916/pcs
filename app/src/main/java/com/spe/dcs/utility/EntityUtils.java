package com.spe.dcs.utility;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.joda.time.DateTime;

public final class EntityUtils {

    private EntityUtils() {}

    public static final Gson gson = new GsonBuilder()
            .registerTypeAdapter(DateTime.class, new DateTimeTypeAdapter())
            .setLenient()
            .create();

}
