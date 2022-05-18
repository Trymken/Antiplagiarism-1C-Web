package algorithms;

import java.io.File;
import java.util.ArrayList;

import static algorithms.Algorithms.getFileExtension;

public class FileWalker {

    private ArrayList<String> listFiles;

    public FileWalker(){
        listFiles = new ArrayList<>();
    }

    public void walk(String path) {
        File root = new File( path );
        File[] list = root.listFiles();

        if (list == null) return;

        for ( File f : list ) {
            if ( f.isDirectory() && !f.getName().equals("Results")) {
                walk( f.getAbsolutePath() );
            }
            else {
                if (getFileExtension(f.getName()).equals("bsl")){
                    listFiles.add(f.getAbsolutePath());
                }
            }
        }
    }

    public ArrayList<String> getListFiles() {
        return listFiles;
    }
}
