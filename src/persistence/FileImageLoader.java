package persistence;

import java.io.*;
import model.Image;

public class FileImageLoader implements ImageLoader{
    private File[] file;
    private int index=0;
    
    public FileImageLoader(File folder){
        this.file = folder.listFiles();
    }
    
    public FileFilter imageType(){
        return new FileFilter(){
            @Override
            public boolean accept(File pathname){
                return pathname.getName().endsWith(".jpg");
            }
        };
    }
    
    public Image imageAt(final int i){
        return new Image(){
            @Override 
            public String name(){
                return file[i].getName();
            }
            @Override
            public InputStream stream(){
                try {
                    return new BufferedInputStream(new FileInputStream(file[i]));
                } catch (FileNotFoundException ex) {
                    return null;
                }
            }
            @Override
            public Image next(){
                if(i==file.length-1){
                    return imageAt(0);
                }else{
                    return imageAt(i+1);
                }
            }
            @Override
            public Image prev(){
                if (i==0){
                    return imageAt(file.length-1);
                }else{
                    return imageAt(i-1);
                }
            }
        };
    }

    @Override
    public Image load() {
        return imageAt(index);
    }

}
