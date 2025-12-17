import controller.ControllerFactory;
import view.CompressionGUI;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                // Set system Look and Feel for professional appearance
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }

            // Initialize the View
            CompressionGUI gui = new CompressionGUI();
            
            // Ensure Controller is initialized (optional, as Singleton loads lazily)
            ControllerFactory.getController();

            // Show GUI
            gui.setVisible(true);
        });
    }
}