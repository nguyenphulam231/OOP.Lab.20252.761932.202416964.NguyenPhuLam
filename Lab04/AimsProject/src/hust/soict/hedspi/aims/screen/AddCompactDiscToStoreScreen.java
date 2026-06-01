package hust.soict.hedspi.aims.screen;

import javax.swing.*;
import hust.soict.hedspi.aims.media.CompactDisc;
import hust.soict.hedspi.aims.store.Store;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddCompactDiscToStoreScreen extends AddItemToStoreScreen {
    private JTextField tfArtist;

    public AddCompactDiscToStoreScreen(Store store) {
        super(store, "Add CD to Store");
        
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String title = tfTitle.getText();
                String category = tfCategory.getText();
                float cost = Float.parseFloat(tfCost.getText());
                String artist = tfArtist.getText();

                CompactDisc cd = new CompactDisc(title, category, artist, cost);
                store.addMedia(cd);
                JOptionPane.showMessageDialog(null, "Thêm đĩa CD vào cửa hàng thành công!");
                dispose();
            }
        });
        this.setVisible(true);
    }

    @Override
    protected void addCustomFields(JPanel panel) {
        panel.add(new JLabel("Artist:"));
        tfArtist = new JTextField();
        panel.add(tfArtist);
    }
}