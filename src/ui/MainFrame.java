package ui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.*;
import persistence.FileImageLoader;

public class MainFrame extends JFrame {

    private JTextField textField;
    private SwingImageDisplay imagePanel;
    private FileImageLoader fileImageLoader;
    private int index = 0;

    public MainFrame() {
        super("Visualizador de imágenes");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        createLayout();
        setMinimumSize(new Dimension(800, 600));
        pack();
        setVisible(true);
    }

    private void createLayout() {
        Container p = getContentPane();
        p.setLayout(new BorderLayout());
        p.add(getChoosePanel(), BorderLayout.NORTH);
        p.add(getImagePanel(), BorderLayout.CENTER);
        p.add(getButtonsPanel(), BorderLayout.SOUTH);
    }

    private JPanel getChoosePanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        JLabel label = new JLabel("Carpeta");
        textField = new JTextField(30);
        JButton button = new JButton("Cargar");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = textField.getText().trim();
                if (text != "") {
                    File folder = new File(text);
                    if (!folder.isDirectory()) {
                        return;
                    }
                    fileImageLoader = new FileImageLoader(folder);
                    imagePanel.show(fileImageLoader.load());
                }
            }
        });

        panel.add(label);
        panel.add(textField);
        panel.add(button);
        return panel;
    }

    private SwingImageDisplay getImagePanel() {
        imagePanel = new SwingImageDisplay();
        return imagePanel;
    }

    private JPanel getButtonsPanel() {
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        JButton nextButton = new JButton("Siguiente");
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                imagePanel.show(imagePanel.current().next());
            }
        });
        JButton prevButton = new JButton("Anterior");
        prevButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                imagePanel.show(imagePanel.current().prev());
            }
        });
        buttonsPanel.add(prevButton);
        buttonsPanel.add(nextButton);
        return buttonsPanel;
    }
}
