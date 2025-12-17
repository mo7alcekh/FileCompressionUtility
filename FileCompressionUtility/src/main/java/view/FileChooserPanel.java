package view;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileChooserPanel extends JPanel {
    private JTextField textField;
    private List<File> selectedFiles = new ArrayList<>();
    private File selectedDirectory = null;
    private boolean isDirSelection;

    public FileChooserPanel(String title, boolean isDirSelection) {
        this.isDirSelection = isDirSelection;
        setLayout(new BorderLayout(10, 0));
        setBackground(new Color(245, 247, 250)); // Match Main BG

        JLabel lbl = new JLabel(title);
        lbl.setPreferredSize(new Dimension(130, 30));
        lbl.setFont(new Font("Segoe UI", Font.BOLD, 12));
        
        textField = new JTextField();
        textField.setEditable(false);
        textField.setBackground(Color.WHITE);
        textField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.LIGHT_GRAY),
            BorderFactory.createEmptyBorder(5, 5, 5, 5)));

        JButton btn = new JButton("Browse...");
        btn.setBackground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.addActionListener(e -> browse());

        add(lbl, BorderLayout.WEST);
        add(textField, BorderLayout.CENTER);
        add(btn, BorderLayout.EAST);
    }

    private void browse() {
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(isDirSelection ? JFileChooser.DIRECTORIES_ONLY : JFileChooser.FILES_AND_DIRECTORIES);
        if (!isDirSelection) chooser.setMultiSelectionEnabled(true);

        if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            if (isDirSelection) {
                selectedDirectory = chooser.getSelectedFile();
                textField.setText(selectedDirectory.getAbsolutePath());
            } else {
                File[] files = chooser.getSelectedFiles();
                selectedFiles = Arrays.asList(files);
                textField.setText(files.length + " item(s) selected");
            }
        }
    }

    public List<File> getSelectedFiles() { return selectedFiles; }
    public File getSelectedDirectory() { return selectedDirectory; }
}