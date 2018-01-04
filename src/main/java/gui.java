import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollBar;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import java.awt.event.*;
import java.awt.Cursor;
import javax.swing.JTextField;
import java.awt.Dimension;
import java.awt.EventQueue;
import javax.swing.border.LineBorder;
import javax.swing.JButton;

public class gui extends JFrame {

    private Afmcc afmcc;
    private JPanel contentPane;
    JLabel label_4;
    JLabel label_10;
    JLabel label_7;
    private JTextField textField_2;
    private JTextField textField_3;
    private JTextField textField;
    private JTextField textField_1;
    private JTextField textField_4;
    private JTextField textField_5;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
    }

    /**
     * Create the frame.
     */
    public gui( Afmcc afmcc ) {

        this.afmcc=afmcc;

        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {}
            @Override
            public void windowClosing(WindowEvent e) {
                afmcc.cstate.close();
                System.exit(0);
            }
            @Override
            public void windowClosed(WindowEvent e) {}

            @Override
            public void windowIconified(WindowEvent e) {}

            @Override
            public void windowDeiconified(WindowEvent e) {}

            @Override
            public void windowActivated(WindowEvent e) {}

            @Override
            public void windowDeactivated(WindowEvent e) {}
        });
        setBounds(100, 100, 617, 572);
        contentPane = new JPanel();
        contentPane.setBackground(Color.LIGHT_GRAY);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        JLabel lblX = new JLabel("- X");

        label_4 = new JLabel("0");
        label_4.setHorizontalAlignment(SwingConstants.CENTER);

        JScrollBar scrollBar = new JScrollBar();
        scrollBar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                scrollBar.setValue(0);
            }
        });
        scrollBar.addAdjustmentListener(new AdjustmentListener() {
            public void adjustmentValueChanged(AdjustmentEvent e) {
                label_4.setText(String.valueOf(scrollBar.getValue()));
            }
        });
        scrollBar.setCursor(Cursor.getPredefinedCursor(Cursor.E_RESIZE_CURSOR));
        scrollBar.setVisibleAmount(0);
        scrollBar.setUnitIncrement(0);
        scrollBar.setToolTipText("");
        scrollBar.setOrientation(JScrollBar.HORIZONTAL);
        scrollBar.setMinimum(-1000);
        scrollBar.setMaximum(1000);
        scrollBar.setBlockIncrement(0);



        JLabel lblX_1 = new JLabel("+ X");
        lblX_1.setHorizontalAlignment(SwingConstants.RIGHT);

        JButton button_2 = new JButton(">");

        textField_2 = new JTextField();
        textField_2.setToolTipText("steps");
        textField_2.setText("1000");
        textField_2.setMinimumSize(new Dimension(40, 20));
        textField_2.setMaximumSize(new Dimension(40, 20));
        textField_2.setHorizontalAlignment(SwingConstants.CENTER);
        textField_2.setColumns(10);
        textField_2.setBorder(new LineBorder(new Color(0, 0, 0)));

        textField_3 = new JTextField();
        textField_3.setToolTipText("steps");
        textField_3.setText("1000");
        textField_3.setMinimumSize(new Dimension(40, 20));
        textField_3.setMaximumSize(new Dimension(40, 20));
        textField_3.setHorizontalAlignment(SwingConstants.CENTER);
        textField_3.setColumns(10);
        textField_3.setBorder(new LineBorder(new Color(0, 0, 0)));

        JButton button_3 = new JButton("<");

        JLabel lblY = new JLabel("- Y");

        label_7 = new JLabel("0");
        label_7.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel lblY_1 = new JLabel("+ Y");
        lblY_1.setHorizontalAlignment(SwingConstants.RIGHT);

        JScrollBar scrollBar_1 = new JScrollBar();
        scrollBar_1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                scrollBar_1.setValue(0);
            }
        });
        scrollBar_1.addAdjustmentListener(new AdjustmentListener() {
            public void adjustmentValueChanged(AdjustmentEvent e) {
                label_7.setText(String.valueOf(scrollBar_1.getValue()));
            }
        });
        scrollBar_1.setCursor(Cursor.getPredefinedCursor(Cursor.E_RESIZE_CURSOR));
        scrollBar_1.setVisibleAmount(0);
        scrollBar_1.setUnitIncrement(0);
        scrollBar_1.setToolTipText("");
        scrollBar_1.setOrientation(JScrollBar.HORIZONTAL);
        scrollBar_1.setMinimum(-1000);
        scrollBar_1.setMaximum(1000);
        scrollBar_1.setBlockIncrement(0);

        JButton button = new JButton("<");

        textField = new JTextField();
        textField.setToolTipText("steps");
        textField.setText("1000");
        textField.setMinimumSize(new Dimension(40, 20));
        textField.setMaximumSize(new Dimension(40, 20));
        textField.setHorizontalAlignment(SwingConstants.CENTER);
        textField.setColumns(10);
        textField.setBorder(new LineBorder(new Color(0, 0, 0)));

        textField_1 = new JTextField();
        textField_1.setToolTipText("steps");
        textField_1.setText("1000");
        textField_1.setMinimumSize(new Dimension(40, 20));
        textField_1.setMaximumSize(new Dimension(40, 20));
        textField_1.setHorizontalAlignment(SwingConstants.CENTER);
        textField_1.setColumns(10);
        textField_1.setBorder(new LineBorder(new Color(0, 0, 0)));

        JButton button_1 = new JButton(">");

        JLabel label_9 = new JLabel("- Z");

        label_10 = new JLabel("0");
        label_10.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel label_11 = new JLabel("+ Z");
        label_11.setHorizontalAlignment(SwingConstants.RIGHT);

        JScrollBar scrollBar_2 = new JScrollBar();
        scrollBar_2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                scrollBar_2.setValue(0);
            }
        });
        scrollBar_2.addAdjustmentListener(new AdjustmentListener() {
            public void adjustmentValueChanged(AdjustmentEvent e) {
                label_10.setText(String.valueOf(scrollBar_2.getValue()));
            }
        });
        scrollBar_2.setCursor(Cursor.getPredefinedCursor(Cursor.E_RESIZE_CURSOR));
        scrollBar_2.setVisibleAmount(0);
        scrollBar_2.setUnitIncrement(0);
        scrollBar_2.setToolTipText("");
        scrollBar_2.setOrientation(JScrollBar.HORIZONTAL);
        scrollBar_2.setMinimum(-1000);
        scrollBar_2.setMaximum(1000);
        scrollBar_2.setBlockIncrement(0);

        JButton button_4 = new JButton("<");

        textField_4 = new JTextField();
        textField_4.setSize(new Dimension(60, 0));
        textField_4.setPreferredSize(new Dimension(60, 20));
        textField_4.setToolTipText("steps");
        textField_4.setText("1000");
        textField_4.setMinimumSize(new Dimension(60, 20));
        textField_4.setMaximumSize(new Dimension(60, 20));
        textField_4.setHorizontalAlignment(SwingConstants.CENTER);
        textField_4.setColumns(10);
        textField_4.setBorder(new LineBorder(new Color(0, 0, 0)));

        textField_5 = new JTextField();
        textField_5.setToolTipText("steps");
        textField_5.setText("1000");
        textField_5.setMinimumSize(new Dimension(40, 20));
        textField_5.setMaximumSize(new Dimension(40, 20));
        textField_5.setHorizontalAlignment(SwingConstants.CENTER);
        textField_5.setColumns(10);
        textField_5.setBorder(new LineBorder(new Color(0, 0, 0)));

        JButton button_5 = new JButton(">");

        GroupLayout gl_contentPane = new GroupLayout(contentPane);
        gl_contentPane.setHorizontalGroup(
                gl_contentPane.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_contentPane.createSequentialGroup()
                                .addGap(100)
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                                        .addGroup(gl_contentPane.createSequentialGroup()
                                                .addComponent(label_9, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
                                                .addGap(105)
                                                .addComponent(label_10, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE)
                                                .addGap(59)
                                                .addComponent(label_11, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE))
                                        .addComponent(scrollBar_2, GroupLayout.PREFERRED_SIZE, 374, GroupLayout.PREFERRED_SIZE)
                                        .addGroup(gl_contentPane.createSequentialGroup()
                                                .addGap(75)
                                                .addComponent(button_4, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
                                                .addGap(5)
                                                .addComponent(textField_4, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
                                                .addGap(5)
                                                .addComponent(textField_5, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
                                                .addGap(5)
                                                .addComponent(button_5, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(gl_contentPane.createSequentialGroup()
                                                .addComponent(lblY, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
                                                .addGap(105)
                                                .addComponent(label_7, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE)
                                                .addGap(59)
                                                .addComponent(lblY_1, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE))
                                        .addComponent(scrollBar_1, GroupLayout.PREFERRED_SIZE, 374, GroupLayout.PREFERRED_SIZE)
                                        .addGroup(gl_contentPane.createSequentialGroup()
                                                .addGap(75)
                                                .addComponent(button, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
                                                .addGap(5)
                                                .addComponent(textField, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
                                                .addGap(5)
                                                .addComponent(textField_1, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
                                                .addGap(5)
                                                .addComponent(button_1, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(gl_contentPane.createSequentialGroup()
                                                .addComponent(lblX, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
                                                .addGap(105)
                                                .addComponent(label_4, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE)
                                                .addGap(59)
                                                .addComponent(lblX_1, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE))
                                        .addComponent(scrollBar, GroupLayout.PREFERRED_SIZE, 374, GroupLayout.PREFERRED_SIZE)
                                        .addGroup(gl_contentPane.createSequentialGroup()
                                                .addGap(75)
                                                .addComponent(button_3, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
                                                .addGap(5)
                                                .addComponent(textField_3, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
                                                .addGap(5)
                                                .addComponent(textField_2, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
                                                .addGap(5)
                                                .addComponent(button_2, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)))
                                .addGap(117))
        );
        gl_contentPane.setVerticalGroup(
                gl_contentPane.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_contentPane.createSequentialGroup()
                                .addGap(20)
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                                        .addComponent(lblX)
                                        .addComponent(label_4)
                                        .addComponent(lblX_1))
                                .addGap(5)
                                .addComponent(scrollBar, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE)
                                .addGap(5)
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                                        .addComponent(button_3, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE)
                                        .addGroup(gl_contentPane.createSequentialGroup()
                                                .addPreferredGap(ComponentPlacement.RELATED)
                                                .addComponent(textField_3, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(gl_contentPane.createSequentialGroup()
                                                .addPreferredGap(ComponentPlacement.RELATED)
                                                .addComponent(textField_2, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE))
                                        .addComponent(button_2, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE))
                                .addGap(20)
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                                        .addComponent(lblY)
                                        .addComponent(label_7)
                                        .addComponent(lblY_1))
                                .addGap(5)
                                .addComponent(scrollBar_1, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE)
                                .addGap(5)
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                                        .addComponent(button, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE)
                                        .addGroup(gl_contentPane.createSequentialGroup()
                                                .addPreferredGap(ComponentPlacement.RELATED)
                                                .addComponent(textField, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(gl_contentPane.createSequentialGroup()
                                                .addPreferredGap(ComponentPlacement.RELATED)
                                                .addComponent(textField_1, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE))
                                        .addComponent(button_1, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                                        .addComponent(label_9)
                                        .addComponent(label_10)
                                        .addComponent(label_11))
                                .addGap(5)
                                .addComponent(scrollBar_2, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE)
                                .addGap(5)
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                                        .addComponent(button_4, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE)
                                        .addGroup(gl_contentPane.createSequentialGroup()
                                                .addPreferredGap(ComponentPlacement.RELATED)
                                                .addComponent(textField_4, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(gl_contentPane.createSequentialGroup()
                                                .addPreferredGap(ComponentPlacement.RELATED)
                                                .addComponent(textField_5, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE))
                                        .addComponent(button_5, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE))
                                .addGap(282))
        );
        contentPane.setLayout(gl_contentPane);
    }
}