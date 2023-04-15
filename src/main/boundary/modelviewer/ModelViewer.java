package main.boundary.modelviewer;

import main.model.Displayable;

import java.util.List;
import java.util.Objects;

/**
 The ModelViewer class is responsible for displaying single or lists of objects that implement the Displayable interface.
 */
public class ModelViewer {
    /**
     * Displays a single Displayable object.
     * @param displayable The Displayable object to be displayed.
     */
    public static void displaySingleDisplayable(Displayable displayable) {
        System.out.println(displayable.getSplitter());
        System.out.print(displayable.getDisplayableString());
        System.out.println(displayable.getSplitter());
    }

    /**
     * Displays a list of Displayable objects.
     * @param displayableList The list of Displayable objects to be displayed.
     */
    public static void displayListOfDisplayable(List<? extends Displayable> displayableList) {
        if (Objects.isNull(displayableList) || displayableList.isEmpty()) {
            System.out.println("Nothing found");
            return;
        }
        System.out.println(displayableList.get(0).getSplitter());
        for (Displayable displayable : displayableList) {
            System.out.print(displayable.getDisplayableString());
            System.out.println(displayable.getSplitter());
        }
    }
}
