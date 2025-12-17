package view;

import controller.CompressionController;
import model.CompressionType;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class CompressionGUI extends JFrame {

    private FileChooserPanel filesPanel;
    private FileChooserPanel destPanel;
    private JComboBox<CompressionType> typeComboBox;
    private JCheckBox encryptCheckBox;
    private JProgressBar progressBar;
    private JLabel statusLabel;

    // --- IMPROVED COLOR PALETTE (High Contrast) ---
    private final Color COLOR_BG = new Color(245, 247, 250);
    
    // Darker Blue for better visibility with white text
    private final Color COLOR_PRIMARY = new Color(0, 102, 204); 
    
    // Darker Green for better visibility with white text
    private final Color COLOR_SUCCESS = new Color(0, 153, 76);
    
    // Header Dark Color
    private final Color COLOR_DARK = new Color(44, 62, 80);

    public CompressionGUI() {
        setTitle("File Compression Utility Pro");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(650, 500);
        setLocationRelativeTo(null);
        getContentPane().setBackground(COLOR_BG);
        
        initComponents();
    }

    private void initComponents() {
        setLayout(new BorderLayout());

        // 1. Header
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(COLOR_DARK);
        headerPanel.setPreferredSize(new Dimension(getWidth(), 60));
        JLabel title = new JLabel("Compression Master");
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Segoe UI", Font.BOLD, 20));
        headerPanel.add(title);
        add(headerPanel, BorderLayout.NORTH);

        // 2. Main Content
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(COLOR_BG);
        mainPanel.setBorder(new EmptyBorder(20, 30, 20, 30));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 5, 10, 5);
        gbc.weightx = 1.0;
        gbc.gridx = 0;

        // Row 1: Files
        filesPanel = new FileChooserPanel("Select Files/Folder:", false);
        gbc.gridy = 0; mainPanel.add(filesPanel, gbc);

        // Row 2: Dest
        destPanel = new FileChooserPanel("Destination Folder:", true);
        gbc.gridy = 1; mainPanel.add(destPanel, gbc);

        // Row 3: Options
        JPanel optPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        optPanel.setBackground(COLOR_BG);
        
        optPanel.add(new JLabel("Format: "));
        typeComboBox = new JComboBox<>(CompressionType.values());
        typeComboBox.setBackground(Color.WHITE);
        optPanel.add(typeComboBox);

        encryptCheckBox = new JCheckBox("Enable Encryption / Decryption (AES)");
        encryptCheckBox.setBackground(COLOR_BG);
        optPanel.add(encryptCheckBox);
        
        gbc.gridy = 2; mainPanel.add(optPanel, gbc);

        // Row 4: Buttons (Fixed Visibility)
        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        btnPanel.setBackground(COLOR_BG);
        
        JButton btnCompress = createStyledButton("Compress", COLOR_PRIMARY);
        btnCompress.addActionListener(e -> CompressionController.getInstance().handleCompressionRequest(
            this, filesPanel.getSelectedFiles(), destPanel.getSelectedDirectory(), 
            (CompressionType)typeComboBox.getSelectedItem(), encryptCheckBox.isSelected()));

        JButton btnDecompress = createStyledButton("Decompress", COLOR_SUCCESS);
        btnDecompress.addActionListener(e -> CompressionController.getInstance().handleDecompressionRequest(
            this, filesPanel.getSelectedFiles(), destPanel.getSelectedDirectory(), 
            (CompressionType)typeComboBox.getSelectedItem(), encryptCheckBox.isSelected()));

        btnPanel.add(btnCompress);
        btnPanel.add(btnDecompress);
        gbc.gridy = 3; mainPanel.add(btnPanel, gbc);

        // Row 5: Progress
        progressBar = new JProgressBar(0, 100);
        progressBar.setStringPainted(true);
        progressBar.setForeground(COLOR_SUCCESS);
        gbc.gridy = 4; mainPanel.add(progressBar, gbc);

        add(mainPanel, BorderLayout.CENTER);

        // 3. Footer Status
        JPanel statusPanel = new JPanel(new BorderLayout());
        statusPanel.setBorder(new EmptyBorder(5, 10, 5, 10));
        statusPanel.setBackground(Color.WHITE);
        statusLabel = new JLabel("Status: Ready");
        statusLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        statusPanel.add(statusLabel, BorderLayout.CENTER);
        add(statusPanel, BorderLayout.SOUTH);
    }

    // --- MODIFIED BUTTON CREATOR FOR BETTER RENDERING ---
    private JButton createStyledButton(String text, Color bg) {
        JButton btn = new JButton(text);
        btn.setBackground(bg);
        btn.setForeground(Color.WHITE); // Ensures text is white
        btn.setFocusPainted(false);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btn.setBorder(BorderFactory.createEmptyBorder(10, 25, 10, 25));
        
        // These two lines ensure the background color renders correctly on Windows
        btn.setContentAreaFilled(false);
        btn.setOpaque(true);
        
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return btn;
    }

    public void setProgress(int value) { progressBar.setValue(value); }
    public void setStatus(String message) { statusLabel.setText("Status: " + message); }
}