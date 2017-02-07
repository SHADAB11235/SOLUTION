package com.netcamp.sol;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by HP on 13-12-2016.
 */
public class FetchSongs {
    boolean fetchstatus=false;
    ArrayList<File> songs=new ArrayList<File>();
    FetchSongs(){

    }
    public ArrayList<File> findSongs(File root){
        ArrayList<File> al=new ArrayList<File>();
        File[] files=root.listFiles();
        for (File singleFile : files){
            if(singleFile.isDirectory() && !singleFile.isHidden()){
                al.addAll(findSongs(singleFile));
            }
            else {
                if(singleFile.getName().endsWith(".mp3")){
                    al.add(singleFile);
                }
            }
        }
        fetchstatus=true;
        songs=al;
        return al;
    }
    public boolean getfetchstatus(){
        return fetchstatus;
    }
    public ArrayList<File> getsonglist(){
        return songs;
    }
}
