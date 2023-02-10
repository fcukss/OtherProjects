package com.kaplya.app;

import com.kaplya.app.core.Window;
import com.kaplya.app.sandBox.Example;

import java.awt.*;

public class Main {
    public static void main(String[] args) {
        Window window = new Window("Window", 960, 640);
        window.addLayer(new Example("Layer1", Color.black));
        window.addLayer(new Example("Layer2", Color.red));
    }
}