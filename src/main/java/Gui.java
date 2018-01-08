import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;

public class Gui extends JFrame {

    private Afmcc afmcc;
    private JPanel contentPane;
    private JLabel xLabel, yLabel, zLabel;
    private JTextField xSteps, ySteps, zSteps;
    private JTextField xVel, yVel, zVel;

    //todo: make tooltip of vel and step fields permanently visible
    //todo: have a set vel and steps event triggered by a value update on their JTextField so gamepad can use these values
    //todo: make scrollbars react to the gamepad triggered movement (use repaintGUI?)
    //todo: set default step value at 100 (default stepvel is to remain at 1)

    /**
     * Create the frame.
     */
    public Gui(Afmcc arg) {

        afmcc=arg;

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


        /*
            X section
        */

        JLabel nX = new JLabel("- X");

        xLabel = new JLabel("0");
        xLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel pX = new JLabel("+ X");
        pX.setHorizontalAlignment(SwingConstants.RIGHT);

        xSteps = new JTextField();
        xSteps.setToolTipText("Steps");
        xSteps.setText("1");
        xSteps.setMinimumSize(new Dimension(40, 20));
        xSteps.setMaximumSize(new Dimension(40, 20));
        xSteps.setHorizontalAlignment(SwingConstants.CENTER);
        xSteps.setColumns(10);
        xSteps.setBorder(new LineBorder(new Color(0, 0, 0)));

        xVel = new JTextField();
        xVel.setToolTipText("Velocity");
        xVel.setText("1");
        xVel.setMinimumSize(new Dimension(40, 20));
        xVel.setMaximumSize(new Dimension(40, 20));
        xVel.setHorizontalAlignment(SwingConstants.CENTER);
        xVel.setColumns(10);
        xVel.setBorder(new LineBorder(new Color(0, 0, 0)));

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
                    System.out.println("[GUI] X: " + xScrollBar.getValue());
                    xLabel.setText(String.valueOf(xScrollBar.getValue()));
                    if(xScrollBar.getValue()!=0) afmcc.bq.add(new Qobj(xScrollBar.getValue(), 0, 0, false, false, false, false, false, false, false));
                    else afmcc.bq.add(new Qobj(0, 0, 0, false, false, false, false, false, false, true));
                } else{
                    System.out.println("[GUI] X bar stop");
                    xLabel.setText(String.valueOf(xScrollBar.getValue()));
                }

            }
        });

        JButton xButton1 = new JButton(">");
        xButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    int xS = Integer.parseInt(xSteps.getText());
                    int xV = Integer.parseInt(xVel.getText());
                    if(xV>0 && xS>0 && xV<=1000 && xS<=1000000) {
                        System.out.println("[GUI] +xVel: " + xV +", +xSteps: " + xS);
                        afmcc.steps[0] = xS;
                        afmcc.stepVel[0] = xV;
                        afmcc.bq.add(new Qobj(0, 0, 0, false, true, false, false, false, false, false));
                    }else System.out.println("[GUI] +x invalid args!");
                } catch (NumberFormatException exc) {
                    // not an integer/s
                    System.out.println("[GUI] +x invalid args!");
                }
            }
        });

        JButton xButton2 = new JButton("<");
        xButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    int xS = Integer.parseInt(xSteps.getText());
                    int xV = Integer.parseInt(xVel.getText());
                    if( xV>0 && xS>0 && xV<=1000 && xS<=1000000) {
                        System.out.println("[GUI] -xVel: " + xV +" -xSteps: " + xS);
                        afmcc.steps[0] = xS;
                        afmcc.stepVel[0] = xV;
                        afmcc.bq.add(new Qobj(0, 0, 0, true, false, false, false, false, false, false));
                    }else System.out.println("[GUI] -x invalid args!");
                } catch (NumberFormatException exc) {
                    // not an integer/s
                    System.out.println("[GUI] -x invalid args!");
                }
            }
        });


        /*
            Y Section
        */

        JLabel nY = new JLabel("- Y");

        yLabel = new JLabel("0");
        yLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel pY = new JLabel("+ Y");
        pY.setHorizontalAlignment(SwingConstants.RIGHT);

        yVel = new JTextField();
        yVel.setToolTipText("Velocity");
        yVel.setText("1");
        yVel.setMinimumSize(new Dimension(40, 20));
        yVel.setMaximumSize(new Dimension(40, 20));
        yVel.setHorizontalAlignment(SwingConstants.CENTER);
        yVel.setColumns(10);
        yVel.setBorder(new LineBorder(new Color(0, 0, 0)));

        ySteps = new JTextField();
        ySteps.setToolTipText("Steps");
        ySteps.setText("1");
        ySteps.setMinimumSize(new Dimension(40, 20));
        ySteps.setMaximumSize(new Dimension(40, 20));
        ySteps.setHorizontalAlignment(SwingConstants.CENTER);
        ySteps.setColumns(10);
        ySteps.setBorder(new LineBorder(new Color(0, 0, 0)));

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
                    System.out.println("[GUI] Y: " + yScrollBar.getValue());
                    yLabel.setText(String.valueOf(yScrollBar.getValue()));
                    if(yScrollBar.getValue()!=0) afmcc.bq.add(new Qobj(0, yScrollBar.getValue(), 0, false, false, false, false, false, false, false));
                    else afmcc.bq.add(new Qobj(0, 0, 0, false, false, false, false, false, false, true));

                } else{
                    System.out.println("[GUI] Y bar stop");
                    yLabel.setText(String.valueOf(yScrollBar.getValue()));
                }

            }
        });

        JButton yButton1 = new JButton(">");
        yButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    int yS = Integer.parseInt(ySteps.getText());
                    int yV = Integer.parseInt(yVel.getText());
                    if( yV>0 && yS>0 && yV<=1000 && yS<=1000000) {
                        System.out.println("[GUI] +yVel: " + yV +", +ySteps: " + yS);
                        afmcc.steps[1] = yS;
                        afmcc.stepVel[1] = yV;
                        afmcc.bq.add(new Qobj(0, 0, 0, false, false, false, true, false, false, false));
                    }else System.out.println("[GUI] +y invalid args!");
                } catch (NumberFormatException exc) {
                    // not an integer/s
                    System.out.println("[GUI] +y invalid args!");
                }

            }
        });

        JButton yButton2 = new JButton("<");
        yButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    int yS = Integer.parseInt(ySteps.getText());
                    int yV = Integer.parseInt(yVel.getText());
                    if( yV>0 && yS>0 && yV<=1000 && yS<=1000000) {
                        System.out.println("[GUI] -yVel: " + yV +", -ySteps: " + yS);
                        afmcc.steps[1] = yS;
                        afmcc.stepVel[1] = yV;
                        afmcc.bq.add(new Qobj(0, 0, 0, false, false, true, false, false, false, false));
                    }else System.out.println("[GUI] -y invalid args!");
                } catch (NumberFormatException exc) {
                    // not an integer/s
                    System.out.println("[GUI] -y invalid args!");
                }
            }
        });


        /*
            Z section
        */

        JLabel nZ = new JLabel("- Z");

        zLabel = new JLabel("0");
        zLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel pZ = new JLabel("+ Z");
        pZ.setHorizontalAlignment(SwingConstants.RIGHT);

        zVel = new JTextField();
        zVel.setSize(new Dimension(60, 0));
        zVel.setPreferredSize(new Dimension(60, 20));
        zVel.setToolTipText("Velocity");
        zVel.setText("1");
        zVel.setMinimumSize(new Dimension(60, 20));
        zVel.setMaximumSize(new Dimension(60, 20));
        zVel.setHorizontalAlignment(SwingConstants.CENTER);
        zVel.setColumns(10);
        zVel.setBorder(new LineBorder(new Color(0, 0, 0)));

        zSteps = new JTextField();
        zSteps.setToolTipText("Steps");
        zSteps.setText("1");
        zSteps.setMinimumSize(new Dimension(40, 20));
        zSteps.setMaximumSize(new Dimension(40, 20));
        zSteps.setHorizontalAlignment(SwingConstants.CENTER);
        zSteps.setColumns(10);
        zSteps.setBorder(new LineBorder(new Color(0, 0, 0)));

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
                    System.out.println("[GUI] Z: " + zScrollBar.getValue());
                    zLabel.setText(String.valueOf(zScrollBar.getValue()));
                    if(zScrollBar.getValue()!=0) afmcc.bq.add(new Qobj(0, 0, zScrollBar.getValue(), false, false, false, false, false, false, false));
                    else afmcc.bq.add(new Qobj(0, 0, 0, false, false, false, false, false, false, true));
                } else{
                    System.out.println("[GUI] Z bar stop");
                    zLabel.setText(String.valueOf(zScrollBar.getValue()));
                }
            }
        });

        JButton zButton1 = new JButton(">");
        zButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //procar func c CU30Step
                try{
                    int zS = Integer.parseInt(zSteps.getText());
                    int zV = Integer.parseInt(zVel.getText());
                    if( zV>0 && zS>0 && zV<=1000 && zS<=1000000) {
                        System.out.println("[GUI] +zVel: " + zV +", +zSteps: " + zS);
                        afmcc.steps[2] = zS;
                        afmcc.stepVel[2] = zV;
                        afmcc.bq.add(new Qobj(0, 0, 0, false, false, false, false, false, true, false));
                    }else System.out.println("[GUI] +z invalid args!");
                } catch (NumberFormatException exc) {
                    // not an integer/s
                    System.out.println("[GUI] +z invalid args!");
                }
            }
        });

        JButton zButton2 = new JButton("<");
        zButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    int zS = Integer.parseInt(zSteps.getText());
                    int zV = Integer.parseInt(zVel.getText());
                    if( zV>0 && zS>0 && zV<=1000 && zS<=1000000) {
                        System.out.println("[GUI] -zVel: " + zV +", -zSteps: " + zS);
                        afmcc.steps[2] = zS;
                        afmcc.stepVel[2] = zV;
                        afmcc.bq.add(new Qobj(0, 0, 0, false, false, false, false, true, false, false));
                    }else System.out.println("[GUI] -z invalid args!");
                } catch (NumberFormatException exc) {
                    // not an integer/s
                    System.out.println("[GUI] -z invalid args!");
                }
            }
        });


        GroupLayout gl_contentPane = new GroupLayout(contentPane);
        gl_contentPane.setHorizontalGroup(
                gl_contentPane.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_contentPane.createSequentialGroup()
                                .addGap(100)
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                                        .addGroup(gl_contentPane.createSequentialGroup()
                                                .addComponent(nZ, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
                                                .addGap(105)
                                                .addComponent(zLabel, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE)
                                                .addGap(59)
                                                .addComponent(pZ, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE))
                                        .addComponent(zScrollBar, GroupLayout.PREFERRED_SIZE, 374, GroupLayout.PREFERRED_SIZE)
                                        .addGroup(gl_contentPane.createSequentialGroup()
                                                .addGap(75)
                                                .addComponent(zButton2, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
                                                .addGap(5)
                                                .addComponent(zVel, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
                                                .addGap(5)
                                                .addComponent(zSteps, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
                                                .addGap(5)
                                                .addComponent(zButton1, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(gl_contentPane.createSequentialGroup()
                                                .addComponent(nY, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
                                                .addGap(105)
                                                .addComponent(yLabel, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE)
                                                .addGap(59)
                                                .addComponent(pY, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE))
                                        .addComponent(yScrollBar, GroupLayout.PREFERRED_SIZE, 374, GroupLayout.PREFERRED_SIZE)
                                        .addGroup(gl_contentPane.createSequentialGroup()
                                                .addGap(75)
                                                .addComponent(yButton2, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
                                                .addGap(5)
                                                .addComponent(yVel, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
                                                .addGap(5)
                                                .addComponent(ySteps, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
                                                .addGap(5)
                                                .addComponent(yButton1, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(gl_contentPane.createSequentialGroup()
                                                .addComponent(nX, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
                                                .addGap(105)
                                                .addComponent(xLabel, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE)
                                                .addGap(59)
                                                .addComponent(pX, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE))
                                        .addComponent(xScrollBar, GroupLayout.PREFERRED_SIZE, 374, GroupLayout.PREFERRED_SIZE)
                                        .addGroup(gl_contentPane.createSequentialGroup()
                                                .addGap(75)
                                                .addComponent(xButton2, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
                                                .addGap(5)
                                                .addComponent(xVel, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
                                                .addGap(5)
                                                .addComponent(xSteps, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
                                                .addGap(5)
                                                .addComponent(xButton1, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)))
                                .addGap(117))
        );
        gl_contentPane.setVerticalGroup(
                gl_contentPane.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_contentPane.createSequentialGroup()
                                .addGap(20)
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                                        .addComponent(nX)
                                        .addComponent(xLabel)
                                        .addComponent(pX))
                                .addGap(5)
                                .addComponent(xScrollBar, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE)
                                .addGap(5)
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                                        .addComponent(xButton2, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE)
                                        .addGroup(gl_contentPane.createSequentialGroup()
                                                .addPreferredGap(ComponentPlacement.RELATED)
                                                .addComponent(xVel, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(gl_contentPane.createSequentialGroup()
                                                .addPreferredGap(ComponentPlacement.RELATED)
                                                .addComponent(xSteps, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE))
                                        .addComponent(xButton1, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE))
                                .addGap(20)
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                                        .addComponent(nY)
                                        .addComponent(yLabel)
                                        .addComponent(pY))
                                .addGap(5)
                                .addComponent(yScrollBar, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE)
                                .addGap(5)
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                                        .addComponent(yButton2, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE)
                                        .addGroup(gl_contentPane.createSequentialGroup()
                                                .addPreferredGap(ComponentPlacement.RELATED)
                                                .addComponent(yVel, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(gl_contentPane.createSequentialGroup()
                                                .addPreferredGap(ComponentPlacement.RELATED)
                                                .addComponent(ySteps, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE))
                                        .addComponent(yButton1, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                                        .addComponent(nZ)
                                        .addComponent(zLabel)
                                        .addComponent(pZ))
                                .addGap(5)
                                .addComponent(zScrollBar, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE)
                                .addGap(5)
                                .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
                                        .addComponent(zButton2, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE)
                                        .addGroup(gl_contentPane.createSequentialGroup()
                                                .addPreferredGap(ComponentPlacement.RELATED)
                                                .addComponent(zVel, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(gl_contentPane.createSequentialGroup()
                                                .addPreferredGap(ComponentPlacement.RELATED)
                                                .addComponent(zSteps, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE))
                                        .addComponent(zButton1, GroupLayout.PREFERRED_SIZE, 15, GroupLayout.PREFERRED_SIZE))
                                .addGap(282))
        );
        contentPane.setLayout(gl_contentPane);
    }
}