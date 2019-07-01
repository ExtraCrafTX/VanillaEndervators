package com.extracraftx.minecraft.vanillaendervators.config;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Config {
    public static Config INSTANCE = new Config();

    private static final File configDir = new File("config");
    private static final File configFile = new File("config/vanillaendervators_config.json");
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().setLenient().serializeNulls().create();

    public int teleportDamage = 2;
    public int maxRange = 256;
    public boolean sameColourOnly = true;
    public boolean teleportWhileSprinting = false;
    public boolean allowBlocked = false;

    public static void loadConfig(){
        try{
            configDir.mkdirs();
            if(configFile.createNewFile()){
                FileWriter fw = new FileWriter(configFile);
                fw.append(gson.toJson(INSTANCE));
                fw.close();
            }else{
                FileReader fr = new FileReader(configFile);
                INSTANCE = gson.fromJson(fr, Config.class);
                fr.close();
                return;
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static void saveConfigs(){
        try{
            configDir.mkdirs();
            FileWriter fw = new FileWriter(configFile);
            fw.append(gson.toJson(INSTANCE));
            fw.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

}