
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.ImageCapabilities;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class encryption {

    public static void operation(int key) {

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.showSaveDialog(null);
        File file = fileChooser.getSelectedFile();

        try {
            FileInputStream fis = new FileInputStream(file);

            byte[] data = new byte[fis.available()];
            fis.read(data);
            int i = 0;
            for (byte b : data) {

                data[i] = (byte) (b ^ key);
                i++;
            }
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(data);
            fos.close();
            fis.close();
            JOptionPane.showMessageDialog(null, "encrypted");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        JFrame f = new JFrame();
        f.setTitle("imageEncryption");
        f.setSize(400, 400);
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Font font = new Font("Roboto", Font.BOLD, 25);

        JButton button = new JButton();
        button.setText("Select Image");
        button.setFont(font);
        JTextField textField = new JTextField(10);
        textField.setFont(font);

        button.addActionListener(e -> {
            String text = textField.getText();
            int temp = Integer.parseInt(text);
            operation(temp);
        });

        f.setLayout(new FlowLayout());
        f.add(button);
        f.add(textField);

        f.setVisible(true);

    }

}
