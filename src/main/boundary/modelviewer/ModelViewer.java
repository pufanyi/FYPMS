package main.boundary.modelviewer;

import main.model.Displayable;

import java.util.List;
import java.util.Objects;

public class ModelViewer {
    public static void displaySingleDisplayable(Displayable displayable) {
        System.out.println(displayable.getSplitter());
        System.out.print(displayable.getDisplayableString());
        System.out.println(displayable.getSplitter());
    }

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
