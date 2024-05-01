/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileReader;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 *
 * @author Admin
 */
public class MainFrame extends javax.swing.JFrame {

    /**
     * Creates new form API_EDIT
     */

    public MainFrame() {
        initComponents();
        jScrollPane1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        MainPanel.setLayout(new GridLayout(15,1));  
        updateScrollPane();
        MainPanel.removeAll();
        MainPanel.revalidate();
        MainPanel.repaint();
        
        String filepath = "src/main/java/TrendingMethod/Contents.json";
        ContentPanel(JsonRead(filepath));
        updateScrollPane();
        
        // Thêm panel mới vào MainPanel và cập nhật hiển thị
        MainPanel.revalidate();
        MainPanel.repaint();
        
        // Thêm action cho Enter Key
        Search.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "clickButton");
        Search.getActionMap().put("clickButton", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
            Search.doClick();
            }
        });
    }
private void updateScrollPane() {
        int preferredHeight = MainPanel.getPreferredSize().height;
        int scrollPaneHeight = jScrollPane1.getViewport().getExtentSize().height;
        jScrollPane1.setVerticalScrollBarPolicy(preferredHeight > scrollPaneHeight ? 
                                                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS : 
                                                JScrollPane.VERTICAL_SCROLLBAR_NEVER);
    }
    public static JSONArray JsonRead(String filePath) {
        try {
            FileReader reader = new FileReader(filePath);
            JSONParser jsonParser = new JSONParser();
            return (JSONArray) jsonParser.parse(reader);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
public static void ContentPanel(JSONArray jsonArray){
if (jsonArray !=null){
    for (Object obj : jsonArray) {
    JSONObject jsonObject = (JSONObject) obj;
    JPanel ContentPane = new JPanel();
    ContentPane.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    ContentPane.setLayout(new GridLayout(3, 1)); // GridLayout cho 3 thành phần này
        // Lấy các giá trị từ JSONObject
    String a = (String) jsonObject.get("Tiêu đề bài viết");
    String b = (String) jsonObject.get("Tên tác giả nếu có");
    String c = (String) jsonObject.get("Ngày tạo");
    
    JLabel Baiviet = new JLabel("       "+a);
    JLabel Tacgia = new JLabel("       Tác giả: "+b);
    JLabel Ngay = new JLabel("       Ngày tạo: "+c);
    Baiviet.setFont(new Font("Segoe UI", Font.PLAIN, 14)); // Font Arial, kích thước 20

        
    Baiviet.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent  e) {
                    // Tạo JFrame chứa JTextArea
                    JFrame outputFrame = new JFrame("Thông tin bài viết");
                    outputFrame.setTitle("Thông tin bài viết");
                    Dimension preferredSize = new Dimension(650, 450);
                    outputFrame.setPreferredSize(preferredSize);
                    outputFrame.setLocation(400, 250); // Đặt vị trí xuất hiện của JFrame
//                    outputFrame.setLocationRelativeTo(null);

                    // Lấy thông tin từ JSON và hiển thị trên JTextArea
                    JTextArea textArea = new JTextArea();
                    textArea.setEditable(false); // Không cho phép chỉnh sửa
                    textArea.setLineWrap(true); // Cho phép tự động xuống dòng nếu cần
                    textArea.setWrapStyleWord(true); // Đảm bảo không cắt từ
                        textArea.append("Link bài viết    : " + jsonObject.get("Link bài viết") + "\n");
                        textArea.append("Nguồn website  : " + jsonObject.get("Nguồn website") + "\n");
                        textArea.append("Loại bài viết  : " + jsonObject.get("Loại bài viết") + "\n");
                        textArea.append("Tóm tắt bài viết  : " + jsonObject.get("Tóm tắt bài viết (nếu có)") + "\n");
                        textArea.append("Tiêu đề bài viết  : " + jsonObject.get("Tiêu đề bài viết") + "\n");
                        textArea.append("Nội dung bài viết  : " + jsonObject.get("Nội dung chi tiết bài viết") + "\n");
                        textArea.append("Ngày tạo  : " + jsonObject.get("Ngày tạo") + "\n");
                        textArea.append("Tag/Hash tag  : " + jsonObject.get("Tag/Hash tag đi kèm") + "\n");
                        textArea.append("Tên tác giả  : " + jsonObject.get("Tên tác giả nếu có") + "\n");
                        textArea.append("Chuyên mục : " + jsonObject.get("Chuyên mục mà bài viết thuộc về") + "\n\n");                    
                    JScrollPane scrollPane = new JScrollPane(textArea);
                    outputFrame.add(scrollPane, BorderLayout.CENTER);
                    outputFrame.pack();
                    outputFrame.setVisible(true);
            }
                        @Override
            public void mouseEntered(MouseEvent e) {
                // Khi chuột vào, đặt font in đậm và có gạch chân
                Font font = Baiviet.getFont();
                Baiviet.setFont(font.deriveFont(font.getStyle() | Font.BOLD ));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                // Khi chuột ra, đặt lại font bình thường
                Font font = Baiviet.getFont();
                Baiviet.setFont(font.deriveFont(font.getStyle() & ~Font.BOLD ));
            }

            
        });
    
    ContentPane.add(Baiviet);
    ContentPane.add(Tacgia);
    ContentPane.add(Ngay);
    MainPanel.add(ContentPane);
} }
else{
        MainPanel.removeAll();
        MainPanel.revalidate();
        MainPanel.repaint();
        JLabel Nothing = new JLabel("Không có bài viết cần tìm!");
        Nothing.setFont(new Font("Segoe UI", Font.PLAIN, 14)); // Font Arial, kích thước 20
        MainPanel.add(Nothing);
        // Thêm panel mới vào MainPanel và cập nhật hiển thị
        MainPanel.revalidate();
        MainPanel.repaint();
}
}

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        SearchText = new javax.swing.JTextField();
        Home = new javax.swing.JButton();
        Search = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        MainPanel = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Search_API");
        setName("MainFrame"); // NOI18N

        Home.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        Home.setText("Home");
        Home.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                HomeActionPerformed(evt);
            }
        });

        Search.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        Search.setText("Search");
        Search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SearchActionPerformed(evt);
            }
        });

        MainPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout MainPanelLayout = new javax.swing.GroupLayout(MainPanel);
        MainPanel.setLayout(MainPanelLayout);
        MainPanelLayout.setHorizontalGroup(
            MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 697, Short.MAX_VALUE)
        );
        MainPanelLayout.setVerticalGroup(
            MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 430, Short.MAX_VALUE)
        );

        jScrollPane1.setViewportView(MainPanel);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(Home)
                .addGap(18, 18, 18)
                .addComponent(SearchText, javax.swing.GroupLayout.PREFERRED_SIZE, 478, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(Search)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jScrollPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(SearchText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Search)
                    .addComponent(Home))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1))
        );

        getAccessibleContext().setAccessibleName("MFrame");

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void HomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_HomeActionPerformed
        // TODO add your handling code here:
        MainPanel.removeAll();
        MainPanel.revalidate();
        MainPanel.repaint();
        String filepath = "src/main/java/TrendingMethod/Contents.json";        
        ContentPanel(JsonRead(filepath));
        updateScrollPane();
        // Thêm panel mới vào MainPanel và cập nhật hiển thị
        MainPanel.revalidate();
        MainPanel.repaint();
    }//GEN-LAST:event_HomeActionPerformed

    private void SearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SearchActionPerformed
        // TODO add your handling code here:
        MainPanel.removeAll();
        MainPanel.revalidate();
        MainPanel.repaint();
        String Search = SearchText.getText();
        JSONArray ReturnArray = new JSONArray();
        try {
            // Đọc và phân tích tệp JSON
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(new FileReader("src/main/java/TrendingMethod/Contents.json"));
            JSONArray jsonArray = (JSONArray) obj;

            // Duyệt qua mỗi đối tượng JSON trong mảng JSON
            for (Object item : jsonArray) {
                JSONObject jsonObject = (JSONObject) item;

                // Duyệt qua mỗi cặp key-value trong đối tượng JSON
                for (Object key : jsonObject.keySet()) {
                    Object value = jsonObject.get(key);

                    // Kiểm tra xem giá trị có chứa chuỗi cần tìm không
                    if (value instanceof String && ((String) value).contains(Search)) {
                        // Nếu tìm thấy, trả về đối tượng JSON
                        ReturnArray.add(jsonObject);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Nếu không tìm thấy, trả về null
        ContentPanel(ReturnArray);
        updateScrollPane();
        // Thêm panel mới vào MainPanel và cập nhật hiển thị
        MainPanel.revalidate();
        MainPanel.repaint();
    }//GEN-LAST:event_SearchActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Home;
    private static javax.swing.JPanel MainPanel;
    private javax.swing.JButton Search;
    private javax.swing.JTextField SearchText;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
