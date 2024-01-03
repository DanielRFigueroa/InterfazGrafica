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
    private static JLabel[][] tablero;

    public InterfazGrafica() {

        System.setProperty("awt.useSystemAAFontSettings", "on");
        System.setProperty("swing.aatext", "true");

        askNumberOfPlayers();  // Preguntar al usuario por el número de jugadores
        initComponents(); // Inicializar la interfaz gráfica después de obtener el número de jugadores
        createTablero(); // Crea el tablero dependiendo del numero de jugadores
        inicioSupervivientes(numeroDeJugadores);
        generarZombies(numeroDeJugadores);
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
    private void createTablero() {
        int gridSize = 7 + numeroDeJugadores - 1;
        tablero = new JLabel[gridSize][gridSize];

        Mapa.setLayout(new GridLayout(gridSize, gridSize));

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

        for (int row = 0; row < gridSize; row++) {
            for (int col = 0; col < gridSize; col++) {
                JLabel casilla = new JLabel();
                casilla.setLayout(null);
                casilla.setPreferredSize(new Dimension(casillaSize, casillaSize)); // Establece el tamaño de cada casilla

                // Añadimos imagen de fondo
                ImageIcon originalIcon = new ImageIcon(getClass().getResource("/com/proyecto/imagenes/texturacuadrada.jpg"));
                Image originalImage = originalIcon.getImage();
                Image scaledImage = originalImage.getScaledInstance(casillaSize, casillaSize, Image.SCALE_SMOOTH);
                ImageIcon scaledIcon = new ImageIcon(scaledImage);
                casilla.setIcon(scaledIcon);

                casilla.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                tablero[row][col] = casilla;
                Mapa.add(casilla);
            }
        }
        Mapa.revalidate();
        Mapa.repaint();
    }

    private ImageIcon scaleImageIcon(String imagePath, int width, int height) {
        ImageIcon originalIcon = new ImageIcon(getClass().getResource(imagePath));
        Image originalImage = originalIcon.getImage();
        Image scaledImage = originalImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(scaledImage);
    }

    public void cambiarImagenCasilla(int coordX, int coordY, String imagePath) {
        // Verifica que las coordenadas estén dentro de los límites del tablero
        if (coordX >= 0 && coordX < tablero.length && coordY >= 0 && coordY < tablero[0].length) {
            // Añade la nueva imagen a la casilla sin afectar la imagen de fondo
            ImageIcon newIcon = new ImageIcon(getClass().getResource("/com/proyecto/imagenes/Atacarx.png"));
            Image newImage = newIcon.getImage();
            Image scaledImage = newImage.getScaledInstance(tablero[coordX][coordY].getWidth(), tablero[coordX][coordY].getHeight(), Image.SCALE_SMOOTH);
            ImageIcon scaledIcon = new ImageIcon(scaledImage);
            tablero[coordX][coordY].setIcon(scaledIcon);
        }
    }

    private void inicioSupervivientes(int numeroDeJugadores) {
        JLabel casilla = tablero[0][0];

        // Tamaño de la casilla
        int casillaSize = casilla.getPreferredSize().width;

        int maxImagenesPorFila = 2;

        // Número máximo de imágenes a mostrar
        int maxImagenes = 4;

        // Calcula el número real de imágenes a mostrar basado en el número de jugadores
        int numImagenes = Math.min(numeroDeJugadores, maxImagenes);

        // Espaciado entre las imágenes
        int spacing = 5;

        // Calcula el tamaño de cada imagen
        int imageHeight = 0;
        int imageWidth = 0;
        if (numeroDeJugadores == 1) {
            imageHeight = 40;
            imageWidth = 40;
        } else if (numeroDeJugadores == 2) {
            imageHeight = 30;
            imageWidth = 30;
        } else if (numeroDeJugadores == 3) {
            imageHeight = 28;
            imageWidth = 28;
        } else if (numeroDeJugadores == 4) {
            imageHeight = 25;
            imageWidth = 25;
        }

        // Coordenadas iniciales para la primera imagen
        int x = 0;
        int y = 0;

        // Crea las imágenes y las agrega a la casilla
        for (int i = 0; i < numImagenes; i++) {
            JLabel label = new JLabel(new ImageIcon(getClass().getResource("/com/proyecto/imagenes/monito2.jpg")));
            label.setBounds(x, y, imageWidth, imageHeight);
            casilla.add(label);

            // Actualiza las coordenadas para la próxima imagen
            x += imageWidth + spacing;
            if ((i + 1) % maxImagenesPorFila == 0) {
                x = 0;
                y += imageHeight + spacing;
            }
        }

        // Repinta la casilla para que se reflejen los cambios
        casilla.revalidate();
        casilla.repaint();
    }

    private void generarZombies(int numeroDeJugadores) {
        int maxImagenesPorFila = 2;
        int maxImagenes = numeroDeJugadores * 3;

        // Espaciado entre las imágenes
        int spacing = 5;

        for (int i = 0; i < maxImagenes; i++) {
            // Obtenemos una casilla aleatoria diferente de (0, 0)
            int randomRow, randomCol;
            do {
                randomRow = (int) (Math.random() * tablero.length);
                randomCol = (int) (Math.random() * tablero[0].length);
            } while (randomRow == 0 && randomCol == 0);

            JLabel casilla = tablero[randomRow][randomCol];

            // Tamaño de la casilla
            int casillaSize = casilla.getPreferredSize().width;

            // Calcula el tamaño de cada imagen
            int imageHeight = 0;
            int imageWidth = 0;
            if (numeroDeJugadores == 1) {
                imageHeight = 40;
                imageWidth = 40;
            } else if (numeroDeJugadores == 2) {
                imageHeight = 30;
                imageWidth = 30;
            } else if (numeroDeJugadores == 3) {
                imageHeight = 28;
                imageWidth = 28;
            } else if (numeroDeJugadores == 4) {
                imageHeight = 25;
                imageWidth = 25;
            }

            // Crea la imagen y la agrega a la casilla
            JLabel label = new JLabel(new ImageIcon(getClass().getResource("/com/proyecto/imagenes/zombie.png")));
            label.setBounds(randomRow, randomCol, imageWidth, imageHeight);
            casilla.add(label);

            /*randomRow += imageWidth + spacing;
            if ((i + 1) % maxImagenesPorFila == 0) {
                randomRow = 0;
                randomCol += imageHeight + spacing;
            }*/

            // Repinta la casilla para que se reflejen los cambios
            casilla.revalidate();
            casilla.repaint();
        }
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

        // Intento de poner una imagen de fondo en el panel controles en vez de color de fondo
        Controles = new ImagePanel("/com/proyecto/imagenes/fondo1.png");
        //Controles.setBackground(new java.awt.Color(102, 102, 102));

        Up.setBackground(new java.awt.Color(0, 0, 0));
        Up.setIcon(scaleImageIcon("/com/proyecto/imagenes/flechaup.png", 60, 60));
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
        // numerox = 2;
        // cambiarImagenCasilla(coordsuperviviente, coordsuperviviente, "/com/proyecto/imagenes/Superviviente.png");
    }//GEN-LAST:event_UpActionPerformed

    private void DownActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DownActionPerformed
        // FLECHA ABAJO
    }//GEN-LAST:event_DownActionPerformed

    private void LeftActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LeftActionPerformed
        // FLECHA IZQUIERDA
    }//GEN-LAST:event_LeftActionPerformed

    private void RightActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RightActionPerformed
        // FLECHA DERECHA
    }//GEN-LAST:event_RightActionPerformed

    private void ChangeWeaponActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChangeWeaponActionPerformed
        // CAMBIAR ARMA
    }//GEN-LAST:event_ChangeWeaponActionPerformed

    private void SearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SearchActionPerformed
        // BUSCAR
    }//GEN-LAST:event_SearchActionPerformed

    private void AttackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AttackActionPerformed
        // ATACAR
    }//GEN-LAST:event_AttackActionPerformed

    private void NothingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NothingActionPerformed
        // BOTON NADA
        cambiarImagenCasilla(0, 3, "/com/proyecto/imagenes/Atacarx.png");

    }//GEN-LAST:event_NothingActionPerformed

    // Clase para poder usar imagenes de fondo en los jpanel, por el momento solo la he usado en Controles
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
        // Set the Nimbus look and feel, esto lo tengo que instalar, pero lo dejo asi por si se utiliza este codigo en otra compu
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
