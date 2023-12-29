package com.proyecto.gui;
/*
 * @author danny
 */
import javax.swing.JOptionPane;
import java.awt.*;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class InterfazGrafica extends javax.swing.JFrame {

    private int numeroDeJugadores;
    private JPanel[][] tablero;

    public InterfazGrafica() {

        System.setProperty("awt.useSystemAAFontSettings", "on");
        System.setProperty("swing.aatext", "true");

        askNumberOfPlayers();  // Preguntar al usuario por el número de jugadores
        initComponents(); // Inicializar la interfaz gráfica después de obtener el número de jugadores
        createTablero(); // Crea el tablero dependiendo del numero de jugadores
    }

    private void createTablero() {
        int gridSize = 7 + numeroDeJugadores - 1; // Calcula el tamaño del tablero según el número de jugadores
        tablero = new JPanel[gridSize][gridSize];

        // Configura el tamaño general del tablero y su diseño
        Mapa.setLayout(new GridLayout(gridSize, gridSize));

        // Tamaño de cada casilla
        int casillaSize = 0;
        if (numeroDeJugadores == 1) {
            casillaSize = 80;
        } else if (numeroDeJugadores == 2) {
            casillaSize = 70;
        } else if (numeroDeJugadores == 3) {
            casillaSize = 62;
        } else if (numeroDeJugadores == 4) {
            casillaSize = 56;
        }

        // Crea las casillas y las agrega al tablero
        for (int row = 0; row < gridSize; row++) {
            for (int col = 0; col < gridSize; col++) {
                JPanel casilla = new JPanel();
                casilla.setLayout(null);
                casilla.setPreferredSize(new Dimension(casillaSize, casillaSize)); // Establece el tamaño de cada casilla

                // Añadimos imagen de textura
                ImageIcon originalIcon = new ImageIcon(getClass().getResource("/com/proyecto/imagenes/texturacuadrada.jpg"));
                Image originalImage = originalIcon.getImage();

                // Escalamos la imagen al tamaño de la casilla
                Image scaledImage = originalImage.getScaledInstance(casillaSize, casillaSize, Image.SCALE_SMOOTH);
                ImageIcon scaledIcon = new ImageIcon(scaledImage);
                JLabel label1 = new JLabel(scaledIcon);
                label1.setBounds(0, 5, casillaSize-1, casillaSize-1); // Posición y tamaño de la primera imagen

                casilla.add(label1);
                casilla.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                tablero[row][col] = casilla;
                Mapa.add(casilla);
            }
        }
        // Centra el tablero en el panel Mapa
        Mapa.revalidate();
        Mapa.repaint();
    }
    private void askNumberOfPlayers() {
        boolean numeroValido = false;

        while (!numeroValido) {
            try {
                String input = JOptionPane.showInputDialog("Introduce el número de jugadores:");
                numeroDeJugadores = Integer.parseInt(input);

                if (numeroDeJugadores >= 1 && numeroDeJugadores <= 4) {
                    numeroValido = true;
                } else {
                    JOptionPane.showMessageDialog(this, "Número de jugadores no válido. Intenta de nuevo.");
                }
            } catch (NumberFormatException | NullPointerException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Por favor, introduce un número válido.");
            }
        }
    }

    private ImageIcon scaleImageIcon(String imagePath, int width, int height) {
        ImageIcon originalIcon = new ImageIcon(getClass().getResource(imagePath));
        Image originalImage = originalIcon.getImage();
        Image scaledImage = originalImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(scaledImage);
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        Controles = new javax.swing.JPanel();
        Up = new javax.swing.JButton();
        Down = new javax.swing.JButton();
        Left = new javax.swing.JButton();
        Right = new javax.swing.JButton();
        Search = new javax.swing.JButton();
        Attack = new javax.swing.JButton();
        ChangeWeapon = new javax.swing.JButton();
        Nothing = new javax.swing.JButton();
        Moverse = new javax.swing.JLabel();
        Buscar = new javax.swing.JLabel();
        Atacar = new javax.swing.JLabel();
        ElegirArma = new javax.swing.JLabel();
        Nada = new javax.swing.JLabel();
        Auxiliar = new javax.swing.JPanel();
        Mapa = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(0, 0, 0));
        // Intento de poner una imagen de fondo en el panel controles
        Controles = new ImagePanel("/com/proyecto/imagenes/fondo1.png");
        //Controles.setBackground(new java.awt.Color(102, 102, 102));

        Up.setBackground(new java.awt.Color(0, 0, 0));
        Up.setIcon(scaleImageIcon("/com/proyecto/imagenes/flechaup.png", 60, 60)); // Ajusta el tamaño según tus necesidades
        Up.setBorder(null);
        Up.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UpActionPerformed(evt);
            }
        });

        Down.setBackground(new java.awt.Color(0, 0, 0));
        Down.setIcon(scaleImageIcon("/com/proyecto/imagenes/flechadown.png", 60, 60));
        Down.setBorder(null);
        Down.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {

                DownActionPerformed(evt);
            }
        });

        Left.setBackground(new java.awt.Color(0, 0, 0));
        Left.setIcon(scaleImageIcon("/com/proyecto/imagenes/flechaizq.png", 60, 60));
        Left.setBorder(null);
        Left.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {

                LeftActionPerformed(evt);
            }
        });

        Right.setBackground(new java.awt.Color(0, 0, 0));
        Right.setIcon(scaleImageIcon("/com/proyecto/imagenes/flechader.png", 60, 60));
        Right.setBorder(null);
        Right.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {

                RightActionPerformed(evt);
            }
        });

        Search.setBackground(new java.awt.Color(102, 102, 102));
        Search.setIcon(scaleImageIcon("/com/proyecto/imagenes/buscarx.png", 60, 80));
        Search.setBorder(null);
        Search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {

                SearchActionPerformed(evt);
            }
        });

        Attack.setBackground(new java.awt.Color(102, 102, 102));
        Attack.setIcon(scaleImageIcon("/com/proyecto/imagenes/Atacarx.png", 80, 80));
        Attack.setBorder(null);
        Attack.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        Attack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {

                AttackActionPerformed(evt);
            }
        });

        ChangeWeapon.setBackground(new java.awt.Color(102, 102, 102));
        ChangeWeapon.setIcon(scaleImageIcon("/com/proyecto/imagenes/Cambiar2.png", 80, 80));
        ChangeWeapon.setBorder(null);
        ChangeWeapon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {

                ChangeWeaponActionPerformed(evt);
            }
        });

        Nothing.setBackground(new java.awt.Color(102, 102, 102));
        Nothing.setIcon(scaleImageIcon("/com/proyecto/imagenes/nada2.png", 70, 70));
        Nothing.setBorder(null);
        Nothing.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {

                NothingActionPerformed(evt);
            }
        });

        Moverse.setText("Moverse");

        Buscar.setText("Buscar");

        Atacar.setText("Atacar");

        ElegirArma.setText("Elegir arma");

        Nada.setText("No hacer nada");

        javax.swing.GroupLayout ControlesLayout = new javax.swing.GroupLayout(Controles);
        Controles.setLayout(ControlesLayout);
        ControlesLayout.setHorizontalGroup(
            ControlesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ControlesLayout.createSequentialGroup()
                .addGroup(ControlesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ControlesLayout.createSequentialGroup()
                        .addGap(66, 66, 66)
                        .addGroup(ControlesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(ControlesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(Search, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(ControlesLayout.createSequentialGroup()
                                    .addGap(59, 59, 59)
                                    .addComponent(Buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(Attack, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(ControlesLayout.createSequentialGroup()
                                    .addGap(60, 60, 60)
                                    .addComponent(Atacar, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(ControlesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(ControlesLayout.createSequentialGroup()
                                    .addGap(62, 62, 62)
                                    .addGroup(ControlesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(Down, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(Up, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(ControlesLayout.createSequentialGroup()
                                    .addComponent(Left, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(Moverse)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(Right, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(ControlesLayout.createSequentialGroup()
                        .addGap(112, 112, 112)
                        .addGroup(ControlesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(ControlesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(Nada)
                                .addComponent(Nothing))
                            .addGroup(ControlesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(ChangeWeapon, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(ControlesLayout.createSequentialGroup()
                                    .addComponent(ElegirArma, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(15, 15, 15))))))
                .addContainerGap(77, Short.MAX_VALUE))
        );
        ControlesLayout.setVerticalGroup(
            ControlesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ControlesLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(Up, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(ControlesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ControlesLayout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addGroup(ControlesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Left, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Right, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(ControlesLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(Moverse)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Down, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(Search)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Buscar)
                .addGap(18, 18, 18)
                .addComponent(Attack)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Atacar)
                .addGap(18, 18, 18)
                .addComponent(ChangeWeapon, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ElegirArma)
                .addGap(18, 18, 18)
                .addComponent(Nothing)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Nada)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        Auxiliar.setBackground(new java.awt.Color(204, 255, 255));

        javax.swing.GroupLayout AuxiliarLayout = new javax.swing.GroupLayout(Auxiliar);
        Auxiliar.setLayout(AuxiliarLayout);
        AuxiliarLayout.setHorizontalGroup(
            AuxiliarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(567, 567, Short.MAX_VALUE)
        );
        AuxiliarLayout.setVerticalGroup(
            AuxiliarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(90, 90, Short.MAX_VALUE)
        );

        Mapa.setBackground(new java.awt.Color(51, 102, 0));

        javax.swing.GroupLayout MapaLayout = new javax.swing.GroupLayout(Mapa);
        Mapa.setLayout(MapaLayout);
        MapaLayout.setHorizontalGroup(
            MapaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(567, 567, 567)
        );
        MapaLayout.setVerticalGroup(
            MapaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(Controles, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Mapa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Auxiliar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Controles, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(Mapa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Auxiliar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void UpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UpActionPerformed
        // FLECHA ARRIBA
    }//GEN-LAST:event_UpActionPerformed

    private void ChangeWeaponActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChangeWeaponActionPerformed
        // CAMBIAR ARMA
    }//GEN-LAST:event_ChangeWeaponActionPerformed

    private void DownActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DownActionPerformed
        // FLECHA ABAJO
    }//GEN-LAST:event_DownActionPerformed

    private void LeftActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LeftActionPerformed
        // FLECHA IZQUIERDA
    }//GEN-LAST:event_LeftActionPerformed

    private void RightActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RightActionPerformed
        // FLECHA DERECHA
    }//GEN-LAST:event_RightActionPerformed

    private void SearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SearchActionPerformed
        // BUSCAR
    }//GEN-LAST:event_SearchActionPerformed

    private void AttackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AttackActionPerformed
        // ATACAR
    }//GEN-LAST:event_AttackActionPerformed

    private void NothingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NothingActionPerformed
        // BOTON NADA
    }//GEN-LAST:event_NothingActionPerformed

    public class ImagePanel extends JPanel {
        private Image backgroundImage;

        public ImagePanel(String imagePath) {
            this.backgroundImage = new ImageIcon(getClass().getResource(imagePath)).getImage();
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }

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
            java.util.logging.Logger.getLogger(InterfazGrafica.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(InterfazGrafica.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(InterfazGrafica.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(InterfazGrafica.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new InterfazGrafica().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Atacar;
    private javax.swing.JButton Attack;
    private javax.swing.JPanel Auxiliar;
    private javax.swing.JLabel Buscar;
    private javax.swing.JButton ChangeWeapon;
    private javax.swing.JPanel Controles;
    private javax.swing.JButton Down;
    private javax.swing.JLabel ElegirArma;
    private javax.swing.JButton Left;
    private javax.swing.JPanel Mapa;
    private javax.swing.JLabel Moverse;
    private javax.swing.JLabel Nada;
    private javax.swing.JButton Nothing;
    private javax.swing.JButton Right;
    private javax.swing.JButton Search;
    private javax.swing.JButton Up;
    // End of variables declaration//GEN-END:variables
}
