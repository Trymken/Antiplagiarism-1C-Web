package algorithms;

import java.io.File;
import java.util.ArrayList;

import static algorithms.UsefulMethods.getFileExtension;

public class FileWalker {

    private final ArrayList<String> listFilesBsl;
    private final ArrayList<String> listFilesXml;

    public FileWalker(){
        listFilesBsl = new ArrayList<>();
        listFilesXml = new ArrayList<>();
    }

    public void walk(String path) {
        File root = new File( path );
        File[] list = root.listFiles();

        if (list == null) return;

        for ( File f : list ) {
            if (f.isDirectory()) {
                walk( f.getAbsolutePath() );
            }
            else {
                String extension = getFileExtension(f.getName());
                if (extension.equals("bsl")){
                    listFilesBsl.add(f.getAbsolutePath());
                } else if (extension.equals("xml")) {
                    listFilesXml.add(f.getAbsolutePath());
                }
            }
        }
    }

    public ArrayList<String> getListFilesBsl() {
        return listFilesBsl;
    }

    public ArrayList<String> getListFilesXml() {
        return listFilesXml;
    }
}
