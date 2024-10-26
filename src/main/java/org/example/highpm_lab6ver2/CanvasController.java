package org.example.highpm_lab6ver2;

import geometry2d.Figure;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.util.Random;

public class CanvasController {
    private Node draggedElement;

    public Pane canvasPane;

    @FXML
    public void onRectangleButtonClick() {
        int minSize = 25;
        int maxSize = 125;

        Random random = new Random();
        geometry2d.Rectangle rec = new geometry2d.Rectangle(random.nextInt(maxSize - minSize) + minSize, random.nextInt(maxSize - minSize) + minSize);

        Rectangle rectangle = new Rectangle(rec.getLength(), rec.getHeight());
        rectangle.setLayoutX(random.nextInt((int) (canvasPane.getWidth() - rectangle.getWidth())));
        rectangle.setLayoutY(random.nextInt((int) (canvasPane.getHeight() - rectangle.getHeight())));
        rectangle.setFill(randomColor());

        rectangle.setOnMouseClicked(e -> {
            if(e.getButton()== MouseButton.SECONDARY){
                rectangle.setFill(randomColor());
            }
        });

        canvasPane.getChildren().add(rectangle);
    }

    @FXML
    public void onCircleButtonClick() {
        int minDiameter = 20;
        int maxDiameter = 50;

        Random random = new Random();
        geometry2d.Circle cir = new geometry2d.Circle(random.nextInt(maxDiameter - minDiameter) + minDiameter);

        Circle circle = new Circle(cir.getRadius());
        circle.setCenterX(random.nextInt((int) (canvasPane.getWidth() - (2 * cir.getRadius()))) + cir.getRadius());
        circle.setLayoutY(random.nextInt((int) (canvasPane.getHeight() - (2 * cir.getRadius()))) + cir.getRadius());
        circle.setFill(randomColor());

        circle.setOnMouseClicked(e->{
            if(e.getButton()== MouseButton.SECONDARY){
                circle.setFill(randomColor());
            }
        });

        canvasPane.getChildren().add(circle);
    }

    @FXML
    public void canvasMousePress(MouseEvent mouseEvent) {
        canvasPane.getChildren().forEach((c) -> {
            if(c.isPressed()){
                draggedElement = c;
            }
        });

        if(draggedElement != null) {
            canvasPane.getChildren().remove(draggedElement);
            canvasPane.getChildren().add(draggedElement);
        }
    }

    @FXML
    public void canvasMouseUp(MouseEvent mouseEvent) {
        draggedElement = null;
    }

    @FXML
    public void canvasMouseMove(MouseEvent mouseEvent) {
        if(draggedElement != null){
            if(mouseEvent.getX() > canvasPane.getWidth() || mouseEvent.getX() < 0
                    || mouseEvent.getY() < 0 || mouseEvent.getY() > canvasPane.getHeight()){
                canvasMouseUp(mouseEvent);
            }

            if(draggedElement instanceof Rectangle){
                draggedElement.setLayoutX(mouseEvent.getX()-(((Rectangle) draggedElement).getWidth()/2));
                draggedElement.setLayoutY(mouseEvent.getY()-(((Rectangle) draggedElement).getHeight()/2));
            }
            if(draggedElement instanceof Circle){
                draggedElement.setLayoutX(mouseEvent.getX()-(((Circle) draggedElement).getCenterX()));
                draggedElement.setLayoutY(mouseEvent.getY()-(((Circle) draggedElement).getCenterY()));
            }
        }
    }

    private Color randomColor(){
        Random random = new Random();
        return Color.rgb(random.nextInt(255), random.nextInt(255), random.nextInt(255));
    }
}