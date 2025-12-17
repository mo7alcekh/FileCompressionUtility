package controller;

/**
 * Simple Factory for obtaining the controller.
 * In larger apps, this might handle dependency injection logic.
 */
public class ControllerFactory {
    
    public static CompressionController getController() {
        return CompressionController.getInstance();
    }
}