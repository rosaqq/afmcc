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
import javax.swing.border.LineBorder;
import javax.swing.JButton;

public class Gui extends JFrame {

    private Afmcc afmcc;
    private JPanel contentPane;
    JLabel xLabel;
    JLabel yLabel;
    JLabel zLabel;
    private JTextField xSteps;
    private JTextField xVel;
    private JTextField yVel;
    private JTextField ySteps;
    private JTextField zVel;
    private JTextField zSteps;

    public static void main(String[] args) {
    }

    /**
     * Create the frame.
     */
    public Gui(Afmcc afmcc ) {

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

        xLabel = new JLabel("0");
        xLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JScrollBar xScrollBar = new JScrollBar();
        xScrollBar.setCursor(Cursor.getPredefinedCursor(Cursor.E_RESIZE_CURSOR));
        xScrollBar.setVisibleAmount(0);
        xScrollBar.setUnitIncrement(0);
        xScrollBar.setToolTipText("");
        xScrollBar.setOrientation(JScrollBar.HORIZONTAL);
        xScrollBar.setMinimum(-1000);
        xScrollBar.setMaximum(1000);
        xScrollBar.setBlockIncrement(0);
        xScrollBar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                xScrollBar.setValue(0);
            }
        });
        xScrollBar.addAdjustmentListener(new AdjustmentListener() {
            public void adjustmentValueChanged(AdjustmentEvent e) {
                if(e.getValueIsAdjusting() || (!e.getValueIsAdjusting() && xScrollBar.getValue()==0) ) {
                    System.out.println("X adjustment - " + xScrollBar.getValue());
                    xLabel.setText(String.valueOf(xScrollBar.getValue()));
                    //procar func em c

                } else{
                    System.out.println("aint adjusting so  won't move!");
                    xLabel.setText(String.valueOf(xScrollBar.getValue()));
                }

            }
        });

        JLabel lblX_1 = new JLabel("+ X");
        lblX_1.setHorizontalAlignment(SwingConstants.RIGHT);

        JButton button_2 = new JButton(">");
        button_2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //procar func c CU30Step
                //afmcc.bq.offer(new qObj("step",1,xSteps,xVel));
            }
        });

        xSteps = new JTextField();
        xSteps.setToolTipText("steps");
        xSteps.setText("1000");
        xSteps.setMinimumSize(new Dimension(40, 20));
        xSteps.setMaximumSize(new Dimension(40, 20));
        xSteps.setHorizontalAlignment(SwingConstants.CENTER);
        xSteps.setColumns(10);
        xSteps.setBorder(new LineBorder(new Color(0, 0, 0)));

        xVel = new JTextField();
        xVel.setToolTipText("velocity");
        xVel.setText("1000");
        xVel.setMinimumSize(new Dimension(40, 20));
        xVel.setMaximumSize(new Dimension(40, 20));
        xVel.setHorizontalAlignment(SwingConstants.CENTER);
        xVel.setColumns(10);
        xVel.setBorder(new LineBorder(new Color(0, 0, 0)));

        JButton button_3 = new JButton("<");
        button_3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //procar func c CU30Step
                //afmcc.bq.offer(new qObj("step",1,xSteps,-xVel));
            }
        });

        JLabel lblY = new JLabel("- Y");

        yLabel = new JLabel("0");
        yLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel lblY_1 = new JLabel("+ Y");
        lblY_1.setHorizontalAlignment(SwingConstants.RIGHT);

        JScrollBar yScrollBar = new JScrollBar();
        yScrollBar.setCursor(Cursor.getPredefinedCursor(Cursor.E_RESIZE_CURSOR));
        yScrollBar.setVisibleAmount(0);
        yScrollBar.setUnitIncrement(0);
        yScrollBar.setToolTipText("");
        yScrollBar.setOrientation(JScrollBar.HORIZONTAL);
        yScrollBar.setMinimum(-1000);
        yScrollBar.setMaximum(1000);
        yScrollBar.setBlockIncrement(0);
        yScrollBar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                yScrollBar.setValue(0);
            }
        });
        yScrollBar.addAdjustmentListener(new AdjustmentListener() {
            public void adjustmentValueChanged(AdjustmentEvent e) {

                if(e.getValueIsAdjusting() || (!e.getValueIsAdjusting() && yScrollBar.getValue()==0) ) {
                    System.out.println("Y adjustment - " + yScrollBar.getValue());
                    yLabel.setText(String.valueOf(yScrollBar.getValue()));
                    //procar func em c

                } else{
                    System.out.println("aint adjusting so won't move!");
                    yLabel.setText(String.valueOf(yScrollBar.getValue()));
                }

            }
        });


        JButton button = new JButton("<");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //procar func c CU30Step
                //afmcc.bq.offer(new qObj("step",2,ySteps,-yVel));
            }
        });

        yVel = new JTextField();
        yVel.setToolTipText("velocity");
        yVel.setText("1000");
        yVel.setMinimumSize(new Dimension(40, 20));
        yVel.setMaximumSize(new Dimension(40, 20));
        yVel.setHorizontalAlignment(SwingConstants.CENTER);
        yVel.setColumns(10);
        yVel.setBorder(new LineBorder(new Color(0, 0, 0)));

        ySteps = new JTextField();
        ySteps.setToolTipText("Steps");
        ySteps.setText("0");
        ySteps.setMinimumSize(new Dimension(40, 20));
        ySteps.setMaximumSize(new Dimension(40, 20));
        ySteps.setHorizontalAlignment(SwingConstants.CENTER);
        ySteps.setColumns(10);
        ySteps.setBorder(new LineBorder(new Color(0, 0, 0)));

        JButton button_1 = new JButton(">");
        button_1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    int yS = Integer.parseInt(ySteps.getText());
                    int yV = Integer.parseInt(yVel.getText());
                    if( yV>0 && yS>0 && yV<=1000 && yS<=1000000) {
                        System.out.println("vel : " + yV +" steps : " + yS);
                        afmcc.bq.offer(new Qobj("step",2,Integer.parseInt(ySteps.getText()),Integer.parseInt(yVel.getText())));
                    }else System.out.println("invalid args!");
                } catch (NumberFormatException exc) {
                    // not an integer/s
                    System.out.println("invalid args!");
                }

            }
        });

        JLabel label_9 = new JLabel("- Z");

        zLabel = new JLabel("0");
        zLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel label_11 = new JLabel("+ Z");
        label_11.setHorizontalAlignment(SwingConstants.RIGHT);

        JScrollBar zScrollBar = new JScrollBar();
        zScrollBar.setCursor(Cursor.getPredefinedCursor(Cursor.E_RESIZE_CURSOR));
        zScrollBar.setVisibleAmount(0);
        zScrollBar.setUnitIncrement(0);
        zScrollBar.setToolTipText("");
        zScrollBar.setOrientation(JScrollBar.HORIZONTAL);
        zScrollBar.setMinimum(-1000);
        zScrollBar.setMaximum(1000);
        zScrollBar.setBlockIncrement(0);
        zScrollBar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                zScrollBar.setValue(0);
            }
        });
        zScrollBar.addAdjustmentListener(new AdjustmentListener() {
            public void adjustmentValueChanged(AdjustmentEvent e) {
                if(e.getValueIsAdjusting() || (!e.getValueIsAdjusting() && zScrollBar.getValue()==0) ) {
                    System.out.println("Z adjustment - " + zScrollBar.getValue());
                    zLabel.setText(String.valueOf(zScrollBar.getValue()));
                    //procar func em c

                } else{
                    System.out.println("aint adjusting so won't move!");
                    zLabel.setText(String.valueOf(zScrollBar.getValue()));
                }
            }
        });

        JButton button_4 = new JButton("<");
        button_4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //procar func c CU30Step
                //afmcc.bq.offer(new qObj("step",1,zSteps,-zVel));
            }
        });

        zVel = new JTextField();
        zVel.setSize(new Dimension(60, 0));
        zVel.setPreferredSize(new Dimension(60, 20));
        zVel.setToolTipText("Velocity");
        zVel.setText("1000");
        zVel.setMinimumSize(new Dimension(60, 20));
        zVel.setMaximumSize(new Dimension(60, 20));
        zVel.setHorizontalAlignment(SwingConstants.CENTER);
        zVel.setColumns(10);
        zVel.setBorder(new LineBorder(new Color(0, 0, 0)));

        zSteps = new JTextField();
        zSteps.setToolTipText("Steps");
        zSteps.setText("1000");
        zSteps.setMinimumSize(new Dimension(40, 20));
        zSteps.setMaximumSize(new Dimension(40, 20));
        zSteps.setHorizontalAlignment(SwingConstants.CENTER);
        zSteps.setColumns(10);
        zSteps.setBorder(new LineBorder(new Color(0, 0, 0)));

        JButton button_5 = new JButton(">");
        button_5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //procar func c CU30Step
                //afmcc.bq.offer(new qObj("step",3,zSteps,zVel));
            }
        });


        GroupLayout gl_contentPane = new GroupLayout(contentPane);
        gl_contentPane.setHorizontalGroup(
                gl_contentPane.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_contentPane.createSequentialGroup()
                                .addGap(100)
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                                        .addGroup(gl_contentPane.createSequentialGroup()
                                                .addComponent(label_9, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
                                                .addGap(105)
                                                .addComponent(zLabel, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE)
                                                .addGap(59)
                                                .addComponent(label_11, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE))
                                        .addComponent(zScrollBar, GroupLayout.PREFERRED_SIZE, 374, GroupLayout.PREFERRED_SIZE)
                                        .addGroup(gl_contentPane.createSequentialGroup()
                                                .addGap(75)
                                                .addComponent(button_4, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
                                                .addGap(5)
                                                .addComponent(zVel, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
                                                .addGap(5)
                                                .addComponent(zSteps, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
                                                .addGap(5)
                                                .addComponent(button_5, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(gl_contentPane.createSequentialGroup()
                                                .addComponent(lblY, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
                                                .addGap(105)
                                                .addComponent(yLabel, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE)
                                                .addGap(59)
                                                .addComponent(lblY_1, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE))
                                        .addComponent(yScrollBar, GroupLayout.PREFERRED_SIZE, 374, GroupLayout.PREFERRED_SIZE)
                                        .addGroup(gl_contentPane.createSequentialGroup()
                                                .addGap(75)
                                                .addComponent(button, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
                                                .addGap(5)
                                                .addComponent(yVel, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
                                                .addGap(5)
                                                .addComponent(ySteps, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
                                                .addGap(5)
                                                .addComponent(button_1, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(gl_contentPane.createSequentialGroup()
                                                .addComponent(lblX, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
                                                .addGap(105)
                                                .addComponent(xLabel, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE)
                                                .addGap(59)
                                                .addComponent(lblX_1, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE))
                                        .addComponent(xScrollBar, GroupLayout.PREFERRED_SIZE, 374, GroupLayout.PREFERRED_SIZE)
                                        .addGroup(gl_contentPane.createSequentialGroup()
                                                .addGap(75)
                                                .addComponent(button_3, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
                                                .addGap(5)
                                                .addComponent(xVel, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
                                                .addGap(5)
                                                .addComponent(xSteps, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
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
                                        .addComponent(xLabel)
                                        .addComponent(lblX_1))
                                .addGap(5)
                                .addComponent(xScrollBar, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE)
                                .addGap(5)
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                                        .addComponent(button_3, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE)
                                        .addGroup(gl_contentPane.createSequentialGroup()
                                                .addPreferredGap(ComponentPlacement.RELATED)
                                                .addComponent(xVel, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(gl_contentPane.createSequentialGroup()
                                                .addPreferredGap(ComponentPlacement.RELATED)
                                                .addComponent(xSteps, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE))
                                        .addComponent(button_2, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE))
                                .addGap(20)
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                                        .addComponent(lblY)
                                        .addComponent(yLabel)
                                        .addComponent(lblY_1))
                                .addGap(5)
                                .addComponent(yScrollBar, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE)
                                .addGap(5)
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                                        .addComponent(button, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE)
                                        .addGroup(gl_contentPane.createSequentialGroup()
                                                .addPreferredGap(ComponentPlacement.RELATED)
                                                .addComponent(yVel, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(gl_contentPane.createSequentialGroup()
                                                .addPreferredGap(ComponentPlacement.RELATED)
                                                .addComponent(ySteps, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE))
                                        .addComponent(button_1, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                                        .addComponent(label_9)
                                        .addComponent(zLabel)
                                        .addComponent(label_11))
                                .addGap(5)
                                .addComponent(zScrollBar, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE)
                                .addGap(5)
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                                        .addComponent(button_4, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE)
                                        .addGroup(gl_contentPane.createSequentialGroup()
                                                .addPreferredGap(ComponentPlacement.RELATED)
                                                .addComponent(zVel, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(gl_contentPane.createSequentialGroup()
                                                .addPreferredGap(ComponentPlacement.RELATED)
                                                .addComponent(zSteps, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE))
                                        .addComponent(button_5, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE))
                                .addGap(282))
        );
        contentPane.setLayout(gl_contentPane);
    }
}