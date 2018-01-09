package model;

import java.io.InputStream;

public interface Image {
    public String name();
    public InputStream stream();
    public Image next();
    public Image prev();
}
